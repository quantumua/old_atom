package com.betamedia.atom.core.dsl.operations;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.tp.api.model.order.Order;

/**
 * Created by mbelyaev on 3/27/17.
 */
public interface OrderOperations<T extends EnvironmentDependent> extends EnvironmentDependent {
    Order get(String id);

    Order waitForStatusChange(Order order);
}
