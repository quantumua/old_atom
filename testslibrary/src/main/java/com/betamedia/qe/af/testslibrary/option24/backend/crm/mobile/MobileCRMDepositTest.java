package com.betamedia.qe.af.testslibrary.option24.backend.crm.mobile;

import com.betamedia.qe.af.core.api.tp.entities.builders.MarketingParametersBuilder;
import com.betamedia.qe.af.core.api.tp.entities.builders.MobileDepositBuilder;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMDeposit;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMError;
import com.betamedia.qe.af.core.testingtype.tp.TPBackEndTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Oleksandr Losiev on 4/21/17.
 */
public class MobileCRMDepositTest extends TPBackEndTest {

    private final String expiryErrorCode = "ExpirationDatePassedEx";
    private final String expiryErrorMessage = "The credit card expired";
    private final String missingAccountErrorCode = "DepositProviderEx";
    private final String missingAccountErrorMessage = "Your deposit attempt could not be completed - please contact our support team";
    private final String systemExceptionErrorCode = "SystemEx";
    private final String unknownErrorMessage = "Unknown error occurred.";

    @Test
    @Parameters("tradingAccountId")
    public void testDeposit(String tradingAccountId) {
        MobileDepositBuilder depositBuilder = new MobileDepositBuilder(tradingAccountId);
        CRMDeposit deposit = operations().customerOperations().deposit(depositBuilder);
        assertNotNull(deposit);
    }

    @Test
    @Parameters("tradingAccountId")
    public void testDepositWithoutAddressFields(String tradingAccountId) {
        MobileDepositBuilder depositBuilder = new MobileDepositBuilder(tradingAccountId);
        depositBuilder.setAddress(null);
        depositBuilder.setCity(null);
        depositBuilder.setCountryCode(null);

        CRMDeposit deposit = operations().customerOperations().deposit(depositBuilder);
        assertNotNull(deposit);
    }

    @Test
    @Parameters("tradingAccountId")
    public void testDepositWithInvalidExpirationYear(String tradingAccountId) {
        MobileDepositBuilder depositBuilder = new MobileDepositBuilder(tradingAccountId);
        depositBuilder.setExpiryYear(2015);

        List<CRMError> depositErrors = operations().customerOperations().depositWithErrors(depositBuilder);
        verifyErrorCodeAndMessage(depositErrors, expiryErrorCode, expiryErrorMessage);
    }

    @Test
    @Parameters("tradingAccountId")
    public void testDepositWithInvalidExpirationMonth(String tradingAccountId) {
        MobileDepositBuilder depositBuilder = new MobileDepositBuilder(tradingAccountId);
        depositBuilder.setExpiryYear(2017);
        depositBuilder.setExpiryMonth(1);

        List<CRMError> depositErrors = operations().customerOperations().depositWithErrors(depositBuilder);
        verifyErrorCodeAndMessage(depositErrors, expiryErrorCode, expiryErrorMessage);
    }

    @Test
    public void testDepositWithMissingAccountName() {
        MobileDepositBuilder depositBuilder = new MobileDepositBuilder(null);
        depositBuilder.setTradingAccountName(null);
        depositBuilder.setTradingAccountId(null);

        List<CRMError> depositErrors = operations().customerOperations().depositByNameWithErrors(depositBuilder);
        verifyErrorCodeAndMessage(depositErrors, missingAccountErrorCode, missingAccountErrorMessage);
    }

    @Test
    @Parameters("tradingAccountId")
    public void testDepositWithParamXAliases(String tradingAccountId) {
        MobileDepositBuilder depositBuilder = new MobileDepositBuilder(tradingAccountId);
        MarketingParametersBuilder marketingParametersBuilder = new MarketingParametersBuilder(true);
        CRMDeposit deposit = operations().customerOperations().deposit(depositBuilder, marketingParametersBuilder);
        assertNotNull(deposit);
    }

    @Test
    @Parameters("tradingAccountId")
    public void testDepositWithPXAliases(String tradingAccountId) {
        MobileDepositBuilder depositBuilder = new MobileDepositBuilder(tradingAccountId);
        MarketingParametersBuilder marketingParametersBuilder = new MarketingParametersBuilder(true);
        CRMDeposit deposit = operations().customerOperations().deposit(depositBuilder, marketingParametersBuilder);
        assertNotNull(deposit);
    }

    @Test
    @Parameters("tradingAccountId")
    public void testDepositWithAddressLengthOverLimit(String tradingAccountId) {
        final String longAddress = "lvAk3XNLV49AAjSURGCDfNHuxgilbNm3thmo6SqKBLF7YC9Nb0DWcsoJJvlBY57VuhbpQvcclSpmhrX" +
                "q0zDXicgEJPSP33jnqAVugmL1RRXDwR0ajxpOP2zO8kLqBLvlMisEMV0DzremhEX8YYKcNX5qqGeYtGAub2tQpimQCGXo2SxJKzw" +
                "WYrFisOJj0K0UtHBMW6k0lUUrlUSluLVUaA67muD301vOoAbo5evVV3itGx7OlS4uwS6mSBigVYMFmrc5hOE31VIjlN0l6BI4Dhj" +
                "huyKbO94pGbX0W4nbzPZL";
        MobileDepositBuilder depositBuilder = new MobileDepositBuilder(tradingAccountId);
        depositBuilder.setAddress(longAddress);
        List<CRMError> depositErrors = operations().customerOperations().depositWithErrors(depositBuilder);
        verifyErrorCodeAndMessage(depositErrors, systemExceptionErrorCode, unknownErrorMessage);
    }

    private void verifyErrorCodeAndMessage(List<CRMError> depositErrors, String expectedErrorCode, String expectedErrorMessage) {
        assertTrue(depositErrors.size() == 1);

        CRMError error = depositErrors.get(0);
        assertEquals(expectedErrorCode, error.getCode());
        assertEquals(expectedErrorMessage, error.getMessage());
    }
}
