package com.betamedia.atom.testslibrary.samples.properties.impl;

import com.betamedia.atom.core.dsl.type.EnvironmentType;
import com.betamedia.atom.core.dsl.type.ProductType;
import com.betamedia.atom.core.testingtype.annotations.TestConfigurationProperties;
import com.betamedia.atom.testslibrary.samples.properties.AnnotatedTestPropertiesDemonstrationTest;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.Test;

/**
 * This WebClientTest implementation overrides environmentUrl from product page to widget page (expected to fail!)
 *
 * @author mbelyaev
 * @since 8/7/17
 */
@TestConfigurationProperties(
        productType = ProductType.TP,
        environment = EnvironmentType.QA,
        environmentUrl = "http://widgets-qa-eu.24option.com/TestPage1.html",
        browserType = BrowserType.FIREFOX)
public class WrongEnvironmentTest extends AnnotatedTestPropertiesDemonstrationTest {
    @Test(expectedExceptions = TimeoutException.class)
    public void failingTest() {
        pages().topNavigationPage().logIn();
    }
}
