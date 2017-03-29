package com.betamedia.qe.af.core.api.tp.operations;

import com.betamedia.tp.api.model.order.Order;

/**
 * Created by mbelyaev on 3/27/17.
 */
public interface OrderOperations {
    Order get(String id);

    Order waitForStatusChange(Order order);
}
