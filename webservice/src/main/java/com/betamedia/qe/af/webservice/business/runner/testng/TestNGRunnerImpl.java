package com.betamedia.qe.af.webservice.business.runner.testng;

import com.betamedia.qe.af.common.factory.webdriver.WebDriverFactory;
import com.betamedia.qe.af.common.factory.webdriver.WebDriverFactoryImpl;
import com.betamedia.qe.af.common.holder.ApplicationVersionHolder;
import com.betamedia.qe.af.common.holder.SUTPropertiesHolder;
import com.betamedia.qe.af.common.holder.TPApplicationVersionHolderImpl;
import com.betamedia.qe.af.common.holder.ThreadLocalBeansHolder;
import com.betamedia.qe.af.common.repository.VersionedWebElementRepositoryImpl;
import com.betamedia.qe.af.common.repository.WebElementRepository;
import com.betamedia.qe.af.webservice.business.ClassLoaderInvocationHandler;
import com.betamedia.qe.af.webservice.business.runner.TestRunner;
import com.betamedia.qe.af.webservice.business.types.TestType;
import com.betamedia.qe.af.webservice.configuration.webdriver.BrowserDesiredCapabilities;
import com.google.common.base.Strings;
import net.sf.cglib.proxy.Enhancer;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.TestNG;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

import static com.betamedia.qe.af.webservice.business.types.TestType.TESTNG;
import static com.betamedia.qe.af.webservice.utils.PropertiesUtils.permute;


/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
@Component
public class TestNGRunnerImpl implements TestRunner {

    @Autowired
    private ChromeDriverService chromeDriverService;

    @Autowired
    private Executor runnerTaskExecutor;

    @Autowired
    private WebElementRepository webElementRepository;

    @Autowired
    private ApplicationVersionHolder applicationVersionHolder;

    private Map<String, BrowserDesiredCapabilities> capabilities;

    //TODO add input property validation

    @Autowired
    private ClassLoaderInvocationHandler classLoaderInvocationHandler;

    private void setContextClassLoader() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ClassLoader.class);
        enhancer.setCallback(classLoaderInvocationHandler);
        Thread.currentThread().setContextClassLoader((ClassLoader) enhancer.create());
    }

    @Override
    public TestType getType() {
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
        runnerTaskExecutor.execute(() -> TestNGRunnerImpl.this.run(xmlNames, TestNGRunnerImpl.this.getWebDriverFactory(props), TestNGRunnerImpl.this.getAppVersion(props), "test-output/" + path));
        return path + "/index.html";
    }

    private void run(List<String> xmlNames, WebDriverFactory webDriverFactory, String version, String outputDirectory) {
        ThreadLocalBeansHolder.setWebDriverFactoryThreadLocal(webDriverFactory);
        ThreadLocalBeansHolder.setVersionedWebElementRepositoryThreadLocal(new VersionedWebElementRepositoryImpl(version, webElementRepository));
        TestNG testng = new TestNG();
        testng.setOutputDirectory(outputDirectory);
        testng.setTestSuites(xmlNames);
        setContextClassLoader();
        testng.run();
    }

    @Autowired
    private void setCapabilities(List<BrowserDesiredCapabilities> browserDesiredCapabilities) {
        this.capabilities = browserDesiredCapabilities.stream()
                .collect(Collectors.toMap(BrowserDesiredCapabilities::getType, m -> m));
    }

    private WebDriverFactory getWebDriverFactory(Properties properties) {
        String browserType = properties.getProperty(SUTPropertiesHolder.BROWSER_TYPE);
        String remoteDriverUrl = properties.getProperty(SUTPropertiesHolder.REMOTE_DRIVER_URL);
        String domainUrl = properties.getProperty(SUTPropertiesHolder.DOMAIN_URL);
        return new WebDriverFactoryImpl(browserType, remoteDriverUrl, domainUrl, chromeDriverService, getCapabilities(browserType));
    }

    private DesiredCapabilities getCapabilities(String browserType) {
        return capabilities.get(browserType).getCapabilities();
    }

    private String getAppVersion(Properties properties) {

        String version = properties.getProperty(SUTPropertiesHolder.ID_VERSION);
        try {
            return Strings.isNullOrEmpty(version) ? applicationVersionHolder.getVersion() : version;
        } catch (IOException e) {
            throw new RuntimeException("Failed to obtain app version from " + TPApplicationVersionHolderImpl.VERSION_SOURCE, e);
        }
    }
}
