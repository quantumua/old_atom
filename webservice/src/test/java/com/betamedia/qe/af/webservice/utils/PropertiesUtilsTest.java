package com.betamedia.qe.af.webservice.utils;

import com.betamedia.qe.af.common.holder.SUTPropertiesHolder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Properties;

import static com.betamedia.qe.af.webservice.utils.PropertiesUtils.permute;
import static com.betamedia.qe.af.webservice.utils.StringUtils.COMMA;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/16/17.
 */
public class PropertiesUtilsTest {

    @Test
    public void testPermute() throws Exception {
        String browser1 = "chrome";
        String browser2 = "firefox";
        String version1 = "v1";
        String version2 = "v2";
        String remoteDriver1 = "remoteDriver1";
        String remoteDriver2 = "remoteDriver2";

        String browsersList = browser1 + " " + COMMA + browser2;
        String versionsList = version1 + COMMA + version2;
        String domain = "/domain";
        String remoteDriverList = remoteDriver1 + COMMA + remoteDriver2;
        List<Properties> permuteResult = permute(get(browsersList, versionsList, domain, remoteDriverList));
        Properties[] expectedResult =
                new Properties[]{
                        get(browser1, version1, domain, remoteDriver1),
                        get(browser1, version2, domain, remoteDriver1),
                        get(browser1, version1, domain, remoteDriver2),
                        get(browser1, version2, domain, remoteDriver2),
                        get(browser2, version1, domain, remoteDriver1),
                        get(browser2, version2, domain, remoteDriver1),
                        get(browser2, version1, domain, remoteDriver2),
                        get(browser2, version2, domain, remoteDriver2),
                };
        Assert.assertEquals(permuteResult.size(), 8);
        Assert.assertEqualsNoOrder(permuteResult.toArray(), expectedResult);
    }

    private Properties get(String browser, String version, String domain, String remoteDriver) {
        Properties properties = new Properties();
        properties.put(SUTPropertiesHolder.BROWSER_TYPE, browser);
        properties.put(SUTPropertiesHolder.ID_VERSION, version);
        properties.put(SUTPropertiesHolder.DOMAIN_URL, domain);
        properties.put(SUTPropertiesHolder.REMOTE_DRIVER_URL, remoteDriver);
        return properties;
    }
}