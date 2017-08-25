package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding;

import com.betamedia.atom.core.dsl.pages.extensions.*;

/**
 * @author Leonid Artemiev
 * @since 7/20/17
 */
public interface UploadDocumentsTab extends LocalizationOperations, CssOperations, FieldOperations, ScriptOperations {
    boolean exists();

    boolean creditCardImagesSentMsgExists();

    boolean creditCardXImageExists();

    UploadDocumentsTab invoke();

    void creditCardClickHeader();

    void porClickHeader();

    UploadDocumentsTab uploadCreditCard(String frontImagePath, String backImagePath);

    void verifyPOIOveralStatusReviewed();

    void verifyPOROveralStatusReviewed();

    UploadDocumentsTab poiUploadPassport(String poiPassportPath);

    UploadDocumentsTab porUploadOtherRelevantBill(String imagePath);

    UploadDocumentsTab poiUploadIdCardDocuments(String imageFrontPath, String imageBackPath);

    UploadDocumentsTab poiUploadDriverLicenseDocuments(String imageFrontPath, String imageBackPath);

    UploadDocumentsTab porUploadGasBill(String imagePath);

    boolean isPorHeaderCollapsed();

    void verifyPOIDocumentsUploaded(int documentsCount);

    void verifyPOIInvalidDocumentUploaded(int documentsCount);

    void verifyPORDocumentsUploaded(int documentsCount);

    void verifyPORInvalidDocumentUploaded(int documentsCount);

    boolean verifyPORsectionExpanded();

}