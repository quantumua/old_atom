package com.betamedia.qe.af.pages.tp.login.impl;

import com.betamedia.qe.af.entities.page.AbstractPageObject;
import com.betamedia.qe.af.pages.common.annotation.StoredId;
import com.betamedia.qe.af.pages.tp.login.LoginErrorNotification;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by mbelyaev on 2/17/17.
 */
public class LoginErrorNotificationImpl extends AbstractPageObject implements LoginErrorNotification {

    @StoredId("errorNotification")
    private By errorNotification;
    @StoredId("errorCloseBtn")
    private By errorCloseBtn;

    public LoginErrorNotificationImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public By getLocator() {
        return errorNotification;
    }

    @Override
    public void dismiss() {
        waitFor();
        webDriver.findElement(errorCloseBtn).click();
    }
}
