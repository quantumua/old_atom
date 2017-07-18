package com.betamedia.atom.core.configuration.environment.properties;

import com.betamedia.atom.core.configuration.environment.QAEnvironmentConfig;
import com.betamedia.atom.core.configuration.properties.*;
import com.betamedia.atom.core.environment.tp.QAEnvironment;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * QA Environment-specific properties configuration class.
 *
 * @author mbelyaev
 * @since 7/4/17
 */
@Configuration
@PropertySource("/config/environment/qa-env.properties")
@ConditionalOnBean(QAEnvironmentConfig.class)
public class QAPropertiesConfig {
    private static final String QA_TP_PREFIX = "qa.tp";

    @Bean
    @ConfigurationProperties(QA_TP_PREFIX)
    public EnvironmentProperties<QAEnvironment> qaEnvironmentProperties() {
        return new EnvironmentProperties<>();
    }

    @Bean
    public DataSourceProperties qaDataSourceProperties() {
        return qaEnvironmentProperties().getDb();
    }

    @Bean
    public CRMProperties<QAEnvironment> qaCrmProperties() {
        return qaEnvironmentProperties().getCrm();
    }

    @Bean
    public EntityProperties<QAEnvironment> qaEntityProperties() {
        return qaEnvironmentProperties().getEntity();
    }

    @Bean
    public SpaceProperties<QAEnvironment> qaSpaceProperties() {
        return qaEnvironmentProperties().getGigaspaces();
    }

    @Bean
    public FeedGatewayProperties<QAEnvironment> qaFeedGatewayProperties() {
        return qaEnvironmentProperties().getFeedGateway();
    }

}
