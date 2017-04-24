package com.betamedia.qe.af.core.api.tp.adapters.impl;

import com.betamedia.qe.af.core.api.tp.adapters.AbstractHttpAdapter;
import com.betamedia.qe.af.core.api.tp.adapters.MobileCRMHTTPAdaper;
import com.betamedia.qe.af.core.api.tp.entities.request.CustomerRO;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMRegisterResult;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMResponse;
import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;
import com.betamedia.qe.af.core.environment.tp.properties.CRMPropertiesHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/31/17.
 */

public abstract class AbstractMobileCRMHTTPAdapter<T extends EnvironmentDependent> extends AbstractHttpAdapter implements MobileCRMHTTPAdaper<T> {

    private static final Logger logger = LogManager.getLogger(AbstractMobileCRMHTTPAdapter.class);
    public static final String REGISTER_CUSTOMER_URL = "/customer/registration";

    @Autowired
    private CRMPropertiesHolder<T> crmPropertiesHolder;

    @Override
    protected String getBaseUrl() {
        return crmPropertiesHolder.getMobileCRMUrl();
    }

    @Override
    public CRMResponse<CRMRegisterResult> register(CustomerRO customerRO) {
        String url = buildRequestUrl(REGISTER_CUSTOMER_URL, customerRO).build().toUriString();
        logger.info("Registering new customer, url={}, {}", url, getEnvironment());
        CRMResponse<CRMRegisterResult> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<CRMResponse<CRMRegisterResult>>() {
                }).getBody();
        logger.info("New customer created, {}, {}", response, getEnvironment());
        return response;
    }
}
