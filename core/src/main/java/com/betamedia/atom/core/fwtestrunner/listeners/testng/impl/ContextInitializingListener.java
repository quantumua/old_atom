package com.betamedia.atom.core.fwtestrunner.listeners.testng.impl;

import com.betamedia.atom.core.CoreInfrastructure;
import com.betamedia.atom.core.fwtestrunner.environment.configuration.TestConfigurationPropertiesParser;
import com.betamedia.atom.core.fwtestrunner.environment.configuration.TestConfigurationPropertiesParserProvider;
import com.betamedia.atom.core.fwtestrunner.environment.initializer.TestRunningEnvInitializer;
import com.betamedia.atom.core.fwtestrunner.environment.initializer.TestRunningEnvInitializerProvider;
import com.betamedia.atom.core.holders.AppContextHolder;
import com.betamedia.atom.core.testingtype.annotations.TestConfigurationProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.betamedia.atom.core.fwtestrunner.listeners.testng.impl.TestLinkListener.CONFIGURATION_KEY;

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
    public void onStart(ITestContext context) {
        ITestNGMethod[] methods = context.getAllTestMethods();
        if (methods.length == 0) return;
        initializeAppContext();
        TestConfigurationProperties testProperties = (TestConfigurationProperties) methods[0]
                .getRealClass()
                .getAnnotation(TestConfigurationProperties.class);
        Properties combinedProperties = getPropertiesParser(testProperties).parse(testProperties);
        combinedProperties.setProperty("product.type", testProperties.productType().toString());
        context.setAttribute(CONFIGURATION_KEY, combinedProperties);
        getInitializer(testProperties).initializeEnvironment(combinedProperties);
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

    private static boolean isContextPresent() {
        return Objects.nonNull(AppContextHolder.getContext());
    }

    private static TestConfigurationPropertiesParser getPropertiesParser(TestConfigurationProperties testProperties) {
        return AppContextHolder.getBean(TestConfigurationPropertiesParserProvider.class).get(testProperties.productType());
    }

    private static TestRunningEnvInitializer getInitializer(TestConfigurationProperties testProperties) {
        return AppContextHolder.getBean(TestRunningEnvInitializerProvider.class).get(testProperties.productType());
    }

    @Override
    public void onTestStart(ITestResult result) {/*Do nothing*/}

    @Override
    public void onTestSuccess(ITestResult result) {/*Do nothing*/}

    @Override
    public void onTestFailure(ITestResult result) {/*Do nothing*/}

    @Override
    public void onTestSkipped(ITestResult result) {/*Do nothing*/}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {/*Do nothing*/}

    @Override
    public void onFinish(ITestContext context) {/*Do nothing*/}


}
