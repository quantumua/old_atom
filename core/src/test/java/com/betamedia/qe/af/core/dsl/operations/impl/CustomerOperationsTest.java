package com.betamedia.qe.af.core.dsl.operations.impl;

import com.betamedia.qe.af.core.api.tp.adapters.MobileCRMHTTPAdaper;
import com.betamedia.qe.af.core.api.tp.entities.request.CustomerRO;
import com.betamedia.qe.af.core.api.tp.entities.request.MarketingParametersRO;
import com.betamedia.qe.af.core.api.tp.entities.request.MobileDepositRO;
import com.betamedia.qe.af.core.api.tp.entities.response.*;
import com.betamedia.qe.af.core.environment.tp.QAEnvironment;
import com.betamedia.qe.af.core.persistence.entities.TrackingInfo;
import com.betamedia.qe.af.core.persistence.entities.TrackingInfoExtension;
import com.betamedia.qe.af.core.persistence.repositories.AbstractTrackingInfoExtensionRepository;
import com.betamedia.qe.af.core.persistence.repositories.AbstractTrackingInfoRepository;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Oleksandr Losiev on 4/20/17.
 */
public class CustomerOperationsTest {

    private final String customerId = "1243";
    private final String displayId = "535";
    private final String firstName = "firstName";
    private final String lastName = "lastName";
    private final String email = "firstName.lastName@gmail.com";
    private final String userName = "testUsername";
    private final String password = "testPassword";
    private final String transactionId = "testTransactionId";
    private final String tradingAccountId = "testTradingAccountId";
    private final String siteId = "24options";
    private final String keyword = "testKeyword";
    private final String trackingId = "testTrackingId";

    private CRMResponse<CRMRegisterResult> expectedCustomerResponse;
    private CRMResponse<CRMDeposit> expectedDepositResponse;
    private CRMResponse<CRMDeposit> errorDepositResponse;
    private CRMCustomer expectedCustomer;
    private CRMDeposit expectedDeposit;
    private CRMError expectedError;
    private TrackingInfo expectedTrackingInfo;
    private TrackingInfoExtension expectedTrackingInfoExtension;

    @InjectMocks
    private QAEnvCustomerOperationsImpl customerOperations;

    @Mock
    private MobileCRMHTTPAdaper mobileCRMHTTPAdaper;

    @Mock
    private AbstractTrackingInfoRepository<QAEnvironment> trackingInfoRepository;

    @Mock
    private AbstractTrackingInfoExtensionRepository<QAEnvironment> trackingInfoExtensionRepository;

    @BeforeClass
    public void setupClass() {
        expectedCustomerResponse = getExpectedCustomerResponse();
        expectedCustomer = getExpectedCustomer();
        expectedDeposit = getExpectedDeposit();
        expectedDepositResponse = getExpectedDepositResponse();
        errorDepositResponse = getErrorDepositResponse();
        expectedTrackingInfo = getExpectedTrackingInfo();
        expectedTrackingInfoExtension = getExpectedTrackingInfoExtension();
        expectedError = getExpectedError();
    }

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(mobileCRMHTTPAdaper.register(any())).thenReturn(expectedCustomerResponse);
        when(mobileCRMHTTPAdaper.login(userName, password)).thenReturn(expectedCustomerResponse);
        when(mobileCRMHTTPAdaper.deposit(any())).thenReturn(expectedDepositResponse);
        when(mobileCRMHTTPAdaper.depositByName(any())).thenReturn(expectedDepositResponse);
        when(trackingInfoRepository.findOne(trackingId)).thenReturn(expectedTrackingInfo);
        when(trackingInfoExtensionRepository.findByCustomerIdOrderByCookieCreationTimeDesc(customerId))
                .thenReturn(Collections.singletonList(expectedTrackingInfoExtension));
        when(trackingInfoExtensionRepository.findByKeywordOrderByCookieCreationTimeDesc(keyword))
                .thenReturn(Collections.singletonList(expectedTrackingInfoExtension));
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
        CRMCustomer actualCustomer = customerOperations.register(
                CustomerRO.builder()
                        .setEmail(newEmail)
                        .build());

        verify(mobileCRMHTTPAdaper).register(argumentCaptor.capture());
        assertEquals(newEmail, argumentCaptor.getValue().getEmail());
        assertThat(expectedCustomer, new ReflectionEquals(actualCustomer));

    }

    @Test
    public void testRegisterCustomerWithMarketingParameters() {
        when(mobileCRMHTTPAdaper.register(any(CustomerRO.class), any(MarketingParametersRO.class))).thenReturn(expectedCustomerResponse);

        ArgumentCaptor<MarketingParametersRO> argumentCaptor = ArgumentCaptor.forClass(MarketingParametersRO.class);
        MarketingParametersRO.MarketingParametersBuilder marketingParametersBuilder = MarketingParametersRO.builder(true);
        marketingParametersBuilder.setSiteId(siteId);
        marketingParametersBuilder.setKeyword(keyword);

        CRMCustomer actualCustomer = customerOperations.register(
                CustomerRO.builder().build(),
                MarketingParametersRO.builder(true)
                        .setSiteId(siteId)
                        .setKeyword(keyword)
                        .build()
        );

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
    public void testRegisterCustomerWithExpectedErrors() {
        when(mobileCRMHTTPAdaper.register(any())).thenReturn(errorDepositResponse);
        List<CRMError> actualErrors = customerOperations.registerWithErrors(CustomerRO.builder().build());
        assertTrue(actualErrors.size() == 1);
        assertThat(expectedError, new ReflectionEquals(actualErrors.get(0)));
    }

    @Test
    public void testLogin() {
        CRMCustomer actualCustomer = customerOperations.login(userName, password);
        assertThat(expectedCustomer, new ReflectionEquals(actualCustomer));
    }

    @Test
    public void testLogout() {
        final String customerId = "testId";
        when(mobileCRMHTTPAdaper.logout(customerId)).thenReturn(null);

        customerOperations.logout(customerId);

        verify(mobileCRMHTTPAdaper).logout(customerId);
    }

    @Test
    public void testDeposit() {
        CRMDeposit actualDeposit = customerOperations.deposit(MobileDepositRO.builder(tradingAccountId).build());
        assertThat(expectedDeposit, new ReflectionEquals(actualDeposit));
    }

    @Test
    public void testDepositWithMarketingParameters() {
        when(mobileCRMHTTPAdaper.deposit(any(MobileDepositRO.class), any(MarketingParametersRO.class))).thenReturn(expectedDepositResponse);

        ArgumentCaptor<MarketingParametersRO> argumentCaptor = ArgumentCaptor.forClass(MarketingParametersRO.class);

        CRMDeposit actualDeposit = customerOperations.deposit(
                MobileDepositRO.builder(tradingAccountId).build(),
                MarketingParametersRO.builder(true)
                        .setSiteId(siteId)
                        .setKeyword(keyword)
                        .build());

        verify(mobileCRMHTTPAdaper).deposit(any(MobileDepositRO.class), argumentCaptor.capture());
        assertEquals(siteId, argumentCaptor.getValue().getSiteId());
        assertEquals(keyword, argumentCaptor.getValue().getKeyword());
        assertThat(expectedDeposit, new ReflectionEquals(actualDeposit));
    }

    @Test(expectedExceptions = AssertionError.class)
    public void testDepositWithUnexpectedErrors() {
        when(mobileCRMHTTPAdaper.deposit(any())).thenReturn(errorDepositResponse);
        customerOperations.deposit(MobileDepositRO.builder(tradingAccountId).build());
    }

    @Test
    public void testDepositWithExpectedErrors() {
        when(mobileCRMHTTPAdaper.deposit(any())).thenReturn(errorDepositResponse);
        List<CRMError> actualErrors = customerOperations.depositWithErrors(MobileDepositRO.builder(tradingAccountId).build());
        assertTrue(actualErrors.size() == 1);
        assertThat(expectedError, new ReflectionEquals(actualErrors.get(0)));
    }

    @Test()
    public void testDepositByName() {
        CRMDeposit actualDeposit = customerOperations.depositByName(MobileDepositRO.builder(tradingAccountId).build());
        assertThat(expectedDeposit, new ReflectionEquals(actualDeposit));
    }

    @Test
    public void testGetTrackingInfo() {
        TrackingInfo actualInfo = customerOperations.getCustomerTrackingInfo(trackingId);
        assertThat(expectedTrackingInfo, new ReflectionEquals(actualInfo));
    }

    @Test
    public void testGetTrackingInfoExtensionByKeyword() {
        TrackingInfoExtension actualInfo = customerOperations.getCustomerTrackingInfoExtensionByKeyword(keyword);
        assertThat(expectedTrackingInfoExtension, new ReflectionEquals(actualInfo));
    }

    @Test
    public void testGetTrackingInfoExtensionById() {
        TrackingInfoExtension actualInfo = customerOperations.getCustomerTrackingInfoExtensionByCustomerId(customerId);
        assertThat(expectedTrackingInfoExtension, new ReflectionEquals(actualInfo));
    }

    private CRMResponse<CRMRegisterResult> getExpectedCustomerResponse() {
        return new CRMResponse<>(Collections.emptyList(),
                new CRMRegisterResult(getExpectedCustomer()), Collections.emptyList(), "", "");
    }

    private CRMResponse<CRMDeposit> getExpectedDepositResponse() {
        return new CRMResponse<>(Collections.emptyList(), getExpectedDeposit(), Collections.emptyList(), "", "");
    }

    private CRMResponse<CRMDeposit> getErrorDepositResponse() {
        return new CRMResponse<>(null, null,
                Collections.singletonList(getExpectedError()), null, null);
    }

    private CRMError getExpectedError() {
        return new CRMError("errorCode", "errorMessage");
    }

    private CRMCustomer getExpectedCustomer() {
        CRMCustomer crmCustomer = new CRMCustomer();
        crmCustomer.setId(customerId);
        crmCustomer.setDisplayId(displayId);
        crmCustomer.setFirstName(firstName);
        crmCustomer.setLastName(lastName);
        crmCustomer.setEmail(email);
        return crmCustomer;
    }

    private CRMDeposit getExpectedDeposit() {
        return new CRMDeposit(transactionId);
    }

    private TrackingInfo getExpectedTrackingInfo() {
        return new TrackingInfo(trackingId);
    }

    private TrackingInfoExtension getExpectedTrackingInfoExtension() {
        return new TrackingInfoExtension(trackingId, customerId);
    }
}