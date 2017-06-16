package com.betamedia.atom.testslibrary.option24.web;


import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.atom.core.testingtype.web.WEBEndToEndTest;
import com.betamedia.atom.testslibrary.option24.end2end.crm.newQuestionnaries.Questions;
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
	    
         @Test
		  public void  onboardingWizardAdditionalDetails() {
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
	    public void  onboardingWizardFNSpersonalLowscore() {
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
	                .withIndustry(Questions.Industry.COMPUTER.get())
	                .withEmployerName("fgsfds")
	                .withTaxResidenceCountry("DE")
	                .withUSReportabilityStatus(Questions.HasTaxIdentificationNumber.YES.get())
	                .withTaxIdentificationNumberStatus(Questions.HasTaxIdentificationNumber.NO.get())
	                .withTaxIdentificationNumber("123456789")
	                .withEducationLevel(Questions.EducationLevel.SECONDARY.get())
	                .withEducationField(Questions.EducationField.COMPUTER.get())
	                .withPoliticalExposureStatus(Questions.IsPoliticallyExposed.NO.get())
	                .withSourceOfFunds(Questions.SourceOfFunds.EMPLOYMENT.get())
	                .withAnnualIncome(Questions.AnnualIncome.INCOME_OVER_100K.get())
	                .withNetWealth(Questions.NetWealth.NET_WEALTH_OVER_300K.get())
	                .withExpectedDepositsPerYear(Questions.ExpectedDepositsPerYear.DEPOSITS_OVER_50K.get())
	                .withPurposeOfTrading(Questions.PurposeOfTrading.ADDITIONAL_INCOME.get())
	                .build());       
	      
	    }
        
       @Test
	    public void  onboardingWizardFNSpersonal() {
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
	       
	      

	    }
	    
       @Test
	    public void  onboardingWizardFNStradingReject() {
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
		       
		      
	         pages().fnsTradingExperience().submitOnWizard(TradingExperienceInfo.builder()
	                 .withSharesExperience(Questions.SharesExperience.NEVER.get())
	                 .withBinaryExperience(Questions.BinaryExperience.NEVER.get())
	                 .withForExExperience(Questions.ForExExperience.NEVER.get())
	                 .withFinancialWorkExperience(Questions.FinancialWorkExperience.NEITHER.get())
	                 .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.NON_RISKY.get())
	                 .withMainFactorKnowledge(Questions.MainFactorKnowledge.ANNOUNCEMENT.get())
	                 .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.LONDON_STOCK.get())
	                 .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.PROVIDES.get())
	                 .withStopLossKnowledge(Questions.StopLossKnowledge.BUY.get())
	                 .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_10K.get())
	                 .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.WARNING_CALL.get())
	                 .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EARNINGS.get())
	                 .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A2_450.get())
	                 .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A2_1200.get())
	                 .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_60.get())
	                 .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_75.get())
	                 .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_35.get())
	                 .build());
	         
	         pages().topNavigationPage().isLoggedOut();
	    }
	    
        @Test
	    public void  onboardingWizardFNStradingRisk() {
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
		       
		      
	         pages().fnsTradingExperience().submitOnWizard(TradingExperienceInfo.builder()
	        		 .withSharesExperience(Questions.SharesExperience.NEVER.get())
	                 .withBinaryExperience(Questions.BinaryExperience.NEVER.get())
	                 .withForExExperience(Questions.ForExExperience.NEVER.get())
	                 .withFinancialWorkExperience(Questions.FinancialWorkExperience.WORKED.get())
	                 .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.SPECULATIVE.get())
	                 .withMainFactorKnowledge(Questions.MainFactorKnowledge.ANNOUNCEMENT.get())
	                 .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.LONDON_STOCK.get())
	                 .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.PROVIDES.get())
	                 .withStopLossKnowledge(Questions.StopLossKnowledge.MINIMIZE.get())
	                 .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_1K.get())
	                 .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.WARNING_CALL.get())
	                 .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EARNINGS.get())
	                 .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A2_450.get())
	                 .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A2_1200.get())
	                 .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_60.get())
	                 .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_75.get())
	                 .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_35.get())
	                 .build());
//	         FIXME not implemented
//	         pages().riskwarning().waitforRisWarning();
	    }
	    
	    @Test
	    public void  onboardingWizardFNStradingNoRisk() {
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
		       
		      
	         pages().fnsTradingExperience().submitOnWizard(TradingExperienceInfo.builder()
	        		 .withSharesExperience(Questions.SharesExperience.NEVER.get())
	                 .withBinaryExperience(Questions.BinaryExperience.OCCASIONALLY.get())
	                 .withAverageYearlyBinaryVolume(Questions.AverageYearlyBinaryVolume.VOLUME_500_5K.get())
	                 .withForExExperience(Questions.ForExExperience.NEVER.get())
	                 .withFinancialWorkExperience(Questions.FinancialWorkExperience.WORKED.get())
	                 .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.SPECULATIVE.get())
	                 .withMainFactorKnowledge(Questions.MainFactorKnowledge.ANNOUNCEMENT.get())
	                 .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.LONDON_STOCK.get())
	                 .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.PROVIDES.get())
	                 .withStopLossKnowledge(Questions.StopLossKnowledge.MINIMIZE.get())
	                 .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_1K.get())
	                 .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.WARNING_CALL.get())
	                 .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EARNINGS.get())
	                 .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A1_800.get())
	                 .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A1_1800.get())
	                 .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_75.get())
	                 .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_75.get())
	                 .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_35.get())
	                 .build());
//	         FIXME not implemented
//	         pages().creditCardDepositPage().waitforCreditCardDepositPage();
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
        


