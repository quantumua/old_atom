package com.betamedia.atom.core.configuration.environment.persistence;

import com.betamedia.atom.core.configuration.PrimaryDataSourcePostProcessor;
import com.betamedia.atom.core.configuration.environment.AutomationEnvironmentConfig;
import com.betamedia.atom.core.persistence.entities.ContactExtension;
import com.betamedia.atom.core.persistence.repositories.impl.automation.AutomationEnvContactExtensionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

import static com.betamedia.atom.core.configuration.environment.persistence.StubConfig.DB_ENABLED_PROPERTY;

/**
 * Automation Environment-specific repository configuration class.
 * Please take care to prefix bean names with <code>automation</code> for post-processing purposes.
 *
 * @author mbelyaev
 * @see PrimaryDataSourcePostProcessor
 * @since 7/4/17
 */
@Configuration
@EnableJpaRepositories(
        basePackageClasses = {
                ContactExtension.class,
                AutomationEnvContactExtensionRepository.class
        },
        entityManagerFactoryRef = "automationEntityManagerFactory"
)
@ConditionalOnProperty(name = DB_ENABLED_PROPERTY, matchIfMissing = true)
@ConditionalOnBean(AutomationEnvironmentConfig.class)
public class AutomationPersistenceConfig {
    @Autowired
    private DataSourceProperties automationDataSourceProperties;

    @Bean
    public DataSource automationDataSource() {
        return automationDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean automationEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(automationDataSource())
                .packages(
                        ContactExtension.class,
                        AutomationEnvContactExtensionRepository.class)
                .persistenceUnit("automationPersistenceUnit")
                .build();
    }
}
