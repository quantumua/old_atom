package com.betamedia.atom.testslibrary.option24.web.personalDetailsSlide;

import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import com.betamedia.atom.core.testingtype.web.WebEndToEndTest;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetailsData;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;

/**
 * @author Leonid Artemiev
 * @since 7/13/17
 */

public class PersonalDetailsSlideLocalisationTest extends WebEndToEndTest {

    private static final String ARABIC_LANGUAGE = "AR";
    private static final String RTL_DIRECTION = "rtl";

    @BeforeMethod
	public void before(){
        pages().topNavigationPage().signUp();
        CustomerRegistrationInfo customer = CustomerRegistrationInfo.builder(WebSiteNamingStrategy.get()).build();
        pages().registrationDialog().fillRegisterForm(customer);
        pages().registrationDialog().submitRegisterForm();
        pages().welcomeDialog().start();
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
        pages().topNavigationPage().selectLanguage(ARABIC_LANGUAGE);
        pages().welcomeBackMessage().continueQuestionnaire();
        pages().accountAdditionalDetails().exists();
        pages().accountAdditionalDetails().verifyTextDirectionElements(RTL_DIRECTION);
    }
}
