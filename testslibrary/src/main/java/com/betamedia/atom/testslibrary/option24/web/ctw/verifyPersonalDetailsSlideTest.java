package com.betamedia.atom.testslibrary.option24.web.ctw;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.betamedia.atom.core.testingtype.web.WEBEndToEndTest;

/**
 * @author leonid.a
 */

public class verifyPersonalDetailsSlideTest extends WEBEndToEndTest{

	/*
	 *[testlink]  CTW-5624:Verify slide appear after registration and welcome slide
	 * 
	 */
    @Parameters({"countrycode"}) 
    @Test
	  public void  verifySlideAppearAfterRegistrationAndWelcomeSlide(String countrycode) {
	     	pages().topNavigationPage().signUp();
	        pages().registrationPage().register(countrycode);
	        pages().welcomepage().isStartBtnDisplayed();
	        pages().welcomepage().start();
	        pages().accountAdditionalDetailsPage().exists();
    }
    
	/*
	 *[testlink]   CTW-5625:Verify "Submit" bttn is mandatory for moving fwd
	 * 
	 */
    @Parameters({"countrycode"}) 
    @Test
    public void  verifySubmitBbttnIisMandatoryForMovingFwd(String countrycode) {
     	pages().topNavigationPage().signUp();
        pages().registrationPage().register(countrycode);
        pages().welcomepage().isStartBtnDisplayed();
        pages().welcomepage().start();
        pages().accountAdditionalDetailsPage().assertUpdateBtnIsDisabled();;
    }
    

}
