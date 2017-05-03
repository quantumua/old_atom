package com.betamedia.qe.af.core.dsl.operations.impl;

import com.betamedia.qe.af.core.api.tp.adapters.MobileCRMHTTPAdaper;
import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.builders.MarketingParametersBuilder;
import com.betamedia.qe.af.core.api.tp.entities.request.CustomerRO;
import com.betamedia.qe.af.core.api.tp.entities.request.MarketingParametersRO;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMError;
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
public class CustomerOperationsTest {

    private final String id = "1243";
    private final String displayId = "535";
    private final String firstName = "firstName";
    private final String lastName = "lastName";
    private final String email = "firstName.lastName@gmail.com";
    private final String userName = "testUsername";
    private final String password = "testPassword";

    private CRMResponse<CRMRegisterResult> expectedResponse;
    private CRMCustomer expectedCustomer;

    @InjectMocks
    private QAEnvCustomerOperationsImpl customerOperations;

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
        when(mobileCRMHTTPAdaper.login(userName, password)).thenReturn(expectedResponse);

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

    @Test
    public void testRegisterCustomerWithMarketingParameters() {
        final String siteId = "24options";
        final String keyword = "testKeyword";
        when(mobileCRMHTTPAdaper.register(any(CustomerRO.class), any(MarketingParametersRO.class))).thenReturn(expectedResponse);

        ArgumentCaptor<MarketingParametersRO> argumentCaptor = ArgumentCaptor.forClass(MarketingParametersRO.class);
        CustomerBuilder customerBuilder = new CustomerBuilder();
        MarketingParametersBuilder marketingParametersBuilder = new MarketingParametersBuilder(true);
        marketingParametersBuilder.setSiteId(siteId);
        marketingParametersBuilder.setKeyword(keyword);

        CRMCustomer actualCustomer = customerOperations.register(customerBuilder, marketingParametersBuilder);

        verify(mobileCRMHTTPAdaper).register(any(CustomerRO.class), argumentCaptor.capture());
        assertEquals(siteId, argumentCaptor.getValue().getSiteId());
        assertEquals(keyword, argumentCaptor.getValue().getKeyword());
        assertThat(expectedCustomer, new ReflectionEquals(actualCustomer));
    }

    @Test(expectedExceptions = AssertionError.class)
    public void testRegisterCustomerWithUnexpectedErrors() {
        CRMResponse<CRMRegisterResult> errorResponse = new CRMResponse<>(null, null,
                Collections.singletonList(new CRMError("errorCode", "errorMessage")), null, null);
        when(mobileCRMHTTPAdaper.register(any(CustomerRO.class))).thenReturn(errorResponse);

        customerOperations.register();
    }

    @Test
    public void testLogin() {
        CRMCustomer actualCustomer = customerOperations.register();
        assertThat(expectedCustomer, new ReflectionEquals(actualCustomer));
    }

    @Test(enabled = false)
    public void testLogout() {

    }

    @Test(enabled = false)
    public void testDeposit() {

    }

    @Test(enabled = false)
    public void testDepositWithMarketingParameters() {

    }

    @Test(expectedExceptions = AssertionError.class, enabled = false)
    public void testDepositWithUnxepectedErrors() {

    }

    @Test(enabled = false)
    public void testDeposithWithExpectedErrors() {

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