package com.betamedia.atom.core.persistence.repositories;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.atom.core.persistence.entities.RegulationAnswerExtensionBase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Created by vsnigur on 9/7/17.
 */
public interface AbstractRegulationAnswerExtensionBase <T extends EnvironmentDependent> extends JpaRepository<RegulationAnswerExtensionBase, String> {

    List<RegulationAnswerExtensionBase> findByCustomerId(String customerId);

}
