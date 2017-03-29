package com.betamedia.qe.af.core.api.tp.operations;

import com.betamedia.tp.api.model.scheduling.Timezone;

/**
 * Created by mbelyaev on 3/23/17.
 */
public interface TimezoneOperations {
    Timezone get();

    Timezone get(String id);
}
