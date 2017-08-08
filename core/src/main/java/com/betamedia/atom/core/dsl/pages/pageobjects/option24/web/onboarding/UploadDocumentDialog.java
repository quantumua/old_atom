package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding;

/**
 * Created by vsnigur on 6/7/17.
 */
public interface UploadDocumentDialog {

    boolean exists();

    void goToDocumentsUpload();

    void poiClickHeader();

    void uploadIdCard();

    void poiUploadPassport();

    void poiUploadDriverLicense();

    boolean poiBackImageExists();

    void porClickHeader();

    void close();
}