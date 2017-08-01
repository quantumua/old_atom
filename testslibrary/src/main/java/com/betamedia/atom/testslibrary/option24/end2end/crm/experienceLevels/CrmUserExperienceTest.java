package com.betamedia.atom.testslibrary.option24.end2end.crm.experienceLevels;

import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import org.testng.annotations.Test;
import static com.betamedia.atom.core.api.crm.form.entities.QuestionnaireAnswers.*;

/**
 * Created by mbelyaev on 5/17/17.
 */
public class CrmUserExperienceTest extends AbstractUserExperienceTest {

    @Test(description = "ID:9082")
    public void newCustomerExperienceTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().navigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());
        operations().onBoardingOperations().assertCustomerUnknown(customer.getId());
    }

    @Test(description = "ID:9083")
    public void score10CustomerExperienceTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                TradingExperienceInfo.builder()
                        .withSharesExperience(SharesExperience.NEVER)
                        .withBinaryExperience(BinaryExperience.REGULARLY)
                        .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_UNDER_500)
                        .withForExExperience(ForExExperience.NEVER)
                        .withFinancialWorkExperience(FinancialWorkExperience.NEITHER)
                        .withCfdBinaryKnowledge(CfdBinaryKnowledge.PHYSICALLY_DELIVERING)
                        .withMainFactorKnowledge(MainFactorKnowledge.ANNOUNCEMENT)
                        .withHowToCloseKnowledge(HowToCloseKnowledge.LONDON_STOCK)
                        .withCfdLeverageKnowledge(CfdLeverageKnowledge.NONE)
                        .withStopLossKnowledge(StopLossKnowledge.MINIMIZE)
                        .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_10K)
                        .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.WARNING_CALL)
                        .withAutomaticStopKnowledge(AutomaticStopKnowledge.EARNINGS)
                        .withLossOn1to50Knowledge(LossOn1to50Knowledge.A3_200)
                        .withLossOn1to200Knowledge(LossOn1to200Knowledge.A3_1000)
                        .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_100)
                        .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_50)
                        .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_45)
                        .build()
        );
        pages().navigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());
        operations().onBoardingOperations().assertCustomerRejected(customer.getId());
    }

    @Test(description = "ID:9084")
    public void score25CustomerExperienceTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                TradingExperienceInfo.builder()
                        .withSharesExperience(SharesExperience.NEVER)
                        .withBinaryExperience(BinaryExperience.OCCASIONALLY)
                        .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_500_5K)
                        .withForExExperience(ForExExperience.NEVER)
                        .withFinancialWorkExperience(FinancialWorkExperience.NEITHER)
                        .withCfdBinaryKnowledge(CfdBinaryKnowledge.PHYSICALLY_DELIVERING)
                        .withMainFactorKnowledge(MainFactorKnowledge.ANNOUNCEMENT)
                        .withHowToCloseKnowledge(HowToCloseKnowledge.LONDON_STOCK)
                        .withCfdLeverageKnowledge(CfdLeverageKnowledge.NONE)
                        .withStopLossKnowledge(StopLossKnowledge.BUY)
                        .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K)
                        .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.WARNING_CALL)
                        .withAutomaticStopKnowledge(AutomaticStopKnowledge.EQUITY_FALLS)
                        .withLossOn1to50Knowledge(LossOn1to50Knowledge.A2_450)
                        .withLossOn1to200Knowledge(LossOn1to200Knowledge.A3_1000)
                        .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_75)
                        .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_100)
                        .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_25)
                        .build()
        );
        pages().navigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());
        operations().onBoardingOperations().assertCustomerNoExperience(customer.getId());
    }

    @Test(description = "ID:9085")
    public void score45CustomerExperienceTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                TradingExperienceInfo.builder()
                        .withSharesExperience(SharesExperience.REGULARLY)
                        .withBinaryExperience(BinaryExperience.OCCASIONALLY)
                        .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_500_5K)
                        .withForExExperience(ForExExperience.NEVER)
                        .withFinancialWorkExperience(FinancialWorkExperience.NEITHER)
                        .withCfdBinaryKnowledge(CfdBinaryKnowledge.SPECULATIVE)
                        .withMainFactorKnowledge(MainFactorKnowledge.INTEREST_RATES)
                        .withHowToCloseKnowledge(HowToCloseKnowledge.ONLY_PLATFORM)
                        .withCfdLeverageKnowledge(CfdLeverageKnowledge.MAGNIFIES)
                        .withStopLossKnowledge(StopLossKnowledge.MINIMIZE)
                        .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K)
                        .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.WARNING_CALL)
                        .withAutomaticStopKnowledge(AutomaticStopKnowledge.EQUITY_FALLS)
                        .withLossOn1to50Knowledge(LossOn1to50Knowledge.A2_450)
                        .withLossOn1to200Knowledge(LossOn1to200Knowledge.A1_1800)
                        .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_75)
                        .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_100)
                        .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_25)
                        .build()
        );
        pages().navigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());
        operations().onBoardingOperations().assertCustomerLowExperience(customer.getId());
    }

    @Test(description = "ID:9086")
    public void score65CustomerExperienceTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                TradingExperienceInfo.builder()
                        .withSharesExperience(SharesExperience.REGULARLY)
                        .withBinaryExperience(BinaryExperience.NEVER)
                        .withForExExperience(ForExExperience.FREQUENTLY)
                        .withAverageYearlyForExVolume(AverageYearlyForExVolume.VOLUME_UNDER_50K)
                        .withCommonLeverage(CommonForExLeverage.LEVERAGE_1TO50_1TO200)
                        .withFinancialWorkExperience(FinancialWorkExperience.NEITHER)
                        .withCfdBinaryKnowledge(CfdBinaryKnowledge.SPECULATIVE)
                        .withMainFactorKnowledge(MainFactorKnowledge.INTEREST_RATES)
                        .withHowToCloseKnowledge(HowToCloseKnowledge.ONLY_PLATFORM)
                        .withCfdLeverageKnowledge(CfdLeverageKnowledge.MAGNIFIES)
                        .withStopLossKnowledge(StopLossKnowledge.MINIMIZE)
                        .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K)
                        .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.WARNING_CALL)
                        .withAutomaticStopKnowledge(AutomaticStopKnowledge.EQUITY_FALLS)
                        .withLossOn1to50Knowledge(LossOn1to50Knowledge.A2_450)
                        .withLossOn1to200Knowledge(LossOn1to200Knowledge.A1_1800)
                        .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_75)
                        .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_100)
                        .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_25)
                        .build()
        );
        pages().navigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore10());
        operations().onBoardingOperations().assertCustomerHighExperience(customer.getId());
    }

    @Test(description = "ID:9087")
    public void score85CustomerExperienceTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                TradingExperienceInfo.builder()
                        .withSharesExperience(SharesExperience.FREQUENTLY)
                        .withBinaryExperience(BinaryExperience.FREQUENTLY)
                        .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_ABOVE_10K)
                        .withForExExperience(ForExExperience.FREQUENTLY)
                        .withAverageYearlyForExVolume(AverageYearlyForExVolume.VOLUME_ABOVE_500K)
                        .withCommonLeverage(CommonForExLeverage.LEVERAGE_ABOVE_1TO500)
                        .withFinancialWorkExperience(FinancialWorkExperience.SEMINARS)
                        .withCfdBinaryKnowledge(CfdBinaryKnowledge.SPECULATIVE)
                        .withMainFactorKnowledge(MainFactorKnowledge.INTEREST_RATES)
                        .withHowToCloseKnowledge(HowToCloseKnowledge.ONLY_PLATFORM)
                        .withCfdLeverageKnowledge(CfdLeverageKnowledge.MAGNIFIES)
                        .withStopLossKnowledge(StopLossKnowledge.MINIMIZE)
                        .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K)
                        .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.MARGIN_CALL)
                        .withAutomaticStopKnowledge(AutomaticStopKnowledge.EQUITY_FALLS)
                        .withLossOn1to50Knowledge(LossOn1to50Knowledge.A1_800)
                        .withLossOn1to200Knowledge(LossOn1to200Knowledge.A1_1800)
                        .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_75)
                        .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_100)
                        .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_25)
                        .build()
        );
        pages().navigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());
        operations().onBoardingOperations().assertCustomerExpert(customer.getId());
    }
}
