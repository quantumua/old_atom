package com.betamedia.atom.core.configuration.environment.connectors;

import com.betamedia.atom.core.api.tp.adapters.impl.AbstractMobileCRMHTTPAdapter;
import com.betamedia.atom.core.api.tp.adapters.impl.AbstractTPCRMHttpAdapter;
import com.betamedia.atom.core.configuration.environment.QAEnvironmentConfig;
import com.betamedia.atom.core.dsl.type.EnvironmentType;
import com.betamedia.atom.core.environment.tp.QAEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * QA Environment-specific connector configuration class.
 *
 * @author mbelyaev
 * @since 7/4/17
 */
@Configuration
@Profile("qa")
public class QAConnectorsConfig {

    @Bean
    public AbstractTPCRMHttpAdapter<QAEnvironment> qaTPCRMHttpAdapter() {
        return new AbstractTPCRMHttpAdapter<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractMobileCRMHTTPAdapter<QAEnvironment> qaMobileCRMHttpAdapter() {
        return new AbstractMobileCRMHTTPAdapter<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

}
