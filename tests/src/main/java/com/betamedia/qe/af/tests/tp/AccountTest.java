package com.betamedia.qe.af.tests.tp;

import com.betamedia.qe.af.core.tests.tp.TPBackEndTest;
import com.betamedia.tp.api.model.Account;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/29/17.
 */
public class AccountTest extends TPBackEndTest{

    /**
     * Create account via TP CRM api
     * and verify that account is created on TP
     */
    @Test
    public void createAccountThroughCRMApi(){
        Account crmAccount = operations().accountOperations().getCRM();
        Account tpAccount = operations().accountOperations().getTP(crmAccount.getId());
        assertEquals(crmAccount.getDisplayId(), tpAccount.getDisplayId());
        assertEquals(crmAccount.getDateCreated(), tpAccount.getDateCreated());
    }
}
