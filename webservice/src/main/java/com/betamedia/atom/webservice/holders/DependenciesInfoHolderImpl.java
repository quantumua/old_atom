package com.betamedia.atom.webservice.holders;


import com.betamedia.atom.core.utils.PropertiesUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/28/17.
 */
@Component
public class DependenciesInfoHolderImpl implements DependenciesInfoHolder {

    private static final Logger logger = LogManager.getLogger(DependenciesInfoHolderImpl.class);

    private static String DEPENDENCIES_PROPERTIES_PATH = "META-INF/maven/dependencies.properties";
    private static String CORE_VERSION = "com.betamedia.atom/core/version";

    private Properties properties;
    private String coreVersion;

    @PostConstruct
    public void init() {
        try {
            properties = PropertiesUtils.getProperties(DEPENDENCIES_PROPERTIES_PATH);
            coreVersion = properties.getProperty(CORE_VERSION);
        } catch (IOException e) {
            logger.warn("Can't load dependencies info, ", e.getMessage());
        }
    }

    @Override
    public String getCoreVersion() {
        return coreVersion;
    }
}
