package com.betamedia.qe.af.webservice.configuration.webdriver.chrome;

import com.betamedia.qe.af.common.holder.SUTPropertiesHolder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by mbelyaev on 2/24/17.
 */
//TODO externalize parameter constants
@Configuration
public class ChromeDriverConfig {

    @Bean
    @Lazy
    public DesiredCapabilities chromeCapabilities() {
        return DesiredCapabilities.chrome();
    }

    @Bean
    @Lazy
    public DesiredCapabilities firefoxCapabilities() {
        return DesiredCapabilities.firefox();
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    @Lazy
    public ChromeDriverService chromeDriverService(SUTPropertiesHolder sutPropertiesHolder) throws IOException {
        String driverPath = sutPropertiesHolder.get(SUTPropertiesHolder.CHROME_DRIVER_PATH);
        return new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(driverPath))
                .usingAnyFreePort()
                .build();
    }

    //TODO refactor, need 2 beans WebDriver and WebDriverFactory, smth like: webdriver(){return factory.get()}
    @Bean
    @Scope("prototype")
    public WebDriver driver() throws IOException {
        SUTPropertiesHolder holder = (SUTPropertiesHolder) RequestContextHolder.getRequestAttributes().getAttribute("sutPropertyHolder", RequestAttributes.SCOPE_REQUEST);
        WebDriver driver = null;
        String remoteDriverUrl = holder.get(SUTPropertiesHolder.REMOTE_DRIVER_URL);
        String browserType = holder.get(SUTPropertiesHolder.BROWSER_TYPE);
        String domainUrl = holder.get(SUTPropertiesHolder.DOMAIN_URL);
        DesiredCapabilities capabilities = getCapabilities(browserType);
        if (remoteDriverUrl != null) {
            driver = new RemoteWebDriver(new URL(remoteDriverUrl), capabilities);
        } else {
            switch (browserType) {
                case BrowserType.CHROME:
                    driver = new RemoteWebDriver(chromeDriverService(holder).getUrl(), capabilities);
                    break;
                case BrowserType.FIREFOX:
                    driver = new FirefoxDriver(capabilities);
                    break;
                default:
            }
        }
        if (driver != null) {
            driver.get(domainUrl);
        }
        return driver;
    }

    private DesiredCapabilities getCapabilities(String browserType) {
        switch (browserType) {
            case BrowserType.CHROME:
                return chromeCapabilities();
            case BrowserType.FIREFOX:
                return firefoxCapabilities();
            default:
                return null;
        }
    }
}
