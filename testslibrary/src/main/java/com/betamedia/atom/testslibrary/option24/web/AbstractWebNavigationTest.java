package com.betamedia.atom.testslibrary.option24.web;

import com.betamedia.atom.testslibrary.option24.end2end.bmw.AbstractWebCustomerRegistrationTest;

/**
 * Created by lartemyev on 9/6/17.
 */
public class AbstractWebNavigationTest extends AbstractWebCustomerRegistrationTest {

    public void closeOnboardingWizardAndGoTrade() {
        pages().uploadDocumentDialog().close();
        pages().confirmCloseMessage().acceptClose();
        pages().setLeverageDialog().selectLeverage("100");
    }

    public void closeOnboardingWizardAndGoToMyAccount() {
        closeOnboardingWizardAndGoTrade();
        pages().topNavigationPage().goToMyAccount();
    }

    public void closeOnboardingWizardAndGoToUploadDocumentTab() {
        closeOnboardingWizardAndGoTrade();
        goToUploadDocumentTab();
    }

    public void goToUploadDocumentTab() {
        pages().topNavigationPage().goToMyAccount();
        pages().uploadDocumentsTab().invoke();
    }
}
