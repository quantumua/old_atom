package com.betamedia.qe.af.testslibrary.option24.end2end.crm;

import com.betamedia.qe.af.core.api.crm.form.builders.AccountAdditionalDetailsBuilder;
import com.betamedia.qe.af.core.testingtype.tp.TPResourceAwareEndToEndTest;
import org.testng.annotations.Test;

/**
 * Created by mbelyaev on 5/17/17.
 */
public class AccountAdditionalDetailsTest extends TPResourceAwareEndToEndTest{

    @Test
    public void accountAdditionalDetailsAddInformationTest(){
        pages().register().register();
        pages().crmNavigation().accountAdditionalDetails();
        pages().accountAdditionalDetails().update(
                (new AccountAdditionalDetailsBuilder()
                .withBirthDateDay("1")
                .withBirthDateMonth("2")
                .withBirthDateYear("1990")
                .withCountryOfBirth("Togo")
                .withNationality("Togo")
                .build()
        ));
    }

}
