package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.InsufficientFundsDialog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InsufficientFundsDialogImpl extends AbstractPageObject implements InsufficientFundsDialog{

    @StoredId
    private By insufficientFundsDialog;
    @StoredId
    private By closeButton;

    protected InsufficientFundsDialogImpl(WebDriver webDriver) { super(webDriver); }

    @Override
    public boolean exists(){
        return waitUntilDisplayed(insufficientFundsDialog).isDisplayed();
    }

    @Override
    public void close(){
        waitUntilDisplayed(closeButton).click();
    }
}
