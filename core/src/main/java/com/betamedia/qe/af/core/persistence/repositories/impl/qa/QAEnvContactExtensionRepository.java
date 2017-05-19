package com.betamedia.qe.af.core.persistence.repositories.impl.qa;

import com.betamedia.qe.af.core.environment.tp.QAEnvironment;
import com.betamedia.qe.af.core.persistence.repositories.AbstractContactExtensionRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
@Component
public interface QAEnvContactExtensionRepository extends AbstractContactExtensionRepository<QAEnvironment>, QAEnvironment{
}
