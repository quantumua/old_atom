package com.betamedia.atom.testslibrary.option24.web.personalDetailsSlide;

import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import com.betamedia.atom.core.fwdataaccess.repository.util.Language;
import com.betamedia.atom.core.testingtype.web.WebEndToEndTest;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetailsData;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;

import static com.betamedia.atom.core.dsl.pages.utils.PageObjectUtils.RTL_DIRECTION;

/**
 * @author Leonid Artemiev
 * @since 7/13/17
 */

public class PersonalDetailsSlideLocalisationTest extends PersonalDetailsSlideTest {

    @BeforeMethod
    @Parameters({"countrycode", "phonecountryprefix"})
    public void before(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        super.before(countrycode, phonecountryprefix);
    }

	/*
	 *[testlink]  CTW-5680:Verify the slide is translated to all languages
	 */
    @Test(dataProvider = GENERIC_DATA_PROVIDER)
    @TestLinkProperties(displayId ="CTW-5680")
	  public void  verifyTheSlideIsTranslatedToAllLanguages(AccountAdditionalDetailsData data) {
    	pages().topNavigationPage().selectLanguage(data.getLanguage());
        pages().welcomeBackMessage().continueQuestionnaire();
        pages().accountAdditionalDetails().exists();
        pages().accountAdditionalDetails().verifySlideTranslation(data);
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
        pages().topNavigationPage().selectLanguage(Language.ARABIC.code);
        pages().welcomeBackMessage().continueQuestionnaire();
        pages().accountAdditionalDetails().exists();
        pages().accountAdditionalDetails().verifyTextDirectionElements(RTL_DIRECTION);
    }
}
