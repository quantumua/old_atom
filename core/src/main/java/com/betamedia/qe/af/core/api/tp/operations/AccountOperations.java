package com.betamedia.qe.af.core.api.tp.operations;

import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
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
    Account get();

    Account get(String id);

    Account update(Account account, Set<String> properties);

    Account updateBalance(String accountId, Double amount);

    /**
     * Create new account(user) with default params through CRM-API
     * @return
     */
    Account register();

    /**
     * Create new account(user) with custom params through CRM-API
     * @return
     */
    Account register(CustomerBuilder customerBuilder);
}
