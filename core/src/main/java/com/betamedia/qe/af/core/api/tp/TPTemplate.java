package com.betamedia.qe.af.core.api.tp;

import com.betamedia.qe.af.core.api.BackEndOperationsTemplate;
import com.betamedia.qe.af.core.api.tp.operations.AccountGroupOperations;
import com.betamedia.qe.af.core.api.tp.operations.AccountOperations;
import com.betamedia.qe.af.core.api.tp.operations.BonusOperations;
import com.betamedia.qe.af.core.api.tp.operations.BrandOperation;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
public interface TPTemplate extends BackEndOperationsTemplate{

    AccountOperations accountOperations();

    AccountGroupOperations accountGroupOperations();

    BrandOperation brandOperation();

    BonusOperations bonusOperations();
}
