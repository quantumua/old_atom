package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.fwtestrunner.storage.FileSystemStorageService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.UploadDocumentsTab;
import org.testng.annotations.Optional;

import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author Leonid Artemiev
 * @since 7/20/17
 */
public class UploadDocumentsTabImpl extends AbstractPageObject implements UploadDocumentsTab {

    private static final String CREDIT_CARD_FRONT_PATH = "files/sample_credit_card_front.jpg";
    private static final String CREDIT_CARD_BACK_PATH = "files/sample_credit_card_back.jpg";

    @StoredId
    private By uploadDocumentsTab;
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
    private By creditCardSentMsg;

    public UploadDocumentsTabImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean exists() {
        return waitUntilExists(uploadDocumentsTab).isDisplayed();
    }

    @Override
    public boolean creditCardImagesSentMsgExists() {
        return exists(creditCardSentMsg);
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
    public void uploadCreditCard() {
        clickCreditCardHeader();
        setDisplayBlock(creditCardUploadInput);
        uploadFromPath(storeToTemp(CREDIT_CARD_FRONT_PATH), creditCardUploadInput);
        selectCreditCardDocumentType(creditCardSelection, true);
        waitUntilExists(creditCardBackImage).isDisplayed();
        find(creditCardUploadInput).clear();
        uploadFromPath(storeToTemp(CREDIT_CARD_BACK_PATH), creditCardUploadInput);
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
