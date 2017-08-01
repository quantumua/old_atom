package com.betamedia.atom.testslibrary.option24.web.withdrawalValidate;


import com.betamedia.atom.core.api.crm.form.entities.Withdrawal;
import com.betamedia.atom.core.testingtype.web.WebEndToEndTest;
import static org.testng.AssertJUnit.assertEquals;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/15/17.
 */
public class WithdrawalTest extends WebEndToEndTest {
	String expected = "red";

    @Test
    public void basicWithdrawalRequest() {
        pages().topNavigationPage().logIn();
        pages().loginDialog().login("Web_lcfogu@24options.atom", "123123");
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        pages().topNavigationPage().bankingDetails();
        pages().topNavigationPage().withdrawalTab();
        pages().withdrawalPage().submit((Withdrawal.builder().withwithdrawalAmount("50")
        		.withwithdrawalbankName("6787868686")
        		.withwithdrawalBeneficaryCity("tyuyutyut")
        		.withwithdrawalBeneficaryName("rteteter")
        		.withwithdrawalBranch("tyuyutu")
        		.withwithdrawalIban("tyythbytht")
        		.withwithdrawalCountryCode("DE")
        		.withwwithdrawalRoutingNumberCode("SD")
        		.withwithdrawalSwift("XOXO")
        		.withwithdrawalComment("ERTRTETE")
        		.build())
             
        );
        
        
        String	orange = "orange";
        Reporter.log(String.format("Check expected: '%s' and actual: '%s' <br/>", expected, orange));
        assertEquals(" The validation is not correct", expected, orange);
    }
}
      
        
        
 


