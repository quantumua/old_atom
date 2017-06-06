package com.betamedia.atom.core.dsl.operations;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.tp.api.model.Position;

/**
 * Created by mbelyaev on 3/27/17.
 */
public interface PositionOperations<T extends EnvironmentDependent> extends EnvironmentDependent {
    Position get(String id);

    Position getByDisplayId(String displayId);

    /**
     * Wait until position will be closed and return the final value
     * @param position opened position
     * @return
     */
    Position getExpired(Position position);
}
