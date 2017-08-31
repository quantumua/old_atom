package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.extensions.CssOperations;
import com.betamedia.atom.core.dsl.pages.extensions.ScriptOperations;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.UploadDocumentsDialog;
import com.betamedia.atom.core.fwtestrunner.storage.FileSystemStorageService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.nio.file.Paths;
import java.util.UUID;

/**
 * Created by vsnigur on 6/7/17.
 * dialog after questionnaires to upload document scan for identification person
 */
public class UploadDocumentsDialogImpl extends AbstractPageObject implements UploadDocumentsDialog, CssOperations, ScriptOperations {

    private static final String POI_ID_FRONT_PATH = "files/sample_id_front.jpg";
    private static final String POI_ID_BACK_PATH = "files/sample_id_back.jpg";

    @StoredId
    private By pageRoot;
    /* smsWrapper */
    @StoredId (localized = true)
    private By progressBarText;
    @StoredId (localized = true)
    private By chatLink;
    @StoredId (localized = true)
    private By docHeader;
    @StoredId (localized = true)
    private By docDescription;
    @StoredId (localized = true)
    private By smsWrapper;
    @StoredId (localized = true)
    private By smsWrapperDescription;
    @StoredId
    private By uploadDocumentDialogCloseButton;
    @StoredId
    private By poiContainer;
    @StoredId
    private By poiDocumentApproved;
    @StoredId
    private By poiDocumentNotApproved;

    /* POI (Proof of identity) Controls*/
    @StoredId (localized = true)
    private By poiHeader;
    @StoredId //(localized = true)
    private By poiDocumentTypeSelector;
    @StoredId //(localized = true)
    private By poiWrapper;
    @StoredId
    private By poiUploadInput;
    @StoredId
    private By poiIDSelection;
    @StoredId
    private By poiPassportSelection;
    @StoredId
    private By poiDriverLicenseSelection;
    @StoredId
    private By velocityAnimatingImage;
    @StoredId
    private By poiFrontImage;
    @StoredId
    private By poiBackImage;
    @StoredId
    private By poiNotApprovedXImage;
    @StoredId
    private By poiUploadDocumentWrapperOpen;
    @StoredId
    private By poiUploadDocumentWrapperClosed;

    /* POR (Proof of residence) Controls*/
    @StoredId (localized = true)
    private By porHeader;
    @StoredId
    private By porDocumentTypeSelector;
    @StoredId
    private By porWrapper;
    @StoredId
    private By porUploadInput;
    @StoredId
    private By documentTypeButton;
    @StoredId
    private By porCheckmarkImage;
    @StoredId
    private By porApprovedImage;
    @StoredId
    private By porNotApprovedXImage;
    @StoredId
    private By porUploadDocumentWrapperOpen;
    @StoredId
    private By porUploadDocumentWrapperClosed;

    public UploadDocumentsDialogImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean exists() {
        return waitUntilExists(pageRoot).isDisplayed();
    }

    @Override
    public boolean porXImageExists() {
        return waitUntilExists(porNotApprovedXImage).isDisplayed();
    }

    @Override
    public boolean porCheckmarkImageExists() {
        return waitUntilExists(porCheckmarkImage).isDisplayed();
    }

    @Override
    public boolean isExitButtonExists(){
        return exists(uploadDocumentDialogCloseButton);
    }

    @Override
    public boolean isPoiExpanded(){
        return waitUntilDisplayed(poiUploadDocumentWrapperOpen).isDisplayed();
    }

    @Override
    public boolean isPoiCollapsed(){
        return waitUntilDisplayed(poiUploadDocumentWrapperClosed).isDisplayed();
    }

    @Override
    public boolean isPorExpanded(){
        return waitUntilDisplayed(porUploadDocumentWrapperOpen).isDisplayed();
    }

    @Override
    public boolean isPorCollapsed(){
        return waitUntilDisplayed(porUploadDocumentWrapperClosed).isDisplayed();
    }

    @Override
    public void close() {
        waitUntilDisplayed(uploadDocumentDialogCloseButton).click();
    }

    @Override
    public void poiClickHeader(){
        waitUntilDisplayed(poiHeader).click();
    }

    @Override
    public UploadDocumentsDialog poiUploadIdCard() {
        poiUploadIdCard(POI_ID_FRONT_PATH, POI_ID_BACK_PATH);
        return this;
    }

    @Override
    public UploadDocumentsDialog poiUploadIdCard(String frontImagePath, String backImagePath) {
        poiClickHeader();
        selectPOIDocumentType(poiIDSelection);
        setDisplayBlock(poiUploadInput);
        uploadFromPath(storeToTemp(frontImagePath), poiUploadInput);
        waitUntilExists(poiBackImage).isDisplayed();
        clearElementText(poiUploadInput);
        uploadFromPath(storeToTemp(backImagePath), poiUploadInput);
        return this;
    }

    @Override
    public UploadDocumentsDialog poiUploadPassport(String imagePath) {
        poiClickHeader();
        selectPOIDocumentType(poiPassportSelection, false);
        /*make input element visible*/
        setDisplayBlock(poiUploadInput);
        /*upload file*/
        uploadFromPath(storeToTemp(imagePath), poiUploadInput);
        /*wait until upload is over and back image is available*/
        return this;
    }

    @Override
    public boolean poiXImageExists() {
        return waitUntilExists(poiNotApprovedXImage).isDisplayed();
    }

    @Override
    public UploadDocumentsDialog poiUploadDriverLicense(String frontImagePath, String backImagePath) {
        poiClickHeader();
        selectPOIDocumentType(poiDriverLicenseSelection);
        setDisplayBlock(poiUploadInput);
        uploadFromPath(storeToTemp(frontImagePath), poiUploadInput);
        waitUntil(() -> maybe(()-> find(poiBackImage)).orElseGet(() -> find(poiNotApprovedXImage)).isDisplayed()); // waitUntilExists(poiBackImage).isDisplayed();
        clearElementText(poiUploadInput);
        uploadFromPath(storeToTemp(backImagePath), poiUploadInput);
        return this;
    }

    @Override
    public UploadDocumentsDialog porUploadElectricityBill(String imagePath) {
        porClickHeader();
        //Electricity bill selected by default
        waitUntilDisplayed(porWrapper);
        /*make input element visible*/
        setDisplayBlock(porUploadInput);
        /*upload file*/
        uploadFromPath(storeToTemp(imagePath), porUploadInput);
        return this;
    }

    @Override
    public boolean poiBackImageExists() {
        return exists(poiBackImage);
    }

    @Override
    public void porClickHeader(){
        waitUntilDisplayed(porHeader).click();
    }

    private static String storeToTemp(String resource) {
        return Paths.get(FileSystemStorageService.storeSystemResource(resource, UUID.randomUUID().toString() + ".jpg", "temp")).toAbsolutePath().toString();
    }

    private void selectPOIDocumentType(By locator) {
        selectPOIDocumentType(locator, true);
    }

    private void selectPOIDocumentType(By locator, Boolean waitForTransformation) {
        scrollIntoView(waitUntilDisplayed(poiWrapper, documentTypeButton)).click();
        waitUntilDisplayed(poiWrapper, locator).click();
        if (waitForTransformation) {
            /*wait until animation starts*/
            // waitUntil(() -> !checkCssValue("transform", "none", poiFrontImage));
            waitUntil(() -> exists(velocityAnimatingImage));
            /*wait until animation ends*/
            // waitUntil(() -> checkCssValue("transform", "none", poiFrontImage));
            waitUntil(() -> !exists(velocityAnimatingImage));
        }
    }
}