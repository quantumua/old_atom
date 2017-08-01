package com.betamedia.atom.testslibrary.option24.end2end.crm.experienceLevels;

import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions;
import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import org.testng.annotations.Test;
import static com.betamedia.atom.core.api.crm.form.entities.QuestionnaireAnswers.*;

/**
 * Created by vsnigur on 5/25/17.
 */
public class RejectedUserTest extends AbstractUserExperienceTest {

    /**
     * - Create user via mobile API;
     * - Update as needed answers in the trading experience crm widgets page;
     * - Update personal score in the personal information crm widgets page;
     * - Check experience score in the DB;
     * - Check that user is rejected;
     */
    @Test(description = "ID:9025")
    public void checkCustomerWithScore0IsRejectedTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(tradingExperienceInfoWith0Score());
        pages().navigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());

        operations().onBoardingOperations().assertUsernameScore(customer.getUserName(),Double.valueOf(0));
        operations().onBoardingOperations().assertUsernameRejected(customer.getUserName());
    }

    /**
     * - Create user via mobile API;
     * - Update as needed answers in the trading experience crm widgets page;
     * - Update personal score in the personal information crm widgets page;
     * - Check experience score in the DB;
     * - Check that user is rejected;
     */
    @Test(description = "ID:9069")
    public void checkCustomerWithScore10IsRejectedTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(tradingExperienceInfoWith10Score());
        pages().navigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());

        operations().onBoardingOperations().assertUsernameScore(customer.getUserName(),Double.valueOf(10));
        operations().onBoardingOperations().assertUsernameRejected(customer.getUserName());
    }

    /**
     * - Create user via mobile API;
     * - Update as needed answers in the trading experience crm widgets page;
     * - Update personal score in the personal information crm widgets page;
     * - Check experience score in the DB;
     * - Check that user is rejected;
     */
    @Test(description = "ID:9026")
    public void checkCustomerWithScore15IsRejectedTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(tradingExperienceInfoWith15Score());
        pages().navigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());

        operations().onBoardingOperations().assertUsernameScore(customer.getUserName(),Double.valueOf(15));
        operations().onBoardingOperations().assertUsernameRejected(customer.getUserName());
    }

    /**
     * - Create user via mobile API;
     * - Update as needed answers in the trading experience crm widgets page;
     * - Update personal score in the personal information crm widgets page;
     * - Check that user access type is no login;
     */
    @Test(description = "ID:9019")
    public void checkRejectedCustomerAccessNoLoginTest() {
        CRMCustomer customer = crmRegisterAndLogIn();
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(tradingExperienceInfoWith0Score());
        pages().navigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());

        operations().onBoardingOperations().assertUsernameLoginType(
                customer.getUserName(),
                OnboardingWizardConditions.AccessType.NOLOGIN
        );
    }

    /**
     * Build trading experience info with 0 score
     * @return - TradingExperienceInfo instance
     */
    private TradingExperienceInfo tradingExperienceInfoWith0Score() {
        return TradingExperienceInfo.builder()
                .withSharesExperience(SharesExperience.NEVER)
                .withBinaryExperience(BinaryExperience.NEVER)
                .withForExExperience(ForExExperience.NEVER)
                .withFinancialWorkExperience(FinancialWorkExperience.NEITHER)
                .withCfdBinaryKnowledge(CfdBinaryKnowledge.NON_RISKY)
                .withMainFactorKnowledge(MainFactorKnowledge.ANNOUNCEMENT)
                .withHowToCloseKnowledge(HowToCloseKnowledge.LONDON_STOCK)
                .withCfdLeverageKnowledge(CfdLeverageKnowledge.PROVIDES)
                .withStopLossKnowledge(StopLossKnowledge.BUY)
                .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_10K)
                .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.WARNING_CALL)
                .withAutomaticStopKnowledge(AutomaticStopKnowledge.EARNINGS)
                .withLossOn1to50Knowledge(LossOn1to50Knowledge.A2_450)
                .withLossOn1to200Knowledge(LossOn1to200Knowledge.A2_1200)
                .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_60)
                .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_75)
                .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_35)
                .build();
    }

    /**
     * Build trading experience info with 10 score
     * @return - TradingExperienceInfo instance
     */
    private TradingExperienceInfo tradingExperienceInfoWith10Score() {
        return TradingExperienceInfo.builder()
                .withSharesExperience(SharesExperience.NEVER)
                .withBinaryExperience(BinaryExperience.NEVER)
                .withForExExperience(ForExExperience.NEVER)
                .withFinancialWorkExperience(FinancialWorkExperience.WORKED)
                .withCfdBinaryKnowledge(CfdBinaryKnowledge.NON_RISKY)
                .withMainFactorKnowledge(MainFactorKnowledge.ANNOUNCEMENT)
                .withHowToCloseKnowledge(HowToCloseKnowledge.LONDON_STOCK)
                .withCfdLeverageKnowledge(CfdLeverageKnowledge.PROVIDES)
                .withStopLossKnowledge(StopLossKnowledge.BUY)
                .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_10K)
                .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.WARNING_CALL)
                .withAutomaticStopKnowledge(AutomaticStopKnowledge.EARNINGS)
                .withLossOn1to50Knowledge(LossOn1to50Knowledge.A2_450)
                .withLossOn1to200Knowledge(LossOn1to200Knowledge.A1_1800)
                .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_75)
                .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_75)
                .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_35)
                .build();
    }

    /**
     * Build trading experience info with 15 score
     * @return - TradingExperienceInfo instance
     */
    private TradingExperienceInfo tradingExperienceInfoWith15Score() {
        return  TradingExperienceInfo.builder()
                .withSharesExperience(SharesExperience.NEVER)
                .withBinaryExperience(BinaryExperience.NEVER)
                .withForExExperience(ForExExperience.NEVER)
                .withFinancialWorkExperience(FinancialWorkExperience.NEITHER)
                .withCfdBinaryKnowledge(CfdBinaryKnowledge.NON_RISKY)
                .withMainFactorKnowledge(MainFactorKnowledge.ANNOUNCEMENT)
                .withHowToCloseKnowledge(HowToCloseKnowledge.LONDON_STOCK)
                .withCfdLeverageKnowledge(CfdLeverageKnowledge.PROVIDES)
                .withStopLossKnowledge(StopLossKnowledge.BUY)
                .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_10K)
                .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.WARNING_CALL)
                .withAutomaticStopKnowledge(AutomaticStopKnowledge.EARNINGS)
                .withLossOn1to50Knowledge(LossOn1to50Knowledge.A1_800)
                .withLossOn1to200Knowledge(LossOn1to200Knowledge.A1_1800)
                .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_75)
                .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_100)
                .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_25)
                .build();
    }
}
