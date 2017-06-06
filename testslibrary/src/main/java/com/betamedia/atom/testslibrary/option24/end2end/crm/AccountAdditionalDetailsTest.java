package com.betamedia.atom.testslibrary.option24.end2end.crm;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.testingtype.tp.TPCachedResourceEndToEndTest;
import org.testng.annotations.Test;

/**
 * Created by vsnigur on 5/19/17.
 */
public class AccountAdditionalDetailsTest extends TPCachedResourceEndToEndTest {

    @Test
    public void accountAdditionalDetailsAddInformationTest(){
        pages().register().register();
        pages().crmNavigation().accountAdditionalDetails();
        pages().accountAdditionalDetails().update(
                (AccountAdditionalDetails.builder()
                .withBirthDateDay("1")
                .withBirthDateMonth("2")
                .withBirthDateYear("1990")
                .withCountryOfBirth("Togo")
                .withNationality("Togo")
                .build()
        ));
    }

}
