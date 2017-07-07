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
import static org.testng.Assert.assertTrue;

/**
 * Created by Oleksandr Losiev on 4/21/17.
 */
public class MobileCRMLoginTest extends TPBackEndTest {

    @Override
    protected Class getDataSourceEntity() {
        return UserNamePwdData.class;
    }

    @Override
    protected String getDataSourcePath() {
        return "/data/logindata.csv";
    }

    @Test(dataProvider = GENERIC_DATA_PROVIDER)
    public void testLogin(UserNamePwdData cred) {
        final String expectedPlatform = "scipio";
        final String expectedProduct = "binary";

        CRMCustomer loggedInCustomer = operations().customerOperations().login(cred.getUserName(), cred.getUserPwd());
        assertEquals(cred.getUserName(), loggedInCustomer.getUserName());

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
