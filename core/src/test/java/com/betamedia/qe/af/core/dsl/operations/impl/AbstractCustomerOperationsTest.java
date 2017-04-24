package com.betamedia.qe.af.core.dsl.operations.impl;

import com.betamedia.qe.af.core.api.tp.adapters.MobileCRMHTTPAdaper;
import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.request.CustomerRO;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMRegisterResult;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMResponse;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Oleksandr Losiev on 4/20/17.
 */
public class AbstractCustomerOperationsTest {

    private final String id = "1243";
    private final String displayId = "535";
    private final String firstName = "firstName";
    private final String lastName = "lastName";
    private final String email = "firstName.lastName@gmail.com";

    private CRMResponse<CRMRegisterResult> expectedResponse;
    private CRMCustomer expectedCustomer;

    @InjectMocks
    private AbstractCustomerOperations customerOperations;

    @Mock
    private MobileCRMHTTPAdaper mobileCRMHTTPAdaper;

    @BeforeClass
    public void setupClass() {
        expectedResponse = getExpectedResponse();
        expectedCustomer = getExpectedCustomer();
    }

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(mobileCRMHTTPAdaper.register(any())).thenReturn(expectedResponse);
    }

    @Test
    public void testRegisterDefaultCustomer() {
        CRMCustomer actualCustomer = customerOperations.register();
        assertThat(expectedCustomer, new ReflectionEquals(actualCustomer));
    }

    @Test
    public void testRegisterCustomerWithCustomerBuilder() {
        ArgumentCaptor<CustomerRO> argumentCaptor = ArgumentCaptor.forClass(CustomerRO.class);
        String newEmail = "newEmail";
        CustomerBuilder customerBuilder = new CustomerBuilder();
        customerBuilder.setEmail(newEmail);

        CRMCustomer actualCustomer = customerOperations.register(customerBuilder);

        verify(mobileCRMHTTPAdaper).register(argumentCaptor.capture());
        assertEquals(newEmail, argumentCaptor.getValue().getEmail());
        assertThat(expectedCustomer, new ReflectionEquals(actualCustomer));

    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testRegisterCustomerWithNullBuilder() {
        customerOperations.register(null);
    }

    private CRMResponse<CRMRegisterResult> getExpectedResponse() {
        return new CRMResponse<>(Collections.emptyList(),
                new CRMRegisterResult(getExpectedCustomer()), Collections.emptyList(), "", "");
    }

    private CRMCustomer getExpectedCustomer() {
        CRMCustomer crmCustomer = new CRMCustomer();
        crmCustomer.setId(id);
        crmCustomer.setDisplayId(displayId);
        crmCustomer.setFirstName(firstName);
        crmCustomer.setLastName(lastName);
        crmCustomer.setEmail(email);
        return crmCustomer;
    }
}