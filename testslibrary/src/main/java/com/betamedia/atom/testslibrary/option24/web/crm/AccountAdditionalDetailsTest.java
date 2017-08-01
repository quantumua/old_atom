package com.betamedia.atom.testslibrary.option24.web.crm;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.testingtype.widgets.WidgetsEndToEndTest;
import org.testng.annotations.Test;

/**
 * Created by vsnigur on 5/19/17.
 */
public class AccountAdditionalDetailsTest extends WidgetsEndToEndTest {

    @Test
    public void accountAdditionalDetailsAddInformationTest(){
        pages().registerPage().register();
        pages().navigation().accountAdditionalDetails();
        pages().accountAdditionalDetailsPage().update(
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