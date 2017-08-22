package com.betamedia.atom.testslibrary.option24.web;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.web.form.Country;
import com.betamedia.atom.core.testingtype.web.WebEndToEndTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static com.betamedia.atom.core.api.crm.form.entities.QuestionnaireAnswers.*;

/**
 * @author Lilian Medina
 *         Date: 5/15/17.
 */

public class OnboardingWebWizardTestSuite extends WebEndToEndTest {
	 private static CustomerRO customer = CustomerRO.builder(WebSiteNamingStrategy.get()).build();

	 @Parameters({"countrycode"}) 
	 @Test
	    public void  onboardingWizardRegistration(String countrycode) {
	     	pages().topNavigationPage().signUp();
	        pages().registrationDialog().register(countrycode);
	        pages().welcomepage().isStartBtnDisplayed();
	        pages().welcomepage().start();	        	       
	        
	        
	    }
	    @Parameters({"countrycode"}) 
         @Test
		  public void  onboardingWizardAdditionalDetails(String countrycode) {
		     	pages().topNavigationPage().signUp();
		        pages().registrationDialog().register(countrycode);
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
	       pages().registrationDialog().register(countrycode);
	       pages().welcomepage().isStartBtnDisplayed();
	       pages().welcomepage().start();
	       pages().accountAdditionalDetailsPage().update(AccountAdditionalDetails.builder()
	               .withBirthDateDay("1")
	               .withBirthDateMonth("2")
	               .withBirthDateYear("1990")
	               .withCountryOfBirth("DE")
	               .withNationality("DE")
	               .build());


	       pages().fnsPersonalInformation().submit(PersonalInformation.builder()
	                .withEmploymentStatus(EmploymentStatus.SALARIED_EMPLOYEE)
	                .withIndustry(Industry.COMPUTER)
	                .withEmployerName("fgsfds")
	                .withTaxResidenceCountry("DE")
	                .withUSReportabilityStatus(IsUSReportable.YES)
	                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.NO)
	                .withTaxIdentificationNumber("123456789")
	                .withEducationLevel(EducationLevel.SECONDARY)
	                .withEducationField(EducationField.COMPUTER)
	                .withPoliticalExposureStatus(IsPoliticallyExposed.NO)
	                .withSourceOfFunds(SourceOfFunds.EMPLOYMENT)
	                .withAnnualIncome(AnnualIncome.INCOME_OVER_100K)
	                .withNetWealth(NetWealth.NET_WEALTH_OVER_300K)
	                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_OVER_50K)
	                .withPurposeOfTrading(PurposeOfTrading.ADDITIONAL_INCOME)
	                .build());

	    }
	   @Parameters({"countrycode"})
       @Test
	    public void  onboardingWizardFNSpersonal(String countrycode) {
		   pages().topNavigationPage().signUp();
	       pages().registrationDialog().register(countrycode);
	       pages().welcomepage().isStartBtnDisplayed();
	       pages().welcomepage().start();
	       pages().accountAdditionalDetailsPage().update(AccountAdditionalDetails.builder()
	               .withBirthDateDay("1")
	               .withBirthDateMonth("2")
	               .withBirthDateYear("1990")
	               .withCountryOfBirth("DE")
	               .withNationality("DE")
	               .build());


	       pages().fnsPersonalInformation().submit(PersonalInformation.builder()
	                .withEmploymentStatus(EmploymentStatus.SALARIED_EMPLOYEE)
	                .withIndustry(Industry.FINANCE)
	                .withEmployerName("fgsfds")
	                .withTaxResidenceCountry("DE")
	                .withUSReportabilityStatus(IsUSReportable.NO)
	                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.NO)
	                .withTaxIdentificationNumber("123456789")
	                .withEducationLevel(EducationLevel.POST_GRADUATE)
	                .withEducationField(EducationField.ACCOUNTING)
	                .withPoliticalExposureStatus(IsPoliticallyExposed.NO)
	                .withSourceOfFunds(SourceOfFunds.EMPLOYMENT)
	                .withAnnualIncome(AnnualIncome.INCOME_OVER_100K)
	                .withNetWealth(NetWealth.NET_WEALTH_OVER_300K)
	                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_OVER_50K)
	                .withPurposeOfTrading(PurposeOfTrading.ADDITIONAL_INCOME)
	                .build());



	    }
	   @Parameters({"countrycode"})
       @Test
	    public void  onboardingWizardFNSTradingReject(String countrycode) {
	    	  pages().topNavigationPage().signUp();
		       pages().registrationDialog().register(countrycode);
		       pages().welcomepage().isStartBtnDisplayed();
		       pages().welcomepage().start();
		       pages().accountAdditionalDetailsPage().update(AccountAdditionalDetails.builder()
		               .withBirthDateDay("1")
		               .withBirthDateMonth("2")
		               .withBirthDateYear("1990")
		               .withCountryOfBirth("DE")
		               .withNationality("DE")
		               .build());


		       pages().fnsPersonalInformation().submit(PersonalInformation.builder()
		                .withEmploymentStatus(EmploymentStatus.SALARIED_EMPLOYEE)
		                .withIndustry(Industry.FINANCE)
		                .withEmployerName("fgsfds")
		                .withTaxResidenceCountry("DE")
		                .withUSReportabilityStatus(IsUSReportable.NO)
		                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.NO)
		                .withTaxIdentificationNumber("123456789")
		                .withEducationLevel(EducationLevel.SECONDARY)
		                .withEducationField(EducationField.LAW)
		                .withPoliticalExposureStatus(IsPoliticallyExposed.NO)
		                .withSourceOfFunds(SourceOfFunds.EMPLOYMENT)
		                .withAnnualIncome(AnnualIncome.INCOME_OVER_100K)
		                .withNetWealth(NetWealth.NET_WEALTH_OVER_300K)
		                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_OVER_50K)
		                .withPurposeOfTrading(PurposeOfTrading.ADDITIONAL_INCOME)
		                .build());


	         pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
	                 .withSharesExperience(SharesExperience.NEVER)
	                 .withBinaryExperience(BinaryExperience.NEVER)
	                 .withForExExperience(ForExExperience.NEVER)
	                 .withFinancialWorkExperience(FinancialWorkExperience.NEITHER)
	                 .withCfdBinaryKnowledge(CfdBinaryKnowledge.NON_RISKY)
	                 .withMainFactorKnowledge(MainFactorKnowledge.ANNOUNCEMENT)
	                 .withHowToCloseKnowledge(HowToCloseKnowledge.LONDON_STOCK)
	                 .withCfdLeverageKnowledge(CfdLeverageKnowledge.PROVIDES)
	                 .withStopLossKnowledge(StopLossKnowledge.BUY)
	                 .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_10K)
	                 .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.WARNING_CALL)
	                 .withAutomaticStopKnowledge(AutomaticStopKnowledge.EARNINGS)
	                 .withLossOn1to50Knowledge(LossOn1to50Knowledge.A2_450)
	                 .withLossOn1to200Knowledge(LossOn1to200Knowledge.A2_1200)
	                 .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_60)
	                 .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_75)
	                 .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_35)
	                 .build());

	         pages().topNavigationPage().isLoggedOut();
	    }

	    @Parameters({"countrycode"})
        @Test
	    public void  onboardingWizardFNStradingRisk(String countrycode) {
	    	  pages().topNavigationPage().signUp();
		       pages().registrationDialog().register(countrycode);
		       pages().welcomepage().isStartBtnDisplayed();
		       pages().welcomepage().start();
		       pages().accountAdditionalDetailsPage().update(AccountAdditionalDetails.builder()
		               .withBirthDateDay("1")
		               .withBirthDateMonth("2")
		               .withBirthDateYear("1990")
		               .withCountryOfBirth("DE")
		               .withNationality("DE")
		               .build());


		       pages().fnsPersonalInformation().submit(PersonalInformation.builder()
		                .withEmploymentStatus(EmploymentStatus.SALARIED_EMPLOYEE)
		                .withIndustry(Industry.FINANCE)
		                .withEmployerName("fgsfds")
		                .withTaxResidenceCountry("DE")
		                .withUSReportabilityStatus(IsUSReportable.NO)
		                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.NO)
		                .withTaxIdentificationNumber("123456789")
		                .withEducationLevel(EducationLevel.POST_GRADUATE)
		                .withEducationField(EducationField.ACCOUNTING)
		                .withPoliticalExposureStatus(IsPoliticallyExposed.NO)
		                .withSourceOfFunds(SourceOfFunds.EMPLOYMENT)
		                .withAnnualIncome(AnnualIncome.INCOME_OVER_100K)
		                .withNetWealth(NetWealth.NET_WEALTH_OVER_300K)
		                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_OVER_50K)
		                .withPurposeOfTrading(PurposeOfTrading.ADDITIONAL_INCOME)
		                .build());


	         pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
	                 .withSharesExperience(SharesExperience.NEVER)
	                 .withBinaryExperience(BinaryExperience.NEVER)
	                 .withForExExperience(ForExExperience.NEVER)
	                 .withFinancialWorkExperience(FinancialWorkExperience.NEITHER)
	                 .withCfdBinaryKnowledge(CfdBinaryKnowledge.NON_RISKY)
	                 .withMainFactorKnowledge(MainFactorKnowledge.ANNOUNCEMENT)
	                 .withHowToCloseKnowledge(HowToCloseKnowledge.LONDON_STOCK)
	                 .withCfdLeverageKnowledge(CfdLeverageKnowledge.PROVIDES)
	                 .withStopLossKnowledge(StopLossKnowledge.BUY)
	                 .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_10K)
	                 .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.WARNING_CALL)
	                 .withAutomaticStopKnowledge(AutomaticStopKnowledge.EARNINGS)
	                 .withLossOn1to50Knowledge(LossOn1to50Knowledge.A2_450)
	                 .withLossOn1to200Knowledge(LossOn1to200Knowledge.A2_1200)
	                 .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_60)
	                 .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_75)
	                 .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_35)
	                 .build());
         pages().riskWarning().waitForRiskWarning();
	    }


        @Test
	    public void  onboardingWizardSpainRiskSignature() {
	    	  pages().topNavigationPage().signUp();
		       pages().registrationDialog().register("es");
		       pages().welcomepage().isStartBtnDisplayed();
		       pages().welcomepage().start();
		       pages().accountAdditionalDetailsPage().update(AccountAdditionalDetails.builder()
		               .withBirthDateDay("1")
		               .withBirthDateMonth("2")
		               .withBirthDateYear("1990")
		               .withCountryOfBirth("DE")
		               .withNationality("DE")
		               .build());


		       pages().fnsPersonalInformation().submit(PersonalInformation.builder()
		                .withEmploymentStatus(EmploymentStatus.SALARIED_EMPLOYEE)
		                .withIndustry(Industry.FINANCE)
		                .withEmployerName("fgsfds")
		                .withTaxResidenceCountry("DE")
		                .withUSReportabilityStatus(IsUSReportable.NO)
		                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.NO)
		                .withTaxIdentificationNumber("123456789")
		                .withEducationLevel(EducationLevel.POST_GRADUATE)
		                .withEducationField(EducationField.ACCOUNTING)
		                .withPoliticalExposureStatus(IsPoliticallyExposed.NO)
		                .withSourceOfFunds(SourceOfFunds.EMPLOYMENT)
		                .withAnnualIncome(AnnualIncome.INCOME_OVER_100K)
		                .withNetWealth(NetWealth.NET_WEALTH_OVER_300K)
		                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_OVER_50K)
		                .withPurposeOfTrading(PurposeOfTrading.ADDITIONAL_INCOME)
		                .build());


	         pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
	        		 .withSharesExperience(SharesExperience.NEVER)
	                 .withBinaryExperience(BinaryExperience.NEVER)
	                 .withForExExperience(ForExExperience.NEVER)
	                 .withFinancialWorkExperience(FinancialWorkExperience.WORKED)
	                 .withCfdBinaryKnowledge(CfdBinaryKnowledge.SPECULATIVE)
	                 .withMainFactorKnowledge(MainFactorKnowledge.ANNOUNCEMENT)
	                 .withHowToCloseKnowledge(HowToCloseKnowledge.LONDON_STOCK)
	                 .withCfdLeverageKnowledge(CfdLeverageKnowledge.PROVIDES)
	                 .withStopLossKnowledge(StopLossKnowledge.MINIMIZE)
	                 .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K)
	                 .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.WARNING_CALL)
	                 .withAutomaticStopKnowledge(AutomaticStopKnowledge.EARNINGS)
	                 .withLossOn1to50Knowledge(LossOn1to50Knowledge.A2_450)
	                 .withLossOn1to200Knowledge(LossOn1to200Knowledge.A2_1200)
	                 .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_60)
	                 .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_75)
	                 .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_35)
	                 .build());
//	         FIXME not implemented
         pages().signatureRiskWarning().waitforRiskSingnature();
	    }

	    @Parameters({"countrycode"})
	    @Test
	    public void  onboardingWizardFNStradingNoRisk(String countrycode) {
	    	  pages().topNavigationPage().signUp();
		       pages().registrationDialog().register(countrycode);
		       pages().welcomepage().isStartBtnDisplayed();
		       pages().welcomepage().start();
		       pages().accountAdditionalDetailsPage().update(AccountAdditionalDetails.builder()
		               .withBirthDateDay("1")
		               .withBirthDateMonth("2")
		               .withBirthDateYear("1990")
		               .withCountryOfBirth("DE")
		               .withNationality("DE")
		               .build());


		       pages().fnsPersonalInformation().submit(PersonalInformation.builder()
		                .withEmploymentStatus(EmploymentStatus.SALARIED_EMPLOYEE)
		                .withIndustry(Industry.FINANCE)
		                .withEmployerName("fgsfds")
		                .withTaxResidenceCountry("DE")
		                .withUSReportabilityStatus(IsUSReportable.NO)
		                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.NO)
		                .withTaxIdentificationNumber("123456789")
		                .withEducationLevel(EducationLevel.POST_GRADUATE)
		                .withEducationField(EducationField.ACCOUNTING)
		                .withPoliticalExposureStatus(IsPoliticallyExposed.NO)
		                .withSourceOfFunds(SourceOfFunds.EMPLOYMENT)
		                .withAnnualIncome(AnnualIncome.INCOME_OVER_100K)
		                .withNetWealth(NetWealth.NET_WEALTH_OVER_300K)
		                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_OVER_50K)
		                .withPurposeOfTrading(PurposeOfTrading.ADDITIONAL_INCOME)
		                .build());


	         pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
	        		 .withSharesExperience(SharesExperience.NEVER)
	                 .withBinaryExperience(BinaryExperience.OCCASIONALLY)
	                 .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_500_5K)
	                 .withForExExperience(ForExExperience.NEVER)
	                 .withFinancialWorkExperience(FinancialWorkExperience.WORKED)
	                 .withCfdBinaryKnowledge(CfdBinaryKnowledge.SPECULATIVE)
	                 .withMainFactorKnowledge(MainFactorKnowledge.ANNOUNCEMENT)
	                 .withHowToCloseKnowledge(HowToCloseKnowledge.LONDON_STOCK)
	                 .withCfdLeverageKnowledge(CfdLeverageKnowledge.PROVIDES)
	                 .withStopLossKnowledge(StopLossKnowledge.MINIMIZE)
	                 .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K)
	                 .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.WARNING_CALL)
	                 .withAutomaticStopKnowledge(AutomaticStopKnowledge.EARNINGS)
	                 .withLossOn1to50Knowledge(LossOn1to50Knowledge.A1_800)
	                 .withLossOn1to200Knowledge(LossOn1to200Knowledge.A1_1800)
	                 .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_75)
	                 .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_75)
	                 .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_35)
	                 .build());
//	         FIXME not implemented
	         pages().creditCardDepositPage().waitforCreditCardDepositPage();
	    }

	    @Parameters({"countrycode"})
	    @Test
	    public void  onboardingWizardDeposit(String countrycode) {
	    	  pages().topNavigationPage().signUp();
		       pages().registrationDialog().register(countrycode);
		       pages().welcomepage().isStartBtnDisplayed();
		       pages().welcomepage().start();
		       pages().accountAdditionalDetailsPage().update(AccountAdditionalDetails.builder()
		               .withBirthDateDay("1")
		               .withBirthDateMonth("2")
		               .withBirthDateYear("1990")
		               .withCountryOfBirth("DE")
		               .withNationality("DE")
		               .build());
	      
		       
		       pages().fnsPersonalInformation().submit(PersonalInformation.builder()
		                .withEmploymentStatus(EmploymentStatus.SALARIED_EMPLOYEE)
		                .withIndustry(Industry.FINANCE)
		                .withEmployerName("fgsfds")
		                .withTaxResidenceCountry("DE")
		                .withUSReportabilityStatus(IsUSReportable.NO)
		                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.NO)
		                .withTaxIdentificationNumber("123456789")
		                .withEducationLevel(EducationLevel.POST_GRADUATE)
		                .withEducationField(EducationField.ACCOUNTING)
		                .withPoliticalExposureStatus(IsPoliticallyExposed.NO)
		                .withSourceOfFunds(SourceOfFunds.EMPLOYMENT)
		                .withAnnualIncome(AnnualIncome.INCOME_OVER_100K)
		                .withNetWealth(NetWealth.NET_WEALTH_OVER_300K)
		                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_OVER_50K)
		                .withPurposeOfTrading(PurposeOfTrading.ADDITIONAL_INCOME)
		                .build());
		       
		      
	         pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
	        		 .withSharesExperience(SharesExperience.NEVER)
	                 .withBinaryExperience(BinaryExperience.OCCASIONALLY)
	                 .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_500_5K)
	                 .withForExExperience(ForExExperience.NEVER)
	                 .withFinancialWorkExperience(FinancialWorkExperience.WORKED)
	                 .withCfdBinaryKnowledge(CfdBinaryKnowledge.SPECULATIVE)
	                 .withMainFactorKnowledge(MainFactorKnowledge.ANNOUNCEMENT)
	                 .withHowToCloseKnowledge(HowToCloseKnowledge.LONDON_STOCK)
	                 .withCfdLeverageKnowledge(CfdLeverageKnowledge.PROVIDES)
	                 .withStopLossKnowledge(StopLossKnowledge.MINIMIZE)
	                 .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K)
	                 .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.WARNING_CALL)
	                 .withAutomaticStopKnowledge(AutomaticStopKnowledge.EARNINGS)
	                 .withLossOn1to50Knowledge(LossOn1to50Knowledge.A1_800)
	                 .withLossOn1to200Knowledge(LossOn1to200Knowledge.A1_1800)
	                 .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_75)
	                 .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_75)
	                 .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_35)
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
	         
	         pages().uploadDocumentDialog().poiUploadIdCard();
	    }

//	    @Test
	    public void  onboardingWizardUploadPOI() {
	     	pages().topNavigationPage().signUp();
	        pages().registrationDialog().register(Country.SPAIN.getName());
	        
	    }
	    
//	    @Test
	    public void  onboardingWizardUploadPOR() {
	     	pages().topNavigationPage().signUp();
	        pages().registrationDialog().register(Country.SPAIN.getName());
	        
	    }
}
        


