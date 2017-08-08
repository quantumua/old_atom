package com.betamedia.atom.core.fwtestrunner.listeners.testng.impl;

import com.betamedia.atom.core.configuration.environment.TestEnvInitializerConfig;
import com.betamedia.atom.core.dsl.type.EnvironmentType;
import com.betamedia.atom.core.dsl.type.ProductType;
import com.betamedia.atom.core.fwtestrunner.environment.configuration.impl.TPTestConfigurationPropertiesParserImpl;
import com.betamedia.atom.core.fwtestrunner.environment.initializer.TestRunningEnvInitializer;
import com.betamedia.atom.core.holders.AppContextHolder;
import com.betamedia.atom.core.testingtype.annotations.TestConfigurationProperties;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.Test;

import java.util.Properties;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.ReflectionTestUtils.setField;

/**
 * @author mbelyaev
 * @since 7/28/17
 */
public class ContextInitializingListenerTest {
    private static final Logger logger = LogManager.getLogger(ContextInitializingListenerTest.class);

    private static TestRunningEnvInitializer mockEnvInitializer = mock(TestRunningEnvInitializer.class);

    private static AppContextHolder spyHolder = spy(AppContextHolder.class);

    @Test
    public void concurrentInitializationTest() throws Exception {
        /* TestNG's ITestContext is compiled with provided Google Guice dependencies.
         * We have to provide runtime definitions for required classes For Mockito to mock the interface correctly.
         */
        new ByteBuddy()
                .subclass(Object.class)
                .name("com.google.inject.Injector")
                .make()
                .load(ITestContext.class.getClassLoader(), ClassLoadingStrategy.Default.INJECTION);
        new ByteBuddy()
                .subclass(Object.class)
                .name("com.google.inject.Module")
                .make()
                .load(ITestContext.class.getClassLoader(), ClassLoadingStrategy.Default.INJECTION);

        when(mockEnvInitializer.getType()).thenReturn(ProductType.TP);
        int threadNum = 10;
        ExecutorService pool = new ThreadPoolExecutor(0, threadNum, 0, TimeUnit.SECONDS, new SynchronousQueue<>());
        /*Instantiate and call ContextInitializingListener#onTestStart from [threadNum] separate child threads and wait for completion*/
        CompletableFuture.allOf(
                IntStream.range(0, threadNum).boxed()
                        .map(i -> CompletableFuture.runAsync(() -> {
                            ContextInitializingListener listener = new ContextInitializingListener();
                            logger.info("Created listener");
                            setField(listener, "contextInitializer", MockContext.class);
                            logger.info("Attempting to initialize shared context");
                            ITestContext mockContext = mock(ITestContext.class, withSettings().stubOnly());
                            ITestNGMethod mockMethod = mock(ITestNGMethod.class);
                            when(mockMethod.getRealClass()).thenReturn(MockTestClass.class);
                            when(mockContext.getAllTestMethods()).thenReturn(new ITestNGMethod[]{mockMethod});
                            listener.onStart(mockContext);
                            logger.info("Context is present now");
        /*ContextInitializingListener can't exit without functional context available*/
                            Assert.assertNotNull(AppContextHolder.getContext());
                        }, pool))
                        .toArray(CompletableFuture[]::new))
                .get();
        /*Application context is expected to be initialized once*/
        verify(spyHolder).init();
        /*Environment is expected to be initialized for each test execution*/
        verify(mockEnvInitializer, times(threadNum)).initializeEnvironment(any(Properties.class));
    }

    @Configuration
    @ComponentScan(basePackageClasses = TPTestConfigurationPropertiesParserImpl.class)
    @Import(TestEnvInitializerConfig.class)
    public static class MockContext {
        @Bean
        public AppContextHolder appContextHolder() {
            return spyHolder;
        }

        @Bean
        public TestRunningEnvInitializer testRunningEnvInitializer() {
            return mockEnvInitializer;
        }

    }

    @TestConfigurationProperties(
            productType = ProductType.TP,
            environment = EnvironmentType.QA,
            browserType = "firefox",
            environmentUrl = "sampleUrl"
    )
    public static class MockTestClass {
    }

}