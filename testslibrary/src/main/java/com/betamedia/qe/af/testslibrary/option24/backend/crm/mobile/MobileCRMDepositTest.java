package com.betamedia.qe.af.testslibrary.option24.backend.crm.mobile;

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

    private void verifyErrorCodeAndMessage(List<CRMError> depositErrors, String expectedErrorCode, String expectedErrorMessage) {
        assertTrue(depositErrors.size() == 1);

        CRMError error = depositErrors.get(0);
        assertEquals(expectedErrorCode, error.getCode());
        assertEquals(expectedErrorMessage, error.getMessage());
    }
}
