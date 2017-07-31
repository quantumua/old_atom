package com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.UploadDocumentsPage;

/**
 * @author Leonid Artemiev
 * @since 7/20/17
 */
public class UploadDocumentsPageImpl extends AbstractPageObject implements UploadDocumentsPage {

    @StoredId
    private By uploadDocumentsPageImpl;
    @StoredId
    private By documents;
    @StoredId
    private By poiTitle;
    @StoredId
    private By poiContainer;
    @StoredId
    private By poiTypeSelector;
    @StoredId
    private By poiBrowseButton;
    @StoredId
    private By poiDocumentAprroved;
    @StoredId
    private By poiDocumentNotAprroved;

    public UploadDocumentsPageImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean exists() {
        return waitUntilExists(uploadDocumentsPageImpl).isDisplayed();
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
