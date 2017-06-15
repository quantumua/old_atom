package com.betamedia.atom.testslibrary.option24.end2end.crm.experienceLevels;

import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.testslibrary.option24.end2end.crm.newQuestionnaries.Questions;
import org.testng.annotations.Test;

/**
 * Created by vsnigur on 5/25/17.
 */
public class ExpertUserTest extends AbstractUserExperienceTest {

    /**
     * - Build user for the test;
     * - Login with created user;
     * @return - CRMCustomer
     */
    private CRMCustomer crmRegisterAndLogIn(){
        CRMCustomer customer = operations().customerOperations().register();
        pages().crmNavigation().login();
        pages().crmLoginPage().login(customer.getUserName(), CustomerRO.CustomerROBuilder.DEFAULT_PASSWORD);
        return customer;
    }

    /**
     * - Create user via mobile API;
     * - Update as needed answers in the trading experience crm widgets page;
     * - Update personal score in the personal information crm widgets page;
     * - Check experience score in the DB;
     * - Check that user has expert experience level;
     */
    @Test(description = "ID:9035")
    public void checkCustomerWithScore71IsExpertTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(tradingExperienceInfoWith71Score());
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());

        operations().onBoardingOperations().assertUsernameScore(customer.getUserName(),Double.valueOf(71));
        operations().onBoardingOperations().assertUsernameExpert(customer.getUserName());
    }

    /**
     * - Create user via mobile API;
     * - Update as needed answers in the trading experience crm widgets page;
     * - Update personal score in the personal information crm widgets page;
     * - Check experience score in the DB;
     * - Check that user has expert experience level;
     */
    @Test(description = "ID:9073")
    public void checkCustomerWithScore85IsExpertTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(tradingExperienceInfoWith85Score());
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore5());

        operations().onBoardingOperations().assertUsernameScore(customer.getUserName(),Double.valueOf(85));
        operations().onBoardingOperations().assertUsernameExpert(customer.getUserName());
    }

    /**
     * - Create user via mobile API;
     * - Update as needed answers in the trading experience crm widgets page;
     * - Update personal score in the personal information crm widgets page;
     * - Check experience score in the DB;
     * - Check that user has expert experience level;
     */
    @Test(description = "ID:9036")
    public void checkCustomerWithScore100IsExpertTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(tradingExperienceInfoWith100Score());
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore10());

        operations().onBoardingOperations().assertUsernameScore(customer.getUserName(),Double.valueOf(100));
        operations().onBoardingOperations().assertUsernameExpert(customer.getUserName());
    }

    /**
     * - Create user via mobile API;
     * - Update as needed answers in the trading experience crm widgets page;
     * - Update personal score in the personal information crm widgets page;
     * - Check that user has no trade access type in the DB;
     */
    @Test(description = "ID:9023")
    public void checkExpertCustomerAccessActiveTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(tradingExperienceInfoWith71Score());
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore5());

        operations().onBoardingOperations().assertUsernameLoginType(
                customer.getUserName(),
                OnboardingWizardConditions.AccessType.NOTRADE
        );
    }

    /**
     * Build trading experience info with 71 score
     * @return - TradingExperienceInfo instance
     */
    private TradingExperienceInfo tradingExperienceInfoWith71Score() {
        return TradingExperienceInfo.builder()
                .withSharesExperience(Questions.SharesExperience.FREQUENTLY.get())
                .withBinaryExperience(Questions.BinaryExperience.FREQUENTLY.get())
                .withAverageYearlyBinaryVolume(Questions.AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.get())
                .withForExExperience(Questions.ForExExperience.FREQUENTLY.get())
                .withAverageYearlyForExVolume(Questions.AverageYearlyForExVolume.VOLUME_50K_150K.get())
                .withCommonLeverage(Questions.CommonLeverageForExVolume.LEVERAGE_1TO50_1TO200.get())
                .withFinancialWorkExperience(Questions.FinancialWorkExperience.WORKED.get())
                .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.SPECULATIVE.get())
                .withMainFactorKnowledge(Questions.MainFactorKnowledge.INTEREST_RATES.get())
                .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.ONLY_PLATFORM.get())
                .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.MAGNIFIES.get())
                .withStopLossKnowledge(Questions.StopLossKnowledge.MINIMIZE.get())
                .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_1K.get())
                .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.MARGIN_CALL.get())
                .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EARNINGS.get())
                .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A2_450.get())
                .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A1_1800.get())
                .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_60.get())
                .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_75.get())
                .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_25.get())
                .build();
    }

    /**
     * Build trading experience info with 85 score
     * @return - TradingExperienceInfo instance
     */
    private TradingExperienceInfo tradingExperienceInfoWith85Score() {
        return TradingExperienceInfo.builder()
                .withSharesExperience(Questions.SharesExperience.FREQUENTLY.get())
                .withBinaryExperience(Questions.BinaryExperience.FREQUENTLY.get())
                .withAverageYearlyBinaryVolume(Questions.AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.get())
                .withForExExperience(Questions.ForExExperience.FREQUENTLY.get())
                .withAverageYearlyForExVolume(Questions.AverageYearlyForExVolume.VOLUME_50K_150K.get())
                .withCommonLeverage(Questions.CommonLeverageForExVolume.LEVERAGE_1TO50_1TO200.get())
                .withFinancialWorkExperience(Questions.FinancialWorkExperience.WORKED.get())
                .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.SPECULATIVE.get())
                .withMainFactorKnowledge(Questions.MainFactorKnowledge.INTEREST_RATES.get())
                .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.LONDON_STOCK.get())
                .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.MAGNIFIES.get())
                .withStopLossKnowledge(Questions.StopLossKnowledge.MINIMIZE.get())
                .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_1K.get())
                .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.MARGIN_CALL.get())
                .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EQUITY_FALLS.get())
                .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A1_800.get())
                .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A1_1800.get())
                .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_75.get())
                .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_100.get())
                .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_25.get())
                .build();
    }

    /**
     * Build trading experience info with 100 score
     * @return - TradingExperienceInfo instance
     */
    private TradingExperienceInfo tradingExperienceInfoWith100Score() {
        return  TradingExperienceInfo.builder()
                .withSharesExperience(Questions.SharesExperience.FREQUENTLY.get())
                .withBinaryExperience(Questions.BinaryExperience.FREQUENTLY.get())
                .withAverageYearlyBinaryVolume(Questions.AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.get())
                .withForExExperience(Questions.ForExExperience.FREQUENTLY.get())
                .withAverageYearlyForExVolume(Questions.AverageYearlyForExVolume.VOLUME_50K_150K.get())
                .withCommonLeverage(Questions.CommonLeverageForExVolume.LEVERAGE_1TO50_1TO200.get())
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
                .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A1_1800.get())
                .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_75.get())
                .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_100.get())
                .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_25.get())
                .build();
    }
}
