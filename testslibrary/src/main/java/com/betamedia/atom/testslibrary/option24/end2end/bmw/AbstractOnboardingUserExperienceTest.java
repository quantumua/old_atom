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
    public void createUserByUI(String countrycode, String phonecountryprefix, @Optional String depositAmount){
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(CustomerRegistrationInfo.builder(WebSiteNamingStrategy.get()).withCountry(countrycode)
                .withPhoneCountryPrefix(phonecountryprefix)
                .withCurrency(Currency.USD.getFullName())
                .build());
        pages().welcomeDialog().isStartBtnDisplayed();
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        PersonalInformation personalInfo = getPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfo);
        pages().fnsTradingExperience().submit(getTradingExperienceInfo());
        pages().creditCardDeposit().submit((CreditCardDeposit.builder()
                .withDepositAmount(depositAmount)
                .build()));
        pages().thankYouPage().doContinue();
        pages().fnsEmployerInfo().submit(personalInfo);
    }

    private PersonalInformation getPersonalInformation() {
        return PersonalInformation.builder()
                .withEmploymentStatus(EmploymentStatus.SALARIED_EMPLOYEE)
                .withIndustry(Industry.FINANCE)
                .withEmployerName("fgsfds")
                .withTaxResidenceCountry("DE")
                .withUSReportabilityStatus(IsUSReportable.NO)
                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.NO)
                .withTaxIdentificationNumber("123456789")
                .withEducationLevel(EducationLevel.POST_GRADUATE)
                .withEducationField(EducationField.ACCOUNTING)
                .withPoliticalExposureStatus(IsPoliticallyExposed.NO)
                .withSourceOfFunds(SourceOfFunds.EMPLOYMENT)
                .withAnnualIncome(AnnualIncome.INCOME_OVER_100K)
                .withNetWealth(NetWealth.NET_WEALTH_OVER_300K)
                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_OVER_50K)
                .withPurposeOfTrading(PurposeOfTrading.ADDITIONAL_INCOME)
                .build();
    }

    private TradingExperienceInfo getTradingExperienceInfo(){
        return TradingExperienceInfo.builder()
                .withSharesExperience(SharesExperience.NEVER)
                .withBinaryExperience(BinaryExperience.OCCASIONALLY)
                .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_500_5K)
                .withForExExperience(ForExExperience.NEVER)
                .withFinancialWorkExperience(FinancialWorkExperience.WORKED)
                .withCfdBinaryKnowledge(CfdBinaryKnowledge.SPECULATIVE)
                .withMainFactorKnowledge(MainFactorKnowledge.ANNOUNCEMENT)
                .withHowToCloseKnowledge(HowToCloseKnowledge.LONDON_STOCK)
                .withCfdLeverageKnowledge(CfdLeverageKnowledge.PROVIDES)
                .withStopLossKnowledge(StopLossKnowledge.MINIMIZE)
                .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K)
                .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.WARNING_CALL)
                .withAutomaticStopKnowledge(AutomaticStopKnowledge.EARNINGS)
                .withLossOn1to50Knowledge(LossOn1to50Knowledge.A1_800)
                .withLossOn1to200Knowledge(LossOn1to200Knowledge.A1_1800)
                .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_75)
                .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_75)
                .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_35)
                .build();
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