package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.UploadDocumentsTab;
import com.betamedia.atom.core.fwtestrunner.storage.FileSystemStorageService;
import static com.betamedia.atom.core.testingtype.base.AbstractTest.softAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Leonid Artemiev
 * @since 7/20/17
 */
public class UploadDocumentsTabImpl extends AbstractPageObject implements UploadDocumentsTab {

    @StoredId
    private By winID;

    /*POI (Proof of identity) Controls*/
    @StoredId
    private By poiHeader;
    @StoredId
    private By poiHeaderCollapsed;
    @StoredId
    private By poiDocumentTypeSelector;
    @StoredId
    private By poiWrapper;
    @StoredId
    private By documentTypeButton;
    @StoredId
    private By poiUploadInput;
    @StoredId
    private By poiPassportSelection;
    @StoredId
    private By poiFrontImage;
    @StoredId
    private By poiBackImage;
    @StoredId
    private By poiIdCardFrontSelection;
    @StoredId
    private By poiIdCardBackSelection;
    @StoredId
    private By poiDriverLicenseFrontSelection;
    @StoredId
    private By poiDriverLicenseBackSelection;
        // POI Document Upload Status controls
    @StoredId
    private By poiOveralStatusReviewed;
    @StoredId
    private By poiImageSent;
    @StoredId
    private By poiImageReviewed;
    @StoredId
    private By poiImageApproved;
    @StoredId
    private By poiRedXImage;

    /* POR (Proof of residency) Controls*/
    @StoredId
    private By porHeader;
    @StoredId
    private By porWrapper;
    @StoredId
    private By porUploadInput;
    @StoredId
    private By porSentImage;
    @StoredId
    private By porNotApprovedXImage;




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

    public UploadDocumentsTabImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean exists() {
        return waitUntilExists(winID).isDisplayed();
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
    public void invoke(){
    	waitUntilDisplayed(winID).click();
    }

    @Override
    public void creditCardClickHeader(){
        waitUntilDisplayed(creditCardHeader).click();
        waitUntilDisplayed(creditCardWrapper);
    }

    @Override
    public void porClickHeader(){
        waitUntilDisplayed(porHeader).click();
        waitUntilDisplayed(porWrapper);
    }

    @Override
    public void uploadCreditCard(String frontImagePath, String backImagePath) {
        creditCardClickHeader();
        setDisplayBlock(creditCardUploadInput);
        uploadFromPath(storeToTemp(frontImagePath), creditCardUploadInput);
        selectDocumentType(creditCardWrapper, creditCardSelection, true, creditCardFrontImage);
        waitUntilExists(creditCardBackImage).isDisplayed();
        find(creditCardUploadInput).clear();
        uploadFromPath(storeToTemp(backImagePath), creditCardUploadInput);
    }

    @Override
    public void verifyPOIDocumentIsUploaded() {
//        softAssert().assertTrue(waitUntilExists(poiOveralStatusReviewed).isDisplayed(), "Overal status Reviewed is available");
        softAssert().assertTrue(waitUntilExists(poiImageSent).isDisplayed(), "Image icon Sent is available");
        softAssert().assertTrue(waitUntilExists(poiImageReviewed).isDisplayed(), "Image icon Reviewed is available");
        softAssert().assertTrue(waitUntilExists(poiImageApproved).isDisplayed(), "Image icon Approved is available");
    }

    @Override
    public void poiUploadPassport(String imagePath) {
        poiExpandHeader();
        selectDocumentType(poiWrapper, poiPassportSelection, false, null);
        /*make input element visible*/
        setDisplayBlock(poiUploadInput);
        /*upload file*/
        uploadFromPath(storeToTemp(imagePath), poiUploadInput);
        /*wait until upload is over and back image is available*/
    }

    @Override
    public void poiUploadIdCardDocuments(String imageFrontPath, String imageBackPath) {
        poiUploadTwoSidePOIDocuments(poiIdCardFrontSelection, poiIdCardBackSelection, imageFrontPath, imageBackPath);
    }

    @Override
    public void poiUploadDriverLicenseDocuments(String imageFrontPath, String imageBackPath) {
        poiUploadTwoSidePOIDocuments(poiDriverLicenseFrontSelection, poiDriverLicenseBackSelection, imageFrontPath, imageBackPath);
    }

    private void poiUploadTwoSidePOIDocuments(By documentFrontSelection, By documentBackSelection, String imageFrontPath, String imageBackPath) {
        poiExpandHeader();
        selectDocumentType(poiWrapper, documentFrontSelection, true, poiFrontImage);
        /*make input element visible*/
        setDisplayBlock(poiUploadInput);
        /*upload file*/
        uploadFromPath(storeToTemp(imageFrontPath), poiUploadInput);
        /*wait until upload is over and back image is available*/
        waitUntilDisplayed(poiImageApproved);
        selectDocumentType(poiWrapper, documentBackSelection, true, poiBackImage);
        find(poiUploadInput).clear();
        uploadFromPath(storeToTemp(imageBackPath), poiUploadInput);
    }

    @Override
    public void verifyPOIDocumentsUploaded(int documentsCount) {
        softAssert().assertTrue(waitUntil(() ->
                findElements(poiImageSent).size() == documentsCount),
                "Unable to locate " + documentsCount + " submitted POI Documents");
    }

    @Override
    public void verifyPOIInvalidDocumentUploaded(int documentsCount) {
        softAssert().assertTrue(waitUntil(() ->
                        findElements(poiRedXImage).size() == documentsCount),
                "Unable to locate " + documentsCount + " not approved POI Documents");
    }

    private void poiClickHeader(){
        waitUntilDisplayed(poiHeader).click();
    }

    private void poiExpandHeader(){
        if (exists(poiHeaderCollapsed)) {
            poiClickHeader();
        }
        scrollIntoView(waitUntilExists(poiUploadInput));
        scrollIntoView(find(poiHeader));
    }

    private static String storeToTemp(String resource) {
        return Paths.get(FileSystemStorageService.storeSystemResource(resource, UUID.randomUUID().toString() + ".jpg", "temp")).toAbsolutePath().toString();
    }

    private void selectDocumentType(By wrapper, By locator, Boolean waitForTransformation, By transformImage) {
        waitUntilDisplayed(wrapper, documentTypeButton).click();
        waitUntilDisplayed(wrapper, locator).click();
        if (waitForTransformation) {
            /*wait until animation starts*/
            waitUntil(() -> !checkCssProperty("transform", "none", transformImage));
            /*wait until animation ends*/
            waitUntil(() -> checkCssProperty("transform", "none", transformImage));
        }
    }
}
