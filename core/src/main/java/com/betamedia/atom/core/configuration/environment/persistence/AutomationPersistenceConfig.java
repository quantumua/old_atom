package com.betamedia.atom.core.configuration.environment.persistence;

import com.betamedia.atom.core.configuration.StubAutoConfiguration;
import com.betamedia.atom.core.persistence.entities.ContactExtension;
import com.betamedia.atom.core.persistence.entities.WithdrawalRequest;
import com.betamedia.atom.core.persistence.repositories.impl.automation.AutomationEnvContactExtensionRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

/**
 * Automation Environment-specific repository configuration class.
 *
 * @author mbelyaev
 * @since 7/4/17
 */
@Configuration
@Profile("automation")
@EnableJpaRepositories(
        basePackageClasses = {
                ContactExtension.class,
                AutomationEnvContactExtensionRepository.class,
                WithdrawalRequest.class
        },
        entityManagerFactoryRef = "automationEntityManagerFactory"
)
@ConditionalOnProperty(name = StubAutoConfiguration.DB_ENABLED_PROPERTY, matchIfMissing = true)
public class AutomationPersistenceConfig {
    @Autowired
    private DataSourceProperties automationDataSourceProperties;

    @Bean
    public DataSource automationDataSource() {
        return automationDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean automationEntityManagerFactory(JpaProperties jpaProperties, ObjectProvider<PersistenceUnitManager> persistenceUnitManager) {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), jpaProperties.getProperties(), persistenceUnitManager.getIfAvailable())
                .dataSource(automationDataSource())
                .packages(
                        ContactExtension.class,
                        AutomationEnvContactExtensionRepository.class,
                        WithdrawalRequest.class)
                        
                .persistenceUnit("automationPersistenceUnit")
                .build();
    }
}
