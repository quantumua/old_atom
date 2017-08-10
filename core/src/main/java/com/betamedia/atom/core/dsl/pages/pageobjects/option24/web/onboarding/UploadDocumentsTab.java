package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding;

/**
 * @author Leonid Artemiev
 * @since 7/20/17
 */
public interface UploadDocumentsTab {
    boolean exists();

    boolean creditCardImagesSentMsgExists();

   boolean creditCardXImageExists();

    void goToDocumentsUpload();

    void creditCardClickHeader();

    void porClickHeader();

    void uploadCreditCard(String frontImagePath, String backImagePath);

    void verifyPOIDocumentIsUploaded();

    void poiUploadPassport(String poiPassportPath);
}
