package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.onboarding.impl;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.onboarding.StartTradeDialog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

/**
 * Created by vsnigur on 5/30/17.
 */
public class StartTradeDialogImpl extends AbstractPageObject implements StartTradeDialog {
    @StoredId
    private By startTradeDialog;

    @StoredId
    private By startTradeButton;

    public StartTradeDialogImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void startTrade() {
        waitUntilDisplayed(startTradeButton).click();
    }
}
