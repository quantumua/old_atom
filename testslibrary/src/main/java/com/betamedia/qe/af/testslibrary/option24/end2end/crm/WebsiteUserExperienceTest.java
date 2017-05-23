package com.betamedia.qe.af.testslibrary.option24.end2end.crm;

import com.betamedia.qe.af.core.api.crm.form.builders.TradingExperienceInfoBuilder;
import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import org.testng.annotations.Test;

import static com.betamedia.qe.af.testslibrary.option24.end2end.crm.newQuestionnaries.Questions.*;

/**
 * Created by mbelyaev on 5/17/17.
 */
public class WebsiteUserExperienceTest extends AbstractUserExperienceTest {

    @Test(description = "ID:9029")
    public void score31CustomerExperienceTest() {
        pages().crmNavigation().register();
        CustomerBuilder.CustomerRO customer = new CustomerBuilder().createCustomerRO();
        pages().register().register(customer);
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                new TradingExperienceInfoBuilder()
                        .withSharesExperience(SharesExperience.NEVER.get())
                        .withBinaryExperience(BinaryExperience.REGULARLY.get())
                        .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.get())
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
        operations().onBoardingOperations().assertUsernameLowExperience(customer.getEmail());
    }

    @Test(description = "ID:9071")
    public void score45CustomerExperienceTest() {
        pages().crmNavigation().register();
        CustomerBuilder.CustomerRO customer = new CustomerBuilder().createCustomerRO();
        pages().register().register(customer);
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                new TradingExperienceInfoBuilder()
                        .withSharesExperience(SharesExperience.REGULARLY.get())
                        .withBinaryExperience(BinaryExperience.REGULARLY.get())
                        .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_UNDER_500.get())
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
        operations().onBoardingOperations().assertUsernameLowExperience(customer.getEmail());
    }

    @Test(description = "ID:9032")
    public void score50CustomerExperienceTest() {
        pages().crmNavigation().register();
        CustomerBuilder.CustomerRO customer = new CustomerBuilder().createCustomerRO();
        pages().register().register(customer);
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                new TradingExperienceInfoBuilder()
                        .withSharesExperience(SharesExperience.REGULARLY.get())
                        .withBinaryExperience(BinaryExperience.REGULARLY.get())
                        .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_UNDER_500.get())
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
        pages().fnsPersonalInformation().submit(personalInfoScore5());
        operations().onBoardingOperations().assertUsernameLowExperience(customer.getEmail());
    }
}
