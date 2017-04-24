package com.betamedia.qe.af.core.dsl.operations.impl;

import com.betamedia.qe.af.core.api.tp.adapters.MobileCRMHTTPAdaper;
import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.builders.MobileDepositBuilder;
import com.betamedia.qe.af.core.api.tp.entities.response.*;
import com.betamedia.qe.af.core.dsl.operations.CustomerOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.testng.Assert.*;

/**
 * This class is designed to facilitate the execution of common operations related to customer operations.
 * It can be used as a "building block" when writing integration tests.
 * @author Maksym Tsybulskyy
 *         Date: 3/31/17.
 * @see CRMCustomer
 */
@Component
public class CustomerOperationsImpl implements CustomerOperations {

    private static final Logger logger = LogManager.getLogger(CustomerOperationsImpl.class);

    @Autowired
    private MobileCRMHTTPAdaper mobileCRMHTTPAdaper;

    /**
     * Registers new CRM customer with default customer builder
     */
    @Override
    public CRMCustomer register() {
        return register(new CustomerBuilder());
    }

    /**
     * Registers new CRM customer with given customer builder.
     */
    @Override
    public CRMCustomer register(CustomerBuilder customerBuilder) {
        CRMResponse<CRMRegisterResult> register = mobileCRMHTTPAdaper.register(customerBuilder.createCustomerRO());
        CRMRegisterResult registrationResult = register.getResult();
        List<CRMError> registrationErrors = register.getErrors();

        assertNotNull(registrationResult, "The new customer wasn't created" + registrationErrors);
        assertTrue(registrationErrors.isEmpty(), "There were errors during customer registration:" + registrationErrors);
        return registrationResult.getCustomer();
    }

    /**
     * Logs in an existing user with given username and password.
     */
    @Override
    public CRMCustomer login(String username, String password) {
        CRMResponse<CRMRegisterResult> loginResponse = mobileCRMHTTPAdaper.login(username, password);
        CRMRegisterResult loggedInCustomer = loginResponse.getResult();
        List<CRMError> loginErrors = loginResponse.getErrors();

        assertNotNull(loggedInCustomer, "The new customer wasn't created" + loginErrors);
        assertTrue(loginErrors.isEmpty(), "There were errors during customer registration:" + loginErrors);
        return loggedInCustomer.getCustomer();
    }

    /**
     * Logouts an existing user with given customer id.
     */
    @Override
    public void logout(String customerId) {
        CRMResponse logoutResponse = mobileCRMHTTPAdaper.logout(customerId);
        List<CRMError> logoutErrors = logoutResponse == null? null : logoutResponse.getErrors();
        assertNull(logoutResponse, "There were errors during customer registration:" + logoutErrors);
    }

    /**
     * Performs a deposit with given deposit builder.
     */
    @Override
    public CRMDeposit deposit(MobileDepositBuilder depositBuilder) {
        CRMResponse<MobileCRMDeposit> depositResponse = mobileCRMHTTPAdaper.deposit(depositBuilder.createMobileDepositRO());
        CRMDeposit depositResult = depositResponse.getResult();
        List<CRMError> depositErrors = depositResponse.getErrors();

        assertNotNull(depositResult, "Deposit wasn't made" + depositErrors);
        assertTrue(depositErrors.isEmpty(), "There were errors when performing the deposit:" + depositErrors);
        return depositResult;
    }

    @Override
    public List<CRMError>  depositWithErrors(MobileDepositBuilder depositBuilder) {
        CRMResponse<MobileCRMDeposit> depositResponse = mobileCRMHTTPAdaper.deposit(depositBuilder.createMobileDepositRO());
        List<CRMError> depositErrors = depositResponse.getErrors();
        assertFalse(depositErrors.isEmpty(), "Deposit errors were expected, but there were none.");
        return depositErrors;
    }
}
