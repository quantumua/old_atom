package com.betamedia.atom.testslibrary.option24.web.createnewcustomers.european;

import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;
import com.betamedia.atom.core.api.web.form.Country;
import com.betamedia.atom.core.api.web.form.Currency;
import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import com.betamedia.atom.core.fwdataaccess.repository.util.Language;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import com.betamedia.atom.testslibrary.option24.web.createnewcustomers.CreateNewCustomers;
import org.testng.annotations.Test;

import static com.betamedia.atom.core.dsl.pages.extensions.LocalizationOperations.Direction.RTL;

/**
 * Created by vsnigur on 7/17/17.
 */
public class LongRegistrationWizard extends CreateNewCustomers {

    private static final String LOCALE = "/en";
    private static final String LICENSE_TERMS_DOCUMENT = SERVER_DOCUMENT+LOCALE+"/clientagreement_24option.pdf";
    private static final String BONUS_TERMS_AND_CONDITIONS_DOCUMENT = SERVER_DOCUMENT+LOCALE+"/bonustermsandconditions_24option.pdf";
    private static final String PRIVACY_POLICY_DOCUMENT = SERVER_DOCUMENT+LOCALE+"/privacypolicy_24option.pdf";

    /**
     * open register form
     * select European country;
     * register user;
     * make sure user appears in the CRM DataBase;
     */

    @Test(description = "CTW-5416:Verify full registration on SEU")
    @TestLinkProperties(displayId = "CTW-5416")
    @Override
    public void verifySignUpButtonRedirectToOnboardingOpenAccount() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo.builder(WebSiteNamingStrategy.get())
                .withPhoneCountryPrefix(Country.UNITED_KINGDOM.getPhonePrefix())
                .build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        pages().registrationDialog().submitRegisterForm();
        pages().welcomeDialog().isStartBtnDisplayed();
        operations().onBoardingOperations().assertUserCreatedInDatabase(customerRegistrationInfo.getEmail());
    }

    /**
     * verify correct localization after registration for DEU language
     */
    @Test(description = "CTW-5442:SEU: Correct redirect after open account slide submit (different languages)")
    @TestLinkProperties(displayId = "CTW-5442")
    public void verifyRedirectAfterOpenAccountSlideSubmit() {
        Language language = Language.GERMAN;
        pages().topNavigationPage().selectLanguage(language.code);
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo
                .builder(WebSiteNamingStrategy.get()).build();
        pages().loadingDialog().isDisplayed();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        pages().registrationDialog().submitRegisterForm();
        pages().welcomeDialog().isStartBtnDisplayed();
        pages().welcomeDialog().validateCaption(language);
        pages().welcomeDialog().validateStartButtonCaption(language);
    }

    /**
     * verify legal term and conditions link
     */
    @Test(description = "CTW-18362:Legal terms and conditions")
    @TestLinkProperties(displayId = "CTW-18362")
    public void verifyLegalTermsAndConditionsDocument() {
        pages().topNavigationPage().signUp();
        pages().registrationDialog().legallTermsAndConditionsLinkOpen();
        pages().browser().switchToTab(SECOND_TAB);
        pages().browser().waitUntilPageLoad();
        softAssert().assertEquals(pages().browser().getTabUrl(SECOND_TAB), LICENSE_TERMS_DOCUMENT);
        pages().browser().switchToTab(FIRST_TAB);
        softAssert().assertEquals(pages().registrationDialog().getLegallTermsAndConditionsLink(),
                getLegalTermsAndConditionsExpectedLink());
    }

    /**
     * verify BonusTermsConditions link
     */
    @Test(description = "CTW-18365:Bonus terms and conditions")
    @TestLinkProperties(displayId = "CTW-18365")
    public void verifyBonusTermsConditionsLinks() {
        pages().topNavigationPage().signUp();
        pages().registrationDialog().bonusTermsConditionsLinkOpen();
        pages().browser().switchToTab(SECOND_TAB);
        pages().browser().waitUntilPageLoad();
        softAssert().assertEquals(pages().browser().getTabUrl(SECOND_TAB), BONUS_TERMS_AND_CONDITIONS_DOCUMENT);
        pages().browser().switchToTab(FIRST_TAB);
        softAssert().assertEquals(pages().registrationDialog().getBonusTermsConditionsLink(),
                getBonusTermsConditionsExpectedLink());
    }

    /**
     * verify CookiePolicy link
     */
    @Test(description = "CTW-18369:Cookie policy")
    @TestLinkProperties(displayId = "CTW-18369")
    public void verifyCookiePolicyLinks() {
        pages().topNavigationPage().signUp();
        pages().registrationDialog().cookiePolicyLinkOpen();
        pages().browser().switchToTab(SECOND_TAB);
        pages().browser().waitUntilPageLoad();
        softAssert().assertEquals(pages().browser().getTabUrl(SECOND_TAB), PRIVACY_POLICY_DOCUMENT);
        pages().browser().switchToTab(FIRST_TAB);
        softAssert().assertEquals(pages().registrationDialog().getCookiePolicyLink(),
                getCookiePolicyExpectedLink());
    }

    /**
     *  open register dialog;
     *  submit empty form;
     *  check error notification for first field;
     *  check red border for other not updated fields
     */
    @Test(description = "CTW-5418:Wizard Registration form: submit empty form")
    @TestLinkProperties(displayId = "CTW-5418")
    public void verifyEmptySubmittedForm() {
        pages().topNavigationPage().signUp();
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
     *  open register dialog;
     *  try input none alphabetical chars into first name
     *  assert that field does not accept special chars
     */
    @Test(description = "CTW-5419:First Name field validations: No option to insert anything but letters")
    @TestLinkProperties(displayId = "CTW-5419")
    public void verifyNoInputNoneCharsSymbolsIntoFirstName() {
        CustomerRegistrationInfo customerRegistrationInfo =
                CustomerRegistrationInfo.builder(WebSiteNamingStrategy.get())
                .withFirstName(INCORRECT_CHARS_NAME)
                .build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        pages().registrationDialog().clickAgreement();
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertTrue( pages().registrationDialog().getFirstName().isEmpty(),
                "Special symbols were inputted into first name field.");
    }

    /**
     *  open register dialog;
     *  input max+1 chars into first name field
     *  check that impossible input more than max chars into first name field
     */
    @Test(description = "CTW-18418:First Name field validations: No option to fill more than 20 characters")
    @TestLinkProperties(displayId = "CTW-18418")
    public void verifyImpossibleInputMoreThanTwentyCharsIntoFirstName() {
        CustomerRegistrationInfo customerRegistrationInfo =
                CustomerRegistrationInfo.builder(WebSiteNamingStrategy.get())
                .withFirstName(MAX_PLUS_ONE_CHARS_NAME)
                .build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        pages().registrationDialog().clickAgreement();
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getFirstName(), MAX_CHARS_NAME,
                "Possible input max+1 chars into first name field.");
    }

    /**
     * open register dialog;
     * input one char into first name field
     * check error message that impossible input less than 2 chars as first name
     */
    @Test(description = "CTW-18419:First Name field validations: No option to fill less than 2 characters")
    @TestLinkProperties(displayId = "CTW-18419")
    public void verifyImpossibleInputLessThanTwoCharsIntoFirstName() {
        CustomerRegistrationInfo customerRegistrationInfo =
                CustomerRegistrationInfo.builder(WebSiteNamingStrategy.get())
                .withFirstName(ONE_SYMBOL_NAME)
                .build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        pages().registrationDialog().clickAgreement();
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(), FAIL_NAME_NOTIFICATION,
                "Possible input one chars into first name field.");
    }

    /**
     * open register dialog;
     * input one char into last name field
     * check error message that impossible input none alphabetical symbols into last name
     */
    @Test(description = "CTW-5420:Last Name field validations: No option to insert anything but letters")
    @TestLinkProperties(displayId = "CTW-5420")
    public void validateImpossibleInputSpecialCharsIntoLastNameField() {
        CustomerRegistrationInfo customerRegistrationInfo =
                CustomerRegistrationInfo.builder(WebSiteNamingStrategy.get())
                .withLastName(INCORRECT_CHARS_NAME)
                .build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        pages().registrationDialog().clickAgreement();
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertTrue(pages().registrationDialog().getLastName().isEmpty(),
                "Special symbols were inputted into last name field.");
    }

    /**
     *  open register dialog;
     *  input max+1 chars into last name field
     *  check that impossible input more than max chars into last name field
     */
    @Test(description = "CTW-18422:Last Name field validations: no option to fill more than 20 characters")
    @TestLinkProperties(displayId = "CTW-18422")
    public void verifyImpossibleInputMoreThanTwentyCharsIntoLastName() {
        CustomerRegistrationInfo customerRegistrationInfo =
                CustomerRegistrationInfo.builder(WebSiteNamingStrategy.get())
                        .withLastName(MAX_PLUS_ONE_CHARS_NAME)
                        .build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        pages().registrationDialog().clickAgreement();
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getLastName(), MAX_CHARS_NAME,
                 "Possible input max+1 chars into last name field.");
    }

    /**
     * try register customer with last name one char
     */
    @Test(description = "CTW-18425:Last Name field validations: No option to fill less than 2 characters")
    @TestLinkProperties(displayId = "CTW-18425")
    public void verifyImpossibleInputLessThanTwoCharsIntoLastName() {
        CustomerRegistrationInfo customerRegistrationInfo =
                CustomerRegistrationInfo.builder(WebSiteNamingStrategy.get())
                .withLastName(ONE_SYMBOL_NAME)
                .build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customerRegistrationInfo);
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(), FAIL_NAME_NOTIFICATION,
                "Possible input one chars into last name field.");
    }

    /**
     * - generate user account using builder;
     * - validate email name field;
     */
    @Test(description = "CTW-5421:Email field validations")
    @TestLinkProperties(displayId = "CTW-5421")
    public void emailFieldValidations() {
        CustomerRegistrationInfo customer =
                CustomerRegistrationInfo.builder(WebSiteNamingStrategy.get())
                .withEmail(INCORRECT_EMAIL).build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Enter a valid email address.");

        customer.setEmail(INCORRECT_EMAIL_TWO_AT);
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Enter a valid email address.");

        customer.setEmail(INCORRECT_EMAIL_NO_AT);
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Enter a valid email address.");

        customer.setEmail(INCORRECT_EMAIL_DIGIT_DOMAIN);
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Enter a valid email address.");

        customer.setEmail(INCORRECT_CHARS_IN_EMAIL);
        pages().registrationDialog().fillRegisterForm(customer);
        softAssert().assertEquals(pages().registrationDialog().getEmail(), EMPTY_STRING);
        customer.setEmail(EMPTY_STRING);
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getBorderColorForEmail(), RED_RGB_STYLE);
        customer.setEmail(CORRECT_EMAIL);
        pages().registrationDialog().fillRegisterForm(customer);
        softAssert().assertEquals(pages().registrationDialog().getBorderColorForEmail(), GREEN_RGB_STYLE);
    }

    /**
     * - generate user account using builder;
     * - validate selected prefix saved in the form;
     * - validate registration is not available for Israel country;
     */
    @Test(description = "CTW-5422:Phone Prefix field validation")
    @TestLinkProperties(displayId = "CTW-5422")
    public void verifyPhonePrefixFunctionality() {
        CustomerRegistrationInfo customer =
                CustomerRegistrationInfo.builder(WebSiteNamingStrategy.get())
                .withPhoneCountryPrefix(Country.ISRAEL.getPhonePrefix())
                .build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().setCountryPrefix(Country.JORDAN.getName());
        pages().redirectDialog().startTrade();
        pages().registrationDialog().exists();
        softAssert().assertEquals(pages().registrationDialog().getCountryPrefix(), Country.JORDAN.getPhonePrefix());
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Registration Is Not Available In Your Country");
        softAssert().assertEquals(pages().registrationDialog().getBorderForCountryField(), RED_RGB_STYLE);
    }

    /**
     * - generate user account using builder;
     * - validate phone field;
     */
    @Test(description = "CTW-5423:Phone Number field validation")
    @TestLinkProperties(displayId = "CTW-5423")
    public void validateInputsIntoPhoneField() {
        CustomerRegistrationInfo customer =
                CustomerRegistrationInfo.builder(WebSiteNamingStrategy.get())
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
    @TestLinkProperties(displayId = "CTW-5424")
    public void validateSearchFunctionalityInCountry() {
        pages().topNavigationPage().signUp();
        pages().registrationDialog().setCountryPrefix(Country.JORDAN.getName());
        pages().redirectDialog().startTrade();
        pages().registrationDialog().exists();
        softAssert().assertEquals(pages().registrationDialog().countrySearch(SEARCH_BY_SYMBOL, Country.IRELAND.getName()),
                Country.IRELAND.getName(),"Country was not available in the search result.");
    }

    /**
     * - make sure impossible register customer for avoid countries;
     */
    @Test(description = "CTW-5425:Country dropdown validations")
    @TestLinkProperties(displayId = "CTW-5425")
    public void validateCountryDropDownField() {
        CustomerRegistrationInfo customer =
                CustomerRegistrationInfo.builder(WebSiteNamingStrategy.get())
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
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Registration Is Not Available In Your Country", NO_ERROR_MESSAGE);
        pages().registrationDialog().setCountryPrefix(Country.ISRAEL.getName());
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Registration Is Not Available In Your Country", NO_ERROR_MESSAGE);
    }

    /**
     * check currency selection is saved
     * check USD is not available as currency
     *
     */
    @Test(description = "CTW-5426:Currency dropdown field validation")
    @TestLinkProperties(displayId = "CTW-5426")
    public void validateCurrencyDropDownField() {
        CustomerRegistrationInfo customer =
                CustomerRegistrationInfo.builder(WebSiteNamingStrategy.get())
                .withCountry(Country.UNITED_KINGDOM.getName())
                .withPhoneCountryPrefix(Country.UNITED_KINGDOM.getPhonePrefix())
                .withCurrency(Currency.EUR.getFullName())
                .build();
        pages().topNavigationPage().signUp();
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
    @Test(description = "CTW-5427:Password fields validations")
    @TestLinkProperties(displayId = "CTW-5427")
    public void validatePasswordsField() {
        CustomerRegistrationInfo customer=CustomerRegistrationInfo.builder(WebSiteNamingStrategy.get()).build();
        customer.setPassword(EMPTY_STRING);
        fillRegisterCustomerDialog(customer);
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Incorrect password.",
                NO_ERROR_MESSAGE);
        customer.setPassword(INCORRECT_PASSWORD);
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Enter digits and letters only (lower and upper case)",
                 NO_ERROR_MESSAGE);
        softAssert().assertEquals(pages().registrationDialog().getPasswordSize(), "15");
        pages().browser().refreshPage();
        pages().topNavigationPage().signUp();
        customer.setPassword(FOUR_CHARS);
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Enter between 5 to 15 characters", NO_ERROR_MESSAGE);
        pages().browser().refreshPage();
        pages().topNavigationPage().signUp();
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
    @Test(description = "CTW-5428:''I'm over 18'' checkbox validation")
    @TestLinkProperties(displayId = "CTW-5428")
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
    @TestLinkProperties(displayId = "CTW-5429")
    public void checkSubmitButtonForRegisterNewCustomer() {
        fillRegisterCustomerDialog();
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertTrue(pages().loadingDialog().isDisplayed(),
                "Loading popup appeared.");
        softAssert().assertFalse(pages().registrationDialog().submitIsEnabled(),
                "Submit button is enabled during customer registration.");
        softAssert().assertTrue(pages().welcomeDialog().isStartBtnDisplayed(),
                "Start Trade dialog did not appear after registration.");
    }

    /**
     *  register customer
     *  verify customer for existence in the CRM Database
     */
    @Test(description = "CTW-5430:Full registration (including CRM+ email validations)")
    @TestLinkProperties(displayId = "CTW-5430")
    public void checkValidateEmailForRegisteredNewCustomer() {
        CustomerRegistrationInfo customer = fillRegisterCustomerDialog();
        pages().registrationDialog().submitRegisterForm();
        pages().welcomeDialog().isStartBtnDisplayed();
        operations().onBoardingOperations().assertUserCreatedInDatabase(customer.getEmail());
    }

    /**
     *  register customer
     *  verify mandatory fields
     *  verify customer for existence in the CRM Database
     */
    @Test(description = "CTW-5626:SEU: Registration form - Mandatory fields E2E")
    @TestLinkProperties(displayId = "CTW-5626")
    public void checkCheckMandatoryFieldsInTheCustomerRegistrationForm() {
        CustomerRegistrationInfo customer = getCustomer();
        customer.setFirstName(EMPTY_STRING);
        pages().topNavigationPage().signUp();
        registerNewCustomer(customer);
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(), "Enter your first name");
        softAssert().assertEquals(pages().registrationDialog().getBorderColorFirstName(), RED_RGB_STYLE);

        customer = getCustomer();
        customer.setLastName(EMPTY_STRING);
        registerNewCustomer(customer);
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(), "Enter your last name");
        softAssert().assertEquals(pages().registrationDialog().getBorderColorLastName(), RED_RGB_STYLE);

        customer = getCustomer();
        customer.setEmail(EMPTY_STRING);
        registerNewCustomer(customer);
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(), "Enter a valid email address.");
        softAssert().assertEquals(pages().registrationDialog().getBorderColorForEmail(), RED_RGB_STYLE);

        customer = getCustomer();
        customer.setPhoneCountryPrefix(EMPTY_PHONE_PREFIX);
        registerNewCustomer(customer);
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(), "Country is a mandatory field.");
        softAssert().assertEquals(pages().registrationDialog().getBorderForPrefixField(), RED_RGB_STYLE);

        customer = getCustomer();
        customer.setPhoneNumber(EMPTY_STRING);
        registerNewCustomer(customer);
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(), "Enter at least 6 characters");
        softAssert().assertEquals(pages().registrationDialog().getBorderColorPhone(), RED_RGB_STYLE);

        customer = getCustomer();
        customer.setPassword(EMPTY_STRING);
        pages().browser().deleteAllCookies();
        pages().browser().refreshPage();
        pages().topNavigationPage().signUp();
        registerNewCustomer(customer);
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(), "Incorrect password.");
        softAssert().assertEquals(pages().registrationDialog().getBorderColorForPassword(), RED_RGB_STYLE);

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
        pages().welcomeDialog().isStartBtnDisplayed();
        operations().onBoardingOperations().assertUserCreatedInDatabase(customer.getEmail());
    }

    /**
     * verify directions in the registration page for mandatory fields if RTL language was selected
     */
    @Test(description = "CTW-5874:AR - Verify when choosing AR language the order of the Registration slide is changing RTL")
    @TestLinkProperties(displayId = "CTW-5874")
    public void checkRegistrationDialogFieldsDirectionForRightToLeftLanguages() {
        pages().topNavigationPage().selectLanguage(Language.ARABIC.code);
        pages().loadingDialog().isDisplayed();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().exists();
        pages().registrationDialog().verifyContentAlignment(RTL);
    }

    /**
     * build new customer generated randomly
     * @return - generated new customer using builder
     */
    private CustomerRegistrationInfo getCustomer() {
        return CustomerRegistrationInfo.builder(WebSiteNamingStrategy.get())
                .build();
    }

}
