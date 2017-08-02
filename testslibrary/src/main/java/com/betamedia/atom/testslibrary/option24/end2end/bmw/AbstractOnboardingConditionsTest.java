package com.betamedia.atom.testslibrary.option24.end2end.bmw;

import com.betamedia.atom.core.api.crm.form.entities.*;
import com.betamedia.atom.core.testingtype.web.WebEndToEndTest;
import org.testng.Reporter;
import static com.betamedia.atom.core.api.crm.form.entities.QuestionnaireAnswers.*;

/**
 * Created by Oleksandr Losiev on 5/29/17.
 */
public class AbstractOnboardingConditionsTest extends WebEndToEndTest {

    protected void verifyResultingSlidesShown(OnboardingWizardConditions conditions) {
        if (!conditions.isShowWizard()) {
            Reporter.log("INFO: ShowWizard should happens.");
            return;
        }

        if (conditions.isShowWelcome()) {
            Reporter.log("INFO: ShowWelcome should happens.");
            pages().welcomeDialog().start();
        }

        if (conditions.isShowWelcomeBack()) {
            Reporter.log("INFO: ShowWelcomeBack should happens.");
            pages().welcomeBackMessage().continueQuestionnaire();
        }

        if (conditions.isShowAdditionalDetails()) {
            Reporter.log("INFO: ShowAdditionalDetails should happens.");
            pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        }

        if (conditions.isShowRiskWarning()) {
            Reporter.log("INFO: ShowRiskWarning should happens.");
            pages().riskWarningPage().accept();
        }

        if (conditions.isShowFnsPersonal()) {
            Reporter.log("INFO: ShowFnsPersonal should happens.");
            passPersonalQuestionnaire();
        }

        if (conditions.isShowFnsTrading()) {
            Reporter.log("INFO: ShowFnsTrading should happens.");
            passTradingQuestionnaire();
        }

        if (conditions.isShowDeposit()) {
            Reporter.log("INFO: ShowDeposit should happens.");
            pages().creditCardDeposit().submit(CreditCardDeposit.builder().build());
        }

        if (conditions.isShowPoiPor()) {
            Reporter.log("INFO: ShowPoiPor should happens.");

            pages().documentUploadForm().uploadIdCard();
            return;
        }
        pages().startTradeDialog().startTrade();
    }

    private void passPersonalQuestionnaire() {
        pages().fnsPersonalInformation().submit(PersonalInformation.builder()
                .withEmploymentStatus(EmploymentStatus.SALARIED_EMPLOYEE)
                .withIndustry(Industry.ACCOUNTING)
                .withEmployerName("testEmployer")
                .withTaxResidenceCountry("AF")
                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.YES)
                .withTaxIdentificationNumber("1111111")
                .withUSReportabilityStatus(IsUSReportable.NO)
                .withEducationLevel(EducationLevel.POST_GRADUATE)
                .withEducationField(EducationField.ACCOUNTING)
                .withPoliticalExposureStatus(IsPoliticallyExposed.NO)
                .withSourceOfFunds(SourceOfFunds.EMPLOYMENT)
                .withAnnualIncome(AnnualIncome.INCOME_OVER_100K)
                .withNetWealth(NetWealth.NET_WEALTH_OVER_300K)
                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_OVER_50K)
                .withPurposeOfTrading(PurposeOfTrading.SPECULATIVE)
                .build()
        );
    }

    private void passTradingQuestionnaire() {
        pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
                .withSharesExperience(SharesExperience.FREQUENTLY)
                .withBinaryExperience(BinaryExperience.FREQUENTLY)
                .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_ABOVE_10K)
                .withForExExperience(ForExExperience.FREQUENTLY)
                .withAverageYearlyForExVolume(AverageYearlyForExVolume.VOLUME_ABOVE_500K)
                .withCommonLeverage(CommonForExLeverage.LEVERAGE_ABOVE_1TO500)
                .withFinancialWorkExperience(FinancialWorkExperience.SEMINARS)
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
                .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_75)
                .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_100)
                .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_25)
                .build()
        );
        pages().confirmAnswers().next();
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
        return "/data/wizardTestCases.csv";
    }
}