package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.impl;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.LoginErrorNotification;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by mbelyaev on 2/17/17.
 */
public class LoginErrorNotificationImpl extends AbstractPageObject implements LoginErrorNotification {

    @StoredId("errorNotification")
    private By errorNotification;

    public LoginErrorNotificationImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean isDisplayed() {
        waitUntilDisplayed(errorNotification);
        return find(errorNotification).isDisplayed();
    }
}
