package com.betamedia.atom.testslibrary.option24.end2end.bmw;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.util.Strings;
import java.util.Arrays;
import static com.betamedia.atom.core.utils.StringUtils.COMMA;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by vsnigur on 5/29/17.
 * Updated by Nir Shukrun on 6/6/17.
 */
public class CustomerLeverageTest extends AbstractRestAPICustomerRegistrationTest {

    private final int FIRST_AVERAGE = 0;
    private final int SECOND_AVERAGE = 1;
    private final int THIRD_AVERAGE = 2;

    private final String AVERAGE1TO50 = "1:50";
    private final String AVERAGE1TO100 = "1:100";
    private final String AVERAGE1TO200 = "1:200";
    
    /**
     * Assert that current user was logged in successfully
     */
    public void assertUserLogin() {
        pages().startTradeDialog().startTrade();
        pages().setLeverageDialog().closeLeverageDialog();
        assertTrue("User wan't login successfully", pages().topNavigationPage().isLoggedIn());
    }
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
        pages().welcomeDialog().start();
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
        pages().welcomeDialog().start();
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
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
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
    public void checkIsAbbleToLoginForLowExperienceCustomer() {
        createUser(OnboardingWizardConditions.ExperienceLevel.LOW_EXPERIENCE, ExperienceScore.LOW_EXPERIENCE).getUserName();
        pages().welcomeDialog().start();
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
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
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
    public void checkIsAbbleToLoginForHighExperienceCustomer() {
        createUser(OnboardingWizardConditions.ExperienceLevel.HIGH_EXPERIENCE, ExperienceScore.HIGH_EXPERIENCE).getUserName();
        pages().welcomeDialog().start();
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
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
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
    public void checkIsAbbleToLoginLeverageForExpertCustomer() {
        createUser(OnboardingWizardConditions.ExperienceLevel.EXPERT, ExperienceScore.EXPERT).getUserName();
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        updateCreditCard();
        assertUserLogin();
    }

    /**
     * Test main actions
     * 1. Create user via mobile api
     * 2. Update user experience level, score and country in the DB
     * 3. Login as created user into web
     * 4. Verify user was logged in and products are available
     */
    @Test(description = "crm-NA")
    @Parameters({"countryCode", "availableProducts"})
    public void checkAvailableProductsForNoExperienceCustomer(String countryCode, String availableProducts) {
        if (Strings.isNullOrEmpty(availableProducts)) {
            return;
        }
        createUser(countryCode, OnboardingWizardConditions.ExperienceLevel.NO_EXPERIENCE, ExperienceScore.NO_EXPERIENCE);
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().riskWarningPage().accept();
        updateCreditCard();
        assertUserLogin();
        Arrays.stream(availableProducts.split(COMMA))
                .forEach(product -> assertTrue(pages().topNavigationPage().getProducts().contains(product)));
    }

    /**
     * Test main actions
     * 1. Create user via mobile api
     * 2. Update user experience level, score and country in the DB
     * 3. Login as created user into web
     * 4. Verify user was logged in and products are not available
     */
    @Test(description = "crm-NA")
    @Parameters({"countryCode", "notAvailableProducts"})
    public void checkNotAvailableProductsForNoExperienceCustomer(String countryCode, String notAvailableProducts) {
        if (Strings.isNullOrEmpty(notAvailableProducts)) {
            return;
        }
        createUser(countryCode, OnboardingWizardConditions.ExperienceLevel.NO_EXPERIENCE, ExperienceScore.NO_EXPERIENCE);
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().riskWarningPage().accept();
        updateCreditCard();
        assertUserLogin();
        Arrays.stream(notAvailableProducts.split(COMMA))
                .forEach(product -> assertFalse(pages().topNavigationPage().getProducts().contains(product)));
    }
}
