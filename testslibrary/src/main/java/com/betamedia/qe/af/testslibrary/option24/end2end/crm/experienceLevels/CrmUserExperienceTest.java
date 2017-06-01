package com.betamedia.qe.af.testslibrary.option24.end2end.crm.experienceLevels;

import com.betamedia.qe.af.core.api.crm.form.builders.TradingExperienceInfoBuilder;
import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import org.testng.annotations.Test;

import static com.betamedia.qe.af.testslibrary.option24.end2end.crm.newQuestionnaries.Questions.*;

/**
 * Created by mbelyaev on 5/17/17.
 */
public class CrmUserExperienceTest extends AbstractUserExperienceTest {

    private CRMCustomer crmRegisterAndLogIn(){
        CRMCustomer customer = operations().customerOperations().register();
        pages().crmNavigation().login();
        pages().crmLoginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
        return customer;
    }

    @Test(description = "ID:9082")
    public void newCustomerExperienceTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());
        operations().onBoardingOperations().assertCustomerUnknown(customer.getId());
    }

    @Test(description = "ID:9083")
    public void score10CustomerExperienceTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                new TradingExperienceInfoBuilder()
                        .withSharesExperience(SharesExperience.NEVER.get())
                        .withBinaryExperience(BinaryExperience.REGULARLY.get())
                        .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_UNDER_500.get())
                        .withForExExperience(ForExExperience.NEVER.get())
                        .withFinancialWorkExperience(FinancialWorkExperience.NEITHER.get())
                        .withCfdBinaryKnowledge(CfdBinaryKnowledge.PHYSICALLY_DELIVERING.get())
                        .withMainFactorKnowledge(MainFactorKnowledge.ANNOUNCEMENT.get())
                        .withHowToCloseKnowledge(HowToCloseKnowledge.LONDON_STOCK.get())
                        .withCfdLeverageKnowledge(CfdLeverageKnowledge.NONE.get())
                        .withStopLossKnowledge(StopLossKnowledge.MINIMIZE.get())
                        .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_10K.get())
                        .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.WARNING_CALL.get())
                        .withAutomaticStopKnowledge(AutomaticStopKnowledge.EARNINGS.get())
                        .withLossOn1to50Knowledge(LossOn1to50Knowledge.A3_200.get())
                        .withLossOn1to200Knowledge(LossOn1to200Knowledge.A3_1000.get())
                        .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_100.get())
                        .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_50.get())
                        .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_45.get())
                        .build()
        );
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());
        operations().onBoardingOperations().assertCustomerRejected(customer.getId());
    }

    @Test(description = "ID:9084")
    public void score25CustomerExperienceTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                new TradingExperienceInfoBuilder()
                        .withSharesExperience(SharesExperience.NEVER.get())
                        .withBinaryExperience(BinaryExperience.OCCASIONALLY.get())
                        .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_500_5K.get())
                        .withForExExperience(ForExExperience.NEVER.get())
                        .withFinancialWorkExperience(FinancialWorkExperience.NEITHER.get())
                        .withCfdBinaryKnowledge(CfdBinaryKnowledge.PHYSICALLY_DELIVERING.get())
                        .withMainFactorKnowledge(MainFactorKnowledge.ANNOUNCEMENT.get())
                        .withHowToCloseKnowledge(HowToCloseKnowledge.LONDON_STOCK.get())
                        .withCfdLeverageKnowledge(CfdLeverageKnowledge.NONE.get())
                        .withStopLossKnowledge(StopLossKnowledge.BUY.get())
                        .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K.get())
                        .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.WARNING_CALL.get())
                        .withAutomaticStopKnowledge(AutomaticStopKnowledge.EQUITY_FALLS.get())
                        .withLossOn1to50Knowledge(LossOn1to50Knowledge.A2_450.get())
                        .withLossOn1to200Knowledge(LossOn1to200Knowledge.A3_1000.get())
                        .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_75.get())
                        .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_100.get())
                        .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_25.get())
                        .build()
        );
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());
        operations().onBoardingOperations().assertCustomerNoExperience(customer.getId());
    }

    @Test(description = "ID:9085")
    public void score45CustomerExperienceTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                new TradingExperienceInfoBuilder()
                        .withSharesExperience(SharesExperience.REGULARLY.get())
                        .withBinaryExperience(BinaryExperience.OCCASIONALLY.get())
                        .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_500_5K.get())
                        .withForExExperience(ForExExperience.NEVER.get())
                        .withFinancialWorkExperience(FinancialWorkExperience.NEITHER.get())
                        .withCfdBinaryKnowledge(CfdBinaryKnowledge.SPECULATIVE.get())
                        .withMainFactorKnowledge(MainFactorKnowledge.INTEREST_RATES.get())
                        .withHowToCloseKnowledge(HowToCloseKnowledge.ONLY_PLATFORM.get())
                        .withCfdLeverageKnowledge(CfdLeverageKnowledge.MAGNIFIES.get())
                        .withStopLossKnowledge(StopLossKnowledge.MINIMIZE.get())
                        .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K.get())
                        .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.WARNING_CALL.get())
                        .withAutomaticStopKnowledge(AutomaticStopKnowledge.EQUITY_FALLS.get())
                        .withLossOn1to50Knowledge(LossOn1to50Knowledge.A2_450.get())
                        .withLossOn1to200Knowledge(LossOn1to200Knowledge.A1_1800.get())
                        .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_75.get())
                        .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_100.get())
                        .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_25.get())
                        .build()
        );
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());
        operations().onBoardingOperations().assertCustomerLowExperience(customer.getId());
    }

    @Test(description = "ID:9086")
    public void score65CustomerExperienceTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                new TradingExperienceInfoBuilder()
                        .withSharesExperience(SharesExperience.REGULARLY.get())
                        .withBinaryExperience(BinaryExperience.NEVER.get())
                        .withForExExperience(ForExExperience.FREQUENTLY.get())
                        .withAverageYearlyForExVolume(AverageYearlyForExVolume.VOLUME_UNDER_50K.get())
                        .withCommonLeverage(CommonLeverage.LEVERAGE_1TO50_1TO200.get())
                        .withFinancialWorkExperience(FinancialWorkExperience.NEITHER.get())
                        .withCfdBinaryKnowledge(CfdBinaryKnowledge.SPECULATIVE.get())
                        .withMainFactorKnowledge(MainFactorKnowledge.INTEREST_RATES.get())
                        .withHowToCloseKnowledge(HowToCloseKnowledge.ONLY_PLATFORM.get())
                        .withCfdLeverageKnowledge(CfdLeverageKnowledge.MAGNIFIES.get())
                        .withStopLossKnowledge(StopLossKnowledge.MINIMIZE.get())
                        .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K.get())
                        .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.WARNING_CALL.get())
                        .withAutomaticStopKnowledge(AutomaticStopKnowledge.EQUITY_FALLS.get())
                        .withLossOn1to50Knowledge(LossOn1to50Knowledge.A2_450.get())
                        .withLossOn1to200Knowledge(LossOn1to200Knowledge.A1_1800.get())
                        .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_75.get())
                        .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_100.get())
                        .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_25.get())
                        .build()
        );
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore10());
        operations().onBoardingOperations().assertCustomerHighExperience(customer.getId());
    }

    @Test(description = "ID:9087")
    public void score85CustomerExperienceTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                new TradingExperienceInfoBuilder()
                        .withSharesExperience(SharesExperience.FREQUENTLY.get())
                        .withBinaryExperience(BinaryExperience.FREQUENTLY.get())
                        .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.get())
                        .withForExExperience(ForExExperience.FREQUENTLY.get())
                        .withAverageYearlyForExVolume(AverageYearlyForExVolume.VOLUME_ABOVE_500K.get())
                        .withCommonLeverage(CommonLeverage.LEVERAGE_ABOVE_1TO500.get())
                        .withFinancialWorkExperience(FinancialWorkExperience.SEMINARS.get())
                        .withCfdBinaryKnowledge(CfdBinaryKnowledge.SPECULATIVE.get())
                        .withMainFactorKnowledge(MainFactorKnowledge.INTEREST_RATES.get())
                        .withHowToCloseKnowledge(HowToCloseKnowledge.ONLY_PLATFORM.get())
                        .withCfdLeverageKnowledge(CfdLeverageKnowledge.MAGNIFIES.get())
                        .withStopLossKnowledge(StopLossKnowledge.MINIMIZE.get())
                        .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K.get())
                        .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.MARGIN_CALL.get())
                        .withAutomaticStopKnowledge(AutomaticStopKnowledge.EQUITY_FALLS.get())
                        .withLossOn1to50Knowledge(LossOn1to50Knowledge.A1_800.get())
                        .withLossOn1to200Knowledge(LossOn1to200Knowledge.A1_1800.get())
                        .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_75.get())
                        .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_100.get())
                        .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_25.get())
                        .build()
        );
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());
        operations().onBoardingOperations().assertCustomerExpert(customer.getId());
    }


}
