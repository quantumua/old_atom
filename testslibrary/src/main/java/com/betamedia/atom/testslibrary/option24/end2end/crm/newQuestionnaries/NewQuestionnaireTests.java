package com.betamedia.atom.testslibrary.option24.end2end.crm.newQuestionnaries;

import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.atom.core.api.crm.form.entities.QuestionnaireAnswers;
import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WidgetsNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.testingtype.widgets.WidgetsEndToEndTest;
import com.betamedia.common.enums.Country;
import org.testng.annotations.Test;

/**
 * Created by vadyms on 5/22/17.
 */
public class NewQuestionnaireTests extends WidgetsEndToEndTest {

    private final static String FREE_TEXT = "Free text";
    private final static String COUNTRY = Country.TOGO.getDbValue().toUpperCase();
    private final static String TAX_ID = "1234567890";

    @Test(description = "ID:9129")
    public void calculationOfScore86Test(){
        pages().navigation().register();
        CustomerRO customer = CustomerRO.builder(WidgetsNamingStrategy.get()).build();
        pages().registerPage().register(customer);
//        pages().navigation().login();
//        pages().crmLoginPage().login(customer.getEmail(),"123123");
        pages().navigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(PersonalInformation.builder()
                .withEmploymentStatus(QuestionnaireAnswers.EmploymentStatus.SALARIED_EMPLOYEE)
                .withIndustry(QuestionnaireAnswers.Industry.ACCOUNTING)
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(QuestionnaireAnswers.HasTaxIdentificationNumber.YES)
                .withTaxIdentificationNumber(TAX_ID)
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
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
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
                .withLossOn1to200Knowledge(QuestionnaireAnswers.LossOn1to200Knowledge.A2_1200)
                .withBinaryInvestProfitKnowledge(QuestionnaireAnswers.BinaryInvestProfitKnowledge.PROFIT_60)
                .withBinaryInvestLossKnowledge(QuestionnaireAnswers.BinaryInvestLossKnowledge.LOSS_75)
                .withBinaryProbabilityKnowledge(QuestionnaireAnswers.BinaryProbabilityKnowledge.MONEY_35)
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(), 86d);
    }

    @Test(description = "ID:9130")
    public void calculationOfScore49Test(){
        pages().navigation().register();
        CustomerRO customer = CustomerRO.builder(WidgetsNamingStrategy.get()).build();
        pages().registerPage().register(customer);
        pages().navigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(PersonalInformation.builder()
                .withEmploymentStatus(QuestionnaireAnswers.EmploymentStatus.UNEMPLOYED)
                .withIndustry(QuestionnaireAnswers.Industry.FINANCE)
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(QuestionnaireAnswers.HasTaxIdentificationNumber.NO)
                .withSocialSecurityNumber(TAX_ID)
                .withUSReportabilityStatus(QuestionnaireAnswers.IsUSReportable.YES)
                .withEducationLevel(QuestionnaireAnswers.EducationLevel.BACHELOR)
                .withEducationField(QuestionnaireAnswers.EducationField.LAW)
                .withPoliticalExposureStatus(QuestionnaireAnswers.IsPoliticallyExposed.YES)
                .withPoliticalExposureComment(FREE_TEXT)
                .withSourceOfFunds(QuestionnaireAnswers.SourceOfFunds.SAVINGS)
                .withAnnualIncome(QuestionnaireAnswers.AnnualIncome.INCOME_50K_100K)
                .withNetWealth(QuestionnaireAnswers.NetWealth.NET_WEALTH_150K_300K)
                .withExpectedDepositsPerYear(QuestionnaireAnswers.ExpectedDepositsPerYear.DEPOSITS_25K_50K)
                .withPurposeOfTrading(QuestionnaireAnswers.PurposeOfTrading.ADDITIONAL_INCOME)
                .build()
        );
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
                .withSharesExperience(QuestionnaireAnswers.SharesExperience.REGULARLY)
                .withBinaryExperience(QuestionnaireAnswers.BinaryExperience.FREQUENTLY)
                .withAverageYearlyBinaryVolume(QuestionnaireAnswers.AverageYearlyBinaryVolume.VOLUME_ABOVE_10K)
                .withForExExperience(QuestionnaireAnswers.ForExExperience.REGULARLY)
                .withAverageYearlyForExVolume(QuestionnaireAnswers.AverageYearlyForExVolume.VOLUME_ABOVE_500K)
                .withCommonLeverage(QuestionnaireAnswers.CommonForExLeverage.LEVERAGE_ABOVE_1TO500)
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
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(), 49d);    }

    @Test(description = "ID:9131")
    public void calculationOfScore48Test(){
        pages().navigation().register();
        CustomerRO customer = CustomerRO.builder(WidgetsNamingStrategy.get()).build();
        pages().registerPage().register(customer);
        pages().navigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(PersonalInformation.builder()
                .withEmploymentStatus(QuestionnaireAnswers.EmploymentStatus.STUDENT)
                .withIndustry(QuestionnaireAnswers.Industry.FUNDS)
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(QuestionnaireAnswers.HasTaxIdentificationNumber.YES)
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(QuestionnaireAnswers.IsUSReportable.NO)
                .withEducationLevel(QuestionnaireAnswers.EducationLevel.SECONDARY)
                .withEducationField(QuestionnaireAnswers.EducationField.COMPUTER)
                .withPoliticalExposureStatus(QuestionnaireAnswers.IsPoliticallyExposed.NO)
                .withSourceOfFunds(QuestionnaireAnswers.SourceOfFunds.RETIREMENT)
                .withAnnualIncome(QuestionnaireAnswers.AnnualIncome.INCOME_25K_50K)
                .withNetWealth(QuestionnaireAnswers.NetWealth.NET_WEALTH_50K_150K)
                .withExpectedDepositsPerYear(QuestionnaireAnswers.ExpectedDepositsPerYear.DEPOSITS_15K_25K)
                .withPurposeOfTrading(QuestionnaireAnswers.PurposeOfTrading.HEDGING)
                .build()
        );
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
                .withSharesExperience(QuestionnaireAnswers.SharesExperience.OCCASIONALLY)
                .withBinaryExperience(QuestionnaireAnswers.BinaryExperience.REGULARLY)
                .withAverageYearlyBinaryVolume(QuestionnaireAnswers.AverageYearlyBinaryVolume.VOLUME_ABOVE_10K)
                .withForExExperience(QuestionnaireAnswers.ForExExperience.OCCASIONALLY)
                .withAverageYearlyForExVolume(QuestionnaireAnswers.AverageYearlyForExVolume.VOLUME_ABOVE_500K)
                .withCommonLeverage(QuestionnaireAnswers.CommonForExLeverage.LEVERAGE_ABOVE_1TO500)
                .withFinancialWorkExperience(QuestionnaireAnswers.FinancialWorkExperience.BOTH)
                .withCfdBinaryKnowledge(QuestionnaireAnswers.CfdBinaryKnowledge.PHYSICALLY_DELIVERING)
                .withMainFactorKnowledge(QuestionnaireAnswers.MainFactorKnowledge.EMPLOYEE_LAYOFFS)
                .withHowToCloseKnowledge(QuestionnaireAnswers.HowToCloseKnowledge.CANNOT_SELL)
                .withCfdLeverageKnowledge(QuestionnaireAnswers.CfdLeverageKnowledge.NONE)
                .withStopLossKnowledge(QuestionnaireAnswers.StopLossKnowledge.TAKE)
                .withRequiredMarginKnowledge(QuestionnaireAnswers.RequiredMarginKnowledge.MARGIN_100K)
                .withMarginLevelDropKnowledge(QuestionnaireAnswers.MarginLevelDropKnowledge.MARGIN_UPGRADE)
                .withAutomaticStopKnowledge(QuestionnaireAnswers.AutomaticStopKnowledge.OPEN_POSITION)
                .withLossOn1to50Knowledge(QuestionnaireAnswers.LossOn1to50Knowledge.A3_200)
                .withLossOn1to200Knowledge(QuestionnaireAnswers.LossOn1to200Knowledge.A3_1000)
                .withBinaryInvestProfitKnowledge(QuestionnaireAnswers.BinaryInvestProfitKnowledge.PROFIT_100)
                .withBinaryInvestLossKnowledge(QuestionnaireAnswers.BinaryInvestLossKnowledge.LOSS_50)
                .withBinaryProbabilityKnowledge(QuestionnaireAnswers.BinaryProbabilityKnowledge.MONEY_45)
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(), 48d);
    }


    @Test(description = "ID:9132")
    public void calculationOfScore32Test(){
        pages().navigation().register();
        CustomerRO customer = CustomerRO.builder(WidgetsNamingStrategy.get()).build();
        pages().registerPage().register(customer);
        pages().navigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(PersonalInformation.builder()
                .withEmploymentStatus(QuestionnaireAnswers.EmploymentStatus.RETIRED)
                .withIndustry(QuestionnaireAnswers.Industry.ATTORNEYS)
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(QuestionnaireAnswers.HasTaxIdentificationNumber.YES)
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(QuestionnaireAnswers.IsUSReportable.NO)
                .withEducationLevel(QuestionnaireAnswers.EducationLevel.PRIMARY)
                .withEducationField(QuestionnaireAnswers.EducationField.MEDICINE)
                .withPoliticalExposureStatus(QuestionnaireAnswers.IsPoliticallyExposed.NO)
                .withSourceOfFunds(QuestionnaireAnswers.SourceOfFunds.INHERITANCE)
                .withAnnualIncome(QuestionnaireAnswers.AnnualIncome.INCOME_15K_25K)
                .withNetWealth(QuestionnaireAnswers.NetWealth.NET_WEALTH_15K_50K)
                .withExpectedDepositsPerYear(QuestionnaireAnswers.ExpectedDepositsPerYear.DEPOSITS_10K_15K)
                .withPurposeOfTrading(QuestionnaireAnswers.PurposeOfTrading.OTHER)
                .withPurposeOfTradingOther(FREE_TEXT)
                .build()
        );
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
                .withSharesExperience(QuestionnaireAnswers.SharesExperience.NEVER)
                .withBinaryExperience(QuestionnaireAnswers.BinaryExperience.NEVER)
                .withForExExperience(QuestionnaireAnswers.ForExExperience.NEVER)
                .withFinancialWorkExperience(QuestionnaireAnswers.FinancialWorkExperience.NEITHER)
                .withCfdBinaryKnowledge(QuestionnaireAnswers.CfdBinaryKnowledge.SPECULATIVE)
                .withMainFactorKnowledge(QuestionnaireAnswers.MainFactorKnowledge.INTEREST_RATES)
                .withHowToCloseKnowledge(QuestionnaireAnswers.HowToCloseKnowledge.ONLY_PLATFORM)
                .withCfdLeverageKnowledge(QuestionnaireAnswers.CfdLeverageKnowledge.MAGNIFIES)
                .withStopLossKnowledge(QuestionnaireAnswers.StopLossKnowledge.BUY)
                .withRequiredMarginKnowledge(QuestionnaireAnswers.RequiredMarginKnowledge.MARGIN_1K)
                .withMarginLevelDropKnowledge(QuestionnaireAnswers.MarginLevelDropKnowledge.MARGIN_CALL)
                .withAutomaticStopKnowledge(QuestionnaireAnswers.AutomaticStopKnowledge.EQUITY_FALLS)
                .withLossOn1to50Knowledge(QuestionnaireAnswers.LossOn1to50Knowledge.A1_800)
                .withLossOn1to200Knowledge(QuestionnaireAnswers.LossOn1to200Knowledge.A1_1800)
                .withBinaryInvestProfitKnowledge(QuestionnaireAnswers.BinaryInvestProfitKnowledge.PROFIT_100)
                .withBinaryInvestLossKnowledge(QuestionnaireAnswers.BinaryInvestLossKnowledge.LOSS_100)
                .withBinaryProbabilityKnowledge(QuestionnaireAnswers.BinaryProbabilityKnowledge.MONEY_45)
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(), 32d);
    }

    @Test(description = "ID:9133")
    public void calculationOfScore61Test(){
        pages().navigation().register();
        CustomerRO customer = CustomerRO.builder(WidgetsNamingStrategy.get()).build();
        pages().registerPage().register(customer);
        pages().navigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(PersonalInformation.builder()
                .withEmploymentStatus(QuestionnaireAnswers.EmploymentStatus.SELF_EMPLOYED)
                .withIndustry(QuestionnaireAnswers.Industry.COMPUTER)
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(QuestionnaireAnswers.HasTaxIdentificationNumber.YES)
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(QuestionnaireAnswers.IsUSReportable.NO)
                .withEducationLevel(QuestionnaireAnswers.EducationLevel.NONE)
                .withEducationField(QuestionnaireAnswers.EducationField.OTHER)
                .withEducationFieldOther(FREE_TEXT)
                .withPoliticalExposureStatus(QuestionnaireAnswers.IsPoliticallyExposed.NO)
                .withSourceOfFunds(QuestionnaireAnswers.SourceOfFunds.OTHER)
                .withSourceOfFundsOther(FREE_TEXT)
                .withAnnualIncome(QuestionnaireAnswers.AnnualIncome.INCOME_15KLESS)
                .withNetWealth(QuestionnaireAnswers.NetWealth.NET_WEALTH_UNDER_15K)
                .withExpectedDepositsPerYear(QuestionnaireAnswers.ExpectedDepositsPerYear.DEPOSITS_UNDER_10K)
                .withPurposeOfTrading(QuestionnaireAnswers.PurposeOfTrading.SPECULATIVE)
                .build()
        );
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
                .withSharesExperience(QuestionnaireAnswers.SharesExperience.NEVER)
                .withBinaryExperience(QuestionnaireAnswers.BinaryExperience.OCCASIONALLY)
                .withAverageYearlyBinaryVolume(QuestionnaireAnswers.AverageYearlyBinaryVolume.VOLUME_ABOVE_10K)
                .withForExExperience(QuestionnaireAnswers.ForExExperience.FREQUENTLY)
                .withAverageYearlyForExVolume(QuestionnaireAnswers.AverageYearlyForExVolume.VOLUME_ABOVE_500K)
                .withCommonLeverage(QuestionnaireAnswers.CommonForExLeverage.LEVERAGE_ABOVE_1TO500)
                .withFinancialWorkExperience(QuestionnaireAnswers.FinancialWorkExperience.WORKED)
                .withCfdBinaryKnowledge(QuestionnaireAnswers.CfdBinaryKnowledge.PHYSICALLY_DELIVERING)
                .withMainFactorKnowledge(QuestionnaireAnswers.MainFactorKnowledge.INTEREST_RATES)
                .withHowToCloseKnowledge(QuestionnaireAnswers.HowToCloseKnowledge.LONDON_STOCK)
                .withCfdLeverageKnowledge(QuestionnaireAnswers.CfdLeverageKnowledge.MAGNIFIES)
                .withStopLossKnowledge(QuestionnaireAnswers.StopLossKnowledge.BUY)
                .withRequiredMarginKnowledge(QuestionnaireAnswers.RequiredMarginKnowledge.MARGIN_1K)
                .withMarginLevelDropKnowledge(QuestionnaireAnswers.MarginLevelDropKnowledge.MARGIN_CALL)
                .withAutomaticStopKnowledge(QuestionnaireAnswers.AutomaticStopKnowledge.EQUITY_FALLS)
                .withLossOn1to50Knowledge(QuestionnaireAnswers.LossOn1to50Knowledge.A1_800)
                .withLossOn1to200Knowledge(QuestionnaireAnswers.LossOn1to200Knowledge.A2_1200)
                .withBinaryInvestProfitKnowledge(QuestionnaireAnswers.BinaryInvestProfitKnowledge.PROFIT_75)
                .withBinaryInvestLossKnowledge(QuestionnaireAnswers.BinaryInvestLossKnowledge.LOSS_50)
                .withBinaryProbabilityKnowledge(QuestionnaireAnswers.BinaryProbabilityKnowledge.MONEY_45)
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(), 61d);
    }

    @Test(description = "ID:9134")
    public void calculationOfScore70Test(){
        pages().navigation().register();
        CustomerRO customer = CustomerRO.builder(WidgetsNamingStrategy.get()).build();
        pages().registerPage().register(customer);
        pages().navigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(PersonalInformation.builder()
                .withEmploymentStatus(QuestionnaireAnswers.EmploymentStatus.SALARIED_EMPLOYEE)
                .withIndustry(QuestionnaireAnswers.Industry.OTHER)
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(QuestionnaireAnswers.HasTaxIdentificationNumber.YES)
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(QuestionnaireAnswers.IsUSReportable.NO)
                .withEducationLevel(QuestionnaireAnswers.EducationLevel.POST_GRADUATE)
                .withEducationField(QuestionnaireAnswers.EducationField.ACCOUNTING)
                //.withEducationFieldOther(null)
                .withPoliticalExposureStatus(QuestionnaireAnswers.IsPoliticallyExposed.NO)
                .withSourceOfFunds(QuestionnaireAnswers.SourceOfFunds.EMPLOYMENT)
                //.withSourceOfFundsOther(null)
                .withAnnualIncome(QuestionnaireAnswers.AnnualIncome.INCOME_OVER_100K)
                .withNetWealth(QuestionnaireAnswers.NetWealth.NET_WEALTH_OVER_300K)
                .withExpectedDepositsPerYear(QuestionnaireAnswers.ExpectedDepositsPerYear.DEPOSITS_OVER_50K)
                .withPurposeOfTrading(QuestionnaireAnswers.PurposeOfTrading.SPECULATIVE)
                .build()
        );
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
                .withSharesExperience(QuestionnaireAnswers.SharesExperience.NEVER)
                .withBinaryExperience(QuestionnaireAnswers.BinaryExperience.FREQUENTLY)
                .withAverageYearlyBinaryVolume(QuestionnaireAnswers.AverageYearlyBinaryVolume.VOLUME_ABOVE_10K)
                .withForExExperience(QuestionnaireAnswers.ForExExperience.FREQUENTLY)
                .withAverageYearlyForExVolume(QuestionnaireAnswers.AverageYearlyForExVolume.VOLUME_ABOVE_500K)
                .withCommonLeverage(QuestionnaireAnswers.CommonForExLeverage.LEVERAGE_ABOVE_1TO500)
                .withFinancialWorkExperience(QuestionnaireAnswers.FinancialWorkExperience.WORKED)
                .withCfdBinaryKnowledge(QuestionnaireAnswers.CfdBinaryKnowledge.PHYSICALLY_DELIVERING)
                .withMainFactorKnowledge(QuestionnaireAnswers.MainFactorKnowledge.INTEREST_RATES)
                .withHowToCloseKnowledge(QuestionnaireAnswers.HowToCloseKnowledge.LONDON_STOCK)
                .withCfdLeverageKnowledge(QuestionnaireAnswers.CfdLeverageKnowledge.MAGNIFIES)
                .withStopLossKnowledge(QuestionnaireAnswers.StopLossKnowledge.BUY)
                .withRequiredMarginKnowledge(QuestionnaireAnswers.RequiredMarginKnowledge.MARGIN_1K)
                .withMarginLevelDropKnowledge(QuestionnaireAnswers.MarginLevelDropKnowledge.MARGIN_CALL)
                .withAutomaticStopKnowledge(QuestionnaireAnswers.AutomaticStopKnowledge.EQUITY_FALLS)
                .withLossOn1to50Knowledge(QuestionnaireAnswers.LossOn1to50Knowledge.A1_800)
                .withLossOn1to200Knowledge(QuestionnaireAnswers.LossOn1to200Knowledge.A2_1200)
                .withBinaryInvestProfitKnowledge(QuestionnaireAnswers.BinaryInvestProfitKnowledge.PROFIT_75)
                .withBinaryInvestLossKnowledge(QuestionnaireAnswers.BinaryInvestLossKnowledge.LOSS_50)
                .withBinaryProbabilityKnowledge(QuestionnaireAnswers.BinaryProbabilityKnowledge.MONEY_45)
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(), 70d);
    }

    @Test(description = "ID:9135")
    public void calculationOfScore65Test(){
        pages().navigation().register();
        CustomerRO customer = CustomerRO.builder(WidgetsNamingStrategy.get()).build();
        pages().registerPage().register(customer);
        pages().navigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(PersonalInformation.builder()
                .withEmploymentStatus(QuestionnaireAnswers.EmploymentStatus.UNEMPLOYED)
                .withIndustry(QuestionnaireAnswers.Industry.ATTORNEYS)
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(QuestionnaireAnswers.HasTaxIdentificationNumber.YES)
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(QuestionnaireAnswers.IsUSReportable.NO)
                .withEducationLevel(QuestionnaireAnswers.EducationLevel.BACHELOR)
                .withEducationField(QuestionnaireAnswers.EducationField.LAW)
                //.withEducationFieldOther(null)
                .withPoliticalExposureStatus(QuestionnaireAnswers.IsPoliticallyExposed.NO)
                .withSourceOfFunds(QuestionnaireAnswers.SourceOfFunds.SAVINGS)
                //.withSourceOfFundsOther(null)
                .withAnnualIncome(QuestionnaireAnswers.AnnualIncome.INCOME_50K_100K)
                .withNetWealth(QuestionnaireAnswers.NetWealth.NET_WEALTH_150K_300K)
                .withExpectedDepositsPerYear(QuestionnaireAnswers.ExpectedDepositsPerYear.DEPOSITS_25K_50K)
                .withPurposeOfTrading(QuestionnaireAnswers.PurposeOfTrading.ADDITIONAL_INCOME)
                .build()
        );
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
                .withSharesExperience(QuestionnaireAnswers.SharesExperience.FREQUENTLY)
                .withBinaryExperience(QuestionnaireAnswers.BinaryExperience.FREQUENTLY)
                .withAverageYearlyBinaryVolume(QuestionnaireAnswers.AverageYearlyBinaryVolume.VOLUME_ABOVE_10K)
                .withForExExperience(QuestionnaireAnswers.ForExExperience.FREQUENTLY)
                .withAverageYearlyForExVolume(QuestionnaireAnswers.AverageYearlyForExVolume.VOLUME_ABOVE_500K)
                .withCommonLeverage(QuestionnaireAnswers.CommonForExLeverage.LEVERAGE_ABOVE_1TO500)
                .withFinancialWorkExperience(QuestionnaireAnswers.FinancialWorkExperience.BOTH)
                .withCfdBinaryKnowledge(QuestionnaireAnswers.CfdBinaryKnowledge.NON_RISKY)
                .withMainFactorKnowledge(QuestionnaireAnswers.MainFactorKnowledge.ANNOUNCEMENT)
                .withHowToCloseKnowledge(QuestionnaireAnswers.HowToCloseKnowledge.ONLY_PLATFORM)
                .withCfdLeverageKnowledge(QuestionnaireAnswers.CfdLeverageKnowledge.PROVIDES)
                .withStopLossKnowledge(QuestionnaireAnswers.StopLossKnowledge.MINIMIZE)
                .withRequiredMarginKnowledge(QuestionnaireAnswers.RequiredMarginKnowledge.MARGIN_10K)
                .withMarginLevelDropKnowledge(QuestionnaireAnswers.MarginLevelDropKnowledge.WARNING_CALL)
                .withAutomaticStopKnowledge(QuestionnaireAnswers.AutomaticStopKnowledge.EARNINGS)
                .withLossOn1to50Knowledge(QuestionnaireAnswers.LossOn1to50Knowledge.A1_800)
                .withLossOn1to200Knowledge(QuestionnaireAnswers.LossOn1to200Knowledge.A2_1200)
                .withBinaryInvestProfitKnowledge(QuestionnaireAnswers.BinaryInvestProfitKnowledge.PROFIT_75)
                .withBinaryInvestLossKnowledge(QuestionnaireAnswers.BinaryInvestLossKnowledge.LOSS_75)
                .withBinaryProbabilityKnowledge(QuestionnaireAnswers.BinaryProbabilityKnowledge.MONEY_35)
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(), 65d);
    }

    @Test(description = "ID:9136")
    public void calculationOfScore68Test(){
        pages().navigation().register();
        CustomerRO customer = CustomerRO.builder(WidgetsNamingStrategy.get()).build();
        pages().registerPage().register(customer);
        pages().navigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(PersonalInformation.builder()
                .withEmploymentStatus(QuestionnaireAnswers.EmploymentStatus.RETIRED)
                .withIndustry(QuestionnaireAnswers.Industry.FINANCE)
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(QuestionnaireAnswers.HasTaxIdentificationNumber.YES)
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(QuestionnaireAnswers.IsUSReportable.NO)
                .withEducationLevel(QuestionnaireAnswers.EducationLevel.BACHELOR)
                .withEducationField(QuestionnaireAnswers.EducationField.LAW)
                //.withEducationFieldOther(null)
                .withPoliticalExposureStatus(QuestionnaireAnswers.IsPoliticallyExposed.NO)
                .withSourceOfFunds(QuestionnaireAnswers.SourceOfFunds.SAVINGS)
                //.withSourceOfFundsOther(null)
                .withAnnualIncome(QuestionnaireAnswers.AnnualIncome.INCOME_50K_100K)
                .withNetWealth(QuestionnaireAnswers.NetWealth.NET_WEALTH_150K_300K)
                .withExpectedDepositsPerYear(QuestionnaireAnswers.ExpectedDepositsPerYear.DEPOSITS_25K_50K)
                .withPurposeOfTrading(QuestionnaireAnswers.PurposeOfTrading.ADDITIONAL_INCOME)
                .build()
        );
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
                        .withSharesExperience(QuestionnaireAnswers.SharesExperience.FREQUENTLY)
                        .withBinaryExperience(QuestionnaireAnswers.BinaryExperience.FREQUENTLY)
                        .withAverageYearlyBinaryVolume(QuestionnaireAnswers.AverageYearlyBinaryVolume.VOLUME_ABOVE_10K)
                        .withForExExperience(QuestionnaireAnswers.ForExExperience.FREQUENTLY)
                        .withAverageYearlyForExVolume(QuestionnaireAnswers.AverageYearlyForExVolume.VOLUME_ABOVE_500K)
                        .withCommonLeverage(QuestionnaireAnswers.CommonForExLeverage.LEVERAGE_ABOVE_1TO500)
                        .withFinancialWorkExperience(QuestionnaireAnswers.FinancialWorkExperience.BOTH)
                        .withCfdBinaryKnowledge(QuestionnaireAnswers.CfdBinaryKnowledge.NON_RISKY)
                        .withMainFactorKnowledge(QuestionnaireAnswers.MainFactorKnowledge.ANNOUNCEMENT)
                        .withHowToCloseKnowledge(QuestionnaireAnswers.HowToCloseKnowledge.ONLY_PLATFORM)
                        .withCfdLeverageKnowledge(QuestionnaireAnswers.CfdLeverageKnowledge.PROVIDES)
                        .withStopLossKnowledge(QuestionnaireAnswers.StopLossKnowledge.MINIMIZE)
                        .withRequiredMarginKnowledge(QuestionnaireAnswers.RequiredMarginKnowledge.MARGIN_10K)
                        .withMarginLevelDropKnowledge(QuestionnaireAnswers.MarginLevelDropKnowledge.WARNING_CALL)
                        .withAutomaticStopKnowledge(QuestionnaireAnswers.AutomaticStopKnowledge.EARNINGS)
                        .withLossOn1to50Knowledge(QuestionnaireAnswers.LossOn1to50Knowledge.A1_800)
                        .withLossOn1to200Knowledge(QuestionnaireAnswers.LossOn1to200Knowledge.A2_1200)
                        .withBinaryInvestProfitKnowledge(QuestionnaireAnswers.BinaryInvestProfitKnowledge.PROFIT_75)
                        .withBinaryInvestLossKnowledge(QuestionnaireAnswers.BinaryInvestLossKnowledge.LOSS_75)
                        .withBinaryProbabilityKnowledge(QuestionnaireAnswers.BinaryProbabilityKnowledge.MONEY_35)
                        .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(), 68d);
    }

    @Test(description = "ID:9137")
    public void calculationOfScore66Test(){
        pages().navigation().register();
        CustomerRO customer = CustomerRO.builder(WidgetsNamingStrategy.get()).build();
        pages().registerPage().register(customer);
        pages().navigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(PersonalInformation.builder()
                .withEmploymentStatus(QuestionnaireAnswers.EmploymentStatus.RETIRED)
                .withIndustry(QuestionnaireAnswers.Industry.FINANCE)
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(QuestionnaireAnswers.HasTaxIdentificationNumber.YES)
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(QuestionnaireAnswers.IsUSReportable.NO)
                .withEducationLevel(QuestionnaireAnswers.EducationLevel.BACHELOR)
                .withEducationField(QuestionnaireAnswers.EducationField.LAW)
                //.withEducationFieldOther(null)
                .withPoliticalExposureStatus(QuestionnaireAnswers.IsPoliticallyExposed.NO)
                .withSourceOfFunds(QuestionnaireAnswers.SourceOfFunds.SAVINGS)
                //.withSourceOfFundsOther(null)
                .withAnnualIncome(QuestionnaireAnswers.AnnualIncome.INCOME_50K_100K)
                .withNetWealth(QuestionnaireAnswers.NetWealth.NET_WEALTH_150K_300K)
                .withExpectedDepositsPerYear(QuestionnaireAnswers.ExpectedDepositsPerYear.DEPOSITS_25K_50K)
                .withPurposeOfTrading(QuestionnaireAnswers.PurposeOfTrading.ADDITIONAL_INCOME)
                .build()
        );
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
                        .withSharesExperience(QuestionnaireAnswers.SharesExperience.FREQUENTLY)
                        .withBinaryExperience(QuestionnaireAnswers.BinaryExperience.FREQUENTLY)
                        .withAverageYearlyBinaryVolume(QuestionnaireAnswers.AverageYearlyBinaryVolume.VOLUME_ABOVE_10K)
                        .withForExExperience(QuestionnaireAnswers.ForExExperience.FREQUENTLY)
                        .withAverageYearlyForExVolume(QuestionnaireAnswers.AverageYearlyForExVolume.VOLUME_ABOVE_500K)
                        .withCommonLeverage(QuestionnaireAnswers.CommonForExLeverage.LEVERAGE_ABOVE_1TO500)
                        .withFinancialWorkExperience(QuestionnaireAnswers.FinancialWorkExperience.BOTH)
                        .withCfdBinaryKnowledge(QuestionnaireAnswers.CfdBinaryKnowledge.NON_RISKY)
                        .withMainFactorKnowledge(QuestionnaireAnswers.MainFactorKnowledge.ANNOUNCEMENT)
                        .withHowToCloseKnowledge(QuestionnaireAnswers.HowToCloseKnowledge.ONLY_PLATFORM)
                        .withCfdLeverageKnowledge(QuestionnaireAnswers.CfdLeverageKnowledge.PROVIDES)
                        .withStopLossKnowledge(QuestionnaireAnswers.StopLossKnowledge.MINIMIZE)
                        .withRequiredMarginKnowledge(QuestionnaireAnswers.RequiredMarginKnowledge.MARGIN_10K)
                        .withMarginLevelDropKnowledge(QuestionnaireAnswers.MarginLevelDropKnowledge.WARNING_CALL)
                        .withAutomaticStopKnowledge(QuestionnaireAnswers.AutomaticStopKnowledge.EARNINGS)
                        .withLossOn1to50Knowledge(QuestionnaireAnswers.LossOn1to50Knowledge.A2_450)
                        .withLossOn1to200Knowledge(QuestionnaireAnswers.LossOn1to200Knowledge.A2_1200)
                        .withBinaryInvestProfitKnowledge(QuestionnaireAnswers.BinaryInvestProfitKnowledge.PROFIT_75)
                        .withBinaryInvestLossKnowledge(QuestionnaireAnswers.BinaryInvestLossKnowledge.LOSS_75)
                        .withBinaryProbabilityKnowledge(QuestionnaireAnswers.BinaryProbabilityKnowledge.MONEY_35)
                        .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(), 66d);
    }
}
