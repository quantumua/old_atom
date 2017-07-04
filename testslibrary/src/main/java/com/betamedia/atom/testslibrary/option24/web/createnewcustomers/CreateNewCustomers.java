package com.betamedia.atom.testslibrary.option24.web.createnewcustomers;

import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.CRMMobileAPINamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.testslibrary.option24.end2end.bmw.AbstractOnboardingUserExperienceTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by vsnigur on 7/3/17.
 */
public class CreateNewCustomers extends AbstractOnboardingUserExperienceTest {

    private String COUNTRY_CODE = "us";

    @BeforeMethod
    public void before() {

    }

    @Test(description = "CTW-5079:verify sign up button gives you the open account pop up")
    public void verifySignUpButtonRedirectToOnboardingOpenAccount() {
        pages().topNavigationPage().signUp();
        assertTrue("Registration dialog does not appear.",pages().registrationDialog().exists());
        assertEquals("Apply for an account with 24option", pages().registrationDialog().dialogCaption());
    }

    @Test(description = "CTW-5089:main page elements verification")
    public void applyForAnAccountElementsVerification() {
        pages().topNavigationPage().signUp();
        assertTrue("Logo does not appear.", pages().registrationDialog().logoExists());
        assertTrue("Registration dialog does not appear.",pages().registrationDialog().languageExists());
        assertTrue("Login button does not appear.", pages().registrationDialog().loginButtonExists());
        assertTrue("Risk notification does not appear.", pages().registrationDialog().riskMessageExists());
        assertTrue("Live chat does not appear.", pages().registrationDialog().liveChatExists());
    }

    @Test(description = "CTW-5375:first name field validations")
    public void applyForAnAccountFirstNameFieldValidations() {
        CustomerRO customerRO = CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build();
        customerRO.setFirstName("a");
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(customerRO);
        assertEquals("Enter at least 2 characters", pages().registrationDialog().firstNameGetMessage());
    }

}
