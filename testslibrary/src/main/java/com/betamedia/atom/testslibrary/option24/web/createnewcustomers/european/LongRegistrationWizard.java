package com.betamedia.atom.testslibrary.option24.web.createnewcustomers.european;

import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.CRMMobileAPINamingStrategy;
import com.betamedia.atom.core.api.web.form.Country;
import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import com.betamedia.atom.testslibrary.option24.web.createnewcustomers.CreateNewCustomers;
import org.testng.Reporter;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by vsnigur on 7/17/17.
 */
public class LongRegistrationWizard extends CreateNewCustomers {

    /**
     * open register form
     * select European country;
     * register user;
     * make sure user appears in the CRM DataBase;
     */
    @Test(description = "CTW-5416:Verify full registration on SEU")
    public void verifySignUpButtonRedirectToOnBoardingOpenAccount() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo.builder(CRMMobileAPINamingStrategy.get())
                .withPhoneCountryPrefix(Country.UNITED_KINGDOM.getPhonePrefix())
                .build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        pages().registrationDialog().submitRegisterForm();
        pages().welcomePage().isStartBtnDisplayed();
        operations().onBoardingOperations().assertUserCreatedInDatabase(customerRegistrationInfo.getEmail());
    }

    /**
     *  open register dialog;
     *  submit empty form;
     *  check error notification for first field;
     *  check red border for other not updated fields
     */
    @Test(description = "CTW-5418:Wizard Registration form: submit empty form")
    public void verifyEmptySubmittedForm() {
        pages().topNavigationPage().signUp();
        pages().registrationDialog().submitRegisterForm();
        assertEquals("Enter your first name", pages().registrationDialog().getFirstNameStatusError());
        assertEquals(RED_RGB_STYLE, pages().registrationDialog().getBorderColorFirstName());
        assertEquals(RED_RGB_STYLE, pages().registrationDialog().getBorderColorLastName());
        assertEquals(RED_RGB_STYLE, pages().registrationDialog().getBorderColorForEmail());
        assertEquals(RED_RGB_STYLE, pages().registrationDialog().getBorderForPrefixField());
        assertEquals(RED_RGB_STYLE, pages().registrationDialog().getBorderColorPhone());
        assertEquals(RED_RGB_STYLE, pages().registrationDialog().getBorderColorForPassword());
    }

    /**
     *  open register dialog;
     *  try input none alphabetical chars into first name
     *  assert that field does not accept special chars
     */
    @Test(description = "CTW-5419:First Name field validations: No option to insert anything but letters")
    public void verifyNoInputNoneCharsSymbolsIntoFirstName() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo.builder(CRMMobileAPINamingStrategy.get())
                .withFirstName(INCORRECT_CHARS)
                .build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        pages().registrationDialog().clickAgreement();
        pages().registrationDialog().submitRegisterForm();
        assertTrue("Special symbols were inputted into first name field.",
                pages().registrationDialog().getFirstName().isEmpty());
    }

    /**
     *  open register dialog;
     *  input max+1 chars into first name field
     *  check that impossible input more than max chars into first name field
     */
    @Test(description = "CTW-18418:First Name field validations: No option to fill more than 20 characters")
    public void verifyImpossibleInputMoreThanTwentyCharsIntoFirstName() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo.builder(CRMMobileAPINamingStrategy.get())
                .withFirstName(MAX_PLUS_ONE_CHARS)
                .build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        pages().registrationDialog().clickAgreement();
        pages().registrationDialog().submitRegisterForm();
        assertEquals("Possible input max+1 chars into first name field.",
                MAX_CHARS, pages().registrationDialog().getFirstName());
    }

    /**
     * open register dialog;
     * input one char into first name field
     * check error message that impossible input less than 2 chars as first name
     */
    @Test(description = "CTW-18419:First Name field validations: No option to fill less than 2 characters")
    public void verifyImpossibleInputLessThanTwoCharsIntoFirstName() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo.builder(CRMMobileAPINamingStrategy.get())
                .withFirstName(ONE_SYMBOL)
                .build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        pages().registrationDialog().clickAgreement();
        pages().registrationDialog().submitRegisterForm();
        assertEquals("Possible input one chars into first name field.",
                FAIL_NAME_NOTIFICATION, pages().registrationDialog().getErrorMessageNotification());
    }

    /**
     * open register dialog;
     * input one char into first name field
     * check error message that impossible input less than 2 chars as first name
     */
    @Test(description = "CTW-5420:Last Name field validations: No option to insert anything but letters")
    public void validateImpossibleInputSpecialCharsIntoLastNameField() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo.builder(CRMMobileAPINamingStrategy.get())
                .withLastName(INCORRECT_CHARS)
                .build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        pages().registrationDialog().clickAgreement();
        pages().registrationDialog().submitRegisterForm();
        assertTrue("Special symbols were inputted into last name field.",
                pages().registrationDialog().getLastName().isEmpty());
    }

    /**
     * open register dialog;
     * input chars and digits into last name field;
     * check error message that possible input only alphabetical chars;
     */
    @Test(description = "CTW-5420:Last Name field validations: No option to insert anything but letters")
    public void validateImpossibleInputAnyCharsExceptLettersIntoLastName() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo.builder(CRMMobileAPINamingStrategy.get())
                .withLastName(SYMBOLS_AND_DIGITS)
                .build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        pages().registrationDialog().clickAgreement();
        pages().registrationDialog().submitRegisterForm();
        assertTrue("Not only letters inputted into last name field.",
                pages().registrationDialog().getLastName().equalsIgnoreCase(SYMBOLS_AND_NO_DIGITS));
    }

    /**
     * try register customer with last name one char
     */
    @Test(description = "CTW-18425:Last Name field validations: No option to fill less than 2 characters")
    public void verifyImpossibleInputLessThanTwoCharsIntoLastName() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo.builder(CRMMobileAPINamingStrategy.get())
                .withLastName(ONE_SYMBOL)
                .build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        pages().registrationDialog().submitRegisterForm();
        assertEquals("Possible input one chars into last name field.",
                FAIL_NAME_NOTIFICATION, pages().registrationDialog().getErrorMessageNotification());
    }

    /**
     * - generate user account using builder;
     * - validate email name field;
     */
    @Test(description = "CTW-5209:email field validations")
    public void emailFieldValidations() {
        CustomerRegistrationInfo customer = CustomerRegistrationInfo.builder(CRMMobileAPINamingStrategy.get())
                .withEmailName(INCORRECT_EMAIL).build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().submitRegisterForm();
        assertEquals("Enter a valid email address.",
                pages().registrationDialog().getErrorMessageNotification());

        customer.setEmail(INCORRECT_EMAIL_TWO_AT);
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().submitRegisterForm();
        assertEquals("Enter a valid email address.",
                pages().registrationDialog().getErrorMessageNotification());

        customer.setEmail(INCORRECT_EMAIL_NO_AT);
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().submitRegisterForm();
        assertEquals("Enter a valid email address.",
                pages().registrationDialog().getErrorMessageNotification());

        customer.setEmail(INCORRECT_EMAIL_DIGIT_DOMAIN);
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().submitRegisterForm();
        assertEquals("Enter a valid email address.",
                pages().registrationDialog().getErrorMessageNotification());

        customer.setEmail(INCORRECT_CHARS_IN_EMAIL);
        pages().registrationDialog().fillRegisterForm(customer);
        assertEquals("", pages().registrationDialog().getEmail());
        customer.setEmail("");
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().submitRegisterForm();
        assertEquals(RED_RGB_STYLE, pages().registrationDialog().getBorderColorForEmail());
        customer.setEmail(CORRECT_EMAIL);
        pages().registrationDialog().fillRegisterForm(customer);
        assertEquals(GREEN_RGB_STYLE, pages().registrationDialog().getBorderColorForEmail());
    }

    /**
     * - generate user account using builder;
     * - validate selected prefix saved in the form;
     * - validate registration is not available for Israel country;
     */
    @Test(description = "CTW-5422:Phone Prefix field validation")
    public void verifyPhonePrefixFunctionality() {
        CustomerRegistrationInfo customer = CustomerRegistrationInfo.builder(CRMMobileAPINamingStrategy.get())
                .withPhoneCountryPrefix(Country.ISRAEL.getPhonePrefix())
                .build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().setCountryPrefix(Country.JORDAN.getName());
        pages().redirectDialog().startTrade();
        pages().registrationDialog().exists();
        assertEquals(Country.JORDAN.getPhonePrefix(), pages().registrationDialog().getCountryPrefix());
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().submitRegisterForm();
        assertEquals("Registration Is Not Available In Your Country",
                pages().registrationDialog().getErrorMessageNotification());
        assertEquals(RED_RGB_STYLE, pages().registrationDialog().getBorderForCountryField());
    }

    /**
     * - generate user account using builder;
     * - validate phone field;
     */
    @Test(description = "CTW-5211:phone number field validations")
    public void validateInputsIntoPhoneField() {
        CustomerRegistrationInfo customer = CustomerRegistrationInfo.builder(CRMMobileAPINamingStrategy.get())
                .withPhoneNumber(PHONE_NO_DIGITS)
                .build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().submitRegisterForm();
        validateValue("", pages().registrationDialog().getPhoneNumber(),
                "None digits was accepted into phone field.");
        customer.setPhoneNumber(PHONE_FIVE_DIGITS);
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().submitRegisterForm();
        validateValue("Enter between 6 to 15 numbers",pages().registrationDialog().getErrorMessageNotification(),
                "5 digits were accepted by phone field");

    }

    private void validateValue( Object expected, Object actual, String errorMessage) {
        Reporter.log(String.format("Check expected: '%s' and actual: '%s' <br/>", expected, actual));
        assertEquals(errorMessage, expected, actual);
    }
}
