package com.betamedia.atom.core.configuration.persistence;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.atom.core.environment.tp.properties.PersistenceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
public abstract class AbstractPersistenceConfig <T extends EnvironmentDependent> implements EnvironmentDependent {

    @Autowired
    PersistenceProperties<T> persistenceProperties;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(persistenceProperties.getDriverClassName());
        dataSource.setUrl(persistenceProperties.getDatabaseUrl());
        dataSource.setUsername(persistenceProperties.getDatabaseUsername());
        dataSource.setPassword(persistenceProperties.getDatabasePassword());

        return dataSource;
    }
}