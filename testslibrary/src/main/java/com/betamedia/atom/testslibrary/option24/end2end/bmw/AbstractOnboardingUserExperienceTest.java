package com.betamedia.atom.testslibrary.option24.end2end.bmw;

import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions.ExperienceLevel;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.CRMMobileAPINamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;

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
