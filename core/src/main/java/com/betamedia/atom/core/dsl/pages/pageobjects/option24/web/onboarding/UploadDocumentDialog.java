package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding;

import org.testng.annotations.Optional;

/**
 * Created by vsnigur on 6/7/17.
 */
public interface UploadDocumentDialog {

    boolean exists();

    void goToDocumentsUpload();

    void poiClickHeader();

    void uploadIdCard();

    void uploadIdCard(String frontImagePath, String backImagePath);

    void poiUploadPassport(String imagePath);

    void poiXImageExists();

    void poiUploadDriverLicense(String frontImagePath, String backImagePath);

    boolean poiBackImageExists();

    void porClickHeader();

    void close();
}