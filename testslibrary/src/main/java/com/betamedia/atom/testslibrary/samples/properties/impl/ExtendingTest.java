package com.betamedia.atom.testslibrary.samples.properties.impl;

import com.betamedia.atom.testslibrary.samples.properties.AnnotatedTestPropertiesDemonstrationTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * This test uses properties as defined on parent class
 *
 * @author mbelyaev
 * @since 8/7/17
 */
public class ExtendingTest extends AnnotatedTestPropertiesDemonstrationTest {
    @Test
    public void failedLoginTest() {
        pages().topNavigationPage().logIn();
        pages().loginDialog().login("randomname", "randompassword");
        Assert.assertTrue(pages().loginErrorNotification().isDisplayed());
    }
}
