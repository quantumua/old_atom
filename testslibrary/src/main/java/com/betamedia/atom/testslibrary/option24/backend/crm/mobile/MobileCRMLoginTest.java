package com.betamedia.atom.testslibrary.option24.backend.crm.mobile;

import com.betamedia.atom.core.api.tp.entities.response.CRMAccount;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.core.testingtype.tp.TPBackEndTest;
import com.betamedia.atom.core.testlink.annotations.TestLinkDisplayId;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Oleksandr Losiev on 4/21/17.
 */
public class MobileCRMLoginTest extends TPBackEndTest {

    @Override
    protected Class getDataSourceEntity() {
        return UsernamePwdData.class;
    }

    @Override
    protected String getDataSourcePath() {
        return "/data/usernamepwd.csv";
    }

    @Test
    @Parameters({"username", "password"})
    @TestLinkDisplayId("CTW-11804")
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
    @TestLinkDisplayId("CTW-11803")
    public void testLogout(String customerId) {
        operations().customerOperations().logout(customerId);
    }
}
