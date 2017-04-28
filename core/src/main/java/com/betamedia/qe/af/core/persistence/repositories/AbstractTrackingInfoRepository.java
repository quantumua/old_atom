package com.betamedia.qe.af.core.persistence.repositories;

import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;
import com.betamedia.qe.af.core.persistence.entities.TrackingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
public interface AbstractTrackingInfoRepository <T extends EnvironmentDependent> extends JpaRepository<TrackingInfo, String>, EnvironmentDependent {

}
