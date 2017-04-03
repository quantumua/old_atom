package com.betamedia.qe.af.common.factory.webdriver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by mbelyaev on 3/31/17.
 */
@Component
public class WebDriverFactoryProviderImpl implements WebDriverFactoryProvider {

    @Autowired
    private List<ParametrizedWebDriverFactoryProvider> providers;

    private Map<String, ParametrizedWebDriverFactory> providerMap;

    @PostConstruct
    public void init() {
        providerMap = providers.stream().collect(Collectors.toConcurrentMap(ParametrizedWebDriverFactoryProvider::getType, ParametrizedWebDriverFactoryProvider::get));
    }

    @Override

    public WebDriverFactory get(String browserType, String remoteDriverUrl, String domainUrl) {
        String driverType = remoteDriverUrl == null ? browserType : "remote";
        ParametrizedWebDriverFactory webDriverFactory = providerMap.get(driverType);
        return () -> {
            WebDriver driver = webDriverFactory.apply(getCapabilities(browserType), remoteDriverUrl);
            if (driver != null) {
                driver.get(domainUrl);
            }
            return driver;
        };
    }

    private DesiredCapabilities getCapabilities(String browserType) {
        return new DesiredCapabilities(browserType, "", Platform.ANY);
    }

}
