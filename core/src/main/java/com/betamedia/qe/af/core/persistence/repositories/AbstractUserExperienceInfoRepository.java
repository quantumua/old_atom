package com.betamedia.qe.af.core.persistence.repositories;

import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;
import com.betamedia.qe.af.core.persistence.entities.ContactExtensionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mbelyaev on 5/19/17.
 */
public interface AbstractUserExperienceInfoRepository<T extends EnvironmentDependent> extends JpaRepository<ContactExtensionInfo, String>, EnvironmentDependent {
    ContactExtensionInfo findByUsername(String username);
}
