package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.StartTradeDialog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    @Override
    public boolean exists() {
        try {
            waitUntilDisplayed(startTradeDialog);
            return find(startTradeDialog).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
