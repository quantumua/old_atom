package com.betamedia.atom.testslibrary.option24.web.questionnaires;

import com.betamedia.atom.core.api.crm.form.entities.*;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;
import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import com.betamedia.atom.core.persistence.entities.RegulationAnswerExtensionBase;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vsnigur on 9/5/17.
 */
public class FunctionalityWebPart extends AbstractExperienceLevelsTests {

    private static final String[] PERSONAL_PERCENTAGES={"15","20","28","40","45","50","54","57","61","64"};
    private static final String[] EXPERIENCE_PERCENTAGES={"67","70","73","73","73","76","79","81","84","86","88","91"};
    private static final int EXPECTED_ANSWERS_COUNT = PERSONAL_PERCENTAGES.length+EXPERIENCE_PERCENTAGES.length;
    /*
        Personal information
    */
    @Test(description = "CTW-5890:Questionnaires Free text box: boundary values validation")
    @TestLinkProperties(displayId = "CTW-5890")
    public void boundaryValuesValidationForPersonalInformationQuestions() {
        invokeSlideFNS();
        pages().fnsPersonalInformation().assertBoundaryFields(getPersonalInformationOtherAnswersMaxText());
    }

    @Test(description = "CTW-5891:Free text box fields: data saved and updated in CRM (test depend on web test at first)")
    @TestLinkProperties(displayId = "CTW-5891")
    public void dataSavedAndUpdatedInCRM() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo
                .builder(WebSiteNamingStrategy.get()).build();
        PersonalInformation personalInformation = getPersonalInformationScore0();
        TradingExperienceInfo tradingExperienceInfo = tradingExperienceInfoWith0Score();
        invokeSlideFNS(customerRegistrationInfo);
        passQuestionnaire(personalInformation, tradingExperienceInfo);
        pages().riskWarning().accept();
        pages().creditCardDepositDialog().close();
        pages().confirmCloseMessage().acceptClose();
        assertAnswersInCRMDB(customerRegistrationInfo, personalInformation, tradingExperienceInfo);
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

    @Test(description = "CTW-5893:Free text box fields: data saved and updated in CRM")
    @TestLinkProperties(displayId = "CTW-5893")
    public void verifyFreeTextBoxFieldsDataValuesSavedAndUpdatedInCRM() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo
                .builder(WebSiteNamingStrategy.get()).build();
        PersonalInformation personalInformation = getPersonalInformationOtherAnswers();
        TradingExperienceInfo tradingExperienceInfo = getRejectedTradingExperienceInfo();
        invokeSlideFNS(customerRegistrationInfo);
        passQuestionnaire(personalInformation, tradingExperienceInfo);
        pages().riskWarning().accept();
        pages().creditCardDepositDialog().close();
        pages().confirmCloseMessage().acceptClose();
        assertAnswersInCRMDBOtherAnswers(customerRegistrationInfo, personalInformation, tradingExperienceInfo);
    }

    /*
    AML Slide
    */
    @Test(description = "CTW-19108:AML Slide loads properly")
    @TestLinkProperties(displayId = "CTW-19108")
    public void verifyAMLSlideLoadsProperly() {
        invokeSlideFNS();
        PersonalInformation personalInformation = getPersonalInformationScore0();
        pages().fnsPersonalInformation().assertCloseNotExist(personalInformation);
        pages().fnsTradingExperience().assertCloseNotExist(tradingExperienceInfoWith0Score());
        pages().riskWarning().accept();
        pages().creditCardDepositDialog().submit(CreditCardDeposit.builder().build());
        pages().thankYouPage().doContinue();
        pages().fnsEmployerInfo().assertPersonalInformationExists();
        pages().fnsEmployerInfo().submit(personalInformation);
    }

    @Test(description = "CTW-19418:AML questions")
    @TestLinkProperties(displayId = "CTW-19418")
    public void verifyAMLQuestions() {
        invokeSlideFNS();
        PersonalInformation personalInformation = getPersonalInformationScore0();
        pages().fnsPersonalInformation().assertCloseNotExist(personalInformation);
        pages().fnsTradingExperience().assertCloseNotExist(tradingExperienceInfoWith0Score());
        pages().riskWarning().accept();
        pages().creditCardDepositDialog().submit(CreditCardDeposit.builder().build());
        pages().thankYouPage().doContinue();
        pages().fnsEmployerInfo().submit(personalInformation);
    }

    @Test(description = "CTW-19118:Progress bar correct percentage")
    @TestLinkProperties(displayId = "CTW-19118")
    @Parameters({"expectedPersonalPercentages", "expectedExperiencePercentages"})
    public void verifyProgressBarCorrectPercentage(@Optional() String[] expectedPersonalPercentages,
                                                   @Optional() String[] expectedExperiencePercentages) {

        if (expectedPersonalPercentages==null) expectedPersonalPercentages = PERSONAL_PERCENTAGES;
        if (expectedExperiencePercentages==null) expectedExperiencePercentages = EXPERIENCE_PERCENTAGES;
        invokeSlideFNS();
        PersonalInformation personalInformation = getPersonalInformationScore0();
        pages().fnsPersonalInformation().assertProgressBar(personalInformation, expectedPersonalPercentages);
        pages().fnsTradingExperience().assertProgressBar(tradingExperienceInfoWith0Score(), expectedExperiencePercentages);

    }

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
        softAssert().assertTrue(pages().thankYouPage().startTradeExists(),"Thank you '1' did not appear");
    }

    @Test(description = "CTW-19138:Slide loads after skipping deposit")
    @TestLinkProperties(displayId = "CTW-19138")
    public void verifyAMLSlideLoadsAfterSkippingDeposit() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo
                .builder(WebSiteNamingStrategy.get()).build();
        PersonalInformation personalInformation = getPersonalInformationScore0();
        TradingExperienceInfo tradingExperienceInfo = tradingExperienceInfoWith0Score();
        invokeSlideFNS(customerRegistrationInfo);
        passQuestionnaire(personalInformation, tradingExperienceInfo);
        pages().riskWarning().accept();
        pages().creditCardDepositDialog().close();
        pages().confirmCloseMessage().acceptClose();
        pages().fnsEmployerInfo().assertPersonalInformationExists();
    }

    @Test(description = "CTW-19139:POI / POR loads after AML finished")
    @TestLinkProperties(displayId = "CTW-19139")
    public void verifyPOIPOURLoadsAfterSkipDepositAndAMLUpdate() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo
                .builder(WebSiteNamingStrategy.get()).build();
        PersonalInformation personalInformation = getPersonalInformationScore0();
        TradingExperienceInfo tradingExperienceInfo = tradingExperienceInfoWith0Score();
        invokeSlideFNS(customerRegistrationInfo);
        passQuestionnaire(personalInformation, tradingExperienceInfo);
        pages().riskWarning().accept();
        pages().creditCardDepositDialog().close();
        pages().confirmCloseMessage().acceptClose();
        pages().fnsEmployerInfo().submit(personalInformation);
        softAssert().assertTrue(pages().uploadDocumentDialog().exists(),"POI / POR did not appear after skip deposit");
    }

    @Test(description = "CTW-19140:poi / por function properly after AML slide")
    @TestLinkProperties(displayId = "CTW-19140")
    public void verifyPOIPORFunctionProperlyAfterSkipDepositAndFillAMLSlide() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo
                .builder(WebSiteNamingStrategy.get()).build();
        PersonalInformation personalInformation = getPersonalInformationScore0();
        TradingExperienceInfo tradingExperienceInfo = tradingExperienceInfoWith0Score();
        invokeSlideFNS(customerRegistrationInfo);
        passQuestionnaire(personalInformation, tradingExperienceInfo);
        pages().riskWarning().accept();
        pages().creditCardDepositDialog().close();
        pages().confirmCloseMessage().acceptClose();
        pages().fnsEmployerInfo().submit(personalInformation);
        pages().uploadDocumentDialog().poiUploadIdCard();
        softAssert().assertTrue(pages().thankYouPage().startTradeExists(),"'Thank you' two did not appear");
    }

    @Test(description = "CTW-19146:Thank you 2 loads once poi / por succeed")
    @TestLinkProperties(displayId = "CTW-19146")
    public void verifyThankYouTwoLoadsAfterSkipDepositAndPassAMLPassPOIPOR() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo
                .builder(WebSiteNamingStrategy.get()).build();
        PersonalInformation personalInformation = getPersonalInformationScore0();
        TradingExperienceInfo tradingExperienceInfo = tradingExperienceInfoWith0Score();
        invokeSlideFNS(customerRegistrationInfo);
        passQuestionnaire(personalInformation, tradingExperienceInfo);
        pages().riskWarning().accept();
        pages().creditCardDepositDialog().close();
        pages().confirmCloseMessage().acceptClose();
        pages().fnsEmployerInfo().submit(personalInformation);
        pages().uploadDocumentDialog().poiUploadIdCard();
        softAssert().assertTrue(pages().thankYouPage().startTradeExists(),"'Thank you' did not appear");
    }

    @Test(description = "CTW-19147:Deposit slide loads when clicking deposit")
    @TestLinkProperties(displayId = "CTW-19147")
    public void verifyDepositSlideLoadsWhenClickingDepositButton() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo
                .builder(WebSiteNamingStrategy.get()).build();
        PersonalInformation personalInformation = getPersonalInformationScore0();
        TradingExperienceInfo tradingExperienceInfo = tradingExperienceInfoWith0Score();
        invokeSlideFNS(customerRegistrationInfo);
        passQuestionnaire(personalInformation, tradingExperienceInfo);
        pages().riskWarning().accept();
        pages().creditCardDepositDialog().close();
        pages().confirmCloseMessage().acceptClose();
        pages().topNavigationPage().deposit();
        softAssert().assertTrue(pages().creditCardDepositDialog().isDisplayed(),"'Credit card deposit' has not appeared");
    }

    @Test(description = "CTW-19148:Thank you 1 loads when deposit succeed")
    @TestLinkProperties(displayId = "CTW-19148")
    public void verifyThankYouOneLoadsWhenDepositsucceed() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo
                .builder(WebSiteNamingStrategy.get()).build();
        PersonalInformation personalInformation = getPersonalInformationScore0();
        TradingExperienceInfo tradingExperienceInfo = tradingExperienceInfoWith0Score();
        invokeSlideFNS(customerRegistrationInfo);
        passQuestionnaire(personalInformation, tradingExperienceInfo);
        pages().riskWarning().accept();
        pages().creditCardDepositDialog().close();
        pages().confirmCloseMessage().acceptClose();
        pages().topNavigationPage().deposit();
        pages().creditCardDepositDialog().submit(CreditCardDeposit.builder().build());
        softAssert().assertTrue(pages().thankYouPage().startTradeExists(),"'Thank you' dialog has not appeared");
    }

    @Test(description = "CTW-19149:Progress bar removed from AML slide")
    @TestLinkProperties(displayId = "CTW-19149")
    public void verifyProgressBarRemovedFromAML() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo
                .builder(WebSiteNamingStrategy.get()).build();
        PersonalInformation personalInformation = getPersonalInformationScore0();
        TradingExperienceInfo tradingExperienceInfo = tradingExperienceInfoWith0Score();
        invokeSlideFNS(customerRegistrationInfo);
        passQuestionnaire(personalInformation, tradingExperienceInfo);
        pages().riskWarning().accept();
        pages().creditCardDepositDialog().submit(CreditCardDeposit.builder().build());
        pages().thankYouPage().doContinue();
        softAssert().assertFalse(pages().fnsEmployerInfo().progressBarExists(),"Progress bar exists in the AML dialog");
    }



    /**
     * check FNS answers in the CRM DB
     * @param customerRegistrationInfo - customer information
     * @param personalInformation - personal information filled in the FNS UI
     * @param tradingExperienceInfo - experience information filled in the FNS UI
     */
    private void assertAnswersInCRMDB(CustomerRegistrationInfo customerRegistrationInfo, PersonalInformation personalInformation, TradingExperienceInfo tradingExperienceInfo ) {
        List<RegulationAnswerExtensionBase> answers = operations().customerOperations()
                .findCustomerAnswers(operations().customerOperations()
                        .findByEmailAddress(customerRegistrationInfo.getEmail()).getContactId());
        softAssert().assertEquals(answers.size(), PERSONAL_PERCENTAGES.length+EXPERIENCE_PERCENTAGES.length);
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

    /**
     * check FNS answers in the CRM DB if other fields were selected
     * @param customerRegistrationInfo - customer information
     * @param personalInformation - personal information filled in the FNS UI
     * @param tradingExperienceInfo - experience information filled in the FNS UI
     */
    private void assertAnswersInCRMDBOtherAnswers(CustomerRegistrationInfo customerRegistrationInfo, PersonalInformation personalInformation, TradingExperienceInfo tradingExperienceInfo ) {
        List<RegulationAnswerExtensionBase> answers = operations().customerOperations()
                .findCustomerAnswers(operations().customerOperations()
                        .findByEmailAddress(customerRegistrationInfo.getEmail()).getContactId());
        softAssert().assertEquals(answers.size(), EXPECTED_ANSWERS_COUNT);
        Map<String,String> expectedAnswer = new HashMap<>();
        expectedAnswer.put(personalInformation.employmentStatus, null );
        expectedAnswer.put(personalInformation.industry, personalInformation.industryOther );
        expectedAnswer.put(personalInformation.educationLevel, personalInformation.educationFieldOther);
        expectedAnswer.put(personalInformation.educationField , null);
        expectedAnswer.put(personalInformation.isPoliticallyExposed , personalInformation.politicalExposureComment);
        expectedAnswer.put(personalInformation.sourceOfFunds , personalInformation.sourceOfFundsOther);
        expectedAnswer.put(personalInformation.annualIncome , null);
        expectedAnswer.put(personalInformation.netWealth , null);
        expectedAnswer.put(personalInformation.expectedDepositsPerYear, null);
        expectedAnswer.put(personalInformation.purposeOfTrading , personalInformation.purposeOfTradingOther);
        expectedAnswer.put(tradingExperienceInfo.financialWorkExperience , null);
        expectedAnswer.put(tradingExperienceInfo.cfdBinaryKnowledge , null);
        expectedAnswer.put(tradingExperienceInfo.mainFactorKnowledge , null);
        expectedAnswer.put(tradingExperienceInfo.howToCloseKnowledge , null);
        expectedAnswer.put(tradingExperienceInfo.requiredMarginKnowledge , null);
        expectedAnswer.put(tradingExperienceInfo.marginLevelDropKnowledge , null);
        expectedAnswer.put(tradingExperienceInfo.lossOn1to50Knowledge , null);
        expectedAnswer.put(tradingExperienceInfo.lossOn1to200Knowledge , null);
        expectedAnswer.put(tradingExperienceInfo.financialWorkExperience , null);
        expectedAnswer.put(tradingExperienceInfo.instrumentsTradedBefore , null);
        expectedAnswer.put(tradingExperienceInfo.frequencyPastTransactions , null);
        expectedAnswer.put(tradingExperienceInfo.volumePastTransaction , null);
        expectedAnswer.put(tradingExperienceInfo.commonLevelPastTransaction , null);

        checkAnswerOtherExisting(answers, expectedAnswer);

    }

    /**
     * check answers in the CRM DB after fill them in the UI FNS forms
     * @param answers - answers to check
     * @param expectedAnswer - expected answers in the CRM DB
     */
    private void checkAnswerExisting(List<RegulationAnswerExtensionBase> answers, String... expectedAnswer) {
        for (String answerToDetect:expectedAnswer) {
            softAssert().assertNotNull(answers.stream()
                            .filter(answer-> answer.getAnswerKey().equalsIgnoreCase(answerToDetect))
                            .findFirst().orElse(null),
                    answerToDetect + " was not detected in the list");
        }
    }

    /**
     * verify other answers in the DB after adding then in the FNS forms
     * @param answers - answers to check in the CRM DB
     * @param expectedAnswer - expected other answers in the CRM DB
     */
    private void checkAnswerOtherExisting(List<RegulationAnswerExtensionBase> answers, Map<String,String> expectedAnswer) {
        expectedAnswer.forEach((answerToDetect, answerText)-> {
            softAssert().assertNotNull(answers.stream()
                            .filter(answer-> answer.getAnswerKey().equalsIgnoreCase(answerToDetect))
                            .findFirst().orElse(null),
                    answerToDetect + " was not detected in the list");
            if  (answerText != null) {
                softAssert().assertNotNull(answers.stream()
                                .filter(answer-> answer.getAnswerText().equalsIgnoreCase(answerText))
                                .findFirst().orElse(null),
                        answerText + " was not detected for "+ answerToDetect);
            }
        });
    }

    /**
     * register random customer and navigate to the FNS first slide
     */
    private void invokeSlideFNS() {
        CustomerRegistrationInfo customerRegistrationInfo = CustomerRegistrationInfo
                .builder(WebSiteNamingStrategy.get()).build();
        invokeSlideFNS(customerRegistrationInfo);
    }

    /**
     * Register user using UI, fill all forms until FNS
     * @param customerRegistrationInfo - customer to register
     */
    private void invokeSlideFNS(CustomerRegistrationInfo customerRegistrationInfo) {
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(customerRegistrationInfo);
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
    }


    /**
     * Register user using UI, pass all slides until POI POR
     * @return Personal information object
     */
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
