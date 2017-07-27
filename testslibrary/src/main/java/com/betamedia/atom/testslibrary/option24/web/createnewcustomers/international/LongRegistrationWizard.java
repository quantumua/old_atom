package com.betamedia.atom.testslibrary.option24.web.createnewcustomers.international;

import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.CRMMobileAPINamingStrategy;
import com.betamedia.atom.core.api.web.form.Country;
import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import com.betamedia.atom.testslibrary.option24.end2end.bmw.AbstractOnboardingUserExperienceTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.betamedia.atom.testslibrary.option24.web.createnewcustomers.CreateNewCustomers.*;
import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by vsnigur on 7/17/17.
 * tests for registration dialog
 */
public class LongRegistrationWizard extends AbstractOnboardingUserExperienceTest {

    @BeforeMethod
    public void beforeTest() {
        pages().topNavigationPage().signUp();
        pages().registrationDialog().setCountryPrefix(Country.ZAMBIA.getName());
        pages().redirectDialog().startTrade();
    }


    /**
     * open register form
     * select country from international group;
     * redirect to the international site;
     * register user;
     * make sure user appears in the CRM DataBase;
     */
    @Test(description = "CTW-5764:Verify full registration on SINT")
    public void verifySignUpButtonRedirectToOnBoardingOpenAccount() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo.builder(CRMMobileAPINamingStrategy.get())
                .withPhoneCountryPrefix(Country.ZAMBIA.getPhonePrefix())
                .build();
        pages().registrationDialog().exists();
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        pages().registrationDialog().submitRegisterForm();
        pages().creditCardDeposit().waitforCreditCardDepositPage();
        operations().onBoardingOperations().assertUserCreatedInDatabase(customerRegistrationInfo.getEmail());
    }

    /**
     * try register customer with last name one char
     */
    @Test(description = "CTW-18425:Last Name field validations: No option to fill less than 2 characters")
    public void verifyImpossibleInputLessThanTwoCharsIntoLastName() {
        pages().registrationDialog().fillRegisterForm(getCustomerWithLastName(ONE_SYMBOL));
        pages().registrationDialog().submitRegisterForm();
        assertEquals("Possible input one chars into last name field.",
                FAIL_NAME_NOTIFICATION, pages().registrationDialog().getErrorMessageNotification());
    }

    /**
     * try register customer with last name more than 20 chars
     */
    @Test(description = "CTW-18649:Last Name field validations: no option to fill more than 20 characters")
    public void verifyImpossibleInputMaxPlusOneCharsIntoLastName() {
        pages().registrationDialog().fillRegisterForm(getCustomerWithLastName(MAX_PLUS_ONE_CHARS));
        assertEquals("Possible input more than 20 chars into last name field.",
                MAX_CHARS, pages().registrationDialog().getLastName());
    }

    /**
     * try type specific symbols into last name field
     */
    @Test(description = "CTW-18648:Last Name field validations: No option to insert anything but letters")
    public void verifyImpossibleInputSpecialSymbolsIntoLastName() {
        pages().registrationDialog().fillRegisterForm(getCustomerWithLastName(MAX_PLUS_ONE_CHARS));
        assertEquals("Possible input more than 20 chars into last name field.",
                MAX_CHARS, pages().registrationDialog().getLastName());
    }

    /**
     * verify error notification if try register customer with one symbol in the first name
     */
    @Test(description = "CTW-18647:First Name field validations: No option to fill less than 2 characters")
    public void verifyImpossibleRegisterCustomerWithOneLetterName() {
        pages().registrationDialog().fillRegisterForm(getCustomerWithLastName(MAX_PLUS_ONE_CHARS));
        assertEquals("Possible input more than 20 chars into last name field.",
                MAX_CHARS, pages().registrationDialog().getLastName());
    }

    private CustomerRegistrationInfo getCustomerWithLastName(String lastName) {
        CustomerRegistrationInfo customer = CustomerRegistrationInfo.builder(CRMMobileAPINamingStrategy.get())
                .withCountry(Country.ZAMBIA.getName())
                .withPhoneCountryPrefix(Country.ZAMBIA.getPhonePrefix())
                .withLastName(lastName)
                .build();
        customer.setLastName(lastName);
        return customer;
    }

}
