package com.betamedia.atom.testslibrary.option24.end2end.crm.newQuestionnaries;

import com.betamedia.common.enums.Country;
import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.testingtype.tp.TPCachedResourceEndToEndTest;
import org.testng.annotations.Test;


/**
 * Created by vadyms on 5/22/17.
 */
public class NewQuestionnaireTests extends TPCachedResourceEndToEndTest {

    private final static String FREE_TEXT = "Free text";
    private final static String COUNTRY = Country.TOGO.getDbValue().toUpperCase();
    private final static String TAX_ID = "1234567890";

    @Test(description = "ID:9129")
    public void calculationOfScore86Test(){
        pages().crmNavigation().register();
        CustomerRO customer = CustomerRO.builder().build();
        pages().register().register(customer);
//        pages().crmNavigation().login();
//        pages().crmLoginPage().login(customer.getEmail(),"123123");
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(PersonalInformation.builder()
                .withEmploymentStatus(Questions.EmploymentStatus.SALARIED_EMPLOYEE.get())
                .withIndustry(Questions.Industry.ACCOUNTING.get())
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(Questions.HasTaxIdentificationNumber.YES.get())
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(Questions.IsUSReportable.NO.get())
                .withEducationLevel(Questions.EducationLevel.POST_GRADUATE.get())
                .withEducationField(Questions.EducationField.ACCOUNTING.get())
                .withPoliticalExposureStatus(Questions.IsPoliticallyExposed.NO.get())
                .withSourceOfFunds(Questions.SourceOfFunds.EMPLOYMENT.get())
                .withAnnualIncome(Questions.AnnualIncome.INCOME_OVER_100K.get())
                .withNetWealth(Questions.NetWealth.NET_WEALTH_OVER_300K.get())
                .withExpectedDepositsPerYear(Questions.ExpectedDepositsPerYear.DEPOSITS_OVER_50K.get())
                .withPurposeOfTrading(Questions.PurposeOfTrading.SPECULATIVE.get())
                .build()
        );
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
                .withSharesExperience(Questions.SharesExperience.FREQUENTLY.get())
                .withBinaryExperience(Questions.BinaryExperience.FREQUENTLY.get())
                .withAverageYearlyBinaryVolume(Questions.AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.get())
                .withForExExperience(Questions.ForExExperience.FREQUENTLY.get())
                .withAverageYearlyForExVolume(Questions.AverageYearlyForExVolume.VOLUME_ABOVE_500K.get())
                .withCommonLeverage(Questions.CommonLeverageForExVolume.LEVERAGE_ABOVE_1TO500.get())
                .withFinancialWorkExperience(Questions.FinancialWorkExperience.WORKED.get())
                .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.SPECULATIVE.get())
                .withMainFactorKnowledge(Questions.MainFactorKnowledge.INTEREST_RATES.get())
                .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.ONLY_PLATFORM.get())
                .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.MAGNIFIES.get())
                .withStopLossKnowledge(Questions.StopLossKnowledge.MINIMIZE.get())
                .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_1K.get())
                .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.MARGIN_CALL.get())
                .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EQUITY_FALLS.get())
                .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A1_800.get())
                .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A2_1200.get())
                .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_60.get())
                .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_75.get())
                .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_35.get())
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(), 86d);
    }

    @Test(description = "ID:9130")
    public void calculationOfScore49Test(){
        pages().crmNavigation().register();
        CustomerRO customer = CustomerRO.builder().build();
        pages().register().register(customer);
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(PersonalInformation.builder()
                .withEmploymentStatus(Questions.EmploymentStatus.UNEMPLOYED.get())
                .withIndustry(Questions.Industry.FINANCE.get())
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(Questions.HasTaxIdentificationNumber.NO.get())
                .withSocialSecurityNumber(TAX_ID)
                .withUSReportabilityStatus(Questions.IsUSReportable.YES.get())
                .withEducationLevel(Questions.EducationLevel.BACHELOR.get())
                .withEducationField(Questions.EducationField.LAW.get())
                .withPoliticalExposureStatus(Questions.IsPoliticallyExposed.YES.get())
                .withPoliticalExposureComment(FREE_TEXT)
                .withSourceOfFunds(Questions.SourceOfFunds.SAVINGS.get())
                .withAnnualIncome(Questions.AnnualIncome.INCOME_50K_100K.get())
                .withNetWealth(Questions.NetWealth.NET_WEALTH_150K_300K.get())
                .withExpectedDepositsPerYear(Questions.ExpectedDepositsPerYear.DEPOSITS_25K_50K.get())
                .withPurposeOfTrading(Questions.PurposeOfTrading.ADDITIONAL_INCOME.get())
                .build()
        );
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
                .withSharesExperience(Questions.SharesExperience.REGULARLY.get())
                .withBinaryExperience(Questions.BinaryExperience.FREQUENTLY.get())
                .withAverageYearlyBinaryVolume(Questions.AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.get())
                .withForExExperience(Questions.ForExExperience.REGULARLY.get())
                .withAverageYearlyForExVolume(Questions.AverageYearlyForExVolume.VOLUME_ABOVE_500K.get())
                .withCommonLeverage(Questions.CommonLeverageForExVolume.LEVERAGE_ABOVE_1TO500.get())
                .withFinancialWorkExperience(Questions.FinancialWorkExperience.SEMINARS.get())
                .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.NON_RISKY.get())
                .withMainFactorKnowledge(Questions.MainFactorKnowledge.ANNOUNCEMENT.get())
                .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.LONDON_STOCK.get())
                .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.PROVIDES.get())
                .withStopLossKnowledge(Questions.StopLossKnowledge.BUY.get())
                .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_10K.get())
                .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.WARNING_CALL.get())
                .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EARNINGS.get())
                .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A2_450.get())
                .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A2_1200.get())
                .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_60.get())
                .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_75.get())
                .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_35.get())
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(), 49d);    }

    @Test(description = "ID:9131")
    public void calculationOfScore48Test(){
        pages().crmNavigation().register();
        CustomerRO customer = CustomerRO.builder().build();
        pages().register().register(customer);
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(PersonalInformation.builder()
                .withEmploymentStatus(Questions.EmploymentStatus.STUDENT.get())
                .withIndustry(Questions.Industry.FUNDS.get())
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(Questions.HasTaxIdentificationNumber.YES.get())
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(Questions.IsUSReportable.NO.get())
                .withEducationLevel(Questions.EducationLevel.SECONDARY.get())
                .withEducationField(Questions.EducationField.COMPUTER.get())
                .withPoliticalExposureStatus(Questions.IsPoliticallyExposed.NO.get())
                .withSourceOfFunds(Questions.SourceOfFunds.RETIREMENT.get())
                .withAnnualIncome(Questions.AnnualIncome.INCOME_25K_50K.get())
                .withNetWealth(Questions.NetWealth.NET_WEALTH_50K_150K.get())
                .withExpectedDepositsPerYear(Questions.ExpectedDepositsPerYear.DEPOSITS_15K_25K.get())
                .withPurposeOfTrading(Questions.PurposeOfTrading.HEDGING.get())
                .build()
        );
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
                .withSharesExperience(Questions.SharesExperience.OCCASIONALLY.get())
                .withBinaryExperience(Questions.BinaryExperience.REGULARLY.get())
                .withAverageYearlyBinaryVolume(Questions.AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.get())
                .withForExExperience(Questions.ForExExperience.OCCASIONALLY.get())
                .withAverageYearlyForExVolume(Questions.AverageYearlyForExVolume.VOLUME_ABOVE_500K.get())
                .withCommonLeverage(Questions.CommonLeverageForExVolume.LEVERAGE_ABOVE_1TO500.get())
                .withFinancialWorkExperience(Questions.FinancialWorkExperience.BOTH.get())
                .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.PHYSICALLY_DELIVERING.get())
                .withMainFactorKnowledge(Questions.MainFactorKnowledge.EMPLOYEE_LAYOFFS.get())
                .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.CANNOT_SELL.get())
                .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.NONE.get())
                .withStopLossKnowledge(Questions.StopLossKnowledge.TAKE.get())
                .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_100K.get())
                .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.MARGIN_UPGRADE.get())
                .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.OPEN_POSITION.get())
                .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A3_200.get())
                .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A3_1000.get())
                .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_100.get())
                .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_50.get())
                .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_45.get())
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(), 48d);
    }


    @Test(description = "ID:9132")
    public void calculationOfScore32Test(){
        pages().crmNavigation().register();
        CustomerRO customer = CustomerRO.builder().build();
        pages().register().register(customer);
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(PersonalInformation.builder()
                .withEmploymentStatus(Questions.EmploymentStatus.RETIRED.get())
                .withIndustry(Questions.Industry.ATTORNEYS.get())
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(Questions.HasTaxIdentificationNumber.YES.get())
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(Questions.IsUSReportable.NO.get())
                .withEducationLevel(Questions.EducationLevel.PRIMARY.get())
                .withEducationField(Questions.EducationField.MEDICINE.get())
                .withPoliticalExposureStatus(Questions.IsPoliticallyExposed.NO.get())
                .withSourceOfFunds(Questions.SourceOfFunds.INHERITANCE.get())
                .withAnnualIncome(Questions.AnnualIncome.INCOME_15K_25K.get())
                .withNetWealth(Questions.NetWealth.NET_WEALTH_15K_50K.get())
                .withExpectedDepositsPerYear(Questions.ExpectedDepositsPerYear.DEPOSITS_10K_15K.get())
                .withPurposeOfTrading(Questions.PurposeOfTrading.OTHER.get())
                .withPurposeOfTradingOther(FREE_TEXT)
                .build()
        );
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
                .withSharesExperience(Questions.SharesExperience.NEVER.get())
                .withBinaryExperience(Questions.BinaryExperience.NEVER.get())
                .withForExExperience(Questions.ForExExperience.NEVER.get())
                .withFinancialWorkExperience(Questions.FinancialWorkExperience.NEITHER.get())
                .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.SPECULATIVE.get())
                .withMainFactorKnowledge(Questions.MainFactorKnowledge.INTEREST_RATES.get())
                .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.ONLY_PLATFORM.get())
                .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.MAGNIFIES.get())
                .withStopLossKnowledge(Questions.StopLossKnowledge.BUY.get())
                .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_1K.get())
                .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.MARGIN_CALL.get())
                .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EQUITY_FALLS.get())
                .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A1_800.get())
                .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A1_1800.get())
                .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_100.get())
                .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_100.get())
                .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_45.get())
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(), 32d);
    }

    @Test(description = "ID:9133")
    public void calculationOfScore61Test(){
        pages().crmNavigation().register();
        CustomerRO customer = CustomerRO.builder().build();
        pages().register().register(customer);
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(PersonalInformation.builder()
                .withEmploymentStatus(Questions.EmploymentStatus.SELF_EMPLOYED.get())
                .withIndustry(Questions.Industry.COMPUTER.get())
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(Questions.HasTaxIdentificationNumber.YES.get())
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(Questions.IsUSReportable.NO.get())
                .withEducationLevel(Questions.EducationLevel.NONE.get())
                .withEducationField(Questions.EducationField.OTHER.get())
                .withEducationFieldOther(FREE_TEXT)
                .withPoliticalExposureStatus(Questions.IsPoliticallyExposed.NO.get())
                .withSourceOfFunds(Questions.SourceOfFunds.OTHER.get())
                .withSourceOfFundsOther(FREE_TEXT)
                .withAnnualIncome(Questions.AnnualIncome.INCOME_15KLESS.get())
                .withNetWealth(Questions.NetWealth.NET_WEALTH_UNDER_15K.get())
                .withExpectedDepositsPerYear(Questions.ExpectedDepositsPerYear.DEPOSITS_UNDER_10K.get())
                .withPurposeOfTrading(Questions.PurposeOfTrading.SPECULATIVE.get())
                .build()
        );
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
                .withSharesExperience(Questions.SharesExperience.NEVER.get())
                .withBinaryExperience(Questions.BinaryExperience.OCCASIONALLY.get())
                .withAverageYearlyBinaryVolume(Questions.AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.get())
                .withForExExperience(Questions.ForExExperience.FREQUENTLY.get())
                .withAverageYearlyForExVolume(Questions.AverageYearlyForExVolume.VOLUME_ABOVE_500K.get())
                .withCommonLeverage(Questions.CommonLeverageForExVolume.LEVERAGE_ABOVE_1TO500.get())
                .withFinancialWorkExperience(Questions.FinancialWorkExperience.WORKED.get())
                .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.PHYSICALLY_DELIVERING.get())
                .withMainFactorKnowledge(Questions.MainFactorKnowledge.INTEREST_RATES.get())
                .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.LONDON_STOCK.get())
                .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.MAGNIFIES.get())
                .withStopLossKnowledge(Questions.StopLossKnowledge.BUY.get())
                .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_1K.get())
                .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.MARGIN_CALL.get())
                .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EQUITY_FALLS.get())
                .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A1_800.get())
                .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A2_1200.get())
                .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_75.get())
                .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_50.get())
                .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_45.get())
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(), 61d);
    }

    @Test(description = "ID:9134")
    public void calculationOfScore70Test(){
        pages().crmNavigation().register();
        CustomerRO customer = CustomerRO.builder().build();
        pages().register().register(customer);
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(PersonalInformation.builder()
                .withEmploymentStatus(Questions.EmploymentStatus.SALARIED_EMPLOYEE.get())
                .withIndustry(Questions.Industry.OTHER.get())
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(Questions.HasTaxIdentificationNumber.YES.get())
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(Questions.IsUSReportable.NO.get())
                .withEducationLevel(Questions.EducationLevel.POST_GRADUATE.get())
                .withEducationField(Questions.EducationField.ACCOUNTING.get())
                //.withEducationFieldOther(null)
                .withPoliticalExposureStatus(Questions.IsPoliticallyExposed.NO.get())
                .withSourceOfFunds(Questions.SourceOfFunds.EMPLOYMENT.get())
                //.withSourceOfFundsOther(null)
                .withAnnualIncome(Questions.AnnualIncome.INCOME_OVER_100K.get())
                .withNetWealth(Questions.NetWealth.NET_WEALTH_OVER_300K.get())
                .withExpectedDepositsPerYear(Questions.ExpectedDepositsPerYear.DEPOSITS_OVER_50K.get())
                .withPurposeOfTrading(Questions.PurposeOfTrading.SPECULATIVE.get())
                .build()
        );
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
                .withSharesExperience(Questions.SharesExperience.NEVER.get())
                .withBinaryExperience(Questions.BinaryExperience.FREQUENTLY.get())
                .withAverageYearlyBinaryVolume(Questions.AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.get())
                .withForExExperience(Questions.ForExExperience.FREQUENTLY.get())
                .withAverageYearlyForExVolume(Questions.AverageYearlyForExVolume.VOLUME_ABOVE_500K.get())
                .withCommonLeverage(Questions.CommonLeverageForExVolume.LEVERAGE_ABOVE_1TO500.get())
                .withFinancialWorkExperience(Questions.FinancialWorkExperience.WORKED.get())
                .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.PHYSICALLY_DELIVERING.get())
                .withMainFactorKnowledge(Questions.MainFactorKnowledge.INTEREST_RATES.get())
                .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.LONDON_STOCK.get())
                .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.MAGNIFIES.get())
                .withStopLossKnowledge(Questions.StopLossKnowledge.BUY.get())
                .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_1K.get())
                .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.MARGIN_CALL.get())
                .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EQUITY_FALLS.get())
                .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A1_800.get())
                .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A2_1200.get())
                .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_75.get())
                .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_50.get())
                .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_45.get())
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(), 70d);
    }

    @Test(description = "ID:9135")
    public void calculationOfScore65Test(){
        pages().crmNavigation().register();
        CustomerRO customer = CustomerRO.builder().build();
        pages().register().register(customer);
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(PersonalInformation.builder()
                .withEmploymentStatus(Questions.EmploymentStatus.UNEMPLOYED.get())
                .withIndustry(Questions.Industry.ATTORNEYS.get())
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(Questions.HasTaxIdentificationNumber.YES.get())
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(Questions.IsUSReportable.NO.get())
                .withEducationLevel(Questions.EducationLevel.BACHELOR.get())
                .withEducationField(Questions.EducationField.LAW.get())
                //.withEducationFieldOther(null)
                .withPoliticalExposureStatus(Questions.IsPoliticallyExposed.NO.get())
                .withSourceOfFunds(Questions.SourceOfFunds.SAVINGS.get())
                //.withSourceOfFundsOther(null)
                .withAnnualIncome(Questions.AnnualIncome.INCOME_50K_100K.get())
                .withNetWealth(Questions.NetWealth.NET_WEALTH_150K_300K.get())
                .withExpectedDepositsPerYear(Questions.ExpectedDepositsPerYear.DEPOSITS_25K_50K.get())
                .withPurposeOfTrading(Questions.PurposeOfTrading.ADDITIONAL_INCOME.get())
                .build()
        );
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
                .withSharesExperience(Questions.SharesExperience.FREQUENTLY.get())
                .withBinaryExperience(Questions.BinaryExperience.FREQUENTLY.get())
                .withAverageYearlyBinaryVolume(Questions.AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.get())
                .withForExExperience(Questions.ForExExperience.FREQUENTLY.get())
                .withAverageYearlyForExVolume(Questions.AverageYearlyForExVolume.VOLUME_ABOVE_500K.get())
                .withCommonLeverage(Questions.CommonLeverageForExVolume.LEVERAGE_ABOVE_1TO500.get())
                .withFinancialWorkExperience(Questions.FinancialWorkExperience.BOTH.get())
                .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.NON_RISKY.get())
                .withMainFactorKnowledge(Questions.MainFactorKnowledge.ANNOUNCEMENT.get())
                .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.ONLY_PLATFORM.get())
                .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.PROVIDES.get())
                .withStopLossKnowledge(Questions.StopLossKnowledge.MINIMIZE.get())
                .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_10K.get())
                .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.WARNING_CALL.get())
                .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EARNINGS.get())
                .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A1_800.get())
                .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A2_1200.get())
                .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_75.get())
                .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_75.get())
                .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_35.get())
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(), 65d);
    }

    @Test(description = "ID:9136")
    public void calculationOfScore68Test(){
        pages().crmNavigation().register();
        CustomerRO customer = CustomerRO.builder().build();
        pages().register().register(customer);
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(PersonalInformation.builder()
                .withEmploymentStatus(Questions.EmploymentStatus.RETIRED.get())
                .withIndustry(Questions.Industry.FINANCE.get())
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(Questions.HasTaxIdentificationNumber.YES.get())
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(Questions.IsUSReportable.NO.get())
                .withEducationLevel(Questions.EducationLevel.BACHELOR.get())
                .withEducationField(Questions.EducationField.LAW.get())
                //.withEducationFieldOther(null)
                .withPoliticalExposureStatus(Questions.IsPoliticallyExposed.NO.get())
                .withSourceOfFunds(Questions.SourceOfFunds.SAVINGS.get())
                //.withSourceOfFundsOther(null)
                .withAnnualIncome(Questions.AnnualIncome.INCOME_50K_100K.get())
                .withNetWealth(Questions.NetWealth.NET_WEALTH_150K_300K.get())
                .withExpectedDepositsPerYear(Questions.ExpectedDepositsPerYear.DEPOSITS_25K_50K.get())
                .withPurposeOfTrading(Questions.PurposeOfTrading.ADDITIONAL_INCOME.get())
                .build()
        );
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
                        .withSharesExperience(Questions.SharesExperience.FREQUENTLY.get())
                        .withBinaryExperience(Questions.BinaryExperience.FREQUENTLY.get())
                        .withAverageYearlyBinaryVolume(Questions.AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.get())
                        .withForExExperience(Questions.ForExExperience.FREQUENTLY.get())
                        .withAverageYearlyForExVolume(Questions.AverageYearlyForExVolume.VOLUME_ABOVE_500K.get())
                        .withCommonLeverage(Questions.CommonLeverageForExVolume.LEVERAGE_ABOVE_1TO500.get())
                        .withFinancialWorkExperience(Questions.FinancialWorkExperience.BOTH.get())
                        .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.NON_RISKY.get())
                        .withMainFactorKnowledge(Questions.MainFactorKnowledge.ANNOUNCEMENT.get())
                        .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.ONLY_PLATFORM.get())
                        .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.PROVIDES.get())
                        .withStopLossKnowledge(Questions.StopLossKnowledge.MINIMIZE.get())
                        .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_10K.get())
                        .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.WARNING_CALL.get())
                        .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EARNINGS.get())
                        .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A1_800.get())
                        .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A2_1200.get())
                        .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_75.get())
                        .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_75.get())
                        .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_35.get())
                        .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(), 68d);
    }

    @Test(description = "ID:9137")
    public void calculationOfScore66Test(){
        pages().crmNavigation().register();
        CustomerRO customer = CustomerRO.builder().build();
        pages().register().register(customer);
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(PersonalInformation.builder()
                .withEmploymentStatus(Questions.EmploymentStatus.RETIRED.get())
                .withIndustry(Questions.Industry.FINANCE.get())
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(Questions.HasTaxIdentificationNumber.YES.get())
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(Questions.IsUSReportable.NO.get())
                .withEducationLevel(Questions.EducationLevel.BACHELOR.get())
                .withEducationField(Questions.EducationField.LAW.get())
                //.withEducationFieldOther(null)
                .withPoliticalExposureStatus(Questions.IsPoliticallyExposed.NO.get())
                .withSourceOfFunds(Questions.SourceOfFunds.SAVINGS.get())
                //.withSourceOfFundsOther(null)
                .withAnnualIncome(Questions.AnnualIncome.INCOME_50K_100K.get())
                .withNetWealth(Questions.NetWealth.NET_WEALTH_150K_300K.get())
                .withExpectedDepositsPerYear(Questions.ExpectedDepositsPerYear.DEPOSITS_25K_50K.get())
                .withPurposeOfTrading(Questions.PurposeOfTrading.ADDITIONAL_INCOME.get())
                .build()
        );
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
                        .withSharesExperience(Questions.SharesExperience.FREQUENTLY.get())
                        .withBinaryExperience(Questions.BinaryExperience.FREQUENTLY.get())
                        .withAverageYearlyBinaryVolume(Questions.AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.get())
                        .withForExExperience(Questions.ForExExperience.FREQUENTLY.get())
                        .withAverageYearlyForExVolume(Questions.AverageYearlyForExVolume.VOLUME_ABOVE_500K.get())
                        .withCommonLeverage(Questions.CommonLeverageForExVolume.LEVERAGE_ABOVE_1TO500.get())
                        .withFinancialWorkExperience(Questions.FinancialWorkExperience.BOTH.get())
                        .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.NON_RISKY.get())
                        .withMainFactorKnowledge(Questions.MainFactorKnowledge.ANNOUNCEMENT.get())
                        .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.ONLY_PLATFORM.get())
                        .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.PROVIDES.get())
                        .withStopLossKnowledge(Questions.StopLossKnowledge.MINIMIZE.get())
                        .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_10K.get())
                        .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.WARNING_CALL.get())
                        .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EARNINGS.get())
                        .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A2_450.get())
                        .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A2_1200.get())
                        .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_75.get())
                        .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_75.get())
                        .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_35.get())
                        .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(), 66d);
    }
}
