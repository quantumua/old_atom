package com.betamedia.atom.core.fwtestrunner.environment.configuration.impl;

import com.betamedia.atom.core.dsl.type.TestConfigurationKeys;
import com.betamedia.atom.core.fwtestrunner.environment.configuration.TestConfigurationPropertiesParser;
import com.betamedia.atom.core.product.TPProduct;
import com.betamedia.atom.core.testingtype.annotations.TestConfigurationProperties;
import com.google.common.base.Strings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Properties;

import static com.betamedia.atom.core.dsl.type.TestConfigurationKeys.*;

/**
 * Creates test execution {@link Properties} out of environment properties of the application. Used for running tests
 * through maven surefire.
 *
 * @author Maksym Tsybulskyy
 * Date: 2/28/17.
 */
@Component
public final class TPTestConfigurationPropertiesParserImpl implements TestConfigurationPropertiesParser, TPProduct {

    private static final Logger logger = LogManager.getLogger(TPTestConfigurationPropertiesParserImpl.class);

    private Environment env;

    @Autowired
    private TPTestConfigurationPropertiesParserImpl(Environment environment) {
        this.env = environment;
    }

    @Override
    public Properties parse(TestConfigurationProperties configuration) {
        Properties properties = new Properties();
        putSystemProperty(ENVIRONMENT, properties, configuration.environment().getValue());
        putSystemProperty(ENVIRONMENT_URL, properties, configuration.environmentUrl());
        putSystemProperty(BROWSER_TYPE, properties, configuration.browserType());
        putSystemProperty(REMOTE_DRIVER_URL, properties, configuration.remoteDriverUrl());
        putSystemProperty(REVISION_DATE, properties, configuration.revisionDate());
        putSystemProperty(IMPLEMENTATION_VERSION, properties, configuration.implVersion());
        return properties;
    }

    private void putSystemProperty(TestConfigurationKeys key, Properties properties, String defaultValue) {
        String keyString = key.getKey();
        String value = env.getProperty(keyString, defaultValue);
        logger.debug("Test running parameter: " + keyString + ": " + value);
        if (!Strings.isNullOrEmpty(value)) {
            properties.put(keyString, value);
        }
    }
}
