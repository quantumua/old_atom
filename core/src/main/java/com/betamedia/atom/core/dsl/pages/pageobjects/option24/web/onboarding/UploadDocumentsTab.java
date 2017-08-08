package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding;

/**
 * @author Leonid Artemiev
 * @since 7/20/17
 */
public interface UploadDocumentsTab {
    boolean exists();

    boolean creditCardImagesSentMsgExists();

    void goToDocumentsUpload();

    void clickCreditCardHeader();

    void uploadCreditCard();

}
