package com.betamedia.atom.core.dsl.operations.impl;

import com.betamedia.atom.core.api.tp.adapters.TPCRMHttpAdapter;
import com.betamedia.atom.core.api.tp.entities.request.AccountRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMAccountCreate;
import com.betamedia.atom.core.api.tp.entities.response.CRMDeposit;
import com.betamedia.atom.core.api.tp.entities.response.TPCRMResponse;
import com.betamedia.atom.core.connectors.tp.FWTPConnector;
import com.betamedia.atom.core.dsl.operations.AccountGroupOperations;
import com.betamedia.atom.core.dsl.operations.BrandOperations;
import com.betamedia.atom.core.environment.tp.QAEnvironment;
import com.betamedia.common.enums.Country;
import com.betamedia.common.enums.Currency;
import com.betamedia.common.utils.CollectionUtils;
import com.betamedia.tp.api.model.Account;
import com.betamedia.tp.api.model.AccountGroup;
import com.betamedia.tp.api.model.Brand;
import com.betamedia.tp.api.model.enums.AccountLevel;
import com.betamedia.tp.api.model.enums.AccountStatus;
import com.betamedia.tp.api.model.enums.AccountType;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.betamedia.atom.core.api.tp.entities.request.CustomerRO.CustomerROBuilder.DEFAULT_PASSWORD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Oleksandr Losiev on 4/18/17.
 */
public class AccountOperationsTest {
    private static class QAEnvAccountOperationsImpl extends AbstractAccountOperations<QAEnvironment> implements QAEnvironment {}

    @InjectMocks
    private QAEnvAccountOperationsImpl accountOperations;

    @Mock
    private FWTPConnector tpConnector;

    @Mock
    private AccountGroupOperations accountGroupOperations;

    @Mock
    private BrandOperations brandOperations;

    @Mock
    private TPCRMHttpAdapter crmHttpAdapter;

    private AccountGroup accountGroup;
    private String accountGroupId = "testGroupId";
    private String accountGroupDisplayId = "testGroupDisplayId";

    private Brand brand;
    private String brandId = "testBrandId";
    private String brandDisplayId = "testBrandDisplayId";

    private String accountId = "testAccountId";
    private String accountDisplayId = "testAccountDisplayId";

    private double depositAmount = 200;
    private String transactionId = "testTransactionId";

    @BeforeClass
    public void setupClass() throws Exception {
        accountGroup = new AccountGroup();
        accountGroup.setId(accountGroupId);
        accountGroup.setDisplayId(accountGroupDisplayId);
        brand = new Brand();
        brand.setId(brandId);
        brand.setDisplayId(brandDisplayId);
    }

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(accountGroupOperations.get()).thenReturn(accountGroup);
        when(brandOperations.get()).thenReturn(brand);
        when(tpConnector.update(any(Account.class), any())).then(returnsFirstArg());
        when(tpConnector.readById(Account.class, accountId)).thenReturn(getExpectedAccount());

        doAnswer(invocationOnMock -> {
            AccountRO accountRo = invocationOnMock.getArgumentAt(0, AccountRO.class);
            CRMAccountCreate accountCreate = new CRMAccountCreate(accountId, accountDisplayId, accountRo.getBrandId());
            return new TPCRMResponse<CRMAccountCreate>(accountCreate, null);
        }).when(crmHttpAdapter).create(any(AccountRO.class));
    }

    @Test
    public void testGetDefaultTP() {
        doAnswer(invocationOnMock -> {
            Account account = invocationOnMock.getArgumentAt(0, Account.class);
            account.setId(accountId);
            when(tpConnector.readById(Account.class, accountId)).thenReturn(account);
            return account;
        }).when(tpConnector).create(any(Account.class));

        Account actualAccount = accountOperations.getTP();
        assertThat(getExpectedAccount(), new ReflectionEquals(actualAccount, "dateCreated"));
    }

    @Test
    public void testGetTPById() {
        Account actualAccount = accountOperations.getTP(accountId);
        assertThat(getExpectedAccount(), new ReflectionEquals(actualAccount, "dateCreated"));
    }

    @Test(expectedExceptions = AssertionError.class)
    public void testGetUnavailableTP() {
        accountOperations.getTP("unavailable");
    }

    @Test
    public void testUpdateTP() {
        Account expectedAccount = getExpectedAccount();
        Set<String> properties = new HashSet<>();
        properties.add("testProperty");

        Account actualAccount = accountOperations.updateTP(expectedAccount, properties);

        verify(tpConnector).update(expectedAccount, properties);
        assertThat(getExpectedAccount(), new ReflectionEquals(actualAccount, "dateCreated"));
    }

    @Test
    public void testUpdateBalanceTP() {
        double newBalance = 250;

        Account actualAccount = accountOperations.updateBalanceTP(accountId, newBalance);

        verify(tpConnector).update(actualAccount, CollectionUtils.toSet(Account.EP_ACCOUNT_BALANCE.getName()));
        assertEquals(newBalance, (double) actualAccount.getBalance(), 0.01f);
    }

    @Test
    public void testGetDefaultCRM() {
        ArgumentCaptor<AccountRO> argumentCaptor = ArgumentCaptor.forClass(AccountRO.class);

        Account actualAccount = accountOperations.getCRM();

        verify(crmHttpAdapter).create(argumentCaptor.capture());
        assertEquals(brandDisplayId, argumentCaptor.getValue().getBrandId());
        assertThat(getExpectedAccount(), new ReflectionEquals(actualAccount, "dateCreated"));
    }

    @Test
    public void testGetCRMWithAccountBuilder() {
        ArgumentCaptor<AccountRO> argumentCaptor = ArgumentCaptor.forClass(AccountRO.class);
        String newBrandDisplayId = "newBrandDisplayId";
        AccountRO.AccountROBuilder accountROBuilder = AccountRO.builder();
        accountROBuilder.setBrandDisplayId(newBrandDisplayId);

        Account actualAccount = accountOperations.getCRM(accountROBuilder);

        verify(crmHttpAdapter).create(argumentCaptor.capture());
        assertEquals(newBrandDisplayId, argumentCaptor.getValue().getBrandId());
        assertThat(getExpectedAccount(), new ReflectionEquals(actualAccount, "dateCreated"));
    }

    @Test
    public void testDepositCRM() {
        doAnswer(invocationOnMock -> {
            CRMDeposit crmDeposit = new CRMDeposit(transactionId);
            return new TPCRMResponse<CRMDeposit>(crmDeposit, null);
        }).when(crmHttpAdapter).deposit(accountId, depositAmount, brandDisplayId);

        String actualTransactionId = accountOperations.depositCRM(accountId, depositAmount);
        assertEquals(transactionId, actualTransactionId);
    }

    private Account getExpectedAccount() {
        Account account = new Account();
        account.setAccountGroup(accountGroup);
        account.setAccountType(AccountType.REAL);
        account.setAssetsUsage(new ArrayList<>());
        account.setBrand(brand);
        account.setCountry(Country.USA);
        account.setCreatedBy("Automation test");
        account.setCurrency(Currency.USD);
        account.setDateCreated(System.currentTimeMillis());
        account.setDescription("This account was created by automatic test.");
        account.setLevel(AccountLevel.REGULAR);
        account.setPassword(DEFAULT_PASSWORD);
        account.setStatus(AccountStatus.ACTIVE);
        account.setBalance(AbstractAccountOperations.DEFAULT_ACCOUNT_BALANCE);
        account.setId(accountId);
        return account;
    }
}
