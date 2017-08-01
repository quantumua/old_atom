package com.betamedia.atom.testslibrary.option24.backend.crm;

import com.betamedia.atom.core.testingtype.web.WebBackEndTest;
import com.betamedia.tp.api.model.Account;
import org.apache.commons.lang3.NotImplementedException;
import org.testng.annotations.Test;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/29/17.
 */
public class AccountTest extends WebBackEndTest {

    /**
     * Create account via TP CRM api
     * and verify that account is created on TP
     */
    @Test
    public void createAccountThroughCRMApi() {
        Account crmAccount = operations().accountOperations().getCRM();
        //TODO update test to validate account creation without GS
        throw new NotImplementedException("Deprecated test - GigaSpaces has been removed from application");
    }

}
