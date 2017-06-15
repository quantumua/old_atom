package com.betamedia.atom.core.persistence.repositories.impl.newautomation;

import com.betamedia.atom.core.environment.tp.NewAutomationEnvironment;
import com.betamedia.atom.core.persistence.repositories.AbstractRiskLimitsRepository;
import org.springframework.stereotype.Component;

/**
 * @author mbelyaev
 * @since 6/13/17
 */
@Component
public interface NewAutomationEnvRiskLimitsRepository extends AbstractRiskLimitsRepository<NewAutomationEnvironment>, NewAutomationEnvironment {
}
