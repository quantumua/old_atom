package com.betamedia.qe.af.core.api.tp.adapters.impl;

import com.betamedia.qe.af.core.api.tp.adapters.CRMHTTPAdapter;
import com.betamedia.qe.af.core.api.tp.operations.BrandOperation;
import com.betamedia.tp.api.model.enums.BonusType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private final String CANCEL_BONUS_URL = "account/bonus/cancel";
    private final String ADJUSTMENT_URL = "account/adjustment";
    private final String ADD_BONUS_URL = "account/bonus/add";
    private final String CANCEL_WITHDRAWAL_URL = "account/withdrawal/cancel";
    private final String DEPOSIT_URL = "account/deposit";
    private final String WITHDRAWAL_URL = "account/withdrawal/add";

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
     * @param accountId
     * @param bonusType
     * @param amount
     * @param wagerAmount
     * @return displayId for created bonus
     */
    @Override
    public String addBonus(String accountId, BonusType bonusType, Double amount, Double wagerAmount) {
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
//        CRMResponse<Object> crmResponse = restTemplate.exchange(crmUrl+ADD_BONUS_URL, HttpMethod.GET, null,
//                new ParameterizedTypeReference<CRMResponse<Object>>() {
//                }, params).getBody();
//        logger.debug("Bonus for account {} succesfully added, {}",accountId, crmResponse);
//        return crmResponse;
        //     TODO add parameterized CRMResponse<Object> and return String directly
//        Do not work - {"errors":[{"code":"generalError","message":"Required String parameter 'userName' is not present"}],"result":null}
//        need to enable rest template logging
//        String response = restTemplate.exchange(crmUrl + ADD_BONUS_URL, HttpMethod.GET, null, String.class, params).getBody();
        String response = restTemplate.exchange(url, HttpMethod.GET, null, String.class).getBody();
        logger.info("Bonus added, {}", response);
        DocumentContext documentContext = JsonPath.parse(response);
        /*ResponseExample
        {
            "errors": [],
            "result": {
                "bonusId": "B308"
            }
        }*/
        return documentContext.read("$.result.bonusId");
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
