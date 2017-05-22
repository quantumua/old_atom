package com.betamedia.qe.af.core.api.tp.adapters;

import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.request.MarketingParametersRO;
import com.betamedia.qe.af.core.api.tp.entities.request.MobileDepositRO;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMRegisterResult;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMResponse;
import com.betamedia.qe.af.core.api.tp.entities.response.MobileCRMDeposit;
import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/30/17.
 */
public interface MobileCRMHTTPAdaper<T extends EnvironmentDependent> extends EnvironmentDependent {

    /**
     * Register a new customer
     * @param customerRO
     * @return
     */
    CRMResponse<CRMRegisterResult> register(CustomerBuilder.CustomerRO customerRO);

    CRMResponse<CRMRegisterResult> register(CustomerBuilder.CustomerRO customerRO, MarketingParametersRO marketingParametersRO);

    CRMResponse<CRMRegisterResult> login(String username, String password);

    CRMResponse logout(String customerId);

    CRMResponse<MobileCRMDeposit> deposit(MobileDepositRO depositRO);

    CRMResponse<MobileCRMDeposit> deposit(MobileDepositRO depositRO, MarketingParametersRO marketingParametersRO);

    CRMResponse<MobileCRMDeposit> depositByName(MobileDepositRO depositRO);
}
