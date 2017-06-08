package com.betamedia.atom.testslibrary.option24.end2end.crm;

import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.testingtype.tp.TPCachedResourceEndToEndTest;
import com.betamedia.atom.core.utils.StringUtils;
import org.testng.annotations.Test;

/**
 * Created by vsnigur on 5/19/17.
 */
public class CreditCardDepositTest extends TPCachedResourceEndToEndTest {
    private static final String USERNAME = StringUtils.generateRandomId(10).replaceAll("[^A-Za-z]", "");
    private final static String PASSWORD = CustomerRO.CustomerROBuilder.DEFAULT_PASSWORD;

    @Test
    public void creditCardDepositSubmitInformationTest() {

        pages().registerPage().register();
        pages().crmNavigation().creditCardDeposit();
        pages().creditCardDeposit().submit(
//                TODO Add to builder autogeneration of unique fields, set default values for required fields in builder
                (CreditCardDeposit.builder()
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
