package com.betamedia.atom.core.dsl.operations.impl;

import com.betamedia.atom.core.connectors.tp.FWTPConnector;
import com.betamedia.atom.core.dsl.operations.AccountGroupOperations;
import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.atom.core.dsl.operations.OrderOperations;
import com.betamedia.tp.api.model.enums.OrderStatus;
import com.betamedia.tp.api.model.order.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertNotNull;

/**
 * This class is designed to facilitate the execution of common operations related to order operations.
 * It can be used as a "building block" when writing integration tests.
 *
 * Created by mbelyaev on 3/27/17.
 *
 * @see Order
 */
public abstract class AbstractOrderOperations<T extends EnvironmentDependent> implements OrderOperations<T>{
    private static final Logger logger = LogManager.getLogger(AbstractOrderOperations.class);

    @Autowired
    private FWTPConnector<T> tpConnector;
    @Autowired
    private AccountGroupOperations<T> accountGroupOperations;

    /**
     * Returns an order by given id.
     */
    @Override
    public Order get(String id) {
        Order order = tpConnector.readById(Order.class, id);
        assertNotNull(order, "Order id=" + id + " is not available in GS");
        return order;
    }

    /**
     * Waits until either an order is no longer opened or a cancel interval has passed.
     * Then returns the same order
     * @param order order to monitor
     * @return input order
     */
    @Override
    public Order waitForStatusChange(Order order) {
        Integer delay = accountGroupOperations.getOpeningDelay(order.getAmount());
        Order newOrder = get(order.getId());
        try {
            TimeUnit.MILLISECONDS.sleep(delay);
            long cancelTime = System.currentTimeMillis() + 1000 * 60;
            while (System.currentTimeMillis() < cancelTime && newOrder.getOrderStatus() == OrderStatus.OPEN) {
                TimeUnit.MILLISECONDS.sleep(500);
                newOrder = get(order.getId());
            }
        } catch (InterruptedException e) {
            logger.error(e);
            Thread.currentThread().interrupt();
        }
        return newOrder;
    }
}