package com.betamedia.atom.core.configuration.environment;

import com.betamedia.atom.core.dsl.operations.impl.*;
import com.betamedia.atom.core.dsl.pages.type.EnvironmentType;
import com.betamedia.atom.core.dsl.templates.tp.impl.AbstractTPTemplate;
import com.betamedia.atom.core.environment.tp.QAEnvironment;
import com.betamedia.atom.core.fwdataaccess.repository.util.version.AbstractApplicationVersionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * QA Environment-specific bean configuration class.
 *
 * @author mbelyaev
 * @since 6/29/17
 */
@Configuration
@Profile("qa")
public class QAEnvironmentConfig {

    public static EnvironmentType getEnvironment() {
        return EnvironmentType.QA;
    }

    @Bean
    public AbstractApplicationVersionService<QAEnvironment> qaVersionService() {
        return new AbstractApplicationVersionService<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTPTemplate<QAEnvironment> qaTPTemplate() {
        return new AbstractTPTemplate<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractAccountGroupOperations<QAEnvironment> qaAccountGroupOperations() {
        return new AbstractAccountGroupOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractAccountOperations<QAEnvironment> qaAccountOperations() {
        return new AbstractAccountOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractAssetOperations<QAEnvironment> qaAssetOperations() {
        return new AbstractAssetOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractBonusOperations<QAEnvironment> qaBonusOperations() {
        return new AbstractBonusOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractBrandOperations<QAEnvironment> qaBrandOperations() {
        return new AbstractBrandOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractFeedOperations<QAEnvironment> qaFeedOperations() {
        return new AbstractFeedOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractOptionOperations<QAEnvironment> qaOptionOperations() {
        return new AbstractOptionOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractOptionTemplateOperations<QAEnvironment> qaOptionTemplateOperations() {
        return new AbstractOptionTemplateOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractOrderOperations<QAEnvironment> qaOrderOperations() {
        return new AbstractOrderOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractPositionOperations<QAEnvironment> qaPositionOperations() {
        return new AbstractPositionOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractSchedulerOperations<QAEnvironment> qaSchedulerOperations() {
        return new AbstractSchedulerOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTagOperations<QAEnvironment> qaTagOperations() {
        return new AbstractTagOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTimezoneOperations<QAEnvironment> qaTimezoneOperations() {
        return new AbstractTimezoneOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTradingCalendarOperations<QAEnvironment> qaTradingCalendarOperations() {
        return new AbstractTradingCalendarOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractVolatilityUnitOperations<QAEnvironment> qaVolatilityUnitOperations() {
        return new AbstractVolatilityUnitOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractCustomerOperations<QAEnvironment> qaCustomerOperations() {
        return new AbstractCustomerOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractOnBoardingOperations<QAEnvironment> qaOnBoardingOperations() {
        return new AbstractOnBoardingOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.getEnvironment();
            }
        };
    }

}