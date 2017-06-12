package com.betamedia.atom.core.configuration.persistence.impl;

import com.betamedia.atom.core.configuration.persistence.AbstractPersistenceConfig;
import com.betamedia.atom.core.environment.tp.QAEnvironment;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "com.betamedia.atom.core.persistence.repositories.impl.qa"
)
public class QAEnvPersistenceConfig extends AbstractPersistenceConfig<QAEnvironment> implements QAEnvironment{
}