package com.betamedia.qe.af.common.factory.webdriver;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/13/17.
 */
public interface WebDriverFactory {
    WebDriver get() throws MalformedURLException;
}
