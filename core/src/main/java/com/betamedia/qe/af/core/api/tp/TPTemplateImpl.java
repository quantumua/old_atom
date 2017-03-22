package com.betamedia.qe.af.core.api.tp;

import com.betamedia.qe.af.core.api.tp.operations.AccountGroupOperations;
import com.betamedia.qe.af.core.api.tp.operations.AccountOperations;
import com.betamedia.qe.af.core.api.tp.operations.BonusOperations;
import com.betamedia.qe.af.core.api.tp.operations.BrandOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
@Component
public class TPTemplateImpl implements TPTemplate {

    @Autowired
    private AccountOperations accountOperations;
    @Autowired
    private AccountGroupOperations accountGroupOperations;
    @Autowired
    private BrandOperation brandOperation;
    @Autowired
    private BonusOperations bonusOperations;

    @Override
    public AccountOperations accountOperations() {
        return accountOperations;
    }

    public AccountGroupOperations accountGroupOperations() {
        return accountGroupOperations;
    }

    public BrandOperation brandOperation() {
        return brandOperation;
    }

    @Override
    public BonusOperations bonusOperations() {
        return bonusOperations;
    }
}
