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
    public void calculationOfScore77Test(){
        pages().crmNavigation().register();
        CustomerBuilder.CustomerRO customer = new CustomerBuilder().createCustomerRO();
        pages().register().register(customer);
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(new PersonalInformationBuilder()
                .withEmploymentStatus(FnsEmploymentStatus.SALARIED_EMPLOYEE.getAnswer())
                .withIndustry(FnsIndustry.ACCOUNTING.getAnswer())
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(FnsHaveTin.YES.getAnswer())
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(FnsIsUSReportablePerson.NO.getAnswer())
                .withEducationLevel(FnsLevelOfEducation.POST_GRADUATE.getAnswer())
                .withEducationField(FnsFieldOfStudy.ACCOUNTING.getAnswer())
                .withPoliticalExposureStatus(FnsPoliticallyExposed.NO.getAnswer())
                .withSourceOfFunds(FnsSourceOfFunds.EMPLOYMENT.getAnswer())
                .withAnnualIncome(FnsTotalAnnualIncome.INCOME_OVER100K.getAnswer())
                .withNetWealth(FnsNetWealth.NET_WEALTH_OVER_300K.getAnswer())
                .withExpectedDepositsPerYear(FnsExpectedDeposits.DEPOSITS_OVER50K.getAnswer())
                .withPurposeOfTrading(FnsPurposeOfTrading.SPECULATIVE.getAnswer())
                .build()
        );
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(new TradingExperienceInfoBuilder()
                .withSharesExperience(FnsFinancialProducts1Shares.FREQUENTLY.getAnswer())
                .withBinaryExperience(FnsFinancialProducts1Binary.FREQUENTLY.getAnswer())
                .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.getAnswer())
                .withAverageYearlyForExVolume(AverageYearlyForExVolume.VOLUME_ABOVE_500K.getAnswer())
                .withCommonLeverage(CommonLeverageForExVolume.LEVERAGE_ABOVE1TO500.getAnswer())
                .withForExExperience(FnsFinancialProducts2Forex.FREQUENTLY.getAnswer())
                .withFinancialWorkExperience(FnsSelectIfApplicable.WORKED.getAnswer())
                .withCfdBinaryKnowledge(FnsKnowledgeCfdsBinaries.SPECULATIVE.getAnswer())
                .withMainFactorKnowledge(FnsKnowledgeMainFactor.INTEREST_RATES.getAnswer())
                .withHowToCloseKnowledge(FnsKnowledgeIfYouOpen.ONLY_PLATFORM.getAnswer())
                .withCfdLeverageKnowledge(FnsKnowledgeLeverageCFD.MAGNIFIES.getAnswer())
                .withStopLossKnowledge(FnsKnowledgeStoploss.MINIMIZE.getAnswer())
                .withRequiredMarginKnowledge(FnsKnowledgeRequiredMargin.MARGIN1K.getAnswer())
                .withMarginLevelDropKnowledge(FnsKnowledgeMarginLevelDrop.MARGIN_CALL.getAnswer())
                .withAutomaticStopKnowledge(FnsKnowledgeAutomaticStop.EQUITY_FALLS.getAnswer())
                .withLossOn1to50Knowledge(FnsKnowledgePositionLoss1to50.LOSS1TO50A1_800.getAnswer())
                .withLossOn1to200Knowledge(FnsKnowledgePositionLoss1to200.LOSS1TO200A2_1200.getAnswer())
                .withBinaryInvestProfitKnowledge(FnsKnowledgeInvestBinaryProfit.BINARY_PROFIT_60.getAnswer())
                .withBinaryInvestLossKnowledge(FnsKnowledgeInvestBinaryLoss.LOSS75.getAnswer())
                .withBinaryProbabilityKnowledge(FnsKnowledgeBinaryInTheMoney.MONEY35.getAnswer())
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(),Double.valueOf(77));
    }

    @Test(description = "ID:9130")
    public void calculationOfScore26Test(){
        pages().crmNavigation().register();
        CustomerBuilder.CustomerRO customer = new CustomerBuilder().createCustomerRO();
        pages().register().register(customer);
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(new PersonalInformationBuilder()
                .withEmploymentStatus(FnsEmploymentStatus.UNEMPLOYED.getAnswer())
                .withIndustry(FnsIndustry.FINANCE.getAnswer())
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(FnsHaveTin.NO.getAnswer())
                .withSocialSecurityNumber(TAX_ID)
                .withUSReportabilityStatus(FnsIsUSReportablePerson.YES.getAnswer())
                .withEducationLevel(FnsLevelOfEducation.BACHELOR.getAnswer())
                .withEducationField(FnsFieldOfStudy.LAW.getAnswer())
                .withPoliticalExposureStatus(FnsPoliticallyExposed.YES.getAnswer())
                .withPoliticalExposureComment(FREE_TEXT)
                .withSourceOfFunds(FnsSourceOfFunds.SAVINGS.getAnswer())
                .withAnnualIncome(FnsTotalAnnualIncome.INCOME_50K_100K.getAnswer())
                .withNetWealth(FnsNetWealth.NET_WEALTH_150K_300K.getAnswer())
                .withExpectedDepositsPerYear(FnsExpectedDeposits.DEPOSITS_25K_50K.getAnswer())
                .withPurposeOfTrading(FnsPurposeOfTrading.ADDITIONAL_INCOME.getAnswer())
                .build()
        );
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(new TradingExperienceInfoBuilder()
                .withSharesExperience(FnsFinancialProducts1Shares.REGULARLY.getAnswer())
                .withBinaryExperience(FnsFinancialProducts1Binary.FREQUENTLY.getAnswer())
                .withForExExperience(FnsFinancialProducts2Forex.REGULARLY.getAnswer())
                .withFinancialWorkExperience(FnsSelectIfApplicable.ATTENDED.getAnswer())
                .withCfdBinaryKnowledge(FnsKnowledgeCfdsBinaries.NON_RISKY.getAnswer())
                .withMainFactorKnowledge(FnsKnowledgeMainFactor.ANNOUNCEMENT.getAnswer())
                .withHowToCloseKnowledge(FnsKnowledgeIfYouOpen.LONDON_STOCK.getAnswer())
                .withCfdLeverageKnowledge(FnsKnowledgeLeverageCFD.PROVIDES.getAnswer())
                .withStopLossKnowledge(FnsKnowledgeStoploss.BUY.getAnswer())
                .withRequiredMarginKnowledge(FnsKnowledgeRequiredMargin.MARGIN10K.getAnswer())
                .withMarginLevelDropKnowledge(FnsKnowledgeMarginLevelDrop.WARNING_CALL.getAnswer())
                .withAutomaticStopKnowledge(FnsKnowledgeAutomaticStop.EARNINGS.getAnswer())
                .withLossOn1to50Knowledge(FnsKnowledgePositionLoss1to50.LOSS1TO50A2_450.getAnswer())
                .withLossOn1to200Knowledge(FnsKnowledgePositionLoss1to200.LOSS1TO200A2_1200.getAnswer())
                .withBinaryInvestProfitKnowledge(FnsKnowledgeInvestBinaryProfit.BINARY_PROFIT_60.getAnswer())
                .withBinaryInvestLossKnowledge(FnsKnowledgeInvestBinaryLoss.LOSS75.getAnswer())
                .withBinaryProbabilityKnowledge(FnsKnowledgeBinaryInTheMoney.MONEY35.getAnswer())
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(),Double.valueOf(26));    }

    @Test(description = "ID:9131")
    public void calculationOfScore29Test(){
        pages().crmNavigation().register();
        CustomerBuilder.CustomerRO customer = new CustomerBuilder().createCustomerRO();
        pages().register().register(customer);
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(new PersonalInformationBuilder()
                .withEmploymentStatus(FnsEmploymentStatus.STUDENT.getAnswer())
                .withIndustry(FnsIndustry.FUNDS.getAnswer())
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(FnsHaveTin.YES.getAnswer())
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(FnsIsUSReportablePerson.NO.getAnswer())
                .withEducationLevel(FnsLevelOfEducation.SECONDARY.getAnswer())
                .withEducationField(FnsFieldOfStudy.COMPUTER.getAnswer())
                .withPoliticalExposureStatus(FnsPoliticallyExposed.NO.getAnswer())
                .withSourceOfFunds(FnsSourceOfFunds.RETIREMENT.getAnswer())
                .withAnnualIncome(FnsTotalAnnualIncome.INCOME_25K_50K.getAnswer())
                .withNetWealth(FnsNetWealth.NET_WEALTH_50K_150K.getAnswer())
                .withExpectedDepositsPerYear(FnsExpectedDeposits.DEPOSITS_15K_25K.getAnswer())
                .withPurposeOfTrading(FnsPurposeOfTrading.HEDGING.getAnswer())
                .build()
        );
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(new TradingExperienceInfoBuilder()
                .withSharesExperience(FnsFinancialProducts1Shares.OCCASIONALLY.getAnswer())
                .withBinaryExperience(FnsFinancialProducts1Binary.REGULARLY.getAnswer())
                .withForExExperience(FnsFinancialProducts2Forex.OCCASIONALLY.getAnswer())
                .withFinancialWorkExperience(FnsSelectIfApplicable.BOTH.getAnswer())
                .withCfdBinaryKnowledge(FnsKnowledgeCfdsBinaries.PHYSICALLY_DELIVERING.getAnswer())
                .withMainFactorKnowledge(FnsKnowledgeMainFactor.EMPLOYEE_LAYOFFS.getAnswer())
                .withHowToCloseKnowledge(FnsKnowledgeIfYouOpen.CANNOT_SELL.getAnswer())
                .withCfdLeverageKnowledge(FnsKnowledgeLeverageCFD.NONE.getAnswer())
                .withStopLossKnowledge(FnsKnowledgeStoploss.TAKE.getAnswer())
                .withRequiredMarginKnowledge(FnsKnowledgeRequiredMargin.MARGIN100K.getAnswer())
                .withMarginLevelDropKnowledge(FnsKnowledgeMarginLevelDrop.MARGIN_UPGRADE.getAnswer())
                .withAutomaticStopKnowledge(FnsKnowledgeAutomaticStop.OPEN_POSITION.getAnswer())
                .withLossOn1to50Knowledge(FnsKnowledgePositionLoss1to50.LOSS1TO50A3_200.getAnswer())
                .withLossOn1to200Knowledge(FnsKnowledgePositionLoss1to200.LOSS1TO200A3_1000.getAnswer())
                .withBinaryInvestProfitKnowledge(FnsKnowledgeInvestBinaryProfit.BINARY_PROFIT_100.getAnswer())
                .withBinaryInvestLossKnowledge(FnsKnowledgeInvestBinaryLoss.LOSS50.getAnswer())
                .withBinaryProbabilityKnowledge(FnsKnowledgeBinaryInTheMoney.MONEY45.getAnswer())
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(),Double.valueOf(29));
    }


    @Test(description = "ID:9132")
    public void calculationOfScore50Test(){
        pages().crmNavigation().register();
        CustomerBuilder.CustomerRO customer = new CustomerBuilder().createCustomerRO();
        pages().register().register(customer);
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(new PersonalInformationBuilder()
                .withEmploymentStatus(FnsEmploymentStatus.RETIRED.getAnswer())
                .withIndustry(FnsIndustry.ATTORNEYS.getAnswer())
                .withEmployerName(FREE_TEXT)
                .withTaxResidenceCountry(COUNTRY)
                .withTaxIdentificationNumberStatus(FnsHaveTin.YES.getAnswer())
                .withTaxIdentificationNumber(TAX_ID)
                .withUSReportabilityStatus(FnsIsUSReportablePerson.NO.getAnswer())
                .withEducationLevel(FnsLevelOfEducation.PRIMARY.getAnswer())
                .withEducationField(FnsFieldOfStudy.MEDICINE.getAnswer())
                .withPoliticalExposureStatus(FnsPoliticallyExposed.NO.getAnswer())
                .withSourceOfFunds(FnsSourceOfFunds.INHERITANCE.getAnswer())
                .withAnnualIncome(FnsTotalAnnualIncome.INCOME_15K_25K.getAnswer())
                .withNetWealth(FnsNetWealth.NET_WEALTH_15K_50K.getAnswer())
                .withExpectedDepositsPerYear(FnsExpectedDeposits.DEPOSITS_10K_15K.getAnswer())
                .withPurposeOfTrading(FnsPurposeOfTrading.OTHER.getAnswer())
                .build()
        );
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(new TradingExperienceInfoBuilder()
                .withSharesExperience(FnsFinancialProducts1Shares.NEVER.getAnswer())
                .withBinaryExperience(FnsFinancialProducts1Binary.OCCASIONALLY.getAnswer())
                .withForExExperience(FnsFinancialProducts2Forex.NEVER.getAnswer())
                .withAverageYearlyForExVolume(AverageYearlyForExVolume.VOLUME_ABOVE_500K.getAnswer())
                .withFinancialWorkExperience(FnsSelectIfApplicable.NEITHER.getAnswer())
                .withCfdBinaryKnowledge(FnsKnowledgeCfdsBinaries.SPECULATIVE.getAnswer())
                .withMainFactorKnowledge(FnsKnowledgeMainFactor.INTEREST_RATES.getAnswer())
                .withHowToCloseKnowledge(FnsKnowledgeIfYouOpen.ONLY_PLATFORM.getAnswer())
                .withCfdLeverageKnowledge(FnsKnowledgeLeverageCFD.MAGNIFIES.getAnswer())
                .withStopLossKnowledge(FnsKnowledgeStoploss.BUY.getAnswer())
                .withRequiredMarginKnowledge(FnsKnowledgeRequiredMargin.MARGIN1K.getAnswer())
                .withMarginLevelDropKnowledge(FnsKnowledgeMarginLevelDrop.MARGIN_CALL.getAnswer())
                .withAutomaticStopKnowledge(FnsKnowledgeAutomaticStop.EQUITY_FALLS.getAnswer())
                .withLossOn1to50Knowledge(FnsKnowledgePositionLoss1to50.LOSS1TO50A1_800.getAnswer())
                .withLossOn1to200Knowledge(FnsKnowledgePositionLoss1to200.LOSS1TO200A1_1800.getAnswer())
                .withBinaryInvestProfitKnowledge(FnsKnowledgeInvestBinaryProfit.BINARY_PROFIT_100.getAnswer())
                .withBinaryInvestLossKnowledge(FnsKnowledgeInvestBinaryLoss.LOSS100.getAnswer())
                .withBinaryProbabilityKnowledge(FnsKnowledgeBinaryInTheMoney.MONEY45.getAnswer())
                .build()
        );
        operations().onBoardingOperations().assertUsernameScore(customer.getEmail(),Double.valueOf(50));
    }
}
