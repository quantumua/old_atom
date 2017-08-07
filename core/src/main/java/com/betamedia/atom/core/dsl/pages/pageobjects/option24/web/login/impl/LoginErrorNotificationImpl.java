package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.login.impl;

import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.login.LoginErrorNotification;
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
