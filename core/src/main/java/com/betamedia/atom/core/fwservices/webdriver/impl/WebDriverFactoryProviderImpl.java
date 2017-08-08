package com.betamedia.atom.core.fwservices.webdriver.impl;

import com.betamedia.atom.core.fwservices.webdriver.ParametrizedWebDriverFactory;
import com.betamedia.atom.core.fwservices.webdriver.ParametrizedWebDriverFactoryProvider;
import com.betamedia.atom.core.fwservices.webdriver.WebDriverFactory;
import com.betamedia.atom.core.fwservices.webdriver.WebDriverFactoryProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Provides configured {@link WebDriverFactory} instances for requested web driver parameters.
 *
 * @author mbelyaev
 * @since 3/31/17
 */
@Component
public class WebDriverFactoryProviderImpl implements WebDriverFactoryProvider {
    private static final Logger logger = LogManager.getLogger(WebDriverFactoryProviderImpl.class);

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
        return Optional.ofNullable(driverType)
                .map(providerMap::get)
                .map(factory ->
                        (WebDriverFactory) () -> {
                            try {
                                WebDriver driver = factory.apply(getCapabilities(browserType), remoteDriverUrl);
                                driver.get(domainUrl);
                                return driver;
                            } catch (MalformedURLException e) {
                                logger.error("Failed to instantiate WebDriver:", e);
                                throw new RuntimeException(e);
                            }
                        })
                .orElse(null);
    }

    private DesiredCapabilities getCapabilities(String browserType) {
        return new DesiredCapabilities(browserType, "", Platform.ANY);
    }

}
