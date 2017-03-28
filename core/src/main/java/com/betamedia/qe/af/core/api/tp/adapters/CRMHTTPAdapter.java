package com.betamedia.qe.af.core.api.tp.adapters;

import com.betamedia.qe.af.core.api.tp.entities.Customer;
import com.betamedia.qe.af.core.api.tp.entities.response.AddBonus;
import com.betamedia.qe.af.core.api.tp.entities.response.CustomerRegister;
import com.betamedia.qe.af.core.api.tp.entities.response.TPCRMResponse;
import com.betamedia.tp.api.model.enums.BonusType;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
public interface CRMHTTPAdapter {

    TPCRMResponse<AddBonus> addBonus(String accountId, BonusType bonusType, Double amount, Double wagerAmount);
    
    TPCRMResponse<CustomerRegister> register(Customer customer);
}
