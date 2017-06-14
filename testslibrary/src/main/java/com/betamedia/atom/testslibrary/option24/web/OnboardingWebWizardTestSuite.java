package com.betamedia.atom.testslibrary.option24.web;


import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.atom.core.testingtype.web.WEBEndToEndTest;
import com.betamedia.atom.testslibrary.option24.end2end.crm.AccountAdditionalDetailsTest;
import com.betamedia.atom.testslibrary.option24.end2end.crm.newQuestionnaries.Questions;

import org.testng.annotations.Test;

/**
 * @author Lilian Medina
 *         Date: 5/15/17.
 */

public class OnboardingWebWizardTestSuite extends WEBEndToEndTest {
	

//	 @Test
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
      
	       
	       pages().fnsPersonalInformation().submitOnWizard(PersonalInformation.builder()
	                .withEmploymentStatus(Questions.EmploymentStatus.SALARIED_EMPLOYEE.get())
	                .withIndustry(Questions.Industry.FINANCE.get())
	                .withEmployerName("fgsfds")
	                .withTaxResidenceCountry("DE")
	                .withUSReportabilityStatus(Questions.HasTaxIdentificationNumber.NO.get())
	                .withTaxIdentificationNumberStatus(Questions.HasTaxIdentificationNumber.NO.get())
	                .withTaxIdentificationNumber("123456789")
	                .withEducationLevel(Questions.EducationLevel.POST_GRADUATE.get())
	                .withEducationField(Questions.EducationField.ACCOUNTING.get())
	                .withPoliticalExposureStatus(Questions.IsPoliticallyExposed.NO.get())
	                .withSourceOfFunds(Questions.SourceOfFunds.EMPLOYMENT.get())
	                .withAnnualIncome(Questions.AnnualIncome.INCOME_OVER_100K.get())
	                .withNetWealth(Questions.NetWealth.NET_WEALTH_OVER_300K.get())
	                .withExpectedDepositsPerYear(Questions.ExpectedDepositsPerYear.DEPOSITS_OVER_50K.get())
	                .withPurposeOfTrading(Questions.PurposeOfTrading.ADDITIONAL_INCOME.get())
	                .build());
	       
	      
//	       try {
//	            Thread.sleep(10000);
//	        } catch (InterruptedException e) {
//	            // TODO Auto-generated catch block
//	            e.printStackTrace();
//	        }
	        
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
        


