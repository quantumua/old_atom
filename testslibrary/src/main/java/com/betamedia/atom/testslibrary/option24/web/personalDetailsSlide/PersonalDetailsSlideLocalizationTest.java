package com.betamedia.atom.testslibrary.option24.web.personalDetailsSlide;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetailsData;
import com.betamedia.atom.core.api.web.form.Country;
import com.betamedia.atom.core.fwdataaccess.repository.util.Language;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.betamedia.atom.core.dsl.pages.extensions.LocalizationOperations.Direction.RTL;


/**
 * @author Leonid Artemiev
 * @since 7/13/17
 */

public class PersonalDetailsSlideLocalizationTest extends PersonalDetailsSlideFunctionalityTest {


    @BeforeMethod
    public void before() {
        // empty
    }
	/*
	 *[testlink]  CTW-5680:Verify the slide is translated to all languages
	 */
    @Test(dataProvider = GENERIC_DATA_PROVIDER)
    @TestLinkProperties(displayId ="CTW-5680")
	  public void  verifyTheSlideIsTranslatedToAllLanguages(AccountAdditionalDetailsData data) {
        pages().controlPanel().logOut();
        pages().topNavigationPage().selectLanguage(data.getLanguage());
        registedAndStart(Country.GERMANY.getName(), Country.GERMANY.getPhonePrefix());
        pages().accountAdditionalDetails().exists();
        pages().accountAdditionalDetails().verifySlideTranslation(data, data.getLanguage());
    }
    
    @Override
    protected Class getDataSourceEntity() {
        return AccountAdditionalDetailsData.class;
    }

    @Override
    protected String getDataSourcePath() {
        return "/data/accountAdditionalDetailsData.csv";
    }
    
	/*
	 *[testlink]   CTW-5682:Verify the slide turns RTL on AR
	 */
    @Test(description = "CTW-5682:Verify the slide turns RTL on AR")
    @TestLinkProperties(displayId ="CTW-5682")
    public void  verifyTheSlideTurnsRTLOnAR() {
        pages().controlPanel().logOut();
        pages().topNavigationPage().selectLanguage(Language.ARABIC.code);
        registedAndStart(Country.GERMANY.getName(), Country.GERMANY.getPhonePrefix());
        pages().accountAdditionalDetails().exists();
        pages().accountAdditionalDetails().verifyDirection(RTL);
    }
}
