package com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.UploadDocumentDialog;
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
    private static final String POI_PASSPORT_PATH = "files/sample_passport_tmp.jpg";
    private static final String POI_DRIVER_LICENSE_FRONT_PATH = "files/sample_driver_license_front_tmp.jpg";
    private static final String POI_DRIVER_LICENSE_BACK_PATH = "files/sample_driver_license_back_tmp.jpg";

    @StoredId
    private By pageRoot;
    @StoredId
    private By documents;
    @StoredId
    private By poiContainer;
    @StoredId
    private By poiDocumentApproved;
    @StoredId
    private By poiDocumentNotApproved;
// ------------
    @StoredId
    private By poiHeader;
    @StoredId
    private By poiDocumentTypeSelector;
    @StoredId
    private By poiWrapper;
    @StoredId
    private By poiUploadInput;
    @StoredId
    private By poiFrontImage;
    @StoredId
    private By poiBackImage;
    @StoredId
    private By idSelection;
    @StoredId
    private By passportSelection;
    @StoredId
    private By driverLicenseSelection;
    @StoredId
    private By documentTypeButton;

    public UploadDocumentDialogImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean exists() {
        return waitUntilExists(pageRoot).isDisplayed();
    }

    @Override
    public void goToDocumentsUpload(){
        waitUntilDisplayed(documents).click();
    }

    @Override
    public void clickPOIHeader(){
        waitUntilDisplayed(poiHeader).click();
    }

    @Override
    public void uploadIdCard() {
        clickPOIHeader();
//        request removing !important from display:none on document type <select> styling to make selection easier
//        setDisplayBlock(poiDocumentTypeSelector);
//        inSelect(poiDocumentTypeSelector).selectByVisibleText("Identity Card");
        /*click on button and then on option for ID card*/
        selectPOIDocumentType(idSelection);
        /*make input element visible*/
        setDisplayBlock(poiUploadInput);
        /*upload file*/
        uploadFromPath(storeToTemp(POI_ID_FRONT_PATH), poiUploadInput);
        /*wait until upload is over and back image is available*/
        waitUntilExists(poiBackImage).isDisplayed();
        /*clear input form*/
        find(poiUploadInput).clear();
        /*upload file*/
        uploadFromPath(storeToTemp(POI_ID_BACK_PATH), poiUploadInput);
    }

    @Override
    public void uploadPassport() {
        clickPOIHeader();
        selectPOIDocumentType(passportSelection);
        /*make input element visible*/
        setDisplayBlock(poiUploadInput);
        /*upload file*/
        uploadFromPath(storeToTemp(POI_PASSPORT_PATH), poiUploadInput);
        /*wait until upload is over and back image is available*/
    }

    @Override
    public void uploadDriverLicense() {
        clickPOIHeader();
        selectPOIDocumentType(driverLicenseSelection);
        setDisplayBlock(poiUploadInput);
        uploadFromPath(storeToTemp(POI_DRIVER_LICENSE_FRONT_PATH), poiUploadInput);
        waitUntilExists(poiBackImage).isDisplayed();
        find(poiUploadInput).clear();
        uploadFromPath(storeToTemp(POI_DRIVER_LICENSE_BACK_PATH), poiUploadInput);
    }

    @Override
    public boolean poiBackImageExists() {
        return exists(poiBackImage);
    }

    private boolean isTransformed(By locator) {
        return !find(locator).getCssValue("transform").equals("none");
    }

    private static String storeToTemp(String resource) {
        return Paths.get(FileSystemStorageService.storeSystemResource(resource, UUID.randomUUID().toString() + ".jpg", "temp")).toAbsolutePath().toString();
    }

    private void selectPOIDocumentType(By locator) {
        waitUntilDisplayed(poiWrapper, documentTypeButton).click();
        waitUntilDisplayed(poiWrapper, locator).click();
        /*wait until animation starts*/
        waitUntil(() -> isTransformed(poiFrontImage));
        /*wait until animation ends*/
        waitUntil(() -> !isTransformed(poiFrontImage));
    }

}