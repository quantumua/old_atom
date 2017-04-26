package com.betamedia.qe.af.core.fwtestrunner.runner.testng;

import com.betamedia.qe.af.core.dsl.pages.type.EnvironmentType;
import com.betamedia.qe.af.core.dsl.templates.tp.TPTemplate;
import com.betamedia.qe.af.core.dsl.templates.tp.TPTemplateProvider;
import com.betamedia.qe.af.core.fwdataaccess.repository.impl.VersionedWebElementRepositoryImpl;
import com.betamedia.qe.af.core.fwdataaccess.repository.impl.WebElementRepository;
import com.betamedia.qe.af.core.fwdataaccess.repository.util.ApplicationVersionHolder;
import com.betamedia.qe.af.core.fwdataaccess.repository.util.tp.TPApplicationVersionHolderImpl;
import com.betamedia.qe.af.core.fwservices.webdriver.WebDriverFactory;
import com.betamedia.qe.af.core.fwservices.webdriver.WebDriverFactoryProvider;
import com.betamedia.qe.af.core.fwtestrunner.RunnerResult;
import com.betamedia.qe.af.core.fwtestrunner.runner.TestRunner;
import com.betamedia.qe.af.core.fwtestrunner.types.TestRunnerType;
import com.betamedia.qe.af.core.holders.ConfigurationPropertyKey;
import com.betamedia.qe.af.core.holders.ThreadLocalBeansHolder;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.TestNG;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static com.betamedia.qe.af.core.fwtestrunner.types.TestRunnerType.TESTNG;


/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
@Component
public class TestNGRunnerImpl implements TestRunner {

    @Autowired
    private WebDriverFactoryProvider webDriverFactoryProvider;

    @Autowired
    private TPTemplateProvider templateProvider;

    @Autowired
    private WebElementRepository webElementRepository;

    @Autowired
    private ApplicationVersionHolder applicationVersionHolder;

    @Override
    public TestRunnerType getType() {
        return TESTNG;
    }

    @Override
    public RunnerResult run(Properties props, List<String> suites, String outputDirectory) {
        ThreadLocalBeansHolder.setWebDriverFactoryThreadLocal(getWebDriverFactory(props));
        ThreadLocalBeansHolder.setVersionedWebElementRepositoryThreadLocal(new VersionedWebElementRepositoryImpl(getAppVersion(props), webElementRepository));
        ThreadLocalBeansHolder.setOperationsTemplateThreadLocal(getOperationsTemplate(props));
        TestNG testng = new TestNG();
        testng.setOutputDirectory("test-output/" + outputDirectory);
        testng.setTestSuites(suites);
        testng.run();
        return new RunnerResult(testng.getOutputDirectory()+"/emailable-report.html", testng.hasFailure() || testng.hasSkip());
    }

    private TPTemplate getOperationsTemplate(Properties properties) {
        String environment = properties.getProperty(ConfigurationPropertyKey.ENVIRONMENT, EnvironmentType.QA.getValue());
        return templateProvider.get(EnvironmentType.parse(environment));
    }

    private WebDriverFactory getWebDriverFactory(Properties properties) {
        String domainUrl = properties.getProperty(ConfigurationPropertyKey.ENVIRONMENT_URL);
        String remoteDriverUrl = properties.getProperty(ConfigurationPropertyKey.REMOTE_DRIVER_URL);
        String browserType = properties.getProperty(ConfigurationPropertyKey.BROWSER_TYPE);
        return webDriverFactoryProvider.get(browserType, remoteDriverUrl, domainUrl);
    }

    private String getAppVersion(Properties properties) {

        String version = properties.getProperty(ConfigurationPropertyKey.ID_VERSION);
        try {
            return Strings.isNullOrEmpty(version) ? applicationVersionHolder.getVersion() : version;
        } catch (IOException e) {
            throw new RuntimeException("Failed to obtain app version from " + TPApplicationVersionHolderImpl.VERSION_SOURCE, e);
        }
    }

}
