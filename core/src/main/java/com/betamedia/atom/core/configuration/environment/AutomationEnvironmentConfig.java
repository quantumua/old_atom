package com.betamedia.atom.core.configuration.environment;

import com.betamedia.atom.core.dsl.operations.impl.*;
import com.betamedia.atom.core.dsl.pages.type.EnvironmentType;
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
    public AbstractAccountGroupOperations<AutomationEnvironment> automationAccountGroupOperations() {
        return new AbstractAccountGroupOperations<AutomationEnvironment>() {

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
    public AbstractAssetOperations<AutomationEnvironment> automationAssetOperations() {
        return new AbstractAssetOperations<AutomationEnvironment>() {

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
    public AbstractBrandOperations<AutomationEnvironment> automationBrandOperations() {
        return new AbstractBrandOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractFeedOperations<AutomationEnvironment> automationFeedOperations() {
        return new AbstractFeedOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractOptionOperations<AutomationEnvironment> automationOptionOperations() {
        return new AbstractOptionOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractOptionTemplateOperations<AutomationEnvironment> automationOptionTemplateOperations() {
        return new AbstractOptionTemplateOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractOrderOperations<AutomationEnvironment> automationOrderOperations() {
        return new AbstractOrderOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractPositionOperations<AutomationEnvironment> automationPositionOperations() {
        return new AbstractPositionOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractSchedulerOperations<AutomationEnvironment> automationSchedulerOperations() {
        return new AbstractSchedulerOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTagOperations<AutomationEnvironment> automationTagOperations() {
        return new AbstractTagOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTimezoneOperations<AutomationEnvironment> automationTimezoneOperations() {
        return new AbstractTimezoneOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTradingCalendarOperations<AutomationEnvironment> automationTradingCalendarOperations() {
        return new AbstractTradingCalendarOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractVolatilityUnitOperations<AutomationEnvironment> automationVolatilityUnitOperations() {
        return new AbstractVolatilityUnitOperations<AutomationEnvironment>() {

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
