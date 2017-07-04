package com.betamedia.atom.testslibrary.option24.backend.crm.mobile;

import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.core.testingtype.tp.TPBackEndTest;
import com.betamedia.atom.core.testlink.annotations.TestLinkDisplayId;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/**
 * Created by Oleksandr Losiev on 4/21/17.
 */
public class MobileCRMLoginTest extends TPBackEndTest {

    @Test
    @Parameters({"username", "password"})
    @TestLinkDisplayId("CTW-11804")
    public void testLogin(String username, String password) {
        final String expectedPlatform = "scipio";
        final String expectedProduct = "binary";

      CRMCustomer loggedInCustomer = operations().customerOperations().login(username, password);
        assertEquals(username, loggedInCustomer.getUserName());

        assertFalse(Arrays.stream(loggedInCustomer.getAccounts())
                .filter(account -> account.getPlatform().equals(expectedPlatform) &&
                            account.getProduct().equals(expectedProduct))
                .collect(Collectors.toList())
                .isEmpty()
        );
    }

    @Test
    @Parameters("customerId")
    @TestLinkDisplayId("CTW-11803")
    public void testLogout(String customerId) {
        operations().customerOperations().logout(customerId);
    }
}
