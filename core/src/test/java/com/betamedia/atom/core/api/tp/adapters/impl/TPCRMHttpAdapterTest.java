package com.betamedia.atom.core.api.tp.adapters.impl;

import com.betamedia.atom.core.api.tp.adapters.AbstractHttpAdapter;
import com.betamedia.atom.core.api.tp.entities.request.AccountRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMAccountCreate;
import com.betamedia.atom.core.api.tp.entities.response.CRMAddBonus;
import com.betamedia.atom.core.api.tp.entities.response.CRMDeposit;
import com.betamedia.atom.core.api.tp.entities.response.TPCRMResponse;
import com.betamedia.atom.core.configuration.properties.CRMProperties;
import com.betamedia.atom.core.dsl.type.EnvironmentType;
import com.betamedia.atom.core.environment.tp.QAEnvironment;
import com.betamedia.tp.api.model.enums.AccountType;
import com.betamedia.tp.api.model.enums.BonusType;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by Oleksandr Losiev on 5/10/17.
 */
public class TPCRMHttpAdapterTest {
    private static class QAEnvTPCRMHttpAdapterImpl extends AbstractTPCRMHttpAdapter<QAEnvironment> {
        @Override
        public EnvironmentType getEnvironment() {
            return EnvironmentType.QA;
        }
    }

    @InjectMocks
    private QAEnvTPCRMHttpAdapterImpl adapter;

    @Mock
    private CRMProperties crmProperties;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ResponseEntity responseEntity;

    private String backOfficePassword = "testPassword";
    private String backOfficeUsername = "testUsername";
    private String baseUrl = "http://www.baseUrl.com/";
    private String depositUrl = "account/deposit";
    private String addBonusUrl = "account/bonus/add";
    private String createAccountUrl = "account/create";

    private String accountId = "testAccountId";
    private String accountDisplayId = "testAccountDisplayId";
    private String firstName = "testFirstName";
    private String currency = "usd";
    private String brandDisplayId = "testBrandDisplayId";
    private String transactionId = "testTransactionId";
    private String bonusDisplayId = "testBonusDisplayId";
    private BonusType bonusType = BonusType.CASHBACK;
    private Double amount = 124.5;
    private Double wagerAmount = 2355.3;

    private AccountType accountType = AccountType.TEST;

    @BeforeClass
    public void setupClass() throws Exception {
    }

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(crmProperties.getBackOfficePassword()).thenReturn(backOfficePassword);
        when(crmProperties.getBackOfficeUsername()).thenReturn(backOfficeUsername);
        when(crmProperties.getUrl()).thenReturn(baseUrl);
        ReflectionTestUtils.setField(adapter, AbstractHttpAdapter.class, "restTemplate", restTemplate, RestTemplate.class);
        adapter.init();
    }

    @Test
    public void testAddBonus() throws Exception {
        String url = baseUrl + addBonusUrl + "?accountId=" + accountId + "&brandId=" + brandDisplayId +
                "&amount=" + amount + "&wagerAmount=" + wagerAmount + "&bonusType=" + bonusType + "&userPassword=" + backOfficePassword + "&userName=" + backOfficeUsername;
        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), eq(null), any(ParameterizedTypeReference.class))).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(new TPCRMResponse<CRMAddBonus>(new CRMAddBonus(bonusDisplayId), Collections.emptyList()));

        TPCRMResponse<CRMAddBonus> depositResponse = adapter.addBonus(accountId, bonusType, amount, wagerAmount, brandDisplayId);
        assertNotNull(depositResponse);

        CRMAddBonus addBonus = depositResponse.getResult();
        assertNotNull(addBonus);

        assertEquals(bonusDisplayId, addBonus.getBonusDisplayId());
    }

    @Test
    public void testCreate() throws Exception {
        String url = baseUrl + createAccountUrl + "?currency=" + currency + "&accountType=" + accountType.getName() + "&brandId=" + brandDisplayId +
                "&firstName=" + firstName + "&userPassword=" + backOfficePassword + "&userName=" + backOfficeUsername;
        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), eq(null), any(ParameterizedTypeReference.class))).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(new TPCRMResponse<CRMAccountCreate>(new CRMAccountCreate(accountId, accountDisplayId, brandDisplayId), Collections.emptyList()));

        TPCRMResponse<CRMAccountCreate> accountResponse = adapter.create(
                AccountRO.builder()
                        .setBrandDisplayId(brandDisplayId)
                        .setCurrency(currency)
                        .setAccountType(accountType.getName())
                        .setFirstName(firstName)
                        .build());
        assertNotNull(accountResponse);

        CRMAccountCreate account = accountResponse.getResult();
        assertNotNull(account);

        assertEquals(accountId, account.getAccountId());
        assertEquals(accountDisplayId, account.getAccountDisplayId());
    }

    @Test
    public void testDeposit() throws Exception {
        String url = baseUrl + depositUrl + "?accountId=" + accountId + "&brandId=" + brandDisplayId +
                "&amount=" + amount + "&userPassword=" + backOfficePassword + "&userName=" + backOfficeUsername;
        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), eq(null), any(ParameterizedTypeReference.class))).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(new TPCRMResponse<CRMDeposit>(new CRMDeposit(transactionId), Collections.emptyList()));

        TPCRMResponse<CRMDeposit> depositResponse = adapter.deposit(accountId, amount, brandDisplayId);
        assertNotNull(depositResponse);

        CRMDeposit deposit = depositResponse.getResult();
        assertNotNull(deposit);

        assertEquals(transactionId, deposit.getTransactionId());
    }
}