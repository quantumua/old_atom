package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding;

/**
 * @author Leonid Artemiev
 * @since 7/20/17
 */
public interface UploadDocumentsTab {
    boolean exists();

    boolean creditCardImagesSentMsgExists();

    boolean porImagesSentMsgExists();

    boolean porXImageExists();

    boolean creditCardXImageExists();

    void goToDocumentsUpload();

    void clickCreditCardHeader();

    void clickPORHeader();

    void porUploadElectricityBill(String imagePath);

    void uploadCreditCard(String frontImagePath, String backImagePath);

}
