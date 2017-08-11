package com.betamedia.atom.core.configuration.environment;

import com.betamedia.atom.core.dsl.operations.impl.*;
import com.betamedia.atom.core.dsl.templates.tp.impl.AbstractTPTemplate;
import com.betamedia.atom.core.dsl.type.EnvironmentType;
import com.betamedia.atom.core.environment.tp.NewAutomationEnvironment;
import com.betamedia.atom.core.fwdataaccess.repository.util.version.AbstractApplicationVersionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * New Automation Environment-specific bean configuration class.
 *
 * @author mbelyaev
 * @since 6/29/17
 */
@Configuration
@Profile("newAutomation")
public class NewAutomationEnvironmentConfig {

    public static EnvironmentType getEnvironment() {
        return EnvironmentType.NEW_AUTOMATION;
    }

    @Bean
    public AbstractApplicationVersionService<NewAutomationEnvironment> newAutomationVersionService() {
        return new AbstractApplicationVersionService<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTPTemplate<NewAutomationEnvironment> newAutomationTPTemplate() {
        return new AbstractTPTemplate<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractAccountOperations<NewAutomationEnvironment> newAutomationAccountOperations() {
        return new AbstractAccountOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractBonusOperations<NewAutomationEnvironment> newAutomationBonusOperations() {
        return new AbstractBonusOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractCustomerOperations<NewAutomationEnvironment> newAutomationCustomerOperations() {
        return new AbstractCustomerOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractOnBoardingOperations<NewAutomationEnvironment> newAutomationOnBoardingOperations() {
        return new AbstractOnBoardingOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractCrmDBOperations<NewAutomationEnvironment> newAutomationCrmDBOperations() {
        return new AbstractCrmDBOperations<NewAutomationEnvironment>() {

            @Override
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

}