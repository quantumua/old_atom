package com.betamedia.atom.testslibrary.option24.web.withdrawalValidate;


import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.api.crm.form.entities.Withdrawal;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.core.testingtype.tp.TPEndToEndTest;
import com.betamedia.atom.core.testingtype.web.WEBEndToEndTest;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/15/17.
 */
public class WithdrawalTest extends WEBEndToEndTest {

    @Test
    public void basicWithdrawalRequest() {
        pages().topNavigationPage().logIn();
        pages().loginPage().login("Web_lcfogu@24options.atom", "123123");
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
    }
        
        
        
//        pages().creditCardDeposit().submit(
//                (CreditCardDeposit.builder()
//                        .withDepositAmount("100")
//                        .withCreditCardNumber("1234-1234-1234-1234")
//                        .withCVV2("123")
//                        .withExpiryDateMonth("1")
//                        .withExpiryDateYear("2020")
//                        .withCardHoldersFirstName("CardHoldersFirstName")
//                        .withCardHoldersLastName("CardHoldersLastName")
//                        .withBillingAddress("BillingAddress")
//                        .withCity("City")
//                        .withZipCode("ZipCode")
//                        .build())
        }
      
        
        
 


