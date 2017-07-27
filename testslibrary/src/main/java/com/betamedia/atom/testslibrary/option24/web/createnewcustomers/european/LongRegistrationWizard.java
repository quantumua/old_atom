package com.betamedia.atom.testslibrary.option24.web.createnewcustomers.european;

import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.CRMMobileAPINamingStrategy;
import com.betamedia.atom.core.api.web.form.Country;
import com.betamedia.atom.core.api.web.form.Currency;
import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import com.betamedia.atom.testslibrary.option24.web.createnewcustomers.CreateNewCustomers;
import org.testng.Reporter;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
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
    @Test(description = "CTW-5421:Email field validations")
    public void emailFieldValidations() {
        CustomerRegistrationInfo customer = CustomerRegistrationInfo.builder(CRMMobileAPINamingStrategy.get())
                .withEmail(INCORRECT_EMAIL).build();
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
        assertEquals(EMPTY_STRING, pages().registrationDialog().getEmail());
        customer.setEmail(EMPTY_STRING);
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
    @Test(description = "CTW-5423:Phone Number field validation")
    public void validateInputsIntoPhoneField() {
        CustomerRegistrationInfo customer = CustomerRegistrationInfo.builder(CRMMobileAPINamingStrategy.get())
                .withPhoneNumber(PHONE_NO_DIGITS)
                .build();
        pages().topNavigationPage().signUp();
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
    @Test(description = "CTW-5424:Country dropdown field search engine")
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
    @Test(description = "CTW-5425:Country dropdown validations")
    public void validateCountryDropDownField() {
        CustomerRegistrationInfo customer = CustomerRegistrationInfo.builder(CRMMobileAPINamingStrategy.get())
                .withPhoneCountryPrefix(Country.UNITED_KINGDOM.getPhonePrefix())
                .withCountry(Country.ISRAEL.getName())
                .build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().selectCountry(customer.getCountry());
        pages().redirectDialog().startTrade();
        pages().registrationDialog().exists();
        pages().registrationDialog().setPasswordFields(customer.getPassword(),customer.getPassword());
        pages().registrationDialog().clickAgreement();
        pages().registrationDialog().submitRegisterForm();
        assertEquals(NO_ERROR_MESSAGE,
                "Registration Is Not Available In Your Country",
                pages().registrationDialog().getErrorMessageNotification());
        pages().registrationDialog().setCountryPrefix(Country.ISRAEL.getName());
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
    @Test(description = "CTW-5426:Currency dropdown field validation")
    public void validateCurrencyDropDownField() {
        CustomerRegistrationInfo customer = CustomerRegistrationInfo.builder(CRMMobileAPINamingStrategy.get())
                .withCountry(Country.UNITED_KINGDOM.getName())
                .withPhoneCountryPrefix(Country.UNITED_KINGDOM.getPhonePrefix())
                .withCurrency(Currency.EUR.getFullName())
                .build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(
                customer);
        assertFalse("Currencies are not available",
                pages().registrationDialog().availableCurrencies().isEmpty());
        assertFalse("Currency was not saved after selection.",
                pages().registrationDialog().getCurrency().equalsIgnoreCase(customer.getCurrency()));
        assertFalse("USD currency is available in the list.",
                pages().registrationDialog().availableCurrencies().contains(Currency.USD.getShortName()));
    }

    /**
     * - check password fields for symbols input
     */
    @Test(description = "CTW-5427:Password fields validations")
    public void validatePasswordsField() {
        CustomerRegistrationInfo customer=fillRegisterCustomerDialog();
        pages().registrationDialog().submitRegisterForm();
        assertEquals(NO_ERROR_MESSAGE,"Incorrect password.",
                pages().registrationDialog().getErrorMessageNotification());
        customer.setPassword(INCORRECT_PASSWORD);
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().submitRegisterForm();
        assertEquals(NO_ERROR_MESSAGE,"Enter digits and letters only (lower and upper case)",
                pages().registrationDialog().getErrorMessageNotification());
        assertEquals("15",
                pages().registrationDialog().getPasswordSize());
        pages().browser().refreshPage();
        pages().topNavigationPage().signUp();
        customer.setPassword(FOUR_CHARS);
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().submitRegisterForm();
        assertEquals(NO_ERROR_MESSAGE, "Enter between 5 to 15 characters",
                pages().registrationDialog().getErrorMessageNotification());
        pages().browser().refreshPage();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(
                CustomerRegistrationInfo.builder(CRMMobileAPINamingStrategy.get()).build());
        pages().registrationDialog().clickAgreement();
        pages().registrationDialog().submitRegisterForm();
        pages().registrationDialog().agreementStatus();
        assertEquals(GREEN_RGB_STYLE,
                pages().registrationDialog().getBorderColorForPassword());
        pages().registrationDialog().clickAgreement();
        pages().registrationDialog().submitRegisterForm();
        assertTrue("Additional details window did not appear.",
                pages().startTradeDialog().exists());
    }

    /**
     * - check adult checkbox confirmation message
     */
    @Test(description = "CTW-5428:''I'm over 18'' checkbox validation")
    public void validateAdultCheckBox() {
        fillRegisterCustomerDialog();
        pages().registrationDialog().clickAgreement();
        pages().registrationDialog().submitRegisterForm();
        validateValue("You must read and agree to the above terms!",
                pages().registrationDialog().agreementStatus(),
                "Adult confirmation message did not appear");
    }

    /**
     * - verify Submit button behaviour in the register dialog
     */
    @Test(description = "CTW-5429:Submit button tests")
    public void checkSubmitButtonForRegisterNewCustomer() {
        fillRegisterCustomerDialog();
        pages().registrationDialog().submitRegisterForm();
        assertTrue("Loading popup appeared.", pages().loadingDialog().isDisplayed());
        assertFalse("Submit button is enabled during customer registration.",
                pages().registrationDialog().submitIsEnabled());
        assertTrue("Start Trade dialog did not appear after registration.",
                pages().welcomePage().isStartBtnDisplayed());
    }

    /**
     *  register customer
     *  verify customer for existence in the CRM Database
     */
    @Test(description = "CTW-5430:Full registration (including CRM+ email validations)")
    public void checkValidateEmailForRegisteredNewCustomer() {
        CustomerRegistrationInfo customer = fillRegisterCustomerDialog();
        pages().registrationDialog().submitRegisterForm();
        pages().welcomePage().isStartBtnDisplayed();
        operations().onBoardingOperations().assertUserCreatedInDatabase(customer.getEmail());
    }

    /**
     *  register customer
     *  verify customer for existence in the CRM Database
     */
    @Test(description = "CTW-5626:SEU: Registration form - Mandatory fields E2E")
    public void checkCheckMandatoryFieldsInTheCustomerRegistrationForm() {
        CustomerRegistrationInfo customer = getCustomer();
        customer.setFirstName(EMPTY_STRING);
        pages().topNavigationPage().signUp();
        registerNewCustomer(customer);
        assertEquals("Enter your first name", pages().registrationDialog().getErrorMessageNotification());
        assertEquals(RED_RGB_STYLE, pages().registrationDialog().getBorderColorFirstName());

        customer = getCustomer();
        customer.setLastName(EMPTY_STRING);
        registerNewCustomer(customer);
        assertEquals("Enter your last name", pages().registrationDialog().getErrorMessageNotification());
        assertEquals(RED_RGB_STYLE, pages().registrationDialog().getBorderColorLastName());

        customer = getCustomer();
        customer.setEmail(EMPTY_STRING);
        registerNewCustomer(customer);
        assertEquals("Enter a valid email address.", pages().registrationDialog().getErrorMessageNotification());
        assertEquals(RED_RGB_STYLE, pages().registrationDialog().getBorderColorForEmail());

        customer = getCustomer();
        customer.setPhoneCountryPrefix(EMPTY_PHONE_PREFIX);
        registerNewCustomer(customer);
        assertEquals("Country is a mandatory field.", pages().registrationDialog().getErrorMessageNotification());
        assertEquals(RED_RGB_STYLE, pages().registrationDialog().getBorderForPrefixField());

        customer = getCustomer();
        customer.setPhoneNumber(EMPTY_STRING);
        registerNewCustomer(customer);
        assertEquals("Enter at least 6 characters", pages().registrationDialog().getErrorMessageNotification());
        assertEquals(RED_RGB_STYLE, pages().registrationDialog().getBorderColorPhone());

        customer = getCustomer();
        customer.setPassword(EMPTY_STRING);
        pages().browser().deleteAllCookies();
        pages().browser().refreshPage();
        pages().topNavigationPage().signUp();
        registerNewCustomer(customer);
        assertEquals("Incorrect password.", pages().registrationDialog().getErrorMessageNotification());
        assertEquals(RED_RGB_STYLE, pages().registrationDialog().getBorderColorForPassword());

        pages().browser().deleteAllCookies();
        pages().browser().refreshPage();
        fillRegisterCustomerDialog(getCustomer());
        pages().registrationDialog().clickAgreement();
        pages().registrationDialog().submitRegisterForm();
        validateValue("You must read and agree to the above terms!",
                pages().registrationDialog().agreementStatus(),
                "Adult confirmation message did not appear");

        pages().browser().deleteAllCookies();
        pages().browser().refreshPage();
        customer=fillRegisterCustomerDialog();
        pages().registrationDialog().submitRegisterForm();
        pages().welcomePage().isStartBtnDisplayed();
        operations().onBoardingOperations().assertUserCreatedInDatabase(customer.getEmail());
    }

    private void validateValue( Object expected, Object actual, String errorMessage) {
        Reporter.log(String.format("Check expected: '%s' and actual: '%s' <br/>", expected, actual));
        assertEquals(errorMessage, expected, actual);
    }

    private CustomerRegistrationInfo getCustomer() {
        return CustomerRegistrationInfo.builder(CRMMobileAPINamingStrategy.get())
                .build();
    }
}
