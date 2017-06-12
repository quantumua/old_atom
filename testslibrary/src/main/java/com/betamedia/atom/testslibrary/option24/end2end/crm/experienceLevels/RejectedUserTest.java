package com.betamedia.atom.testslibrary.option24.end2end.crm.experienceLevels;

import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.testslibrary.option24.end2end.crm.newQuestionnaries.Questions;
import org.testng.annotations.Test;

/**
 * Created by vsnigur on 5/25/17.
 */
public class RejectedUserTest extends AbstractUserExperienceTest {

    private CRMCustomer crmRegisterAndLogIn(){
        CRMCustomer customer = operations().customerOperations().register();
        pages().crmNavigation().login();
        pages().crmLoginPage().login(customer.getUserName(), CustomerRO.CustomerROBuilder.DEFAULT_PASSWORD);
        return customer;
    }

    @Test(description = "ID:9025")
    public void checkCustomerWithScore0IsRejectedTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(tradingExperienceInfoWith0Score());
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());

        operations().onBoardingOperations().assertUsernameScore(customer.getUserName(),Double.valueOf(0));
        operations().onBoardingOperations().assertUsernameRejected(customer.getUserName());
    }

    @Test(description = "ID:9069")
    public void checkCustomerWithScore10IsRejectedTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(tradingExperienceInfoWith10Score());
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());

        operations().onBoardingOperations().assertUsernameScore(customer.getUserName(),Double.valueOf(10));
        operations().onBoardingOperations().assertUsernameRejected(customer.getUserName());
    }

    @Test(description = "ID:9026")
    public void checkCustomerWithScore15IsRejectedTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(tradingExperienceInfoWith15Score());
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());

        operations().onBoardingOperations().assertUsernameScore(customer.getUserName(),Double.valueOf(15));
        operations().onBoardingOperations().assertUsernameRejected(customer.getUserName());
    }

    @Test(description = "ID:9019")
    public void checkRejectedCustomerAccessNoLoginTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(tradingExperienceInfoWith0Score());
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());

        operations().onBoardingOperations().assertUsernameLoginType(
                customer.getUserName(),
                OnboardingWizardConditions.AccessType.NOLOGIN
        );
    }

    private TradingExperienceInfo tradingExperienceInfoWith0Score() {
        return TradingExperienceInfo.builder()
                .withSharesExperience(Questions.SharesExperience.NEVER.get())
                .withBinaryExperience(Questions.BinaryExperience.NEVER.get())
                .withForExExperience(Questions.ForExExperience.NEVER.get())
                .withFinancialWorkExperience(Questions.FinancialWorkExperience.NEITHER.get())
                .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.NON_RISKY.get())
                .withMainFactorKnowledge(Questions.MainFactorKnowledge.ANNOUNCEMENT.get())
                .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.LONDON_STOCK.get())
                .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.PROVIDES.get())
                .withStopLossKnowledge(Questions.StopLossKnowledge.BUY.get())
                .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_10K.get())
                .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.WARNING_CALL.get())
                .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EARNINGS.get())
                .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A2_450.get())
                .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A2_1200.get())
                .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_60.get())
                .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_75.get())
                .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_35.get())
                .build();
    }
    private TradingExperienceInfo tradingExperienceInfoWith10Score() {
        return TradingExperienceInfo.builder()
                .withSharesExperience(Questions.SharesExperience.NEVER.get())
                .withBinaryExperience(Questions.BinaryExperience.NEVER.get())
                .withForExExperience(Questions.ForExExperience.NEVER.get())
                .withFinancialWorkExperience(Questions.FinancialWorkExperience.WORKED.get())
                .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.NON_RISKY.get())
                .withMainFactorKnowledge(Questions.MainFactorKnowledge.ANNOUNCEMENT.get())
                .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.LONDON_STOCK.get())
                .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.PROVIDES.get())
                .withStopLossKnowledge(Questions.StopLossKnowledge.BUY.get())
                .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_10K.get())
                .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.WARNING_CALL.get())
                .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EARNINGS.get())
                .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A2_450.get())
                .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A1_1800.get())
                .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_75.get())
                .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_75.get())
                .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_35.get())
                .build();
    }

    private TradingExperienceInfo tradingExperienceInfoWith15Score() {
        return  TradingExperienceInfo.builder()
                .withSharesExperience(Questions.SharesExperience.NEVER.get())
                .withBinaryExperience(Questions.BinaryExperience.NEVER.get())
                .withForExExperience(Questions.ForExExperience.NEVER.get())
                .withFinancialWorkExperience(Questions.FinancialWorkExperience.NEITHER.get())
                .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.NON_RISKY.get())
                .withMainFactorKnowledge(Questions.MainFactorKnowledge.ANNOUNCEMENT.get())
                .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.LONDON_STOCK.get())
                .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.PROVIDES.get())
                .withStopLossKnowledge(Questions.StopLossKnowledge.BUY.get())
                .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_10K.get())
                .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.WARNING_CALL.get())
                .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EARNINGS.get())
                .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A1_800.get())
                .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A1_1800.get())
                .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_75.get())
                .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_100.get())
                .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_25.get())
                .build();
    }
}