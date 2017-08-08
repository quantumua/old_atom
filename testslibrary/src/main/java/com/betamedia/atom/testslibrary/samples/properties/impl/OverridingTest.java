package com.betamedia.atom.testslibrary.samples.properties.impl;

import com.betamedia.atom.core.dsl.type.EnvironmentType;
import com.betamedia.atom.core.dsl.type.ProductType;
import com.betamedia.atom.core.testingtype.annotations.TestConfigurationProperties;
import com.betamedia.atom.testslibrary.samples.properties.AnnotatedTestPropertiesDemonstrationTest;
import org.openqa.selenium.remote.BrowserType;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * This test overrides browserType from {@link BrowserType#FIREFOX} to {@link BrowserType#CHROME}
 *
 * @author mbelyaev
 * @since 8/7/17
 */
@TestConfigurationProperties(
        productType = ProductType.TP,
        environment = EnvironmentType.QA,
        environmentUrl = "https://qawww.24option.com/eu/trade/",
        browserType = BrowserType.CHROME)
public class OverridingTest extends AnnotatedTestPropertiesDemonstrationTest {
    @Test
    public void failedLoginTest() {
        pages().topNavigationPage().logIn();
        pages().loginDialog().login("randomname", "randompassword");
        Assert.assertTrue(pages().loginErrorNotification().isDisplayed());
    }
}
