package com.betamedia.atom.core.fwtestrunner;

import com.betamedia.atom.core.fwtestrunner.classloader.ContextClassLoaderManagingExecutor;
import com.betamedia.atom.core.fwtestrunner.listeners.TestTaskCompletionListener;
import com.betamedia.atom.core.fwtestrunner.runner.TestRunner;
import com.betamedia.atom.core.fwtestrunner.scheduling.ExecutionListener;
import com.betamedia.atom.core.fwtestrunner.scheduling.ObservableSupplier;
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

    private Map<TestRunnerType, TestRunner> runners;

    @Autowired
    private void setRunners(List<TestRunner> runnersList) {
        this.runners = runnersList.stream()
                .collect(Collectors.toMap(TestRunner::getType, m -> m));
    }

    @Override
    public List<TestTask> handleTask(Properties properties, MultipartFile[] suites, MultipartFile tempJar, List<TestTaskCompletionListener> listeners) {
        String tempDirectory = UUID.randomUUID().toString();
        List<String> suitePaths = storageService.storeToTemp(suites, tempDirectory);
        String tempJarPath = storageService.storeToTemp(tempJar, tempDirectory);
        return handleTask(properties, suitePaths, tempJarPath, listeners);
    }

    @Override
    public List<TestTask> handleTask(Properties properties, List<String> suitePaths, String tempJarPath, List<TestTaskCompletionListener> listeners) {
        List<TestTask> tasks = getTasks(properties, suitePaths);
        async(() -> classLoaderExecutor.run(getTaskExecutions(tasks), tempJarPath), listeners);
        return tasks;
    }

    @Override
    public List<ExecutionArguments> handle(Properties properties, MultipartFile[] suites, MultipartFile tempJar, ExecutionListener<List<RunnerResult>> listener) {
        String tempDirectory = UUID.randomUUID().toString();
        List<String> suitePaths = storageService.storeToTemp(suites, tempDirectory);
        String tempJarPath = null;
        if (tempJar != null) {
            tempJarPath = storageService.storeToTemp(tempJar, tempDirectory);
        }
        return handle(properties, suitePaths, tempJarPath, listener);
    }

    @Override
    public List<ExecutionArguments> handle(Properties properties, List<String> suitePaths, String tempJarPath, ExecutionListener<List<RunnerResult>> listener) {
        List<ExecutionArguments> argsList = getExecutionArguments(properties, suitePaths);
        execute(() -> classLoaderExecutor.run(getExecutions(argsList), tempJarPath), listener);
        return argsList;
    }

    private List<ExecutionArguments> getExecutionArguments(Properties properties, List<String> suites) {
        return PropertiesUtils.permute(properties).stream()
                .map(p -> new ExecutionArguments(p, suites))
                .collect(Collectors.toList());
    }

    private List<TestTask> getTasks(Properties properties, List<String> suites) {
        return PropertiesUtils.permute(properties).stream()
                .map(p -> TestTask.builder()
                        .withStatus(TestTask.Status.CREATED)
                        .withProperties(properties)
                        .withSuites(suites)
                        .build())
                .collect(Collectors.toList());
    }


    private void execute(Supplier<List<RunnerResult>> supplier, ExecutionListener<List<RunnerResult>> listener) {
        asyncTaskExecutor.execute(() -> new ObservableSupplier(supplier, listener).get());
    }

    private void async(Supplier<List<TestTask>> supplier, List<TestTaskCompletionListener> listeners) {
        asyncTaskExecutor.execute(() -> {
            List<TestTask> results = supplier.get();
            listeners.forEach(results::forEach);
        });
    }

    private List<Supplier<RunnerResult>> getExecutions(List<ExecutionArguments> argsList) {
        return argsList.stream()
                .map(args -> (Supplier<RunnerResult>) () -> run(args))
                .collect(Collectors.toList());
    }

    private List<Supplier<TestTask>> getTaskExecutions(List<TestTask> tasks) {
        return tasks.stream()
                .map(task -> (Supplier<TestTask>) () -> run(task))
                .collect(Collectors.toList());
    }

    private RunnerResult run(ExecutionArguments args) {
        return Optional.ofNullable(runners.get(getType(args.properties)))
                .orElseThrow(() -> new RuntimeException("No corresponding runner"))
                .run(args.properties, args.suites, args.reportDirectory);
    }

    private TestTask run(TestTask task) {
        return Optional.ofNullable(runners.get(getType(task.properties)))
                .orElseThrow(() -> new RuntimeException("No corresponding runner"))
                .run(task);
    }

    private TestRunnerType getType(Properties properties) {
        return TestRunnerType.parse((String) properties.get(ConfigurationPropertyKey.RUNNER_TYPE));
    }

}
