package com.betamedia.atom.core.persistence.repositories;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.atom.core.persistence.entities.CreditCarddepositExtensionBase;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Leonid Artemiev
 * @since 8/28/17
 */
public interface AbstractCreditCardDepositExtensionBase <T extends EnvironmentDependent> extends JpaRepository<CreditCarddepositExtensionBase, String> {
    CreditCarddepositExtensionBase findByCustomerId(String customerId);
}
