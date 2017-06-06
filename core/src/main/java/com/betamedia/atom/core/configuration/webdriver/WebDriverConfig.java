package com.betamedia.atom.core.configuration.webdriver;

import com.betamedia.atom.core.fwservices.webdriver.ParametrizedWebDriverFactory;
import com.betamedia.atom.core.fwservices.webdriver.ParametrizedWebDriverFactoryProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.io.File;
import java.net.URL;

/**
 * @author mbelyaev
 * @since 2/24/17
 */
@Configuration
public class WebDriverConfig {
    /**
     * Need to avoid unnecessarily restarting the ChromeDriver server with each instance (see {@link ChromeDriver})
     *
     * @param chromeDriverPath
     * @return
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    @Lazy
    public ChromeDriverService chromeDriverService(@Value("${chrome.driver.path}") String chromeDriverPath) {
        return new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(chromeDriverPath))
                .usingAnyFreePort()
                .build();
    }

    @Bean
    public ParametrizedWebDriverFactoryProvider remoteDriverSupplier() {
        return new ParametrizedWebDriverFactoryProvider() {
            @Override
            public String getType() {
                return "remote";
            }

            @Override
            public ParametrizedWebDriverFactory get() {
                return (dc, url) -> (WebDriver) new RemoteWebDriver(new URL(url), dc);
            }
        };
    }

    @Bean
    public ParametrizedWebDriverFactoryProvider chromeDriverSupplier(@Lazy ChromeDriverService driverService) {
        return new ParametrizedWebDriverFactoryProvider() {
            @Override
            public String getType() {
                return BrowserType.CHROME;
            }

            @Override
            public ParametrizedWebDriverFactory get() {
                return (dc, url) -> (WebDriver) new RemoteWebDriver(driverService.getUrl(), dc);
            }
        };
    }

    @Bean
    public ParametrizedWebDriverFactoryProvider firefoxDriverSupplier() {
        return new ParametrizedWebDriverFactoryProvider() {
            @Override
            public String getType() {
                return BrowserType.FIREFOX;
            }

            @Override
            public ParametrizedWebDriverFactory get() {
                return (dc, url) -> (WebDriver) new FirefoxDriver(new FirefoxOptions().setLegacy(true).addDesiredCapabilities(dc));
            }
        };
    }

    @Bean
    public ParametrizedWebDriverFactoryProvider ieDriverSupplier() {
        return new ParametrizedWebDriverFactoryProvider() {
            @Override
            public String getType() {
                return BrowserType.IE;
            }

            @Override
            public ParametrizedWebDriverFactory get() {
                return (dc, url) -> (WebDriver) new InternetExplorerDriver(dc);
            }
        };
    }

}
