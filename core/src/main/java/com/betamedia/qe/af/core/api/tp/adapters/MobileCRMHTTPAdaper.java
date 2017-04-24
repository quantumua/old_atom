package com.betamedia.qe.af.core.api.tp.adapters;

import com.betamedia.qe.af.core.api.tp.entities.request.CustomerRO;
import com.betamedia.qe.af.core.api.tp.entities.request.MobileDepositRO;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMRegisterResult;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMResponse;
import com.betamedia.qe.af.core.api.tp.entities.response.MobileCRMDeposit;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/30/17.
 */
public interface MobileCRMHTTPAdaper {

    /**
     * Register a new customer
     * @param customerRO
     * @return
     */
    CRMResponse<CRMRegisterResult> register(CustomerRO customerRO);

    CRMResponse<CRMRegisterResult> login(String username, String password);

    CRMResponse logout(String customerId);

    CRMResponse<MobileCRMDeposit> deposit(MobileDepositRO depositRO);
}
