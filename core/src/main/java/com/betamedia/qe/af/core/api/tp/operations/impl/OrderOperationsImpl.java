package com.betamedia.qe.af.core.api.tp.operations.impl;

import com.betamedia.qe.af.core.connectors.tp.AFTPConnector;
import com.betamedia.qe.af.core.api.tp.operations.AccountGroupOperations;
import com.betamedia.qe.af.core.api.tp.operations.OrderOperations;
import com.betamedia.tp.api.model.enums.OrderStatus;
import com.betamedia.tp.api.model.order.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertNotNull;

/**
 * Created by mbelyaev on 3/27/17.
 */
@Component
public class OrderOperationsImpl implements OrderOperations{
    private static final Logger logger = LogManager.getLogger(OrderOperationsImpl.class);

    @Autowired
    private AFTPConnector tpConnector;
    @Autowired
    private AccountGroupOperations accountGroupOperations;

    @Override
    public Order get(String id) {
        Order order = tpConnector.readById(Order.class, id);
        assertNotNull(order, "Order id=" + id + " is not available in GS");
        return order;
    }

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
