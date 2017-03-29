package com.betamedia.qe.af.core.api.tp.operations;

import com.betamedia.tp.api.model.Position;
import com.betamedia.tp.api.model.order.Order;

/**
 * Created by mbelyaev on 3/27/17.
 */
public interface PositionOperations {
    Position get(String id);

    Position getByDisplayId(String displayId);
}
