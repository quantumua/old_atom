package com.betamedia.qe.af.core.persistence.repositories.impl.automation;

import com.betamedia.qe.af.core.environment.tp.AutomationEnvironment;
import com.betamedia.qe.af.core.persistence.repositories.AbstractTrackingInfoRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
@Component
public interface AutomationEnvTrackingInfoRepository extends AbstractTrackingInfoRepository<AutomationEnvironment>, AutomationEnvironment {
}
