package com.betamedia.qe.af.testslibrary.option24.backend.crm.mobile;

import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMAccount;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.qe.af.core.testingtype.tp.TPBackEndTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Created by Oleksandr Losiev on 4/21/17.
 */
public class MobileCRMRegistrationTest extends TPBackEndTest {

    @DataProvider(name = "registrationCurrencies")
    public static Object[][] currencies() {
        return new Object[][] {
                {"CHF", "CHF"},
                {"JPY", "JPY"},
                {"BRL", "EUR"},
                {"USD", "EUR"},
                {"VER", "EUR"},
                {"NULL", "EUR"},
                {"", "EUR"}
        };
    }

    @Test
    public void testDefaultRegistration() {
        CRMCustomer registeredCustomer = operations().customerOperations().register();
        assertNotNull(registeredCustomer);
    }

    @Test(dataProvider = "registrationCurrencies")
    public void testRegistrationCurrencies(String registrationCurrency, String expectedCurrency) {
        CustomerBuilder customerBuilder = new CustomerBuilder();
        customerBuilder.setCurrency(registrationCurrency);

        CRMCustomer registeredCustomer = operations().customerOperations().register(customerBuilder);

        CRMAccount[] accounts = registeredCustomer.getAccounts();
        for (CRMAccount account : accounts) {
            assertEquals(expectedCurrency, account.getCurrency());
        }
    }

    @Test
    public void testRegistrationWithTargetForex() {
        final String targetForex = "forex";

        CustomerBuilder customerBuilder = new CustomerBuilder();
        customerBuilder.setTarget(targetForex);

        CRMCustomer registeredCustomer = operations().customerOperations().register(customerBuilder);
        assertNotNull(registeredCustomer);
    }

    @Test(enabled = false)
    public void testRegistrationArabicSymbolsNotEncoded() {
        final String arabicName = "%C3%99%C2%81%C3%99%C2%87%C3%98%C2%AF%C3%98%C2%A7%C3%99%C2%84%C3%98%C2%A8%C3%98%C2%B1%C3%98%C2%A7%C3%99%C2%83";
        CustomerBuilder customerBuilder = new CustomerBuilder();
        customerBuilder.setFirstName(arabicName);
        customerBuilder.setLastName(arabicName);

        CRMCustomer registeredCustomer = operations().customerOperations().register(customerBuilder);
        assertNotNull(registeredCustomer);
    }
}
