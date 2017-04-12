package com.betamedia.qe.af.core.api.tp.entities.builders;

import static com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder.CHARS_IN_ID;
import static com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder.TP_AUTOMATION_PREFIX;
import static org.testng.Assert.*;

import org.testng.annotations.Test;
import com.betamedia.qe.af.core.api.tp.entities.request.AccountRO;

public class AccountBuilderTest {

    private final String ACCOUNTSTATUS = "accountStatus";
    private final String ACCOUNTTYPE = "accountType";
    private final String ACOUNTGROUPID = "acountGroupId";
    private final String BRANDDISPLAYID = "brandDisplayId";
    private final String COUNTRY = "country";
    private final String CURRENCY = "currency";
    private final String DESCRIPTION = "description";
    private final String EMAIL = "email@email.com";
    private final String FIRSTNAME = "firstName";
    private final String LASTNAME = "lastName";
    private final String PHONE = "phone";
    private final String PRICETYPE = "priceType";

    @Test(expectedExceptions = NullPointerException.class)
    public void testNPEWhenNoBrandId() {
        new AccountBuilder().createAccountRO();
    }

    @Test()
    public void testDefaultUniqueFields() {
        AccountRO accountRO = getRequired().createAccountRO();
        assertTrue(accountRO.getFirstName().contains(TP_AUTOMATION_PREFIX));
        assertEquals(TP_AUTOMATION_PREFIX.length() + CHARS_IN_ID, accountRO.getFirstName().length());
    }

    @Test()
    public void testCreateAccountRO() {
        checkFieldsValues(getAll().createAccountRO());
    }


    private AccountBuilder getAll() {
        return new AccountBuilder()
                .setAccountStatus(ACCOUNTSTATUS)
                .setAccountType(ACCOUNTTYPE)
                .setAcountGroupId(ACOUNTGROUPID)
                .setBrandDisplayId(BRANDDISPLAYID)
                .setCountry(COUNTRY)
                .setCurrency(CURRENCY)
                .setDescription(DESCRIPTION)
                .setEmail(EMAIL)
                .setFirstName(FIRSTNAME)
                .setLastName(LASTNAME)
                .setPhone(PHONE)
                .setPriceType(PRICETYPE);
    }

    private void checkFieldsValues(AccountRO accountRO) {
        assertTrue(accountRO.getCurrency().length() > 0);
        assertEquals(accountRO.getAccountStatus(), ACCOUNTSTATUS);
        assertEquals(accountRO.getAccountType(), ACCOUNTTYPE);
        assertEquals(accountRO.getAcountGroupId(), ACOUNTGROUPID);
        assertEquals(accountRO.getBrandId(), BRANDDISPLAYID);
        assertEquals(accountRO.getCountry(), COUNTRY);
        assertEquals(accountRO.getCurrency(), CURRENCY);
        assertEquals(accountRO.getDescription(), DESCRIPTION);
        assertEquals(accountRO.getEmail(), EMAIL);
        assertEquals(accountRO.getFirstName(), FIRSTNAME);
        assertEquals(accountRO.getLastName(), LASTNAME);
        assertEquals(accountRO.getPhone(), PHONE);
        assertEquals(accountRO.getPriceType(), PRICETYPE);
    }

    private AccountBuilder getRequired() {
        AccountBuilder acountBuilder = new AccountBuilder();
        acountBuilder.setBrandDisplayId(BRANDDISPLAYID);
        return acountBuilder;
    }
}
