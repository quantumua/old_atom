package com.betamedia.atom.testslibrary.option24.web.questionnaires;

import com.betamedia.atom.core.api.crm.form.entities.*;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;
import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import com.betamedia.atom.core.persistence.entities.RegulationAnswerExtensionBase;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import org.testng.annotations.Test;
import java.util.List;

/**
 * Created by vsnigur on 9/5/17.
 */
public class FunctionalityWebPart extends AbstractExperienceLevelsTests {

    private static final int EXPECTED_ANSWERS_COUNT = 22;

    /*
        Personal information
         */
    @Test(description = "CTW-5890:Questionnaires Free text box: boundary values validation")
    @TestLinkProperties(displayId = "CTW-5890")
    public void boundaryValuesValidationForPersonalInformationQuestions() {
        invokeSlideFNS();
        pages().fnsPersonalInformation().assertBoundaryFields(getPersonalInformationOtherAnswersMaxText());
    }

    /*
    Experience level
     */
    @Test(description = "CTW-5892:Questionnaires Free text box: boundary values validation")
    @TestLinkProperties(displayId = "CTW-5892")
    public void boundaryValuesValidationForExperienceLevels() {
        invokeSlideFNS();
        pages().fnsPersonalInformation().submit(getPersonalInformationScore0());
        pages().fnsTradingExperience().assertBoundaryFields(tradingExperienceInfoWith0Score());
    }

    /*
    AML Slide
    */
    @Test(description = "CTW-19119:Exit point absence")
    @TestLinkProperties(displayId = "CTW-19119")
    public void verifyAMLSlideExitPointAbsence() {
        invokeSlideFNS();
        PersonalInformation personalInformation = getPersonalInformationScore0();
        pages().fnsPersonalInformation().assertCloseNotExist(personalInformation);
        pages().fnsTradingExperience().assertCloseNotExist(tradingExperienceInfoWith0Score());
        pages().riskWarning().accept();
        pages().creditCardDepositDialog().submit(CreditCardDeposit.builder().build());
        pages().thankYouPage().doContinue();
        pages().fnsEmployerInfo().assertCloseNotExist(personalInformation);
        pages().fnsEmployerInfo().submit(personalInformation);
    }

    /*
    AML Slide
    */
    @Test(description = "CTW-19120:Slide load on page refresh")
    @TestLinkProperties(displayId = "CTW-19120")
    public void verifyAMLSlideLoadOnPageRefresh() {
        invokeSlideFNS();
        PersonalInformation personalInformation = getPersonalInformationScore0();
        pages().fnsPersonalInformation().assertCloseNotExist(personalInformation);
        pages().fnsTradingExperience().assertCloseNotExist(tradingExperienceInfoWith0Score());
        pages().riskWarning().accept();
        pages().creditCardDepositDialog().submit(CreditCardDeposit.builder().build());
        pages().thankYouPage().doContinue();
        pages().fnsEmployerInfo().assertPersonalInformationExists();
        pages().browser().refreshPage();
        pages().thankYouPage().doContinue();
        pages().fnsEmployerInfo().assertPersonalInformationExists();
    }

    /*
    AML Slide
    */
    @Test(description = "CTW-19121:Slide loads on trade")
    @TestLinkProperties(displayId = "CTW-19121")
    public void verifyAMLSlideLoadsOnTrade() {
        invokeSlideFNS();
        PersonalInformation personalInformation = getPersonalInformationScore0();
        pages().fnsPersonalInformation().assertCloseNotExist(personalInformation);
        pages().fnsTradingExperience().assertCloseNotExist(tradingExperienceInfoWith0Score());
        pages().riskWarning().accept();
        pages().creditCardDepositDialog().submit(CreditCardDeposit.builder().build());
        pages().thankYouPage().doContinue();
        pages().fnsEmployerInfo().assertPersonalInformationExists();
        pages().registrationDialog().clickLogo();
        pages().topNavigationPage().trade();
        pages().thankYouPage().doContinue();
        pages().fnsEmployerInfo().assertPersonalInformationExists();
    }

    /*
    AML Slide
    */
    @Test(description = "CTW-19122:Slide loads on deposit")
    @TestLinkProperties(displayId = "CTW-19122")
    public void verifyAMLSlideLoadsOnDeposit() {
        invokeSlideFNS();
        PersonalInformation personalInformation = getPersonalInformationScore0();
        pages().fnsPersonalInformation().assertCloseNotExist(personalInformation);
        pages().fnsTradingExperience().assertCloseNotExist(tradingExperienceInfoWith0Score());
        pages().riskWarning().accept();
        pages().creditCardDepositDialog().submit(CreditCardDeposit.builder().build());
        pages().thankYouPage().doContinue();
        pages().fnsEmployerInfo().assertPersonalInformationExists();
        pages().registrationDialog().clickLogo();
        pages().topNavigationPage().deposit();
        pages().thankYouPage().doContinue();
        pages().fnsEmployerInfo().assertPersonalInformationExists();
    }

    /*
    AML Slide
    */
    @Test(description = "CTW-19125:Slide loads after deposit + thank you 2 is completed")
    @TestLinkProperties(displayId = "CTW-19125")
    public void verifyAMLSlideLoadsAfterDepositAndThankYouIsCompleted() {
        invokeSlideFNS();
        PersonalInformation personalInformation = getPersonalInformationScore0();
        pages().fnsPersonalInformation().assertCloseNotExist(personalInformation);
        pages().fnsTradingExperience().assertCloseNotExist(tradingExperienceInfoWith0Score());
        pages().riskWarning().accept();
        pages().creditCardDepositDialog().submit(CreditCardDeposit.builder().build());
        pages().thankYouPage().doContinue();
        pages().fnsEmployerInfo().assertPersonalInformationExists();
        pages().registrationDialog().clickLogo();
        pages().topNavigationPage().deposit();
        pages().thankYouPage().doContinue();
        pages().fnsEmployerInfo().assertPersonalInformationExists();
    }

    /*
    POI / POR Slide
    */
    @Test(description = "CTW-19126:POI / POR loads after AML")
    @TestLinkProperties(displayId = "CTW-19126")
    public void verifyPOIAndPORSlideLoadsAfterAMLSlide() {
        invokeSlideFNS();
        PersonalInformation personalInformation = getPersonalInformationScore0();
        pages().fnsPersonalInformation().assertCloseNotExist(personalInformation);
        pages().fnsTradingExperience().assertCloseNotExist(tradingExperienceInfoWith0Score());
        pages().riskWarning().accept();
        pages().creditCardDepositDialog().submit(CreditCardDeposit.builder().build());
        pages().thankYouPage().doContinue();
        pages().fnsEmployerInfo().submit(personalInformation);
        softAssert().assertTrue(pages().uploadDocumentDialog().exists(),"POI / POR did not appear");
    }

    /*
    POI / POR Slide
    */
    @Test(description = "CTW-19136:POI / POR function properly after AML")
    @TestLinkProperties(displayId = "CTW-19136")
    public void verifyPOIAndPORSlideFunctionProperlyAfterAML() {
        pages().fnsEmployerInfo().submit(invokePOIPORDialog());
        pages().uploadDocumentDialog().poiUploadIdCard();
        softAssert().assertTrue(pages().thankYouPage().startTradeExists(),"Thank you 1 did not appear");
    }

    /*
    POI / POR Slide
    */
    @Test(description = "CTW-19137:Thank you 1 loads after POI / POR")
    @TestLinkProperties(displayId = "CTW-19137")
    public void verifyThankYouLoadsAfterPOIandPOR() {
        pages().fnsEmployerInfo().submit(invokePOIPORDialog());
        pages().uploadDocumentDialog().poiUploadIdCard();
        softAssert().assertTrue(pages().thankYouPage().startTradeExists(),"Thank you 1 did not appear");
    }

    @Test(description = "CTW-5891:Free text box fields: data saved and updated in CRM (test depend on web test at first)")
    @TestLinkProperties(displayId = "CTW-5891")
    public void dataSavedAndUpdatedInCRM() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo
                .builder(WebSiteNamingStrategy.get()).build();
        PersonalInformation personalInformation = getPersonalInformationScore0();
        TradingExperienceInfo tradingExperienceInfo = tradingExperienceInfoWith0Score();
        invokeSlideFNS(customerRegistrationInfo);
        passQuestionnaire(getPersonalInformationScore0(), tradingExperienceInfoWith0Score());
        pages().riskWarning().accept();
        pages().creditCardDepositDialog().close();
        pages().confirmCloseMessage().acceptClose();
        assertAnswersInCRMDB(customerRegistrationInfo, personalInformation, tradingExperienceInfo);

    }
    private void assertAnswersInCRMDB(CustomerRegistrationInfo customerRegistrationInfo, PersonalInformation personalInformation, TradingExperienceInfo tradingExperienceInfo ) {
        List<RegulationAnswerExtensionBase> answers = operations().customerOperations()
                .findCustomerAnswers(operations().customerOperations()
                        .findByEmailAddress(customerRegistrationInfo.getEmail()).getContactId());
        softAssert().assertEquals(answers.size(), EXPECTED_ANSWERS_COUNT);
        checkAnswerExisting(answers, personalInformation.employmentStatus, personalInformation.industry,
                personalInformation.educationLevel,
                personalInformation.educationField,
                personalInformation.isPoliticallyExposed,
                personalInformation.sourceOfFunds,
                personalInformation.annualIncome,
                personalInformation.netWealth,
                personalInformation.expectedDepositsPerYear,
                personalInformation.purposeOfTrading,
                tradingExperienceInfo.financialWorkExperience,
                tradingExperienceInfo.cfdBinaryKnowledge,
                tradingExperienceInfo.mainFactorKnowledge,
                tradingExperienceInfo.howToCloseKnowledge,
                tradingExperienceInfo.requiredMarginKnowledge,
                tradingExperienceInfo.marginLevelDropKnowledge,
                tradingExperienceInfo.lossOn1to50Knowledge,
                tradingExperienceInfo.lossOn1to200Knowledge,
                tradingExperienceInfo.financialWorkExperience,
                tradingExperienceInfo.instrumentsTradedBefore,
                tradingExperienceInfo.frequencyPastTransactions,
                tradingExperienceInfo.volumePastTransaction,
                tradingExperienceInfo.commonLevelPastTransaction);

    }

    private void checkAnswerExisting(List<RegulationAnswerExtensionBase> answers, String... expectedAnswer) {
        for (String answerToDetect:expectedAnswer) {
            softAssert().assertNotNull(answers.stream()
                            .filter(answer-> answer.getAnswerKey().equalsIgnoreCase(answerToDetect))
                            .findFirst().orElse(null),
                    answerToDetect + " was not detected in the list");
        }
    }

    private void invokeSlideFNS() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo
                .builder(WebSiteNamingStrategy.get()).build();
        invokeSlideFNS(customerRegistrationInfo);
    }

    private void invokeSlideFNS(CustomerRegistrationInfo customerRegistrationInfo) {
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(customerRegistrationInfo);
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
    }


    private PersonalInformation invokePOIPORDialog() {
        invokeSlideFNS();
        PersonalInformation personalInformation = getPersonalInformationScore0();
        pages().fnsPersonalInformation().assertCloseNotExist(personalInformation);
        pages().fnsTradingExperience().assertCloseNotExist(tradingExperienceInfoWith0Score());
        pages().riskWarning().accept();
        pages().creditCardDepositDialog().submit(CreditCardDeposit.builder().build());
        pages().thankYouPage().doContinue();
        return personalInformation;
    }
}
