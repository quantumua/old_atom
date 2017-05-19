package com.betamedia.qe.af.core.persistence.repositories.impl.automation;

import com.betamedia.qe.af.core.environment.tp.AutomationEnvironment;
import com.betamedia.qe.af.core.persistence.repositories.AbstractUserExperienceInfoRepository;
import org.springframework.stereotype.Component;

/**
 * Created by mbelyaev on 5/19/17.
 */
@Component
public interface AutomationEnvUserExperienceInfoRepository extends AbstractUserExperienceInfoRepository<AutomationEnvironment>, AutomationEnvironment {
}
