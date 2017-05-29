package com.betamedia.qe.af.testslibrary.option24.end2end.crm;

import com.betamedia.qe.af.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import org.testng.annotations.Test;

/**
 * Created by Nir Shukrun on 5/28/17.
 */
public class MobileCRMOnboardingDemoWizardConditionsTest extends AbstractOnboardingConditionsTest {

    @Test(dataProvider = "GenericDataProvider")
    public void testWizard2(OnboardingWizardConditions conditions) throws Exception {
        pages().topNavigationPage().logIn();
        pages().loginPage().login(conditions.username(), CustomerBuilder.PASSWORD);

        verifyResultingSlidesShown(conditions);
    }
}
