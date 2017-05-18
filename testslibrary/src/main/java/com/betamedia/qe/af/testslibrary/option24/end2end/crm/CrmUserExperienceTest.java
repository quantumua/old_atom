package com.betamedia.qe.af.testslibrary.option24.end2end.crm;

import com.betamedia.qe.af.core.api.crm.form.builders.PersonalInformationBuilder;
import com.betamedia.qe.af.core.api.crm.form.builders.TradingExperienceInfoBuilder;
import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.testingtype.tp.TPResourceAwareEndToEndTest;
import org.testng.annotations.Test;

/**
 * Created by mbelyaev on 5/17/17.
 */
public class CrmUserExperienceTest extends TPResourceAwareEndToEndTest {
    private static final String USERNAME = "qetest@qe.test";
    private final static String PASSWORD = CustomerBuilder.PASSWORD;

    @Test
    public void experienceInputTest() {
        pages().crmNavigation().login();
        pages().crmLoginPage().login(USERNAME, PASSWORD);
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit
                (new TradingExperienceInfoBuilder()
                        .withSharesExperience("sharesRegularly")
                        .withBinaryExperience("binaryRegularly")
                        .withAverageYearlyBinaryVolume("financialProducts1Volume5k-10k")
                        .withForExExperience("forexRegularly")
                        .withAverageYearlyForExVolume("financialProducts2Volume50k-150k")
                        .withCommonLeverage("financialProducts2Leverage1to50-1to200")
                        .withFinancialWorkExperience("selectIfApplicableWorked")
                        .withCfdBinaryKnowledge("knowledgeCfdsBinariesSpeculative")
                        .withMainFactorKnowledge("knowledgeMainFactorInterestRates")
                        .withHowToCloseKnowledge("knowledgeIfYouOpenOnlyPlatform")
                        .withCfdLeverageKnowledge("knowledgeLeverageCFDMagnifies")
                        .withStopLossKnowledge("knowledgeStoplossMinimize")
                        .withRequiredMarginKnowledge("knowledgeRequiredMargin1k")
                        .withMarginLevelDropKnowledge("knowledgeMarginLevelDrop")
                        .withAutomaticStopKnowledge("knowledgeAutomaticStopEquityFalls")
                        .withLossOn1to50Knowledge("knowledgePositionLoss1to50A2")
                        .withLossOn1to200Knowledge("knowledgePositionLoss1to200A1")
                        .withBinaryInvestProfitKnowledge("knowledgeInvestBinaryProfit75")
                        .withBinaryInvestLossKnowledge("knowledgeInvestBinaryLoss100")
                        .withBinaryProbabilityKnowledge("knowledgeBinaryInTheMoney25")
                        .build()
                );
    }

    @Test
    public void informationInputTest() {
        pages().crmNavigation().login();
        pages().crmLoginPage().login(USERNAME, PASSWORD);
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit
                (new PersonalInformationBuilder()
                        .withEmploymentStatus("employmentStatusSalariedEmployee")
                        .withIndustry("industryFinance")
                        .withEmployerName("fgsfds")
                        .withTaxResidenceCountry("JP")
                        .withUSReportabilityStatus("usReportableYes")
                        .withTaxIdentificationNumberStatus("tinNo")
                        .withSocialSecurityNumber("123456789")
                        .withEducationLevel("levelOfEducationPostGraduate")
                        .withEducationField("fieldOfStudyAccounting")
                        .withPoliticalExposureStatus("politicallyExposedNo")
                        .withSourceOfFunds("sourceOfFundsEmployment")
                        .withAnnualIncome("totalAnnualIncomeOver100k")
                        .withNetWealth("netWealthOver300k")
                        .withExpectedDepositsPerYear("expectedDepositsOver50k")
                        .withPurposeOfTrading("purposeOfTradingAdditionalIncome")
                        .build()
                );
    }
}
