package com.betamedia.qe.af.core.persistence.repositories.impl.automation;

import com.betamedia.qe.af.core.environment.tp.AutomationEnvironment;
import com.betamedia.qe.af.core.persistence.repositories.AbstractContactExtensionRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
@Component
public interface AutomationEnvContactExtensionRepository extends AbstractContactExtensionRepository<AutomationEnvironment>, AutomationEnvironment {
}
