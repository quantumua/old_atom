package com.betamedia.qe.af.testslibrary.option24.end2end.crm;

import com.betamedia.qe.af.core.api.crm.form.builders.TradingExperienceInfoBuilder;
import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import org.testng.annotations.Test;

/**
 * Created by mbelyaev on 5/17/17.
 */
public class CrmUserExperienceTest extends AbstractUserExperienceTest {

    @Test
    public void newCustomerExperienceTest() {
        CRMCustomer customer = operations().customerOperations().register();
        pages().crmNavigation().login();
        pages().crmLoginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());
        operations().onBoardingOperations().assertCustomerUnknown(customer.getId());
    }

    @Test
    public void score10CustomerExperienceTest() {
        CRMCustomer customer = operations().customerOperations().register();
        pages().crmNavigation().login();
        pages().crmLoginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                new TradingExperienceInfoBuilder()
                        .withSharesExperience("sharesNever")
                        .withBinaryExperience("binaryNever")
                        .withAverageYearlyBinaryVolume("financialProducts1VolumeUnder500")
                        .withForExExperience("forexNever")
                        .withAverageYearlyForExVolume("financialProducts2VolumeUnder50k")
                        .withCommonLeverage("financialProducts2LeverageUnder1to50")
                        .withFinancialWorkExperience("selectIfApplicableNeither")
                        .withCfdBinaryKnowledge("knowledgeCfdsBinariesPhysicallyDelivering")
                        .withMainFactorKnowledge("knowledgeMainFactorAnnouncement")
                        .withHowToCloseKnowledge("knowledgeIfYouOpenLondonStock")
                        .withCfdLeverageKnowledge("knowledgeLeverageCFDNone")
                        .withStopLossKnowledge("knowledgeStoplossMinimize")
                        .withRequiredMarginKnowledge("knowledgeRequiredMargin10k")
                        .withMarginLevelDropKnowledge("knowledgeMarginLevelDropWarningCall")
                        .withAutomaticStopKnowledge("knowledgeAutomaticStopEarnings")
                        .withLossOn1to50Knowledge("knowledgePositionLoss1to50A3")
                        .withLossOn1to200Knowledge("knowledgePositionLoss1to200A3")
                        .withBinaryInvestProfitKnowledge("knowledgeInvestBinaryProfit100")
                        .withBinaryInvestLossKnowledge("knowledgeInvestBinaryLoss50")
                        .withBinaryProbabilityKnowledge("knowledgeBinaryInTheMoney45")
                        .build()
        );
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());
        operations().onBoardingOperations().assertCustomerRejected(customer.getId());
    }

    @Test
    public void score25CustomerExperienceTest() {
        CRMCustomer customer = operations().customerOperations().register();
        pages().crmNavigation().login();
        pages().crmLoginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                new TradingExperienceInfoBuilder()
                        .withSharesExperience("sharesNever")
                        .withBinaryExperience("binaryNever")
                        .withAverageYearlyBinaryVolume("financialProducts1VolumeUnder500")
                        .withForExExperience("forexNever")
                        .withAverageYearlyForExVolume("financialProducts2VolumeUnder50k")
                        .withCommonLeverage("financialProducts2LeverageUnder1to50")
                        .withFinancialWorkExperience("selectIfApplicableNeither")
                        .withCfdBinaryKnowledge("knowledgeCfdsBinariesPhysicallyDelivering")
                        .withMainFactorKnowledge("knowledgeMainFactorAnnouncement")
                        .withHowToCloseKnowledge("knowledgeIfYouOpenLondonStock")
                        .withCfdLeverageKnowledge("knowledgeLeverageCFDNone")
                        .withStopLossKnowledge("knowledgeStoplossBuy")
                        .withRequiredMarginKnowledge("knowledgeRequiredMargin1k")
                        .withMarginLevelDropKnowledge("knowledgeMarginLevelDropWarningCall")
                        .withAutomaticStopKnowledge("knowledgeAutomaticStopEquityFalls")
                        .withLossOn1to50Knowledge("knowledgePositionLoss1to50A2")
                        .withLossOn1to200Knowledge("knowledgePositionLoss1to200A3")
                        .withBinaryInvestProfitKnowledge("knowledgeInvestBinaryProfit75")
                        .withBinaryInvestLossKnowledge("knowledgeInvestBinaryLoss100")
                        .withBinaryProbabilityKnowledge("knowledgeBinaryInTheMoney25")
                        .build()
        );
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());
        operations().onBoardingOperations().assertCustomerNoExperience(customer.getId());
    }

    @Test
    public void score45CustomerExperienceTest() {
        CRMCustomer customer = operations().customerOperations().register();
        pages().crmNavigation().login();
        pages().crmLoginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                new TradingExperienceInfoBuilder()
                        .withSharesExperience("sharesRegularly")
                        .withBinaryExperience("binaryNever")
                        .withAverageYearlyBinaryVolume("financialProducts1VolumeUnder500")
                        .withForExExperience("forexNever")
                        .withAverageYearlyForExVolume("financialProducts2VolumeUnder50k")
                        .withCommonLeverage("financialProducts2LeverageUnder1to50")
                        .withFinancialWorkExperience("selectIfApplicableNeither")
                        .withCfdBinaryKnowledge("knowledgeCfdsBinariesSpeculative")
                        .withMainFactorKnowledge("knowledgeMainFactorInterestRates")
                        .withHowToCloseKnowledge("knowledgeIfYouOpenOnlyPlatform")
                        .withCfdLeverageKnowledge("knowledgeLeverageCFDMagnifies")
                        .withStopLossKnowledge("knowledgeStoplossMinimize")
                        .withRequiredMarginKnowledge("knowledgeRequiredMargin1k")
                        .withMarginLevelDropKnowledge("knowledgeMarginLevelDropWarningCall")
                        .withAutomaticStopKnowledge("knowledgeAutomaticStopEquityFalls")
                        .withLossOn1to50Knowledge("knowledgePositionLoss1to50A2")
                        .withLossOn1to200Knowledge("knowledgePositionLoss1to200A1")
                        .withBinaryInvestProfitKnowledge("knowledgeInvestBinaryProfit75")
                        .withBinaryInvestLossKnowledge("knowledgeInvestBinaryLoss100")
                        .withBinaryProbabilityKnowledge("knowledgeBinaryInTheMoney25")
                        .build()
        );
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore0());
        operations().onBoardingOperations().assertCustomerLowExperience(customer.getId());
    }

    @Test
    public void score65CustomerExperienceTest() {
        CRMCustomer customer = operations().customerOperations().register();
        pages().crmNavigation().login();
        pages().crmLoginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                new TradingExperienceInfoBuilder() //55
                        .withSharesExperience("sharesRegularly")
                        .withBinaryExperience("binaryFrequently")
                        .withForExExperience("forexFrequently")
                        .withFinancialWorkExperience("selectIfApplicableNeither")
                        .withCfdBinaryKnowledge("knowledgeCfdsBinariesSpeculative")
                        .withMainFactorKnowledge("knowledgeMainFactorInterestRates")
                        .withHowToCloseKnowledge("knowledgeIfYouOpenOnlyPlatform")
                        .withCfdLeverageKnowledge("knowledgeLeverageCFDMagnifies")
                        .withStopLossKnowledge("knowledgeStoplossMinimize")
                        .withRequiredMarginKnowledge("knowledgeRequiredMargin1k")
                        .withMarginLevelDropKnowledge("knowledgeMarginLevelDropWarningCall")
                        .withAutomaticStopKnowledge("knowledgeAutomaticStopEquityFalls")
                        .withLossOn1to50Knowledge("knowledgePositionLoss1to50A2")
                        .withLossOn1to200Knowledge("knowledgePositionLoss1to200A1")
                        .withBinaryInvestProfitKnowledge("knowledgeInvestBinaryProfit75")
                        .withBinaryInvestLossKnowledge("knowledgeInvestBinaryLoss100")
                        .withBinaryProbabilityKnowledge("knowledgeBinaryInTheMoney25")
                        .build()
        );
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore10());
        operations().onBoardingOperations().assertCustomerHighExperience(customer.getId());
    }

    @Test
    public void score85CustomerExperienceTest() {
        CRMCustomer customer = operations().customerOperations().register();
        pages().crmNavigation().login();
        pages().crmLoginPage().login(customer.getUserName(), CustomerBuilder.PASSWORD);
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                new TradingExperienceInfoBuilder() //67
                        .withSharesExperience("sharesFrequently")
                        .withBinaryExperience("binaryFrequently")
                        .withForExExperience("forexFrequently")
                        .withFinancialWorkExperience("selectIfApplicableBoth")
                        .withCfdBinaryKnowledge("knowledgeCfdsBinariesSpeculative")
                        .withMainFactorKnowledge("knowledgeMainFactorInterestRates")
                        .withHowToCloseKnowledge("knowledgeIfYouOpenOnlyPlatform")
                        .withCfdLeverageKnowledge("knowledgeLeverageCFDMagnifies")
                        .withStopLossKnowledge("knowledgeStoplossMinimize")
                        .withRequiredMarginKnowledge("knowledgeRequiredMargin1k")
                        .withMarginLevelDropKnowledge("knowledgeMarginLevelDropMarginCall")
                        .withAutomaticStopKnowledge("knowledgeAutomaticStopEquityFalls")
                        .withLossOn1to50Knowledge("knowledgePositionLoss1to50A1") ///???
                        .withLossOn1to200Knowledge("knowledgePositionLoss1to200A1")
                        .withBinaryInvestProfitKnowledge("knowledgeInvestBinaryProfit75")
                        .withBinaryInvestLossKnowledge("knowledgeInvestBinaryLoss100")
                        .withBinaryProbabilityKnowledge("knowledgeBinaryInTheMoney25")
                        .build()
        );
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfoScore10());
        operations().onBoardingOperations().assertCustomerExpert(customer.getId());
    }


}
