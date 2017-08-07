package com.betamedia.atom.core.holders;

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
 *         Date: 2/28/17.
 */
@Component
public final class ConfigurationPropertiesProvider {

    private static final Logger logger = LogManager.getLogger(ConfigurationPropertiesProvider.class);

    public static final String DEFAULT_ENVIRONMENT_URL = "https://qawww.24option.com/eu/trade/";
    public static final String DEFAULT_BROWSER_TYPE = "firefox";
    public static final String DEFAULT_ENVIRONMENT = "qa";

    private Environment env;

    @Autowired
    private ConfigurationPropertiesProvider(Environment environment) {
        this.env = environment;
    }

    public Properties getFromSystem() {
        Properties properties = new Properties();
        putSystemProperty(REMOTE_DRIVER_URL.getKey(), properties, null);
        putSystemProperty(ENVIRONMENT_URL.getKey(), properties, DEFAULT_ENVIRONMENT_URL);
        putSystemProperty(BROWSER_TYPE.getKey(), properties, DEFAULT_BROWSER_TYPE);
        putSystemProperty(IMPLEMENTATION_VERSION.getKey(), properties, null);
        putSystemProperty(REVISION_DATE.getKey(), properties, null);
        putSystemProperty(ENVIRONMENT.getKey(), properties, DEFAULT_ENVIRONMENT);
        return properties;
    }

    private void putSystemProperty(String key, Properties properties, String defaultValue) {
        String value = env.getProperty(key, defaultValue);
        logger.info("Test running parameter: " + key + ": " + value);
        if (!Strings.isNullOrEmpty(value)) {
            properties.put(key, value);
        }
    }
}
