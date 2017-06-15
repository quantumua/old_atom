package com.betamedia.atom.testslibrary.option24.end2end.bmw;

import org.testng.Assert;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions.ExperienceLevel;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.CRMMobileAPINamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.core.dsl.operations.TagOperations;
import com.betamedia.tp.api.model.Asset;
import com.betamedia.tp.api.model.enums.OptionType;

/**
 * @author  leonid.a
 * @since   5/29/17.
 */
public class AbstractOnboardingUserExperienceTest extends AbstractOnboardingConditionsTest {
    /**
     * Create user via mobile API using parameters to update in DB
     * @param experienceLevel - experience level for the user
     * @param experienceScore - experience score to add for the user into DB
     * @return - created CRMCustomer object
     */
    protected CRMCustomer createUser(OnboardingWizardConditions.ExperienceLevel experienceLevel, ExperienceScore experienceScore) {
        return createUser(CustomerRO.builder(CRMMobileAPINamingStrategy.get()), getConditions(experienceLevel), experienceScore);
    }
    /**
     * Create user via mobile API using parameters to update in DB
     *
     * @param experienceLevel - experience level for the user
     * @param experienceScore - experience score to add for the user into DB
     * @return - created CRMCustomer object
     */
    protected CRMCustomer createUser(String countryCode, ExperienceLevel experienceLevel, ExperienceScore experienceScore) {
        return createUser(CustomerRO.builder(CRMMobileAPINamingStrategy.get())
                .setCountryCode(countryCode), getConditions(experienceLevel), experienceScore);
    }
    /**
     * Create user via mobile API using parameters to update in DB
     *
     * @param builder - customer builder to use
     * @param conditions - onboarding wizard conditions to use
     * @param score - target experience score to set in database
     * @return - created CRMCustomer object
     */
    protected CRMCustomer createUser(CustomerRO.CustomerROBuilder builder, OnboardingWizardConditions conditions, ExperienceScore score){
        CRMCustomer crmCustomer = operations().customerOperations().registerWithWizardConditions(
                builder,
                conditions
        );
        operations().customerOperations().updateExperienceScoreInDB(crmCustomer.getId(), score.get());
        pages().topNavigationPage().logIn();
        pages().loginPage().login(crmCustomer.getUserName(), CustomerRO.CustomerROBuilder.DEFAULT_PASSWORD);
        return crmCustomer;
    }
    /**
     * Build onboarding wizard condition
     * @param experienceLevel - experience level to set into builder
     * @return
     */
    private OnboardingWizardConditions getConditions(ExperienceLevel experienceLevel) {
        return new OnboardingWizardConditions(true, true, true, true,
                experienceLevel, false,
                OnboardingWizardConditions.AccountType.REAL,
                OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED,
                OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED, true,
                false, OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED);
    
    }
    /**
     * Method to update credit card for current logged in user
     */
    protected void updateCreditCard() {
        pages().creditCardDeposit().submit(
                CreditCardDeposit.builder().build());
    }

    /**
     * Create user via mobile API with parameters:
     * ExperienceLevel = HIGH_EXPERIENCE
     * ExperienceScore = HIGH_EXPERIENCE
     */

    protected CRMCustomer createHighExperiencedUser() {
        CRMCustomer crmCustomer = createUser(OnboardingWizardConditions.ExperienceLevel.HIGH_EXPERIENCE, ExperienceScore.HIGH_EXPERIENCE);
        pages().welcomePage().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        updateCreditCard();
        pages().startTradeDialog().startTrade();
        Assert.assertTrue(pages().topNavigationPage().isLoggedIn());
        return crmCustomer;
    }

    /**
     * Asset is ready to trade:
     * 1. Get asset
     * 2. Create a option: HILO, SHORT_TERM_60_SEC_GAME_H3_TEXT
     * 3. Inject Feed for asset with price 1.5d
     */
    
    protected Asset assetIsReadyToTrade() {
    Asset asset = operations().assetOperations().get();
    operations().optionTemplateOperations().create(asset.getId(), OptionType.HILO, TagOperations.TagName.SHORT_TERM_60_SEC_GAME_H3_TEXT);
    operations().feedOperations().injectFeed(asset.getId(), 1.5d);
    return asset;
    }
    
    /**
     * enums for experience scores in web test classes 
     */
    
    public enum ExperienceScore {
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
