package com.betamedia.qe.af.webservice.configuration;

import com.betamedia.qe.af.common.holder.SUTPropertiesHolder;
import com.betamedia.qe.af.common.holder.SUTPropertiesHolderImpl;
import org.springframework.context.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Properties;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/28/17.
 */

@Configuration
public class PerRequestConfiguration {

    @Bean
    @Lazy
    @RequestScope
    public SUTPropertiesHolder sutPropertiesHolder(Properties properties) {
        return new SUTPropertiesHolderImpl(properties);
    }
}
