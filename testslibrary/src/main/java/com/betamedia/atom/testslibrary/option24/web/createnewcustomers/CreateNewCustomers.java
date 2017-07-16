package com.betamedia.atom.testslibrary.option24.web.createnewcustomers;

import com.betamedia.atom.core.api.crm.form.entities.*;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.CRMMobileAPINamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.testslibrary.option24.end2end.bmw.AbstractOnboardingUserExperienceTest;
import com.betamedia.atom.testslibrary.option24.end2end.crm.newQuestionnaries.Questions;
import org.testng.Reporter;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by vsnigur on 7/3/17.
 */
public class CreateNewCustomers extends AbstractOnboardingUserExperienceTest {

    private static final String INCORRECT_CHARS_IN_EMAIL = "`~!#$%^&*()+=<>?,/[]{}";
    private static final String INCORRECT_EMAIL = "qatest11@@test.com";
    private static final String CORRECT_EMAIL = "qatest11@test.com";
    private static final String RED_RGB_STYLE = "rgb(221, 69, 44)";
    private static final String GREEN_RGB_STYLE = "rgb(101, 204, 10)";
    private static final String ONE_SYMBOL = "a";
    private static final String MAX_PLUS_ONE_CHARS = "abcdefghijklmnopqrstu";
    private static final String MAX_CHARS = "abcdefghijklmnopqrst";
    private static final String FOUR_CHARS = "abcd";
    private static final String SYMBOLS_AND_DIGITS = "a1b2c3d4e5";
    private static final String SYMBOLS_AND_NO_DIGITS = "abcde";
    private static final String FAIL_NAME_NOTIFICATION = "Enter at least 2 characters";
    private static final String JORDAN_COUNTRY = "Jordan";
    private static final String JORDAN_PHONE_PREFIX = "+962";
    private static final String ISRAEL_COUNTRY = "Israel";
    private static final String ISRAEL_PHONE_PREFIX = "+972";
    private static final String IRELAND_COUNTRY = "Ireland";
    private static final String PHONE_FIVE_DIGITS = "12345";
    private static final String PHONE_NO_DIGITS = "phone~!@#$";
    private static final String INCORRECT_PASSWORD = "!@#$%";
    private static final String NO_ERROR_MESSAGE = "Error message did not appear.";
    private static final int ZERO_VALUE = 0;
    private static final int WEB_SOURCE_ID = 206440004;

    /**
     * - verify that sign up button opens registration dialog
     * - verify registration dialog caption
     */
    @Test(description = "CTW-5079:verify sign up button gives you the open account pop up")
    public void verifySignUpButtonRedirectToOnboardingOpenAccount() {
        pages().topNavigationPage().signUp();
        assertTrue("Registration dialog does not appear.",pages().registrationDialog().exists());
        assertEquals("Apply for an account with 24option", pages().registrationDialog().dialogCaption());
    }

    /**
     * Verify registration page elements
     * - logo;
     * - login button;
     * - risk message;
     * - live chat control;
     */
    @Test(description = "CTW-5089:main page elements verification")
    public void applyForAnAccountElementsVerification() {
        pages().topNavigationPage().signUp();
        assertTrue("Logo does not appear.", pages().registrationDialog().logoExists());
        assertTrue("Registration dialog does not appear.",pages().topNavigationPage().languageExists());
        assertTrue("Login button does not appear.", pages().registrationDialog().loginButtonExists());
        assertTrue("Risk notification does not appear.", pages().registrationDialog().riskMessageExists());
        //assertTrue("Live chat does not appear.", pages().registrationDialog().liveChatExists());
        assertTrue("Live chat link does not appear.", pages().registrationDialog().chatLinkDisplayed());
    }

    /**
     * - generate user account using builder;
     * - validate first name field;
     */
    @Test(description = "CTW-5375:first name field validations")
    public void applyForAnAccountFirstNameFieldValidations() {
        CustomerRO customerRO = CustomerRO.builder(
                CRMMobileAPINamingStrategy.get()).setFirstName(MAX_PLUS_ONE_CHARS).build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customerRO);
        assertEquals(MAX_CHARS, pages().registrationDialog().getFirstName());
        customerRO.setFirstName(SYMBOLS_AND_DIGITS);
        pages().registrationDialog().fillRegisterForm(customerRO);
        assertEquals(SYMBOLS_AND_NO_DIGITS, pages().registrationDialog().getFirstName());
        customerRO.setFirstName(ONE_SYMBOL);
        pages().registrationDialog().register(customerRO);
        assertEquals(FAIL_NAME_NOTIFICATION, pages().registrationDialog().getErrorMessageNotification());
    }

    /**
     * - generate user account using builder;
     * - validate last name field;
     */
    @Test(description = "CTW-5208:last name field validations")
    public void applyForAnAccountLastNameFieldValidations() {
        CustomerRO customerRO = CustomerRO.builder(CRMMobileAPINamingStrategy.get())
                .setLastName(MAX_PLUS_ONE_CHARS).build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(customerRO);
        assertEquals(MAX_CHARS, pages().registrationDialog().getLastName());
        customerRO.setLastName(SYMBOLS_AND_DIGITS);
        pages().registrationDialog().fillRegisterForm(customerRO);
        assertEquals(SYMBOLS_AND_NO_DIGITS, pages().registrationDialog().getLastName());
        customerRO.setLastName(ONE_SYMBOL);
        pages().registrationDialog().register(customerRO);
        assertEquals(FAIL_NAME_NOTIFICATION, pages().registrationDialog().getErrorMessageNotification());
    }

    /**
     * - generate user account using builder;
     * - validate email name field;
     */
    @Test(description = "CTW-5209:email field validations")
    public void emailFieldValidations() {
        CustomerRO customerRO = CustomerRO.builder(CRMMobileAPINamingStrategy.get())
                .setPassword("").setEmail(INCORRECT_EMAIL).build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(customerRO);
        assertEquals("Enter a valid email address.",
                pages().registrationDialog().getErrorMessageNotification());
        customerRO.setEmail(INCORRECT_CHARS_IN_EMAIL);
        pages().registrationDialog().fillRegisterForm(customerRO);
        assertEquals("", pages().registrationDialog().getEmail());
        customerRO.setEmail("");
        pages().registrationDialog().register(customerRO);
        assertEquals(RED_RGB_STYLE, pages().registrationDialog().getBorderColorForEmail());
        customerRO.setEmail(CORRECT_EMAIL);
        pages().registrationDialog().register(customerRO);
        assertEquals(GREEN_RGB_STYLE, pages().registrationDialog().getBorderColorForEmail());
    }

    /**
     * - generate user account using builder;
     * - validate prefix field field;
     */
    @Test(description = "CTW-5210:prefix field validations")
    public void prefixFieldValidations() {
        CustomerRO customerRO = CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().setCountryPrefix(JORDAN_COUNTRY);
        pages().redirectDialog().startTrade();
        pages().registrationDialog().exists();
        assertEquals(JORDAN_PHONE_PREFIX, pages().registrationDialog().getCountryPrefix());
        pages().registrationDialog().setCountryPrefix(ISRAEL_COUNTRY);
        pages().registrationDialog().register(customerRO);
        assertEquals("Registration Is Not Available In Your Country",
                pages().registrationDialog().getErrorMessageNotification());
        assertEquals(RED_RGB_STYLE, pages().registrationDialog().getBorderForCountryField());
    }

    /**
     * - generate user account using builder;
     * - validate phone field;
     */
    @Test(description = "CTW-5211:phone number field validations")
    public void phoneFieldValidations() {
        CustomerRO customerRO = CustomerRO.builder(CRMMobileAPINamingStrategy.get())
                .setPhone(PHONE_NO_DIGITS).build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(customerRO);
        assertEquals("", pages().registrationDialog().getPhoneNumber());
        customerRO.setPhone(PHONE_FIVE_DIGITS);
        pages().registrationDialog().register(customerRO);
        assertEquals("Enter between 6 to 15 numbers", pages().registrationDialog().getErrorMessageNotification());
    }

    /**
     * - generate user account using builder;
     * - validate country search functionality for field;
     */
    @Test(description = "CTW-5260:country dropdown field search engine")
    public void countryDropdownFieldSearchEngine() {
        pages().topNavigationPage().signUp();
        pages().registrationDialog().setCountryPrefix(JORDAN_COUNTRY);
        pages().redirectDialog().startTrade();
        pages().registrationDialog().exists();
        assertEquals("Country was not available in the search result.",
                IRELAND_COUNTRY, pages().registrationDialog().countrySearch("I", IRELAND_COUNTRY));
    }

    /**
     * - generate user account using builder;
     * - validate country field field;
     */
    @Test(description = "CTW-5261:country dropdown validations")
    public void countryDropdownValidations() {
        pages().topNavigationPage().signUp();
        pages().registrationDialog().countrySearch("", ISRAEL_COUNTRY);
        pages().redirectDialog().startTrade();
        pages().registrationDialog().fillRegisterForm(
                CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build());
        pages().registrationDialog().submitRegisterForm();
        assertEquals(NO_ERROR_MESSAGE,
                "Registration Is Not Available In Your Country",
                pages().registrationDialog().getErrorMessageNotification());
    }

    /**
     * - generate user account using builder;
     * - validate currency field;
     */
    @Test(description = "CTW-5262:currency dropdown field validation")
    public void currencyDropdownFieldValidation() {
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(
                CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build());
        assertFalse("Currencies are not available",
                pages().registrationDialog().availableCurrencies().isEmpty());
    }

    /**
     * - generate user account using builder;
     * - validate password field;
     */
    @Test(description = "CTW-5264:password fields validations")
    public void passwordFieldsValidation() {
        CustomerRO customerRO = CustomerRO.builder(CRMMobileAPINamingStrategy.get())
                .setPassword("").build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(customerRO);
        assertEquals(NO_ERROR_MESSAGE,
                "Incorrect password.",
                pages().registrationDialog().getErrorMessageNotification());
        customerRO.setPassword(INCORRECT_PASSWORD);
        pages().registrationDialog().register(customerRO);
        assertEquals(NO_ERROR_MESSAGE,
                "Enter digits and letters only (lower and upper case)",
                pages().registrationDialog().getErrorMessageNotification());
        assertEquals("15",
                pages().registrationDialog().getPasswordSize());
        pages().browser().refreshPage();
        pages().topNavigationPage().signUp();
        customerRO.setPassword(FOUR_CHARS);
        pages().registrationDialog().register(customerRO);
        assertEquals(NO_ERROR_MESSAGE,
                "Enter between 5 to 15 characters",
                pages().registrationDialog().getErrorMessageNotification());
        pages().browser().refreshPage();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build());
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
     * - generate user account using builder;
     * - validate agreement checkbox for message notification;
     */
    @Test(description = "CTW-5265:checkbox validation")
    public void agreementCheckboxValidation() {
        pages().topNavigationPage().signUp();
        pages().registrationDialog().fillRegisterForm(
                CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build());
        pages().registrationDialog().clickAgreement();
        pages().registrationDialog().submitRegisterForm();
        assertEquals("You must read and agree to the above terms!",
                pages().registrationDialog().agreementStatus());
    }

    /**
     * - generate user account using builder;
     * - validate submit button functionality;
     */
    @Test(description = "CTW-5359:submit button")
    public void submitButtonValidation() {
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(
                CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build());
        assertFalse(pages().registrationDialog().submitIsEnabled());
        assertTrue(pages().startTradeDialog().exists());
    }

    /**
     * - generate 2 user account using mobile API registration;
     * - add same credit cards for the users using UI;
     * - verify users connection in the DB;
     */
    @Test(description = "CTW-17742:Same Credit Card")
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
    public void validateConnectionInDBForDifferentCustomersButSamePhone() {
        CustomerRO customerROFirst = CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build();
        CustomerRO customerROSecond = CustomerRO.builder(CRMMobileAPINamingStrategy.get())
                .setPhone(customerROFirst.getPhone()).build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(customerROFirst);
        pages().welcomePage().isStartBtnDisplayed();
        pages().browser().deleteAllCookies();
        pages().browser().refreshPage();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(customerROSecond);
        pages().welcomePage().isStartBtnDisplayed();
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
        assertEquals(NO_ERROR_MESSAGE,
                "Customer with the same user name is already registered",
                pages().registrationDialog().getErrorMessageNotification());
    }

    /**
     * - create 2 user using UI from the same workstation;
     * - verify users are not connected in the DB;
     */
    @Test(description = "CTW-17746:Same Machine ID -negative")
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
    public void validateBulkEmailHasNoneZeroForNewCreatedCustomer() {
        CustomerRO customerRO = CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build();
        createCustomer(customerRO);
        operations().onBoardingOperations().assertBulkEmailHasNotValue(customerRO.getEmail(), ZERO_VALUE);
    }

    /**
     * - create user using UI;
     * - verify phone calls field value for user, it is not 0;
     */
    @Test(description = "CTW-9009:Phone calls indicator = Allow by default")
    public void validatePhoneCallsForNewCreatedCustomer() {
        CustomerRO customerRO = CustomerRO.builder(CRMMobileAPINamingStrategy.get()).build();
        createCustomer(customerRO);
        operations().onBoardingOperations().assertDoNotPhoneHasNotValue(customerRO.getEmail(), String.valueOf(ZERO_VALUE));
    }

    /**
     * - create user using IU;
     * - verify customer creation source;
     */
    @Test(description = "CTW-2544: Web page")
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
        pages().welcomePage().isStartBtnDisplayed();
    }

    /**
     * pass questionnaire for newly created user;
     */
    private void passQuestionnaire() {
        pages().fnsPersonalInformation().submitOnWizard(PersonalInformation.builder()
                .withEmploymentStatus(Questions.EmploymentStatus.SALARIED_EMPLOYEE.get())
                .withIndustry(Questions.Industry.ACCOUNTING.get())
                .withEmployerName("testEmployer")
                .withTaxResidenceCountry("AF")
                .withTaxIdentificationNumberStatus(Questions.HasTaxIdentificationNumber.YES.get())
                .withTaxIdentificationNumber("1111111")
                .withUSReportabilityStatus(Questions.IsUSReportable.NO.get())
                .withEducationLevel(Questions.EducationLevel.POST_GRADUATE.get())
                .withEducationField(Questions.EducationField.ACCOUNTING.get())
                .withPoliticalExposureStatus(Questions.IsPoliticallyExposed.NO.get())
                .withSourceOfFunds(Questions.SourceOfFunds.EMPLOYMENT.get())
                .withAnnualIncome(Questions.AnnualIncome.INCOME_OVER_100K.get())
                .withNetWealth(Questions.NetWealth.NET_WEALTH_OVER_300K.get())
                .withExpectedDepositsPerYear(Questions.ExpectedDepositsPerYear.DEPOSITS_OVER_50K.get())
                .withPurposeOfTrading(Questions.PurposeOfTrading.SPECULATIVE.get())
                .build()
        );
        pages().fnsTradingExperience().submitOnWizard(TradingExperienceInfo.builder()
                .withSharesExperience(Questions.SharesExperience.NEVER.get())
                .withBinaryExperience(Questions.BinaryExperience.NEVER.get())
                .withForExExperience(Questions.ForExExperience.NEVER.get())
                .withFinancialWorkExperience(Questions.FinancialWorkExperience.NEITHER.get())
                .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.NON_RISKY.get())
                .withMainFactorKnowledge(Questions.MainFactorKnowledge.ANNOUNCEMENT.get())
                .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.LONDON_STOCK.get())
                .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.PROVIDES.get())
                .withStopLossKnowledge(Questions.StopLossKnowledge.BUY.get())
                .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_10K.get())
                .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.WARNING_CALL.get())
                .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EARNINGS.get())
                .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A2_450.get())
                .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A2_1200.get())
                .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_60.get())
                .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_75.get())
                .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_35.get())
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
        pages().loginPage().login(customerRO.getEmail(),customerRO.getPassword());
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

    private OnboardingWizardConditions createConditionsToShowOnlyDepositPage() {
        return new OnboardingWizardConditions(true, true, true, true,
                OnboardingWizardConditions.ExperienceLevel.HIGH_EXPERIENCE, false,
                OnboardingWizardConditions.AccountType.REAL,
                OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED,
                OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED, true,
                true, OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED);
    }
}
