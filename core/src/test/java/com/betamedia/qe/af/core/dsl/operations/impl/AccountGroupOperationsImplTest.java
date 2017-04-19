package com.betamedia.qe.af.core.dsl.operations.impl;

import com.betamedia.qe.af.core.connectors.tp.AFTPConnector;
import com.betamedia.tp.api.model.AccountGroup;
import com.betamedia.tp.api.model.DealApprovalConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Oleksandr Losiev on 4/18/17.
 */
public class AccountGroupOperationsImplTest {

    @InjectMocks
    private AccountGroupOperationsImpl accountGroupOperations;

    @Mock
    private AFTPConnector tpConnector;

    private final String accountGroupName = "testAccountGroup";
    private final String accountGroupDescription = "testAccountGroupDescription";
    private final String defaultAccountGroupId = "57e3e893-78aa-48fa-9bfe-e903fdc923f8";
    private final String accountGroupId = "testId";

    private final int firstDealAmount = 5;
    private final int firstDealDelay = 6;
    private final int secondDealAmount = 10;
    private final int secondDealDelay = -2;
    private final int marketDealAmount = 15;
    private final int marketDealDelay = 9;
    private final int defaultDelay = 0;

    private AccountGroup accountGroup;

    @BeforeClass
    public void setup() throws Exception {
        accountGroup = new AccountGroup();
        accountGroup.setName(accountGroupName);
        accountGroup.setDescription(accountGroupDescription);
        setDealApprovalConfigurations(accountGroup);
    }

    @BeforeMethod
    public void initMocks() {
        MockitoAnnotations.initMocks(this);

        when(tpConnector.readById(AccountGroup.class, accountGroupId)).thenReturn(accountGroup);
        when(tpConnector.readById(AccountGroup.class, defaultAccountGroupId)).thenReturn(accountGroup);

        accountGroupOperations.init();
    }

    @Test
    public void testGetDefaultAccountGroup() {
        AccountGroup actualAccountGroup = accountGroupOperations.get();
        assertEquals(accountGroupName, actualAccountGroup.getName());
        assertEquals(accountGroupDescription, actualAccountGroup.getDescription());
    }

    @Test
    public void testGetAccountGroupById() {
        AccountGroup actualAccountGroup = accountGroupOperations.get(accountGroupId);
        assertEquals(accountGroupName, actualAccountGroup.getName());
        assertEquals(accountGroupDescription, actualAccountGroup.getDescription());
    }

    @Test(expectedExceptions = AssertionError.class)
    public void testGetUnavailableAccountGroup() {
        accountGroupOperations.get("unavailable");
    }

    @Test
    public void testOpeningDelay() {
        int actualDelay = accountGroupOperations.getOpeningDelay((double)firstDealAmount + 1);
        assertEquals(firstDealDelay, actualDelay);
    }

    @Test
    public void testNegativeOpeningDelay() {
        int actualDelay = accountGroupOperations.getOpeningDelay((double)secondDealAmount + 1);
        assertEquals(secondDealDelay, actualDelay);
    }

    @Test
    public void testDealWithThresholdAmount() {
        int actualDelay = accountGroupOperations.getOpeningDelay((double)marketDealAmount);
        assertEquals(marketDealDelay, actualDelay);
    }

    @Test
    public void testAllDealsAboveThresholdAmount() {
        int actualDelay = accountGroupOperations.getOpeningDelay(0d);
        assertEquals(defaultDelay, actualDelay);
    }

    private void setDealApprovalConfigurations(AccountGroup accountGroup) {
        List<DealApprovalConfiguration> dealApprovalConfigurations = new ArrayList<>();
        List<DealApprovalConfiguration> marketDealApprovalConfigurations = new ArrayList<>();

        DealApprovalConfiguration dealConfiguration = new DealApprovalConfiguration();
        dealConfiguration.setAmount(firstDealAmount);
        dealConfiguration.setDelay(firstDealDelay);

        DealApprovalConfiguration secondDealConfiguration = new DealApprovalConfiguration();
        secondDealConfiguration.setAmount(secondDealAmount);
        secondDealConfiguration.setDelay(secondDealDelay);

        DealApprovalConfiguration marketDealConfiguration = new DealApprovalConfiguration();
        marketDealConfiguration.setAmount(marketDealAmount);
        marketDealConfiguration.setDelay(marketDealDelay);

        dealApprovalConfigurations.add(dealConfiguration);
        dealApprovalConfigurations.add(secondDealConfiguration);
        marketDealApprovalConfigurations.add(marketDealConfiguration);

        accountGroup.setDealApprovalConfigurations(dealApprovalConfigurations);
        accountGroup.setMarketDealApprovalConfigurations(marketDealApprovalConfigurations);
    }
}
