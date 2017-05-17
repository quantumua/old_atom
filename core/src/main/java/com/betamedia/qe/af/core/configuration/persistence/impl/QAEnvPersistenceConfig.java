package com.betamedia.qe.af.core.configuration.persistence.impl;

import com.betamedia.qe.af.core.configuration.persistence.AbstractPersistenceConfig;
import com.betamedia.qe.af.core.environment.tp.QAEnvironment;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "com.betamedia.qe.af.core.persistence.repositories.impl.qa",
        entityManagerFactoryRef = "entityManager",
        transactionManagerRef = "transactionManager"
)
public class QAEnvPersistenceConfig extends AbstractPersistenceConfig<QAEnvironment> implements QAEnvironment{
}
