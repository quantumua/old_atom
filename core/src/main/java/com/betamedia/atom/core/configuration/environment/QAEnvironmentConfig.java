package com.betamedia.atom.core.configuration.environment;

import com.betamedia.atom.core.api.tp.adapters.impl.AbstractMobileCRMHTTPAdapter;
import com.betamedia.atom.core.api.tp.adapters.impl.AbstractTPCRMHttpAdapter;
import com.betamedia.atom.core.connectors.tp.FWTPConnector;
import com.betamedia.atom.core.connectors.tp.feedgateway.FWFeedGatewayConnector;
import com.betamedia.atom.core.dsl.operations.impl.*;
import com.betamedia.atom.core.dsl.pages.type.EnvironmentType;
import com.betamedia.atom.core.dsl.templates.tp.impl.AbstractTPTemplate;
import com.betamedia.atom.core.environment.tp.QAEnvironment;
import com.betamedia.atom.core.environment.tp.properties.AbstractEnvPropertiesHolder;
import com.betamedia.atom.core.fwdataaccess.repository.util.version.AbstractApplicationVersionService;
import com.betamedia.atom.core.persistence.entities.ContactExtension;
import com.betamedia.atom.core.persistence.repositories.impl.qa.QAEnvContactExtensionRepository;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

/**
 * @author mbelyaev
 * @since 6/29/17
 */
@Configuration
@PropertySource("/env/qa-env.properties")
@EnableJpaRepositories(
        basePackageClasses = {
                ContactExtension.class,
                QAEnvContactExtensionRepository.class
        },
        entityManagerFactoryRef = "qaEntityManagerFactory"
)
public class QAEnvironmentConfig implements QAEnvironment {
    @Bean
    public AbstractEnvPropertiesHolder<QAEnvironment> qaPropertiesHolder() {
        return new AbstractEnvPropertiesHolder<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractApplicationVersionService<QAEnvironment> qaVersionService() {
        return new AbstractApplicationVersionService<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTPTemplate<QAEnvironment> qaTPTemplate() {
        return new AbstractTPTemplate<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTPCRMHttpAdapter<QAEnvironment> qaTPCRMHttpAdapter() {
        return new AbstractTPCRMHttpAdapter<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractMobileCRMHTTPAdapter<QAEnvironment> qaMobileCRMHttpAdapter() {
        return new AbstractMobileCRMHTTPAdapter<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public FWFeedGatewayConnector<QAEnvironment> qaFeedGWConnector() {
        return new FWFeedGatewayConnector<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public FWTPConnector<QAEnvironment> qaFWTPConnector() {
        return new FWTPConnector<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractAccountGroupOperations<QAEnvironment> qaAccountGroupOperations() {
        return new AbstractAccountGroupOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractAccountOperations<QAEnvironment> qaAccountOperations() {
        return new AbstractAccountOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractAssetOperations<QAEnvironment> qaAssetOperations() {
        return new AbstractAssetOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractBonusOperations<QAEnvironment> qaBonusOperations() {
        return new AbstractBonusOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractBrandOperations<QAEnvironment> qaBrandOperations() {
        return new AbstractBrandOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.this.getEnvironment();
            }
        };
    }


    @Bean
    public AbstractFeedOperations<QAEnvironment> qaFeedOperations() {
        return new AbstractFeedOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.this.getEnvironment();
            }
        };
    }


    @Bean
    public AbstractOptionOperations<QAEnvironment> qaOptionOperations() {
        return new AbstractOptionOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractOptionTemplateOperations<QAEnvironment> qaOptionTemplateOperations() {
        return new AbstractOptionTemplateOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractOrderOperations<QAEnvironment> qaOrderOperations() {
        return new AbstractOrderOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractPositionOperations<QAEnvironment> qaPositionOperations() {
        return new AbstractPositionOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractSchedulerOperations<QAEnvironment> qaSchedulerOperations() {
        return new AbstractSchedulerOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTagOperations<QAEnvironment> qaTagOperations() {
        return new AbstractTagOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTimezoneOperations<QAEnvironment> qaTimezoneOperations() {
        return new AbstractTimezoneOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractTradingCalendarOperations<QAEnvironment> qaTradingCalendarOperations() {
        return new AbstractTradingCalendarOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractVolatilityUnitOperations<QAEnvironment> qaVolatilityUnitOperations() {
        return new AbstractVolatilityUnitOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractCustomerOperations<QAEnvironment> qaCustomerOperations() {
        return new AbstractCustomerOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    public AbstractOnBoardingOperations<QAEnvironment> qaOnBoardingOperations() {
        return new AbstractOnBoardingOperations<QAEnvironment>() {

            public EnvironmentType getEnvironment() {
                return QAEnvironmentConfig.this.getEnvironment();
            }
        };
    }

    @Bean
    @Primary
    @ConfigurationProperties("qa.db")
    public DataSource qaDataSource() {
        return qaDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    @Primary
    @ConfigurationProperties("qa.db")
    public DataSourceProperties qaDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean qaEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(qaDataSource())
                .packages(
                        ContactExtension.class,
                        QAEnvContactExtensionRepository.class
                )
                .persistenceUnit("qaPersistenceUnit")
                .build();
    }
}