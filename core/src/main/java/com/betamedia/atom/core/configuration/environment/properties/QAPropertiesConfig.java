package com.betamedia.atom.core.configuration.environment.properties;

import com.betamedia.atom.core.configuration.properties.CRMProperties;
import com.betamedia.atom.core.configuration.properties.EntityProperties;
import com.betamedia.atom.core.configuration.properties.EnvironmentProperties;
import com.betamedia.atom.core.environment.tp.QAEnvironment;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * QA Environment-specific properties configuration class.
 *
 * @author mbelyaev
 * @since 7/4/17
 */
@Configuration
@Profile("qa")
@PropertySource("/config/environment/qa-env.properties")
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

}
