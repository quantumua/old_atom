package com.betamedia.atom.core.testlink;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by sergeyg on 04.07.17.
 */
@Component
@PropertySource("classpath:testlink.properties")
@ConfigurationProperties(prefix = "testlink")
public class TestLinkProperties {
    private String url;
    private String key;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
