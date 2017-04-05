package com.betamedia.qe.af.core.dsl.operations;

import com.betamedia.qe.af.core.api.tp.entities.builders.AccountBuilder;
import com.betamedia.tp.api.model.Account;

import java.util.Set;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
public interface AccountOperations {

    /**
     * Create new account with default params through GS
     * @return
     */
    Account getTP();

    Account getTP(String id);

    Account updateTP(Account account, Set<String> properties);

    Account updateBalanceTP(String accountId, Double amount);

    /**
     * Create new account with default params through TP CRM API
     * @return
     */
    Account getCRM();

    /**
     * Create new account with custom params through TP CRM API
     * @return
     */
    Account getCRM(AccountBuilder accountBuilder);

    /**
     * Deposit for account through TP CRM API
     * @param accountId
     * @param amount
     * @return id of transaction
     */
    String depositCRM(String accountId, Double amount);
}
