package com.betamedia.atom.testslibrary.option24.web.crm;

import org.testng.annotations.Test;

import com.betamedia.atom.core.api.crm.form.entities.AccountDetails;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WidgetsNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.testingtype.tp.TPClientTest;

/**
 * Created by vsnigur on 5/19/17.
 */
public class AccountDetailsTest extends TPClientTest {

    @Test
    public void accountDetailsAddInformationTest() {
        pages().crmNavigation().register();
        pages().registerPage().register();
        pages().crmNavigation().accountDetails();
        pages().accountDetails().update(AccountDetails
                .builderFor(CustomerRO.builder(WidgetsNamingStrategy.get())
                        .setCity("city")
                        .setBirthOfDate("1990-01-01")
                        .build())
                .setStreetNumber("streetNumber")
                .build()
        );
    }
}
