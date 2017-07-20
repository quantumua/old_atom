package com.betamedia.atom.testslibrary.option24.web.ctw;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetailsData;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.testingtype.tp.TPEndToEndTest;

/**
 * @author Leonid Artemiev
 * @since 7/13/17
 */

public class PersonalDetailsSlideLocalisationTest extends TPEndToEndTest {
	@Parameters({"countrycode"})
    @BeforeMethod
	public void before(@Optional("de") String countrycode){
    	pages().topNavigationPage().signUp();
        CustomerRO customer = CustomerRO.builder(WebSiteNamingStrategy.get()).setCountryCode(countrycode).build();
        pages().registrationDialog().register(customer);
        pages().welcomePage().isStartBtnDisplayed();
        pages().welcomePage().start();
	}

	/*
	 *[testlink]  CTW-5680:Verify the slide is translated to all languages
	 */
    @Test(dataProvider = GENERIC_DATA_PROVIDER)
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
    public void  verifyTheSlideTurnsRTLOnAR() {
        pages().topNavigationPage().selectLanguage("AR");
        pages().welcomeBackMessage().continueQuestionnaire();
        pages().accountAdditionalDetails().exists();
        pages().accountAdditionalDetails().verifyTextDirectionElements("RTL");
    }
}
