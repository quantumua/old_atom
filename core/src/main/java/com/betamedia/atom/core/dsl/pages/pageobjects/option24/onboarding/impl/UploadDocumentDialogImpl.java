package com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.UploadDocumentDialog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by vsnigur on 6/7/17.
 */
public class UploadDocumentDialogImpl extends AbstractPageObject implements UploadDocumentDialog {

    @StoredId
    private By uploadDocumentDialogImpl;

    public UploadDocumentDialogImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean exists() {
        return waitUntilExists(uploadDocumentDialogImpl).isDisplayed();
    }
}
