package com.betamedia.qe.af.core.configuration.webdriver;

import com.betamedia.qe.af.core.webdriver.ParametrizedWebDriverFactory;
import com.betamedia.qe.af.core.webdriver.ParametrizedWebDriverFactoryProvider;
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
import java.io.IOException;
import java.net.URL;

/**
 * Created by mbelyaev on 2/24/17.
 */
//TODO externalize parameter constants
@Configuration
public class WebDriverConfig {
    /**
     * Need to avoid unnecessarily restarting the ChromeDriver server with each instance, {@see CromeDriver}
     * {@link RemoteWebDriver}
     *
     * @param chromeDriverPath
     * @return
     * @throws IOException
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    @Lazy
    public ChromeDriverService chromeDriverService(@Value("${chrome.driver.path}") String chromeDriverPath) throws IOException {
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
                return (dc, url) -> new RemoteWebDriver(new URL(url), dc);
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
                return (dc, url) -> new RemoteWebDriver(driverService.getUrl(), dc);
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
                return (dc, url) -> new FirefoxDriver(new FirefoxOptions().setLegacy(true).addDesiredCapabilities(dc));
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
                return (dc, url) -> new InternetExplorerDriver(dc);
            }
        };
    }

}
