package com.betamedia.atom.core.fwtestrunner.listeners.testng.impl;

import com.betamedia.atom.core.CoreInfrastructure;
import com.betamedia.atom.core.fwtestrunner.environment.impl.TPTestRunningEnvInitializer;
import com.betamedia.atom.core.holders.ConfigurationPropertiesProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.testng.IExecutionListener;

import java.util.Properties;

/**
 * @author Maksym Tsybulskyy
 *         Date: 6/30/17.
 */
public class ExternalExecutionListener implements IExecutionListener {

    private ConfigurableApplicationContext context;

    @Override
    public void onExecutionStart() {
        context = SpringApplication.run(CoreInfrastructure.class);
        ConfigurationPropertiesProvider configProperties = context.getBean(ConfigurationPropertiesProvider.class);
        TPTestRunningEnvInitializer tpTestRunningEnvInitializer = context.getBean(TPTestRunningEnvInitializer.class);
        Properties properties = configProperties.getFromSystem();
        tpTestRunningEnvInitializer.initializeEnvironment(properties);
    }
    @Override
    public void onExecutionFinish() {
        context.close();
    }
}