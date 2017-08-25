package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.ThankYouPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author mbelyaev
 * @since 7/31/17
 */
public class ThankYouPageImpl extends AbstractPageObject implements ThankYouPage {
    @StoredId
    private By continueButton;
    @StoredId
    private By startTradeButton;

    public ThankYouPageImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void doContinue() {
        click(continueButton);
    }

    @Override
    public void startTrade() {
        click(startTradeButton);
    }

    @Override
    public boolean waitForStartTrade() {
        return waitUntilDisplayed(startTradeButton).isDisplayed();
    }

    @Override
    public boolean startTradeExists() {
        return find(startTradeButton).isDisplayed();
    }
}
