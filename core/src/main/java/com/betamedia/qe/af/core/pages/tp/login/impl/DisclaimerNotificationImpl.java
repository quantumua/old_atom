package com.betamedia.qe.af.core.pages.tp.login.impl;

import com.betamedia.qe.af.core.pages.AbstractPageObject;
import com.betamedia.qe.af.core.pages.annotation.StoredId;
import com.betamedia.qe.af.core.pages.tp.login.DisclaimerNotification;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by mbelyaev on 2/16/17.
 */
public class DisclaimerNotificationImpl extends AbstractPageObject implements DisclaimerNotification {

    @StoredId("disclaimerCheckbox")
    private By disclaimerCheckbox;
    @StoredId("disclaimerSubmitBtn")
    private By disclaimerSubmitBtn;

    public DisclaimerNotificationImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void accept() {
        waitUntilDisplayed(disclaimerCheckbox);
        find(disclaimerCheckbox).click();
        find(disclaimerSubmitBtn).click();
    }

}