package com.betamedia.atom.core.fwtestrunner;

import com.betamedia.atom.core.fwtestrunner.classloader.ContextClassLoaderManagingExecutor;
import com.betamedia.atom.core.fwtestrunner.listeners.TestTaskCompletionListener;
import com.betamedia.atom.core.fwtestrunner.runner.TestRunner;
import com.betamedia.atom.core.fwtestrunner.storage.StorageService;
import com.betamedia.atom.core.fwtestrunner.types.TestRunnerType;
import com.betamedia.atom.core.holders.ConfigurationPropertyKey;
import com.betamedia.atom.core.utils.PropertiesUtils;
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
    public List<TestInformation> handleTest(Properties properties, MultipartFile[] suites, Optional<MultipartFile> tempJar, List<TestTaskCompletionListener> listeners) {
        String tempDirectory = UUID.randomUUID().toString();
        Function<MultipartFile, String> store = file -> storageService.storeToTemp(file, tempDirectory);
        List<String> suitePaths = Arrays.stream(suites).map(store).collect(Collectors.toList());
        Optional<String> tempJarPath = tempJar.map(store);
        return handleTest(properties, suitePaths, tempJarPath, listeners);
    }

    @Override
    public List<TestInformation> handleTest(Properties properties, List<String> suitePaths, Optional<String> tempJarPath, List<TestTaskCompletionListener> listeners) {
        List<TestInformation> tasks = getTests(properties, suitePaths);
        async(() -> classLoaderExecutor.run(getTestExecutions(tasks), tempJarPath), listeners);
        return tasks;
    }

    private List<TestInformation> getTests(Properties properties, List<String> suites) {
        return PropertiesUtils.permute(properties).stream()
                .map(p -> testInformationHandler.builder()
                        .withStatus(TestInformation.Status.CREATED)
                        .withProperties(properties)
                        .withSuites(suites)
                        .build())
                .peek(testInformationHandler::put)
                .collect(Collectors.toList());
    }

    private void async(Supplier<List<TestInformation>> supplier, List<TestTaskCompletionListener> listeners) {
        asyncTaskExecutor.execute(() -> {
            List<TestInformation> results = supplier.get();
            listeners.forEach(results::forEach);
        });
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
