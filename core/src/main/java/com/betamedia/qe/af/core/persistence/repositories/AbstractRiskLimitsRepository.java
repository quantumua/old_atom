package com.betamedia.qe.af.core.persistence.repositories;

import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;
import com.betamedia.qe.af.core.persistence.entities.RiskLimits;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
public interface AbstractRiskLimitsRepository<T extends EnvironmentDependent> extends JpaRepository<RiskLimits, String>, EnvironmentDependent {

}