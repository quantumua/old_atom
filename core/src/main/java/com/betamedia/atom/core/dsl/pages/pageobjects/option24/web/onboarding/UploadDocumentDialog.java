package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding;

/**
 * Created by vsnigur on 6/7/17.
 */
public interface UploadDocumentDialog {

    boolean exists();

    void goToDocumentsUpload();

    void clickPOIHeader();

    void uploadIdCard();

    void uploadPassport();

    void uploadDriverLicense();

    boolean poiBackImageExists();
}