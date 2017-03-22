package com.betamedia.qe.af.core.pages.tp.login.impl;

import com.betamedia.qe.af.core.pages.AbstractPageObject;
import com.betamedia.qe.af.core.pages.annotation.StoredId;
import com.betamedia.qe.af.core.pages.tp.login.LoginErrorNotification;
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
        waitFor(errorNotification);
        return find(errorNotification).isDisplayed();
    }
}
