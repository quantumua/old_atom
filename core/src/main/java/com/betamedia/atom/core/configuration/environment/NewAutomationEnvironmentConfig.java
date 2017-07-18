package com.betamedia.atom.core.configuration.environment;

import com.betamedia.atom.core.dsl.operations.impl.*;
import com.betamedia.atom.core.dsl.pages.type.EnvironmentType;
import com.betamedia.atom.core.dsl.templates.tp.impl.AbstractTPTemplate;
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
    public AbstractAccountGroupOperations<NewAutomationEnvironment> newAutomationAccountGroupOperations() {
        return new AbstractAccountGroupOperations<NewAutomationEnvironment>() {
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
    public AbstractAssetOperations<NewAutomationEnvironment> newAutomationAssetOperations() {
        return new AbstractAssetOperations<NewAutomationEnvironment>() {
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
    public AbstractBrandOperations<NewAutomationEnvironment> newAutomationBrandOperations() {
        return new AbstractBrandOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractFeedOperations<NewAutomationEnvironment> newAutomationFeedOperations() {
        return new AbstractFeedOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractOptionOperations<NewAutomationEnvironment> newAutomationOptionOperations() {
        return new AbstractOptionOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractOptionTemplateOperations<NewAutomationEnvironment> newAutomationOptionTemplateOperations() {
        return new AbstractOptionTemplateOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractOrderOperations<NewAutomationEnvironment> newAutomationOrderOperations() {
        return new AbstractOrderOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractPositionOperations<NewAutomationEnvironment> newAutomationPositionOperations() {
        return new AbstractPositionOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractSchedulerOperations<NewAutomationEnvironment> newAutomationSchedulerOperations() {
        return new AbstractSchedulerOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTagOperations<NewAutomationEnvironment> newAutomationTagOperations() {
        return new AbstractTagOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTimezoneOperations<NewAutomationEnvironment> newAutomationTimezoneOperations() {
        return new AbstractTimezoneOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTradingCalendarOperations<NewAutomationEnvironment> newAutomationTradingCalendarOperations() {
        return new AbstractTradingCalendarOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractVolatilityUnitOperations<NewAutomationEnvironment> newAutomationVolatilityUnitOperations() {
        return new AbstractVolatilityUnitOperations<NewAutomationEnvironment>() {
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

}