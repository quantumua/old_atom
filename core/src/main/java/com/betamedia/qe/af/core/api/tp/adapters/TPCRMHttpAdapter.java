package com.betamedia.qe.af.core.api.tp.adapters;

import com.betamedia.qe.af.core.api.tp.entities.AccountRO;
import com.betamedia.qe.af.core.api.tp.entities.response.AccountCreateCRM;
import com.betamedia.qe.af.core.api.tp.entities.response.AddBonus;
import com.betamedia.qe.af.core.api.tp.entities.response.TPCRMResponse;
import com.betamedia.tp.api.model.enums.BonusType;

/**
 * Adapter for TP-CRM-API (doing operation on TP via http protocol, generally used by CRM)
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
public interface TPCRMHttpAdapter {

    /**
     * Add bonus for account
     *
     * @param accountId
     * @param bonusType
     * @param amount
     * @param wagerAmount
     * @return displayId for created bonus
     */
    TPCRMResponse<AddBonus> addBonus(String accountId, BonusType bonusType, Double amount, Double wagerAmount);

    /**
     * Create a new accounts
     * @param accountRO
     * @return
     */
    TPCRMResponse<AccountCreateCRM> create(AccountRO accountRO);
}
