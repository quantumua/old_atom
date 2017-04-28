package com.betamedia.qe.af.core.persistence.repositories.impl.qa;

import com.betamedia.qe.af.core.environment.tp.QAEnvironment;
import com.betamedia.qe.af.core.persistence.repositories.AbstractTrackingInfoRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
@Component
public interface QAEnvTrackingInfoRepository extends AbstractTrackingInfoRepository<QAEnvironment>, QAEnvironment{
}
