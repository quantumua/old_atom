package com.betamedia.atom.testslibrary.option24.end2end.crm;

import com.betamedia.atom.core.api.crm.form.entities.*;
import com.betamedia.atom.core.testingtype.tp.TPEndToEndTest;
import com.betamedia.atom.testslibrary.option24.end2end.crm.newQuestionnaries.Questions;
import org.testng.Reporter;

/**
 * Created by Oleksandr Losiev on 5/29/17.
 */
public class AbstractOnboardingConditionsTest extends TPEndToEndTest {

    protected void verifyResultingSlidesShown(OnboardingWizardConditions conditions) {
        if (!conditions.isShowWizard()) {
            pages().browser().takeScreenShot();
            Reporter.log("INFO: ShowWizard should happens.");
            return;
        }

        if (conditions.isShowWelcome()) {
            pages().browser().takeScreenShot();
            Reporter.log("INFO: ShowWelcome should happens.");
            pages().welcomePage().start();
        }

        if (conditions.isShowWelcomeBack()) {
            pages().browser().takeScreenShot();
            Reporter.log("INFO: ShowWelcomeBack should happens.");
            pages().welcomeBackMessage().continueQuestionnaire();
        }

        if (conditions.isShowRiskWarning()) {
            pages().browser().takeScreenShot();
            Reporter.log("INFO: ShowRiskWarning should happens.");
            pages().riskWarningPage().accept();
        }

        if (conditions.isShowAdditionalDetails()) {
            pages().browser().takeScreenShot();
            Reporter.log("INFO: ShowAdditionalDetails should happens.");
            pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        }

        if (conditions.isShowFnsPersonal()) {
            pages().browser().takeScreenShot();
            Reporter.log("INFO: ShowFnsPersonal should happens.");
            passPersonalQuestionnaire();
        }

        if (conditions.isShowFnsTrading()) {
            pages().browser().takeScreenShot();
            Reporter.log("INFO: ShowFnsTrading should happens.");
            passTradingQuestionnaire();
        }

        if (conditions.isShowDeposit()) {
            pages().browser().takeScreenShot();
            Reporter.log("INFO: ShowDeposit should happens.");
            pages().creditCardDeposit().submit(CreditCardDeposit.builder().build());
        }

        if (conditions.isShowPoiPor()) {
            pages().browser().takeScreenShot();
            Reporter.log("INFO: ShowPoiPor should happens.");
            pages().onBoardingWizard().assertOnPOI();
            return;
        }

        pages().onBoardingWizard().confirmMessage();
    }

    private void passPersonalQuestionnaire() {
        pages().fnsPersonalInformation().submitOnWizard(PersonalInformation.builder()
                .withEmploymentStatus(Questions.EmploymentStatus.SALARIED_EMPLOYEE.get())
                .withIndustry(Questions.Industry.ACCOUNTING.get())
                .withEmployerName("testEmployer")
                .withTaxResidenceCountry("AF")
                .withTaxIdentificationNumberStatus(Questions.HasTaxIdentificationNumber.YES.get())
                .withTaxIdentificationNumber("1111111")
                .withUSReportabilityStatus(Questions.IsUSReportable.NO.get())
                .withEducationLevel(Questions.EducationLevel.POST_GRADUATE.get())
                .withEducationField(Questions.EducationField.ACCOUNTING.get())
                .withPoliticalExposureStatus(Questions.IsPoliticallyExposed.NO.get())
                .withSourceOfFunds(Questions.SourceOfFunds.EMPLOYMENT.get())
                .withAnnualIncome(Questions.AnnualIncome.INCOME_OVER_100K.get())
                .withNetWealth(Questions.NetWealth.NET_WEALTH_OVER_300K.get())
                .withExpectedDepositsPerYear(Questions.ExpectedDepositsPerYear.DEPOSITS_OVER_50K.get())
                .withPurposeOfTrading(Questions.PurposeOfTrading.SPECULATIVE.get())
                .build()
        );
    }

    private void passTradingQuestionnaire() {
        pages().fnsTradingExperience().submitOnWizard(TradingExperienceInfo.builder()
                .withSharesExperience(Questions.SharesExperience.FREQUENTLY.get())
                .withBinaryExperience(Questions.BinaryExperience.FREQUENTLY.get())
                .withAverageYearlyBinaryVolume(Questions.AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.get())
                .withForExExperience(Questions.ForExExperience.FREQUENTLY.get())
                .withAverageYearlyForExVolume(Questions.AverageYearlyForExVolume.VOLUME_ABOVE_500K.get())
                .withCommonLeverage(Questions.CommonLeverage.LEVERAGE_ABOVE_1TO500.get())
                .withFinancialWorkExperience(Questions.FinancialWorkExperience.SEMINARS.get())
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
                .build()
        );
    }

    protected boolean checkIfLeveragePopupWillBeShown(String username) {
        return operations().customerOperations().getCustomerLeverageByUsername(username) == 0;
    }

    @Override
    protected Class getDataSourceEntity() {
        return OnboardingWizardConditions.class;
    }

    @Override
    protected String getDataSourcePath() {
        return "/data/demoWizardTestCases.csv";
    }
}
