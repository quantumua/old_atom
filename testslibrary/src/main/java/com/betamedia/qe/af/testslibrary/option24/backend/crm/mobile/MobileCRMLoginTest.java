package com.betamedia.qe.af.testslibrary.option24.backend.crm.mobile;

import com.betamedia.qe.af.core.api.tp.entities.response.CRMAccount;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.qe.af.core.testingtype.tp.TPBackEndTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Oleksandr Losiev on 4/21/17.
 */
public class MobileCRMLoginTest extends TPBackEndTest {

    @Test
    @Parameters({"username", "password"})
    public void testLogin(String username, String password) {
        final String expectedPlatform = "scipio";
        final String expectedProduct = "binary";

        CRMCustomer loggedInCustomer = operations().customerOperations().login(username, password);
        assertEquals(username, loggedInCustomer.getUserName());

        boolean scipioBinaryAccountFound = false;
        for (CRMAccount account : loggedInCustomer.getAccounts()) {
            if (account.getPlatform().equals(expectedPlatform) && account.getProduct().equals(expectedProduct)) {
                scipioBinaryAccountFound = true;
            }
        }
        assertTrue(scipioBinaryAccountFound);
    }

    @Test
    @Parameters("customerId")
    public void testLogout(String customerId) {
        operations().customerOperations().logout(customerId);
    }
}
