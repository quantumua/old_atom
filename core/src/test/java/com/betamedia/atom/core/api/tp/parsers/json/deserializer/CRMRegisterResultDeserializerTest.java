package com.betamedia.atom.core.api.tp.parsers.json.deserializer;

import com.betamedia.atom.core.api.tp.entities.response.CRMAccount;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.core.api.tp.entities.response.CRMRegisterResult;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertThat;

/**
 * Created by Oleksandr Losiev on 4/14/17.
 */
public class CRMRegisterResultDeserializerTest {

    private final String id = "1243";
    private final String displayId = "535";
    private final String firstName = "firstName";
    private final String lastName = "lastName";
    private final String email = "firstName.lastName@gmail.com";
    private final String phone = "12435456";
    private final String userName = "username";
    private final String language = "en";

    private final String idFieldName = "Id";
    private final String displayIdFieldName = "DisplayId";
    private final String firstNameFieldName = "FirstName";
    private final String lastNameFieldName = "LastName";
    private final String emailFieldName = "Email";
    private final String phoneFieldName = "Phone";
    private final String userNameFieldName = "UserName";
    private final String languageFieldName = "Language";
    private final String accountsFieldName = "Accounts";

    private final String accountId = "1536";
    private final String accountCurrency = "USD";
    private final String accountDisplayId = "737";
    private final String accountType = "testType";
    private final String accountPlatform = "testPlatform";
    private final String accountProduct = "testProduct";
    private final BigDecimal accountPendingAmount = new BigDecimal(1000);
    private final String accountExternalId = "9325";

    //TODO: ExtenalId and Id fields are mixed up until the bug is fixed in production.
    private final String accountIdFieldName = "ExternalID";
    private final String accountCurrencyFieldName = "Currency";
    private final String accountDisplayIdFieldName = "TradingAccountName";
    private final String accountTypeFieldName = "AccountType";
    private final String accountPlatformFieldName = "Platform";
    private final String accountProductFieldName = "Product";
    private final String accountPendingAmountFieldName = "PendingAmount";
    private final String accountExternalIdFieldName = "Id";

    private String validJson;
    private String incompleteJson;
    private String emptyObjectJson = "{}";

    private ObjectMapper mapper;
    private CRMRegisterResultDeserializer deserializer;

    @BeforeMethod
    public void setup() throws Exception {
        mapper = new ObjectMapper();
        deserializer = new CRMRegisterResultDeserializer();

        JSONObject validJsonObject = new JSONObject();
        validJsonObject.put(CRMRegisterResultDeserializer.CUSTOMER_NODE_KEY, getCRMCustomerJson());
        validJson = validJsonObject.toString();

        JSONObject incompleteJsonObject = new JSONObject();
        incompleteJsonObject.put(CRMRegisterResultDeserializer.CUSTOMER_NODE_KEY, getIncompleteCRMCustomerJson());
        incompleteJson = incompleteJsonObject.toString();
    }

    @Test
    public void testValidJson() throws Exception {
        JsonParser parser = mapper.getFactory().createParser(validJson);
        Object result = deserializer.deserialize(parser, null);
        CRMRegisterResult crmRegisterResult = (CRMRegisterResult)result;

        CRMCustomer actualCustomer = crmRegisterResult.getCustomer();
        assertThat(getExpectedCustomer(), new ReflectionEquals(actualCustomer, "accounts"));
        assertThat(getExpectedCustomer().getAccounts(), new ReflectionEquals(actualCustomer.getAccounts(), "pendingAmount"));
    }

    @Test
    public void testIncompleteJson() throws Exception {
        JsonParser parser = mapper.getFactory().createParser(incompleteJson);
        Object result = deserializer.deserialize(parser, null);
        CRMRegisterResult crmRegisterResult = (CRMRegisterResult)result;

        CRMCustomer actualCustomer = crmRegisterResult.getCustomer();
        assertThat(getIncompleteExpectedCustomer(), new ReflectionEquals(actualCustomer));
    }

    @Test (expectedExceptions = NullPointerException.class)
    public void testEmptyJsonObject() throws Exception {
        JsonParser parser = mapper.getFactory().createParser(emptyObjectJson);
        Object result = deserializer.deserialize(parser, null);
    }

    private CRMCustomer getExpectedCustomer() {
        CRMCustomer crmCustomer = new CRMCustomer();
        crmCustomer.setId(id);
        crmCustomer.setDisplayId(displayId);
        crmCustomer.setFirstName(firstName);
        crmCustomer.setLastName(lastName);
        crmCustomer.setEmail(email);
        crmCustomer.setPhone(phone);
        crmCustomer.setLanguage(language);
        crmCustomer.setUserName(userName);
        crmCustomer.setAccounts(getExpectedCRMAccounts());
        return crmCustomer;
    }

    private CRMAccount[] getExpectedCRMAccounts() {
        CRMAccount account = new CRMAccount(accountId, accountCurrency, accountDisplayId, accountType, accountPlatform,
                accountProduct, accountPendingAmount, accountExternalId);
        return new CRMAccount[] {account};
    }

    private CRMCustomer getIncompleteExpectedCustomer() {
        CRMCustomer crmCustomer = new CRMCustomer();
        crmCustomer.setId(id);
        crmCustomer.setDisplayId(displayId);
        crmCustomer.setFirstName(firstName);
        return crmCustomer;
    }

    private JSONArray getCRMAccountsJson() throws Exception {
        JSONArray accountsJson = new JSONArray();
        JSONObject account = new JSONObject();
        account.put(accountIdFieldName, accountId);
        account.put(accountCurrencyFieldName, accountCurrency);
        account.put(accountDisplayIdFieldName, accountDisplayId);
        account.put(accountTypeFieldName, accountType);
        account.put(accountPlatformFieldName, accountPlatform);
        account.put(accountProductFieldName, accountProduct);
        account.put(accountPendingAmountFieldName, accountPendingAmount);
        account.put(accountExternalIdFieldName, accountExternalId);

        accountsJson.put(account);
        return accountsJson;
    }

    private JSONObject getCRMCustomerJson() throws Exception{
        JSONObject customer = new JSONObject();
        customer.put(idFieldName, id);
        customer.put(displayIdFieldName, displayId);
        customer.put(firstNameFieldName, firstName);
        customer.put(lastNameFieldName, lastName);
        customer.put(emailFieldName, email);
        customer.put(phoneFieldName, phone);
        customer.put(languageFieldName, language);
        customer.put(userNameFieldName, userName);
        customer.put(accountsFieldName, getCRMAccountsJson());
        return customer;
    }

    private JSONObject getIncompleteCRMCustomerJson() throws Exception {
        JSONObject customer = new JSONObject();
        customer.put(idFieldName, id);
        customer.put(displayIdFieldName, displayId);
        customer.put(firstNameFieldName, firstName);
        return customer;
    }
}
