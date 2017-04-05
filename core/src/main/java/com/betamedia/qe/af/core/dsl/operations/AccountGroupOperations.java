package com.betamedia.qe.af.core.dsl.operations;

import com.betamedia.tp.api.model.AccountGroup;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
public interface AccountGroupOperations {

    AccountGroup get();

    AccountGroup get(String id);

    Integer getOpeningDelay(Double amount);
}
