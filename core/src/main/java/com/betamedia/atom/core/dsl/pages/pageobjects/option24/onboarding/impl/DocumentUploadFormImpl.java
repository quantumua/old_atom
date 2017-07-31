package com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.DocumentUploadForm;
import com.betamedia.atom.core.fwtestrunner.storage.FileSystemStorageService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.nio.file.Paths;
import java.util.UUID;

/**
 * Created by mbelyaev on 5/24/17.
 */
public class DocumentUploadFormImpl extends AbstractPageObject implements DocumentUploadForm {
    private static final String ID_FRONT_PATH = "files/sample_id_front.jpg";
    private static final String ID_BACK_PATH = "files/sample_id_back.jpg";
    private static final String ID_CARD_SELECTION = "li[data-original-index='3']";

    @StoredId
    private By poiHeadline;
    @StoredId
    private By poiDocumentTypeSelector;
    @StoredId
    private By poiWrapper;
    @StoredId
    private By poiUploadInput;
    @StoredId
    private By poiFrontImage;

    public DocumentUploadFormImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void uploadIdCard() {
        waitUntilDisplayed(poiHeadline).click();
//        request removing !important from display:none on document type <select> styling to make selection easier
//        setDisplayBlock(poiDocumentTypeSelector);
//        inSelect(poiDocumentTypeSelector).selectByVisibleText("Identity Card");
        /*click on button and then on option for ID card*/
        waitUntilDisplayed(poiWrapper, By.tagName("button")).click();
        waitUntilDisplayed(poiWrapper, By.cssSelector(ID_CARD_SELECTION)).click();
        /*wait until animation starts*/
        waitUntil(() -> isTransformed(poiFrontImage));
        /*wait until animation ends*/
        waitUntil(() -> !isTransformed(poiFrontImage));
        /*make input element visible*/
        setDisplayBlock(poiUploadInput);
        /*upload file*/
        uploadFromPath(storeToTemp(ID_FRONT_PATH), poiUploadInput);
        /*wait until upload is over*/
        waitUntil(() -> find(poiWrapper, By.tagName("button")).getText().equals("Identity Card - Back\n "));
        /*clear input form*/
        find(poiUploadInput).clear();
        /*upload file*/
        uploadFromPath(storeToTemp(ID_BACK_PATH), poiUploadInput);
    }

    private boolean isTransformed(By locator) {
        return !find(locator).getCssValue("transform").equals("none");
    }

    private static String storeToTemp(String resource) {
        return Paths.get(FileSystemStorageService.storeSystemResource(resource, UUID.randomUUID().toString() + ".jpg", "temp")).toAbsolutePath().toString();
    }

}