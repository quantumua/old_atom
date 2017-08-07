package com.betamedia.atom.core.configuration.environment.connectors;

import com.betamedia.atom.core.api.tp.adapters.impl.AbstractMobileCRMHTTPAdapter;
import com.betamedia.atom.core.api.tp.adapters.impl.AbstractTPCRMHttpAdapter;
import com.betamedia.atom.core.configuration.environment.AutomationEnvironmentConfig;
import com.betamedia.atom.core.dsl.type.EnvironmentType;
import com.betamedia.atom.core.environment.tp.AutomationEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Automation Environment-specific connector configuration class.
 *
 * @author mbelyaev
 * @since 7/4/17
 */
@Configuration
@Profile("automation")
public class AutomationConnectorsConfig {

    @Bean
    public AbstractTPCRMHttpAdapter<AutomationEnvironment> automationTPCRMHttpAdapter() {
        return new AbstractTPCRMHttpAdapter<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractMobileCRMHTTPAdapter<AutomationEnvironment> automationMobileCRMHttpAdapter() {
        return new AbstractMobileCRMHTTPAdapter<AutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

}
