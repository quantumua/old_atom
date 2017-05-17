package com.betamedia.qe.af.core.configuration.persistence.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by Oleksandr Losiev on 5/17/17.
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "com.betamedia.qe.af.core.persistence.repositories.impl.testlink",
        entityManagerFactoryRef = "testLinkEntityManager",
        transactionManagerRef = "testLinkTransactionManager"
)
public class TestLinkPersistenceConfig {

    @Value("${testlink.db.url}")
    private String url;
    @Value("${testlink.db.driverClassName}")
    private String driverClassName;
    @Value("${testlink.db.user}")
    private String user;
    @Value("${testlink.db.password}")
    private String password;

    @Bean
    public DataSource testLinkDataSource() {
        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean testLinkEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(testLinkDataSource());
        em.setPackagesToScan("com.betamedia.qe.af.core.persistence.entities");

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
}
