package com.betamedia.atom.testslibrary.option24.web.ctw;

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
	/*
	 *[testlink]  CTW-5680:Verify the slide is translated to all languages
	 */
    @Parameters({"countrycode"}) 
    @Test(description = "CTW-5680:Verify the slide is translated to all languages", dataProvider = GENERIC_DATA_PROVIDER)
	  public void  verifyTtheSlideIsTranslatedToAllLanguages(String countrycode, AccountAdditionalDetailsData data) {
    	
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
    @Parameters({"countrycode"}) 
    @Test(description = "CTW-5682:Verify the slide turns RTL on AR")
    public void  verifyTheSlideTurnsRTLOnAR(String countrycode) {
        pages().topNavigationPage().signUp();
        CustomerRO customer = CustomerRO.builder(WebSiteNamingStrategy.get()).setCountryCode(countrycode).build();
        pages().registrationDialog().register(customer);
        pages().welcomePage().isStartBtnDisplayed();
        pages().welcomePage().start();
        pages().topNavigationPage().selectLanguage("AR");
        pages().welcomeBackMessage().continueQuestionnaire();
        pages().accountAdditionalDetails().exists();
        pages().accountAdditionalDetails().verifyTextDirectionElements("RTL");
    }
}
