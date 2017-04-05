package com.betamedia.qe.af.core.fwtestrunner.runner.testng;

import com.betamedia.qe.af.core.fwservices.webdriver.WebDriverFactory;
import com.betamedia.qe.af.core.fwservices.webdriver.WebDriverFactoryProvider;
import com.betamedia.qe.af.core.fwdataaccess.repository.util.ApplicationVersionHolder;
import com.betamedia.qe.af.core.holders.ConfigurationPropertyKey;
import com.betamedia.qe.af.core.fwdataaccess.repository.util.tp.TPApplicationVersionHolderImpl;
import com.betamedia.qe.af.core.holders.ThreadLocalBeansHolder;
import com.betamedia.qe.af.core.fwdataaccess.repository.VersionedWebElementRepositoryImpl;
import com.betamedia.qe.af.core.fwdataaccess.repository.WebElementRepository;
import com.betamedia.qe.af.core.fwtestrunner.ClassLoaderInvocationHandler;
import com.betamedia.qe.af.core.fwtestrunner.runner.TestRunner;
import com.betamedia.qe.af.core.fwtestrunner.types.TestRunnerType;
import com.google.common.base.Strings;
import net.sf.cglib.proxy.Enhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.TestNG;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

import static com.betamedia.qe.af.core.utils.PropertiesUtils.permute;
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
    private Executor runnerTaskExecutor;

    @Autowired
    private WebElementRepository webElementRepository;

    @Autowired
    private ApplicationVersionHolder applicationVersionHolder;

    @Autowired
    private ClassLoaderInvocationHandler classLoaderInvocationHandler;

    private void setContextClassLoader() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ClassLoader.class);
        enhancer.setCallback(classLoaderInvocationHandler);
        Thread.currentThread().setContextClassLoader((ClassLoader) enhancer.create());
    }

    @Override
    public TestRunnerType getType() {
        return TESTNG;
    }

    @Override
    public List<String> run(Properties properties, List<String> xmlNames) {
        List<Properties> singleValuePerKeyProps = permute(properties);
        return singleValuePerKeyProps.stream()
                .map(props -> executeForProperties(props, xmlNames))
                .collect(Collectors.toList());
    }

    private String executeForProperties(Properties props, List<String> xmlNames) {
        String path = LocalDateTime.now().toString() + "." + props.hashCode();
        runnerTaskExecutor.execute(() -> run(xmlNames, props, "test-output/" + path));
        return path + "/index.html";
    }

    private void run(List<String> xmlNames, Properties props, String outputDirectory) {
        ThreadLocalBeansHolder.setWebDriverFactoryThreadLocal(getWebDriverFactory(props));
        ThreadLocalBeansHolder.setVersionedWebElementRepositoryThreadLocal(new VersionedWebElementRepositoryImpl(getAppVersion(props), webElementRepository));
        TestNG testng = new TestNG();
        testng.setOutputDirectory(outputDirectory);
        testng.setTestSuites(xmlNames);
        setContextClassLoader();
        testng.run();
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
