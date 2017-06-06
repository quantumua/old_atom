package com.betamedia.atom.core.api.tp.adapters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Collections.singleton;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/31/17.
 */
public abstract class AbstractHttpAdapter {

    protected Map<String, String> requiredParams;
    protected RestTemplate restTemplate = new RestTemplate();
    protected ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        requiredParams = getRequiredParams();
    }

    protected UriComponentsBuilder buildRequestUrl(String url, Object... paramsObjects) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        for (Object paramsObject : paramsObjects) {
            Map<String, String> params = objectMapper.convertValue(paramsObject, new TypeReference<LinkedHashMap<String, String>>() {
            });
            params.values().removeAll(singleton(null));
            map.setAll(params);
        }

        map.setAll(requiredParams);
        return UriComponentsBuilder.fromUriString(getBaseUrl() + url).queryParams(map);
    }

    /**
     * Initialize required shared params for all requests to base_url endpoints, e.g. backOffice's username and password, origin, tokens ...
     * @return
     */
    protected Map<String, String> getRequiredParams(){
        return Collections.EMPTY_MAP;
    }

    protected abstract String getBaseUrl();
}
