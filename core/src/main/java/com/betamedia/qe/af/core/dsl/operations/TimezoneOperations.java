package com.betamedia.qe.af.core.dsl.operations;

import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;
import com.betamedia.tp.api.model.scheduling.Timezone;

/**
 * Created by mbelyaev on 3/23/17.
 */
public interface TimezoneOperations<T extends EnvironmentDependent> extends EnvironmentDependent {
    Timezone get();

    Timezone get(String id);
}
