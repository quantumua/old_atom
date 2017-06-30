package com.betamedia.atom.core.fwtestrunner;

import com.betamedia.atom.core.fwtestrunner.classloader.ContextClassLoaderManagingExecutor;
import com.betamedia.atom.core.fwtestrunner.runner.TestRunner;
import com.betamedia.atom.core.fwtestrunner.types.TestRunnerType;
import com.betamedia.atom.core.holders.ConfigurationPropertyKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author mbelyaev
 * @since 6/26/17
 */
@Service
public class AsyncTestExecutorImpl implements AsyncTestExecutor {
    @Autowired
    private ContextClassLoaderManagingExecutor classLoaderExecutor;
    @Autowired
    private AsyncListenableTaskExecutor asyncTaskExecutor;
    private Map<TestRunnerType, TestRunner> runners;

    @Autowired
    private void setRunners(List<TestRunner> runnersList) {
        this.runners = runnersList.stream()
                .collect(Collectors.toMap(TestRunner::getType, m -> m));
    }

    @Override
    public ListenableFuture<TestInformation> submit(TestInformation testInformation, Optional<String> tempJarPath) {
        return asyncTaskExecutor.submitListenable(() -> classLoaderExecutor.run(getExecution(testInformation, runners), tempJarPath));
    }

    private static Supplier<TestInformation> getExecution(TestInformation test, Map<TestRunnerType, TestRunner> runners) {
        return () -> {
            TestInformation testWithDirectory = test.update()
                    .withTime(LocalDateTime.now())
                    .updateReportDirectory()
                    .build();
            return Optional.ofNullable(runners.get(getType(testWithDirectory.properties)))
                    .orElseThrow(() -> new RuntimeException("No corresponding runner"))
                    .run(testWithDirectory);
        };
    }

    private static TestRunnerType getType(Properties properties) {
        return TestRunnerType.parse((String) properties.get(ConfigurationPropertyKey.RUNNER_TYPE));
    }

}