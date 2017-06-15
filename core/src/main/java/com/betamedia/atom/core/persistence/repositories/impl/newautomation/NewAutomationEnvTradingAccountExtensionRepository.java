package com.betamedia.atom.core.persistence.repositories.impl.newautomation;

import com.betamedia.atom.core.environment.tp.NewAutomationEnvironment;
import com.betamedia.atom.core.persistence.repositories.AbstractTradingAccountExtensionRepository;
import org.springframework.stereotype.Component;

/**
 * @author mbelyaev
 * @since 6/13/17
 */
@Component
public interface NewAutomationEnvTradingAccountExtensionRepository extends AbstractTradingAccountExtensionRepository<NewAutomationEnvironment>, NewAutomationEnvironment {
}
