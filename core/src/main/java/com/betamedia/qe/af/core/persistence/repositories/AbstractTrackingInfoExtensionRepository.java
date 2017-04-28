package com.betamedia.qe.af.core.persistence.repositories;

import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;
import com.betamedia.qe.af.core.persistence.entities.TrackingInfoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
public interface AbstractTrackingInfoExtensionRepository <T extends EnvironmentDependent> extends JpaRepository<TrackingInfoExtension, String>, EnvironmentDependent {
    List<TrackingInfoExtension> findByCustomerIdOrderByCookieCreationTimeDesc(String customerId);
    List<TrackingInfoExtension> findByKeywordOrderByCookieCreationTimeDesc(String keyword);
}
