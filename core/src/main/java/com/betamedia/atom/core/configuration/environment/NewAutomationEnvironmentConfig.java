package com.betamedia.atom.core.configuration.environment;

import com.betamedia.atom.core.api.tp.adapters.impl.AbstractMobileCRMHTTPAdapter;
import com.betamedia.atom.core.api.tp.adapters.impl.AbstractTPCRMHttpAdapter;
import com.betamedia.atom.core.connectors.tp.FWTPConnector;
import com.betamedia.atom.core.connectors.tp.feedgateway.FWFeedGatewayConnector;
import com.betamedia.atom.core.dsl.operations.impl.*;
import com.betamedia.atom.core.dsl.pages.type.EnvironmentType;
import com.betamedia.atom.core.dsl.templates.tp.impl.AbstractTPTemplate;
import com.betamedia.atom.core.environment.tp.NewAutomationEnvironment;
import com.betamedia.atom.core.environment.tp.properties.AbstractEnvPropertiesHolder;
import com.betamedia.atom.core.fwdataaccess.repository.util.version.AbstractApplicationVersionService;
import com.betamedia.atom.core.persistence.entities.ContactExtension;
import com.betamedia.atom.core.persistence.repositories.impl.automation.AutomationEnvContactExtensionRepository;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

/**
 * @author mbelyaev
 * @since 6/29/17
 */
@Configuration
@PropertySource("/env/newAutomation-env.properties")
@EnableJpaRepositories(
        basePackageClasses = {
                ContactExtension.class,
                AutomationEnvContactExtensionRepository.class
        },
        entityManagerFactoryRef = "newAutomationEntityManagerFactory"
)
public class NewAutomationEnvironmentConfig implements NewAutomationEnvironment {
    @Bean
    public AbstractEnvPropertiesHolder<NewAutomationEnvironment> newAutomationPropertiesHolder() {
        return new AbstractEnvPropertiesHolder<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractApplicationVersionService<NewAutomationEnvironment> newAutomationVersionService() {
        return new AbstractApplicationVersionService<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTPTemplate<NewAutomationEnvironment> newAutomationTPTemplate() {
        return new AbstractTPTemplate<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTPCRMHttpAdapter<NewAutomationEnvironment> newAutomationTPCRMHttpAdapter() {
        return new AbstractTPCRMHttpAdapter<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractMobileCRMHTTPAdapter<NewAutomationEnvironment> newAutomationMobileCRMHttpAdapter() {
        return new AbstractMobileCRMHTTPAdapter<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public FWFeedGatewayConnector<NewAutomationEnvironment> newAutomationFeedGWConnector() {
        return new FWFeedGatewayConnector<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public FWTPConnector<NewAutomationEnvironment> newAutomationFWTPConnector() {
        return new FWTPConnector<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractAccountGroupOperations<NewAutomationEnvironment> newAutomationAccountGroupOperations() {
        return new AbstractAccountGroupOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractAccountOperations<NewAutomationEnvironment> newAutomationAccountOperations() {
        return new AbstractAccountOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractAssetOperations<NewAutomationEnvironment> newAutomationAssetOperations() {
        return new AbstractAssetOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractBonusOperations<NewAutomationEnvironment> newAutomationBonusOperations() {
        return new AbstractBonusOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractBrandOperations<NewAutomationEnvironment> newAutomationBrandOperations() {
        return new AbstractBrandOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractFeedOperations<NewAutomationEnvironment> newAutomationFeedOperations() {
        return new AbstractFeedOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractOptionOperations<NewAutomationEnvironment> newAutomationOptionOperations() {
        return new AbstractOptionOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractOptionTemplateOperations<NewAutomationEnvironment> newAutomationOptionTemplateOperations() {
        return new AbstractOptionTemplateOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractOrderOperations<NewAutomationEnvironment> newAutomationOrderOperations() {
        return new AbstractOrderOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractPositionOperations<NewAutomationEnvironment> newAutomationPositionOperations() {
        return new AbstractPositionOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractSchedulerOperations<NewAutomationEnvironment> newAutomationSchedulerOperations() {
        return new AbstractSchedulerOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTagOperations<NewAutomationEnvironment> newAutomationTagOperations() {
        return new AbstractTagOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTimezoneOperations<NewAutomationEnvironment> newAutomationTimezoneOperations() {
        return new AbstractTimezoneOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTradingCalendarOperations<NewAutomationEnvironment> newAutomationTradingCalendarOperations() {
        return new AbstractTradingCalendarOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractVolatilityUnitOperations<NewAutomationEnvironment> newAutomationVolatilityUnitOperations() {
        return new AbstractVolatilityUnitOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractCustomerOperations<NewAutomationEnvironment> newAutomationCustomerOperations() {
        return new AbstractCustomerOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractOnBoardingOperations<NewAutomationEnvironment> newAutomationOnBoardingOperations() {
        return new AbstractOnBoardingOperations<NewAutomationEnvironment>() {
            public EnvironmentType getEnvironment() {
                return NewAutomationEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    @ConfigurationProperties("newAutomation.db")
    public DataSource newAutomationDataSource() {
        return newAutomationDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    @ConfigurationProperties("newAutomation.db")
    public DataSourceProperties newAutomationDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean newAutomationEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(newAutomationDataSource())
                .packages(
                        ContactExtension.class,
                        AutomationEnvContactExtensionRepository.class)
                .persistenceUnit("newAutomationPersistenceUnit")
                .build();
    }
}