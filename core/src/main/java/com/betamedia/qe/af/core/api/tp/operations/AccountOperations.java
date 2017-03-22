package com.betamedia.qe.af.core.api.tp.operations;

import com.betamedia.tp.api.model.Account;

import java.util.Set;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
public interface AccountOperations {

    Account get();

    Account get(String id);

    Account getOrCreate(String id);

    Account update(Account account, Set<String> properties);

    Account updateBalance(String accountId, Double amount);
}
