package com.betamedia.atom.testslibrary.option24.web.questionnaires;

import com.betamedia.atom.core.api.crm.form.entities.*;
import com.betamedia.atom.testslibrary.option24.end2end.bmw.AbstractWebCustomerRegistrationTest;

/**
 * Created by vsnigur on 8/29/17.
 */
public class AbstractExperienceLevelsTests extends AbstractWebCustomerRegistrationTest {

    private final static String OTHER_TEXT = "other answer";

    protected PersonalInformation getPersonalInformationScore0() {
        return PersonalInformation.builder()
                .withEmploymentStatus(QuestionnaireAnswers.EmploymentStatus.SALARIED_EMPLOYEE)
                .withIndustry(QuestionnaireAnswers.Industry.COMPUTER)
                .withEmployerName(EMPLOYER_NAME)
                .withTaxResidenceCountry(TAX_RESIDENCE_COUNTRY)
                .withTaxIdentificationNumberStatus(QuestionnaireAnswers.HasTaxIdentificationNumber.YES)
                .withTaxIdentificationNumber(TAX_IDENTIFICATION_NUMBER)
                .withUSReportabilityStatus(QuestionnaireAnswers.IsUSReportable.YES)
                .withEducationLevel(QuestionnaireAnswers.EducationLevel.PRIMARY)
                .withEducationField(QuestionnaireAnswers.EducationField.COMPUTER)
                .withPoliticalExposureStatus(QuestionnaireAnswers.IsPoliticallyExposed.NO)
                .withSourceOfFunds(QuestionnaireAnswers.SourceOfFunds.EMPLOYMENT)
                .withAnnualIncome(QuestionnaireAnswers.AnnualIncome.INCOME_OVER_100K)
                .withNetWealth(QuestionnaireAnswers.NetWealth.NET_WEALTH_OVER_300K)
                .withExpectedDepositsPerYear(QuestionnaireAnswers.ExpectedDepositsPerYear.DEPOSITS_OVER_50K)
                .withPurposeOfTrading(QuestionnaireAnswers.PurposeOfTrading.ADDITIONAL_INCOME)
                .build();
    }

    protected PersonalInformation getPersonalInformationScoreMaximum() {
        return PersonalInformation.builder()
                .withEmploymentStatus(QuestionnaireAnswers.EmploymentStatus.SALARIED_EMPLOYEE)
                .withIndustry(QuestionnaireAnswers.Industry.ACCOUNTING)
                .withEmployerName(EMPLOYER_NAME)
                .withTaxResidenceCountry(TAX_RESIDENCE_COUNTRY)
                .withUSReportabilityStatus(QuestionnaireAnswers.IsUSReportable.YES)
                .withTaxIdentificationNumberStatus(QuestionnaireAnswers.HasTaxIdentificationNumber.NO)
                .withSocialSecurityNumber(SOCIAL_SECURITY_NUMBER)
                .withEducationLevel(QuestionnaireAnswers.EducationLevel.POST_GRADUATE)
                .withEducationField(QuestionnaireAnswers.EducationField.ACCOUNTING)
                .withPoliticalExposureStatus(QuestionnaireAnswers.IsPoliticallyExposed.NO)
                .withSourceOfFunds(QuestionnaireAnswers.SourceOfFunds.EMPLOYMENT)
                .withAnnualIncome(QuestionnaireAnswers.AnnualIncome.INCOME_OVER_100K)
                .withNetWealth(QuestionnaireAnswers.NetWealth.NET_WEALTH_OVER_300K)
                .withExpectedDepositsPerYear(QuestionnaireAnswers.ExpectedDepositsPerYear.DEPOSITS_OVER_50K)
                .withPurposeOfTrading(QuestionnaireAnswers.PurposeOfTrading.SPECULATIVE)
                .build();
    }

    protected TradingExperienceInfo getRejectedTradingExperienceInfo() {
        return TradingExperienceInfo.builder()
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

                .withInstrumentsTradedBefore(QuestionnaireAnswers.InstrumentsTradedBefore.NO_EXPERIENCE)
                .withFrequencyPastTransactions(QuestionnaireAnswers.FrequencyPastTransactions.OCCASIONALLY)
                .withVolumePastTransaction(QuestionnaireAnswers.VolumePastTransaction.LESS_THAN_10)
                .withCommonLevelPastTransaction(QuestionnaireAnswers.CommonLevelPastTransaction.LOWER_THAN_1_50)
                .build();
    }

    protected TradingExperienceInfo getNoTradingExperienceInfo() {
        return TradingExperienceInfo.builder()
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

                .withInstrumentsTradedBefore(QuestionnaireAnswers.InstrumentsTradedBefore.NO_EXPERIENCE)
                .withFrequencyPastTransactions(QuestionnaireAnswers.FrequencyPastTransactions.OCCASIONALLY)
                .withVolumePastTransaction(QuestionnaireAnswers.VolumePastTransaction.LESS_THAN_10)
                .withCommonLevelPastTransaction(QuestionnaireAnswers.CommonLevelPastTransaction.LOWER_THAN_1_50)
                .build();
    }

    protected TradingExperienceInfo getLowTradingExperienceInfo() {
        return TradingExperienceInfo.builder()
                .withSharesExperience(QuestionnaireAnswers.SharesExperience.REGULARLY)
                .withBinaryExperience(QuestionnaireAnswers.BinaryExperience.REGULARLY)
                .withAverageYearlyBinaryVolume(QuestionnaireAnswers.AverageYearlyBinaryVolume.VOLUME_UNDER_500)
                .withForExExperience(QuestionnaireAnswers.ForExExperience.NEVER)
                .withFinancialWorkExperience(QuestionnaireAnswers.FinancialWorkExperience.WORKED)
                .withCfdBinaryKnowledge(QuestionnaireAnswers.CfdBinaryKnowledge.SPECULATIVE)
                .withMainFactorKnowledge(QuestionnaireAnswers.MainFactorKnowledge.INTEREST_RATES)
                .withHowToCloseKnowledge(QuestionnaireAnswers.HowToCloseKnowledge.ONLY_PLATFORM)
                .withCfdLeverageKnowledge(QuestionnaireAnswers.CfdLeverageKnowledge.MAGNIFIES)
                .withStopLossKnowledge(QuestionnaireAnswers.StopLossKnowledge.MINIMIZE)
                .withRequiredMarginKnowledge(QuestionnaireAnswers.RequiredMarginKnowledge.MARGIN_1K)
                .withMarginLevelDropKnowledge(QuestionnaireAnswers.MarginLevelDropKnowledge.WARNING_CALL)
                .withAutomaticStopKnowledge(QuestionnaireAnswers.AutomaticStopKnowledge.EQUITY_FALLS)
                .withLossOn1to50Knowledge(QuestionnaireAnswers.LossOn1to50Knowledge.A2_450)
                .withLossOn1to200Knowledge(QuestionnaireAnswers.LossOn1to200Knowledge.A1_1800)
                .withBinaryInvestProfitKnowledge(QuestionnaireAnswers.BinaryInvestProfitKnowledge.PROFIT_75)
                .withBinaryInvestLossKnowledge(QuestionnaireAnswers.BinaryInvestLossKnowledge.LOSS_100)
                .withBinaryProbabilityKnowledge(QuestionnaireAnswers.BinaryProbabilityKnowledge.MONEY_25)

                .withInstrumentsTradedBefore(QuestionnaireAnswers.InstrumentsTradedBefore.NO_EXPERIENCE)
                .withFrequencyPastTransactions(QuestionnaireAnswers.FrequencyPastTransactions.OCCASIONALLY)
                .withVolumePastTransaction(QuestionnaireAnswers.VolumePastTransaction.LESS_THAN_10)
                .withCommonLevelPastTransaction(QuestionnaireAnswers.CommonLevelPastTransaction.LOWER_THAN_1_50)
                .build();
    }

    protected TradingExperienceInfo getHighTradingExperienceInfo() {
        return TradingExperienceInfo.builder()
                .withSharesExperience(QuestionnaireAnswers.SharesExperience.FREQUENTLY)
                .withBinaryExperience(QuestionnaireAnswers.BinaryExperience.FREQUENTLY)
                .withAverageYearlyBinaryVolume(QuestionnaireAnswers.AverageYearlyBinaryVolume.VOLUME_ABOVE_10K)
                .withForExExperience(QuestionnaireAnswers.ForExExperience.FREQUENTLY)
                .withAverageYearlyForExVolume(QuestionnaireAnswers.AverageYearlyForExVolume.VOLUME_50K_150K)
                .withCommonLeverage(QuestionnaireAnswers.CommonForExLeverage.LEVERAGE_1TO50_1TO200)
                .withFinancialWorkExperience(QuestionnaireAnswers.FinancialWorkExperience.WORKED)
                .withCfdBinaryKnowledge(QuestionnaireAnswers.CfdBinaryKnowledge.SPECULATIVE)
                .withMainFactorKnowledge(QuestionnaireAnswers.MainFactorKnowledge.INTEREST_RATES)
                .withHowToCloseKnowledge(QuestionnaireAnswers.HowToCloseKnowledge.LONDON_STOCK)
                .withCfdLeverageKnowledge(QuestionnaireAnswers.CfdLeverageKnowledge.MAGNIFIES)
                .withStopLossKnowledge(QuestionnaireAnswers.StopLossKnowledge.MINIMIZE)
                .withRequiredMarginKnowledge(QuestionnaireAnswers.RequiredMarginKnowledge.MARGIN_1K)
                .withMarginLevelDropKnowledge(QuestionnaireAnswers.MarginLevelDropKnowledge.MARGIN_CALL)
                .withAutomaticStopKnowledge(QuestionnaireAnswers.AutomaticStopKnowledge.EQUITY_FALLS)
                .withLossOn1to50Knowledge(QuestionnaireAnswers.LossOn1to50Knowledge.A2_450)
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

    protected TradingExperienceInfo getExpertTradingExperienceInfo() {
        return TradingExperienceInfo.builder()
                .withSharesExperience(QuestionnaireAnswers.SharesExperience.FREQUENTLY)
                .withBinaryExperience(QuestionnaireAnswers.BinaryExperience.FREQUENTLY)
                .withAverageYearlyBinaryVolume(QuestionnaireAnswers.AverageYearlyBinaryVolume.VOLUME_ABOVE_10K)
                .withForExExperience(QuestionnaireAnswers.ForExExperience.FREQUENTLY)
                .withAverageYearlyForExVolume(QuestionnaireAnswers.AverageYearlyForExVolume.VOLUME_ABOVE_500K)
                .withCommonLeverage(QuestionnaireAnswers.CommonForExLeverage.LEVERAGE_ABOVE_1TO500)
                .withFinancialWorkExperience(QuestionnaireAnswers.FinancialWorkExperience.WORKED)
                .withCfdBinaryKnowledge(QuestionnaireAnswers.CfdBinaryKnowledge.SPECULATIVE)
                .withMainFactorKnowledge(QuestionnaireAnswers.MainFactorKnowledge.INTEREST_RATES)
                .withHowToCloseKnowledge(QuestionnaireAnswers.HowToCloseKnowledge.ONLY_PLATFORM)
                .withCfdLeverageKnowledge(QuestionnaireAnswers.CfdLeverageKnowledge.MAGNIFIES)
                .withStopLossKnowledge(QuestionnaireAnswers.StopLossKnowledge.MINIMIZE)
                .withRequiredMarginKnowledge(QuestionnaireAnswers.RequiredMarginKnowledge.MARGIN_1K)
                .withMarginLevelDropKnowledge(QuestionnaireAnswers.MarginLevelDropKnowledge.MARGIN_CALL)
                .withAutomaticStopKnowledge(QuestionnaireAnswers.AutomaticStopKnowledge.EQUITY_FALLS)
                .withLossOn1to50Knowledge(QuestionnaireAnswers.LossOn1to50Knowledge.A1_800)
                .withLossOn1to200Knowledge(QuestionnaireAnswers.LossOn1to200Knowledge.A1_1800)
                .withBinaryInvestProfitKnowledge(QuestionnaireAnswers.BinaryInvestProfitKnowledge.PROFIT_75)
                .withBinaryInvestLossKnowledge(QuestionnaireAnswers.BinaryInvestLossKnowledge.LOSS_100)
                .withBinaryProbabilityKnowledge(QuestionnaireAnswers.BinaryProbabilityKnowledge.MONEY_25)

                .withInstrumentsTradedBefore(QuestionnaireAnswers.InstrumentsTradedBefore.NO_EXPERIENCE)
                .withFrequencyPastTransactions(QuestionnaireAnswers.FrequencyPastTransactions.OCCASIONALLY)
                .withVolumePastTransaction(QuestionnaireAnswers.VolumePastTransaction.LESS_THAN_10)
                .withCommonLevelPastTransaction(QuestionnaireAnswers.CommonLevelPastTransaction.LOWER_THAN_1_50)
                .build();
    }

    protected PersonalInformation getPersonalInformationOtherAnswers() {
        return PersonalInformation.builder()
                .withEmploymentStatus(QuestionnaireAnswers.EmploymentStatus.SALARIED_EMPLOYEE)
                .withIndustry(QuestionnaireAnswers.Industry.OTHER)
                .withIndustryOther(OTHER_TEXT)
                .withEmployerName(EMPLOYER_NAME)
                .withTaxResidenceCountry(TAX_RESIDENCE_COUNTRY)
                .withTaxIdentificationNumberStatus(QuestionnaireAnswers.HasTaxIdentificationNumber.YES)
                .withTaxIdentificationNumber(TAX_IDENTIFICATION_NUMBER)
                .withUSReportabilityStatus(QuestionnaireAnswers.IsUSReportable.YES)
                .withEducationLevel(QuestionnaireAnswers.EducationLevel.PRIMARY)
                .withEducationField(QuestionnaireAnswers.EducationField.OTHER)
                .withEducationFieldOther(OTHER_TEXT)
                .withPoliticalExposureStatus(QuestionnaireAnswers.IsPoliticallyExposed.YES)
                .withPoliticalExposureComment(OTHER_TEXT)
                .withSourceOfFunds(QuestionnaireAnswers.SourceOfFunds.OTHER)
                .withSourceOfFundsOther(OTHER_TEXT)
                .withAnnualIncome(QuestionnaireAnswers.AnnualIncome.INCOME_OVER_100K)
                .withNetWealth(QuestionnaireAnswers.NetWealth.NET_WEALTH_OVER_300K)
                .withExpectedDepositsPerYear(QuestionnaireAnswers.ExpectedDepositsPerYear.DEPOSITS_OVER_50K)
                .withPurposeOfTrading(QuestionnaireAnswers.PurposeOfTrading.OTHER)
                .withPurposeOfTradingOther(OTHER_TEXT)
                .build();
    }

    protected PersonalInformation getPersonalInformationAnswersDependOfPrevious() {
        return PersonalInformation.builder()
                .withEmploymentStatus(QuestionnaireAnswers.EmploymentStatus.SALARIED_EMPLOYEE)
                .withIndustry(QuestionnaireAnswers.Industry.OTHER)
                .withEmployerName(EMPLOYER_NAME)
                .withTaxResidenceCountry(TAX_RESIDENCE_COUNTRY)
                .withTaxIdentificationNumberStatus(QuestionnaireAnswers.HasTaxIdentificationNumber.YES)
                .withTaxIdentificationNumber(TAX_IDENTIFICATION_NUMBER)
                .withUSReportabilityStatus(QuestionnaireAnswers.IsUSReportable.YES)
                .withEducationLevel(QuestionnaireAnswers.EducationLevel.PRIMARY)
                .withEducationField(QuestionnaireAnswers.EducationField.OTHER)
                .withPoliticalExposureStatus(QuestionnaireAnswers.IsPoliticallyExposed.YES)
                .withSourceOfFunds(QuestionnaireAnswers.SourceOfFunds.OTHER)
                .withAnnualIncome(QuestionnaireAnswers.AnnualIncome.INCOME_OVER_100K)
                .withNetWealth(QuestionnaireAnswers.NetWealth.NET_WEALTH_OVER_300K)
                .withExpectedDepositsPerYear(QuestionnaireAnswers.ExpectedDepositsPerYear.DEPOSITS_OVER_50K)
                .withPurposeOfTrading(QuestionnaireAnswers.PurposeOfTrading.OTHER)
                .build();
    }

}
