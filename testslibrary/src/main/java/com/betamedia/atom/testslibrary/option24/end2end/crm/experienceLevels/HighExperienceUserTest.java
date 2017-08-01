package com.betamedia.atom.testslibrary.option24.end2end.crm.experienceLevels;

import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import org.testng.annotations.Test;
import static com.betamedia.atom.core.api.crm.form.entities.QuestionnaireAnswers.*;

/**
 * Created by vsnigur on 5/25/17.
 */
public class HighExperienceUserTest extends AbstractUserExperienceTest {

    /**
     * - Create user via mobile API;
     * - Update as needed answers in the trading experience crm widgets page;
     * - Update personal score in the personal information crm widgets page;
     * - Check experience score in the DB;
     * - Check that user has high experience level;
     */
    @Test(description = "ID:9033")
    public void checkCustomerWithScore51IsHighExperienceTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(tradingExperienceInfoWith51Score());
        pages().navigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());

        operations().onBoardingOperations().assertUsernameScore(customer.getUserName(),Double.valueOf(51));
        operations().onBoardingOperations().assertUsernameHighExperience(customer.getUserName());
    }

    /**
     * - Create user via mobile API;
     * - Update as needed answers in the trading experience crm widgets page;
     * - Update personal score in the personal information crm widgets page;
     * - Check experience score in the DB;
     * - Check that user has high experience level;
     */
    @Test(description = "ID:9072")
    public void checkCustomerWithScore65IsHighExperienceTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(tradingExperienceInfoWith65Score());
        pages().navigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());

        operations().onBoardingOperations().assertUsernameScore(customer.getUserName(),Double.valueOf(65));
        operations().onBoardingOperations().assertUsernameHighExperience(customer.getUserName());
    }

    /**
     * - Create user via mobile API;
     * - Update as needed answers in the trading experience crm widgets page;
     * - Update personal score in the personal information crm widgets page;
     * - Check experience score in the DB;
     * - Check that user has high experience level;
     */
    @Test(description = "ID:9034")
    public void checkCustomerWithScore70IsHighExperienceTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(tradingExperienceInfoWith70Score());
        pages().navigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());

        operations().onBoardingOperations().assertUsernameScore(customer.getUserName(),Double.valueOf(70));
        operations().onBoardingOperations().assertUsernameHighExperience(customer.getUserName());
    }

    /**
     * - Create user via mobile API;
     * - Update as needed answers in the trading experience crm widgets page;
     * - Update personal score in the personal information crm widgets page;
     * - Check that user has no trade access type in the DB;
     */
    @Test(description = "ID:9024")
    public void checkHighExperienceCustomerAccessActiveTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(tradingExperienceInfoWith51Score());
        pages().navigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());

        operations().onBoardingOperations().assertUsernameLoginType(
                customer.getUserName(),
                OnboardingWizardConditions.AccessType.NOTRADE
        );
    }

    /**
     * Build trading experience info with 51 score
     * @return - TradingExperienceInfo instance
     */
    private TradingExperienceInfo tradingExperienceInfoWith51Score() {
        return TradingExperienceInfo.builder()
                .withSharesExperience(SharesExperience.FREQUENTLY)
                .withBinaryExperience(BinaryExperience.NEVER)
                .withForExExperience(ForExExperience.FREQUENTLY)
                .withAverageYearlyForExVolume(AverageYearlyForExVolume.VOLUME_50K_150K)
                .withCommonLeverage(CommonForExLeverage.LEVERAGE_1TO50_1TO200)
                .withFinancialWorkExperience(FinancialWorkExperience.WORKED)
                .withCfdBinaryKnowledge(CfdBinaryKnowledge.SPECULATIVE)
                .withMainFactorKnowledge(MainFactorKnowledge.INTEREST_RATES)
                .withHowToCloseKnowledge(HowToCloseKnowledge.LONDON_STOCK)
                .withCfdLeverageKnowledge(CfdLeverageKnowledge.MAGNIFIES)
                .withStopLossKnowledge(StopLossKnowledge.MINIMIZE)
                .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K)
                .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.MARGIN_CALL)
                .withAutomaticStopKnowledge(AutomaticStopKnowledge.EARNINGS)
                .withLossOn1to50Knowledge(LossOn1to50Knowledge.A1_800)
                .withLossOn1to200Knowledge(LossOn1to200Knowledge.A1_1800)
                .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_60)
                .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_75)
                .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_35)
                .build();
    }

    /**
     * Build trading experience info with 65 score
     * @return - TradingExperienceInfo instance
     */
    private TradingExperienceInfo tradingExperienceInfoWith65Score() {
        return TradingExperienceInfo.builder()
                .withSharesExperience(SharesExperience.FREQUENTLY)
                .withBinaryExperience(BinaryExperience.NEVER)
                .withForExExperience(ForExExperience.FREQUENTLY)
                .withAverageYearlyForExVolume(AverageYearlyForExVolume.VOLUME_50K_150K)
                .withCommonLeverage(CommonForExLeverage.LEVERAGE_1TO50_1TO200)
                .withFinancialWorkExperience(FinancialWorkExperience.WORKED)
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
                .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_60)
                .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_100)
                .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_25)
                .build();
    }

    /**
     * Build trading experience info with 70 score
     * @return - TradingExperienceInfo instance
     */
    private TradingExperienceInfo tradingExperienceInfoWith70Score() {
        return  TradingExperienceInfo.builder()
                .withSharesExperience(SharesExperience.FREQUENTLY)
                .withBinaryExperience(BinaryExperience.FREQUENTLY)
                .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_ABOVE_10K)
                .withForExExperience(ForExExperience.FREQUENTLY)
                .withAverageYearlyForExVolume(AverageYearlyForExVolume.VOLUME_50K_150K)
                .withCommonLeverage(CommonForExLeverage.LEVERAGE_1TO50_1TO200)
                .withFinancialWorkExperience(FinancialWorkExperience.WORKED)
                .withCfdBinaryKnowledge(CfdBinaryKnowledge.SPECULATIVE)
                .withMainFactorKnowledge(MainFactorKnowledge.INTEREST_RATES)
                .withHowToCloseKnowledge(HowToCloseKnowledge.LONDON_STOCK)
                .withCfdLeverageKnowledge(CfdLeverageKnowledge.MAGNIFIES)
                .withStopLossKnowledge(StopLossKnowledge.MINIMIZE)
                .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K)
                .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.MARGIN_CALL)
                .withAutomaticStopKnowledge(AutomaticStopKnowledge.EQUITY_FALLS)
                .withLossOn1to50Knowledge(LossOn1to50Knowledge.A2_450)
                .withLossOn1to200Knowledge(LossOn1to200Knowledge.A1_1800)
                .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_75)
                .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_75)
                .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_35)
                .build();
    }
}
