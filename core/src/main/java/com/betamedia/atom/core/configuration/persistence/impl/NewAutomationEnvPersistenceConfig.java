package com.betamedia.atom.core.configuration.persistence.impl;

import com.betamedia.atom.core.configuration.persistence.AbstractPersistenceConfig;
import com.betamedia.atom.core.environment.tp.NewAutomationEnvironment;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author mbelyaev
 * @since 6/13/17
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "com.betamedia.atom.core.persistence.repositories.impl.newautomation"
)
public class NewAutomationEnvPersistenceConfig extends AbstractPersistenceConfig<NewAutomationEnvironment> implements NewAutomationEnvironment {
}