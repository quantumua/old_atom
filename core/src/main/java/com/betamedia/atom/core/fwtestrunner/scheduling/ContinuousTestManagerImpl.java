package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.betamedia.atom.core.fwtestrunner.TestInformation;
import com.betamedia.atom.core.fwtestrunner.TestRunnerHandler;
import com.betamedia.atom.core.fwtestrunner.reporting.EmailService;
import com.betamedia.atom.core.fwtestrunner.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author mbelyaev
 * @since 4/19/17
 */
@Component
public class ContinuousTestManagerImpl implements ContinuousTestManager {
    @Autowired
    private TestRunnerHandler handler;
    @Autowired
    private StorageService storageService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ContinuousTestFactory scheduledTaskFactory;
    @Autowired
    private ContinuousTestFactory repeatingTaskFactory;

    private ConcurrentMap<UUID, ContinuousTest> continuousTasks = new ConcurrentHashMap<>();

    @Override
    public TestInformation createTest(String name, String emailAddress, Properties properties, MultipartFile[] suites, Optional<String> cronExpression) {
        String tempDirectory = UUID.randomUUID().toString();
        Function<MultipartFile, String> store = file -> storageService.storeToTemp(file, tempDirectory);
        List<String> suitePaths = Arrays.stream(suites).map(store).collect(Collectors.toList());
        Consumer<List<TestInformation>> emailListener = tests ->
                tests.stream()
                        .filter(t -> t.hasFailed)
                        .forEach(t -> emailService.sendLocalFile(emailAddress, name, t.emailReportURL, t.attachmentURLs));
        Function<Consumer<List<TestInformation>>, List<TestInformation>> emailingExecution = getTestExecution(properties, suitePaths, emailListener);
        TestInformation testInformation = TestInformation.builder()
                .isContinuous(true)
                .withName(name)
                .withEmailAddress(emailAddress)
                .withCronExpression(cronExpression.orElse(""))
                .withStatus(TestInformation.Status.CREATED)
                .build();
        ContinuousTest test = cronExpression.isPresent() ? scheduledTaskFactory.get(testInformation, emailingExecution) : repeatingTaskFactory.get(testInformation, emailingExecution);
        Optional.ofNullable(
                continuousTasks.put(testInformation.id, test))
                .ifPresent(ContinuousTest::stop);
        test.start();
        return testInformation;
    }

    @Override
    public void stopTest(UUID name) {
        Optional.ofNullable(continuousTasks.remove(name))
                .ifPresent(ContinuousTest::stop);
    }

    @Override
    public Set<TestInformation> getInfo() {
        return continuousTasks.entrySet()
                .stream()
                .map(e -> e.getValue().getTestInformation())
                .collect(Collectors.toSet());
    }

    private Function<Consumer<List<TestInformation>>, List<TestInformation>> getTestExecution(Properties properties, List<String> suitePaths, Consumer<List<TestInformation>> listener) {
        return injectedListener ->
                handler.handleTest(properties, suitePaths, Optional.empty(), listener.andThen(injectedListener));

    }

}
