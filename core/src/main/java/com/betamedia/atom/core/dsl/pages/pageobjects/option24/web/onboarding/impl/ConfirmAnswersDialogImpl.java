package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.ConfirmAnswersDialog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by vsnigur on 6/27/17.
 */
public class ConfirmAnswersDialogImpl extends AbstractPageObject implements ConfirmAnswersDialog {

    @StoredId
    private By confirmTrueAnswers;
    @StoredId
    private By upButton;
    @StoredId
    private By downButton;
    @StoredId
    private By message;
    @StoredId
    private By nextButton;

    protected ConfirmAnswersDialogImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getContext() {
        return waitUntilDisplayed(message).getText();
    }

    @Override
    public boolean exists() {
        return waitUntilDisplayed(confirmTrueAnswers).isDisplayed();
    }

    @Override
    public void next() {
        waitUntilDisplayed(nextButton).click();
    }
}
