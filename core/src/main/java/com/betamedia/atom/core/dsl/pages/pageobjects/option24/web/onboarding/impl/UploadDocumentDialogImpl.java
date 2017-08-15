package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.UploadDocumentDialog;
import com.betamedia.atom.core.fwtestrunner.storage.FileSystemStorageService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.nio.file.Paths;
import java.util.UUID;

/**
 * Created by vsnigur on 6/7/17.
 * dialog after questionnaires to upload document scan for identification person
 */
public class UploadDocumentDialogImpl extends AbstractPageObject implements UploadDocumentDialog {

    private static final String POI_ID_FRONT_PATH = "files/sample_id_front.jpg";
    private static final String POI_ID_BACK_PATH = "files/sample_id_back.jpg";

    @StoredId
    private By pageRoot;
    @StoredId
    private By uploadDocumentDialogCloseButton;
    @StoredId
    private By poiContainer;
    @StoredId
    private By poiDocumentApproved;
    @StoredId
    private By poiDocumentNotApproved;

    /* POI (Proof of identity) Controls*/
    @StoredId
    private By poiHeader;
    @StoredId
    private By poiDocumentTypeSelector;
    @StoredId
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
    @StoredId
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

    public UploadDocumentDialogImpl(WebDriver webDriver) {
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
    public void uploadIdCard() {
        uploadIdCard(POI_ID_FRONT_PATH, POI_ID_BACK_PATH);
    }

    @Override
    public void uploadIdCard(String frontImagePath, String backImagePath) {
        poiClickHeader();
//        request removing !important from display:none on document type <select> styling to make selection easier
//        setDisplayBlock(poiDocumentTypeSelector);
//        inSelect(poiDocumentTypeSelector).selectByVisibleText("Identity Card");
        /*click on button and then on option for ID card*/
        selectPOIDocumentType(poiIDSelection);
        /*make input element visible*/
        setDisplayBlock(poiUploadInput);
        /*upload file*/
        uploadFromPath(storeToTemp(frontImagePath), poiUploadInput);
        /*wait until upload is over and back image is available*/
        waitUntilExists(poiBackImage).isDisplayed();
        /*clear input form*/
        find(poiUploadInput).clear();
        /*upload file*/
        uploadFromPath(storeToTemp(backImagePath), poiUploadInput);
    }

    @Override
    public void poiUploadPassport(String imagePath) {
        poiClickHeader();
        selectPOIDocumentType(poiPassportSelection, false);
        /*make input element visible*/
        setDisplayBlock(poiUploadInput);
        /*upload file*/
        uploadFromPath(storeToTemp(imagePath), poiUploadInput);
        /*wait until upload is over and back image is available*/
    }

    @Override
    public boolean poiXImageExists() {
        return waitUntilExists(poiNotApprovedXImage).isDisplayed();
    }

    @Override
    public void poiUploadDriverLicense(String frontImagePath, String backImagePath) {
        poiClickHeader();
        selectPOIDocumentType(poiDriverLicenseSelection);
        setDisplayBlock(poiUploadInput);
        uploadFromPath(storeToTemp(frontImagePath), poiUploadInput);
        waitUntilExists(poiBackImage).isDisplayed();
        find(poiUploadInput).clear();
        uploadFromPath(storeToTemp(backImagePath), poiUploadInput);
    }

    @Override
    public void porUploadElectricityBill(String imagePath) {
        porClickHeader();
        //Electricity bill selected by default
        /*make input element visible*/
        setDisplayBlock(porUploadInput);
        /*upload file*/
        uploadFromPath(storeToTemp(imagePath), porUploadInput);
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
            waitUntil(() -> !checkCssValue("transform", "none", poiFrontImage));
            /*wait until animation ends*/
            waitUntil(() -> checkCssValue("transform", "none", poiFrontImage));
        }
    }
}