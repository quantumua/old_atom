package com.betamedia.qe.af.configuration;

import com.betamedia.qe.af.components.SUTPropertiesHolder;
import com.betamedia.qe.af.components.SUTPropertiesHolderImpl;
import org.springframework.context.annotation.*;

import java.util.Properties;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/28/17.
 */

@Configuration
public class PerRequestConfiguration {

    @Bean
    @Lazy
    @Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public SUTPropertiesHolder sutPropertiesHolder(Properties properties) {
        return new SUTPropertiesHolderImpl(properties);
    }
}
