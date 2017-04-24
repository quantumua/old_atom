package com.betamedia.qe.af.core.dsl.operations;

import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;
import com.betamedia.tp.api.model.Position;

/**
 * Created by mbelyaev on 3/27/17.
 */
public interface PositionOperations<T extends EnvironmentDependent> extends EnvironmentDependent {
    Position get(String id);

    Position getByDisplayId(String displayId);
}
