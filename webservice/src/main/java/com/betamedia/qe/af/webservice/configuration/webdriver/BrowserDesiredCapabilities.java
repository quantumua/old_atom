package com.betamedia.qe.af.webservice.configuration.webdriver;

import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/13/17.
 */
public interface BrowserDesiredCapabilities {
    String getType();
    DesiredCapabilities getCapabilities();
}
