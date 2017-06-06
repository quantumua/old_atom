package com.betamedia.atom.core.persistence.repositories.impl.qa;

import com.betamedia.atom.core.environment.tp.QAEnvironment;
import com.betamedia.atom.core.persistence.repositories.AbstractTradingAccountExtensionRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
@Component
public interface QAEnvTradingAccountExtensionRepository extends AbstractTradingAccountExtensionRepository<QAEnvironment>, QAEnvironment {
}
