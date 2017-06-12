package com.betamedia.atom.testslibrary.option24.web;
import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.testslibrary.option24.end2end.crm.AbstractOnboardingConditionsTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by vsnigur on 5/29/17.
 * Updated by Nir Shukrun on 6/6/17.
 */
public class CustomerLeverageTest extends AbstractOnboardingConditionsTest {

    private OnboardingWizardConditions onboardingWizardConditions;
    private final int FIRST_AVERAGE = 0;
    private final int SECOND_AVERAGE = 1;
    private final int THIRD_AVERAGE = 2;

    private final String AVERAGE1TO50 = "1:50";
    private final String AVERAGE1TO100 = "1:100";
    private final String AVERAGE1TO200 = "1:200";

    /**
     * Tests main actions
     * 1. Create user via mobile api
     * 2. Update user experience level and score in the DB
     * 3. Login as created user into web
     * 4. Verify reject message
     */
    @Test(description = "crm-9043")
    public void checkNoCustomerLeverageForRejectedCustomer() {
        createUser(OnboardingWizardConditions.ExperienceLevel.REJECTED, ExperienceScore.REJECTED).getUserName();
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
    public void checkIsNotAbbleToLoginForRejectedCustomer() {
        createUser(OnboardingWizardConditions.ExperienceLevel.REJECTED, ExperienceScore.REJECTED).getUserName();
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
    public void checkCustomerLeverageForNoExperienceCustomer() {
        createUser(OnboardingWizardConditions.ExperienceLevel.NO_EXPERIENCE, ExperienceScore.NO_EXPERIENCE).getUserName();
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
    public void checkIsAbbleToLoginForNoExperienceCustomer() {
        createUser(OnboardingWizardConditions.ExperienceLevel.NO_EXPERIENCE, ExperienceScore.NO_EXPERIENCE).getUserName();
        pages().welcomePage().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().riskWarningPage().accept();
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
    public void checkCustomerLeverageForLowExperienceCustomer() {
        createUser(OnboardingWizardConditions.ExperienceLevel.LOW_EXPERIENCE, ExperienceScore.LOW_EXPERIENCE).getUserName();
        pages().welcomePage().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        updateCreditCard();
        pages().startTradeDialog().startTrade();
        pages().setLeverageDialog().expandList();
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesList().size(), 2);
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesList().get(FIRST_AVERAGE), AVERAGE1TO50);
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesList().get(SECOND_AVERAGE), AVERAGE1TO100);

    }

    /**
     * Tests main actions
     * 1. Create user via mobile api
     * 2. Update user experience level and score in the DB
     * 3. Login as created user into web
     * 4. Verify user was logged in
     */
    @Test(description = "crm-9022")
    public void checkIsAbbleToLoginForLowExperienceCustomer() {
        createUser(OnboardingWizardConditions.ExperienceLevel.LOW_EXPERIENCE, ExperienceScore.LOW_EXPERIENCE).getUserName();
        pages().welcomePage().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
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
    public void checkCustomerLeverageForHighExperienceCustomer() {
        createUser(OnboardingWizardConditions.ExperienceLevel.HIGH_EXPERIENCE, ExperienceScore.HIGH_EXPERIENCE).getUserName();
        pages().welcomePage().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        updateCreditCard();
        pages().startTradeDialog().startTrade();
        pages().setLeverageDialog().expandList();
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesList().size(), 3);
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesList().get(FIRST_AVERAGE), AVERAGE1TO50);
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesList().get(SECOND_AVERAGE), AVERAGE1TO100);
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesList().get(THIRD_AVERAGE), AVERAGE1TO200);
    }

    /**
     * Tests main actions
     * 1. Create user via mobile api
     * 2. Update user experience level and score in the DB
     * 3. Login as created user into web
     * 4. Verify user was logged in
     */
    @Test(description = "crm-9024")
    public void checkIsAbbleToLoginForHighExperienceCustomer() {
        createUser(OnboardingWizardConditions.ExperienceLevel.HIGH_EXPERIENCE, ExperienceScore.HIGH_EXPERIENCE).getUserName();
        pages().welcomePage().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
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
    public void checkCustomerLeverageForExpertCustomer() {
        createUser(OnboardingWizardConditions.ExperienceLevel.EXPERT, ExperienceScore.EXPERT).getUserName();
        pages().welcomePage().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        updateCreditCard();
        pages().startTradeDialog().startTrade();
        pages().setLeverageDialog().expandList();
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesList().size(), 3);
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesList().get(FIRST_AVERAGE), AVERAGE1TO50);
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesList().get(SECOND_AVERAGE), AVERAGE1TO100);
        Assert.assertEquals(pages().setLeverageDialog().getLeveragesList().get(THIRD_AVERAGE), AVERAGE1TO200);
    }

    /**
     * Tests main actions
     * 1. Create user via mobile api
     * 2. Update user experience level and score in the DB
     * 3. Login as created user into web
     * 4. Verify user was logged in
     */
    @Test(description = "crm-9023")
    public void checkIsAbbleToLoginLeverageForExpertCustomer() {
        createUser(OnboardingWizardConditions.ExperienceLevel.EXPERT, ExperienceScore.EXPERT).getUserName();
        pages().welcomePage().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        updateCreditCard();
        assertUserLogin();
    }

    /**
     * Assert that current user was logged in successfully
     */
    private void assertUserLogin() {
        pages().startTradeDialog().startTrade();
        pages().setLeverageDialog().cancelButton();
        assertTrue("User wan't login successfully", pages().topNavigationPage().isLoggedIn());
    }

    /**
     * Create user via mobile API using parameters to update in DB
     * @param experienceLevel - experience level for the user
     * @param experienceScore - experience score to add for the user into DB
     * @return - created CRMCustomer object
     */
    private CRMCustomer createUser(OnboardingWizardConditions.ExperienceLevel experienceLevel, ExperienceScore experienceScore) {
        onboardingWizardConditions = onboardingWizardConditions(experienceLevel);
        CRMCustomer crmCustomer = operations().customerOperations().registerWithWizardConditions(onboardingWizardConditions);
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
     * @param experienceLevel - experience level to set into builder
     * @return
     */
    private OnboardingWizardConditions onboardingWizardConditions(OnboardingWizardConditions.ExperienceLevel experienceLevel) {
        return new OnboardingWizardConditions(true, true, true, true,
                experienceLevel, false,
                OnboardingWizardConditions.AccountType.REAL,
                OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED,
                OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED, true,
                false, OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED);

    }

    /**
     * enums for experience scores in the current test class
     */
    private enum ExperienceScore {
        REJECTED(5),
        NO_EXPERIENCE(25),
        LOW_EXPERIENCE(45),
        HIGH_EXPERIENCE(65),
        EXPERT(85);

        private int score;

        ExperienceScore(int score) {
            this.score = score;
        }

        public int get() {
            return score;
        }
    }
}
