package com.betamedia.atom.core.configuration.environment.persistence;

import com.betamedia.atom.core.configuration.PrimaryDataSourcePostProcessor;
import com.betamedia.atom.core.configuration.environment.QAEnvironmentConfig;
import com.betamedia.atom.core.persistence.entities.ContactBase;
import com.betamedia.atom.core.persistence.entities.ContactExtension;
import com.betamedia.atom.core.persistence.repositories.impl.qa.QAEnvContactExtensionRepository;
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
 * QA Environment-specific repository configuration class.
 * Please take care to prefix bean names with <code>qa</code> for post-processing purposes.
 *
 * @author mbelyaev
 * @see PrimaryDataSourcePostProcessor
 * @since 7/4/17
 */
@Configuration
@EnableJpaRepositories(
        basePackageClasses = {
                ContactExtension.class,
                QAEnvContactExtensionRepository.class
        },
        entityManagerFactoryRef = "qaEntityManagerFactory"
)
@ConditionalOnProperty(name = DB_ENABLED_PROPERTY, matchIfMissing = true)
@ConditionalOnBean(QAEnvironmentConfig.class)
public class QAPersistenceConfig {
    @Autowired
    private DataSourceProperties qaDataSourceProperties;

    @Bean
    public DataSource qaDataSource() {
        return qaDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean qaEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(qaDataSource())
                .packages(
                        ContactBase.class,
                        ContactExtension.class,
                        QAEnvContactExtensionRepository.class
                )
                .persistenceUnit("qaPersistenceUnit")
                .build();
    }
}
