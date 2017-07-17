package com.betamedia.atom.app.scheduling.impl;

import com.betamedia.atom.app.executor.AsyncTestExecutor;
import com.betamedia.atom.app.handler.TestHandlerImpl;
import com.betamedia.atom.app.entity.TestInformation;
import com.betamedia.atom.app.handler.TestInformationHandler;
import com.betamedia.atom.app.reporting.EmailService;
import com.betamedia.atom.app.scheduling.ContinuousTestFactory;
import com.betamedia.atom.app.scheduling.ContinuousTestHandler;
import com.betamedia.atom.app.storage.TempStorageService;
import com.betamedia.atom.core.utils.PropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Manages execution of scheduled and repeating tests. Stores input files and parses input parameters just like
 * {@link TestHandlerImpl}, but encapsulates test execution data in {@link ContinuousTest}
 * objects that manage their own lifecycle through execution callbacks. Stores active {@link ContinuousTest}s and allows
 * their management - getting information and stopping or aborting execution.
 *
 * @author mbelyaev
 * @since 4/19/17
 */
@Component
public class ContinuousTestHandlerImpl implements ContinuousTestHandler {
    @Autowired
    private AsyncTestExecutor asyncTestExecutor;
    @Autowired
    private TempStorageService storageService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ContinuousTestFactory continuousTestFactory;
    @Autowired
    private TestInformationHandler testInformationHandler;

    private ConcurrentMap<UUID, ContinuousTest> continuousTasks = new ConcurrentHashMap<>();

    @Override
    public List<TestInformation> handleTest(String name, Properties properties, MultipartFile[] suites, Optional<String> cronExpression, String emailAddress) {
        String tempDirectory = UUID.randomUUID().toString();
        Function<MultipartFile, String> store = file -> storageService.storeToTemp(file, tempDirectory);
        List<String> suitePaths = Arrays.stream(suites).map(store).collect(Collectors.toList());
        List<TestInformation> tests = getTests(name, properties, suitePaths, cronExpression);
        Consumer<TestInformation> onSuccess = t -> {
            testInformationHandler.put(t);
            if (t.hasFailed)
                emailService.sendLocalFile(emailAddress, name, t.emailReportURL, t.attachmentURLs);
        };
        tests.stream()
                .peek(testInformationHandler::put)
                .map(t -> new Tuple<>(t, continuousTestFactory.get(() -> getExecution(t, onSuccess, cause -> onSuccess.accept(mapFailure(t, cause))), cronExpression)))
                .forEach(t -> {
                    Optional.ofNullable(continuousTasks.put(t.first.id, t.second)).ifPresent(ContinuousTest::stop);
                    t.second.start();
                });
        return tests;
    }

    @Override
    public void stop(UUID id) {
        Optional.ofNullable(continuousTasks.remove(id))
                .ifPresent(ContinuousTest::stop);
    }

    @Override
    public boolean abort(UUID id) {
        return Optional.ofNullable(continuousTasks.remove(id))
                .map(ContinuousTest::abort)
                .orElse(false);
    }

    @Override
    public Set<TestInformation> getInfo() {
        return continuousTasks.keySet().stream()
                .map(k -> testInformationHandler.get(k))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    private ListenableFuture<TestInformation> getExecution(TestInformation testInformation, Consumer<TestInformation> onSuccess, Consumer<Throwable> onFailure) {
        ListenableFuture<TestInformation> future = asyncTestExecutor.submit(testInformation, Optional.empty());
        future.addCallback(onSuccess::accept, onFailure::accept);
        return future;
    }

    private static TestInformation mapFailure(TestInformation test, Throwable cause) {
        return test.update().withStatus(TestInformation.Status.ABORTED).withCause(cause).build();
    }

    private static List<TestInformation> getTests(String name, Properties properties, List<String> suites, Optional<String> cronExpression) {
        return PropertiesUtils.permute(properties).stream()
                .map(p -> TestInformation.builder()
                        .isContinuous(true)
                        .withName(name)
                        .withCronExpression(cronExpression.orElse(""))
                        .withStatus(TestInformation.Status.CREATED)
                        .withProperties(p)
                        .withSuites(suites)
                        .build())
                .collect(Collectors.toList());
    }

    private static class Tuple<T1, T2> {
        public final T1 first;
        public final T2 second;

        public Tuple(T1 first, T2 second) {
            this.first = first;
            this.second = second;
        }
    }
}
