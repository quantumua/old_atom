package com.betamedia.atom.testslibrary.option24.end2end.bmw;

import com.betamedia.atom.core.api.crm.form.entities.*;
import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions.ExperienceLevel;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.CRMMobileAPINamingStrategy;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.core.api.web.form.Currency;
import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import org.testng.annotations.Optional;

import static com.betamedia.atom.core.api.crm.form.entities.QuestionnaireAnswers.*;

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
        pages().loginDialog().login(crmCustomer.getUserName(), CustomerRO.CustomerROBuilder.DEFAULT_PASSWORD);
        return crmCustomer;
    }

    /**
     * Create user via WEB UI using specific parameters
     *
     * @param countrycode - country code
     * @param phonecountryprefix - phone country prefix to use
     * @param depositAmount - deposit amount to set in credit card deposit slide
     */

    public CustomerRegistrationInfo createUserByUI(String countrycode, String phonecountryprefix, @Optional String depositAmount){
        pages().topNavigationPage().signUp();
        CustomerRegistrationInfo customerRegistrationInfo=CustomerRegistrationInfo.builder(WebSiteNamingStrategy.get()).withCountry(countrycode)
                .withPhoneCountryPrefix(phonecountryprefix)
                .withCurrency(Currency.USD.getFullName())
                .build();
        pages().registrationDialog().register(customerRegistrationInfo);
        pages().welcomeDialog().isStartBtnDisplayed();
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        PersonalInformation personalInfo = getPersonalInformation();
        passQuestionnaire(personalInfo, getTradingExperienceInfo());
        pages().creditCardDeposit().submit((CreditCardDeposit.builder()
                .withDepositAmount(depositAmount)
                .build()));
        pages().thankYouPage().doContinue();
        pages().fnsEmployerInfo().submit(personalInfo);
        return customerRegistrationInfo;
    }

    /**
     * Create user via WEB UI
     * pass all questionnaires using provided information
     * @param personalInformation - personal information for answers
     * @param tradingExperienceInfo - trading experience for answers
     * @return - created customer object
     */
    protected CustomerRegistrationInfo createUserByUI(
            PersonalInformation personalInformation, TradingExperienceInfo tradingExperienceInfo, boolean riskWarning) {
        CustomerRegistrationInfo customerRegistrationInfo = getCustomerRegistrationInfo();
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        passQuestionnaire(personalInformation,tradingExperienceInfo);
        if (riskWarning) pages().riskWarning().accept();
        pages().creditCardDeposit().submit(CreditCardDeposit.builder().build());
        pages().thankYouPage().doContinue();
        return customerRegistrationInfo;
    }

    private void passQuestionnaire(PersonalInformation personalInformation, TradingExperienceInfo tradingExperienceInfo) {
        pages().fnsPersonalInformation().submit(personalInformation);
        pages().fnsTradingExperience().submit(tradingExperienceInfo);
    }

    /**
     * make customer through the web
     * @return customer info object used for customer creation
     */
    private CustomerRegistrationInfo getCustomerRegistrationInfo() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo
                .builder(WebSiteNamingStrategy.get()).build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(customerRegistrationInfo);
        return customerRegistrationInfo;
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