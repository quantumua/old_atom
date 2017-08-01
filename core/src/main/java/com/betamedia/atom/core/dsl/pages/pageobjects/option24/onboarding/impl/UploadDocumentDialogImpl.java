package com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.UploadDocumentDialog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by vsnigur on 6/7/17.
 * dialog after questionnaires to upload document scan for identification person
 */
public class UploadDocumentDialogImpl extends AbstractPageObject implements UploadDocumentDialog {

    @StoredId
    private By uploadDocumentDialogImpl;
    @StoredId
    private By documents;
    @StoredId
    private By poiTitle;
    @StoredId
    private By poiContainet;
    @StoredId
    private By poiTypeSelector;
    @StoredId
    private By poiBrowseButton;
    @StoredId
    private By poiDocumentAprroved;
    @StoredId
    private By poiDocumentNotAprroved;

    public UploadDocumentDialogImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean exists() {
        return waitUntilExists(uploadDocumentDialogImpl).isDisplayed();
    }

    @Override
    public void goToDocumentsUpload(){
        waitUntilDisplayed(documents).click();
    }

    @Override
    public void selsetPOI(){
        waitUntilDisplayed(poiTitle).click();
    }

    @Override
    public void browseForFileUpload(){
        waitUntilDisplayed(poiBrowseButton).click();
    }
}