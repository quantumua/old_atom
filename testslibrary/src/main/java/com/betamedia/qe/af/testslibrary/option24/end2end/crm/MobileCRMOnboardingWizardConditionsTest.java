package com.betamedia.qe.af.testslibrary.option24.end2end.crm;

import com.betamedia.qe.af.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.qe.af.core.testingtype.tp.TPEndToEndTest;
import org.testng.annotations.Test;

/**
 * Created by Oleksandr Losiev on 5/19/17.
 */
public class MobileCRMOnboardingWizardConditionsTest extends TPEndToEndTest {

    @Test(dataProvider = "GenericDataProvider")
    public void testWizard(OnboardingWizardConditions wizardConditions) {
        CRMCustomer customer = operations().customerOperations().registerWithWizardConditions(wizardConditions);
        pages().topNavigationPage().logIn();
        pages().loginPage().login(customer.getUserName(), "123123");

        pages().welcomePage().start();
        pages().riskWarningPage().accept();
    }

    @Override
    protected Class getDataSourceEntity() {
        return OnboardingWizardConditions.class;
    }

    @Override
    protected String getDataSourcePath() {
        return "/data/wizardConditionTestCases.csv";
    }
}
