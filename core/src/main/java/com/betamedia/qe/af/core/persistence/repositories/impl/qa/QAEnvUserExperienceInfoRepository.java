package com.betamedia.qe.af.core.persistence.repositories.impl.qa;

import com.betamedia.qe.af.core.environment.tp.QAEnvironment;
import com.betamedia.qe.af.core.persistence.repositories.AbstractUserExperienceInfoRepository;
import org.springframework.stereotype.Component;

/**
 * Created by mbelyaev on 5/19/17.
 */
@Component
public interface QAEnvUserExperienceInfoRepository extends AbstractUserExperienceInfoRepository<QAEnvironment>, QAEnvironment {
}
