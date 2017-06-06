package com.betamedia.atom.core.api.tp.adapters;

import com.betamedia.atom.core.api.tp.entities.request.AccountRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMAccountCreate;
import com.betamedia.atom.core.api.tp.entities.response.TPCRMResponse;
import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.atom.core.api.tp.entities.response.CRMAddBonus;
import com.betamedia.atom.core.api.tp.entities.response.CRMDeposit;
import com.betamedia.tp.api.model.enums.BonusType;

/**
 * Adapter for TP-CRM-API (doing operation on TP via http protocol, generally used by CRM)
 *
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
public interface TPCRMHttpAdapter<T extends EnvironmentDependent> {

    /**
     * Add bonus for account
     *
     * @param accountId
     * @param bonusType
     * @param amount
     * @param wagerAmount
     * @return displayId for created bonus
     */
    TPCRMResponse<CRMAddBonus> addBonus(String accountId, BonusType bonusType, Double amount, Double wagerAmount, String brandDisplayId);

    /**
     * Create a new accounts
     *
     * @param accountRO
     * @return
     */
    TPCRMResponse<CRMAccountCreate> create(AccountRO accountRO);

    /**
     * Deposit to account
     *
     * @param accountId
     * @param amount
     * @param displayBrandId
     * @return transactionId inside CRMDeposit object
     */
    TPCRMResponse<CRMDeposit> deposit(String accountId, Double amount, String displayBrandId);
}
