package com.betamedia.atom.testslibrary.option24.backend.crm.mobile;

import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.CRMMobileAPINamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMAccount;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.core.api.tp.entities.response.CRMError;
import com.betamedia.atom.core.persistence.entities.TrackingInfo;
import com.betamedia.atom.core.persistence.entities.TrackingInfoExtension;
import com.betamedia.atom.core.testingtype.web.WebBackEndTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by Oleksandr Losiev on 4/21/17.
 */
public class MobileCRMRegistrationTest extends WebBackEndTest {

    @DataProvider(name = "registrationCurrencies")
    public static Object[][] currencies() {
        return new Object[][]{
                {"CHF", "CHF"},
                {"JPY", "JPY"},
                {"USD", "EUR"},
                {"", "EUR"}
        };
    }

    @DataProvider(name = "notSupportedCurrencies")
    public static Object[][] notSupportedCurrencies() {
        return new Object[][]{
                {"BRL", "EUR"},
                {"VER", "EUR"},
                {"NULL", "EUR"},
        };
    }

    @Test
    public void testDefaultRegistration() {
        CRMCustomer registeredCustomer = operations().customerOperations().register();
        assertNotNull(registeredCustomer);
    }

    @Test(dataProvider = "registrationCurrencies")
    public void testRegistrationCurrencies(String registrationCurrency, String expectedCurrency) {

        CRMCustomer registeredCustomer = operations().customerOperations().register(
                CustomerRO.builder(CRMMobileAPINamingStrategy.get())
                        .setCurrency(registrationCurrency)
                        .build());

        CRMAccount[] accounts = registeredCustomer.getAccounts();
        for (CRMAccount account : accounts) {
            assertEquals(expectedCurrency, account.getCurrency());
        }
    }

    @Test(dataProvider = "notSupportedCurrencies")
    public void testNotSupportedCurrencies(String registrationCurrency, String expectedCurrency) {
        final String expectedErrorCode = "CurrencyNotSupported";
        final String expectedErrorMessage = "Currency is not supported";

        List<CRMError> registrationErrors = operations().customerOperations().registerWithErrors(
                CustomerRO.builder(CRMMobileAPINamingStrategy.get())
                        .setCurrency(registrationCurrency)
                        .build()
        );

        assertTrue(registrationErrors.size() == 1);
        CRMError error = registrationErrors.get(0);
        assertEquals(expectedErrorCode, error.getCode());
        assertEquals(expectedErrorMessage, error.getMessage());
    }

    @Test
    public void testRegistrationWithTargetForex() {
        final String targetForex = "forex";

        CRMCustomer registeredCustomer = operations().customerOperations().register(
                CustomerRO.builder(CRMMobileAPINamingStrategy.get())
                        .setTarget(targetForex)
                        .build()
        );
        assertNotNull(registeredCustomer);
    }

    @Test
    public void testRegistrationSymbolsNotEncoded() {
        final String decodedName = "Ù\u0081Ù\u0087Ø¯Ø§Ù\u0084Ø¨Ø±Ø§Ù\u0083";

        CRMCustomer registeredCustomer = operations().customerOperations().register(
                CustomerRO.builder(CRMMobileAPINamingStrategy.get())
                        .setFirstName(decodedName)
                        .setLastName(decodedName)
                        .build()
        );
        assertNotNull(registeredCustomer);
        assertEquals(decodedName, registeredCustomer.getFirstName());
        assertEquals(decodedName, registeredCustomer.getLastName());
    }

    @Test
    public void testRegistrationTimeEqualsToCookieCreationTime() {
        CRMCustomer registeredCustomer = operations().customerOperations().register();
        assertNotNull(registeredCustomer);

        TrackingInfoExtension infoExtension = operations().customerOperations().getCustomerTrackingInfoExtensionByCustomerId(registeredCustomer.getId());
        assertNotNull(infoExtension);
        TrackingInfo trackingInfo = operations().customerOperations().getCustomerTrackingInfo(infoExtension.getId());
        assertNotNull(trackingInfo);

        assertEquals(trackingInfo.getCreatedOn(), infoExtension.getCookieCreationTime());
    }
}
