package com.betamedia.qe.af.core.dsl.operations;

import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;
import com.betamedia.tp.api.model.order.Order;

/**
 * Created by mbelyaev on 3/27/17.
 */
public interface OrderOperations<T extends EnvironmentDependent> extends EnvironmentDependent {
    Order get(String id);

    Order waitForStatusChange(Order order);
}
