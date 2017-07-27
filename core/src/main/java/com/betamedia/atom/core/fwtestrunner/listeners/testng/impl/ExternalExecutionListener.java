package com.betamedia.atom.core.fwtestrunner.listeners.testng.impl;

import com.betamedia.atom.core.CoreInfrastructure;
import com.betamedia.atom.core.fwtestrunner.environment.impl.TPTestRunningEnvInitializer;
import com.betamedia.atom.core.holders.AppContextHolder;
import com.betamedia.atom.core.holders.ConfigurationPropertiesProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.testng.IExecutionListener;

import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Maksym Tsybulskyy
 *         Date: 6/30/17.
 */
public class ExternalExecutionListener implements IExecutionListener {
    private static final String CONTEXT_FOUND = "Application context found, aborting initialization";
    private static final String CONTEXT_INITIALIZED = "Initialized listener-managed application context";
    private static final Logger logger = LogManager.getLogger(ExternalExecutionListener.class);
    private static final ReentrantReadWriteLock.WriteLock ctxLock = new ReentrantReadWriteLock().writeLock();
    private ConfigurableApplicationContext context;

    @Override
    public void onExecutionStart() {
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
            context = SpringApplication.run(CoreInfrastructure.class);
            initializeContext();
            logger.debug(CONTEXT_INITIALIZED);
        } finally {
            ctxLock.unlock();
        }
    }

    @Override
    public void onExecutionFinish() {/*Do nothing*/}

    private static boolean isContextPresent() {
        return Objects.nonNull(AppContextHolder.getContext());
    }

    private void initializeContext() {
        ConfigurationPropertiesProvider configProperties = context.getBean(ConfigurationPropertiesProvider.class);
        TPTestRunningEnvInitializer tpTestRunningEnvInitializer = context.getBean(TPTestRunningEnvInitializer.class);
        Properties properties = configProperties.getFromSystem();
        tpTestRunningEnvInitializer.initializeEnvironment(properties);
    }
}
