package com.betamedia.atom.core.configuration.environment;

import com.betamedia.atom.core.api.tp.adapters.impl.AbstractMobileCRMHTTPAdapter;
import com.betamedia.atom.core.api.tp.adapters.impl.AbstractTPCRMHttpAdapter;
import com.betamedia.atom.core.connectors.tp.FWTPConnector;
import com.betamedia.atom.core.connectors.tp.feedgateway.FWFeedGatewayConnector;
import com.betamedia.atom.core.dsl.operations.impl.*;
import com.betamedia.atom.core.dsl.pages.type.EnvironmentType;
import com.betamedia.atom.core.dsl.templates.tp.impl.AbstractTPTemplate;
import com.betamedia.atom.core.environment.tp.AutomationEnvironment;
import com.betamedia.atom.core.fwdataaccess.repository.util.version.AbstractApplicationVersionService;
import com.betamedia.atom.core.persistence.entities.ContactExtension;
import com.betamedia.atom.core.persistence.repositories.impl.automation.AutomationEnvContactExtensionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

/**
 * @author mbelyaev
 * @since 6/29/17
 */
@Configuration
@Profile("automation")
public class AutomationEnvironmentConfig implements AutomationEnvironment {

    @Bean
    public AbstractApplicationVersionService<AutomationEnvironment> automationVersionService() {
        return new AbstractApplicationVersionService<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTPTemplate<AutomationEnvironment> automationTPTemplate() {
        return new AbstractTPTemplate<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTPCRMHttpAdapter<AutomationEnvironment> automationTPCRMHttpAdapter() {
        return new AbstractTPCRMHttpAdapter<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractMobileCRMHTTPAdapter<AutomationEnvironment> automationMobileCRMHttpAdapter() {
        return new AbstractMobileCRMHTTPAdapter<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    @ConfigurationProperties("feed")
    public FWFeedGatewayConnector<AutomationEnvironment> automationFeedGWConnector() {
        return new FWFeedGatewayConnector<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public FWTPConnector<AutomationEnvironment> automationFWTPConnector() {
        return new FWTPConnector<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractAccountGroupOperations<AutomationEnvironment> automationAccountGroupOperations() {
        return new AbstractAccountGroupOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractAccountOperations<AutomationEnvironment> automationAccountOperations() {
        return new AbstractAccountOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractAssetOperations<AutomationEnvironment> automationAssetOperations() {
        return new AbstractAssetOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractBonusOperations<AutomationEnvironment> automationBonusOperations() {
        return new AbstractBonusOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractBrandOperations<AutomationEnvironment> automationBrandOperations() {
        return new AbstractBrandOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }


    @Bean
    public AbstractFeedOperations<AutomationEnvironment> automationFeedOperations() {
        return new AbstractFeedOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }


    @Bean
    public AbstractOptionOperations<AutomationEnvironment> automationOptionOperations() {
        return new AbstractOptionOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractOptionTemplateOperations<AutomationEnvironment> automationOptionTemplateOperations() {
        return new AbstractOptionTemplateOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractOrderOperations<AutomationEnvironment> automationOrderOperations() {
        return new AbstractOrderOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractPositionOperations<AutomationEnvironment> automationPositionOperations() {
        return new AbstractPositionOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractSchedulerOperations<AutomationEnvironment> automationSchedulerOperations() {
        return new AbstractSchedulerOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTagOperations<AutomationEnvironment> automationTagOperations() {
        return new AbstractTagOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTimezoneOperations<AutomationEnvironment> automationTimezoneOperations() {
        return new AbstractTimezoneOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTradingCalendarOperations<AutomationEnvironment> automationTradingCalendarOperations() {
        return new AbstractTradingCalendarOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractVolatilityUnitOperations<AutomationEnvironment> automationVolatilityUnitOperations() {
        return new AbstractVolatilityUnitOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractCustomerOperations<AutomationEnvironment> automationCustomerOperations() {
        return new AbstractCustomerOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractOnBoardingOperations<AutomationEnvironment> automationOnBoardingOperations() {
        return new AbstractOnBoardingOperations<AutomationEnvironment>() {

            public EnvironmentType getEnvironment() {
                return AutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

}
