package com.betamedia.atom.core.api.tp.adapters.impl;

import com.betamedia.atom.core.api.tp.adapters.AbstractHttpAdapter;
import com.betamedia.atom.core.api.tp.adapters.MobileCRMHTTPAdapter;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.tp.entities.request.MarketingParametersRO;
import com.betamedia.atom.core.api.tp.entities.request.MobileDepositRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMRegisterResult;
import com.betamedia.atom.core.api.tp.entities.response.CRMResponse;
import com.betamedia.atom.core.api.tp.entities.response.MobileCRMDeposit;
import com.betamedia.atom.core.configuration.properties.CRMProperties;
import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This is an adapter to perform HTTP requests to Mobile CRM.
 * @author Maksym Tsybulskyy
 *         Date: 3/31/17.
 * @see CRMRegisterResult
 */

public abstract class AbstractMobileCRMHTTPAdapter<T extends EnvironmentDependent> extends AbstractHttpAdapter implements MobileCRMHTTPAdapter<T> {

    private static final Logger logger = LogManager.getLogger(AbstractMobileCRMHTTPAdapter.class);
    public static final String REGISTER_CUSTOMER_URL = "/customer/registration";
    public static final String LOGIN_URL = "/customer/login";
    public static final String LOGOUT_URL = "/customer/logoff";
    public static final String DEPOSIT_URL = "/finance/deposit";
    public static final String DEPOSIT_BY_NAME_URL = "/finance/depositByName";

    @Autowired
    private CRMProperties<T> crmProperties;

    @Override
    protected String getBaseUrl() {
        return crmProperties.getMobileUrl();
    }

    /**
     * Registers a new customer with a given request object.
     */
    @Override
    public CRMResponse<CRMRegisterResult> register(CustomerRO customerRO) {
        String url = buildRequestUrl(REGISTER_CUSTOMER_URL, customerRO).build().toUriString();
        return register(url);
    }

    /**
     * Registers a new customer with a marketing parameters.
     */
    @Override
    public CRMResponse<CRMRegisterResult> register(CustomerRO customerRO, MarketingParametersRO marketingParametersRO) {
        String url = buildRequestUrl(REGISTER_CUSTOMER_URL, customerRO, marketingParametersRO).build().toUriString();
        return register(url);
    }

    private CRMResponse<CRMRegisterResult> register(String url) {
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

        logger.info("Logging in customer, url={}", url, getEnvironment());
        CRMResponse<CRMRegisterResult> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<CRMResponse<CRMRegisterResult>>() {
                }).getBody();
        logger.info("Customer logged in, {}", response, getEnvironment());

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

        logger.info("Logging out customer, url={}", url, getEnvironment());
        CRMResponse response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<CRMResponse>() {
                }).getBody();
        logger.info("Customer logged out, {}", response, getEnvironment());

        return response;
    }

    /**
     * Performs a mobile CRM deposit for a given deposit RO.
     */
    @Override
    public CRMResponse<MobileCRMDeposit> deposit(MobileDepositRO depositRO) {
        String url = buildRequestUrl(DEPOSIT_URL, depositRO).build().toUriString();
        return deposit(url);
    }

    /**
     * Performs a mobile CRM deposit with marketing parameters for a given deposit RO.
     */
    @Override
    public CRMResponse<MobileCRMDeposit> deposit(MobileDepositRO depositRO, MarketingParametersRO marketingParametersRO) {
        String url = buildRequestUrl(DEPOSIT_URL, depositRO, marketingParametersRO).build().toUriString();
        return deposit(url);
    }

    /**
     * Performs a mobile CRM deposit by trading account name, instead of trading id.
     */
    @Override
    public CRMResponse<MobileCRMDeposit> depositByName(MobileDepositRO depositRO) {
        String url = buildRequestUrl(DEPOSIT_BY_NAME_URL, depositRO).build().toUriString();
        return deposit(url);
    }

    private CRMResponse<MobileCRMDeposit> deposit(String url) {
        logger.info("Making a deposit, url={}", url, getEnvironment());
        CRMResponse<MobileCRMDeposit> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<CRMResponse<MobileCRMDeposit>>() {
                }).getBody();
        logger.info("Deposit result, {}", response, getEnvironment());
        return response;
    }
}
