package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.betamedia.atom.core.fwtestrunner.RunnerResult;
import com.betamedia.atom.core.fwtestrunner.TestRunnerHandler;
import com.betamedia.atom.core.fwtestrunner.reporting.EmailService;
import com.betamedia.atom.core.fwtestrunner.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author mbelyaev
 * @since 4/19/17
 */
@Component
public class TestExecutionManagerImpl implements TestExecutionManager {
    @Autowired
    private TestRunnerHandler handler;
    @Autowired
    private TaskScheduler taskScheduler;
    @Autowired
    private TaskExecutor asyncTaskExecutor;
    @Autowired
    private EmailService emailService;
    @Autowired
    private StorageService storageService;

    private ConcurrentMap<String, TestExecution> executions = new ConcurrentHashMap<>();

    @Override
    public void createRepeatingTest(String name, String mailAddress, Properties properties, MultipartFile[] suites) {
        addTestExecution(
                name,
                new RepeatingTestExecution<>(
                        getExecutionWithCallback(properties, suites, sender(mailAddress)),
                        asyncTaskExecutor));
    }

    @Override
    public void createScheduledTest(String name, String mailAddress, Properties properties, MultipartFile[] suites, String cronExpression) {
        addTestExecution(
                name,
                new ScheduledTestExecution<>(
                        getExecutionWithCallback(properties, suites, sender(mailAddress)),
                        cronExpression,
                        taskScheduler));
    }

    @Override
    public void stopTask(String name) {
        Optional.ofNullable(executions.remove(name))
                .ifPresent(TestExecution::stop);
    }

    @Override
    public Set<Map<String, String>> getInfo() {
        return executions.entrySet()
                .stream()
                .map(e -> {
                    Map<String, String> info = new HashMap<>(e.getValue().getInfo());
                    info.put("name", e.getKey());
                    return info;
                })
                .collect(Collectors.toSet());
    }

    private void addTestExecution(String name, TestExecution execution) {
        Optional.ofNullable(executions.put(name, execution))
                .ifPresent(TestExecution::stop);
        execution.start();
    }

    private Consumer<ExecutionListener<List<RunnerResult>>> getExecution(Properties properties, MultipartFile[] suites) {
        return executionListener -> handler.handle(properties, suites, null, executionListener);
    }

    private Consumer<ExecutionListener<List<RunnerResult>>> getExecutionWithCallback(Properties properties, MultipartFile[] suites, Consumer<RunnerResult> callback) {
        String tempDirectory = UUID.randomUUID().toString();
        List<String> suitePaths = storageService.storeToTemp(suites, tempDirectory);
        return executionListener -> handler.handle(properties, suitePaths, null, result -> {
            result.forEach(callback);
            executionListener.onCompletion(result);
        });
    }

    private Consumer<RunnerResult> sender(String mailAddress) {
        return result -> {
            if (result.hasNotPassed() && mailAddress != null && !mailAddress.isEmpty()) {
                emailService.sendLocalFile(mailAddress, "testReport", result.getOutputPath(), result.getAttachmentPaths());
            }
        };
    }
}
