package com.betamedia.qe.af.common.factory.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/13/17.
 */
public class WebDriverFactoryImpl implements WebDriverFactory {
    private String remoteDriverUrl;
    private String domainUrl;
    private String browserType;
    private ChromeDriverService chromeDriverService;
    private DesiredCapabilities capabilities;

    public WebDriverFactoryImpl(String browserType, String remoteDriverUrl, String domainUrl, ChromeDriverService chromeDriverService, DesiredCapabilities capabilities) {
        this.browserType = browserType;
        this.remoteDriverUrl = remoteDriverUrl;
        this.domainUrl = domainUrl;
        this.capabilities = capabilities;
        this.chromeDriverService = chromeDriverService;
    }

    @Override
    public WebDriver get() throws IOException{
        return get(browserType);
    }

    public WebDriver get(String browserType) throws IOException {
        WebDriver driver = null;
        if (remoteDriverUrl != null) {
            driver = new RemoteWebDriver(new URL(remoteDriverUrl), capabilities);
        } else {
            switch (browserType) {
                case BrowserType.CHROME:
                    driver = new RemoteWebDriver(chromeDriverService.getUrl(), capabilities);
                    break;
                case BrowserType.FIREFOX:
                    //currently targeting non-Gecko firefox versions
                    driver = new FirefoxDriver(new FirefoxOptions().addDesiredCapabilities(capabilities).setLegacy(true));
                    break;
                default:
            }
        }
        if (driver != null) {
            driver.get(domainUrl);
        }
        return driver;
    }
}
