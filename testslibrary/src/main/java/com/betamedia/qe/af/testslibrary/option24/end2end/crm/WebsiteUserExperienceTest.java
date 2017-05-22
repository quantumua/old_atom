package com.betamedia.qe.af.testslibrary.option24.end2end.crm;

import com.betamedia.qe.af.core.api.crm.form.builders.TradingExperienceInfoBuilder;
import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.response.CRMCustomer;
import org.testng.annotations.Test;

/**
 * Created by mbelyaev on 5/17/17.
 */
public class WebsiteUserExperienceTest extends AbstractUserExperienceTest {

    @Test
    public void score31CustomerExperienceTest() {
        pages().crmNavigation().register();
        CustomerBuilder.CustomerRO customer = new CustomerBuilder().createCustomerRO();
        pages().register().register(customer);
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(
                new TradingExperienceInfoBuilder()
                        .withSharesExperience("sharesNever")
                        .withBinaryExperience("binaryNever")
                        .withAverageYearlyBinaryVolume("financialProducts1VolumeAbove10k")
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
        operations().onBoardingOperations().assertUsernameLowExperience(customer.getEmail());
    }

    @Test
    public void score45CustomerExperienceTest() {
        pages().crmNavigation().register();
        CustomerBuilder.CustomerRO customer = new CustomerBuilder().createCustomerRO();
        pages().register().register(customer);
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
        operations().onBoardingOperations().assertUsernameLowExperience(customer.getEmail());
    }

    @Test
    public void score50CustomerExperienceTest() {
        pages().crmNavigation().register();
        CustomerBuilder.CustomerRO customer = new CustomerBuilder().createCustomerRO();
        pages().register().register(customer);
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
        pages().fnsPersonalInformation().submit(personalInfoScore5());
        operations().onBoardingOperations().assertUsernameLowExperience(customer.getEmail());
    }
}
