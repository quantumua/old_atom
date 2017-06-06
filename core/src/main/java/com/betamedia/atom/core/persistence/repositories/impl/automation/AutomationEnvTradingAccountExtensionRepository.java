package com.betamedia.atom.core.persistence.repositories.impl.automation;

import com.betamedia.atom.core.environment.tp.AutomationEnvironment;
import com.betamedia.atom.core.persistence.repositories.AbstractTradingAccountExtensionRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
@Component
public interface AutomationEnvTradingAccountExtensionRepository extends AbstractTradingAccountExtensionRepository<AutomationEnvironment>, AutomationEnvironment {
}
