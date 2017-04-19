package com.betamedia.qe.af.core.fwtestrunner;

import com.betamedia.qe.af.core.fwtestrunner.classloader.ContextClassLoaderManagingExecutor;
import com.betamedia.qe.af.core.fwtestrunner.runner.TestRunner;
import com.betamedia.qe.af.core.fwtestrunner.types.TestRunnerType;
import com.betamedia.qe.af.core.holders.ConfigurationPropertyKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static com.betamedia.qe.af.core.utils.PropertiesUtils.permute;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
@Service
public class TestRunnerHandlerImpl implements TestRunnerHandler {
    @Autowired
    private ContextClassLoaderManagingExecutor classLoaderExecutor;

    private Map<TestRunnerType, TestRunner> runners;

    @Autowired
    private void setRunners(List<TestRunner> runnersList) {
        this.runners = runnersList.stream()
                .collect(Collectors.toMap(TestRunner::getType, m -> m));
    }

    @Override
    public List<String> handle(Properties properties, List<String> suites, MultipartFile tempJar) {
        List<ExecutionArguments> argsList = getExecutionArguments(properties);

        List<Runnable> executions = argsList.stream()
                .map(args -> (Runnable) () -> run(args, suites))
                .collect(Collectors.toList());
        if (tempJar != null) {
            execute(() -> classLoaderExecutor.run(executions, tempJar));
        } else {
            execute(() -> classLoaderExecutor.run(executions));
        }

        return argsList.stream().map(args -> args.reportPath + "/index.html").collect(Collectors.toList());
    }

    private void execute(Runnable runnable) {
        Executors.newSingleThreadExecutor().execute(runnable);
    }

    private void run(ExecutionArguments args, List<String> suites) {
        Optional.ofNullable(runners.get(getType(args.properties)))
                .orElseThrow(() -> new RuntimeException("No corresponding runner"))
                .run(args.properties, suites, args.reportPath);
    }

    private List<ExecutionArguments> getExecutionArguments(Properties properties) {
        List<Properties> executionProperties = permute(properties);
        return executionProperties.stream()
                .map(p -> new ExecutionArguments(p, getReportPath(p)))
                .collect(Collectors.toList());
    }

    private String getReportPath(Properties props) {
        LocalDateTime now = LocalDateTime.now();
        return (now.toString() + "." + Objects.hash(props, now, Thread.currentThread())).replaceAll("[^a-zA-Z0-9]", "_");
    }

    private TestRunnerType getType(Properties properties) {
        return TestRunnerType.parse((String) properties.get(ConfigurationPropertyKey.RUNNER_TYPE));
    }

    private class ExecutionArguments {
        public final Properties properties;
        public final String reportPath;

        ExecutionArguments(Properties properties, String reportPath) {
            this.properties = properties;
            this.reportPath = reportPath;
        }
    }
}
