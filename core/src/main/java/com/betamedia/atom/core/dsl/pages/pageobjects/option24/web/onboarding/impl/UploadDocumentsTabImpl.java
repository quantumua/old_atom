package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.UploadDocumentsTab;
import com.betamedia.atom.core.dsl.pages.utils.PageObjectUtils;
import com.betamedia.atom.core.fwdataaccess.repository.util.Language;
import com.betamedia.atom.core.fwtestrunner.storage.FileSystemStorageService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.nio.file.Paths;
import java.util.UUID;

import static com.betamedia.atom.core.testingtype.base.AbstractTest.softAssert;

/**
 * @author Leonid Artemiev
 * @since 7/20/17
 */
public class UploadDocumentsTabImpl extends AbstractPageObject implements UploadDocumentsTab {

    @StoredId
    private By winID;

    /*POI (Proof of identity) Controls*/
    @StoredId (localized = true)
    private By poiHeader;
    @StoredId
    private By poiHeaderCollapsed;
    @StoredId
    private By poiHeaderExpanded;
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
    private By porHeaderCollapsed;
    @StoredId
    private By porHeaderExpanded;
    @StoredId
    private By porWrapper;
    @StoredId
    private By porUploadInput;
    @StoredId
    private By porImageSent;
    @StoredId
    private By porImageReviewed;
    @StoredId
    private By porImageApproved;
    @StoredId
    private By porRedXImage;
    @StoredId
    private By porGasBillSelection;
    @StoredId
    private By porOtherRelevantBillSelection;
    @StoredId
    private  By porOveralStatusReviewed;

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
    public void verifyPOIOveralStatusReviewed() {
        softAssert().assertTrue(waitUntilExists(poiOveralStatusReviewed).isDisplayed(), "Overal status Reviewed is available");
    }

    @Override
    public void verifyPOROveralStatusReviewed() {
        softAssert().assertTrue(waitUntilExists(porOveralStatusReviewed).isDisplayed(), "Overal status Reviewed is available");
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
    public void porUploadGasBill(String imagePath){
        porUploadDocument(imagePath, porGasBillSelection);
    }

    @Override
    public void porUploadOtherRelevantBill(String imagePath){
        porUploadDocument(imagePath, porOtherRelevantBillSelection);
    }

    @Override
    public void poiUploadIdCardDocuments(String imageFrontPath, String imageBackPath) {
        poiUploadTwoSidePOIDocuments(poiIdCardFrontSelection, poiIdCardBackSelection, imageFrontPath, imageBackPath);
    }

    @Override
    public void poiUploadDriverLicenseDocuments(String imageFrontPath, String imageBackPath) {
        poiUploadTwoSidePOIDocuments(poiDriverLicenseFrontSelection, poiDriverLicenseBackSelection, imageFrontPath, imageBackPath);
    }

    @Override
    public boolean isPorHeaderCollapsed(){
        return waitUntilDisplayed(porHeaderCollapsed).isDisplayed();
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
        find(poiUploadInput).clear();
    }

    private void porUploadDocument(String imagePath, By documentType) {
        porExpandHeader();
        selectDocumentType(porWrapper, documentType, false, null);
        /*make input element visible*/
        setDisplayBlock(porUploadInput);
        /*upload file*/
        uploadFromPath(storeToTemp(imagePath), porUploadInput);
        /*wait until upload is over and back image is available*/
    }

    @Override
    public void verifyPOIDocumentsUploaded(int documentsCount) {
        softAssert().assertTrue(waitUntil(() ->
                findElements(poiImageSent).size() == documentsCount),
                "Looking for " + documentsCount + " 'Sent' POI Documents");
        softAssert().assertTrue(waitUntil(() ->
                        findElements(poiImageReviewed).size() == documentsCount),
                "Looking for " + documentsCount + " 'Reviewed' POI Documents");
        softAssert().assertTrue(waitUntil(() ->
                        findElements(poiImageApproved).size() == documentsCount),
                "Looking for " + documentsCount + " 'Approved' POI Documents");
    }

    @Override
    public void verifyPOIInvalidDocumentUploaded(int documentsCount) {
        softAssert().assertTrue(waitUntil(() ->
                        findElements(poiRedXImage).size() == documentsCount),
                "Looking for " + documentsCount + " not approved POI Documents");
    }

    @Override
    public void verifyPORDocumentsUploaded(int documentsCount) {
        softAssert().assertTrue(waitUntil(() ->
                        findElements(porImageSent).size() == documentsCount),
                "Looking for " + documentsCount + " 'Sent' POR Documents");
        softAssert().assertTrue(waitUntil(() ->
                        findElements(porImageReviewed).size() == documentsCount),
                "Looking for " + documentsCount + " 'Reviewed' POR Documents");
        softAssert().assertTrue(waitUntil(() ->
                        findElements(porImageApproved).size() == documentsCount),
                "Looking for " + documentsCount + " 'Approved' POR Documents");
    }

    @Override
    public void verifyPORInvalidDocumentUploaded(int documentsCount) {
        softAssert().assertTrue(waitUntil(() ->
                        findElements(porRedXImage).size() == documentsCount),
                "Looking for " + documentsCount + " not approved POR Documents");
    }

    @Override
    public boolean verifyPORsectionExpanded(){
        return waitUntilDisplayed(porHeaderExpanded).isDisplayed();
    }

    private void poiClickHeader(){
        waitUntilDisplayed(poiHeader).click();
    }

    @Override
    public void verifyTextDirectionElements(String expectedDirection) {
        PageObjectUtils.forPageElements(
                element ->
                        softAssert().assertEquals(
                                getCssValue("direction", element).toLowerCase(),
                                expectedDirection.toLowerCase(),
                                "Text direction verification for: " + element),
                field -> true,
                storedId -> true,
                this);
    }

    @Override
    public void verifySlideLocalization(Language language) {
        PageObjectUtils.forPageElements(
                element ->
                        softAssert().assertEquals(
                                waitUntilDisplayed(element).getText(),
                                getLocalization(element, language),
                                "Text localization verification for: " + element),
                field -> true,
                StoredId::localized,
                this);
        }

    private void poiExpandHeader(){
        if (exists(poiHeaderCollapsed)) {
            poiClickHeader();
        }
        scrollIntoView(waitUntilExists(poiUploadInput));
        scrollIntoView(find(poiHeader));
    }

    private void poiCollapseHeader(){
        waitUntilDisplayed(poiHeaderExpanded);
        if (exists(poiHeaderExpanded)) {
            poiClickHeader();
        }
    }

    private void porExpandHeader(){
        poiCollapseHeader();
        if (exists(porHeaderCollapsed)) {
            porClickHeader();
        }
        scrollIntoView(waitUntilExists(porUploadInput));
        scrollIntoView(find(porHeader));
    }

    private static String storeToTemp(String resource) {
        return Paths.get(FileSystemStorageService.storeSystemResource(resource, UUID.randomUUID().toString() + ".jpg", "temp")).toAbsolutePath().toString();
    }

    private void selectDocumentType(By wrapper, By locator, Boolean waitForTransformation, By transformImage) {
        waitUntilDisplayed(wrapper, documentTypeButton).click();
        waitUntilDisplayed(wrapper, locator).click();
        if (waitForTransformation) {
            /*wait until animation starts*/
            waitUntil(() -> !checkCssValue("transform", "none", transformImage));
            /*wait until animation ends*/
            waitUntil(() -> checkCssValue("transform", "none", transformImage));
        }
    }
}
