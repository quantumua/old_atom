package com.betamedia.atom.testslibrary.option24.web.createnewcustomers;

import com.betamedia.atom.core.api.crm.form.entities.*;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.CRMMobileAPINamingStrategy;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.web.form.Country;
import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import com.betamedia.atom.core.api.web.form.Localization;
import com.betamedia.atom.core.fwdataaccess.annotations.ClasspathLocation;
import com.betamedia.atom.core.fwdataaccess.repository.CsvResourceRepository;
import com.betamedia.atom.core.persistence.repositories.AbstractContactExtensionRepository;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import com.betamedia.atom.testslibrary.option24.end2end.bmw.AbstractOnboardingConditionsTest;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import static com.betamedia.atom.core.api.crm.form.entities.QuestionnaireAnswers.*;

/**
 * Created by vsnigur on 7/3/17.
 */
public class CreateNewCustomers extends AbstractOnboardingConditionsTest {

    /**
     * Strings for tests
     */
    protected static final String INCORRECT_CHARS_IN_EMAIL = "`~!#$%^&*()+=<>?,/[]{}";
    protected static final String INCORRECT_CHARS_NAME = "`~!#$%^&*()+=<>?,/[]{}";
    protected static final String INCORRECT_EMAIL_TWO_AT = "qatest11@@test.com";
    protected static final String INCORRECT_EMAIL_NO_AT = "kjfhskj.gmail.com";
    protected static final String INCORRECT_EMAIL_DIGIT_DOMAIN = "dkjfh@gmail.123";
    protected static final String INCORRECT_EMAIL = "djfhdjk12312";
    protected static final String CORRECT_EMAIL = "qatest11@test.com";
    protected static final String ONE_SYMBOL_NAME = "a";
    protected static final String MAX_PLUS_ONE_CHARS_NAME = "abcdefghijklmnopqrstu";
    protected static final String MAX_CHARS_NAME = "abcdefghijklmnopqrst";
    protected static final String FOUR_CHARS = "abcd";
    protected static final String SYMBOLS_AND_DIGITS = "a1b2c3d4e5";
    protected static final String SYMBOLS_AND_NO_DIGITS = "abcde";
    protected static final String PHONE_FIVE_DIGITS = "12345";
    protected static final String PHONE_NO_DIGITS = "phone~!@#$";
    protected static final String INCORRECT_PASSWORD = "!@#$%";
    protected static final int ZERO_VALUE = 0;
    protected static final int WEB_SOURCE_ID = 206440004;
    protected static final String SEARCH_BY_SYMBOL = "I";
    protected static final String EMPTY_STRING = "";
    protected static final String EMPTY_PHONE_PREFIX = "+";
    protected static final String EXPECTED_LEGALL_TERMS_AND_CONDITIONS_LINK = "/terms-and-conditions/";
    protected static final String EXPECTED_COOKIE_POLICY_LINK = "/terms-and-conditions/privacy-policy/";
    protected static final String EXPECTED_BONUS_TERMS_CONDITIONS_LINK = "/terms-and-conditions/bonus/";
    protected static final String QA_PREFIX_IN_DOMAIN = "qawww";
    protected static final String PRODUCTION_PREFIX = "www";
    protected static final int FIRST_TAB = 0;
    protected static final int SECOND_TAB = 1;
    protected static final String SERVER_DOCUMENT = "https://www.rodelerltd.com/24option";
    protected static final String DEFAULT_URL = "https://qawww.24option.com/eu/trade/";
    protected final String LICENSE_FOLDER = "/terms-and-conditions/";
    protected final String BONUS_FOLDER = "/bonus/";
    protected final String PRIVACY_FOLDER = "/privacy-policy/";

    /**
     * RGB color constants
     */
    protected static final String RED_RGB_STYLE = "rgb(221, 69, 44)";
    protected static final String GREEN_RGB_STYLE = "rgb(101, 204, 10)";
    /**
     * Message notifications for tests
     */
    protected final String FAIL_NAME_NOTIFICATION = "Enter at least 2 characters";
    protected final String NO_ERROR_MESSAGE = "Error message did not appear.";

    /**
     * - verify that sign up button opens registration dialog
     * - verify registration dialog caption
     */

    private AbstractContactExtensionRepository contactExtensionRepository;

    @BeforeClass
    public void before(){
        contactExtensionRepository = operations().crmDbOperations().contactExtensionRepository();
    }

    @Test(description = "CTW-5079:verify sign up button gives you the open account pop up")
    @TestLinkProperties(displayId = "CTW-5079")
    public void verifySignUpButtonRedirectToOnboardingOpenAccount() {
        pages().topNavigationPage().signUp();
        softAssert().assertTrue(pages().registrationDialog().exists(),
                "Registration dialog does not appear.");
        softAssert().assertEquals(pages().registrationDialog().dialogCaption(),
                "Apply for an account with 24option");
    }

    /**
     * Verify registration page elements
     * - logo;
     * - login button;
     * - risk message;
     * - live chat control;
     */
    @Test(description = "CTW-5089:main page elements verification")
    @TestLinkProperties(displayId = "CTW-5089")
    public void applyForAnAccountElementsVerification() {
        pages().topNavigationPage().signUp();
        softAssert().assertTrue( pages().registrationDialog().logoExists(), "Logo does not appear.");
        softAssert().assertTrue( pages().topNavigationPage().languageExists(), "Registration dialog does not appear.");
        softAssert().assertTrue( pages().registrationDialog().loginButtonExists(), "Login button does not appear.");
        softAssert().assertTrue( pages().registrationDialog().riskMessageExists(), "Risk notification does not appear.");
        //assertTrue("Live chat does not appear.", pages().registrationDialog().liveChatExists());
        softAssert().assertTrue( pages().registrationDialog().chatLinkDisplayed(), "Live chat link does not appear.");
    }

    /**
     * - generate user account using builder;
     * - validate first name field;
     */
    @Test(description = "CTW-5375:first name field validations")
    @TestLinkProperties(displayId = "CTW-5375")
    public void applyForAnAccountFirstNameFieldValidations() {
        CustomerRO customerRO = CustomerRO.builder(
                CRMMobileAPINamingStrategy.get()).setFirstName(MAX_PLUS_ONE_CHARS_NAME).build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customerRO);
        softAssert().assertEquals( pages().registrationDialog().getFirstName(), MAX_CHARS_NAME);
        customerRO.setFirstName(SYMBOLS_AND_DIGITS);
        pages().registrationDialog().fillRegisterForm(customerRO);
        softAssert().assertEquals( pages().registrationDialog().getFirstName(), SYMBOLS_AND_NO_DIGITS);
        customerRO.setFirstName(ONE_SYMBOL_NAME);
        pages().registrationDialog().register(customerRO);
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(), FAIL_NAME_NOTIFICATION);
    }

    /**
     * - generate user account using builder;
     * - validate last name field;
     */
    @Test(description = "CTW-5208:last name field validations")
    @TestLinkProperties(displayId = "CTW-5208")
    public void applyForAnAccountLastNameFieldValidations() {
        CustomerRO customerRO = CustomerRO.builder(CRMMobileAPINamingStrategy.get())
                .setLastName(MAX_PLUS_ONE_CHARS_NAME).build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customerRO);
        softAssert().assertEquals( pages().registrationDialog().getLastName(), MAX_CHARS_NAME);
        customerRO.setLastName(SYMBOLS_AND_DIGITS);
        pages().registrationDialog().fillRegisterForm(customerRO);
        softAssert().assertEquals( pages().registrationDialog().getLastName(), SYMBOLS_AND_NO_DIGITS);
        customerRO.setLastName(ONE_SYMBOL_NAME);
        pages().registrationDialog().register(customerRO);
        softAssert().assertEquals( pages().registrationDialog().getErrorMessageNotification(), FAIL_NAME_NOTIFICATION);
    }

    /**
     * - generate user account using builder;
     * - validate email name field;
     */
    @Test(description = "CTW-5209:email field validations")
    @TestLinkProperties(displayId = "CTW-5209")
    public void emailFieldValidations() {
        CustomerRO customerRO = CustomerRO.builder(CRMMobileAPINamingStrategy.get())
                .setPassword(EMPTY_STRING).setEmail(INCORRECT_EMAIL).build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(customerRO);
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Enter a valid email address.");
        customerRO.setEmail(INCORRECT_CHARS_IN_EMAIL);
        pages().registrationDialog().fillRegisterForm(customerRO);
        softAssert().assertEquals(pages().registrationDialog().getEmail(), EMPTY_STRING);
        customerRO.setEmail(EMPTY_STRING);
        pages().registrationDialog().register(customerRO);
        softAssert().assertEquals( pages().registrationDialog().getBorderColorForEmail(), RED_RGB_STYLE);
        customerRO.setEmail(CORRECT_EMAIL);
        pages().registrationDialog().register(customerRO);
        softAssert().assertEquals( pages().registrationDialog().getBorderColorForEmail(), GREEN_RGB_STYLE);
    }

    /**
     * - generate user account using builder;
     * - validate prefix field field;
     */
    @Test(description = "CTW-5210:prefix field validations")
    @TestLinkProperties(displayId = "CTW-5210")
    public void prefixFieldValidations() {
        CustomerRO customerRO = CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().setCountryPrefix(Country.JORDAN.getName());
        pages().redirectDialog().startTrade();
        pages().registrationDialog().exists();
        softAssert().assertEquals( pages().registrationDialog().getCountryPrefix(), Country.JORDAN.getPhonePrefix());
        pages().registrationDialog().setCountryPrefix(Country.ISRAEL.getName());
        pages().registrationDialog().register(customerRO);
        softAssert().assertEquals( pages().registrationDialog().getErrorMessageNotification(),
                "Registration Is Not Available In Your Country");
        softAssert().assertEquals( pages().registrationDialog().getBorderForCountryField(),
                RED_RGB_STYLE);
    }

    /**
     * - generate user account using builder;
     * - validate phone field;
     */
    @Test(description = "CTW-5211:phone number field validations")
    @TestLinkProperties(displayId = "CTW-5211")
    public void phoneFieldValidations() {
        CustomerRO customerRO = CustomerRO.builder(CRMMobileAPINamingStrategy.get())
                .setPhone(PHONE_NO_DIGITS).build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(customerRO);
        softAssert().assertEquals(pages().registrationDialog().getPhoneNumber(),
                EMPTY_STRING);
        customerRO.setPhone(PHONE_FIVE_DIGITS);
        pages().registrationDialog().register(customerRO);
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Enter between 6 to 15 numbers");
    }

    /**
     * - generate user account using builder;
     * - validate country search functionality for field;
     */
    @Test(description = "CTW-5260:country dropdown field search engine")
    @TestLinkProperties(displayId = "CTW-5260")
    public void countryDropdownFieldSearchEngine() {
        pages().topNavigationPage().signUp();
        pages().registrationDialog().setCountryPrefix(Country.JORDAN.getName());
        pages().redirectDialog().startTrade();
        pages().registrationDialog().exists();
        softAssert().assertEquals(pages().registrationDialog().countrySearch(SEARCH_BY_SYMBOL, Country.IRELAND.getName()),
                Country.IRELAND.getName(),"Country was not available in the search result.");
    }

    /**
     * - generate user account using builder;
     * - validate country field field;
     */
    @Test(description = "CTW-5261:country dropdown validations")
    @TestLinkProperties(displayId = "CTW-5261")
    public void countryDropdownValidations() {
        pages().topNavigationPage().signUp();
        pages().registrationDialog().countrySearch(EMPTY_STRING, Country.ISRAEL.getName());
        pages().redirectDialog().startTrade();
        pages().registrationDialog().fillRegisterForm(
                CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build());
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Registration Is Not Available In Your Country", NO_ERROR_MESSAGE);
    }

    /**
     * - generate user account using builder;
     * - validate currency field;
     */
    @Test(description = "CTW-5262:currency dropdown field validation")
    @TestLinkProperties(displayId = "CTW-5262")
    public void currencyDropdownFieldValidation() {
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(
                CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build());
        softAssert().assertFalse( pages().registrationDialog().availableCurrencies().isEmpty(),
                "Currencies are not available");
    }

    /**
     * - generate user account using builder;
     * - validate password field;
     */
    @Test(description = "CTW-5264:password fields validations")
    @TestLinkProperties(displayId = "CTW-5264")
    public void passwordFieldsValidation() {
        CustomerRO customerRO = CustomerRO.builder(CRMMobileAPINamingStrategy.get())
                .setPassword("").build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(customerRO);
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Incorrect password.", NO_ERROR_MESSAGE);
        customerRO.setPassword(INCORRECT_PASSWORD);
        pages().registrationDialog().register(customerRO);
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Enter digits and letters only (lower and upper case)", NO_ERROR_MESSAGE);
        softAssert().assertEquals(pages().registrationDialog().getPasswordSize(), "15");
        pages().browser().refreshPage();
        pages().topNavigationPage().signUp();
        customerRO.setPassword(FOUR_CHARS);
        pages().registrationDialog().register(customerRO);
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Enter between 5 to 15 characters", NO_ERROR_MESSAGE);
        pages().browser().refreshPage();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build());
        pages().registrationDialog().clickAgreement();
        pages().registrationDialog().submitRegisterForm();
        pages().registrationDialog().agreementStatus();
        softAssert().assertEquals( pages().registrationDialog().getBorderColorForPassword(),
                GREEN_RGB_STYLE);
        pages().registrationDialog().clickAgreement();
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertTrue( pages().startTradeDialog().exists(),
                "Additional details window did not appear.");
    }

    /**
     * - generate user account using builder;
     * - validate agreement checkbox for message notification;
     */
    @Test(description = "CTW-5265:checkbox validation")
    @TestLinkProperties(displayId = "CTW-5265")
    public void agreementCheckboxValidation() {
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(
                CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build());
        pages().registrationDialog().clickAgreement();
        pages().registrationDialog().submitRegisterForm();
        softAssert().assertEquals( pages().registrationDialog().agreementStatus(),
                "You must read and agree to the above terms!");
    }

    /**
     * - generate user account using builder;
     * - validate submit button functionality;
     */
    @Test(description = "CTW-5359:submit button")
    @TestLinkProperties(displayId = "CTW-5359")
    public void submitButtonValidation() {
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(
                CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build());
        softAssert().assertFalse(pages().registrationDialog().submitIsEnabled());
        softAssert().assertTrue(pages().startTradeDialog().exists());
    }

    /**
     * - generate 2 user account using mobile API registration;
     * - add same credit cards for the users using UI;
     * - verify users connection in the DB;
     */
    @Test(description = "CTW-17742:Same Credit Card")
    @TestLinkProperties(displayId = "CTW-17742")
    public void validateConnectionInDBForDifferentCustomersButSameCreditCard() {
        CustomerRO customerROFirst = registerCustomer();
        pages().browser().deleteAllCookies();
        pages().browser().refreshPage();
        CustomerRO customerROSecond = registerCustomer();
        operations().onBoardingOperations().assertUsersHaveConnection(
                customerROFirst.getEmail(),
                customerROSecond.getEmail(),
                "Same Credit Card");
    }

    /**
     * - generate 2 user account using builder;
     * - create 2 users using UI with the same phone;
     * - verify users connection in the DB;
     */
    @Test(description = "CTW-17744:Same Phone number1")
    @TestLinkProperties(displayId = "CTW-17744")
    public void validateConnectionInDBForDifferentCustomersButSamePhone() {
        CustomerRO customerROFirst = CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build();
        CustomerRO customerROSecond = CustomerRO.builder(CRMMobileAPINamingStrategy.get())
                .setPhone(customerROFirst.getPhone()).build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(customerROFirst);
        pages().welcomeDialog().isStartBtnDisplayed();
        pages().browser().deleteAllCookies();
        pages().browser().refreshPage();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(customerROSecond);
        pages().welcomeDialog().isStartBtnDisplayed();
        operations().onBoardingOperations().assertUsersHaveConnection(
                customerROFirst.getEmail(),
                customerROSecond.getEmail(),
                "Same Phone");
    }

    /**
     * - generate 2 user using mobile API;
     * - create 2 users with the same first and last names;
     * - verify users are not connected in the DB;
     */
    @Test(description = "CTW-17745:Same First and last name")
    @TestLinkProperties(displayId = "CTW-17745")
    public void validateNoConnectionsInDBForDifferentCustomersWithSameFirstAndLastNames() {
        CustomerRO customerROFirst = CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build();
        CustomerRO customerROSecond = CustomerRO.builder(CRMMobileAPINamingStrategy.get())
                .setFirstName(customerROFirst.getFirstName())
                .setLastName(customerROFirst.getLastName())
                .build();
        createCustomer(customerROFirst);
        pages().browser().deleteAllCookies();
        pages().browser().refreshPage();
        createCustomer(customerROSecond);
        operations().onBoardingOperations().assertUsersHaveNotConnection(
                customerROFirst.getEmail(),
                customerROSecond.getEmail());
    }

    /**
     * - generate 2 user using builder;
     * - try create 2 users with the same email;
     * - verify that impossible to create 2 users with the same email;
     */
    @Test(description = "CTW-17743:Same Email")
    @TestLinkProperties(displayId = "CTW-17743")
    public void validateNoConnectionsInDBForDifferentCustomersWithSameEmail() {
        CustomerRO customerROFirst = CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build();
        CustomerRO customerROSecond = CustomerRO.builder(CRMMobileAPINamingStrategy.get())
                .setEmail(customerROFirst.getEmail())
                .build();
        createCustomer(customerROFirst);
        pages().browser().deleteAllCookies();
        pages().browser().refreshPage();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(customerROSecond);
        softAssert().assertEquals(pages().registrationDialog().getErrorMessageNotification(),
                "Customer with the same user name is already registered", NO_ERROR_MESSAGE);
    }

    /**
     * - create 2 user using UI from the same workstation;
     * - verify users are not connected in the DB;
     */
    @Test(description = "CTW-17746:Same Machine ID -negative")
    @TestLinkProperties(displayId = "CTW-17746")
    public void validateNoConnectionsInDBForDifferentCustomersWithSameMachineID() {
        CustomerRO customerROFirst = CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build();
        CustomerRO customerROSecond = CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build();
        createCustomer(customerROFirst);
        pages().browser().deleteAllCookies();
        pages().browser().refreshPage();
        createCustomer(customerROSecond);
        operations().onBoardingOperations().assertUsersHaveNotConnection(
                customerROFirst.getEmail(),
                customerROSecond.getEmail());
    }

    /**
     * - create user using UI;
     * - verify bulkEmail field value for user, it is not 0;
     */
    @Test(description = "CTW-7884:Bulk emails indicator Allow=No by default")
    @TestLinkProperties(displayId = "CTW-7884")
    public void validateBulkEmailHasNoneZeroForNewCreatedCustomer() {
        CustomerRO customerRO = CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build();
        createCustomer(customerRO);
        assertBulkEmailHasNotValue(customerRO.getEmail(), ZERO_VALUE);
    }

    private void assertBulkEmailHasNotValue(String userLoginName, int notExpectedValue) {
        softAssert().assertTrue(contactExtensionRepository.findByUsername(userLoginName)
                .getAcceptbulkemail() != notExpectedValue);
    }

    /**
     * - create user using UI;
     * - verify phone calls field value for user, it is not 0;
     */
    @Test(description = "CTW-9009:Phone calls indicator = Allow by default")
    @TestLinkProperties(displayId = "CTW-9009")
    public void validatePhoneCallsForNewCreatedCustomer() {
        CustomerRO customerRO = CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build();
        createCustomer(customerRO);
        assertDoNotPhoneHasNotValue(customerRO.getEmail(), String.valueOf(ZERO_VALUE));
    }

    private void assertDoNotPhoneHasNotValue(String userLoginName, String notExpectedDoNotPhoneValue) {
        softAssert().assertFalse(operations().crmDbOperations().contactBaseRepository().findByEmailAddress1(userLoginName)
                .getDoNotPhone().equalsIgnoreCase(notExpectedDoNotPhoneValue));
    }

    /**
     * - create user using IU;
     * - verify customer creation source;
     */
    @Test(description = "CTW-2544: Web page")
    @TestLinkProperties(displayId = "CTW-2544")
    public void validateCustomerCreationSourceId() {
        CustomerRO customerRO = CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build();
        createCustomer(customerRO);
        operations().onBoardingOperations().assertTrafficSource(customerRO.getEmail(), WEB_SOURCE_ID);
    }

    /**
     * create user using customerRO build object
     * @param customerRO - customerRO build object;
     */
    private void createCustomer(CustomerRO customerRO) {
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(customerRO);
        pages().welcomeDialog().isStartBtnDisplayed();
    }

    /**
     * pass questionnaire for newly created user;
     */
    private void passQuestionnaire() {
        pages().fnsPersonalInformation().submit(PersonalInformation.builder()
                .withEmploymentStatus(EmploymentStatus.SALARIED_EMPLOYEE)
                .withIndustry(Industry.ACCOUNTING)
                .withEmployerName("testEmployer")
                .withTaxResidenceCountry("AF")
                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.YES)
                .withTaxIdentificationNumber("1111111")
                .withUSReportabilityStatus(IsUSReportable.NO)
                .withEducationLevel(EducationLevel.POST_GRADUATE)
                .withEducationField(EducationField.ACCOUNTING)
                .withPoliticalExposureStatus(IsPoliticallyExposed.NO)
                .withSourceOfFunds(SourceOfFunds.EMPLOYMENT)
                .withAnnualIncome(AnnualIncome.INCOME_OVER_100K)
                .withNetWealth(NetWealth.NET_WEALTH_OVER_300K)
                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_OVER_50K)
                .withPurposeOfTrading(PurposeOfTrading.SPECULATIVE)
                .build()
        );
        pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
                .withSharesExperience(SharesExperience.NEVER)
                .withBinaryExperience(BinaryExperience.NEVER)
                .withForExExperience(ForExExperience.NEVER)
                .withFinancialWorkExperience(FinancialWorkExperience.NEITHER)
                .withCfdBinaryKnowledge(CfdBinaryKnowledge.NON_RISKY)
                .withMainFactorKnowledge(MainFactorKnowledge.ANNOUNCEMENT)
                .withHowToCloseKnowledge(HowToCloseKnowledge.LONDON_STOCK)
                .withCfdLeverageKnowledge(CfdLeverageKnowledge.PROVIDES)
                .withStopLossKnowledge(StopLossKnowledge.BUY)
                .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_10K)
                .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.WARNING_CALL)
                .withAutomaticStopKnowledge(AutomaticStopKnowledge.EARNINGS)
                .withLossOn1to50Knowledge(LossOn1to50Knowledge.A2_450)
                .withLossOn1to200Knowledge(LossOn1to200Knowledge.A2_1200)
                .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_60)
                .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_75)
                .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_35)
                .withInstrumentsTradedBefore(QuestionnaireAnswers.InstrumentsTradedBefore.NO_EXPERIENCE)
                .withFrequencyPastTransactions(QuestionnaireAnswers.FrequencyPastTransactions.OCCASIONALLY)
                .withVolumePastTransaction(QuestionnaireAnswers.VolumePastTransaction.LESS_THAN_10)
                .withCommonLevelPastTransaction(QuestionnaireAnswers.CommonLevelPastTransaction.LOWER_THAN_1_50)
                .build());
    }

    /**
     * register new customer using UI;
     * @return - customerRO object with fields for created user.
     */
    private CustomerRO registerCustomer() {
        CustomerRO customerRO = CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build();
        operations().customerOperations().updateCustomersOnboardingConditions(
                operations().customerOperations().register(customerRO),
                createConditionsToShowOnlyDepositPage());
        pages().topNavigationPage().logIn();
        pages().loginDialog().login(customerRO.getEmail(),customerRO.getPassword());
        pages().welcomeBackMessage().exists();
        pages().welcomeBackMessage().continueQuestionnaire();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        passQuestionnaire();
        pages().riskWarningPage().accept();
        pages().creditCardDeposit().submit(CreditCardDeposit.builder().build());
        try {
            pages().uploadDocumentDialog().exists();
        } catch (Exception e) {
            Reporter.log("Upload documents dialog did not appear");
        }
        return customerRO;
    }

    /**
     * create condition to show only deposit page for registerred customer
     * @return - condition for customer
     */
    private OnboardingWizardConditions createConditionsToShowOnlyDepositPage() {
        return new OnboardingWizardConditions(true, true, true, true,
                OnboardingWizardConditions.ExperienceLevel.HIGH_EXPERIENCE, false,
                OnboardingWizardConditions.AccountType.REAL,
                OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED,
                OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED, true,
                true, OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED);
    }

    /**
     * generate new customer and register it in the web register form
     * @return - generated customer
     */
    protected CustomerRegistrationInfo fillRegisterCustomerDialog() {
        CustomerRegistrationInfo customer =
                CustomerRegistrationInfo.builder(WebSiteNamingStrategy.get())
                .build();
        fillRegisterCustomerDialog(customer);
        return customer;
    }

    /**
     * fill register form in the web
     * @param customer - customer to register
     */
    protected void fillRegisterCustomerDialog(CustomerRegistrationInfo customer) {
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customer);
    }

    /**
     * register new customer in the register form and submit
     * @param customer - customer to register
     */
    protected void registerNewCustomer(CustomerRegistrationInfo customer) {
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().submitRegisterForm();
    }

    /**
     * validate for equals 2 objects and report log
     * @param expected - expected data
     * @param actual - actual data
     * @param errorMessage - message to report
     */
    public static void validateValue(Object expected, Object actual, String errorMessage) {
        Reporter.log(String.format("Check expected: '%s' and actual: '%s' <br/>",
                expected, actual));
        softAssert().assertEquals(actual, expected, errorMessage);
    }


    /**
     * get localization collection
     * @return - collection of the localizations
     */
    protected List<Localization> getLocalization() {
        return CsvResourceRepository.getData(Localization.class, Localization.class.getAnnotation(ClasspathLocation.class).value());
    }

    /**
     * get default url for the configured web site
     * @return - url with domain only
     */
    protected String getCurrentUrl() {
        URL url = null;
        try {
            url = new URL(DEFAULT_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url.getProtocol() + "://" + url.getAuthority().replace(QA_PREFIX_IN_DOMAIN,PRODUCTION_PREFIX);
    }

    /**
     * construct legal terms and conditions link
     * @return egal terms and conditions link
     */
    protected String getLegalTermsAndConditionsExpectedLink() {
        return getLegalTermsAndConditionsExpectedLink(EMPTY_STRING);
    }

    /**
     * construct legal terms and conditions link for required locale
     * @param locale locale to use
     * @return legal terms and conditions link
     */
    protected String getLegalTermsAndConditionsExpectedLink(String locale) {
        return getCurrentUrl()+locale+EXPECTED_LEGALL_TERMS_AND_CONDITIONS_LINK;
    }

    /**
     * construct bonus terms conditions expected link
     * @return bonus terms conditions expected link
     */
    protected String getBonusTermsConditionsExpectedLink() {
        return getBonusTermsConditionsExpectedLink(EMPTY_STRING);
    }

    /**
     * construct bonus terms conditions expected link for required locale
     * @param locale - locale to use
     * @return bonus terms conditions expected link
     */
    protected String getBonusTermsConditionsExpectedLink(String locale) {
        return getCurrentUrl()+locale+EXPECTED_BONUS_TERMS_CONDITIONS_LINK;
    }

    /**
     * construct expected cookie policy link
     * @return cookie policy expected link
     */
    protected String getCookiePolicyExpectedLink() {
        return getCurrentUrl()+EXPECTED_COOKIE_POLICY_LINK;
    }

    /**
     * construct expected cookie link
     * @param locale - locale for link
     * @return cookie policy expected link
     */
    protected String getCookiePolicyExpectedLink(String locale) {
        return getCurrentUrl()+locale+EXPECTED_COOKIE_POLICY_LINK;
    }
}
