package com.betamedia.qe.af.core.api.tp.adapters.impl;

import com.betamedia.qe.af.core.api.tp.adapters.AbstractHttpAdapter;
import com.betamedia.qe.af.core.api.tp.adapters.TPCRMHttpAdapter;
import com.betamedia.qe.af.core.api.tp.entities.AccountRO;
import com.betamedia.qe.af.core.api.tp.entities.response.AccountCreateCRM;
import com.betamedia.qe.af.core.api.tp.entities.response.AddBonus;
import com.betamedia.qe.af.core.api.tp.entities.response.TPCRMResponse;
import com.betamedia.qe.af.core.api.tp.operations.BrandOperations;
import com.betamedia.tp.api.model.enums.BonusType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

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
@Component
public class TPCRMHttpAdapterImpl extends AbstractHttpAdapter implements TPCRMHttpAdapter {

    private static final Logger logger = LogManager.getLogger(TPCRMHttpAdapterImpl.class);

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
    private BrandOperations brandOperations;

    @Value("${af.crm.url}")
    private String crmUrl;
    @Value("${af.bo.username}")
    private String backOfficeUsername;
    @Value("${af.bo.password}")
    private String backOfficePassword;

    @Override
    protected Map<String, String> getRequiredParams() {
        return Collections.unmodifiableMap(Stream.of(
                new AbstractMap.SimpleEntry<>(PARAM_USERNAME, backOfficeUsername),
                new AbstractMap.SimpleEntry<>(PARAM_PASSWORD, backOfficePassword))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));
    }

    @Override
    protected String getBaseUrl() {
        return crmUrl;
    }

    @Override
    public TPCRMResponse<AddBonus> addBonus(String accountId, BonusType bonusType, Double amount, Double wagerAmount) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("accountId", accountId);
        params.put("brandId", brandOperations.get().getDisplayId());
        params.put("amount", amount.toString());
        params.put("wagerAmount", wagerAmount.toString());
        params.put("bonusType", bonusType.getName());
        String url = buildRequestUrl(ADD_BONUS_URL, params).build().toUriString();
        logger.info("Adding bonus, url={}", url);
        logger.info("Adding bonus {} to account {}", amount, accountId);
        TPCRMResponse<AddBonus> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<TPCRMResponse<AddBonus>>() {
                }).getBody();
        logger.info("Bonus added, {}", response);
        return response;
    }

    @Override
    public TPCRMResponse<AccountCreateCRM> create(AccountRO accountRO) {
        String url = buildRequestUrl(CREATE_ACCOUNT_URL, accountRO).build().toUriString();
        logger.info("Creating new accountRO, url={}", url);
        TPCRMResponse<AccountCreateCRM> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<TPCRMResponse<AccountCreateCRM>>() {
                }).getBody();
        logger.info("AccountRO created, {}", response);
        return response;
    }

}
