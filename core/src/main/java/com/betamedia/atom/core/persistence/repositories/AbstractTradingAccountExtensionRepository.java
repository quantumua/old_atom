package com.betamedia.atom.core.persistence.repositories;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.atom.core.persistence.entities.TradingAccountExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
public interface AbstractTradingAccountExtensionRepository<T extends EnvironmentDependent> extends JpaRepository<TradingAccountExtension, String> {
    List<TradingAccountExtension> findByCustomerId(String customerId);
}
