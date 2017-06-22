package com.betamedia.atom.core.configuration.webdriver;

import com.betamedia.atom.core.fwservices.webdriver.ParametrizedWebDriverFactory;
import com.betamedia.atom.core.fwservices.webdriver.ParametrizedWebDriverFactoryProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
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
import java.util.HashMap;
import java.util.Map;

/**
 * @author mbelyaev
 * @since 2/24/17
 */
@Configuration
public class WebDriverConfig {
    /**
     * Need to avoid unnecessarily restarting the ChromeDriver server with each instance (see {@link ChromeDriver})
     *
     * @param chromeDriverPath path to chromedriver executable
     * @return ChromeDriverService instance
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
    public ParametrizedWebDriverFactoryProvider remoteDriverProvider() {
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
    public ParametrizedWebDriverFactoryProvider chromeDriverProvider(@Lazy ChromeDriverService driverService) {
        return new ParametrizedWebDriverFactoryProvider() {
            @Override
            public String getType() {
                return BrowserType.CHROME;
            }

            @Override
            public ParametrizedWebDriverFactory get() {
                return (dc, url) -> {
                    dc.setCapability(ChromeOptions.CAPABILITY, getChromeOptions());
                    return (WebDriver) new RemoteWebDriver(driverService.getUrl(), dc);
                };
            }

            private ChromeOptions getChromeOptions() {
                ChromeOptions options = new ChromeOptions();
                disableSavingPassword(options);
                return options;
            }

            private void disableSavingPassword(ChromeOptions options) {
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                options.setExperimentalOption("prefs", prefs);
            }
        };
    }

    @Bean
    public ParametrizedWebDriverFactoryProvider firefoxLegacyDriverProvider() {
        return new ParametrizedWebDriverFactoryProvider() {
            @Override
            public String getType() {
                return "firefox-legacy";
            }

            @Override
            public ParametrizedWebDriverFactory get() {
                return (dc, url) -> (WebDriver) new FirefoxDriver(new FirefoxOptions().setLegacy(true).addCapabilities(dc));
            }
        };
    }

    @Bean
    public ParametrizedWebDriverFactoryProvider firefoxDriverProvider(@Value("${gecko.driver.path}") String geckoDriverPath) {
        System.setProperty("webdriver.gecko.driver", geckoDriverPath);
        return new ParametrizedWebDriverFactoryProvider() {
            @Override
            public String getType() {
                return BrowserType.FIREFOX;
            }

            @Override
            public ParametrizedWebDriverFactory get() {
                return (dc, url) -> (WebDriver) new FirefoxDriver(new FirefoxOptions().addCapabilities(dc));
            }
        };
    }

    @Bean
    public ParametrizedWebDriverFactoryProvider ieDriverProvider() {
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
