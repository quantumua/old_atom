package com.betamedia.atom.testslibrary.option24.web.crm;

import com.betamedia.atom.core.api.crm.form.entities.AccountDetails;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WidgetsNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.testingtype.widgets.WidgetsClientTest;
import org.testng.annotations.Test;

/**
 * Created by vsnigur on 5/19/17.
 */
public class AccountDetailsTest extends WidgetsClientTest {

    @Test
    public void accountDetailsAddInformationTest() {
        pages().navigation().register();
        pages().registerPage().register();
        pages().navigation().accountDetails();
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
