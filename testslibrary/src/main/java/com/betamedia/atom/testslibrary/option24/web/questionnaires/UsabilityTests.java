package com.betamedia.atom.testslibrary.option24.web.questionnaires;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import org.testng.annotations.Test;
import static com.betamedia.atom.core.api.web.form.RGB.*;

/**
 * Created by vsnigur on 8/30/17.
 */
public class UsabilityTests extends AbstractExperienceLevelsTests {

    @Test(description = "CTW-5885:Slide with multiple choice")
    @TestLinkProperties(displayId = "CTW-5885")
    public void verifySlideWithMultipleChoiceTest() {
        getCustomerRegistrationInfo();
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().fnsPersonalInformation().assertControlsUsability(
                getPersonalInformationScore0(),
                DUSTY_GRAY.getCode(), GRAY.getCode(),
                WHITE.getCode(), COD_GRAY.getCode());
    }

    @Test(description = "CTW-5886:Slide with field that opens a free text field")
    @TestLinkProperties(displayId = "CTW-5886")
    public void verifySlideTextFieldFunctionalityTest() {
        getCustomerRegistrationInfo();
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().fnsPersonalInformation().submit(getPersonalInformationOtherAnswers());
        pages().fnsTradingExperience().submit(getExpertTradingExperienceInfo());
    }

    @Test(description = "CTW-5887:Slide with a question that depend on the previous question")
    @TestLinkProperties(displayId = "CTW-5887")
    public void slideDependOnPreviousQuestionTest() {
        getCustomerRegistrationInfo();
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().fnsPersonalInformation().submit(getPersonalInformationOtherAnswers());
        pages().fnsTradingExperience().submit(getExpertTradingExperienceInfo());
    }

    @Test(description = "CTW-5889:Slide with sub questions that opens after answering the previous question")
    @TestLinkProperties(displayId = "CTW-5889")
    public void verifySlideWithSubQuestionsOpensDependOnPreviousQuestionTest() {
        getCustomerRegistrationInfo();
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        pages().fnsPersonalInformation().submit(getPersonalInformationOtherAnswers());
        pages().fnsTradingExperience().checkDependsQuestions(getExpertTradingExperienceInfo());
    }
}
