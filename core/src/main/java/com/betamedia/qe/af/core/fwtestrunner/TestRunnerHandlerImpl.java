package com.betamedia.qe.af.core.fwtestrunner;

import com.betamedia.qe.af.core.fwtestrunner.classloader.ContextClassLoaderManagingExecutor;
import com.betamedia.qe.af.core.fwtestrunner.runner.TestRunner;
import com.betamedia.qe.af.core.fwtestrunner.scheduling.ExecutionListener;
import com.betamedia.qe.af.core.fwtestrunner.scheduling.ObservableRunnable;
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
    public List<String> handle(Properties properties, List<String> suites, MultipartFile tempJar, ExecutionListener listener) {
        List<ExecutionArguments> argsList = getExecutionArguments(properties, suites);
        if (tempJar != null) {
            execute(() -> classLoaderExecutor.run(getExecutions(argsList), tempJar), listener);
        } else {
            execute(() -> classLoaderExecutor.run(getExecutions(argsList)), listener);
        }
        return argsList.stream().map(ExecutionArguments::getReportUrl).collect(Collectors.toList());
    }

    private List<ExecutionArguments> getExecutionArguments(Properties properties, List<String> suites) {
        return permute(properties).stream()
                .map(p -> new ExecutionArguments(p, suites))
                .collect(Collectors.toList());
    }

    private void execute(Runnable runnable, ExecutionListener listener) {
        Executors.newSingleThreadExecutor().execute(new ObservableRunnable(runnable, listener));
    }

    private List<Runnable> getExecutions(List<ExecutionArguments> argsList) {
        return argsList.stream()
                .map(args -> (Runnable) () -> run(args))
                .collect(Collectors.toList());
    }

    private void run(ExecutionArguments args) {
        Optional.ofNullable(runners.get(getType(args.properties)))
                .orElseThrow(() -> new RuntimeException("No corresponding runner"))
                .run(args.properties, args.suites, args.reportPath);
    }

    private TestRunnerType getType(Properties properties) {
        return TestRunnerType.parse((String) properties.get(ConfigurationPropertyKey.RUNNER_TYPE));
    }

    private class ExecutionArguments {
        public final Properties properties;
        public final String reportPath;
        public final List<String> suites;

        ExecutionArguments(Properties properties, List<String> suites) {
            this.properties = properties;
            this.reportPath = getReportPath(properties);
            this.suites = suites;
        }

        public String getReportUrl() {
            return reportPath + "/index.html";
        }

        private String getReportPath(Properties props) {
            LocalDateTime now = LocalDateTime.now();
            return (now.toString() + "." + Objects.hash(props, now, Thread.currentThread())).replaceAll("[^a-zA-Z0-9]", "_");
        }
    }
}
