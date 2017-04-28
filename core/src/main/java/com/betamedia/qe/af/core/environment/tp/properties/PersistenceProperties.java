package com.betamedia.qe.af.core.environment.tp.properties;

import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
public interface PersistenceProperties<T extends EnvironmentDependent> {

    String getDriverClassName();

    String getDatabaseUrl();

    String getDatabasePassword();

    String getDatabaseUsername();
}
