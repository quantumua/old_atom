package com.betamedia.atom.testslibrary.option24.web;


import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.testingtype.web.WEBEndToEndTest;
import com.betamedia.atom.testslibrary.option24.end2end.crm.newQuestionnaries.Questions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author Lilian Medina
 *         Date: 5/15/17.
 */

public class OnboardingWebWizardTestSuite extends WEBEndToEndTest {
	 private static CustomerRO customer = CustomerRO.builder(WebSiteNamingStrategy.get()).build();

	 @Parameters({"countrycode"}) 
	 @Test
	    public void  onboardingWizardRegistration(String countrycode) {
	     	pages().topNavigationPage().signUp();
	        pages().registrationPage().register(countrycode);
	        pages().welcomepage().isStartBtnDisplayed();
	        pages().welcomepage().start();	        	       
	        
	        
	    }
	    @Parameters({"countrycode"}) 
         @Test
		  public void  onboardingWizardAdditionalDetails(String countrycode) {
		     	pages().topNavigationPage().signUp();
		        pages().registrationPage().register(countrycode);
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
	    
	    @Parameters({"countrycode"}) 
        @Test
	    public void  onboardingWizardFNSpersonalLowscore(String countrycode) {
		   pages().topNavigationPage().signUp();
	       pages().registrationPage().register(countrycode);
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
	   @Parameters({"countrycode"})  
       @Test
	    public void  onboardingWizardFNSpersonal(String countrycode) {
		   pages().topNavigationPage().signUp();
	       pages().registrationPage().register(countrycode);
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
	   @Parameters({"countrycode"})  
       @Test
	    public void  onboardingWizardFNSTradingReject(String countrycode) {
	    	  pages().topNavigationPage().signUp();
		       pages().registrationPage().register(countrycode);
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
	   
	    @Parameters({"countrycode"}) 
        @Test
	    public void  onboardingWizardFNStradingRisk(String countrycode) {
	    	  pages().topNavigationPage().signUp();
		       pages().registrationPage().register(countrycode);
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
         pages().riskwarning().waitForRiskWarning();
	    }
	    
	    
        @Test
	    public void  onboardingWizardSpainRiskSignature() {
	    	  pages().topNavigationPage().signUp();
		       pages().registrationPage().register("es");
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
         pages().signatureRiskWarning().waitforRiskSingnature();
	    }
	   
	    @Parameters({"countrycode"}) 
	    @Test
	    public void  onboardingWizardFNStradingNoRisk(String countrycode) {
	    	  pages().topNavigationPage().signUp();
		       pages().registrationPage().register(countrycode);
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
	         pages().creditCardDepositPage().waitforCreditCardDepositPage();
	    }
	    
	    @Parameters({"countrycode"}) 
	    @Test
	    public void  onboardingWizardDeposit(String countrycode) {
	    	  pages().topNavigationPage().signUp();
		       pages().registrationPage().register(countrycode);
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

	         pages().creditCardDepositPage().submit((CreditCardDeposit.builder()
                     .withDepositAmount("50")
                     .withCreditCardNumber("4543130000001116")
                     .withCVV2("092")
                     .withExpiryDateMonth("1")
                     .withExpiryDateYear("2020")
                     .withCardHoldersFirstName("CardHoldersFirstName")
                     .withCardHoldersLastName("CardHoldersLastName")
                     .withBillingAddress("BillingAddress")
                     .withCity("City")
                     .withZipCode("ZipCode")
                     .build()));
	         
	         pages().onBoardingWizard().assertOnPOI();
	    }
	    
	    
//	  
	    
	    
//	    @Test
	    public void  onboardingWizardUploadPOI() {
	     	pages().topNavigationPage().signUp();
	        pages().registrationPage().register("es");
	        
	    }
	    
//	    @Test
	    public void  onboardingWizardUploadPOR() {
	     	pages().topNavigationPage().signUp();
	        pages().registrationPage().register("es");
	        
	    }
}
        


