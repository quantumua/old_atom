package com.betamedia.atom.core.dsl.operations.impl;

import com.betamedia.atom.core.connectors.tp.FWTPConnector;
import com.betamedia.atom.core.dsl.operations.PositionOperations;
import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.tp.api.model.Position;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static org.testng.Assert.assertNotNull;

/**
 * This class is designed to facilitate the execution of common operations related to position operations.
 * It can be used as a "building block" when writing integration tests.
 * <p>
 * @author mbelyaev
 * @since 3/27/17
 *
 * @see Position
 */
public abstract class AbstractPositionOperations<T extends EnvironmentDependent> implements PositionOperations<T> {
    private static final Logger logger = LogManager.getLogger(AbstractPositionOperations.class);

    @Autowired
    private FWTPConnector<T> tpConnector;

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

    /**
     * Waits until trade is expired against to given position
     */
    @Override
    public Position getExpired(Position position) {
        // see https://devredmine/issues/65323
        // TODO: Move implementation of waiting trade expirity from legacy framework: com.scipio.tptesting.util.Suspension.waitTradeToExpire
        return position;

    }
}
