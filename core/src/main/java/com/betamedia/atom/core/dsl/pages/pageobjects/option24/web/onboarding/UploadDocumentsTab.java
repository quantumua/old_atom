package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding;

import com.betamedia.atom.core.fwdataaccess.repository.util.Language;

/**
 * @author Leonid Artemiev
 * @since 7/20/17
 */
public interface UploadDocumentsTab {
    boolean exists();

    boolean creditCardImagesSentMsgExists();

   boolean creditCardXImageExists();

    void invoke();

    void creditCardClickHeader();

    void porClickHeader();

    void uploadCreditCard(String frontImagePath, String backImagePath);

    void verifyPOIDocumentIsUploaded();

    void verifyPORDocumentIsUploaded();

    void poiUploadPassport(String poiPassportPath);

    void poiUploadIdCardDocuments(String imageFrontPath, String imageBackPath);

    void poiUploadDriverLicenseDocuments(String imageFrontPath, String imageBackPath);

    void porUploadGasBill(String imagePath);

    void verifyPOIDocumentsUploaded(int documentsCount);

    void verifyPOIInvalidDocumentUploaded(int documentsCount);

    void verifyPORDocumentsUploaded(int documentsCount);

    void verifyPORInvalidDocumentUploaded(int documentsCount);

    boolean verifyPORsectionExpanded();

    void verifyTextDirectionElements(String expectedDirection);

    void verifySlideLocalization(Language language);
}