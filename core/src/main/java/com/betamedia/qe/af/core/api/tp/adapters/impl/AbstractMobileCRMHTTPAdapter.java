package com.betamedia.qe.af.core.api.tp.adapters.impl;

import com.betamedia.qe.af.core.api.tp.adapters.AbstractHttpAdapter;
import com.betamedia.qe.af.core.api.tp.adapters.MobileCRMHTTPAdaper;
import com.betamedia.qe.af.core.api.tp.entities.request.CustomerRO;
import com.betamedia.qe.af.core.api.tp.entities.request.MobileDepositRO;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMRegisterResult;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMResponse;
import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;
import com.betamedia.qe.af.core.environment.tp.properties.CRMPropertiesHolder;
mport com.betamedia.qe.af.core.api.tp.entities.response.MobileCRMDeposit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This is an adapter to perform HTTP requests to Mobile CRM.
 * @author Maksym Tsybulskyy
 *         Date: 3/31/17.
 * @see CRMRegisterResult
 */

public abstract class AbstractMobileCRMHTTPAdapter<T extends EnvironmentDependent> extends AbstractHttpAdapter implements MobileCRMHTTPAdaper<T> {

    private static final Logger logger = LogManager.getLogger(AbstractMobileCRMHTTPAdapter.class);
    public static final String REGISTER_CUSTOMER_URL = "/customer/registration";
    public static final String LOGIN_URL = "/customer/login";
    public static final String LOGOUT_URL = "/customer/logoff";
    public static final String DEPOSIT_URL = "/finance/deposit";

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

    /**
     * Logs in an existing customer with a given username and password.
     */
    @Override
    public CRMResponse<CRMRegisterResult> login(String username, String password) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("username", username);
        params.put("password", password);
        String url = buildRequestUrl(LOGIN_URL, params).build().toUriString();

        logger.info("Logging in customer, url={}", url);
        CRMResponse<CRMRegisterResult> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<CRMResponse<CRMRegisterResult>>() {
                }).getBody();
        logger.info("Customer logged in, {}", response);

        return response;
    }

    /**
     * Logs out an existing customer with a given customer id.
     */
    @Override
    public CRMResponse logout(String customerId) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("customerid", customerId);
        String url = buildRequestUrl(LOGOUT_URL, params).build().toUriString();

        logger.info("Logging out customer, url={}", url);
        CRMResponse response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<CRMResponse>() {
                }).getBody();
        logger.info("Customer logged out, {}", response);

        return response;
    }

    /**
     * Performs a mobile CRM deposit for a given deposit RO.
     */
    @Override
    public CRMResponse<MobileCRMDeposit> deposit(MobileDepositRO depositRO) {
        String url = buildRequestUrl(DEPOSIT_URL, depositRO).build().toUriString();
        logger.info("Making a deposit, url={}", url);
        CRMResponse<MobileCRMDeposit> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<CRMResponse<MobileCRMDeposit>>() {
                }).getBody();
        logger.info("Deposit result, {}", response);
        return response;
    }
}
