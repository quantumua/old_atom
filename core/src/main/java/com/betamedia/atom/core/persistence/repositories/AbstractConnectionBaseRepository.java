package com.betamedia.atom.core.persistence.repositories;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.atom.core.persistence.entities.ConnectionBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by vsnigur on 7/11/17.
 */
public interface AbstractConnectionBaseRepository<T extends EnvironmentDependent> extends JpaRepository<ConnectionBase, String> {
    List<ConnectionBase> findByRecord1Id(String userId);
    List<ConnectionBase> findByRecord2Id(String userId);
}
