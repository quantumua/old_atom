package com.betamedia.atom.core.configuration.environment.properties;

import com.betamedia.atom.core.configuration.environment.AutomationEnvironmentConfig;
import com.betamedia.atom.core.configuration.properties.*;
import com.betamedia.atom.core.environment.tp.AutomationEnvironment;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Automation Environment-specific properties configuration class.
 *
 * @author mbelyaev
 * @since 7/4/17
 */
@Configuration
@PropertySource("/config/environment/automation-env.properties")
@ConditionalOnBean(AutomationEnvironmentConfig.class)
public class AutomationPropertiesConfig {
    private static final String AUTOMATION_TP_PREFIX = "automation.tp";

    @Bean
    @ConfigurationProperties(AUTOMATION_TP_PREFIX)
    public EnvironmentProperties<AutomationEnvironment> automationEnvironmentProperties() {
        return new EnvironmentProperties<>();
    }

    @Bean
    public DataSourceProperties automationDataSourceProperties() {
        return automationEnvironmentProperties().getDb();
    }

    @Bean
    public CRMProperties<AutomationEnvironment> automationCrmProperties() {
        return automationEnvironmentProperties().getCrm();
    }

    @Bean
    public EntityProperties<AutomationEnvironment> automationEntityProperties() {
        return automationEnvironmentProperties().getEntity();
    }

}
