package com.betamedia.atom.testslibrary.option24.end2end.crm;

import com.betamedia.atom.core.api.crm.form.entities.AccountDetails;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.testingtype.tp.TPCachedResourceEndToEndTest;
import org.testng.annotations.Test;

/**
 * Created by vsnigur on 5/19/17.
 */
public class AccountDetailsTest extends TPCachedResourceEndToEndTest {

    @Test
    public void accountDetailsAddInformationTest() {
        pages().register().register();
        pages().crmNavigation().accountDetails();
//        TODO use customerBuilder inside builder
        pages().accountDetails().update(AccountDetails
                .builderFor(CustomerRO.builder()
                        .setCity("city")
                        .setBirthOfDate("1990-1-1")
                        .build())
                .setStreetNumber("streetNumber")
                .build()
        );
    }
}
