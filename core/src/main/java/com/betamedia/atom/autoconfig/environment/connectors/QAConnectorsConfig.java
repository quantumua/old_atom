package com.betamedia.atom.autoconfig.environment.connectors;

import com.betamedia.atom.core.api.tp.adapters.impl.AbstractMobileCRMHTTPAdapter;
import com.betamedia.atom.core.api.tp.adapters.impl.AbstractTPCRMHttpAdapter;
import com.betamedia.atom.autoconfig.environment.QAEnvironmentConfig;
import com.betamedia.atom.core.dsl.pages.type.EnvironmentType;
import com.betamedia.atom.core.environment.tp.QAEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * QA Environment-specific connector configuration class.
 *
 * @author mbelyaev
 * @since 7/4/17
 */
@Configuration
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
