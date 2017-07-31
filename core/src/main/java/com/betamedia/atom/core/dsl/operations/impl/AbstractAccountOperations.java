package com.betamedia.atom.core.dsl.operations.impl;

import com.betamedia.atom.core.api.tp.adapters.TPCRMHttpAdapter;
import com.betamedia.atom.core.api.tp.entities.request.AccountRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMAccountCreate;
import com.betamedia.atom.core.api.tp.entities.response.CRMDeposit;
import com.betamedia.atom.core.api.tp.entities.response.TPCRMResponse;
import com.betamedia.atom.core.dsl.operations.AccountOperations;
import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.tp.api.model.Account;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static org.testng.Assert.assertNotNull;

/**
 * This class is designed to facilitate the execution of common operations related to account.
 * It can be used as a "building block" when writing integration tests.
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 * @see Account
 */
public abstract class AbstractAccountOperations<T extends EnvironmentDependent> implements AccountOperations<T> {

    //TODO modify brand display ID retrieval mechanism after GigaSpaces removal
    private static final String STUB_BRAND_DISPLAY_ID = "stubDisplayId";

    private static final Logger logger = LogManager.getLogger(AbstractAccountOperations.class);

    public static final double DEFAULT_ACCOUNT_BALANCE = 1000.0d;

    @Autowired
    private TPCRMHttpAdapter<T> crmHttpAdapter;

    /**
     * A method to register and get a CRM account.
     * An account is created with current brand display id.
     * @return crm account
     */
    @Override
    public Account getCRM() {
        return getCRM(AccountRO.builder().setBrandDisplayId(STUB_BRAND_DISPLAY_ID));
    }

    /**
     * A method to register and get a CRM account using the specified account builder.
     * If the account builder's brand display id is not set, current display id will be used.
     * @return crm account
     */
    @Override
    public Account getCRM(AccountRO.AccountROBuilder accountROBuilder) {
        if (accountROBuilder.getBrandDisplayId() == null) {
            accountROBuilder.setBrandDisplayId(STUB_BRAND_DISPLAY_ID);
        }
        TPCRMResponse<CRMAccountCreate> register = crmHttpAdapter.create(accountROBuilder.build());
        assertNotNull(register.getResult(), "The new account wasn't created" + register.getErrors());
        throw new NotImplementedException("Can not retrieve Account because of GigaSpaces deprecation");
    }

    /**
     * A method to make a CRM deposit for the given account.
     * @param amount deposit amount
     * @return deposit's transaction id
     */
    @Override
    public String depositCRM(String accountId, Double amount) {
        TPCRMResponse<CRMDeposit> deposit = crmHttpAdapter.deposit(accountId, amount, STUB_BRAND_DISPLAY_ID);
        assertNotNull(deposit.getResult(), "The deposit operation wasn't success" + deposit.getErrors());
        return deposit.getResult().getTransactionId();
    }

    @Override
    public Account getCRM(String id) {
        throw new NotImplementedException("Can not retrieve Account because of GigaSpaces deprecation");
    }

}
