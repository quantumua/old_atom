package com.betamedia.atom.testslibrary.option24.web.ctw;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.betamedia.atom.core.testingtype.web.WEBEndToEndTest;

/**
 * @author Leonid.a
 * @since 7/13/17
 */

public class PersonalDetailsSlideLocalisationTest extends WEBEndToEndTest{
	/*
	 *[testlink]  CTW-5680:Verify the slide is translated to all languages
	 */
    @Parameters({"countrycode"}) 
    @Test(description = "CTW-5680:Verify the slide is translated to all languages")
	  public void  verifyTtheSlideIsTranslatedToAllLanguages(String countrycode) {
    	
    }
    
	/*
	 *[testlink]   CTW-5682:Verify the slide turns RTL on AR
	 */
    @Parameters({"countrycode"}) 
    @Test(description = "CTW-5682:Verify the slide turns RTL on AR")
    public void  verifyTheSlideTurnsRTLOnAR(String countrycode) {
    	
    }

}
