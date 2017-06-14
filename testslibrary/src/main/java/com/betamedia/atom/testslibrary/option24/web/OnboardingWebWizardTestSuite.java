package com.betamedia.atom.testslibrary.option24.web;


import com.betamedia.atom.core.testingtype.web.WEBEndToEndTest;

import org.testng.annotations.Test;

/**
 * @author Lilian Medina
 *         Date: 5/15/17.
 */

public class OnboardingWebWizardTestSuite extends WEBEndToEndTest {

	 @Test
	    public void  onboardingWizardRegistration() {
	     	pages().topNavigationPage().signUp();
	        pages().registrationPage().register();
	        pages().welcomepage().isStartBtnDisplayed();
	        pages().welcomepage().start();
	        
	        
	    }
	    
//	    @Test
	    public void  onboardingWizardFNSpersonalRejectRisk() {
	     	pages().topNavigationPage().signUp();
	        pages().registrationPage().register();
	        
	    }
	    
//	    @Test
	    public void  onboardingWizardFNStradingRejectRisk() {
	     	pages().topNavigationPage().signUp();
	        pages().registrationPage().register();
	        
	    }
	    
//	    @Test
	    public void  onboardingWizardFNStradingRejectNotRisk() {
	     	pages().topNavigationPage().signUp();
	        pages().registrationPage().register();
	        
	    }
	    
//	    @Test
	    public void  onboardingWizardDeposit() {
	     	pages().topNavigationPage().signUp();
	        pages().registrationPage().register();
	        
	    }
	    
//	    @Test
	    public void  onboardingWizardUploadPOI() {
	     	pages().topNavigationPage().signUp();
	        pages().registrationPage().register();
	        
	    }
	    
//	    @Test
	    public void  onboardingWizardUploadPOR() {
	     	pages().topNavigationPage().signUp();
	        pages().registrationPage().register();
	        
	    }
}
        


