package com.betamedia.atom.testslibrary.option24.end2end.crm.experienceLevels;

import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WidgetsNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.testslibrary.option24.end2end.crm.newQuestionnaries.Questions;
import org.testng.annotations.Test;

/**
 * Created by mbelyaev on 5/17/17.
 */
public class LowExperienceUserTest extends AbstractUserExperienceTest {

    /**
     * - Create user via crm widget;
     * - Update as needed answers in the trading experience crm widgets page;
     * - Update personal score in the personal information crm widgets page;
     * - Check that user has low experience level;
     */
    @Test(description = "ID:9029")
    public void checkCustomerWithScore31IsLowExperienceTest() {
        pages().crmNavigation().register();
        CustomerRO customer = CustomerRO.builder(WidgetsNamingStrategy.get()).build();
        pages().registerPage().register(customer);
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                TradingExperienceInfo.builder()
                        .withSharesExperience(Questions.SharesExperience.NEVER.get())
                        .withBinaryExperience(Questions.BinaryExperience.REGULARLY.get())
                        .withAverageYearlyBinaryVolume(Questions.AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.get())
                        .withForExExperience(Questions.ForExExperience.NEVER.get())
                        .withFinancialWorkExperience(Questions.FinancialWorkExperience.NEITHER.get())
                        .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.PHYSICALLY_DELIVERING.get())
                        .withMainFactorKnowledge(Questions.MainFactorKnowledge.ANNOUNCEMENT.get())
                        .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.LONDON_STOCK.get())
                        .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.NONE.get())
                        .withStopLossKnowledge(Questions.StopLossKnowledge.BUY.get())
                        .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_1K.get())
                        .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.WARNING_CALL.get())
                        .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EQUITY_FALLS.get())
                        .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A2_450.get())
                        .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A3_1000.get())
                        .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_75.get())
                        .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_100.get())
                        .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_25.get())
                        .build()
        );
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());
        operations().onBoardingOperations().assertUsernameLowExperience(customer.getEmail());
    }

    /**
     * - Create user via crm widget;
     * - Update as needed answers in the trading experience crm widgets page;
     * - Update personal score in the personal information crm widgets page;
     * - Check that user has low experience level;
     */
    @Test(description = "ID:9071")
    public void checkCustomerWithScore45IsLowExperienceTest() {
        pages().crmNavigation().register();
        CustomerRO customer = CustomerRO.builder(WidgetsNamingStrategy.get()).build();
        pages().registerPage().register(customer);
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                TradingExperienceInfo.builder()
                        .withSharesExperience(Questions.SharesExperience.REGULARLY.get())
                        .withBinaryExperience(Questions.BinaryExperience.REGULARLY.get())
                        .withAverageYearlyBinaryVolume(Questions.AverageYearlyBinaryVolume.VOLUME_UNDER_500.get())
                        .withForExExperience(Questions.ForExExperience.NEVER.get())
                        .withFinancialWorkExperience(Questions.FinancialWorkExperience.NEITHER.get())
                        .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.SPECULATIVE.get())
                        .withMainFactorKnowledge(Questions.MainFactorKnowledge.INTEREST_RATES.get())
                        .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.ONLY_PLATFORM.get())
                        .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.MAGNIFIES.get())
                        .withStopLossKnowledge(Questions.StopLossKnowledge.MINIMIZE.get())
                        .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_1K.get())
                        .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.WARNING_CALL.get())
                        .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EQUITY_FALLS.get())
                        .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A2_450.get())
                        .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A1_1800.get())
                        .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_75.get())
                        .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_100.get())
                        .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_25.get())
                        .build()
        );
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());
        operations().onBoardingOperations().assertUsernameLowExperience(customer.getEmail());
    }

    /**
     * - Create user via crm widget;
     * - Update as needed answers in the trading experience crm widgets page;
     * - Update personal score in the personal information crm widgets page;
     * - Check that user has low experience level;
     */
    @Test(description = "ID:9032")
    public void checkCustomerWithScore50IsLowExperienceTest() {
        pages().crmNavigation().register();
        CustomerRO customer = CustomerRO.builder(WidgetsNamingStrategy.get()).build();
        pages().registerPage().register(customer);
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                TradingExperienceInfo.builder()
                        .withSharesExperience(Questions.SharesExperience.REGULARLY.get())
                        .withBinaryExperience(Questions.BinaryExperience.REGULARLY.get())
                        .withAverageYearlyBinaryVolume(Questions.AverageYearlyBinaryVolume.VOLUME_UNDER_500.get())
                        .withForExExperience(Questions.ForExExperience.NEVER.get())
                        .withFinancialWorkExperience(Questions.FinancialWorkExperience.NEITHER.get())
                        .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.SPECULATIVE.get())
                        .withMainFactorKnowledge(Questions.MainFactorKnowledge.INTEREST_RATES.get())
                        .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.ONLY_PLATFORM.get())
                        .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.MAGNIFIES.get())
                        .withStopLossKnowledge(Questions.StopLossKnowledge.MINIMIZE.get())
                        .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_1K.get())
                        .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.WARNING_CALL.get())
                        .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EQUITY_FALLS.get())
                        .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A2_450.get())
                        .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A1_1800.get())
                        .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_75.get())
                        .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_100.get())
                        .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_25.get())
                        .build()
        );
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore5());
        operations().onBoardingOperations().assertUsernameLowExperience(customer.getEmail());
    }
}
