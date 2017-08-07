package com.betamedia.atom.core.dsl.operations.impl;

import com.betamedia.atom.core.api.tp.adapters.TPCRMHttpAdapter;
import com.betamedia.atom.core.api.tp.entities.request.AccountRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMAccountCreate;
import com.betamedia.atom.core.api.tp.entities.response.CRMDeposit;
import com.betamedia.atom.core.api.tp.entities.response.TPCRMResponse;
import com.betamedia.atom.core.dsl.type.EnvironmentType;
import com.betamedia.atom.core.environment.tp.QAEnvironment;
import com.betamedia.common.enums.Country;
import com.betamedia.common.enums.Currency;
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

import static com.betamedia.atom.core.api.tp.entities.request.CustomerRO.CustomerROBuilder.DEFAULT_PASSWORD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Oleksandr Losiev on 4/18/17.
 */
public class AccountOperationsTest {
    private static class QAEnvAccountOperationsImpl extends AbstractAccountOperations<QAEnvironment> {
        @Override
        public EnvironmentType getEnvironment() {
            return EnvironmentType.QA;
        }
    }

    @InjectMocks
    private QAEnvAccountOperationsImpl accountOperations;

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
        doAnswer(invocationOnMock -> {
            AccountRO accountRo = invocationOnMock.getArgumentAt(0, AccountRO.class);
            CRMAccountCreate accountCreate = new CRMAccountCreate(accountId, accountDisplayId, accountRo.getBrandId());
            return new TPCRMResponse<>(accountCreate, null);
        }).when(crmHttpAdapter).create(any(AccountRO.class));
    }

    //TODO fix test after AccountOperations have been migrated from GigaSpaces properly
    @Test(enabled = false)
    public void testGetDefaultCRM() {
        ArgumentCaptor<AccountRO> argumentCaptor = ArgumentCaptor.forClass(AccountRO.class);

        Account actualAccount = accountOperations.getCRM();

        verify(crmHttpAdapter).create(argumentCaptor.capture());
        assertEquals(brandDisplayId, argumentCaptor.getValue().getBrandId());
        assertThat(getExpectedAccount(), new ReflectionEquals(actualAccount, "dateCreated"));
    }

    //TODO fix test after AccountOperations have been migrated from GigaSpaces properly
    @Test(enabled = false)
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

    //TODO fix test after AccountOperations have been migrated from GigaSpaces properly
    @Test(enabled = false)
    public void testDepositCRM() {
        doAnswer(invocationOnMock -> {
            CRMDeposit crmDeposit = new CRMDeposit(transactionId);
            return new TPCRMResponse<>(crmDeposit, null);
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
