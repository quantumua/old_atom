package com.betamedia.atom.testslibrary.option24.end2end.crm;

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

    @Test(dataProvider = GENERIC_PARALLEL_DATA_PROVIDER)
    public void testWizard(OnboardingWizardConditions conditions) throws Exception {
        CRMCustomer customer = operations().customerOperations().registerWithWizardConditions(conditions);
        if (conditions.hasAdditionalDetails()) {
            operations().customerOperations().updateCustomersOnboardingConditions(customer, createConditionsToShowWelcomeAndAdditionalDetailsPages());
            fillAdditionalDetails(customer);
            operations().customerOperations().updateCustomersOnboardingConditions(customer, conditions);
            pages().browser().waitUntilPageLoad();
        }
        if (conditions.hasPendingDeposit()) {
            operations().customerOperations().updateCustomersOnboardingConditions(customer, createConditionsToShowOnlyDepositPage());
            placePendingDeposit(customer);
            operations().customerOperations().updateCustomersOnboardingConditions(customer, conditions);
            pages().browser().waitUntilPageLoad();
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

        double depositLimit = operations().customerOperations().findMaximumDepositLimit(customer.getId());
        pages().creditCardDeposit().submit(
                CreditCardDeposit.builder()
                        .withDepositAmount(((Double) (depositLimit + 3000)).toString())
                        .build());
    }

    private void fillAdditionalDetails(CRMCustomer customer) {
        goToHomepageAndLogin(customer.getUserName());
        pages().welcomePage().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
    }

    private void goToHomepageAndLogin(String username) {
        pages().browser().deleteAllCookies();
        pages().topNavigationPage().goToHomePage();

        pages().topNavigationPage().logIn();
        pages().loginPage().login(username, CustomerRO.CustomerROBuilder.DEFAULT_PASSWORD);
    }
}
