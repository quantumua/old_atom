package com.betamedia.atom.core.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.atom.core.persistence.entities.ContactBase;

/**
 * Created by Leonid.a on 7/11/17.
 */
public interface AbstractContactBaseRepository<T extends EnvironmentDependent> extends JpaRepository<ContactBase, String> {
	}
