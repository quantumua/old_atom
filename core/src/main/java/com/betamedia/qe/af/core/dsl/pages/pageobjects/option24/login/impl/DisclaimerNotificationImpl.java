package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.impl;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.login.DisclaimerNotification;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

/**
 * Created by mbelyaev on 2/16/17.
 */
public class DisclaimerNotificationImpl extends AbstractPageObject implements DisclaimerNotification {

    @StoredId
    private By disclaimerCheckbox;
    @StoredId
    private By disclaimerSubmitBtn;

    public DisclaimerNotificationImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void tryAccept() {
        try {
            waitUntilDisplayed(disclaimerCheckbox);
            waitUntilDisplayed(disclaimerSubmitBtn);
            find(disclaimerCheckbox).click();
            find(disclaimerSubmitBtn).click();
        } catch (TimeoutException e) {
            Reporter.log("Disclaimer notification not displayed in expected time");
        }
    }

}