package com.betamedia.atom.testslibrary.samples;

import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.core.testingtype.web.WebBackEndTest;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import com.betamedia.atom.testslibrary.option24.backend.crm.mobile.UserNamePwdData;
import org.testng.Reporter;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/**
 * Created by Oleksandr Losiev on 4/21/17.
 */
public class TestLinkReportIntegrationDemonstrationTest extends WebBackEndTest {

    @Override
    protected Class getDataSourceEntity() {
        return UserNamePwdData.class;
    }

    @Override
    protected String getDataSourcePath() {
        return "/data/logindata.csv";
    }

    /**
     * Demo based on {@link com.betamedia.atom.testslibrary.option24.backend.crm.mobile.MobileCRMLoginTest#testLogin(UserNamePwdData)}
     * Everything logged through {@link Reporter#log(String)} will be added to TestLink notes field
     */
    @TestLinkProperties(buildId = 267, planId = 112063)
    @Test(dataProvider = GENERIC_DATA_PROVIDER)
    public void testLogin(UserNamePwdData cred) {
        Reporter.log("This message will be displayed in TestLink");
        CRMCustomer loggedInCustomer = operations().customerOperations().login(cred.getUserName(), cred.getUserPwd());
        assertEquals(cred.getUserName(), loggedInCustomer.getUserName());
        Reporter.log("Username validated = " + cred.getUserName());
        assertFalse(Arrays.stream(loggedInCustomer.getAccounts())
                .filter(account -> account.getPlatform().equals("scipio") &&
                        account.getProduct().equals("binary"))
                .collect(Collectors.toList())
                .isEmpty()
        );
        Reporter.log("Account list not empty  222");
    }
}