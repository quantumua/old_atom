package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.fwtestrunner.storage.FileSystemStorageService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.UploadDocumentsTab;

import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author Leonid Artemiev
 * @since 7/20/17
 */
public class UploadDocumentsTabImpl extends AbstractPageObject implements UploadDocumentsTab {

    @StoredId
    private By uploadDocumentsTab;
    /*Credit Card Controls*/
    @StoredId
    private By creditCardHeader;
    @StoredId
    private By creditCardWrapper;
    @StoredId
    private By creditCardUploadInput;
    @StoredId
    private By creditCardFrontImage;
    @StoredId
    private By creditCardBackImage;
    @StoredId
    private By creditCardSelection;
    @StoredId
    private By creditCardSentImage;
    @StoredId
    private By creditCardNotApprovedXImage;
    /* POR (Proof of residence) Controls*/
    @StoredId
    private By porHeader;
    @StoredId
    private By porUploadInput;
    @StoredId
    private By porSentImage;
    @StoredId
    private By porNotApprovedXImage;

    public UploadDocumentsTabImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean exists() {
        return waitUntilExists(uploadDocumentsTab).isDisplayed();
    }

    @Override
    public boolean porXImageExists() {
        return exists(porNotApprovedXImage);
    }

    @Override
    public boolean porImagesSentMsgExists() {
        return exists(porSentImage);
    }

    @Override
    public boolean creditCardImagesSentMsgExists() {
        return exists(creditCardSentImage);
    }

    @Override
    public boolean creditCardXImageExists() {
        return exists(creditCardNotApprovedXImage);
    }

    @Override
    public void goToDocumentsUpload(){
    	waitUntilDisplayed(uploadDocumentsTab).click();
    }

    @Override
    public void clickCreditCardHeader(){
        waitUntilDisplayed(creditCardHeader).click();
    }

    @Override
    public void clickPORHeader(){
        waitUntilDisplayed(porHeader).click();
    }

    @Override
    public void porUploadElectricityBill(String imagePath) {
        clickPORHeader();
        //Electricity bill selected by default
        /*make input element visible*/
        setDisplayBlock(porUploadInput);
        /*upload file*/
        uploadFromPath(storeToTemp(imagePath), porUploadInput);
        /*wait until upload is over and back image is available*/
    }

    @Override
    public void uploadCreditCard(String frontImagePath, String backImagePath) {
        clickCreditCardHeader();
        setDisplayBlock(creditCardUploadInput);
        uploadFromPath(storeToTemp(frontImagePath), creditCardUploadInput);
        selectCreditCardDocumentType(creditCardSelection, true);
        waitUntilExists(creditCardBackImage).isDisplayed();
        find(creditCardUploadInput).clear();
        uploadFromPath(storeToTemp(backImagePath), creditCardUploadInput);
    }

    private boolean isTransformed(By locator) {
        return !find(locator).getCssValue("transform").equals("none");
    }

    private static String storeToTemp(String resource) {
        return Paths.get(FileSystemStorageService.storeSystemResource(resource, UUID.randomUUID().toString() + ".jpg", "temp")).toAbsolutePath().toString();
    }

    private void selectCreditCardDocumentType(By locator, Boolean waitForTransformation) {
        waitUntilDisplayed(creditCardWrapper).click();
        waitUntilDisplayed(locator).click();
        if (waitForTransformation) {
            /*wait until animation starts*/
            waitUntil(() -> isTransformed(creditCardFrontImage));
            /*wait until animation ends*/
            waitUntil(() -> !isTransformed(creditCardFrontImage));
        }
    }
}
