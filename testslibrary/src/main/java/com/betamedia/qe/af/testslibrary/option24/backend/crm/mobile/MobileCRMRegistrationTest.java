package com.betamedia.qe.af.testslibrary.option24.backend.crm.mobile;

import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.builders.MarketingParametersBuilder;
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

    @Test
    public void testRegistrationWithParamXAliases() {
        CustomerBuilder customerBuilder = new CustomerBuilder();
        MarketingParametersBuilder marketingParametersBuilder = new MarketingParametersBuilder(true);
        CRMCustomer registeredCustomer = operations().customerOperations().register(customerBuilder, marketingParametersBuilder);
        assertNotNull(registeredCustomer);
    }

    @Test
    public void testRegistrationWithPXAliases() {
        CustomerBuilder customerBuilder = new CustomerBuilder();
        MarketingParametersBuilder marketingParametersBuilder = new MarketingParametersBuilder(false);
        CRMCustomer registeredCustomer = operations().customerOperations().register(customerBuilder, marketingParametersBuilder);
        assertNotNull(registeredCustomer);
    }

    @Test
    public void testRegistrationWithAfSiteId() {
        CustomerBuilder customerBuilder = new CustomerBuilder();
        MarketingParametersBuilder marketingParametersBuilder = new MarketingParametersBuilder(true);
        marketingParametersBuilder.setAf_siteid("https://www.24option.com/");
        marketingParametersBuilder.setSiteId(null);
        CRMCustomer registeredCustomer = operations().customerOperations().register(customerBuilder, marketingParametersBuilder);
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

    @Test
    public void testRegistrationSymbolsNotEncoded() {
        final String decodedName = "Ù\u0081Ù\u0087Ø¯Ø§Ù\u0084Ø¨Ø±Ø§Ù\u0083";
        CustomerBuilder customerBuilder = new CustomerBuilder();
        customerBuilder.setFirstName(decodedName);
        customerBuilder.setLastName(decodedName);

        CRMCustomer registeredCustomer = operations().customerOperations().register(customerBuilder);
        assertNotNull(registeredCustomer);
        assertEquals(decodedName, registeredCustomer.getFirstName());
        assertEquals(decodedName, registeredCustomer.getLastName());
    }
}