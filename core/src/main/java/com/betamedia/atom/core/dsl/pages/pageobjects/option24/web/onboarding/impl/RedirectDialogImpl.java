package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.RedirectDialog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

/**
 * Created by vsnigur on 7/10/17.
 */
public class RedirectDialogImpl extends AbstractPageObject implements RedirectDialog {
    @StoredId
    private By redirectDialog;
    @StoredId
    private By startTrade;


    public RedirectDialogImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void startTrade() {
        waitUntilDisplayed(startTrade);
        waitUntilClickable(startTrade).click();
        waitUntilPageLoad();
        Reporter.log("Click 'Start Trade' button.<br/>");
    }
}
