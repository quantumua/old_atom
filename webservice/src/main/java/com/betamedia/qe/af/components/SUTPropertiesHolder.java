package com.betamedia.qe.af.components;

import java.util.Properties;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/28/17.
 */
public interface SUTPropertiesHolder {
    String CHROME_DRIVER_PATH = "chrome.driver.path";
    String REMOTE_DRIVER_URL ="remote.driver.url";
    String DOMAIN_URL = "domain.url";
    String BROWSER_TYPE = "browser.type";
    String ID_VERSION = "id.version";
    <T> T get(String key);
    void set(Properties properties);
}
