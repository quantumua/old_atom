package com.betamedia.qe.af.core.dsl.operations.impl;

import com.betamedia.common.enums.Country;
import com.betamedia.common.enums.Currency;
import com.betamedia.common.utils.CollectionUtils;
import com.betamedia.qe.af.core.api.tp.adapters.TPCRMHttpAdapter;
import com.betamedia.qe.af.core.api.tp.entities.builders.AccountBuilder;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMAccountCreate;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMDeposit;
import com.betamedia.qe.af.core.api.tp.entities.response.TPCRMResponse;
import com.betamedia.qe.af.core.connectors.tp.AFTPConnector;
import com.betamedia.qe.af.core.dsl.operations.AccountGroupOperations;
import com.betamedia.qe.af.core.dsl.operations.AccountOperations;
import com.betamedia.qe.af.core.dsl.operations.BrandOperations;
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

import static com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder.PASSWORD;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * This class is designed to facilitate the execution of common operations related to account.
 * It can be used as a "building block" when writing integration tests.
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 * @see Account
 */
@Component
public class AccountOperationsImpl implements AccountOperations {

    private static final Logger logger = LogManager.getLogger(AccountOperationsImpl.class);

    public static final double DEFAULT_ACCOUNT_BALANCE = 1000.0d;

    @Autowired
    private AFTPConnector tpConnector;

    @Autowired
    private AccountGroupOperations accountGroupOperations;

    @Autowired
    private BrandOperations brandOperations;

    @Autowired
    private TPCRMHttpAdapter crmHttpAdapter;

    /**
     * Creates and returns default trading platform account.
     */
    @Override
    public Account getTP() {
        return createTP();
    }

    /**
     * Returns trading platform account by id.
     */
    @Override
    public Account getTP(String id) {
        Account account = tpConnector.readById(Account.class, id);
        assertNotNull(account, "Account with id " + id + " is not available in GS");
        return account;
    }

    private Account createTP() {
        Account account = new Account();
        account.setAccountGroup(accountGroupOperations.get());
        account.setAccountType(AccountType.REAL);
        account.setAssetsUsage(new ArrayList<>());
        account.setBrand(brandOperations.get());
        account.setCountry(Country.USA);
        account.setCreatedBy("Automation test");
        account.setCurrency(Currency.USD);
        account.setDateCreated(System.currentTimeMillis());
        account.setDescription(
                "This account was created by automatic test.");
        account.setLevel(AccountLevel.REGULAR);
        account.setPassword(PASSWORD);
        account.setStatus(AccountStatus.ACTIVE);
        account = tpConnector.create(account);
        assertNotNull(account);
        account = updateBalanceTP(account.getId(), DEFAULT_ACCOUNT_BALANCE);
        logger.info("Account '" + account.getDisplayId() + "'(" + account.getId() + "') created");
        return account;
    }

    /**
     * A method to update the specified trading platform account with given properties.
     * @param account existing trading platfrom account
     * @param properties properties to set
     * @return updated account
     */
    @Override
    public Account updateTP(Account account, Set<String> properties) {
//        TODO add some verifications or make the method private
        return tpConnector.update(account, properties);
    }

    /**
     * A method to update account balance.
     * @param accountId existing account id
     * @param amount balance to set
     * @return updated account
     */
    @Override
    public Account updateBalanceTP(String accountId, Double amount) {
        Account account = getTP(accountId);
        account.setBalance(amount);
        account = updateTP(account, CollectionUtils.toSet(Account.EP_ACCOUNT_BALANCE.getName()));
        assertEquals(account.getBalance(), amount, "The balance of account " + accountId + "was not updated");
        return account;
    }

    /**
     * A method to register and get a CRM account.
     * An account is created with current brand display id.
     * @return crm account
     */
    @Override
    public Account getCRM() {
        return getCRM(new AccountBuilder().setBrandDisplayId(brandOperations.get().getDisplayId()));
    }

    /**
     * A method to register and get a CRM account using the specified account builder.
     * If the account builder's brand display id is not set, current display id will be used.
     * @return crm account
     */
    @Override
    public Account getCRM(AccountBuilder accountBuilder) {
        if (accountBuilder.getBrandDisplayId() == null) {
            accountBuilder.setBrandDisplayId(brandOperations.get().getDisplayId());
        }
        TPCRMResponse<CRMAccountCreate> register = crmHttpAdapter.create(accountBuilder.createAccountRO());
        assertNotNull(register.getResult(), "The new account wasn't created" + register.getErrors());
        return getTP(register.getResult().getAccountId());
    }

    /**
     * A method to make a CRM deposit for the given account.
     * @param amount deposit amount
     * @return deposit's transaction id
     */
    @Override
    public String depositCRM(String accountId, Double amount) {
        TPCRMResponse<CRMDeposit> deposit = crmHttpAdapter.deposit(accountId, amount, brandOperations.get().getDisplayId());
        assertNotNull(deposit.getResult(), "The deposit operation wasn't success" + deposit.getErrors());
        return deposit.getResult().getTransactionId();
    }


}
