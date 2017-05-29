package com.betamedia.qe.af.testslibrary.option24.end2end.crm;

import com.betamedia.qe.af.core.api.crm.form.builders.TradingExperienceInfoBuilder;
import com.betamedia.qe.af.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.qe.af.testslibrary.option24.end2end.crm.newQuestionnaries.Questions;
import org.testng.annotations.Test;

/**
 * Created by vsnigur on 5/25/17.
 */
public class ExpertUserTest extends AbstractUserExperienceTest {

    private CRMCustomer crmRegisterAndLogIn(){
        CRMCustomer customer = operations().customerOperations().register();
        pages().crmNavigation().login();
        pages().crmLoginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
        return customer;
    }

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

    private TradingExperienceInfoBuilder.TradingExperienceInfo tradingExperienceInfoWith71Score() {
        return new TradingExperienceInfoBuilder()
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
    private TradingExperienceInfoBuilder.TradingExperienceInfo tradingExperienceInfoWith85Score() {
        return new TradingExperienceInfoBuilder()
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

    private TradingExperienceInfoBuilder.TradingExperienceInfo tradingExperienceInfoWith100Score() {
        return  new TradingExperienceInfoBuilder()
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
