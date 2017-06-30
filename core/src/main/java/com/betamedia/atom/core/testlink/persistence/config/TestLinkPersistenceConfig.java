package com.betamedia.atom.core.testlink.persistence.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by sergeyg on 30.06.17.
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "com.betamedia.atom.core.testlink.persistence.repositories",
        entityManagerFactoryRef = "bla",
        transactionManagerRef = "testLinkTransactionManager"
)
@PropertySource("classpath:testlink.properties")
public class TestLinkPersistenceConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean testLinkEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(testLinkDataSource());
        em.setPackagesToScan("com.betamedia.atom.core.testlink.persistence.entities");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        return em;
    }

    @Bean
    public PlatformTransactionManager testLinkTransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                testLinkEntityManager().getObject());
        return transactionManager;
    }

    @Primary
    @ConfigurationProperties(prefix = "testlink.db")
    @Bean
    public DataSource testLinkDataSource() {
        return DataSourceBuilder.create().build();
    }

}
