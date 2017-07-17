package com.betamedia.atom.app.handler;

import com.betamedia.atom.app.entity.TestInformation;
import com.betamedia.atom.app.executor.AsyncTestExecutor;
import com.betamedia.atom.app.executor.AsyncTestExecutorImpl;
import com.betamedia.atom.app.storage.TempStorageService;
import com.betamedia.atom.core.utils.PropertiesUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This service sets up test execution(s) according to passed parameters. Exposes entry points to handle uploaded
 * {@link MultipartFile}s of test suites and library jar, as well as a handler that takes the paths to stored local
 * files directly.<br/>
 * Uploaded files are stored in auto-generated directory and will not persist between deployments.<br/>
 * Handles test {@link Properties} with multiple values per key by generating a set of {@link Properties} with possible
 * combinations and executing each one as separate test run.<br/>
 * Tasks are then sent to {@link AsyncTestExecutorImpl} to run asynchronously and resulting {@link ListenableFuture}s
 * are orchestrated with callbacks and stored by task ID to provide task cancellation facilities.
 *
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
@Service
public class TestHandlerImpl implements TestHandler {
    private static final Logger logger = LogManager.getLogger(TestHandlerImpl.class);

    @Autowired
    private TempStorageService storageService;
    @Autowired
    private TestInformationHandler testInformationHandler;
    @Autowired
    private AsyncTestExecutor asyncTestExecutor;

    private ConcurrentMap<UUID, ListenableFuture<TestInformation>> runningTasks = new ConcurrentHashMap<>();

    @Override
    public List<TestInformation> handleTest(Properties properties, MultipartFile[] suites, Optional<MultipartFile> tempJar) {
        String tempDirectory = UUID.randomUUID().toString();
        Function<MultipartFile, String> store = file -> storageService.storeToTemp(file, tempDirectory);
        List<String> suitePaths = Arrays.stream(suites).map(store).collect(Collectors.toList());
        Optional<String> tempJarPath = tempJar.map(store);
        return handleTest(properties, suitePaths, tempJarPath);
    }

    @Override
    public List<TestInformation> handleTest(Properties properties, List<String> suitePaths, Optional<String> tempJarPath) {
        List<TestInformation> tests = getTests(properties, suitePaths);
        Consumer<TestInformation> onSuccess = t -> {
            runningTasks.remove(t.id);
            testInformationHandler.put(t);
        };
        tests.stream()
                .map(t -> new Tuple<>(t, asyncTestExecutor.submit(t, tempJarPath)))
                .peek(t -> t.second.addCallback(onSuccess::accept, cause -> onSuccess.accept(mapFailure(t.first, cause))))
                .forEach(t -> runningTasks.put(t.first.id, t.second));
        return tests;
    }

    @Override
    public boolean abort(UUID uuid) {
        return runningTasks.get(uuid).cancel(true);
    }

    private static TestInformation mapFailure(TestInformation test, Throwable cause) {
        return test.update().withStatus(TestInformation.Status.ABORTED).withCause(cause).build();
    }

    private static List<TestInformation> getTests(Properties properties, List<String> suites) {
        return PropertiesUtils.permute(properties).stream()
                .map(p -> TestInformation.builder()
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
