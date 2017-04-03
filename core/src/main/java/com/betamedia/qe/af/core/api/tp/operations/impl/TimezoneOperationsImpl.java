package com.betamedia.qe.af.core.api.tp.operations.impl;

import com.betamedia.qe.af.core.connectors.tp.AFTPConnector;
import com.betamedia.qe.af.core.api.tp.operations.TimezoneOperations;
import com.betamedia.tp.api.model.scheduling.Timezone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.testng.Assert.assertNotNull;

/**
 * Created by mbelyaev on 3/23/17.
 */
@Component
public class TimezoneOperationsImpl implements TimezoneOperations {
    private static final Logger logger = LogManager.getLogger(TimezoneOperationsImpl.class);

    @Autowired
    private AFTPConnector tpConnector;

    @Override
    public Timezone get() {
        return create();
    }

    @Override
    public Timezone get(String id) {
        Timezone timezone = tpConnector.readById(Timezone.class, id);
        assertNotNull(timezone, "Timezone id=" + id + " is not available in GS");
        return timezone;
    }

    private Timezone create() {
        return tpConnector.create(new Timezone());
    }
}
