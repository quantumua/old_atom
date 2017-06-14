package com.betamedia.atom.testslibrary.option24.web;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions.ExperienceLevel;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.CRMMobileAPINamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.testslibrary.option24.end2end.crm.AbstractOnboardingConditionsTest;
import com.betamedia.atom.testslibrary.option24.web.CustomerLeverageTest.ExperienceScore;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by vsnigur on 5/29/17.
 * Updated by Nir Shukrun on 6/6/17.
 * Updated by Nir Shukrun on 6/13/17 - new Signature Risk Warning for Spain.
 */
public class CustomerLeverageWithRiskSignatureTest extends AbstractOnboardingConditionsTest {

    private static final int FIRST_AVERAGE = 0;
    private static final int SECOND_AVERAGE = 1;
    private static final int THIRD_AVERAGE = 2;

    private static final String AVERAGE1TO50 = "1:50";
    private static final String AVERAGE1TO100 = "1:100";
    private static final String AVERAGE1TO200 = "1:200";

    private static final String SIGNATURE_RW_NO_EXPERIENCED = "This is a risky investment but you are an experienced person and you aware to the consequences";
    private static final String SIGNATURE_RW_EXPERIENCED = "This is a risky investment but you are an experienced person and you aware to the consequences";


    /**
     * Tests main actions
     * 1. Create user via mobile api
     * 2. Update user experience level and score in the DB
     * 3. Login as created user into web
     * 4. Verify reject message
     */
    @Test(description = "crm-9043")
    @Parameters({"countryCode"})
    public void checkNoCustomerLeverageForRejectedCustomer(String countryCode) {
        createUser(countryCode, ExperienceLevel.REJECTED, ExperienceScore.REJECTED);
        pages().rejectMessage().isDisplayed();
    }

    /**
     * Tests main actions
     * 1. Create user via mobile api
     * 2. Update user experience level and score in the DB
     * 3. Login as created user into web
     * 4. Verify user was logged out
     */
    @Test(description = "crm-9020")
    @Parameters({"countryCode"})
    public void checkIsNotAbleToLoginForRejectedCustomer(String countryCode) {
        createUser(countryCode, ExperienceLevel.REJECTED, ExperienceScore.REJECTED);
        assertTrue(pages().topNavigationPage().isLoggedOut());
    }

    /**
     * Tests main actions
     * 1. Create user via mobile api
     * 2. Update user experience level and score in the DB
     * 3. Login as created user into web
     * 4. Verify user was logged in and welcome message appears
     */
    @Test(description = "crm-9044")
    @Parameters({"countryCode"})
    public void checkCustomerLeverageForNoExperienceCustomer(String countryCode) {
        createUser(countryCode, ExperienceLevel.NO_EXPERIENCE, ExperienceScore.NO_EXPERIENCE);
        pages().welcomePage().start();
    }

    /**
     * Tests main actions
     * 1. Create user via mobile api
     * 2. Update user experience level and score in the DB
     * 3. Login as created user into web
     * 4. Verify user was logged in and welcome message appears
     */
    @Test(description = "crm-9021")
    @Parameters({"countryCode"})
    public void checkIsAbleToLoginForNoExperienceCustomer(String countryCode) {
        createUser(countryCode, ExperienceLevel.NO_EXPERIENCE, ExperienceScore.NO_EXPERIENCE);
        pages().welcomePage().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().signatureRiskWarning().RiskSignatureText(SIGNATURE_RW_NO_EXPERIENCED);
        updateCreditCard();
        assertUserLogin();
        assertTrue(pages().topNavigationPage().isLoggedIn());
    }

    /**
     * Tests main actions
     * 1. Create user via mobile api
     * 2. Update user experience level and score in the DB
     * 3. Login as created user into web
     * 4. Verify user was logged in and start trade message appears
     * 5. Verify 1:50 and 1:100 select leverage options are available
     */
    @Test(description = "crm-9045")
    @Parameters({"countryCode"})
    public void checkCustomerLeverageForLowExperienceCustomer(String countryCode) {
        createUser(countryCode, ExperienceLevel.LOW_EXPERIENCE, ExperienceScore.LOW_EXPERIENCE);
        pages().welcomePage().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().signatureRiskWarning().RiskSignatureText(SIGNATURE_RW_EXPERIENCED);
        updateCreditCard();
        pages().startTradeDialog().startTrade();
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesPickOptions().size(), 2);
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesPickOptions().get(FIRST_AVERAGE), AVERAGE1TO50);
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesPickOptions().get(SECOND_AVERAGE), AVERAGE1TO100);

    }

    /**
     * Tests main actions
     * 1. Create user via mobile api
     * 2. Update user experience level and score in the DB
     * 3. Login as created user into web
     * 4. Verify user was logged in
     */
    @Test(description = "crm-9022")
    @Parameters({"countryCode"})
    public void checkIsAbleToLoginForLowExperienceCustomer(String countryCode) {
        createUser(countryCode, ExperienceLevel.LOW_EXPERIENCE, ExperienceScore.LOW_EXPERIENCE);
        pages().welcomePage().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().signatureRiskWarning().RiskSignatureText("This is a risky investment but you are an experienced person and you aware to the consequences");
        updateCreditCard();
        assertUserLogin();
    }

    /**
     * Tests main actions
     * 1. Create user via mobile api
     * 2. Update user experience level and score in the DB
     * 3. Login as created user into web
     * 4. Verify user was logged in and start trade message appears
     * 5. Verify 1:50, 1:100, 1:200 select leverage options are available
     */
    @Test(description = "crm-9046")
    @Parameters({"countryCode"})
    public void checkCustomerLeverageForHighExperienceCustomer(String countryCode) {
        createUser(countryCode, ExperienceLevel.HIGH_EXPERIENCE, ExperienceScore.HIGH_EXPERIENCE);
        pages().welcomePage().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().signatureRiskWarning().RiskSignatureText(SIGNATURE_RW_EXPERIENCED);
        updateCreditCard();
        pages().startTradeDialog().startTrade();
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesPickOptions().size(), 3);
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesPickOptions().get(FIRST_AVERAGE), AVERAGE1TO50);
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesPickOptions().get(SECOND_AVERAGE), AVERAGE1TO100);
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesPickOptions().get(THIRD_AVERAGE), AVERAGE1TO200);
    }

    /**
     * Tests main actions
     * 1. Create user via mobile api
     * 2. Update user experience level and score in the DB
     * 3. Login as created user into web
     * 4. Verify user was logged in
     */
    @Test(description = "crm-9024")
    @Parameters({"countryCode"})
    public void checkIsAbleToLoginForHighExperienceCustomer(String countryCode) {
        createUser(countryCode, ExperienceLevel.HIGH_EXPERIENCE, ExperienceScore.HIGH_EXPERIENCE);
        pages().welcomePage().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().signatureRiskWarning().RiskSignatureText(SIGNATURE_RW_EXPERIENCED);
        updateCreditCard();
        assertUserLogin();
    }

    /**
     * Tests main actions
     * 1. Create user via mobile api
     * 2. Update user experience level and score in the DB
     * 3. Login as created user into web
     * 4. Verify user was logged in and start trade message appears
     * 5. Verify 1:50, 1:100, 1:200 select leverage options are available
     */
    @Test(description = "crm-9047")
    @Parameters({"countryCode"})
    public void checkCustomerLeverageForExpertCustomer(String countryCode) {
        createUser(countryCode, ExperienceLevel.EXPERT, ExperienceScore.EXPERT);
        pages().welcomePage().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        //Add message
        pages().signatureRiskWarning().RiskSignatureText(SIGNATURE_RW_EXPERIENCED);
        updateCreditCard();
        pages().startTradeDialog().startTrade();
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesPickOptions().size(), 3);
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesPickOptions().get(FIRST_AVERAGE), AVERAGE1TO50);
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesPickOptions().get(SECOND_AVERAGE), AVERAGE1TO100);
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesPickOptions().get(THIRD_AVERAGE), AVERAGE1TO200);
    }

    /**
     * Tests main actions
     * 1. Create user via mobile api
     * 2. Update user experience level and score in the DB
     * 3. Login as created user into web
     * 4. Verify user was logged in
     */
    @Test(description = "crm-9023")
    @Parameters({"countryCode"})
    public void checkIsAbleToLoginLeverageForExpertCustomer(String countryCode) {
        createUser(countryCode, ExperienceLevel.EXPERT, ExperienceScore.EXPERT);
        pages().welcomePage().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().signatureRiskWarning().RiskSignatureText(SIGNATURE_RW_EXPERIENCED);
        updateCreditCard();
        assertUserLogin();
    }

    /**
     * Assert that current user was logged in successfully
     */
    private void assertUserLogin() {
        pages().startTradeDialog().startTrade();
        pages().setLeverageDialog().closeLeverageDialog();
        assertTrue("User wan't login successfully", pages().topNavigationPage().isLoggedIn());
    }

    /**
     * Create user via mobile API using parameters to update in DB
     *
     * @param experienceLevel - experience level for the user
     * @param experienceScore - experience score to add for the user into DB
     * @return - created CRMCustomer object
     */
    private CRMCustomer createUser(String countryCode, ExperienceLevel experienceLevel, ExperienceScore experienceScore) {
        CRMCustomer crmCustomer = operations().customerOperations().registerWithWizardConditions(
                CustomerRO.builder(CRMMobileAPINamingStrategy.get())
                        .setCountryCode(countryCode),
                onboardingWizardConditions(experienceLevel)
        );
        operations().customerOperations().updateExperienceScoreInDB(crmCustomer.getId(), experienceScore.get());
        pages().topNavigationPage().logIn();
        pages().loginPage().login(crmCustomer.getUserName(), CustomerRO.CustomerROBuilder.DEFAULT_PASSWORD);
        return crmCustomer;
    }

    /**
     * Method to update credit card for current logged in user
     */
    private void updateCreditCard() {
        pages().creditCardDeposit().submit(
                CreditCardDeposit.builder()
                        .withDepositAmount("100")
                        .build());
    }

    /**
     * Build onboarding wizard condition
     *
     * @param experienceLevel - experience level to set into builder
     * @return OnboardingWizardConditions
     */
    private OnboardingWizardConditions onboardingWizardConditions(ExperienceLevel experienceLevel) {
        return new OnboardingWizardConditions(true, true, true, true,
                experienceLevel, false,
                OnboardingWizardConditions.AccountType.REAL,
                OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED,
                OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED, true,
                false, OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED);

    }
}
