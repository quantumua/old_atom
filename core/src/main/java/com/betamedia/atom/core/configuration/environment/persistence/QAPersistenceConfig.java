package com.betamedia.atom.core.configuration.environment.persistence;

import com.betamedia.atom.core.persistence.entities.ContactExtension;
import com.betamedia.atom.core.persistence.repositories.impl.qa.QAEnvContactExtensionRepository;
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
 * QA Environment-specific repository configuration class.
 *
 * @author mbelyaev
 * @since 7/4/17
 */
@Configuration
@Profile("qa")
@EnableJpaRepositories(
        basePackageClasses = {
                ContactExtension.class,
                QAEnvContactExtensionRepository.class},
        entityManagerFactoryRef = "qaEntityManagerFactory",
        transactionManagerRef = "qaTransactionManager"
)
@ConditionalOnProperty(name = DB_ENABLED_PROPERTY, matchIfMissing = true)
public class QAPersistenceConfig {
    @Autowired
    private DataSourceProperties qaDataSourceProperties;

    @Bean
    public DataSource qaDataSource() {
        return qaDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean qaEntityManagerFactory(JpaProperties jpaProperties, ObjectProvider<PersistenceUnitManager> persistenceUnitManager) {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), jpaProperties.getProperties(), persistenceUnitManager.getIfAvailable())
                .dataSource(qaDataSource())
                .packages(
                        ContactExtension.class,
                        QAEnvContactExtensionRepository.class)
                .persistenceUnit("qaPersistenceUnit")
                .build();
    }

    @Bean
    public DataSourceTransactionManager qaTransactionManager() {
        return new DataSourceTransactionManager(qaDataSource());
    }

}
