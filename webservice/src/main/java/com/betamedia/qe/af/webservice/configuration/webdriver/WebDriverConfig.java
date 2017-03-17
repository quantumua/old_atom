package com.betamedia.qe.af.webservice.configuration.webdriver;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

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
    public ChromeDriverService chromeDriverService(@Value("${chrome.driver.path}") String chromeDriverPath) throws IOException {
        return new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(chromeDriverPath))
                .usingAnyFreePort()
                .build();
    }

    @Bean
    public BrowserDesiredCapabilities chromeCapabilities() {
        return new BrowserDesiredCapabilities() {
            @Override
            public String getType() {
                return BrowserType.CHROME;
            }

            @Override
            public DesiredCapabilities getCapabilities() {
                return DesiredCapabilities.chrome();
            }
        };
    }

    @Bean
    public BrowserDesiredCapabilities firefoxCapabilities() {
        return new BrowserDesiredCapabilities() {
            @Override
            public String getType() {
                return BrowserType.FIREFOX;
            }

            @Override
            public DesiredCapabilities getCapabilities() {
                return DesiredCapabilities.firefox();
            }
        };
    }
}
