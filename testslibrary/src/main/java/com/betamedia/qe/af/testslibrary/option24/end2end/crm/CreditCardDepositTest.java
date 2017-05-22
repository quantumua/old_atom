package com.betamedia.qe.af.testslibrary.option24.end2end.crm;

import com.betamedia.qe.af.core.api.crm.form.builders.CreditCardDepositBuilder;
import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.testingtype.tp.TPResourceAwareEndToEndTest;
import com.betamedia.qe.af.core.utils.StringUtils;
import org.testng.annotations.Test;

/**
 * Created by mbelyaev on 5/17/17.
 */
public class CreditCardDepositTest extends TPResourceAwareEndToEndTest {
    private static final String USERNAME = StringUtils.generateRandomId(10).replaceAll("[^A-Za-z]", "");
    private final static String PASSWORD = CustomerBuilder.PASSWORD;

    @Test
    public void creditCardDepositSubmitInformationTest() {

        pages().register().register();
        pages().crmNavigation().creditCardDeposit();
        pages().creditCardDeposit().submit(
//                TODO Add to builder autogeneration of unique fields, set default values for required fields in builder
                (new CreditCardDepositBuilder()
                        .withDepositAmount("100")
                        .withCreditCardNumber("1234-1234-1234-1234")
                        .withCVV2("123")
                        .withExpiryDateMonth("1")
                        .withExpiryDateYear("2020")
                        .withCardHoldersFirstName("CardHoldersFirstName")
                        .withCardHoldersLastName("CardHoldersLastName")
                        .withBillingAddress("BillingAddress")
                        .withCity("City")
                        .withZipCode("ZipCode")
                        .build())
        );
    }
}
