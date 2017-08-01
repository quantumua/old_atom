package com.betamedia.atom.testslibrary.option24.backend.crm.mobile;

import com.betamedia.atom.core.api.tp.entities.request.MobileDepositRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMDeposit;
import com.betamedia.atom.core.api.tp.entities.response.CRMError;
import com.betamedia.atom.core.testingtype.widgets.WidgetsBackEndTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by Oleksandr Losiev on 4/21/17.
 */
public class MobileCRMDepositTest extends WidgetsBackEndTest {

    private final String expiryErrorCode = "ExpirationDatePassedEx";
    private final String expiryErrorMessage = "The credit card expired";
    private final String missingAccountErrorCode = "DepositProviderEx";
    private final String missingAccountErrorMessage = "Your deposit attempt could not be completed - please contact our support team";
    private final String systemExceptionErrorCode = "SystemEx";
    private final String unknownErrorMessage = "Unknown error occurred.";

    @Test
    @Parameters("tradingAccountId")
    public void testDeposit(String tradingAccountId) {
        CRMDeposit deposit = operations().customerOperations().deposit(MobileDepositRO.builder(tradingAccountId).build());
        assertNotNull(deposit);
    }

    @Test
    @Parameters("tradingAccountId")
    public void testDepositWithoutAddressFields(String tradingAccountId) {
        CRMDeposit deposit = operations().customerOperations().deposit(
                MobileDepositRO.builder(tradingAccountId)
                        .setAddress(null)
                        .setCity(null)
                        .setCountryCode(null)
                        .build()
        );
        assertNotNull(deposit);
    }

    @Test
    @Parameters("tradingAccountId")
    public void testDepositWithInvalidExpirationYear(String tradingAccountId) {
        List<CRMError> depositErrors = operations().customerOperations().depositWithErrors(
                MobileDepositRO.builder(tradingAccountId)
                        .setExpiryYear(2015)
                        .build());
        verifyErrorCodeAndMessage(depositErrors, expiryErrorCode, expiryErrorMessage);
    }

    @Test
    @Parameters("tradingAccountId")
    public void testDepositWithInvalidExpirationMonth(String tradingAccountId) {
        List<CRMError> depositErrors = operations().customerOperations().depositWithErrors(
                MobileDepositRO.builder(tradingAccountId)
                        .setExpiryYear(2017)
                        .setExpiryMonth(1)
                        .build()
        );
        verifyErrorCodeAndMessage(depositErrors, expiryErrorCode, expiryErrorMessage);
    }

    @Test
    public void testDepositWithMissingAccountName() {
        List<CRMError> depositErrors = operations().customerOperations().depositByNameWithErrors(
                MobileDepositRO.builder(null)
                        .setTradingAccountName(null)
                        .setTradingAccountId(null)
                        .build()
        );
        verifyErrorCodeAndMessage(depositErrors, missingAccountErrorCode, missingAccountErrorMessage);
    }

    @Test
    @Parameters("tradingAccountId")
    public void testDepositWithAddressLengthOverLimit(String tradingAccountId) {
        final String longAddress = "lvAk3XNLV49AAjSURGCDfNHuxgilbNm3thmo6SqKBLF7YC9Nb0DWcsoJJvlBY57VuhbpQvcclSpmhrX" +
                "q0zDXicgEJPSP33jnqAVugmL1RRXDwR0ajxpOP2zO8kLqBLvlMisEMV0DzremhEX8YYKcNX5qqGeYtGAub2tQpimQCGXo2SxJKzw" +
                "WYrFisOJj0K0UtHBMW6k0lUUrlUSluLVUaA67muD301vOoAbo5evVV3itGx7OlS4uwS6mSBigVYMFmrc5hOE31VIjlN0l6BI4Dhj" +
                "huyKbO94pGbX0W4nbzPZL";
        List<CRMError> depositErrors = operations().customerOperations().depositWithErrors(
                MobileDepositRO.builder(tradingAccountId)
                        .setAddress(longAddress)
                        .build()
        );
        verifyErrorCodeAndMessage(depositErrors, systemExceptionErrorCode, unknownErrorMessage);
    }

    private void verifyErrorCodeAndMessage(List<CRMError> depositErrors, String expectedErrorCode, String expectedErrorMessage) {
        assertTrue(depositErrors.size() == 1);

        CRMError error = depositErrors.get(0);
        assertEquals(expectedErrorCode, error.getCode());
        assertEquals(expectedErrorMessage, error.getMessage());
    }
}
