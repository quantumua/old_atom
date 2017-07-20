package com.betamedia.atom.core.configuration.environment.connectors;

import com.betamedia.atom.core.api.tp.adapters.impl.AbstractMobileCRMHTTPAdapter;
import com.betamedia.atom.core.api.tp.adapters.impl.AbstractTPCRMHttpAdapter;
import com.betamedia.atom.core.configuration.environment.NewAutomationEnvironmentConfig;
import com.betamedia.atom.core.connectors.tp.FWTPConnector;
import com.betamedia.atom.core.connectors.tp.feedgateway.FWFeedGatewayConnector;
import com.betamedia.atom.core.dsl.pages.type.EnvironmentType;
import com.betamedia.atom.core.environment.tp.NewAutomationEnvironment;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.betamedia.atom.core.configuration.environment.StubConfig.GIGASPACES_ENABLED_PROPERTY;

/**
 * New Automation Environment-specific connector configuration class.
 * Please take care to prefix bean names with <code>newAutomation</code> for post-processing purposes.
 *
 * @author mbelyaev
 * @see com.betamedia.atom.core.configuration.EnvironmentPropertiesBeanFactoryPostProcessor
 * @since 7/4/17
 */
@Configuration
@ConditionalOnBean(NewAutomationEnvironmentConfig.class)
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

    @Bean
    @ConditionalOnProperty(name = GIGASPACES_ENABLED_PROPERTY, matchIfMissing = true)
    public FWFeedGatewayConnector<NewAutomationEnvironment> newAutomationFeedGWConnector() {
        return new FWFeedGatewayConnector<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    @ConditionalOnProperty(name = GIGASPACES_ENABLED_PROPERTY, matchIfMissing = true)
    public FWTPConnector<NewAutomationEnvironment> newAutomationFWTPConnector() {
        return new FWTPConnector<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

}
