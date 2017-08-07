package com.betamedia.atom.core.configuration.environment.connectors;

import com.betamedia.atom.core.api.tp.adapters.impl.AbstractMobileCRMHTTPAdapter;
import com.betamedia.atom.core.api.tp.adapters.impl.AbstractTPCRMHttpAdapter;
import com.betamedia.atom.core.configuration.environment.NewAutomationEnvironmentConfig;
import com.betamedia.atom.core.dsl.type.EnvironmentType;
import com.betamedia.atom.core.environment.tp.NewAutomationEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * New Automation Environment-specific connector configuration class.
 *
 * @author mbelyaev
 * @since 7/4/17
 */
@Configuration
@Profile("newAutomation")
public class NewAutomationConnectorsConfig {

    @Bean
    public AbstractTPCRMHttpAdapter<NewAutomationEnvironment> newAutomationTPCRMHttpAdapter() {
        return new AbstractTPCRMHttpAdapter<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractMobileCRMHTTPAdapter<NewAutomationEnvironment> newAutomationMobileCRMHttpAdapter() {
        return new AbstractMobileCRMHTTPAdapter<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

}
