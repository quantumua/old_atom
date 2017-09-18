package com.betamedia.atom.app.executor;

import com.betamedia.atom.app.classloader.ContextClassLoaderManagingExecutor;
import com.betamedia.atom.app.entity.TestInformation;
import com.betamedia.atom.app.runner.TestRunner;
import com.betamedia.atom.app.types.TestRunnerType;
import com.betamedia.atom.core.dsl.type.ProductType;
import com.betamedia.atom.core.fwtestrunner.environment.initializer.TestRunningEnvInitializerProvider;
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

import static com.betamedia.atom.core.dsl.type.TestConfigurationKeys.RUNNER_TYPE;

/**
 * Executes the test run asynchronously, backed by Spring's {@link AsyncListenableTaskExecutor} implementation.
 * Selects the proper test runner and environment initializer according to test properties, generates output report path
 * and decorates execution with {@link ContextClassLoaderManagingExecutor}.
 *
 * @author mbelyaev
 * @since 6/26/17
 */
@Service
public class AsyncTestExecutorImpl implements AsyncTestExecutor {
    @Autowired
    private ContextClassLoaderManagingExecutor classLoaderExecutor;
    @Autowired
    private AsyncListenableTaskExecutor asyncTaskExecutor;
    @Autowired
    private TestRunningEnvInitializerProvider testRunningEnvInitializers;
    private Map<TestRunnerType, TestRunner> runners;

    @Autowired
    private void setRunners(List<TestRunner> runnersList) {
        this.runners = runnersList.stream()
                .collect(Collectors.toMap(TestRunner::getType, m -> m));
    }

    @Override
    public ListenableFuture<TestInformation> submit(TestInformation testInformation, Optional<String> tempJarPath) {
        return asyncTaskExecutor.submitListenable(() -> classLoaderExecutor.run(getExecution(testInformation, runners, testRunningEnvInitializers), tempJarPath));
    }

    private static Supplier<TestInformation> getExecution(TestInformation test, Map<TestRunnerType, TestRunner> runners, TestRunningEnvInitializerProvider testRunningEnvInitializers) {
        return () -> {
            TestInformation testWithDirectory = test.update()
                    .withTime(LocalDateTime.now())
                    .updateReportDirectory()
                    .build();
            testRunningEnvInitializers.get(ProductType.TP).initializeEnvironment(test.properties);
            return Optional.ofNullable(runners.get(getType(testWithDirectory.properties)))
                    .orElseThrow(() -> new RuntimeException("No corresponding runner"))
                    .run(testWithDirectory);
        };
    }

    private static TestRunnerType getType(Properties properties) {
        return TestRunnerType.parse((String) properties.get(RUNNER_TYPE.getKey()));
    }

}
