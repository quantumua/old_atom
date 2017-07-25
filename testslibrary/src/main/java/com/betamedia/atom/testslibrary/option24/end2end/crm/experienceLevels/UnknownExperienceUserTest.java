package com.betamedia.atom.testslibrary.option24.end2end.crm.experienceLevels;

import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import org.testng.annotations.Test;

/**
 * Created by vsnigur on 5/25/17.
 */
public class UnknownExperienceUserTest extends AbstractUserExperienceTest {

    /**
     * - Register default user via mobile API and login in the crm widgets
     * - Assert that user created has no trade access type
     */
    @Test(description = "ID:9143")
    public void checkCustomerWithNoScoreIsUnknownExperienceTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        operations().onBoardingOperations().assertUsernameLoginType(
                customer.getUserName(),
                OnboardingWizardConditions.AccessType.NOTRADE
        );
    }

}
