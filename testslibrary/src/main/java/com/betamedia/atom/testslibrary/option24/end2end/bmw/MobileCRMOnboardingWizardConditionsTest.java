package com.betamedia.atom.testslibrary.option24.end2end.bmw;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import org.testng.annotations.Test;

/**
 * Created by Oleksandr Losiev on 5/19/17.
 */
public class MobileCRMOnboardingWizardConditionsTest extends AbstractOnboardingConditionsTest {

    @Test(dataProvider = GENERIC_DATA_PROVIDER)
    public void testWizard(OnboardingWizardConditions conditions) throws Exception {
        CRMCustomer customer = operations().customerOperations().registerWithWizardConditions(conditions);
        if (conditions.hasAdditionalDetails()) {
            operations().customerOperations().updateCustomersOnboardingConditions(customer, createConditionsToShowWelcomeAndAdditionalDetailsPages());
            fillAdditionalDetails(customer);
            operations().customerOperations().updateCustomersOnboardingConditions(customer, conditions);
        } else {
            operations().customerOperations().updateCustomersOnboardingConditions(customer, createConditionsToShowWelcomeAndAdditionalDetailsPages());
            operations().customerOperations().updateCustomersOnboardingConditions(customer, conditions);
        }
        if (conditions.hasPendingDeposit()) {
            operations().customerOperations().updateCustomersOnboardingConditions(customer, createConditionsToShowOnlyDepositPage());
            placePendingDeposit(customer);
            operations().customerOperations().updateCustomersOnboardingConditions(customer, conditions);
        }

        goToHomepageAndLogin(customer.getUserName());
        verifyResultingSlidesShown(conditions);
    }

    private OnboardingWizardConditions createConditionsToShowOnlyDepositPage() {
        return new OnboardingWizardConditions(true, true, true, true,
                OnboardingWizardConditions.ExperienceLevel.HIGH_EXPERIENCE, false,
                OnboardingWizardConditions.AccountType.REAL,
                OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED,
                OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED, true,
                true, OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED);
    }

    private OnboardingWizardConditions createConditionsToShowWelcomeAndAdditionalDetailsPages() {
        return new OnboardingWizardConditions(true, true, false, true,
                OnboardingWizardConditions.ExperienceLevel.HIGH_EXPERIENCE, false,
                OnboardingWizardConditions.AccountType.REAL,
                OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED,
                OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED, false,
                true, OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED);
    }

    private void placePendingDeposit(CRMCustomer customer) {
        goToHomepageAndLogin(customer.getUserName());
        if (pages().welcomeBackMessage().exists()) {
            pages().welcomeBackMessage().continueQuestionnaire();
        }
        if (pages().accountAdditionalDetails().exists()) {
            pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        }
        double depositLimit = operations().customerOperations().findMaximumDepositLimit(customer.getId());
        pages().creditCardDeposit().submit(
                CreditCardDeposit.builder()
                        .withDepositAmount(((Double) (depositLimit + 3000)).toString())
                        .build());
    }

    private void fillAdditionalDetails(CRMCustomer customer) {
        goToHomepageAndLogin(customer.getUserName());
        pages().welcomeBackMessage().continueQuestionnaire();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
    }

    private void goToHomepageAndLogin(String username) {
        pages().browser().deleteAllCookies();
        pages().browser().refreshPage();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pages().topNavigationPage().logIn();
        if (!pages().loginDialog().isSubmitBtnExists()) {
            pages().topNavigationPage().logIn();
        }
        pages().loginDialog().login(username, CustomerRO.CustomerROBuilder.DEFAULT_PASSWORD);
        pages().startTradeDialog().exists();
    }
}
