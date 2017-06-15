package com.betamedia.atom.testslibrary.option24.web.crm;

import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.testingtype.tp.TPClientTest;
import org.testng.annotations.Test;

/**
 * Created by vsnigur on 5/19/17.
 */
public class CreditCardDepositTest extends TPClientTest {

    @Test
    public void creditCardDepositSubmitInformationTest() {
        pages().registerPage().register();
        pages().crmNavigation().creditCardDeposit();
        pages().creditCardDeposit().submit(
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
