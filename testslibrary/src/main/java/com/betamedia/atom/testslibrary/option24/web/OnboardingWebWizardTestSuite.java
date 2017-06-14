package com.betamedia.atom.testslibrary.option24.web;


import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.testingtype.web.WEBEndToEndTest;
import com.betamedia.atom.testslibrary.option24.end2end.crm.AccountAdditionalDetailsTest;

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
	        pages().accountAdditionalDetailsPage().update(AccountAdditionalDetails.builder()
	                .withBirthDateDay("1")
	                .withBirthDateMonth("2")
	                .withBirthDateYear("1990")
	                .withCountryOfBirth("DE")
	                .withNationality("DE")
	                .build());
	       
	        
	        
	    }
	    
   @Test
	    public void  onboardingWizardFNSpersonalRejectRisk() {
		   pages().topNavigationPage().signUp();
	       pages().registrationPage().register();
	       pages().welcomepage().isStartBtnDisplayed();
	       pages().welcomepage().start();
	       pages().accountAdditionalDetailsPage().update(AccountAdditionalDetails.builder()
	               .withBirthDateDay("1")
	               .withBirthDateMonth("2")
	               .withBirthDateYear("1990")
	               .withCountryOfBirth("DE")
	               .withNationality("DE")
	               .build());
      
	        
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
        


