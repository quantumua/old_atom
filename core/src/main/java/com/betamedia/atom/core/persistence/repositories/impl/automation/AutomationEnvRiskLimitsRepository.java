package com.betamedia.atom.core.persistence.repositories.impl.automation;

import com.betamedia.atom.core.persistence.repositories.AbstractRiskLimitsRepository;
import com.betamedia.atom.core.environment.tp.AutomationEnvironment;
import org.springframework.stereotype.Component;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
@Component
public interface AutomationEnvRiskLimitsRepository extends AbstractRiskLimitsRepository<AutomationEnvironment>, AutomationEnvironment {
}
