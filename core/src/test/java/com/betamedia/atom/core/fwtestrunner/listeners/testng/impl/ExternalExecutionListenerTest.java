package com.betamedia.atom.core.fwtestrunner.listeners.testng.impl;

import com.betamedia.atom.core.fwtestrunner.environment.TestRunningEnvInitializer;
import com.betamedia.atom.core.holders.AppContextHolder;
import com.betamedia.atom.core.holders.ConfigurationPropertiesProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

/**
 * @author mbelyaev
 * @since 7/28/17
 */
public class ExternalExecutionListenerTest {
    private static final Logger logger = LogManager.getLogger(ExternalExecutionListenerTest.class);

    private static TestRunningEnvInitializer mockEnvInitializer = mock(TestRunningEnvInitializer.class);

    @Test
    public void concurrentInitializationTest() throws Exception {
        int threadNum = 10;
        ExecutorService pool = new ThreadPoolExecutor(0, threadNum, 0, TimeUnit.SECONDS, new SynchronousQueue<>());
        AtomicInteger contextCount = new AtomicInteger(0);
        /*Instantiate and call ExternalExecutionListener#onExecutionStart from [threadNum] separate child threads and wait for completion*/
        CompletableFuture.allOf(
                IntStream.range(0, threadNum).boxed()
                        .map(i -> CompletableFuture.runAsync(() -> {
                            ExternalExecutionListener listener = new ExternalExecutionListener();
                            logger.info("Created listener");
                            setField(listener, "contextInitializer", MockContext.class);
                            logger.info("Attempting to initialize shared context");
                            listener.onExecutionStart();
                            logger.info("Context is present now");
        /*ExternalExecutionListener can't exit without functional context available*/
                            Assert.assertNotNull(AppContextHolder.getContext());
        /*Counting the amount of ExternalExecutionListener-managed contexts (should be 1)*/
                            Optional.ofNullable(getField(listener, "context")).ifPresent(ctx -> contextCount.incrementAndGet());
                        }, pool))
                        .toArray(CompletableFuture[]::new))
                .get();
        /*Context is expected to be initialized once*/
        verify(mockEnvInitializer).initializeEnvironment(any(Properties.class));
        /*Counting the amount of ExternalExecutionListener-managed contexts (should be 1)*/
        Assert.assertEquals(1, contextCount.get(), "Unexpected instantiated context count");
    }

    @Configuration
    public static class MockContext {
        @Bean
        public AppContextHolder appContextHolder() {
            return new AppContextHolder();
        }

        @Bean
        public TestRunningEnvInitializer testRunningEnvInitializer() {
            return mockEnvInitializer;
        }

        @Bean
        public ApplicationRunner runner(AnnotationConfigApplicationContext ctx) {
            return args -> ctx.register(ConfigurationPropertiesProvider.class);
        }
    }
}