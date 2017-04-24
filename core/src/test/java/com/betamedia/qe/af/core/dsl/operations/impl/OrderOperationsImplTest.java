package com.betamedia.qe.af.core.dsl.operations.impl;

import com.betamedia.qe.af.core.connectors.tp.AFTPConnector;
import com.betamedia.qe.af.core.dsl.operations.AccountGroupOperations;
import com.betamedia.tp.api.model.enums.OrderStatus;
import com.betamedia.tp.api.model.order.Order;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Oleksandr Losiev on 4/20/17.
 */
public class OrderOperationsImplTest {

    @InjectMocks
    private OrderOperationsImpl orderOperations;

    @Mock
    private AFTPConnector tpConnector;

    @Mock
    private AccountGroupOperations accountGroupOperations;

    private Order expectedOrder;
    private String orderId = "orderId";
    private double orderAmount = 5d;

    @BeforeClass
    public void setupClass() {
        expectedOrder = getExpectedOrder();
    }

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(tpConnector.readById(Order.class, orderId)).thenReturn(expectedOrder);
    }

    @Test
    public void testGetOrderById() {
        Order actualOrder = orderOperations.get(orderId);
        assertThat(expectedOrder, new ReflectionEquals(actualOrder));
    }

    @Test
    public void testExpiredOrder() {
        Order actualOrder = orderOperations.waitForStatusChange(expectedOrder);
        assertThat(expectedOrder, new ReflectionEquals(actualOrder));
        verify(tpConnector).readById(Order.class, orderId);
    }

    private Order getExpectedOrder() {
        Order order = new Order();
        order.setId(orderId);
        order.setAmount(orderAmount);
        order.setOrderStatus(OrderStatus.EXPIRED);
        return order;
    }

}