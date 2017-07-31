package com.betamedia.atom.core.api.tp.entities.response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.testng.AssertJUnit.assertNull;
import org.testng.annotations.Test;
import java.math.BigDecimal;

public class CRMCustomerTest {

    private final BigDecimal PENDING_AMOUNT = BigDecimal.valueOf(9999);

    @Test
    public void testNullAccounts() {
        CRMCustomer crmCustomer = new CRMCustomer();
        crmCustomer.setAccounts(null);
        assertNull(crmCustomer.getFXCFDAccount());
    }

    @Test
    public void testNullFieldValuesAccounts() {
        CRMCustomer crmCustomer = new CRMCustomer();
        crmCustomer.setAccounts(getNullFieldValuesCRMAccounts());
        assertNull(crmCustomer.getFXCFDAccount());
    }

    @Test
    public void testShouldCheckBinaryAccountWithIncorrectProduct() {
        CRMCustomer crmCustomer = new CRMCustomer();
        crmCustomer.setAccounts(getCustomCRMAccounts());
        assertNull(crmCustomer.getFXCFDAccount());
    }

    @Test
    public void testShouldCheckBinaryAccountForCorrectProducts() {
        CRMAccount scipioCRMAccount = getCRMAccountCustom();
        scipioCRMAccount.setPlatform("scipio");
        CRMAccount pandaCRMAccount = getCRMAccountCustom();
        pandaCRMAccount.setPlatform("panda");
        CRMCustomer crmCustomer = new CRMCustomer();
        crmCustomer.setAccounts(new CRMAccount[]{scipioCRMAccount, pandaCRMAccount});

        checkFields(crmCustomer.getFXCFDAccount(), pandaCRMAccount);
    }

    private CRMAccount[] getNullFieldValuesCRMAccounts() {
        return new CRMAccount[]{getNullFieldValuesCRMAccount()};
    }

    private CRMAccount[] getCustomCRMAccounts() {
        return new CRMAccount[]{getCRMAccountCustom()};
    }

    private void checkFields(CRMAccount actualCRMAccount, CRMAccount expectedCRMAccount) {
        assertThat(actualCRMAccount, is(samePropertyValuesAs(expectedCRMAccount)));
    }

    private CRMAccount getNullFieldValuesCRMAccount() {
        return new CRMAccount(null, null, null, null, null, null, null, null);
    }

    private CRMAccount getCRMAccountCustom() {
        return new CRMAccount("id", "currency", "displayId", "accountType", "platform", "product", PENDING_AMOUNT, "externalId");
    }
}
