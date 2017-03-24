package com.betamedia.qe.af.core.api.tp.adapters.impl;

import com.betamedia.qe.af.core.api.tp.adapters.CRMHTTPAdapter;
import com.betamedia.qe.af.core.api.tp.entities.response.AddBonus;
import com.betamedia.qe.af.core.api.tp.entities.response.TPCRMResponse;
import com.betamedia.qe.af.core.api.tp.operations.BrandOperation;
import com.betamedia.tp.api.model.enums.BonusType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Collections.singleton;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/22/17.
 */
@Component
public class CRMHTTPAdapterImpl implements CRMHTTPAdapter {

    private static final Logger logger = LogManager.getLogger(CRMHTTPAdapterImpl.class);

    private static final String CANCEL_BONUS_URL = "account/bonus/cancel";
    private static final String ADJUSTMENT_URL = "account/adjustment";
    private static final String ADD_BONUS_URL = "account/bonus/add";
    private static final String CANCEL_WITHDRAWAL_URL = "account/withdrawal/cancel";
    private static final String DEPOSIT_URL = "account/deposit";
    private static final String WITHDRAWAL_URL = "account/withdrawal/add";

    @Autowired
    private BrandOperation brandOperation;

    @Value("${af.crm.url}")
    private String crmUrl;
    @Value("${af.bo.username}")
    private String backOfficeUsername;
    @Value("${af.bo.password}")
    private String backOfficePassword;


    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Add bonus for account
     *
     * @param accountId
     * @param bonusType
     * @param amount
     * @param wagerAmount
     * @return displayId for created bonus
     */
    @Override
    public TPCRMResponse<AddBonus> addBonus(String accountId, BonusType bonusType, Double amount, Double wagerAmount) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("userName", backOfficeUsername);
        params.put("userPassword", backOfficePassword);
        params.put("accountId", accountId);
        params.put("brandId", brandOperation.get().getDisplayId());
        params.put("amount", amount.toString());
        params.put("wagerAmount", wagerAmount.toString());
        params.put("bonusType", bonusType.getName());
        String url = buildRequestUrl(ADD_BONUS_URL, params).build().toUriString();
        logger.info("Adding bonus, url={}", url);
        logger.info("Adding bonus {} to account {}", amount, accountId);
        TPCRMResponse<AddBonus> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<TPCRMResponse<AddBonus>>() {
                }, params).getBody();
        logger.info("Bonus added, {}", response);
        return response;
    }

    //    TODO will be shared with TPHTTPAdapter, need to move to utils or base class
    private UriComponentsBuilder buildRequestUrl(String url, Object paramsObject) {
        Map<String, String> params = objectMapper.convertValue(paramsObject, new TypeReference<LinkedHashMap<String, String>>() {
        });
        params.values().removeAll(singleton(null));
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.setAll(params);
        return UriComponentsBuilder.fromUriString(crmUrl + url).queryParams(map);
    }
}
