package com.betamedia.qe.af.core.api.tp.entities.builders;

import com.betamedia.qe.af.core.api.tp.entities.CustomerRO;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Maksym Tsybulskyy
 *         Date: 4/3/17.
 */
public class CustomerBuilderTest {

    @Test
    public void testCreateDefaultCustomerRO() {
        CustomerBuilder customerBuilder = new CustomerBuilder();
        CustomerRO customerRO = customerBuilder.createCustomerRO();
        Assert.assertTrue(customerRO.getEmail().contains(customerRO.getUserName()));
    }

    @Test
    public void testCreateCustomerRO() {
        String customUserName = "customUserName";
        CustomerBuilder customerBuilder = new CustomerBuilder().setUserName(customUserName);
        CustomerRO customerRO = customerBuilder.createCustomerRO();
        Assert.assertTrue(customUserName.equals(customerRO.getUserName()));
        Assert.assertTrue(customerRO.getEmail().contains(customerRO.getUserName()));
    }
}