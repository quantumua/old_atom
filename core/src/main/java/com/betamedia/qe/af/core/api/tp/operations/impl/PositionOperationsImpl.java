package com.betamedia.qe.af.core.api.tp.operations.impl;

import com.betamedia.qe.af.core.connectors.tp.AFTPConnector;
import com.betamedia.qe.af.core.api.tp.operations.PositionOperations;
import com.betamedia.tp.api.model.Position;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.testng.Assert.assertNotNull;

/**
 * Created by mbelyaev on 3/27/17.
 */
@Component
public class PositionOperationsImpl implements PositionOperations {
    private static final Logger logger = LogManager.getLogger(PositionOperationsImpl.class);

    @Autowired
    private AFTPConnector tpConnector;

    @Override
    public Position get(String id) {
        Position position = tpConnector.readById(Position.class, id);
        assertNotNull(position, "Position id=" + id + " is not available in GS");
        return position;
    }

    @Override
    public Position getByDisplayId(String displayId) {
        Position position = tpConnector.readByDisplayId(Position.class, displayId);
        assertNotNull(position, "Position displayId=" + displayId + " is not available in GS");
        return position;
    }
}
