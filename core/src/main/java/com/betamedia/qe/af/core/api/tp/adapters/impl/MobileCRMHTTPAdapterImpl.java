package com.betamedia.qe.af.core.api.tp.adapters.impl;

import com.betamedia.qe.af.core.api.tp.adapters.AbstractHttpAdapter;
import com.betamedia.qe.af.core.api.tp.adapters.MobileCRMHTTPAdaper;
import com.betamedia.qe.af.core.api.tp.entities.request.CustomerRO;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMRegisterResult;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/31/17.
 */
@Component
public class MobileCRMHTTPAdapterImpl extends AbstractHttpAdapter implements MobileCRMHTTPAdaper {

    private static final Logger logger = LogManager.getLogger(MobileCRMHTTPAdapterImpl.class);
    public static final String REGISTER_CUSTOMER_URL = "/customer/registration";

    @Value("${af.mobile.crm.url}")
    private String mobileCRMUrl;

    @Override
    protected String getBaseUrl() {
        return mobileCRMUrl;
    }

    @Override
    public CRMResponse<CRMRegisterResult> register(CustomerRO customerRO) {
        String url = buildRequestUrl(REGISTER_CUSTOMER_URL, customerRO).build().toUriString();
        logger.info("Registering new customer, url={}", url);
        CRMResponse<CRMRegisterResult> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<CRMResponse<CRMRegisterResult>>() {
                }).getBody();
        logger.info("New customer created, {}", response);
        return response;
    }
}
