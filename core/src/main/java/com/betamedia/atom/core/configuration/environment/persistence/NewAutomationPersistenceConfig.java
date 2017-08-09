package com.betamedia.atom.core.configuration.environment.persistence;

import com.betamedia.atom.core.persistence.entities.ContactExtension;
import com.betamedia.atom.core.persistence.repositories.impl.newautomation.NewAutomationEnvContactExtensionRepository;
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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

import static com.betamedia.atom.core.configuration.StubConfiguration.DB_ENABLED_PROPERTY;

/**
 * New Automation Environment-specific repository configuration class.
 *
 * @author mbelyaev
 * @since 7/4/17
 */
@Configuration
@Profile("newAutomation")
@EnableJpaRepositories(
        basePackageClasses = {
                ContactExtension.class,
                NewAutomationEnvContactExtensionRepository.class},
        entityManagerFactoryRef = "newAutomationEntityManagerFactory",
        transactionManagerRef = "newAutomationTransactionManager"
)
@ConditionalOnProperty(name = DB_ENABLED_PROPERTY, matchIfMissing = true)
public class NewAutomationPersistenceConfig {
    @Autowired
    private DataSourceProperties newAutomationDataSourceProperties;

    @Bean
    public DataSource newAutomationDataSource() {
        return newAutomationDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean newAutomationEntityManagerFactory(JpaProperties jpaProperties, ObjectProvider<PersistenceUnitManager> persistenceUnitManager) {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), jpaProperties.getProperties(), persistenceUnitManager.getIfAvailable())
                .dataSource(newAutomationDataSource())
                .packages(
                        ContactExtension.class,
                        NewAutomationEnvContactExtensionRepository.class)
                .persistenceUnit("newAutomationPersistenceUnit")
                .build();
    }

    @Bean
    public DataSourceTransactionManager newAutomationTransactionManager() {
        return new DataSourceTransactionManager(newAutomationDataSource());
    }

}
