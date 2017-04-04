package com.betamedia.qe.af.testslibrary.tp;

import com.betamedia.qe.af.core.api.tp.entities.response.CRMAccount;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.qe.af.core.tests.tp.TPBackEndTest;
import com.betamedia.tp.api.model.Account;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/29/17.
 */
public class AccountTest extends TPBackEndTest {

    /**
     * Create account via TP CRM api
     * and verify that account is created on TP
     */
    @Test
    public void createAccountThroughCRMApi() {
        Account crmAccount = operations().accountOperations().getCRM();
        Account tpAccount = operations().accountOperations().getTP(crmAccount.getId());
        assertEquals(crmAccount.getDisplayId(), tpAccount.getDisplayId());
        assertEquals(crmAccount.getDateCreated(), tpAccount.getDateCreated());
    }

    @Test
    public void depositToNewlyCreatedAccountTest() {
        CRMCustomer customer = operations().customerOperations().register();
        double deposit = 100d;
        CRMAccount binaryAccount = customer.getBinaryAccount();
        operations().accountOperations().depositCRM(binaryAccount.getId(), deposit);
        Account tpAccount = operations().accountOperations().getTP(binaryAccount.getId());
        assertEquals(tpAccount.getBalance(), deposit);
    }
}
