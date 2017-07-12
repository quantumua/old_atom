package com.betamedia.atom.core.persistence.repositories;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.atom.core.persistence.entities.ConnectionRoleBase;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vsnigur on 7/11/17.
 */
public interface AbstractConnectionRoleBaseRepository<T extends EnvironmentDependent> extends JpaRepository<ConnectionRoleBase, String> {
    ConnectionRoleBase findByConnectionRoleId(String connectionRoleId);
}
