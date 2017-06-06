package com.betamedia.atom.core.environment.tp.properties;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
public interface PersistenceProperties<T extends EnvironmentDependent> {

    String getDriverClassName();

    String getDatabaseUrl();

    String getDatabasePassword();

    String getDatabaseUsername();
}
