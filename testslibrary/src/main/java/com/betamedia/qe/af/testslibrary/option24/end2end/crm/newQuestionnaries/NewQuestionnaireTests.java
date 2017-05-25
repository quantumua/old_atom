package com.betamedia.qe.af.testslibrary.option24.end2end.crm.newQuestionnaries;

import com.betamedia.common.enums.Country;
import com.betamedia.qe.af.core.api.crm.form.builders.PersonalInformationBuilder;
import com.betamedia.qe.af.core.api.crm.form.builders.TradingExperienceInfoBuilder;
import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.testingtype.tp.TPResourceAwareEndToEndTest;
import org.testng.annotations.Test;
import static com.betamedia.qe.af.testslibrary.option24.end2end.crm.newQuestionnaries.Questions.*;


/**
 * Created by vadyms on 5/22/17.
 */
public class NewQuestionnaireTests extends TPResourceAwareEndToEndTest{

    private final static String FREE_TEXT = "Free text";
    private final static String COUNTRY = Country.TOGO.getDbValue().toUpperCase();
    private final static String TAX_ID = "1234567890";

    @Test(description = "ID:9129")
    public void calculationOfScore86Test(){
        pages().crmNavigation().register();
        CustomerBuilder.CustomerRO customer = new CustomerBuilder().createCustomerRO();
        pages().register().register(customer);
//        pages().crmNavigation().login();
//        pages().crmLoginPage().login(customer.getEmail(),"123123");
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(new PersonalInformationBuilder()
                .withEmploymentStatus(EmploymentStatus.SALARIED_EMPLOYEE.get())
                .withIndustry(Industry.ACCOUNTING.get())
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.YES.get())
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(IsUSReportable.NO.get())
                .withEducationLevel(EducationLevel.POST_GRADUATE.get())
                .withEducationField(EducationField.ACCOUNTING.get())
                .withPoliticalExposureStatus(IsPoliticallyExposed.NO.get())
                .withSourceOfFunds(SourceOfFunds.EMPLOYMENT.get())
                .withAnnualIncome(AnnualIncome.INCOME_OVER_100K.get())
                .withNetWealth(NetWealth.NET_WEALTH_OVER_300K.get())
                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_OVER_50K.get())
                .withPurposeOfTrading(PurposeOfTrading.SPECULATIVE.get())
                .build()
        );
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(new TradingExperienceInfoBuilder()
                .withSharesExperience(SharesExperience.FREQUENTLY.get())
                .withBinaryExperience(BinaryExperience.FREQUENTLY.get())
                .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.get())
                .withForExExperience(ForExExperience.FREQUENTLY.get())
                .withAverageYearlyForExVolume(AverageYearlyForExVolume.VOLUME_ABOVE_500K.get())
                .withCommonLeverage(CommonLeverageForExVolume.LEVERAGE_ABOVE_1TO500.get())
                .withFinancialWorkExperience(FinancialWorkExperience.WORKED.get())
                .withCfdBinaryKnowledge(CfdBinaryKnowledge.SPECULATIVE.get())
                .withMainFactorKnowledge(MainFactorKnowledge.INTEREST_RATES.get())
                .withHowToCloseKnowledge(HowToCloseKnowledge.ONLY_PLATFORM.get())
                .withCfdLeverageKnowledge(CfdLeverageKnowledge.MAGNIFIES.get())
                .withStopLossKnowledge(StopLossKnowledge.MINIMIZE.get())
                .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K.get())
                .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.MARGIN_CALL.get())
                .withAutomaticStopKnowledge(AutomaticStopKnowledge.EQUITY_FALLS.get())
                .withLossOn1to50Knowledge(LossOn1to50Knowledge.A1_800.get())
                .withLossOn1to200Knowledge(LossOn1to200Knowledge.A2_1200.get())
                .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_60.get())
                .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_75.get())
                .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_35.get())
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(),Double.valueOf(86));
    }

    @Test(description = "ID:9130")
    public void calculationOfScore49Test(){
        pages().crmNavigation().register();
        CustomerBuilder.CustomerRO customer = new CustomerBuilder().createCustomerRO();
        pages().register().register(customer);
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(new PersonalInformationBuilder()
                .withEmploymentStatus(EmploymentStatus.UNEMPLOYED.get())
                .withIndustry(Industry.FINANCE.get())
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.NO.get())
                .withSocialSecurityNumber(TAX_ID)
                .withUSReportabilityStatus(IsUSReportable.YES.get())
                .withEducationLevel(EducationLevel.BACHELOR.get())
                .withEducationField(EducationField.LAW.get())
                .withPoliticalExposureStatus(IsPoliticallyExposed.YES.get())
                .withPoliticalExposureComment(FREE_TEXT)
                .withSourceOfFunds(SourceOfFunds.SAVINGS.get())
                .withAnnualIncome(AnnualIncome.INCOME_50K_100K.get())
                .withNetWealth(NetWealth.NET_WEALTH_150K_300K.get())
                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_25K_50K.get())
                .withPurposeOfTrading(PurposeOfTrading.ADDITIONAL_INCOME.get())
                .build()
        );
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(new TradingExperienceInfoBuilder()
                .withSharesExperience(SharesExperience.REGULARLY.get())
                .withBinaryExperience(BinaryExperience.FREQUENTLY.get())
                .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.get())
                .withForExExperience(ForExExperience.REGULARLY.get())
                .withAverageYearlyForExVolume(AverageYearlyForExVolume.VOLUME_ABOVE_500K.get())
                .withCommonLeverage(CommonLeverageForExVolume.LEVERAGE_ABOVE_1TO500.get())
                .withFinancialWorkExperience(FinancialWorkExperience.SEMINARS.get())
                .withCfdBinaryKnowledge(CfdBinaryKnowledge.NON_RISKY.get())
                .withMainFactorKnowledge(MainFactorKnowledge.ANNOUNCEMENT.get())
                .withHowToCloseKnowledge(HowToCloseKnowledge.LONDON_STOCK.get())
                .withCfdLeverageKnowledge(CfdLeverageKnowledge.PROVIDES.get())
                .withStopLossKnowledge(StopLossKnowledge.BUY.get())
                .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_10K.get())
                .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.WARNING_CALL.get())
                .withAutomaticStopKnowledge(AutomaticStopKnowledge.EARNINGS.get())
                .withLossOn1to50Knowledge(LossOn1to50Knowledge.A2_450.get())
                .withLossOn1to200Knowledge(LossOn1to200Knowledge.A2_1200.get())
                .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_60.get())
                .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_75.get())
                .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_35.get())
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(),Double.valueOf(49));    }

    @Test(description = "ID:9131")
    public void calculationOfScore48Test(){
        pages().crmNavigation().register();
        CustomerBuilder.CustomerRO customer = new CustomerBuilder().createCustomerRO();
        pages().register().register(customer);
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(new PersonalInformationBuilder()
                .withEmploymentStatus(EmploymentStatus.STUDENT.get())
                .withIndustry(Industry.FUNDS.get())
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.YES.get())
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(IsUSReportable.NO.get())
                .withEducationLevel(EducationLevel.SECONDARY.get())
                .withEducationField(EducationField.COMPUTER.get())
                .withPoliticalExposureStatus(IsPoliticallyExposed.NO.get())
                .withSourceOfFunds(SourceOfFunds.RETIREMENT.get())
                .withAnnualIncome(AnnualIncome.INCOME_25K_50K.get())
                .withNetWealth(NetWealth.NET_WEALTH_50K_150K.get())
                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_15K_25K.get())
                .withPurposeOfTrading(PurposeOfTrading.HEDGING.get())
                .build()
        );
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(new TradingExperienceInfoBuilder()
                .withSharesExperience(SharesExperience.OCCASIONALLY.get())
                .withBinaryExperience(BinaryExperience.REGULARLY.get())
                .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.get())
                .withForExExperience(ForExExperience.OCCASIONALLY.get())
                .withAverageYearlyForExVolume(AverageYearlyForExVolume.VOLUME_ABOVE_500K.get())
                .withCommonLeverage(CommonLeverageForExVolume.LEVERAGE_ABOVE_1TO500.get())
                .withFinancialWorkExperience(FinancialWorkExperience.BOTH.get())
                .withCfdBinaryKnowledge(CfdBinaryKnowledge.PHYSICALLY_DELIVERING.get())
                .withMainFactorKnowledge(MainFactorKnowledge.EMPLOYEE_LAYOFFS.get())
                .withHowToCloseKnowledge(HowToCloseKnowledge.CANNOT_SELL.get())
                .withCfdLeverageKnowledge(CfdLeverageKnowledge.NONE.get())
                .withStopLossKnowledge(StopLossKnowledge.TAKE.get())
                .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_100K.get())
                .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.MARGIN_UPGRADE.get())
                .withAutomaticStopKnowledge(AutomaticStopKnowledge.OPEN_POSITION.get())
                .withLossOn1to50Knowledge(LossOn1to50Knowledge.A3_200.get())
                .withLossOn1to200Knowledge(LossOn1to200Knowledge.A3_1000.get())
                .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_100.get())
                .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_50.get())
                .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_45.get())
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(),Double.valueOf(48));
    }


    @Test(description = "ID:9132")
    public void calculationOfScore681Test(){
        pages().crmNavigation().register();
        CustomerBuilder.CustomerRO customer = new CustomerBuilder().createCustomerRO();
        pages().register().register(customer);
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(new PersonalInformationBuilder()
                .withEmploymentStatus(EmploymentStatus.RETIRED.get())
                .withIndustry(Industry.ATTORNEYS.get())
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.YES.get())
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(IsUSReportable.NO.get())
                .withEducationLevel(EducationLevel.PRIMARY.get())
                .withEducationField(EducationField.MEDICINE.get())
                .withPoliticalExposureStatus(IsPoliticallyExposed.NO.get())
                .withSourceOfFunds(SourceOfFunds.INHERITANCE.get())
                .withAnnualIncome(AnnualIncome.INCOME_15K_25K.get())
                .withNetWealth(NetWealth.NET_WEALTH_15K_50K.get())
                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_10K_15K.get())
                .withPurposeOfTrading(PurposeOfTrading.OTHER.get())
                .withPurposeOfTradingOther(FREE_TEXT)
                .build()
        );
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(new TradingExperienceInfoBuilder()
                .withSharesExperience(SharesExperience.NEVER.get())
                .withBinaryExperience(BinaryExperience.NEVER.get())
                .withForExExperience(ForExExperience.NEVER.get())
                .withFinancialWorkExperience(FinancialWorkExperience.NEITHER.get())
                .withCfdBinaryKnowledge(CfdBinaryKnowledge.SPECULATIVE.get())
                .withMainFactorKnowledge(MainFactorKnowledge.INTEREST_RATES.get())
                .withHowToCloseKnowledge(HowToCloseKnowledge.ONLY_PLATFORM.get())
                .withCfdLeverageKnowledge(CfdLeverageKnowledge.MAGNIFIES.get())
                .withStopLossKnowledge(StopLossKnowledge.BUY.get())
                .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K.get())
                .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.MARGIN_CALL.get())
                .withAutomaticStopKnowledge(AutomaticStopKnowledge.EQUITY_FALLS.get())
                .withLossOn1to50Knowledge(LossOn1to50Knowledge.A1_800.get())
                .withLossOn1to200Knowledge(LossOn1to200Knowledge.A1_1800.get())
                .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_100.get())
                .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_100.get())
                .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_45.get())
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(),Double.valueOf(68));
    }

    @Test(description = "ID:9133")
    public void calculationOfScore61Test(){
        pages().crmNavigation().register();
        CustomerBuilder.CustomerRO customer = new CustomerBuilder().createCustomerRO();
        pages().register().register(customer);
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(new PersonalInformationBuilder()
                .withEmploymentStatus(EmploymentStatus.SELF_EMPLOYED.get())
                .withIndustry(Industry.COMPUTER.get())
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.YES.get())
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(IsUSReportable.NO.get())
                .withEducationLevel(EducationLevel.NONE.get())
                .withEducationField(EducationField.OTHER.get())
                .withEducationFieldOther(FREE_TEXT)
                .withPoliticalExposureStatus(IsPoliticallyExposed.NO.get())
                .withSourceOfFunds(SourceOfFunds.OTHER.get())
                .withSourceOfFundsOther(FREE_TEXT)
                .withAnnualIncome(AnnualIncome.INCOME_15KLESS.get())
                .withNetWealth(NetWealth.NET_WEALTH_UNDER_15K.get())
                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_UNDER_10K.get())
                .withPurposeOfTrading(PurposeOfTrading.SPECULATIVE.get())
                .build()
        );
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(new TradingExperienceInfoBuilder()
                .withSharesExperience(SharesExperience.NEVER.get())
                .withBinaryExperience(BinaryExperience.OCCASIONALLY.get())
                .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.get())
                .withForExExperience(ForExExperience.FREQUENTLY.get())
                .withAverageYearlyForExVolume(AverageYearlyForExVolume.VOLUME_ABOVE_500K.get())
                .withCommonLeverage(CommonLeverageForExVolume.LEVERAGE_ABOVE_1TO500.get())
                .withFinancialWorkExperience(FinancialWorkExperience.WORKED.get())
                .withCfdBinaryKnowledge(CfdBinaryKnowledge.PHYSICALLY_DELIVERING.get())
                .withMainFactorKnowledge(MainFactorKnowledge.INTEREST_RATES.get())
                .withHowToCloseKnowledge(HowToCloseKnowledge.LONDON_STOCK.get())
                .withCfdLeverageKnowledge(CfdLeverageKnowledge.MAGNIFIES.get())
                .withStopLossKnowledge(StopLossKnowledge.BUY.get())
                .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K.get())
                .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.MARGIN_CALL.get())
                .withAutomaticStopKnowledge(AutomaticStopKnowledge.EQUITY_FALLS.get())
                .withLossOn1to50Knowledge(LossOn1to50Knowledge.A1_800.get())
                .withLossOn1to200Knowledge(LossOn1to200Knowledge.A2_1200.get())
                .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_75.get())
                .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_50.get())
                .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_45.get())
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(),Double.valueOf(61));
    }

    @Test(description = "ID:9134")
    public void calculationOfScore70Test(){
        pages().crmNavigation().register();
        CustomerBuilder.CustomerRO customer = new CustomerBuilder().createCustomerRO();
        pages().register().register(customer);
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(new PersonalInformationBuilder()
                .withEmploymentStatus(EmploymentStatus.SALARIED_EMPLOYEE.get())
                .withIndustry(Industry.OTHER.get())
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.YES.get())
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(IsUSReportable.NO.get())
                .withEducationLevel(EducationLevel.POST_GRADUATE.get())
                .withEducationField(EducationField.ACCOUNTING.get())
                //.withEducationFieldOther(null)
                .withPoliticalExposureStatus(IsPoliticallyExposed.NO.get())
                .withSourceOfFunds(SourceOfFunds.EMPLOYMENT.get())
                //.withSourceOfFundsOther(null)
                .withAnnualIncome(AnnualIncome.INCOME_OVER_100K.get())
                .withNetWealth(NetWealth.NET_WEALTH_OVER_300K.get())
                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_OVER_50K.get())
                .withPurposeOfTrading(PurposeOfTrading.SPECULATIVE.get())
                .build()
        );
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(new TradingExperienceInfoBuilder()
                .withSharesExperience(SharesExperience.NEVER.get())
                .withBinaryExperience(BinaryExperience.FREQUENTLY.get())
                .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.get())
                .withForExExperience(ForExExperience.FREQUENTLY.get())
                .withAverageYearlyForExVolume(AverageYearlyForExVolume.VOLUME_ABOVE_500K.get())
                .withCommonLeverage(CommonLeverageForExVolume.LEVERAGE_ABOVE_1TO500.get())
                .withFinancialWorkExperience(FinancialWorkExperience.WORKED.get())
                .withCfdBinaryKnowledge(CfdBinaryKnowledge.PHYSICALLY_DELIVERING.get())
                .withMainFactorKnowledge(MainFactorKnowledge.INTEREST_RATES.get())
                .withHowToCloseKnowledge(HowToCloseKnowledge.LONDON_STOCK.get())
                .withCfdLeverageKnowledge(CfdLeverageKnowledge.MAGNIFIES.get())
                .withStopLossKnowledge(StopLossKnowledge.BUY.get())
                .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K.get())
                .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.MARGIN_CALL.get())
                .withAutomaticStopKnowledge(AutomaticStopKnowledge.EQUITY_FALLS.get())
                .withLossOn1to50Knowledge(LossOn1to50Knowledge.A1_800.get())
                .withLossOn1to200Knowledge(LossOn1to200Knowledge.A2_1200.get())
                .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_75.get())
                .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_50.get())
                .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_45.get())
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(),Double.valueOf(70));
    }

    @Test(description = "ID:9135")
    public void calculationOfScore65Test(){
        pages().crmNavigation().register();
        CustomerBuilder.CustomerRO customer = new CustomerBuilder().createCustomerRO();
        pages().register().register(customer);
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(new PersonalInformationBuilder()
                .withEmploymentStatus(EmploymentStatus.UNEMPLOYED.get())
                .withIndustry(Industry.ATTORNEYS.get())
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.YES.get())
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(IsUSReportable.NO.get())
                .withEducationLevel(EducationLevel.BACHELOR.get())
                .withEducationField(EducationField.LAW.get())
                //.withEducationFieldOther(null)
                .withPoliticalExposureStatus(IsPoliticallyExposed.NO.get())
                .withSourceOfFunds(SourceOfFunds.SAVINGS.get())
                //.withSourceOfFundsOther(null)
                .withAnnualIncome(AnnualIncome.INCOME_50K_100K.get())
                .withNetWealth(NetWealth.NET_WEALTH_150K_300K.get())
                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_25K_50K.get())
                .withPurposeOfTrading(PurposeOfTrading.ADDITIONAL_INCOME.get())
                .build()
        );
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(new TradingExperienceInfoBuilder()
                .withSharesExperience(SharesExperience.FREQUENTLY.get())
                .withBinaryExperience(BinaryExperience.FREQUENTLY.get())
                .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.get())
                .withForExExperience(ForExExperience.FREQUENTLY.get())
                .withAverageYearlyForExVolume(AverageYearlyForExVolume.VOLUME_ABOVE_500K.get())
                .withCommonLeverage(CommonLeverageForExVolume.LEVERAGE_ABOVE_1TO500.get())
                .withFinancialWorkExperience(FinancialWorkExperience.BOTH.get())
                .withCfdBinaryKnowledge(CfdBinaryKnowledge.NON_RISKY.get())
                .withMainFactorKnowledge(MainFactorKnowledge.ANNOUNCEMENT.get())
                .withHowToCloseKnowledge(HowToCloseKnowledge.ONLY_PLATFORM.get())
                .withCfdLeverageKnowledge(CfdLeverageKnowledge.PROVIDES.get())
                .withStopLossKnowledge(StopLossKnowledge.MINIMIZE.get())
                .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_10K.get())
                .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.WARNING_CALL.get())
                .withAutomaticStopKnowledge(AutomaticStopKnowledge.EARNINGS.get())
                .withLossOn1to50Knowledge(LossOn1to50Knowledge.A1_800.get())
                .withLossOn1to200Knowledge(LossOn1to200Knowledge.A2_1200.get())
                .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_75.get())
                .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_75.get())
                .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_35.get())
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(),Double.valueOf(65));
    }

    @Test(description = "ID:9136")
    public void calculationOfScore68Test(){
        pages().crmNavigation().register();
        CustomerBuilder.CustomerRO customer = new CustomerBuilder().createCustomerRO();
        pages().register().register(customer);
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(new PersonalInformationBuilder()
                .withEmploymentStatus(EmploymentStatus.RETIRED.get())
                .withIndustry(Industry.FINANCE.get())
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.YES.get())
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(IsUSReportable.NO.get())
                .withEducationLevel(EducationLevel.BACHELOR.get())
                .withEducationField(EducationField.LAW.get())
                //.withEducationFieldOther(null)
                .withPoliticalExposureStatus(IsPoliticallyExposed.NO.get())
                .withSourceOfFunds(SourceOfFunds.SAVINGS.get())
                //.withSourceOfFundsOther(null)
                .withAnnualIncome(AnnualIncome.INCOME_50K_100K.get())
                .withNetWealth(NetWealth.NET_WEALTH_150K_300K.get())
                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_25K_50K.get())
                .withPurposeOfTrading(PurposeOfTrading.ADDITIONAL_INCOME.get())
                .build()
        );
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(new TradingExperienceInfoBuilder()
                        .withSharesExperience(SharesExperience.FREQUENTLY.get())
                        .withBinaryExperience(BinaryExperience.FREQUENTLY.get())
                        .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.get())
                        .withForExExperience(ForExExperience.FREQUENTLY.get())
                        .withAverageYearlyForExVolume(AverageYearlyForExVolume.VOLUME_ABOVE_500K.get())
                        .withCommonLeverage(CommonLeverageForExVolume.LEVERAGE_ABOVE_1TO500.get())
                        .withFinancialWorkExperience(FinancialWorkExperience.BOTH.get())
                        .withCfdBinaryKnowledge(CfdBinaryKnowledge.NON_RISKY.get())
                        .withMainFactorKnowledge(MainFactorKnowledge.ANNOUNCEMENT.get())
                        .withHowToCloseKnowledge(HowToCloseKnowledge.ONLY_PLATFORM.get())
                        .withCfdLeverageKnowledge(CfdLeverageKnowledge.PROVIDES.get())
                        .withStopLossKnowledge(StopLossKnowledge.MINIMIZE.get())
                        .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_10K.get())
                        .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.WARNING_CALL.get())
                        .withAutomaticStopKnowledge(AutomaticStopKnowledge.EARNINGS.get())
                        .withLossOn1to50Knowledge(LossOn1to50Knowledge.A1_800.get())
                        .withLossOn1to200Knowledge(LossOn1to200Knowledge.A2_1200.get())
                        .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_75.get())
                        .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_75.get())
                        .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_35.get())
                        .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(),Double.valueOf(68));
    }

    @Test(description = "ID:9137")
    public void calculationOfScore66Test(){
        pages().crmNavigation().register();
        CustomerBuilder.CustomerRO customer = new CustomerBuilder().createCustomerRO();
        pages().register().register(customer);
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(new PersonalInformationBuilder()
                .withEmploymentStatus(EmploymentStatus.RETIRED.get())
                .withIndustry(Industry.FINANCE.get())
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.YES.get())
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(IsUSReportable.NO.get())
                .withEducationLevel(EducationLevel.BACHELOR.get())
                .withEducationField(EducationField.LAW.get())
                //.withEducationFieldOther(null)
                .withPoliticalExposureStatus(IsPoliticallyExposed.NO.get())
                .withSourceOfFunds(SourceOfFunds.SAVINGS.get())
                //.withSourceOfFundsOther(null)
                .withAnnualIncome(AnnualIncome.INCOME_50K_100K.get())
                .withNetWealth(NetWealth.NET_WEALTH_150K_300K.get())
                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_25K_50K.get())
                .withPurposeOfTrading(PurposeOfTrading.ADDITIONAL_INCOME.get())
                .build()
        );
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(new TradingExperienceInfoBuilder()
                        .withSharesExperience(SharesExperience.FREQUENTLY.get())
                        .withBinaryExperience(BinaryExperience.FREQUENTLY.get())
                        .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.get())
                        .withForExExperience(ForExExperience.FREQUENTLY.get())
                        .withAverageYearlyForExVolume(AverageYearlyForExVolume.VOLUME_ABOVE_500K.get())
                        .withCommonLeverage(CommonLeverageForExVolume.LEVERAGE_ABOVE_1TO500.get())
                        .withFinancialWorkExperience(FinancialWorkExperience.BOTH.get())
                        .withCfdBinaryKnowledge(CfdBinaryKnowledge.NON_RISKY.get())
                        .withMainFactorKnowledge(MainFactorKnowledge.ANNOUNCEMENT.get())
                        .withHowToCloseKnowledge(HowToCloseKnowledge.ONLY_PLATFORM.get())
                        .withCfdLeverageKnowledge(CfdLeverageKnowledge.PROVIDES.get())
                        .withStopLossKnowledge(StopLossKnowledge.MINIMIZE.get())
                        .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_10K.get())
                        .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.WARNING_CALL.get())
                        .withAutomaticStopKnowledge(AutomaticStopKnowledge.EARNINGS.get())
                        .withLossOn1to50Knowledge(LossOn1to50Knowledge.A2_450.get())
                        .withLossOn1to200Knowledge(LossOn1to200Knowledge.A2_1200.get())
                        .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_75.get())
                        .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_75.get())
                        .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_35.get())
                        .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(),Double.valueOf(66));
    }
}
