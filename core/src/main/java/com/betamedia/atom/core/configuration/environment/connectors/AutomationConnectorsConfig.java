package com.betamedia.atom.core.configuration.environment.connectors;

import com.betamedia.atom.core.api.tp.adapters.impl.AbstractMobileCRMHTTPAdapter;
import com.betamedia.atom.core.api.tp.adapters.impl.AbstractTPCRMHttpAdapter;
import com.betamedia.atom.core.configuration.environment.AutomationEnvironmentConfig;
import com.betamedia.atom.core.connectors.tp.FWTPConnector;
import com.betamedia.atom.core.connectors.tp.feedgateway.FWFeedGatewayConnector;
import com.betamedia.atom.core.dsl.pages.type.EnvironmentType;
import com.betamedia.atom.core.environment.tp.AutomationEnvironment;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.betamedia.atom.core.configuration.environment.StubConfig.GIGASPACES_ENABLED_PROPERTY;

/**
 * Automation Environment-specific connector configuration class.
 *
 * @author mbelyaev
 * @since 7/4/17
 */
@Configuration
@ConditionalOnBean(AutomationEnvironmentConfig.class)
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

    @Bean
    @ConditionalOnProperty(name = GIGASPACES_ENABLED_PROPERTY, matchIfMissing = true)
    public FWFeedGatewayConnector<AutomationEnvironment> automationFeedGWConnector() {
        return new FWFeedGatewayConnector<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    @ConditionalOnProperty(name = GIGASPACES_ENABLED_PROPERTY, matchIfMissing = true)
    public FWTPConnector<AutomationEnvironment> automationFWTPConnector() {
        return new FWTPConnector<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.getEnvironment();
            }
        };
    }
}
