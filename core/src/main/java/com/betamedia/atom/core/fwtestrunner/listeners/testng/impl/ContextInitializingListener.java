package com.betamedia.atom.core.fwtestrunner.listeners.testng.impl;

import com.betamedia.atom.core.CoreInfrastructure;
import com.betamedia.atom.core.fwtestrunner.environment.configuration.TestConfigurationPropertiesParser;
import com.betamedia.atom.core.fwtestrunner.environment.configuration.TestConfigurationPropertiesParserProvider;
import com.betamedia.atom.core.fwtestrunner.environment.initializer.TestRunningEnvInitializerProvider;
import com.betamedia.atom.core.fwtestrunner.environment.initializer.TestRunningEnvInitializer;
import com.betamedia.atom.core.holders.AppContextHolder;
import com.betamedia.atom.core.testingtype.annotations.TestConfigurationProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Maksym Tsybulskyy
 *         Date: 6/30/17.
 */
public class ContextInitializingListener implements ITestListener {
    private static final String CONTEXT_FOUND = "Application context found, aborting initialization";
    private static final String CONTEXT_INITIALIZED = "Initialized listener-managed application context";
    private static final Logger logger = LogManager.getLogger(ContextInitializingListener.class);
    private static final ReentrantReadWriteLock.WriteLock ctxLock = new ReentrantReadWriteLock().writeLock();
    private final Class<?> contextInitializer = CoreInfrastructure.class;

    @Override
    public void onTestStart(ITestResult result) {
        initializeAppContext();
        initializeTestEnvironment(result.getInstance().getClass().getAnnotation(TestConfigurationProperties.class));
    }

    private void initializeAppContext() {
        if (isContextPresent()) {
            logger.debug(CONTEXT_FOUND);
            return;
        }
        ctxLock.lock();
        try {
            if (isContextPresent()) {
                logger.debug(CONTEXT_FOUND);
                return;
            }
            SpringApplication.run(contextInitializer);
            logger.debug(CONTEXT_INITIALIZED);
        } finally {
            ctxLock.unlock();
        }
    }

    private static void initializeTestEnvironment(TestConfigurationProperties configuration) {
        TestConfigurationPropertiesParser propertiesParser = AppContextHolder.getBean(TestConfigurationPropertiesParserProvider.class).get(configuration.productType());
        TestRunningEnvInitializer envInitializer = AppContextHolder.getBean(TestRunningEnvInitializerProvider.class).get(configuration.productType());
        envInitializer.initializeEnvironment(propertiesParser.parse(configuration));
    }

    private static boolean isContextPresent() {
        return Objects.nonNull(AppContextHolder.getContext());
    }

    @Override
    public void onTestSuccess(ITestResult result) {/*Do nothing*/}

    @Override
    public void onTestFailure(ITestResult result) {/*Do nothing*/}

    @Override
    public void onTestSkipped(ITestResult result) {/*Do nothing*/}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {/*Do nothing*/}

    @Override
    public void onStart(ITestContext context) {/*Do nothing*/}

    @Override
    public void onFinish(ITestContext context) {/*Do nothing*/}


}
