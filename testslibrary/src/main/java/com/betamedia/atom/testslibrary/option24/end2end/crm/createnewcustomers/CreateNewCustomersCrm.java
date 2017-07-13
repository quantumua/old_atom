package com.betamedia.atom.testslibrary.option24.end2end.crm.createnewcustomers;

import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.CRMMobileAPINamingStrategy;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WidgetsNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.testslibrary.option24.end2end.bmw.AbstractOnboardingUserExperienceTest;
import com.betamedia.atom.testslibrary.option24.end2end.crm.experienceLevels.AbstractUserExperienceTest;
import com.betamedia.atom.testslibrary.option24.end2end.crm.experienceLevels.CrmUserExperienceTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

/**
 * Created by vsnigur on 7/3/17.
 */
public class CreateNewCustomersCrm extends CrmUserExperienceTest {

    private static int WIDGET_SOURCE_ID = 206440002;

    @Test(description = "CTW-2544: Web widget")
    public void checkTrafficSourceAndMarketingDataValuesInDataBase() {
        pages().crmNavigation().register();
        CRMCustomer customer = crmRegisterAndLogIn();
        operations().onBoardingOperations().assertTrafficSource(customer.getEmail(), WIDGET_SOURCE_ID);
    }
}

