package com.betamedia.atom.core.fwtestrunner;

import com.betamedia.atom.core.fwtestrunner.classloader.ContextClassLoaderManagingExecutor;
import com.betamedia.atom.core.fwtestrunner.listeners.TestCompletionListener;
import com.betamedia.atom.core.fwtestrunner.runner.TestRunner;
import com.betamedia.atom.core.fwtestrunner.storage.StorageService;
import com.betamedia.atom.core.fwtestrunner.types.TestRunnerType;
import com.betamedia.atom.core.holders.ConfigurationPropertyKey;
import com.betamedia.atom.core.utils.PropertiesUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
@Service
public class TestRunnerHandlerImpl implements TestRunnerHandler {
    private static final Logger logger = LogManager.getLogger(TestRunnerHandlerImpl.class);

    @Autowired
    private ContextClassLoaderManagingExecutor classLoaderExecutor;
    @Autowired
    private TaskExecutor asyncTaskExecutor;
    @Autowired
    private StorageService storageService;
    @Autowired
    private TestInformationHandler testInformationHandler;

    private Map<TestRunnerType, TestRunner> runners;

    @Autowired
    private void setRunners(List<TestRunner> runnersList) {
        this.runners = runnersList.stream()
                .collect(Collectors.toMap(TestRunner::getType, m -> m));
    }

    @Override
    public List<TestInformation> handleTest(Properties properties, MultipartFile[] suites, Optional<MultipartFile> tempJar, List<TestCompletionListener> listeners) {
        String tempDirectory = UUID.randomUUID().toString();
        Function<MultipartFile, String> store = file -> storageService.storeToTemp(file, tempDirectory);
        List<String> suitePaths = Arrays.stream(suites).map(store).collect(Collectors.toList());
        Optional<String> tempJarPath = tempJar.map(store);
        return handleTest(properties, suitePaths, tempJarPath, listeners);
    }

    @Override
    public List<TestInformation> handleTest(Properties properties, List<String> suitePaths, Optional<String> tempJarPath, List<TestCompletionListener> listeners) {
        List<TestInformation> tests = getTests(properties, suitePaths);
        async(() -> classLoaderExecutor.run(getTestExecutions(tests), tempJarPath),
                () -> tests.stream().map(t -> t.update().withStatus(TestInformation.Status.FAILED_TO_START).build()).collect(Collectors.toList()),
                listeners);
        return tests;
    }

    private List<TestInformation> getTests(Properties properties, List<String> suites) {
        return PropertiesUtils.permute(properties).stream()
                .map(p -> TestInformation.builder()
                        .withStatus(TestInformation.Status.CREATED)
                        .withProperties(properties)
                        .withSuites(suites)
                        .build())
                .collect(Collectors.toList());
    }

    private void async(Supplier<List<TestInformation>> supplier, Supplier<List<TestInformation>> onException, List<TestCompletionListener> listeners) {
        asyncTaskExecutor.execute(() -> {
            List<TestInformation> results = getResults(supplier, onException);
            results.forEach(testInformationHandler::put);
            listeners.forEach(l -> l.accept(results));
        });
    }

    private List<TestInformation> getResults(Supplier<List<TestInformation>> supplier, Supplier<List<TestInformation>> onException) {
        try {
            return supplier.get();
        } catch (RuntimeException e) {
            logger.error("Failed to execute tests!", e);
            return onException.get();
        }
    }

    private List<Supplier<TestInformation>> getTestExecutions(List<TestInformation> tasks) {
        return tasks.stream()
                .map(task -> (Supplier<TestInformation>) () -> run(task))
                .collect(Collectors.toList());
    }

    private TestInformation run(TestInformation task) {
        return Optional.ofNullable(runners.get(getType(task.properties)))
                .orElseThrow(() -> new RuntimeException("No corresponding runner"))
                .run(task);
    }

    private TestRunnerType getType(Properties properties) {
        return TestRunnerType.parse((String) properties.get(ConfigurationPropertyKey.RUNNER_TYPE));
    }

}
