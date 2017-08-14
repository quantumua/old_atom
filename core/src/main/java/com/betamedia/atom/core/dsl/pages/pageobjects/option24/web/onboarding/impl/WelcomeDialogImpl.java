package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.WelcomeDialog;
import com.betamedia.atom.core.fwdataaccess.repository.util.Language;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.betamedia.atom.core.testingtype.base.AbstractTest.softAssert;

/**
 * Created by Oleksandr Losiev on 5/23/17.
 */
public class WelcomeDialogImpl extends AbstractPageObject implements WelcomeDialog {

    @StoredId(localized = true)
    private By caption;
    @StoredId(localized = true)
    private By startButton;

    public WelcomeDialogImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void start() {
        waitUntilDisplayed(startButton).click();
    }
    
    @Override
    public boolean isStartBtnDisplayed() {
        return maybe(() -> waitUntilDisplayed(startButton)).isPresent();
    }

    @Override
    public String getCaption() {
        return waitUntilDisplayed(caption).getText();
    }

    @Override
    public String getStartButtonCaption() {
        return waitUntilDisplayed(startButton).getText();
    }

    @Override
    public void validateCaption(Language language) {
        softAssert().assertEquals(
                waitUntilDisplayed(caption).getText(),
                getLocalization(caption, language));
    }

    @Override
    public void validateStartButtonCaption(Language language) {
        softAssert().assertEquals(
                waitUntilDisplayed(startButton).getText(),
                getLocalization(startButton, language));
    }
}
