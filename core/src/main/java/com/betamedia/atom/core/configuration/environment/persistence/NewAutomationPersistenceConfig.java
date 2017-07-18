package com.betamedia.atom.core.configuration.environment.persistence;

import com.betamedia.atom.core.configuration.PrimaryDataSourcePostProcessor;
import com.betamedia.atom.core.configuration.environment.NewAutomationEnvironmentConfig;
import com.betamedia.atom.core.persistence.entities.ContactBase;
import com.betamedia.atom.core.persistence.entities.ContactExtension;
import com.betamedia.atom.core.persistence.repositories.impl.newautomation.NewAutomationEnvTrackingInfoRepository;
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
 * New Automation Environment-specific repository configuration class.
 * Please take care to prefix bean names with <code>newAutomation</code> for post-processing purposes.
 *
 * @author mbelyaev
 * @see PrimaryDataSourcePostProcessor
 * @since 7/4/17
 */
@Configuration
@EnableJpaRepositories(
        basePackageClasses = {
                ContactExtension.class,
                NewAutomationEnvTrackingInfoRepository.class
        },
        entityManagerFactoryRef = "newAutomationEntityManagerFactory"
)
@ConditionalOnProperty(name = DB_ENABLED_PROPERTY, matchIfMissing = true)
@ConditionalOnBean(NewAutomationEnvironmentConfig.class)
public class NewAutomationPersistenceConfig {
    @Autowired
    private DataSourceProperties newAutomationDataSourceProperties;

    @Bean
    public DataSource newAutomationDataSource() {
        return newAutomationDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean newAutomationEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(newAutomationDataSource())
                .packages(
                        ContactBase.class,
                        ContactExtension.class,
                        NewAutomationEnvTrackingInfoRepository.class)
                .persistenceUnit("newAutomationPersistenceUnit")
                .build();
    }
}
