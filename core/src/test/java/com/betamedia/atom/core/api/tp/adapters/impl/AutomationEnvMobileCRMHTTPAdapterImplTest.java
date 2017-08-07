package com.betamedia.atom.core.api.tp.adapters.impl;

import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WidgetsNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.tp.entities.request.MarketingParametersRO;
import com.betamedia.atom.core.api.tp.entities.request.MobileDepositRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMError;
import com.betamedia.atom.core.api.tp.entities.response.CRMRegisterResult;
import com.betamedia.atom.core.api.tp.entities.response.CRMResponse;
import com.betamedia.atom.core.configuration.properties.CRMProperties;
import com.betamedia.atom.core.dsl.type.EnvironmentType;
import com.betamedia.atom.core.environment.tp.AutomationEnvironment;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by vsnigur on 5/5/17.
 */
public class AutomationEnvMobileCRMHTTPAdapterImplTest {
    @Mock
    private CRMProperties<AutomationEnvironment> crmProperties;

    private static final String SERVER_URL = "http://some.server.com";
    private static final String EXPECTED_URL = "someUrl";
    private MobileDepositRO mobileDepositRO;
    private MarketingParametersRO marketingParametersRO;
    private CustomerRO customerRO;
    private CRMResponse<CRMRegisterResult> crmResponseExpected;

    private static final String CUSTOMER_ID = "someCustomerID";

    private static final String USER_NAME = "userName";
    private static final String USER_PASSWORD = "userPassword";

    private static final String LANG = "lang";
    private static final String CURRENCY = "currency";
    private static final Long AMOUNT = 1000L;
    private static final String ADDRESS = "address";
    private static final String CITY = "city";
    private static final String COUNTRY_CODE = "countryCode";
    private static final Integer ZIP = 123456;
    private static final Long CC_NUMBER = 1111222233334444L;
    private static final Integer CVV2 = 123;
    private static final Integer EXP_MONTH = 1;
    private static final Integer EXP_YEAR = 1;
    private static final String HOLDER_FIRST_NAME = "holderFirstName";
    private static final String HOLDER_LAST_NAME = "holderLastName";
    private static final String TRADING_ACCOUNT_ID = "tradingAccountId";
    private static final String TRADING_ACCOUNT_NAME = "tradingAccountName";

    private static final String ORIGIN = "origin";
    private static final String REGISTRATION_IP = "registrationIp";
    private static final String CHANNEL = "channel";
    private static final String REFERRER = "referrer";
    private static final String SITE_ID = "siteId";
    private static final String AF_SITE_ID = "af_siteid";

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ResponseEntity<CRMResponse<CRMRegisterResult>> responseEntity;

    private static class AutomationEnvMobileCRMHTTPAdapterImpl extends AbstractMobileCRMHTTPAdapter<AutomationEnvironment> {
        @Override
        public EnvironmentType getEnvironment() {
            return EnvironmentType.AUTOMATION;
        }
    }

    @InjectMocks
    private AutomationEnvMobileCRMHTTPAdapterImpl automationEnvMobileCRMHTTPAdapterImpl;

    @BeforeMethod
    public void setup() {
        marketingParametersRO = getMarketingParametersRO();
        mobileDepositRO = getMobileDepositRO();
        customerRO = getCustomerRO();
        crmResponseExpected = getCRMResponseExpected();
        MockitoAnnotations.initMocks(this);
        intitializeMocks();
    }

    @Test
    public void getBaseUrl() throws Exception {
        AbstractMobileCRMHTTPAdapter<AutomationEnvironment> automationEnvMobileCRMHTTPAdapterImpl = getAdapter();
        ReflectionTestUtils.setField(automationEnvMobileCRMHTTPAdapterImpl,
                AbstractMobileCRMHTTPAdapter.class,
                "crmProperties",
                getCRMPropertiesHolder(),
                CRMProperties.class);

        Assert.assertEquals(automationEnvMobileCRMHTTPAdapterImpl.getBaseUrl(), EXPECTED_URL);
    }

    //TODO fix to support new CustomerRO initialization cycle
    @Test(enabled = false)
    public void registerCustomerRO() throws Exception {
        Assert.assertTrue(EqualsBuilder.reflectionEquals(crmResponseExpected,
                automationEnvMobileCRMHTTPAdapterImpl.register(getCustomerRO())));
    }

    @Test(enabled = false)
    public void registerCustomerROMarketingParametersRO() throws Exception {
        Assert.assertTrue(EqualsBuilder.reflectionEquals(crmResponseExpected, automationEnvMobileCRMHTTPAdapterImpl.register(getCustomerRO(),
                getMarketingParametersRO())));
    }

    @Test
    public void loginAction() throws Exception {
        Assert.assertTrue(EqualsBuilder.reflectionEquals(crmResponseExpected,
                automationEnvMobileCRMHTTPAdapterImpl.login(USER_NAME, USER_PASSWORD)));
    }

    @Test
    public void logoutAction() throws Exception {
        Assert.assertTrue(EqualsBuilder.reflectionEquals(crmResponseExpected,
                automationEnvMobileCRMHTTPAdapterImpl.logout(CUSTOMER_ID)));
    }

    @Test
    public void depositMobileDepositRO() throws Exception {
        Assert.assertTrue(EqualsBuilder.reflectionEquals(crmResponseExpected,
                automationEnvMobileCRMHTTPAdapterImpl.deposit(mobileDepositRO)));
    }

    @Test
    public void depositMobileDepositROMarketingParametersRO() throws Exception {
        Assert.assertTrue(EqualsBuilder.reflectionEquals(crmResponseExpected,
                automationEnvMobileCRMHTTPAdapterImpl.deposit(mobileDepositRO, marketingParametersRO)));
    }

    @Test
    public void depositByName() throws Exception {
        Assert.assertTrue(EqualsBuilder.reflectionEquals(crmResponseExpected,
                automationEnvMobileCRMHTTPAdapterImpl.depositByName(mobileDepositRO)));
    }

    private MobileDepositRO getMobileDepositRO() {
        return MobileDepositRO.builder(TRADING_ACCOUNT_ID)
                .setLanguage(LANG)
                .setCurrency(CURRENCY)
                .setAmount(AMOUNT)
                .setAddress(ADDRESS)
                .setCity(CITY)
                .setCountryCode(COUNTRY_CODE)
                .setZipCode(ZIP)
                .setCreditCardNumber(CC_NUMBER)
                .setCvv2(CVV2)
                .setExpiryMonth(EXP_MONTH)
                .setExpiryYear(EXP_YEAR)
                .setHolderFirstName(HOLDER_FIRST_NAME)
                .setHolderLastName(HOLDER_LAST_NAME)
                .setTradingAccountName(TRADING_ACCOUNT_NAME)
                .build();
    }

    private MarketingParametersRO getMarketingParametersRO() {
        return MarketingParametersRO.builder(false)
                .setOrigin(ORIGIN)
                .setRegistrationIp(REGISTRATION_IP)
                .setChannel(CHANNEL)
                .setReferrer(REFERRER)
                .setSiteId(SITE_ID)
                .setAf_siteid(AF_SITE_ID)
                .setParam1(null)
                .setParam2(null)
                .setParam3(null)
                .setParam4(null)
                .setParam5(null)
                .setKeyword(null)
                .setOfrtc(null)
                .build();
    }

    private CRMResponse getCRMResponseExpected() {
        return new CRMResponse<>(new ArrayList<String>(), null, new ArrayList<CRMError>(), "methodValue", "actionValue");
    }

    private CustomerRO getCustomerRO() {
        return CustomerRO.builder(WidgetsNamingStrategy.get()).build();
    }

    private AbstractMobileCRMHTTPAdapter<AutomationEnvironment> getAdapter() {
        return new AbstractMobileCRMHTTPAdapter<AutomationEnvironment>() {
            @Override
            public EnvironmentType getEnvironment() {
                return EnvironmentType.AUTOMATION;
            }
        };
    }

    private CRMProperties getCRMPropertiesHolder() {
        CRMProperties crmProperties = Mockito.mock(CRMProperties.class);
        when(crmProperties.getMobileUrl()).thenReturn(EXPECTED_URL);
        return crmProperties;
    }

    private void intitializeMocks() {
        automationEnvMobileCRMHTTPAdapterImpl.init();
        when(crmProperties.getMobileUrl()).thenReturn(SERVER_URL);
        when(responseEntity.getBody()).thenReturn(crmResponseExpected);
        String arguments = "";
        when(restTemplate.exchange(
                eq(getURL(AbstractMobileCRMHTTPAdapter.REGISTER_CUSTOMER_URL, arguments)),
                any(HttpMethod.class),
                any(),
                any(new ParameterizedTypeReference<CRMResponse<CRMRegisterResult>>() {
                }.getClass())))
                .thenReturn(responseEntity);

        arguments = String.format("?origin=%s&registrationIp=%s&channel=%s&referrer=%s&siteId=%s&af_siteid=%s",
                ORIGIN, REGISTRATION_IP, CHANNEL, REFERRER, SITE_ID, AF_SITE_ID);
        when(restTemplate.exchange(
                eq(getURL(AbstractMobileCRMHTTPAdapter.REGISTER_CUSTOMER_URL, arguments)),
                any(HttpMethod.class),
                any(),
                any(new ParameterizedTypeReference<CRMResponse<CRMRegisterResult>>() {
                }.getClass())))
                .thenReturn(responseEntity);

        arguments = String.format("?customerid=%s", CUSTOMER_ID);
        when(restTemplate.exchange(
                eq(getURL(AbstractMobileCRMHTTPAdapter.LOGOUT_URL, arguments)),
                any(HttpMethod.class),
                any(),
                any(new ParameterizedTypeReference<CRMResponse<CRMRegisterResult>>() {
                }.getClass())))
                .thenReturn(responseEntity);

        arguments = String.format("?username=%s&password=%s", USER_NAME, USER_PASSWORD);
        when(restTemplate.exchange(
                eq(getURL(AbstractMobileCRMHTTPAdapter.LOGIN_URL, arguments)),
                any(HttpMethod.class),
                any(),
                any(new ParameterizedTypeReference<CRMResponse<CRMRegisterResult>>() {
                }.getClass())))
                .thenReturn(responseEntity);

        arguments = String.format("?lang=%s&currency=%s&amount=%s&address=%s&city=%s&countryCode=%s&zip=%s&ccNumber=%s&cvv2=%s&expMonth=%s&expYear=%s&holderFirstName=%s&holderLastName=%s&tradingAccountId=%s&tradingAccountName=%s",
                LANG, CURRENCY, AMOUNT, ADDRESS, CITY, COUNTRY_CODE, ZIP, CC_NUMBER, CVV2, EXP_MONTH, EXP_YEAR, HOLDER_FIRST_NAME, HOLDER_LAST_NAME, TRADING_ACCOUNT_ID, TRADING_ACCOUNT_NAME);
        when(restTemplate.exchange(
                eq(getURL(AbstractMobileCRMHTTPAdapter.DEPOSIT_URL, arguments)),
                any(HttpMethod.class),
                any(),
                any(new ParameterizedTypeReference<CRMResponse<CRMRegisterResult>>() {
                }.getClass())))
                .thenReturn(responseEntity);

        arguments = String.format("?lang=%s&currency=%s&amount=%s&address=%s&city=%s&countryCode=%s&zip=%s&ccNumber=%s&cvv2=%s&expMonth=%s&expYear=%s&holderFirstName=%s&holderLastName=%s&tradingAccountId=%s&tradingAccountName=%s",
                LANG, CURRENCY, AMOUNT, ADDRESS, CITY, COUNTRY_CODE, ZIP, CC_NUMBER, CVV2, EXP_MONTH, EXP_YEAR, HOLDER_FIRST_NAME, HOLDER_LAST_NAME, TRADING_ACCOUNT_ID, TRADING_ACCOUNT_NAME);
        when(restTemplate.exchange(
                eq(getURL(AbstractMobileCRMHTTPAdapter.DEPOSIT_BY_NAME_URL, arguments)),
                any(HttpMethod.class),
                any(),
                any(new ParameterizedTypeReference<CRMResponse<CRMRegisterResult>>() {
                }.getClass())))
                .thenReturn(responseEntity);

        arguments = String.format("?lang=%s&currency=%s&amount=%s&address=%s&city=%s&countryCode=%s&zip=%s&ccNumber=%s&cvv2=%s&expMonth=%s&expYear=%s&holderFirstName=%s&holderLastName=%s&tradingAccountId=%s&tradingAccountName=%s&origin=%s&registrationIp=%s&channel=%s&referrer=%s&siteId=%s&af_siteid=%s",
                LANG, CURRENCY, AMOUNT, ADDRESS, CITY, COUNTRY_CODE, ZIP, CC_NUMBER, CVV2, EXP_MONTH, EXP_YEAR, HOLDER_FIRST_NAME, HOLDER_LAST_NAME, TRADING_ACCOUNT_ID, TRADING_ACCOUNT_NAME, ORIGIN, REGISTRATION_IP, CHANNEL, REFERRER, SITE_ID, AF_SITE_ID);
        when(restTemplate.exchange(
                eq(getURL(AbstractMobileCRMHTTPAdapter.DEPOSIT_URL, arguments)),
                any(HttpMethod.class),
                any(),
                any(new ParameterizedTypeReference<CRMResponse<CRMRegisterResult>>() {
                }.getClass())))
                .thenReturn(responseEntity);
    }

    private String getURL(String urlPath, String urlArguments) {
        return SERVER_URL + urlPath + urlArguments;
    }
}