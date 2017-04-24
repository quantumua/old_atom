package com.betamedia.qe.af.core.fwtestrunner.scheduling;

import com.betamedia.qe.af.core.fwtestrunner.TestRunnerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by mbelyaev on 4/19/17.
 */
@Component
public class TestExecutionManagerImpl implements TestExecutionManager {
    @Autowired
    private TestRunnerHandler handler;

    private ConcurrentMap<String, TestExecution> executions = new ConcurrentHashMap<>();

    @Override
    public void createRepeatingTest(String name, Properties properties, List<String> suites) {
        addTestExecution(name, new RepeatingTestExecution(getExecution(properties, suites)));
    }

    @Override
    public void createScheduledTest(String name, Properties properties, List<String> suites, String cronExpression) {
        addTestExecution(name, new ScheduledTestExecution(getExecution(properties, suites), cronExpression));
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

    private Consumer<ExecutionListener> getExecution(Properties properties, List<String> suites) {
        return executionListener -> handler.handle(properties, suites, null, executionListener);
    }

}
