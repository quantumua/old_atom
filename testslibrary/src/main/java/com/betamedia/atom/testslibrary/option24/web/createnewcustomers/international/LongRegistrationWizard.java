package com.betamedia.atom.testslibrary.option24.web.createnewcustomers.international;

import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;
import com.betamedia.atom.core.api.web.form.Country;
import com.betamedia.atom.core.api.web.form.Currency;
import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import com.betamedia.atom.testslibrary.option24.end2end.bmw.AbstractOnboardingUserExperienceTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.betamedia.atom.testslibrary.option24.web.createnewcustomers.CreateNewCustomers.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

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
        pages().registrationDialog().exists();
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
        CustomerRegistrationInfo customerRegistrationInfo =
                CustomerRegistrationInfo.builder(WebSiteNamingStrategy.get())
                .withPhoneCountryPrefix(Country.ZAMBIA.getPhonePrefix())
                .build();
        pages().registrationDialog().exists();
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        pages().registrationDialog().submitRegisterForm();
        pages().creditCardDeposit().waitforCreditCardDepositPage();
        operations().onBoardingOperations().assertUserCreatedInDatabase(customerRegistrationInfo.getEmail());
    }

    /**
     * try register customer with one char in the last name
     */
    @Test(description = "CTW-18650:Last Name field validations: No option to fill less than 2 characters")
    public void verifyImpossibleInputLessThanTwoCharsIntoLastName() {
        pages().registrationDialog().fillRegisterForm(getCustomerWithLastName(ONE_SYMBOL_NAME));
        pages().registrationDialog().submitRegisterForm();
        assertEquals("Possible input one chars into last name field.",
                FAIL_NAME_NOTIFICATION, pages().registrationDialog().getErrorMessageNotification());
    }

    /**
     * try register customer with last name more than 20 chars
     */
    @Test(description = "CTW-18649:Last Name field validations: no option to fill more than 20 characters")
    public void verifyImpossibleInputMaxPlusOneCharsIntoLastName() {
        pages().registrationDialog().fillRegisterForm(getCustomerWithLastName(MAX_PLUS_ONE_CHARS_NAME));
        assertEquals("Possible input more than 20 chars into last name field.",
                MAX_CHARS_NAME, pages().registrationDialog().getLastName());
    }

    /**
     * try type specific symbols into last name field
     */
    @Test(description = "CTW-18648:Last Name field validations: No option to insert anything but letters")
    public void verifyImpossibleInputSpecialSymbolsIntoLastName() {
        pages().registrationDialog().fillRegisterForm(getCustomerWithLastName(INCORRECT_CHARS_NAME));
        assertEquals("Possible input none char symbols last name field.",
                EMPTY_STRING, pages().registrationDialog().getLastName());
    }

    /**
     * verify error notification if try register customer with one symbol in the first name
     */
    @Test(description = "CTW-18647:First Name field validations: No option to fill less than 2 characters")
    public void verifyImpossibleRegisterCustomerWithOneLetterFirstName() {
        pages().registrationDialog().fillRegisterForm(getCustomerWithFirstName(ONE_SYMBOL_NAME));
        pages().registrationDialog().submitRegisterForm();
        assertEquals("Possible register customer with one chars in the first name field.",
                FAIL_NAME_NOTIFICATION, pages().registrationDialog().getErrorMessageNotification());
    }

    /**
     * verify max input into first name field
     */
    @Test(description = "CTW-18645:First Name field validations: No option to fill more than 20 characters")
    public void verifyImpossibleInputMaxPlusOneCharsIntoFirstName() {
        pages().registrationDialog().fillRegisterForm(getCustomerWithFirstName(MAX_PLUS_ONE_CHARS_NAME));
        assertEquals("Possible input more than 20 chars into first name field.",
                MAX_CHARS_NAME, pages().registrationDialog().getFirstName());
    }

    /**
     * verify min input into first name field
     */
    @Test(description = "CTW-18644:First Name field validations: No option to insert anything but letters")
    public void verifyImpossibleInputSpecialSymbolsIntoFirstName() {
        pages().registrationDialog().fillRegisterForm(getCustomerWithFirstName(INCORRECT_CHARS_NAME));
        assertEquals("Possible input none char symbols last name field.",
                EMPTY_STRING, pages().registrationDialog().getFirstName());
    }

    /**
     *  open register dialog;
     *  submit empty form;
     *  check error notification for first field;
     *  check red border for other not updated fields
     */
    @Test(description = "CTW-5465:Wizard Registration form: submit empty form")
    public void verifyEmptySubmittedForm() {
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
     * - generate user account using builder;
     * - validate email name field;
     */
    @Test(description = "CTW-5468:Email field validations")
    public void emailFieldValidations() {

        pages().registrationDialog().fillRegisterForm(getCustomerWithEmail(INCORRECT_EMAIL));
        pages().registrationDialog().submitRegisterForm();
        assertEquals("Enter a valid email address.",
                pages().registrationDialog().getErrorMessageNotification());

        pages().registrationDialog().fillRegisterForm(getCustomerWithEmail(INCORRECT_EMAIL_TWO_AT));
        pages().registrationDialog().submitRegisterForm();
        assertEquals("Enter a valid email address.",
                pages().registrationDialog().getErrorMessageNotification());

        pages().registrationDialog().fillRegisterForm(getCustomerWithEmail(INCORRECT_EMAIL_NO_AT));
        pages().registrationDialog().submitRegisterForm();
        assertEquals("Enter a valid email address.",
                pages().registrationDialog().getErrorMessageNotification());

        pages().registrationDialog().fillRegisterForm(getCustomerWithEmail(INCORRECT_EMAIL_DIGIT_DOMAIN));
        pages().registrationDialog().submitRegisterForm();
        assertEquals("Enter a valid email address.",
                pages().registrationDialog().getErrorMessageNotification());

        CustomerRegistrationInfo customerRegistrationInfo= getCustomerWithEmail(INCORRECT_CHARS_IN_EMAIL);
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        assertEquals(EMPTY_STRING, pages().registrationDialog().getEmail());
        customerRegistrationInfo.setEmail(EMPTY_STRING);
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        pages().registrationDialog().submitRegisterForm();
        assertEquals(RED_RGB_STYLE, pages().registrationDialog().getBorderColorForEmail());
        customerRegistrationInfo.setEmail(CORRECT_EMAIL);
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        assertEquals(GREEN_RGB_STYLE, pages().registrationDialog().getBorderColorForEmail());
    }

    /**
     * - generate user account using builder;
     * - validate selected prefix saved in the form;
     * - validate registration is not available for Israel country;
     */
    @Test(description = "CTW-5469:Phone Prefix field validation")
    public void verifyPhonePrefixFunctionality() {
        pages().registrationDialog().setCountryPrefix(Country.AUSTRALIA.getName());
        assertEquals(Country.AUSTRALIA.getPhonePrefix(), pages().registrationDialog().getCountryPrefix());
        CustomerRegistrationInfo customerRegistrationInfo = getCustomerWithPrefix(Country.AUSTRALIA.getPhonePrefix());
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        pages().registrationDialog().submitRegisterForm();
        assertEquals("Registration Is Not Available In Your Country",
                pages().registrationDialog().getErrorMessageNotification());
        assertEquals(RED_RGB_STYLE, pages().registrationDialog().getBorderForCountryField());
    }

    /**
     * - generate user account using builder;
     * - validate phone field;
     */
    @Test(description = "CTW-5470:Phone Number field validation")
    public void validateInputsIntoPhoneField() {
        CustomerRegistrationInfo customer = getCustomerWithPhoneNumber(PHONE_NO_DIGITS);
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().submitRegisterForm();
        validateValue(EMPTY_STRING, pages().registrationDialog().getPhoneNumber(),
                "None digits was accepted into phone field.");
        customer.setPhoneNumber(PHONE_FIVE_DIGITS);
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().submitRegisterForm();
        validateValue("Enter between 6 to 15 numbers",pages().registrationDialog().getErrorMessageNotification(),
                "5 digits were accepted by phone field");
    }

    /**
     * - validate search functionality in the country field;
     */
    @Test(description = "CTW-5471:Country dropdown field search engine")
    public void validateSearchFunctionalityInCountry() {
        pages().topNavigationPage().signUp();
        pages().registrationDialog().setCountryPrefix(Country.JORDAN.getName());
        pages().redirectDialog().startTrade();
        pages().registrationDialog().exists();
        assertEquals("Country was not available in the search result.",
                Country.IRELAND.getName(), pages().registrationDialog().countrySearch(SEARCH_BY_SYMBOL, Country.IRELAND.getName()));
    }

    /**
     * - make sure impossible register customer for avoid countries;
     */
    @Test(description = "CTW-5472:Country dropdown validations")
    public void validateCountryDropDownField() {
        CustomerRegistrationInfo customer = getCustomer();
        customer.setCountry(Country.ISRAEL.getName());
        customer.setPhoneCountryPrefix(Country.UNITED_KINGDOM.getPhonePrefix());
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().selectCountry(customer.getCountry());
        pages().registrationDialog().submitRegisterForm();
        assertEquals(NO_ERROR_MESSAGE,
                "Registration Is Not Available In Your Country",
                pages().registrationDialog().getErrorMessageNotification());
        pages().browser().refreshPage();
        pages().registrationDialog().setCountryPrefix(Country.ISRAEL.getName());
        pages().registrationDialog().setPasswordFields(customer.getPassword(),customer.getPassword());
        pages().registrationDialog().clickAgreement();
        pages().registrationDialog().submitRegisterForm();
        assertEquals(NO_ERROR_MESSAGE,
                "Registration Is Not Available In Your Country",
                pages().registrationDialog().getErrorMessageNotification());
    }

    /**
     * check currency selection is saved
     * check USD is not available as currency
     *
     */
    @Test(description = "CTW-5473:Currency dropdown field validation")
    public void validateCurrencyDropDownField() {
        CustomerRegistrationInfo customer = getCustomer();
        customer.setCurrency(Currency.USD.getFullName());
        pages().registrationDialog().fillRegisterForm(customer);
        assertFalse("Currencies are not available",
                pages().registrationDialog().availableCurrencies().isEmpty());
        assertFalse("Currency was not saved after selection.",
                pages().registrationDialog().getCurrency().equalsIgnoreCase(customer.getCurrency()));
        assertFalse("EUR currency is available in the list.",
                pages().registrationDialog().availableCurrencies().contains(Currency.EUR.getShortName()));
    }

    private CustomerRegistrationInfo getCustomerWithLastName(String lastName) {
        CustomerRegistrationInfo customer=getCustomer();
        customer.setLastName(lastName);
        return customer;
    }

    private CustomerRegistrationInfo getCustomerWithFirstName(String firstName) {
        CustomerRegistrationInfo customer=getCustomer();
        customer.setFirstName(firstName);
        return customer;
    }

    private CustomerRegistrationInfo getCustomerWithEmail(String email) {
        CustomerRegistrationInfo customer=getCustomer();
        customer.setEmail(email);
        return customer;
    }

    private CustomerRegistrationInfo getCustomerWithPrefix(String countryNameForPrefix) {
        CustomerRegistrationInfo customer=getCustomer();
        customer.setPhoneCountryPrefix(countryNameForPrefix);
        return customer;
    }

    private CustomerRegistrationInfo getCustomerWithPhoneNumber(String phoneNumber) {
        CustomerRegistrationInfo customer=getCustomer();
        customer.setPhoneNumber(phoneNumber);
        return customer;
    }

    private CustomerRegistrationInfo getCustomer() {
        return CustomerRegistrationInfo.builder(WebSiteNamingStrategy.get())
                .withCountry(Country.ZAMBIA.getName())
                .withPhoneCountryPrefix(Country.ZAMBIA.getPhonePrefix())
                .build();
    }
}
