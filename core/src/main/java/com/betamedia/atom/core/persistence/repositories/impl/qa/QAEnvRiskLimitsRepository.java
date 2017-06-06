package com.betamedia.atom.core.persistence.repositories.impl.qa;

import com.betamedia.atom.core.persistence.repositories.AbstractRiskLimitsRepository;
import com.betamedia.atom.core.environment.tp.QAEnvironment;
import org.springframework.stereotype.Component;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
@Component
public interface QAEnvRiskLimitsRepository extends AbstractRiskLimitsRepository<QAEnvironment>, QAEnvironment{
}
