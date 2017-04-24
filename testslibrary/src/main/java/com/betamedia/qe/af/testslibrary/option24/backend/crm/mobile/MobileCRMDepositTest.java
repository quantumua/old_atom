package com.betamedia.qe.af.testslibrary.option24.backend.crm.mobile;

import com.betamedia.qe.af.core.api.tp.entities.builders.MobileDepositBuilder;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMDeposit;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMError;
import com.betamedia.qe.af.core.testingtype.tp.TPBackEndTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Oleksandr Losiev on 4/21/17.
 */
public class MobileCRMDepositTest extends TPBackEndTest {

    private final String expiryErrorCode = "ExpirationDatePassedEx";
    private final String expiryErrorMessage = "The credit card expired";
    private final String missingAccountErrorCode = "SystemEx";
    private final String missingAccountErrorMessage = "Your deposit attempt could not been completed - please contact our support team";

    @Test
    public void testDeposit() {
        MobileDepositBuilder depositBuilder = new MobileDepositBuilder();
        CRMDeposit deposit = operations().customerOperations().deposit(depositBuilder);
        assertNotNull(deposit);
    }

    @Test
    public void testDepositWithoutAddressFields() {
        MobileDepositBuilder depositBuilder = new MobileDepositBuilder();
        depositBuilder.setAddress(null);
        depositBuilder.setCity(null);
        depositBuilder.setCountryCode(null);

        CRMDeposit deposit = operations().customerOperations().deposit(depositBuilder);
        assertNotNull(deposit);
    }

    @Test
    public void testDepositWithInvalidExpirationYear() {
        MobileDepositBuilder depositBuilder = new MobileDepositBuilder();
        depositBuilder.setExpiryYear(2015);

        List<CRMError> depositErrors = operations().customerOperations().depositWithErrors(depositBuilder);
        verifyErrorCodeAndMessage(depositErrors, expiryErrorCode, expiryErrorMessage);
    }

    @Test
    public void testDepositWithInvalidExpirationMonth() {
        MobileDepositBuilder depositBuilder = new MobileDepositBuilder();
        depositBuilder.setExpiryYear(2017);
        depositBuilder.setExpiryMonth(1);

        List<CRMError> depositErrors = operations().customerOperations().depositWithErrors(depositBuilder);
        verifyErrorCodeAndMessage(depositErrors, expiryErrorCode, expiryErrorMessage);
    }

    @Test(enabled = false)
    public void testDepositWithMissingAccountId() {
        MobileDepositBuilder depositBuilder = new MobileDepositBuilder();
        depositBuilder.setTradingAccountId(null);

        List<CRMError> depositErrors = operations().customerOperations().depositWithErrors(depositBuilder);
        verifyErrorCodeAndMessage(depositErrors, missingAccountErrorCode, missingAccountErrorMessage);
    }

    private void verifyErrorCodeAndMessage(List<CRMError> depositErrors, String expectedErrorCode, String expectedErrorMessage) {
        assertTrue(depositErrors.size() == 1);

        CRMError error = depositErrors.get(0);
        assertEquals(expectedErrorCode, error.getCode());
        assertEquals(expectedErrorMessage, error.getMessage());
    }
}
