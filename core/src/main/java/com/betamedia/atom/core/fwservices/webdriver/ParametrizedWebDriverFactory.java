package com.betamedia.atom.core.fwservices.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

/**
 * Created by mbelyaev on 3/31/17.
 */
@FunctionalInterface
public interface ParametrizedWebDriverFactory {
    WebDriver apply(DesiredCapabilities capabilities, String url) throws MalformedURLException;
}
