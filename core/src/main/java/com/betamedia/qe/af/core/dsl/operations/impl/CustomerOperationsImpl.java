package com.betamedia.qe.af.core.dsl.operations.impl;

import com.betamedia.qe.af.core.api.tp.adapters.MobileCRMHTTPAdaper;
import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMRegisterResult;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMResponse;
import com.betamedia.qe.af.core.dsl.operations.CustomerOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;

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
     * Registers new CRM customer with given customer builder
     */
    @Override
    public CRMCustomer register(CustomerBuilder customerBuilder) {
        CRMResponse<CRMRegisterResult> register = mobileCRMHTTPAdaper.register(customerBuilder.createCustomerRO());
        Assert.assertNotNull(register.getResult(), "The new customer wasn't created" + register.getErrors());
        return register.getResult().getCustomer();
    }
}
