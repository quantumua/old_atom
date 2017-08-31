package com.betamedia.atom.testslibrary.option24.end2end.bmw;

import com.betamedia.atom.core.api.crm.form.entities.*;
import org.testng.Reporter;

/**
 * Created by Oleksandr Losiev on 5/29/17.
 */
public class AbstractOnboardingConditionsTest extends AbstractCustomerRegistrationTest {

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

    private void passPersonalQuestionnaire() {
        pages().fnsPersonalInformation().submit(getPersonalInformation());
    }

    private void passTradingQuestionnaire() {
        pages().fnsTradingExperience().submit(getTradingExperienceInfo());
        pages().confirmAnswers().next();
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