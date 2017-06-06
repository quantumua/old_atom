package com.betamedia.qe.af.core.persistence.repositories;

import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;
import com.betamedia.qe.af.core.persistence.entities.TradingAccountExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
public interface AbstractTradingAccountExtensionRepository<T extends EnvironmentDependent> extends JpaRepository<TradingAccountExtension, String>, EnvironmentDependent {
    List<TradingAccountExtension> findByCustomerId(String customerId);
}
