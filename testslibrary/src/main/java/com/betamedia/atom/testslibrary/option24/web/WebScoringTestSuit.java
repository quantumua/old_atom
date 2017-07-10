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

public class WebScoringTestSuit extends WEBEndToEndTest {
	 private static CustomerRO customer = CustomerRO.builder(WebSiteNamingStrategy.get()).build(); 	       
	      
	 	    	    
	    @Parameters({"countrycode"}) 
        @Test(description = "CTW-9050")
	    public void  scoringTest1(String countrycode) {
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
	                .withIndustry(Questions.Industry.ACCOUNTING.get())
	                .withEmployerName("fgsfds")
	                .withTaxResidenceCountry("DE")
	                .withUSReportabilityStatus(Questions.HasTaxIdentificationNumber.YES.get())
	                .withTaxIdentificationNumberStatus(Questions.HasTaxIdentificationNumber.NO.get())
	                .withTaxIdentificationNumber("123456789")
	                .withEducationLevel(Questions.EducationLevel.POST_GRADUATE.get())
	                .withEducationField(Questions.EducationField.ACCOUNTING.get())
	                .withPoliticalExposureStatus(Questions.IsPoliticallyExposed.NO.get())
	                .withSourceOfFunds(Questions.SourceOfFunds.EMPLOYMENT.get())
	                .withAnnualIncome(Questions.AnnualIncome.INCOME_OVER_100K.get())
	                .withNetWealth(Questions.NetWealth.NET_WEALTH_OVER_300K.get())
	                .withExpectedDepositsPerYear(Questions.ExpectedDepositsPerYear.DEPOSITS_OVER_50K.get())
	                .withPurposeOfTrading(Questions.PurposeOfTrading.SPECULATIVE.get())
	                .build());
	       
	       pages().fnsTradingExperience().submitOnWizard(TradingExperienceInfo.builder()
	                 .withSharesExperience(Questions.SharesExperience.FREQUENTLY.get())
	                 .withBinaryExperience(Questions.BinaryExperience.NEVER.get())
	                 .withForExExperience(Questions.ForExExperience.FREQUENTLY.get())
	                 .withAverageYearlyForExVolume(Questions.AverageYearlyForExVolume.VOLUME_ABOVE_500K.get())
	                 .withCommonLeverage(Questions.CommonLeverageForExVolume.LEVERAGE_ABOVE_1TO500.get())
	                 .withFinancialWorkExperience(Questions.FinancialWorkExperience.WORKED.get())
	                 .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.SPECULATIVE.get())
	                 .withMainFactorKnowledge(Questions.MainFactorKnowledge.ANNOUNCEMENT.get())
	                 .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.ONLY_PLATFORM.get())
	                 .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.MAGNIFIES.get())
	                 .withStopLossKnowledge(Questions.StopLossKnowledge.MINIMIZE.get())
	                 .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_1K.get())
	                 .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.MARGIN_CALL.get())
	                 .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EQUITY_FALLS.get())
	                 .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A1_800.get())
	                 .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A1_1800.get())
	                 .withBinaryInvestProfitKnowledge(Questions.BinaryInvestProfitKnowledge.PROFIT_60.get())
	                 .withBinaryInvestLossKnowledge(Questions.BinaryInvestLossKnowledge.LOSS_75.get())
	                 .withBinaryProbabilityKnowledge(Questions.BinaryProbabilityKnowledge.MONEY_35.get())
	                 .build());
	       
	       pages().creditCardDepositPage().waitforCreditCardDepositPage();
	    }
	  
	    
}