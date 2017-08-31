package com.betamedia.atom.testslibrary.option24.end2end.bmw;

import com.betamedia.atom.core.api.crm.form.entities.*;
import com.betamedia.atom.core.testingtype.web.WebEndToEndTest;
import org.testng.Reporter;
import static com.betamedia.atom.core.api.crm.form.entities.QuestionnaireAnswers.*;

/**
 * Created by Oleksandr Losiev on 5/29/17.
 */
public class AbstractOnboardingConditionsTest extends WebEndToEndTest {

    public static final String EMPLOYER_NAME = "testEmployer";
    public static final String TAX_RESIDENCE_COUNTRY = "DE";
    public static final String TAX_IDENTIFICATION_NUMBER = "123456789";
    public static final String SOCIAL_SECURITY_NUMBER = "1234567890";

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

            pages().uploadDocumentDialog().poiUploadIdCard();
            return;
        }
        pages().startTradeDialog().startTrade();
    }

    public PersonalInformation getPersonalInformation() {
        return PersonalInformation.builder()
                .withEmploymentStatus(EmploymentStatus.SALARIED_EMPLOYEE)
                .withIndustry(Industry.FINANCE)
                .withEmployerName(EMPLOYER_NAME)
                .withTaxResidenceCountry(TAX_RESIDENCE_COUNTRY)
                .withUSReportabilityStatus(IsUSReportable.NO)
                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.NO)
                .withTaxIdentificationNumber(TAX_IDENTIFICATION_NUMBER)
                .withSocialSecurityNumber(SOCIAL_SECURITY_NUMBER)
                .withEducationLevel(EducationLevel.POST_GRADUATE)
                .withEducationField(EducationField.ACCOUNTING)
                .withPoliticalExposureStatus(IsPoliticallyExposed.NO)
                .withSourceOfFunds(SourceOfFunds.EMPLOYMENT)
                .withAnnualIncome(AnnualIncome.INCOME_OVER_100K)
                .withNetWealth(NetWealth.NET_WEALTH_OVER_300K)
                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_OVER_50K)
                .withPurposeOfTrading(PurposeOfTrading.ADDITIONAL_INCOME)
                .build();
    }

    public TradingExperienceInfo getTradingExperienceInfo(){
        return TradingExperienceInfo.builder()
                .withSharesExperience(SharesExperience.NEVER)
                .withBinaryExperience(BinaryExperience.OCCASIONALLY)
                .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_ABOVE_10K)
                .withForExExperience(ForExExperience.FREQUENTLY)
                .withFinancialWorkExperience(FinancialWorkExperience.WORKED)
                .withCfdBinaryKnowledge(CfdBinaryKnowledge.SPECULATIVE)
                .withMainFactorKnowledge(MainFactorKnowledge.ANNOUNCEMENT)
                .withHowToCloseKnowledge(HowToCloseKnowledge.LONDON_STOCK)
                .withCfdLeverageKnowledge(CfdLeverageKnowledge.PROVIDES)
                .withStopLossKnowledge(StopLossKnowledge.MINIMIZE)
                .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K)
                .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.WARNING_CALL)
                .withAutomaticStopKnowledge(AutomaticStopKnowledge.EARNINGS)
                .withLossOn1to50Knowledge(LossOn1to50Knowledge.A1_800)
                .withLossOn1to200Knowledge(LossOn1to200Knowledge.A1_1800)
                .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_75)
                .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_75)
                .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_35)

                .withInstrumentsTradedBefore(QuestionnaireAnswers.InstrumentsTradedBefore.NO_EXPERIENCE)
                .withFrequencyPastTransactions(QuestionnaireAnswers.FrequencyPastTransactions.OCCASIONALLY)
                .withVolumePastTransaction(QuestionnaireAnswers.VolumePastTransaction.LESS_THAN_10)
                .withCommonLevelPastTransaction(QuestionnaireAnswers.CommonLevelPastTransaction.LOWER_THAN_1_50)
                .build();
    }

    private void passPersonalQuestionnaire() {
        pages().fnsPersonalInformation().submit(getPersonalInformation());
    }

    private void passTradingQuestionnaire() {
        pages().fnsTradingExperience().submit(getTradingExperienceInfo());
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