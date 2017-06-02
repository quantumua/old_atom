package com.betamedia.qe.af.testslibrary.option24.end2end.crm;

import com.betamedia.qe.af.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.qe.af.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.qe.af.core.api.tp.entities.request.CustomerRO;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import org.testng.annotations.Test;

/**
 * Created by Oleksandr Losiev on 5/19/17.
 */
public class MobileCRMOnboardingWizardConditionsTest extends AbstractOnboardingConditionsTest {

    @Test(dataProvider = "GenericDataProvider")
    public void testWizard(OnboardingWizardConditions conditions) throws Exception {
        CRMCustomer customer;

        if (conditions.hasPendingDeposit()) {
            customer = operations().customerOperations().registerWithWizardConditions(createConditionsToShowOnlyDepositPage());
            placePendingDeposit(customer);
            operations().customerOperations().updateOnboardingConditionsInDB(customer.getId(), conditions);
        }
        else {
            customer = operations().customerOperations().registerWithWizardConditions(conditions);
        }

        pages().topNavigationPage().logIn();
        pages().loginPage().login(customer.getUserName(), CustomerRO.CustomerROBuilder.PASSWORD);

        verifyResultingSlidesShown(conditions);
    }

    private OnboardingWizardConditions createConditionsToShowOnlyDepositPage() {
        return new OnboardingWizardConditions(true, true, true, true,
                OnboardingWizardConditions.ExperienceLevel.HIGH_EXPERIENCE, false,
                OnboardingWizardConditions.AccountType.REAL,
                OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED,
                OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED, true,
                false, OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED);
    }

    private void placePendingDeposit(CRMCustomer customer) {
        pages().topNavigationPage().logIn();
        pages().loginPage().login(customer.getUserName(), CustomerRO.CustomerROBuilder.PASSWORD);

        double depositLimit = operations().customerOperations().findMaximumDepositLimit(customer.getId());
        pages().creditCardDeposit().submit(
                CreditCardDeposit.builder()
                        .withDepositAmount(((Double) (depositLimit + 3000)).toString())
                        .build());

        pages().browser().deleteAllCookies();
        pages().topNavigationPage().goToHomePage();
    }
}
