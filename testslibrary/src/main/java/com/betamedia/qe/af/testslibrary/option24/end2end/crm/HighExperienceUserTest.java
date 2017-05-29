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
public class HighExperienceUserTest extends AbstractUserExperienceTest {

    private CRMCustomer crmRegisterAndLogIn(){
        CRMCustomer customer = operations().customerOperations().register();
        pages().crmNavigation().login();
        pages().crmLoginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
        return customer;
    }

    @Test(description = "ID:9033")
    public void checkCustomerWithScore51IsHighExperienceTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(tradingExperienceInfoWith51Score());
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());

        operations().onBoardingOperations().assertUsernameScore(customer.getUserName(),Double.valueOf(51));
        operations().onBoardingOperations().assertUsernameHighExperience(customer.getUserName());
    }

    @Test(description = "ID:9072")
    public void checkCustomerWithScore65IsHighExperienceTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(tradingExperienceInfoWith65Score());
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());

        operations().onBoardingOperations().assertUsernameScore(customer.getUserName(),Double.valueOf(65));
        operations().onBoardingOperations().assertUsernameHighExperience(customer.getUserName());
    }

    @Test(description = "ID:9034")
    public void checkCustomerWithScore70IsHighExperienceTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(tradingExperienceInfoWith70Score());
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());

        operations().onBoardingOperations().assertUsernameScore(customer.getUserName(),Double.valueOf(70));
        operations().onBoardingOperations().assertUsernameHighExperience(customer.getUserName());
    }

    @Test(description = "ID:9024")
    public void checkHighExperienceCustomerAccessActiveTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(tradingExperienceInfoWith51Score());
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());

        operations().onBoardingOperations().assertUsernameLoginType(
                customer.getUserName(),
                OnboardingWizardConditions.AccessType.NOTRADE.getType()
        );
    }

    private TradingExperienceInfoBuilder.TradingExperienceInfo tradingExperienceInfoWith51Score() {
        return new TradingExperienceInfoBuilder()
                .withSharesExperience(Questions.SharesExperience.FREQUENTLY.get())
                .withBinaryExperience(Questions.BinaryExperience.NEVER.get())
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
                .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EARNINGS.get())
                .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A1_800.get())
                .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A1_1800.get())
                .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_60.get())
                .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_75.get())
                .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_35.get())
                .build();
    }
    private TradingExperienceInfoBuilder.TradingExperienceInfo tradingExperienceInfoWith65Score() {
        return new TradingExperienceInfoBuilder()
                .withSharesExperience(Questions.SharesExperience.FREQUENTLY.get())
                .withBinaryExperience(Questions.BinaryExperience.NEVER.get())
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
                .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_60.get())
                .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_100.get())
                .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_25.get())
                .build();
    }

    private TradingExperienceInfoBuilder.TradingExperienceInfo tradingExperienceInfoWith70Score() {
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
                .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.LONDON_STOCK.get())
                .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.MAGNIFIES.get())
                .withStopLossKnowledge(Questions.StopLossKnowledge.MINIMIZE.get())
                .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_1K.get())
                .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.MARGIN_CALL.get())
                .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EQUITY_FALLS.get())
                .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A2_450.get())
                .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A1_1800.get())
                .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_75.get())
                .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_75.get())
                .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_35.get())
                .build();
    }
}
