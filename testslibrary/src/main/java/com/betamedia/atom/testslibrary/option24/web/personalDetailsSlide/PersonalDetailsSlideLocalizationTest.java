package com.betamedia.atom.testslibrary.option24.web.personalDetailsSlide;

import com.betamedia.atom.core.fwdataaccess.repository.util.Language;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import org.testng.annotations.Test;

import static com.betamedia.atom.core.dsl.pages.extensions.LocalizationOperations.Direction.RTL;


/**
 * @author Leonid Artemiev
 * @since 7/13/17
 */

public class PersonalDetailsSlideLocalizationTest extends PersonalDetailsSlideFunctionalityTest {

	/*
	 *[testlink]  CTW-5680:Verify the slide is translated to all languages
	 */
    @Test(description ="CTW-5680:Verify the slide is translated to all languages" )
    @TestLinkProperties(displayId ="CTW-5680")
	  public void  verifyTheSlideIsTranslatedToAllLanguages() {
        for (Language language : Language.values()) {
            pages().topNavigationPage().selectLanguage(language.code);
            pages().welcomeBackMessage().continueQuestionnaire();
            pages().accountAdditionalDetails().verifyLocalization(language);
        }
    }

	/*
	 *[testlink]   CTW-5682:Verify the slide turns RTL on AR
	 */
    @Test(description = "CTW-5682:Verify the slide turns RTL on AR")
    @TestLinkProperties(displayId ="CTW-5682")
    public void  verifyTheSlideTurnsRTLOnAR() {
        pages().topNavigationPage().selectLanguage(Language.ARABIC.code);
        pages().welcomeBackMessage().continueQuestionnaire();
        pages().accountAdditionalDetails().exists();
        pages().accountAdditionalDetails().verifyDirection(RTL);
    }
}
