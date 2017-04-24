package com.betamedia.qe.af.core.dsl.operations.impl;

import com.betamedia.qe.af.core.connectors.tp.AFTPConnector;
import com.betamedia.qe.af.core.dsl.operations.PositionOperations;
import com.betamedia.tp.api.model.Position;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.testng.Assert.assertNotNull;

/**
 * This class is designed to facilitate the execution of common operations related to position operations.
 * It can be used as a "building block" when writing integration tests.
 *
 * Created by mbelyaev on 3/27/17.
 *
 * @see Position
 */
@Component
public class PositionOperationsImpl implements PositionOperations {
    private static final Logger logger = LogManager.getLogger(PositionOperationsImpl.class);

    @Autowired
    private AFTPConnector tpConnector;

    /**
     * Returns position by given id.
     */
    @Override
    public Position get(String id) {
        Position position = tpConnector.readById(Position.class, id);
        assertNotNull(position, "Position id=" + id + " is not available in GS");
        return position;
    }

    /**
     * Returns position by given display id.
     */
    @Override
    public Position getByDisplayId(String displayId) {
        Position position = tpConnector.readByDisplayId(Position.class, displayId);
        assertNotNull(position, "Position displayId=" + displayId + " is not available in GS");
        return position;
    }
}
