package com.betamedia.atom.testslibrary.option24.end2end.bmw;

import com.betamedia.atom.core.api.crm.form.entities.*;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import com.betamedia.atom.core.testingtype.web.WebEndToEndTest;

/**
 * Created by vsnigur on 8/31/17.
 */
public class AbstractCustomerRegistrationTest extends WebEndToEndTest {

    public static final String EMPLOYER_NAME = "testEmployer";
    public static final String TAX_RESIDENCE_COUNTRY = "DE";
    public static final String TAX_IDENTIFICATION_NUMBER = "123456789";
    public static final String SOCIAL_SECURITY_NUMBER = "1234567890";

    /**
     * Build onboarding wizard condition
     * @param experienceLevel - questionnaires level to set into builder
     * @return
     */
    protected OnboardingWizardConditions getConditions(OnboardingWizardConditions.ExperienceLevel experienceLevel) {
        return new OnboardingWizardConditions(true, true, true, true,
                experienceLevel, false,
                OnboardingWizardConditions.AccountType.REAL,
                OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED,
                OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED, true,
                false, OnboardingWizardConditions.DocumentVerificationStatus.VERIFIED);

    }

    /**
     * Create user via mobile API using parameters to update in DB
     *
     * @param builder - customer builder to use
     * @param conditions - onboarding wizard conditions to use
     * @param score - target questionnaires score to set in database
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

    protected void passQuestionnaire(PersonalInformation personalInformation, TradingExperienceInfo tradingExperienceInfo) {
        pages().fnsPersonalInformation().submit(personalInformation);
        pages().fnsTradingExperience().submit(tradingExperienceInfo);
    }

    /**
     * make customer through the web
     * @return customer info object used for customer creation
     */
    protected CustomerRegistrationInfo getCustomerRegistrationInfo() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo
                .builder(WebSiteNamingStrategy.get()).build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(customerRegistrationInfo);
        return customerRegistrationInfo;
    }


    /**
     * Method to update credit card for current logged in user
     */
    protected void updateCreditCard() {
        pages().creditCardDeposit().submit(
                CreditCardDeposit.builder().build());
    }

    /**
     * enums for questionnaires scores in web test classes
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

    public PersonalInformation getPersonalInformation() {
        return PersonalInformation.builder()
                .withEmploymentStatus(QuestionnaireAnswers.EmploymentStatus.SALARIED_EMPLOYEE)
                .withIndustry(QuestionnaireAnswers.Industry.FINANCE)
                .withEmployerName(EMPLOYER_NAME)
                .withTaxResidenceCountry(TAX_RESIDENCE_COUNTRY)
                .withUSReportabilityStatus(QuestionnaireAnswers.IsUSReportable.NO)
                .withTaxIdentificationNumberStatus(QuestionnaireAnswers.HasTaxIdentificationNumber.NO)
                .withTaxIdentificationNumber(TAX_IDENTIFICATION_NUMBER)
                .withSocialSecurityNumber(SOCIAL_SECURITY_NUMBER)
                .withEducationLevel(QuestionnaireAnswers.EducationLevel.POST_GRADUATE)
                .withEducationField(QuestionnaireAnswers.EducationField.ACCOUNTING)
                .withPoliticalExposureStatus(QuestionnaireAnswers.IsPoliticallyExposed.NO)
                .withSourceOfFunds(QuestionnaireAnswers.SourceOfFunds.EMPLOYMENT)
                .withAnnualIncome(QuestionnaireAnswers.AnnualIncome.INCOME_OVER_100K)
                .withNetWealth(QuestionnaireAnswers.NetWealth.NET_WEALTH_OVER_300K)
                .withExpectedDepositsPerYear(QuestionnaireAnswers.ExpectedDepositsPerYear.DEPOSITS_OVER_50K)
                .withPurposeOfTrading(QuestionnaireAnswers.PurposeOfTrading.ADDITIONAL_INCOME)
                .build();
    }

    public TradingExperienceInfo getTradingExperienceInfo(){
        return TradingExperienceInfo.builder()
                .withSharesExperience(QuestionnaireAnswers.SharesExperience.NEVER)
                .withBinaryExperience(QuestionnaireAnswers.BinaryExperience.OCCASIONALLY)
                .withAverageYearlyBinaryVolume(QuestionnaireAnswers.AverageYearlyBinaryVolume.VOLUME_ABOVE_10K)
                .withForExExperience(QuestionnaireAnswers.ForExExperience.FREQUENTLY)
                .withFinancialWorkExperience(QuestionnaireAnswers.FinancialWorkExperience.WORKED)
                .withCfdBinaryKnowledge(QuestionnaireAnswers.CfdBinaryKnowledge.SPECULATIVE)
                .withMainFactorKnowledge(QuestionnaireAnswers.MainFactorKnowledge.ANNOUNCEMENT)
                .withHowToCloseKnowledge(QuestionnaireAnswers.HowToCloseKnowledge.LONDON_STOCK)
                .withCfdLeverageKnowledge(QuestionnaireAnswers.CfdLeverageKnowledge.PROVIDES)
                .withStopLossKnowledge(QuestionnaireAnswers.StopLossKnowledge.MINIMIZE)
                .withRequiredMarginKnowledge(QuestionnaireAnswers.RequiredMarginKnowledge.MARGIN_1K)
                .withMarginLevelDropKnowledge(QuestionnaireAnswers.MarginLevelDropKnowledge.WARNING_CALL)
                .withAutomaticStopKnowledge(QuestionnaireAnswers.AutomaticStopKnowledge.EARNINGS)
                .withLossOn1to50Knowledge(QuestionnaireAnswers.LossOn1to50Knowledge.A1_800)
                .withLossOn1to200Knowledge(QuestionnaireAnswers.LossOn1to200Knowledge.A1_1800)
                .withBinaryInvestProfitKnowledge(QuestionnaireAnswers.BinaryInvestProfitKnowledge.PROFIT_75)
                .withBinaryInvestLossKnowledge(QuestionnaireAnswers.BinaryInvestLossKnowledge.LOSS_75)
                .withBinaryProbabilityKnowledge(QuestionnaireAnswers.BinaryProbabilityKnowledge.MONEY_35)

                .withInstrumentsTradedBefore(QuestionnaireAnswers.InstrumentsTradedBefore.NO_EXPERIENCE)
                .withFrequencyPastTransactions(QuestionnaireAnswers.FrequencyPastTransactions.OCCASIONALLY)
                .withVolumePastTransaction(QuestionnaireAnswers.VolumePastTransaction.LESS_THAN_10)
                .withCommonLevelPastTransaction(QuestionnaireAnswers.CommonLevelPastTransaction.LOWER_THAN_1_50)
                .build();
    }
}
