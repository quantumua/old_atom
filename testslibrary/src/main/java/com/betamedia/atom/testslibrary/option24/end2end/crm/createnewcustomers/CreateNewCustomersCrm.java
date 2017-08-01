package com.betamedia.atom.testslibrary.option24.end2end.crm.createnewcustomers;

import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.testslibrary.option24.end2end.crm.experienceLevels.CrmUserExperienceTest;
import org.testng.annotations.Test;

/**
 * Created by vsnigur on 7/3/17.
 */
public class CreateNewCustomersCrm extends CrmUserExperienceTest {

    private static int WIDGET_SOURCE_ID = 206440002;

    /**
     * - create new customer using widget web site;
     * - verify customer creation source;
     */
    @Test(description = "CTW-2544: Web widget")
    public void checkTrafficSourceAndMarketingDataValuesInDataBase() {
        pages().navigation().register();
        CRMCustomer customer = crmRegisterAndLogIn();
        operations().onBoardingOperations().assertTrafficSource(customer.getEmail(), WIDGET_SOURCE_ID);
    }
}

