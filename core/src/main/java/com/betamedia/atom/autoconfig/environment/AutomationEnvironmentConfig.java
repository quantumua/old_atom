package com.betamedia.atom.autoconfig.environment;

import com.betamedia.atom.autoconfig.environment.connectors.AutomationConnectorsConfig;
import com.betamedia.atom.autoconfig.environment.persistence.AutomationPersistenceConfig;
import com.betamedia.atom.autoconfig.environment.properties.AutomationPropertiesConfig;
import com.betamedia.atom.core.dsl.operations.impl.*;
import com.betamedia.atom.core.dsl.pages.type.EnvironmentType;
import com.betamedia.atom.core.dsl.templates.tp.impl.AbstractTPTemplate;
import com.betamedia.atom.core.environment.tp.AutomationEnvironment;
import com.betamedia.atom.core.fwdataaccess.repository.util.version.AbstractApplicationVersionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

/**
 * Automation Environment-specific bean configuration class.
 *
 * @author mbelyaev
 * @since 6/29/17
 */
@Configuration
@Profile("automation")
@Import({
        AutomationConnectorsConfig.class,
        AutomationPersistenceConfig.class,
        AutomationPropertiesConfig.class
})
public class AutomationEnvironmentConfig {

    public static EnvironmentType getEnvironment() {
        return EnvironmentType.AUTOMATION;
    }

    @Bean
    public AbstractApplicationVersionService<AutomationEnvironment> automationVersionService() {
        return new AbstractApplicationVersionService<AutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTPTemplate<AutomationEnvironment> automationTPTemplate() {
        return new AbstractTPTemplate<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractAccountOperations<AutomationEnvironment> automationAccountOperations() {
        return new AbstractAccountOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractBonusOperations<AutomationEnvironment> automationBonusOperations() {
        return new AbstractBonusOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractCustomerOperations<AutomationEnvironment> automationCustomerOperations() {
        return new AbstractCustomerOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractOnBoardingOperations<AutomationEnvironment> automationOnBoardingOperations() {
        return new AbstractOnBoardingOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.getEnvironment();
            }
        };
    }
}
