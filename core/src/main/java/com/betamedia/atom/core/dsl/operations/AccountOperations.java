package com.betamedia.atom.core.dsl.operations;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.atom.core.api.tp.entities.request.AccountRO;
import com.betamedia.tp.api.model.Account;

import java.util.Set;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
public interface AccountOperations <T extends EnvironmentDependent> extends EnvironmentDependent {

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
    Account getCRM(AccountRO.AccountROBuilder accountROBuilder);

    /**
     * Deposit for account through TP CRM API
     * @param accountId
     * @param amount
     * @return id of transaction
     */
    String depositCRM(String accountId, Double amount);
}
