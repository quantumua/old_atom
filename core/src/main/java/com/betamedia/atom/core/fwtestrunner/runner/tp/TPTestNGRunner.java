package com.betamedia.atom.core.fwtestrunner.runner.tp;

import com.betamedia.atom.core.fwdataaccess.converters.LocalDateTimeConverter;
import com.betamedia.atom.core.dsl.pages.type.EnvironmentType;
import com.betamedia.atom.core.dsl.templates.tp.TPTemplate;
import com.betamedia.atom.core.dsl.templates.tp.TPTemplateProvider;
import com.betamedia.atom.core.fwdataaccess.repository.impl.VersionedWebElementRepositoryImpl;
import com.betamedia.atom.core.fwdataaccess.repository.impl.WebElementRepository;
import com.betamedia.atom.core.fwdataaccess.repository.util.RepositoryVersion;
import com.betamedia.atom.core.fwdataaccess.repository.util.version.ApplicationVersionService;
import com.betamedia.atom.core.fwdataaccess.repository.util.version.ApplicationVersionServiceProvider;
import com.betamedia.atom.core.fwservices.webdriver.WebDriverFactory;
import com.betamedia.atom.core.fwservices.webdriver.WebDriverFactoryProvider;
import com.betamedia.atom.core.fwtestrunner.runner.AbstractTestNGRunner;
import com.betamedia.atom.core.holders.ConfigurationPropertyKey;
import com.betamedia.atom.core.holders.ThreadLocalBeansHolder;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * @author mbelyaev
 * @since 5/31/17
 */
@Component
public class TPTestNGRunner extends AbstractTestNGRunner {
    @Autowired
    private ApplicationVersionServiceProvider applicationVersionServiceProvider;
    @Autowired
    private WebDriverFactoryProvider webDriverFactoryProvider;
    @Autowired
    private TPTemplateProvider templateProvider;
    @Autowired
    private WebElementRepository webElementRepository;

    @Override
    public void initializeEnvironment(Properties properties) {
        ThreadLocalBeansHolder.setWebDriverFactoryThreadLocal(getWebDriverFactory(properties));
        ThreadLocalBeansHolder.setVersionedWebElementRepositoryThreadLocal(new VersionedWebElementRepositoryImpl(getAppVersion(properties), webElementRepository));
        ThreadLocalBeansHolder.setOperationsTemplateThreadLocal(getOperationsTemplate(properties));
    }

    private WebDriverFactory getWebDriverFactory(Properties properties) {
        String domainUrl = properties.getProperty(ConfigurationPropertyKey.ENVIRONMENT_URL);
        String remoteDriverUrl = properties.getProperty(ConfigurationPropertyKey.REMOTE_DRIVER_URL);
        String browserType = properties.getProperty(ConfigurationPropertyKey.BROWSER_TYPE);
        return webDriverFactoryProvider.get(browserType, remoteDriverUrl, domainUrl);
    }

    private TPTemplate getOperationsTemplate(Properties properties) {
        return templateProvider.get(getEnvironment(properties));
    }

    private ApplicationVersionService getApplicationVersionService(Properties properties) {
        return applicationVersionServiceProvider.get(getEnvironment(properties));
    }

    private EnvironmentType getEnvironment(Properties properties) {
        return EnvironmentType.parse(properties.getProperty(ConfigurationPropertyKey.ENVIRONMENT, EnvironmentType.QA.getValue()));
    }

    /**
     * {@link RepositoryVersion} lookup strategy:
     * <li>
     * <ul>- If there is no implementation version provided in test.properties file, try to obtain it from {@link ApplicationVersionService}</ul>
     * <ul>- Otherwise, use provided implementation version and, if present, revision date</ul>
     * </li>
     */
    private RepositoryVersion getAppVersion(Properties properties) {
        String implementationVersion = properties.getProperty(ConfigurationPropertyKey.IMPLEMENTATION_VERSION);
        if (Strings.isNullOrEmpty(implementationVersion)) {
            return getApplicationVersionService(properties).getVersion();
        }
        String dateString = properties.getProperty(ConfigurationPropertyKey.REVISION_DATE);
        return new RepositoryVersion(implementationVersion, Strings.isNullOrEmpty(dateString) ? null : getRevisionDate(dateString));
    }

    private static LocalDateTime getRevisionDate(String dateString) {
        return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(LocalDateTimeConverter.DATE_PATTERN));
    }

}
