package com.betamedia.qe.af.core.dsl.operations;

import com.betamedia.tp.api.model.Position;

/**
 * Created by mbelyaev on 3/27/17.
 */
public interface PositionOperations {
    Position get(String id);

    Position getByDisplayId(String displayId);
}
