package com.betamedia.atom.core.configuration.environment.properties;

import com.betamedia.atom.core.configuration.properties.CRMProperties;
import com.betamedia.atom.core.configuration.properties.EntityProperties;
import com.betamedia.atom.core.configuration.properties.EnvironmentProperties;
import com.betamedia.atom.core.environment.tp.NewAutomationEnvironment;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * New Automation Environment-specific properties configuration class.
 *
 * @author mbelyaev
 * @since 7/4/17
 */
@Configuration
@Profile("newAutomation")
@PropertySource("/config/environment/newAutomation-env.properties")
public class NewAutomationPropertiesConfig {
    private static final String NEW_AUTOMATION_TP_PREFIX = "newAutomation.tp";

    @Bean
    @ConfigurationProperties(NEW_AUTOMATION_TP_PREFIX)
    public EnvironmentProperties<NewAutomationEnvironment> newAutomationEnvironmentProperties() {
        return new EnvironmentProperties<>();
    }

    @Bean
    public DataSourceProperties newAutomationDataSourceProperties() {
        return newAutomationEnvironmentProperties().getDb();
    }

    @Bean
    public CRMProperties<NewAutomationEnvironment> newAutomationCrmProperties() {
        return newAutomationEnvironmentProperties().getCrm();
    }

    @Bean
    public EntityProperties<NewAutomationEnvironment> newAutomationEntityProperties() {
        return newAutomationEnvironmentProperties().getEntity();
    }

}
