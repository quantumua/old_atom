package com.betamedia.qe.af.testslibrary.option24.end2end.crm;

import com.betamedia.qe.af.core.api.crm.form.builders.CreditCardDepositBuilder;
import com.betamedia.qe.af.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
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
            operations().customerOperations().updateOnboardingConditionsInDatabase(customer.getId(), conditions);
        }
        else {
            customer = operations().customerOperations().registerWithWizardConditions(conditions);
        }

        pages().topNavigationPage().logIn();
        pages().loginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);

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
        pages().loginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);

        CreditCardDepositBuilder builder = new CreditCardDepositBuilder();
        double depositLimit = operations().customerOperations().findMaximumDepositLimit(customer.getId());
        builder.withDepositAmount(((Double)(depositLimit + 3000)).toString());
        pages().creditCardDeposit().submit(builder.build());

        pages().browser().deleteAllCookies();
        pages().topNavigationPage().goToHomePage();
    }
}
