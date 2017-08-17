package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding;

import com.betamedia.atom.core.dsl.pages.extensions.LocalizationOperations;

/**
 * @author Leonid Artemiev
 * @since 7/20/17
 */
public interface UploadDocumentsTab extends LocalizationOperations {
    boolean exists();

    boolean creditCardImagesSentMsgExists();

    boolean creditCardXImageExists();

    void invoke();

    void creditCardClickHeader();

    void porClickHeader();

    void uploadCreditCard(String frontImagePath, String backImagePath);

    void verifyPOIOveralStatusReviewed();

    void verifyPOROveralStatusReviewed();

    void poiUploadPassport(String poiPassportPath);

    void porUploadOtherRelevantBill(String imagePath);

    void poiUploadIdCardDocuments(String imageFrontPath, String imageBackPath);

    void poiUploadDriverLicenseDocuments(String imageFrontPath, String imageBackPath);

    void porUploadGasBill(String imagePath);

    boolean isPorHeaderCollapsed();

    void verifyPOIDocumentsUploaded(int documentsCount);

    void verifyPOIInvalidDocumentUploaded(int documentsCount);

    void verifyPORDocumentsUploaded(int documentsCount);

    void verifyPORInvalidDocumentUploaded(int documentsCount);

    boolean verifyPORsectionExpanded();

}