package com.betamedia.qe.af.testslibrary.option24.end2end.crm;

import com.betamedia.qe.af.core.api.crm.form.builders.CreditCardDepositInfoBuilder;
import com.betamedia.qe.af.core.api.crm.form.builders.RegisterInfoBuilder;
import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.testingtype.tp.TPResourceAwareEndToEndTest;
import com.betamedia.qe.af.core.utils.StringUtils;
import org.testng.annotations.Test;

/**
 * Created by mbelyaev on 5/17/17.
 */
public class CreditCardDepositTest extends TPResourceAwareEndToEndTest{
    private static final String USERNAME = StringUtils.generateRandomId(10).replaceAll("[^A-Za-z]","");
    private final static String PASSWORD = CustomerBuilder.PASSWORD;

    @Test
    public void creditCardDepositSubmitInformationTest(){

        makeAUser();
        pages().crmNavigation().creditCardDeposit();
        pages().creditCardDeposit().submit(
                (new CreditCardDepositInfoBuilder()
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

    private void makeAUser() {
        pages().crmNavigation().register();
        pages().register().update(
                (new RegisterInfoBuilder()
                        .withTitle("Mr")
                        .withFirstName(USERNAME)
                        .withLastName(USERNAME+"lastName")
                        .withEmail(USERNAME+"@users.com")
                        .withCountry("Togo")
                        .withTelephonePrefix("")
                        .withTelephoneNumber("911911911911")
                        .withBirthDateDay("1")
                        .withBirthDateMonth("1")
                        .withBirthDateYear("1990")
                        .withAccountBaseCurrency("EUR")
                        .withPassword(PASSWORD)
                        .build())
        );
    }
}
