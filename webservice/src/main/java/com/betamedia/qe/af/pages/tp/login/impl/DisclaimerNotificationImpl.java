package com.betamedia.qe.af.pages.tp.login.impl;

import com.betamedia.qe.af.entities.page.AbstractPageObject;
import com.betamedia.qe.af.pages.common.annotation.StoredId;
import com.betamedia.qe.af.pages.tp.login.DisclaimerNotification;
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
        waitFor();
        webDriver.findElement(disclaimerCheckbox).click();
        webDriver.findElement(disclaimerSubmitBtn).click();
    }

    @Override
    public By getLocator() {
        return disclaimerCheckbox;
    }
}