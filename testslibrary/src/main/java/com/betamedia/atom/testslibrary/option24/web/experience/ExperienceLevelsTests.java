package com.betamedia.atom.testslibrary.option24.web.experience;

import com.betamedia.atom.core.api.crm.form.entities.*;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;
import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import com.betamedia.atom.testslibrary.option24.end2end.bmw.AbstractOnboardingConditionsTest;
import org.testng.annotations.Test;

/**
 * Created by vsnigur on 8/29/17.
 */
public class ExperienceLevelsTests extends AbstractOnboardingConditionsTest {

    private static final String EMPLOYER_NAME = "testEmployer";
    private static final String TAX_RESIDENCE_COUNTRY = "AF";
    private static final String TAX_IDENTIFICATION_NUMBER = "1111111";

    @Test(description = "CTW-5508:Experience level - Rejected")
    @TestLinkProperties(displayId = "CTW-5508")
    public void ExperienceLevelRejected() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo
                .builder(WebSiteNamingStrategy.get()).build();
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(customerRegistrationInfo);
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        passQuestionnaireAsRejected();
        pages().creditCardDeposit().submit(CreditCardDeposit.builder().build());
        pages().thankYouPage().doContinue();
        operations().onBoardingOperations().assertUsernameRejected(customerRegistrationInfo.getEmail());
    }


    private void passQuestionnaireAsRejected() {
        pages().fnsPersonalInformation().submit(PersonalInformation.builder()
                .withEmploymentStatus(QuestionnaireAnswers.EmploymentStatus.UNEMPLOYED)
                .withIndustry(QuestionnaireAnswers.Industry.COMPUTER)
                .withEmployerName(EMPLOYER_NAME)
                .withTaxResidenceCountry(TAX_RESIDENCE_COUNTRY)
                .withTaxIdentificationNumberStatus(QuestionnaireAnswers.HasTaxIdentificationNumber.YES)
                .withTaxIdentificationNumber(TAX_IDENTIFICATION_NUMBER)
                .withUSReportabilityStatus(QuestionnaireAnswers.IsUSReportable.NO)
                .withEducationLevel(QuestionnaireAnswers.EducationLevel.POST_GRADUATE)
                .withEducationField(QuestionnaireAnswers.EducationField.ACCOUNTING)
                .withPoliticalExposureStatus(QuestionnaireAnswers.IsPoliticallyExposed.NO)
                .withSourceOfFunds(QuestionnaireAnswers.SourceOfFunds.EMPLOYMENT)
                .withAnnualIncome(QuestionnaireAnswers.AnnualIncome.INCOME_OVER_100K)
                .withNetWealth(QuestionnaireAnswers.NetWealth.NET_WEALTH_OVER_300K)
                .withExpectedDepositsPerYear(QuestionnaireAnswers.ExpectedDepositsPerYear.DEPOSITS_OVER_50K)
                .withPurposeOfTrading(QuestionnaireAnswers.PurposeOfTrading.SPECULATIVE)
                .build()
        );
        pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
                .withSharesExperience(QuestionnaireAnswers.SharesExperience.NEVER)
                .withBinaryExperience(QuestionnaireAnswers.BinaryExperience.NEVER)
                .withForExExperience(QuestionnaireAnswers.ForExExperience.NEVER)
                .withFinancialWorkExperience(QuestionnaireAnswers.FinancialWorkExperience.SEMINARS)
                .withCfdBinaryKnowledge(QuestionnaireAnswers.CfdBinaryKnowledge.NON_RISKY)
                .withMainFactorKnowledge(QuestionnaireAnswers.MainFactorKnowledge.ANNOUNCEMENT)
                .withHowToCloseKnowledge(QuestionnaireAnswers.HowToCloseKnowledge.LONDON_STOCK)
                .withCfdLeverageKnowledge(QuestionnaireAnswers.CfdLeverageKnowledge.PROVIDES)
                .withStopLossKnowledge(QuestionnaireAnswers.StopLossKnowledge.BUY)
                .withRequiredMarginKnowledge(QuestionnaireAnswers.RequiredMarginKnowledge.MARGIN_10K)
                .withMarginLevelDropKnowledge(QuestionnaireAnswers.MarginLevelDropKnowledge.WARNING_CALL)
                .withAutomaticStopKnowledge(QuestionnaireAnswers.AutomaticStopKnowledge.EARNINGS)
                .withLossOn1to50Knowledge(QuestionnaireAnswers.LossOn1to50Knowledge.A2_450)
                .withLossOn1to200Knowledge(QuestionnaireAnswers.LossOn1to200Knowledge.A2_1200)
                .withBinaryInvestProfitKnowledge(QuestionnaireAnswers.BinaryInvestProfitKnowledge.PROFIT_60)
                .withBinaryInvestLossKnowledge(QuestionnaireAnswers.BinaryInvestLossKnowledge.LOSS_75)
                .withBinaryProbabilityKnowledge(QuestionnaireAnswers.BinaryProbabilityKnowledge.MONEY_35)
                .withInstrumentsTradedBefore(QuestionnaireAnswers.InstrumentsTradedBefore.LEVERAGED)
                .withFrequencyPastTransactions(QuestionnaireAnswers.FrequencyPastTransactions.FREQUENTLY)
                .withVolumePastTransaction(QuestionnaireAnswers.VolumePastTransaction.LESS_THAN_10)
                .withCommonLevelPastTransaction(QuestionnaireAnswers.CommonLevelPastTransaction.LOWER_THAN_1_50)
                .build());
    }
}
