package com.betamedia.atom.core.api.tp.adapters;

import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.tp.entities.request.MarketingParametersRO;
import com.betamedia.atom.core.api.tp.entities.request.MobileDepositRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMRegisterResult;
import com.betamedia.atom.core.api.tp.entities.response.CRMResponse;
import com.betamedia.atom.core.api.tp.entities.response.MobileCRMDeposit;
import com.betamedia.atom.core.environment.tp.EnvironmentDependent;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/30/17.
 */
public interface MobileCRMHTTPAdapter<T extends EnvironmentDependent> extends EnvironmentDependent {

    /**
     * Register a new customer
     * @param customerRO
     * @return
     */
    CRMResponse<CRMRegisterResult> register(CustomerRO customerRO);

    CRMResponse<CRMRegisterResult> register(CustomerRO customerRO, MarketingParametersRO marketingParametersRO);

    CRMResponse<CRMRegisterResult> login(String username, String password);

    CRMResponse logout(String customerId);

    CRMResponse<MobileCRMDeposit> deposit(MobileDepositRO depositRO);

    CRMResponse<MobileCRMDeposit> deposit(MobileDepositRO depositRO, MarketingParametersRO marketingParametersRO);

    CRMResponse<MobileCRMDeposit> depositByName(MobileDepositRO depositRO);
}
