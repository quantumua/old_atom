package com.betamedia.atom.testslibrary.option24.end2end.crm.experienceLevels;

import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WidgetsNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import org.testng.annotations.Test;
import static com.betamedia.atom.core.api.crm.form.entities.QuestionnaireAnswers.*;

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
        pages().navigation().register();
        CustomerRO customer = CustomerRO.builder(WidgetsNamingStrategy.get()).build();
        pages().registerPage().register(customer);
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                TradingExperienceInfo.builder()
                        .withSharesExperience(SharesExperience.NEVER)
                        .withBinaryExperience(BinaryExperience.REGULARLY)
                        .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_ABOVE_10K)
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
        pages().navigation().register();
        CustomerRO customer = CustomerRO.builder(WidgetsNamingStrategy.get()).build();
        pages().registerPage().register(customer);
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                TradingExperienceInfo.builder()
                        .withSharesExperience(SharesExperience.REGULARLY)
                        .withBinaryExperience(BinaryExperience.REGULARLY)
                        .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_UNDER_500)
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
        pages().navigation().register();
        CustomerRO customer = CustomerRO.builder(WidgetsNamingStrategy.get()).build();
        pages().registerPage().register(customer);
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                TradingExperienceInfo.builder()
                        .withSharesExperience(SharesExperience.REGULARLY)
                        .withBinaryExperience(BinaryExperience.REGULARLY)
                        .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_UNDER_500)
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
        pages().fnsPersonalInformation().submit(personalInfoScore5());
        operations().onBoardingOperations().assertUsernameLowExperience(customer.getEmail());
    }
}
