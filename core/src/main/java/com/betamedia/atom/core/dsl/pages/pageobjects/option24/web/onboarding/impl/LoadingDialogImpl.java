package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.LoadingDialog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

/**
 * Created by vsnigur on 7/25/17.
 */
public class LoadingDialogImpl extends AbstractPageObject implements LoadingDialog {

    @StoredId
    private By loadingPopup;

    public LoadingDialogImpl(WebDriver webDriver) {
        super(webDriver);
    }


    @Override
    public boolean isDisplayed() {
        Reporter.log("Wait for loading progress bar element appears.");
        try {
            return waitUntilDisplayed(loadingPopup).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
