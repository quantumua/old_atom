package com.betamedia.atom.testslibrary.option24.end2end.crm;

import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import org.testng.annotations.Test;

/**
 * Created by Nir Shukrun on 5/28/17.
 */
public class MobileCRMOnboardingDemoWizardConditionsTest extends AbstractOnboardingConditionsTest {

    @Test(dataProvider = GENERIC_PARALLEL_DATA_PROVIDER)
    public void testWizard2(OnboardingWizardConditions conditions) throws Exception {
        //required to trigger notification popup
        pages().browser().deleteAllCookies();

        pages().topNavigationPage().logIn();
        pages().loginPage().login(conditions.username(), CustomerRO.CustomerROBuilder.DEFAULT_PASSWORD);


        if (checkIfLeveragePopupWillBeShown(conditions.username())) {
            pages().leveragePopup().setLeverage();
        }

        pages().disclaimerNotification().tryAccept();
        verifyResultingSlidesShown(conditions);
    }
}
