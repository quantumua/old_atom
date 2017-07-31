package com.betamedia.atom.testslibrary.option24.web.createnewcustomers.international;

import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;
import com.betamedia.atom.core.api.web.form.Country;
import com.betamedia.atom.core.api.web.form.Currency;
import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import com.betamedia.atom.testslibrary.option24.web.createnewcustomers.CreateNewCustomers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by vsnigur on 7/17/17.
 * tests for registration dialog
 */
public class LongRegistrationWizard extends CreateNewCustomers {

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
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(), FAIL_NAME_NOTIFICATION,
                "Possible input one chars into last name field.");
    }

    /**
     * try register customer with last name more than 20 chars
     */
    @Test(description = "CTW-18649:Last Name field validations: no option to fill more than 20 characters")
    public void verifyImpossibleInputMaxPlusOneCharsIntoLastName() {
        pages().registrationDialog().fillRegisterForm(getCustomerWithLastName(MAX_PLUS_ONE_CHARS_NAME));
        softAssert().assertEquals(pages().registrationDialog().getLastName(), MAX_CHARS_NAME,
                "Possible input more than 20 chars into last name field.");
    }

    /**
     * try type specific symbols into last name field
     */
    @Test(description = "CTW-18648:Last Name field validations: No option to insert anything but letters")
    public void verifyImpossibleInputSpecialSymbolsIntoLastName() {
        pages().registrationDialog().fillRegisterForm(getCustomerWithLastName(INCORRECT_CHARS_NAME));
        softAssert().assertEquals(pages().registrationDialog().getLastName(),EMPTY_STRING,
                "Possible input none char symbols last name field.");
    }

    /**
     * verify error notification if try register customer with one symbol in the first name
     */
    @Test(description = "CTW-18647:First Name field validations: No option to fill less than 2 characters")
    public void verifyImpossibleRegisterCustomerWithOneLetterFirstName() {
        pages().registrationDialog().fillRegisterForm(getCustomerWithFirstName(ONE_SYMBOL_NAME));
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(), FAIL_NAME_NOTIFICATION,
                "Possible register customer with one chars in the first name field.");
    }

    /**
     * verify max input into first name field
     */
    @Test(description = "CTW-18645:First Name field validations: No option to fill more than 20 characters")
    public void verifyImpossibleInputMaxPlusOneCharsIntoFirstName() {
        pages().registrationDialog().fillRegisterForm(getCustomerWithFirstName(MAX_PLUS_ONE_CHARS_NAME));
        softAssert().assertEquals(pages().registrationDialog().getFirstName(), MAX_CHARS_NAME,
                "Possible input more than 20 chars into first name field.");
    }

    /**
     * verify min input into first name field
     */
    @Test(description = "CTW-18644:First Name field validations: No option to insert anything but letters")
    public void verifyImpossibleInputSpecialSymbolsIntoFirstName() {
        pages().registrationDialog().fillRegisterForm(getCustomerWithFirstName(INCORRECT_CHARS_NAME));
        softAssert().assertEquals(pages().registrationDialog().getFirstName(), EMPTY_STRING,
                "Possible input none char symbols last name field.");
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
        softAssert().assertEquals(pages().registrationDialog().getFirstNameStatusError(),"Enter your first name");
        softAssert().assertEquals(pages().registrationDialog().getBorderColorFirstName(), RED_RGB_STYLE);
        softAssert().assertEquals(pages().registrationDialog().getBorderColorLastName(), RED_RGB_STYLE);
        softAssert().assertEquals(pages().registrationDialog().getBorderColorForEmail(), RED_RGB_STYLE);
        softAssert().assertEquals(pages().registrationDialog().getBorderForPrefixField(), RED_RGB_STYLE);
        softAssert().assertEquals(pages().registrationDialog().getBorderColorPhone(), RED_RGB_STYLE);
        softAssert().assertEquals(pages().registrationDialog().getBorderColorForPassword(), RED_RGB_STYLE);
    }

    /**
     * - generate user account using builder;
     * - validate email name field;
     */
    @Test(description = "CTW-5468:Email field validations")
    public void emailFieldValidations() {

        pages().registrationDialog().fillRegisterForm(getCustomerWithEmail(INCORRECT_EMAIL));
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Enter a valid email address.");

        pages().registrationDialog().fillRegisterForm(getCustomerWithEmail(INCORRECT_EMAIL_TWO_AT));
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Enter a valid email address.");

        pages().registrationDialog().fillRegisterForm(getCustomerWithEmail(INCORRECT_EMAIL_NO_AT));
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Enter a valid email address.");

        pages().registrationDialog().fillRegisterForm(getCustomerWithEmail(INCORRECT_EMAIL_DIGIT_DOMAIN));
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Enter a valid email address.");

        CustomerRegistrationInfo customerRegistrationInfo= getCustomerWithEmail(INCORRECT_CHARS_IN_EMAIL);
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        softAssert().assertEquals(pages().registrationDialog().getEmail(),
                EMPTY_STRING);
        customerRegistrationInfo.setEmail(EMPTY_STRING);
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getBorderColorForEmail(),
                RED_RGB_STYLE);
        customerRegistrationInfo.setEmail(CORRECT_EMAIL);
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        softAssert().assertEquals(pages().registrationDialog().getBorderColorForEmail(),
                GREEN_RGB_STYLE);
    }

    /**
     * - generate user account using builder;
     * - validate selected prefix saved in the form;
     * - validate registration is not available for Israel country;
     */
    @Test(description = "CTW-5469:Phone Prefix field validation")
    public void verifyPhonePrefixFunctionality() {
        pages().registrationDialog().setCountryPrefix(Country.AUSTRALIA.getName());
        softAssert().assertEquals(pages().registrationDialog().getCountryPrefix(),
                Country.AUSTRALIA.getPhonePrefix());
        CustomerRegistrationInfo customerRegistrationInfo = getCustomerWithPrefix(Country.AUSTRALIA.getPhonePrefix());
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Registration Is Not Available In Your Country");
        softAssert().assertEquals(pages().registrationDialog().getBorderForCountryField(),
                RED_RGB_STYLE);
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
        softAssert().assertEquals(pages().registrationDialog().countrySearch(SEARCH_BY_SYMBOL, Country.IRELAND.getName()),
                Country.IRELAND.getName(),
                "Country was not available in the search result.");
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
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Registration Is Not Available In Your Country",
                NO_ERROR_MESSAGE);
        pages().browser().refreshPage();
        pages().registrationDialog().setCountryPrefix(Country.ISRAEL.getName());
        pages().registrationDialog().setPasswordFields(customer.getPassword(),customer.getPassword());
        pages().registrationDialog().clickAgreement();
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Registration Is Not Available In Your Country",
                NO_ERROR_MESSAGE);
    }

    /**
     * check currency selection is saved
     * check EUR is not available as currency
     *
     */
    @Test(description = "CTW-5473:Currency dropdown field validation")
    public void validateCurrencyDropDownField() {
        CustomerRegistrationInfo customer = getCustomer();
        customer.setCurrency(Currency.USD.getFullName());
        pages().registrationDialog().fillRegisterForm(customer);
        softAssert().assertFalse(pages().registrationDialog().availableCurrencies().isEmpty(),
                "Currencies are not available");
        softAssert().assertFalse(pages().registrationDialog().getCurrency().equalsIgnoreCase(customer.getCurrency()),
                "Currency was not saved after selection.");
        softAssert().assertFalse(pages().registrationDialog().availableCurrencies()
                        .stream().anyMatch(value->value.contains(Currency.USD.getFullName())),
                "USD currency is available in the list.");
    }

    /**
     * - check password fields for symbols input
     */
    @Test(description = "CTW-5474:Password fields validations")
    public void validatePasswordsField() {
        CustomerRegistrationInfo customer = getCustomer();
        customer.setPassword(EMPTY_STRING);
        registerNewCustomer(customer);
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Incorrect password.",
                 NO_ERROR_MESSAGE);
        customer.setPassword(INCORRECT_PASSWORD);
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Enter digits and letters only (lower and upper case)",
                NO_ERROR_MESSAGE);
        softAssert().assertEquals(pages().registrationDialog().getPasswordSize(),
                "15");
        pages().browser().refreshPage();
        customer.setPassword(FOUR_CHARS);
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Enter between 5 to 15 characters",
                NO_ERROR_MESSAGE);
        pages().browser().refreshPage();
        pages().registrationDialog().fillRegisterForm(
                CustomerRegistrationInfo.builder(WebSiteNamingStrategy.get()).build());
        pages().registrationDialog().clickAgreement();
        pages().registrationDialog().submitRegisterForm();
        pages().registrationDialog().agreementStatus();
        softAssert().assertEquals(pages().registrationDialog().getBorderColorForPassword(),
                GREEN_RGB_STYLE);
        pages().registrationDialog().clickAgreement();
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertTrue(pages().startTradeDialog().exists(),
                "Additional details window did not appear.");
    }

    /**
     * - check adult checkbox confirmation message
     */
    @Test(description = "CTW-5475:''I'm over 18'' checkbox validation")
    public void validateAdultCheckBox() {
        pages().registrationDialog().fillRegisterForm(getCustomer());
        pages().registrationDialog().clickAgreement();
        pages().registrationDialog().submitRegisterForm();
        validateValue("You must read and agree to the above terms!",
                pages().registrationDialog().agreementStatus(),
                "Adult confirmation message did not appear");
    }

    /**
     * - verify Submit button behaviour in the register dialog
     */
    @Test(description = "CTW-5476:Submit button tests")
    public void checkSubmitButtonForRegisterNewCustomer() {
        pages().registrationDialog().fillRegisterForm(getCustomer());
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertTrue(pages().loadingDialog().isDisplayed(), "Loading popup appeared.");
        softAssert().assertFalse(pages().registrationDialog().submitIsEnabled(),
                "Submit button is enabled during customer registration.");
        softAssert().assertTrue(pages().creditCardDeposit().isDisplayed(),
                "Start Trade dialog did not appear after registration.");
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
