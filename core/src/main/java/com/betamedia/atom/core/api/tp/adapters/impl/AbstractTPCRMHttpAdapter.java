package com.betamedia.atom.core.api.tp.adapters.impl;

import com.betamedia.atom.core.api.tp.adapters.AbstractHttpAdapter;
import com.betamedia.atom.core.api.tp.adapters.TPCRMHttpAdapter;
import com.betamedia.atom.core.api.tp.entities.request.AccountRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMAccountCreate;
import com.betamedia.atom.core.api.tp.entities.response.CRMAddBonus;
import com.betamedia.atom.core.api.tp.entities.response.CRMDeposit;
import com.betamedia.atom.core.api.tp.entities.response.TPCRMResponse;
import com.betamedia.atom.core.configuration.properties.CRMProperties;
import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.tp.api.model.enums.BonusType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/22/17.
 */
public abstract class AbstractTPCRMHttpAdapter<T extends EnvironmentDependent> extends AbstractHttpAdapter implements TPCRMHttpAdapter<T>, EnvironmentDependent {

    private static final Logger logger = LogManager.getLogger(AbstractTPCRMHttpAdapter.class);

    private static final String CANCEL_BONUS_URL = "account/bonus/cancel";
    private static final String ADJUSTMENT_URL = "account/adjustment";
    private static final String ADD_BONUS_URL = "account/bonus/add";
    private static final String CANCEL_WITHDRAWAL_URL = "account/withdrawal/cancel";
    private static final String DEPOSIT_URL = "account/deposit";
    private static final String WITHDRAWAL_URL = "account/withdrawal/add";
    private static final String CREATE_ACCOUNT_URL = "account/create";

    private static final String PARAM_USERNAME = "userName";
    private static final String PARAM_PASSWORD = "userPassword";

    @Autowired
    private CRMProperties<T> crmProperties;

    @Override
    protected Map<String, String> getRequiredParams() {
        return Collections.unmodifiableMap(Stream.of(
                new AbstractMap.SimpleEntry<>(PARAM_USERNAME, crmProperties.getBackOfficeUsername()),
                new AbstractMap.SimpleEntry<>(PARAM_PASSWORD, crmProperties.getBackOfficePassword()))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));
    }

    @Override
    protected String getBaseUrl() {
        return crmProperties.getUrl();
    }

    @Override
    public TPCRMResponse<CRMAddBonus> addBonus(String accountId, BonusType bonusType, Double amount, Double wagerAmount, String brandDisplayId) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("accountId", accountId);
        params.put("brandId", brandDisplayId);
        params.put("amount", amount.toString());
        params.put("wagerAmount", wagerAmount.toString());
        params.put("bonusType", bonusType.getName());
        String url = buildRequestUrl(ADD_BONUS_URL, params).build().toUriString();
        logger.info("Adding bonus, url={}, {}", url, getEnvironment());
        logger.info("Adding bonus {} to account {}, {}", amount, accountId, getEnvironment());
        TPCRMResponse<CRMAddBonus> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<TPCRMResponse<CRMAddBonus>>() {
                }).getBody();
        logger.info("Bonus adding result, {}, {}", response, getEnvironment());
        return response;
    }

    @Override
    public TPCRMResponse<CRMAccountCreate> create(AccountRO accountRO) {
        String url = buildRequestUrl(CREATE_ACCOUNT_URL, accountRO).build().toUriString();
        logger.info("Creating new account, url={}, {}", url, getEnvironment());
        TPCRMResponse<CRMAccountCreate> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<TPCRMResponse<CRMAccountCreate>>() {
                }).getBody();
        logger.info("Account creating result, {}, {}", response, getEnvironment());
        return response;
    }

    @Override
    public TPCRMResponse<CRMDeposit> deposit(String accountId, Double amount, String displayBrandId) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("accountId", accountId);
        params.put("brandId", displayBrandId);
        params.put("amount", amount.toString());
        String url = buildRequestUrl(DEPOSIT_URL, params).build().toUriString();
        logger.info("Deposit, url={}, {}", url, getEnvironment());
        logger.info("Deposit {} to account {}, {}", amount, accountId, getEnvironment());
        TPCRMResponse<CRMDeposit> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<TPCRMResponse<CRMDeposit>>() {
                }).getBody();
        logger.info("Deposit result, {}, {}", response, getEnvironment());
        return response;
    }

}
