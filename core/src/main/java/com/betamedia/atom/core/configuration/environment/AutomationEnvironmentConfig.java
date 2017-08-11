package com.betamedia.atom.core.configuration.environment;

import com.betamedia.atom.core.dsl.operations.impl.*;
import com.betamedia.atom.core.dsl.type.EnvironmentType;
import com.betamedia.atom.core.dsl.templates.tp.impl.AbstractTPTemplate;
import com.betamedia.atom.core.environment.tp.AutomationEnvironment;
import com.betamedia.atom.core.fwdataaccess.repository.util.version.AbstractApplicationVersionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Automation Environment-specific bean configuration class.
 *
 * @author mbelyaev
 * @since 6/29/17
 */
@Configuration
@Profile("automation")
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

    @Bean
    public AbstractCrmDBOperations<AutomationEnvironment> automationCrmDBOperations() {
        return new AbstractCrmDBOperations<AutomationEnvironment>() {

            @Override
            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.getEnvironment();
            }
        };
    }
}
