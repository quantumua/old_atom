package com.betamedia.atom.testslibrary.option24.end2end.bmw;

import static com.betamedia.atom.core.utils.StringUtils.COMMA;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.util.Strings;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions.ExperienceLevel;

/**
 * Created by vsnigur on 5/29/17.
 * Updated by Nir Shukrun on 6/6/17.
 * Updated by Nir Shukrun on 6/13/17 - new Signature Risk Warning for Spain.
 */
public class CustomerLeverageWithRiskSignatureTest extends CustomerLeverageTest {

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
    @Parameters({"countryCode"})
    public void checkIsAbleToLoginForNoExperienceCustomer(String countryCode) {
        createUser(countryCode, ExperienceLevel.NO_EXPERIENCE, ExperienceScore.NO_EXPERIENCE);
        pages().welcomeDialog().start();
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
        pages().welcomeDialog().start();
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
        pages().welcomeDialog().start();
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
        pages().welcomeDialog().start();
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
        pages().welcomeDialog().start();
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
        pages().welcomeDialog().start();
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
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().signatureRiskWarning().RiskSignatureText(SIGNATURE_RW_EXPERIENCED);
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
        createUser(countryCode, ExperienceLevel.NO_EXPERIENCE, ExperienceScore.NO_EXPERIENCE);
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().signatureRiskWarning().RiskSignatureText(SIGNATURE_RW_NO_EXPERIENCED);
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
    @Parameters({"countryCode", "notAvailableProducts" })
    public void checkNotAvailableProductsForNoExperienceCustomer(String countryCode, String notAvailableProducts) {
        if (Strings.isNullOrEmpty(notAvailableProducts)) {
            return;
        }
        createUser(countryCode, ExperienceLevel.NO_EXPERIENCE, ExperienceScore.NO_EXPERIENCE);
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().signatureRiskWarning().RiskSignatureText(SIGNATURE_RW_NO_EXPERIENCED);
        updateCreditCard();
        assertUserLogin();
        Arrays.stream(notAvailableProducts.split(COMMA))
                .forEach(product -> assertFalse(pages().topNavigationPage().getProducts().contains(product)));
    }

    /**
     * Test main actions
     * 1. Create user via mobile api
     * 2. Update user experience level, score and country in the DB
     * 3. Login as created user into web
     * 4. Verify user was logged in and binary product is available
     */
    @Test(description = "crm-NA")
    @Parameters({"countryCode"})
    public void checkBinaryProductForNoExperienceCustomer(String countryCode) {
        createUser(countryCode, ExperienceLevel.NO_EXPERIENCE, ExperienceScore.NO_EXPERIENCE);
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().signatureRiskWarning().RiskSignatureText(SIGNATURE_RW_NO_EXPERIENCED);
        updateCreditCard();
        assertUserLogin();
//        pages().topNavigationPage().binary();
    }

    /**
     * Test main actions
     * 1. Create user via mobile api
     * 2. Update user experience level, score and country in the DB
     * 3. Login as created user into web
     * 4. Verify user was logged in and no binary product is available
     */
    @Test(description = "crm-NA")
    @Parameters({"countryCode"})
    public void checkNoBinaryProductForNoExperienceCustomer(String countryCode) {
        createUser(countryCode, ExperienceLevel.NO_EXPERIENCE, ExperienceScore.NO_EXPERIENCE);
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().signatureRiskWarning().RiskSignatureText(SIGNATURE_RW_NO_EXPERIENCED);
        updateCreditCard();
        assertUserLogin();
        assertFalse(pages().topNavigationPage().getProducts().contains("BINARY"));
    }

    /**
     * Test main actions
     * 1. Create user via mobile api
     * 2. Update user experience level, score and country in the DB
     * 3. Login as created user into web
     * 4. Verify user was logged in and cfd product is available
     */
    @Test(description = "crm-NA")
    @Parameters({"countryCode"})
    public void checkCfdProductForNoExperienceCustomer(String countryCode) {
        createUser(countryCode, ExperienceLevel.NO_EXPERIENCE, ExperienceScore.NO_EXPERIENCE);
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().signatureRiskWarning().RiskSignatureText(SIGNATURE_RW_NO_EXPERIENCED);
        updateCreditCard();
        assertUserLogin();
        pages().topNavigationPage().cfd();
    }

    /**
     * Test main actions
     * 1. Create user via mobile api
     * 2. Update user experience level, score and country in the DB
     * 3. Login as created user into web
     * 4. Verify user was logged in and no cfd product is available
     */
    @Test(description = "crm-NA")
    @Parameters({"countryCode"})
    public void checkNoCfdProductForNoExperienceCustomer(String countryCode) {
        createUser(countryCode, ExperienceLevel.NO_EXPERIENCE, ExperienceScore.NO_EXPERIENCE);
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().signatureRiskWarning().RiskSignatureText(SIGNATURE_RW_NO_EXPERIENCED);
        updateCreditCard();
        assertUserLogin();
        assertFalse(pages().topNavigationPage().getProducts().contains("CFD"));
    }

    /**
     * Test main actions
     * 1. Create user via mobile api
     * 2. Update user experience level, score and country in the DB
     * 3. Login as created user into web
     * 4. Verify user was logged in and forex product is available
     */
    @Test(description = "crm-NA")
    @Parameters({"countryCode"})
    public void checkForexProductForNoExperienceCustomer(String countryCode) {
        createUser(countryCode, ExperienceLevel.NO_EXPERIENCE, ExperienceScore.NO_EXPERIENCE);
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().signatureRiskWarning().RiskSignatureText(SIGNATURE_RW_NO_EXPERIENCED);
        updateCreditCard();
        assertUserLogin();
        assertTrue(pages().topNavigationPage().getProducts().contains("FOREX"));
    }

    /**
     * Test main actions
     * 1. Create user via mobile api
     * 2. Update user experience level, score and country in the DB
     * 3. Login as created user into web
     * 4. Verify user was logged in and no forex product is available
     */
    @Test(description = "crm-NA")
    @Parameters({"countryCode"})
    public void checkNoForexProductForNoExperienceCustomer(String countryCode) {
        createUser(countryCode, ExperienceLevel.NO_EXPERIENCE, ExperienceScore.NO_EXPERIENCE);
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().signatureRiskWarning().RiskSignatureText(SIGNATURE_RW_NO_EXPERIENCED);
        updateCreditCard();
        assertUserLogin();
        assertFalse(pages().topNavigationPage().getProducts().contains("FOREX"));
    }

}
