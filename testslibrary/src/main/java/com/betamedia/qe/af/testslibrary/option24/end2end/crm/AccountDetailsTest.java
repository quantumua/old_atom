package com.betamedia.qe.af.testslibrary.option24.end2end.crm;

import com.betamedia.qe.af.core.api.crm.form.builders.AccountDetailsBuilder;
import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.testingtype.tp.TPResourceAwareEndToEndTest;
import org.testng.annotations.Test;

/**
 * Created by mbelyaev on 5/17/17.
 */
public class AccountDetailsTest extends TPResourceAwareEndToEndTest {

    @Test
    public void accountDetailsAddInformationTest() {
        pages().register().register();
        pages().crmNavigation().accountDetails();
//        TODO use customerBuilder inside builder
        pages().accountDetails().update(new AccountDetailsBuilder(new CustomerBuilder()
                .setCity("city")
                .setBirthOfDate("1990-1-1")
                .createCustomerRO())
                .setStreetNumber("streetNumber")
                .build()
        );
    }
}
