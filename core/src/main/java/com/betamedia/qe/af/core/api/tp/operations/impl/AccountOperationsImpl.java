package com.betamedia.qe.af.core.api.tp.operations.impl;

import com.betamedia.common.enums.Country;
import com.betamedia.common.enums.Currency;
import com.betamedia.common.utils.CollectionUtils;
import com.betamedia.qe.af.common.connectors.tp.AFTPConnector;
import com.betamedia.qe.af.core.api.tp.adapters.CRMHTTPAdapter;
import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.response.CustomerRegister;
import com.betamedia.qe.af.core.api.tp.entities.response.TPCRMResponse;
import com.betamedia.qe.af.core.api.tp.operations.AccountGroupOperations;
import com.betamedia.qe.af.core.api.tp.operations.AccountOperations;
import com.betamedia.qe.af.core.api.tp.operations.BrandOperation;
import com.betamedia.tp.api.model.Account;
import com.betamedia.tp.api.model.enums.AccountLevel;
import com.betamedia.tp.api.model.enums.AccountStatus;
import com.betamedia.tp.api.model.enums.AccountType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
@Component
public class AccountOperationsImpl implements AccountOperations {

    private static final Logger logger = LogManager.getLogger(AccountOperationsImpl.class);

    public static final double DEFAULT_ACCOUNT_BALANCE = 1000.0d;
    public static final String DEFAULT_ACCOUNT_PASSWORD = "123123";

    @Autowired
    private AFTPConnector tpConnector;

    @Autowired
    private AccountGroupOperations accountGroupOperations;

    @Autowired
    private BrandOperation brandOperation;

    @Autowired
    private CRMHTTPAdapter crmHttpAdapter;

    @Override
    public Account get() {
        return create();
    }

    @Override
    public Account get(String id) {
        Account account = tpConnector.readById(Account.class, id);
        assertNotNull(account, "Account with id " + id + " is not available in GS");
        return account;
    }

    private Account create() {
        Account account = new Account();
        account.setAccountGroup(accountGroupOperations.get());
        account.setAccountType(AccountType.REAL);
        account.setAssetsUsage(new ArrayList<String>());
        account.setBrand(brandOperation.get());
        account.setCountry(Country.USA);
        account.setCreatedBy("Automation test");
        account.setCurrency(Currency.USD);
        account.setDateCreated(System.currentTimeMillis());
        account.setDescription(
                "This account was created by automatic test.");
        account.setLevel(AccountLevel.REGULAR);
        account.setPassword(DEFAULT_ACCOUNT_PASSWORD);
        account.setStatus(AccountStatus.ACTIVE);
        account = tpConnector.create(account);
        assertNotNull(account);
        account = updateBalance(account.getId(), DEFAULT_ACCOUNT_BALANCE);
        logger.info("Account '" + account.getDisplayId() + "'(" + account.getId() + "') created");
        return account;
    }

    @Override
    public Account update(Account account, Set<String> properties) {
//        TODO add some verifications or make the method private
        return tpConnector.update(account, properties);
    }

    @Override
    public Account updateBalance(String accountId, Double amount) {
        Account account = get(accountId);
        account.setBalance(amount);
        account = update(account, CollectionUtils.toSet(Account.EP_ACCOUNT_BALANCE.getName()));
        assertEquals(account.getBalance(), amount, "The balance of account " + accountId + "was not updated");
        return account;
    }

    @Override
    public Account register() {
        return register(new CustomerBuilder().setBrandDisplayId(brandOperation.get().getDisplayId()));
    }

    @Override
    public Account register(CustomerBuilder customerBuilder) {
        if (customerBuilder.getBrandDisplayId() ==null ){
            customerBuilder.setBrandDisplayId(brandOperation.get().getDisplayId());
        }
        TPCRMResponse<CustomerRegister> register = crmHttpAdapter.register(customerBuilder.createCustomer());
        assertNotNull(register.getResult(), "The new customer wasn't created" + register.getErrors());
        return get(register.getResult().getAccountId());
    }


}
