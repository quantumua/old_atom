package com.betamedia.atom.testslibrary.option24.web.ctw;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetailsData;
import com.betamedia.atom.core.api.crm.form.entities.QuestionnaireData;
import com.betamedia.atom.core.api.crm.form.parsers.PersonalInformationParser;
import com.betamedia.atom.core.api.crm.form.parsers.TradingExperienceInfoParser;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WidgetsNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.testingtype.web.WEBEndToEndTest;

/**
 * @author Leonid Artemiev
 * @since 7/13/17
 */

public class PersonalDetailsSlideLocalisationTest extends WEBEndToEndTest{
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
    	
    }

}
