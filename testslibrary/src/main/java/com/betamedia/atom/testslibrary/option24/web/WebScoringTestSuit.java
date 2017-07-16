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
	    @Parameters({"countrycode"}) 
        @Test(description = "CTW-9074")
	    public void  scoringTest2(String countrycode) {
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
	                .withEmploymentStatus(Questions.EmploymentStatus.UNEMPLOYED.get())
	                .withIndustry(Questions.Industry.FINANCE.get())
	                .withEmployerName("fgsfds")
	                .withTaxResidenceCountry("DE")
	                .withUSReportabilityStatus(Questions.HasTaxIdentificationNumber.YES.get())
	                .withTaxIdentificationNumberStatus(Questions.HasTaxIdentificationNumber.NO.get())
	                .withTaxIdentificationNumber("123456789")
	                .withEducationLevel(Questions.EducationLevel.BACHELOR.get())
	                .withEducationField(Questions.EducationField.LAW.get())
	                .withPoliticalExposureStatus(Questions.IsPoliticallyExposed.YES.get())
	                .withPoliticalExposureComment("President")
	                .withSourceOfFunds(Questions.SourceOfFunds.SAVINGS.get())
	                .withAnnualIncome(Questions.AnnualIncome.INCOME_50K_100K.get())
	                .withNetWealth(Questions.NetWealth.NET_WEALTH_150K_300K.get())
	                .withExpectedDepositsPerYear(Questions.ExpectedDepositsPerYear.DEPOSITS_25K_50K.get())
	                .withPurposeOfTrading(Questions.PurposeOfTrading.ADDITIONAL_INCOME.get())
	                .build());
	       
	       pages().fnsTradingExperience().submitOnWizard(TradingExperienceInfo.builder()
	                 .withSharesExperience(Questions.SharesExperience.REGULARLY.get())
	                 .withBinaryExperience(Questions.BinaryExperience.FREQUENTLY.get())
	                 .withAverageYearlyBinaryVolume(Questions.AverageYearlyBinaryVolume.VOLUME_ABOVE_10K.get())
	                 .withForExExperience(Questions.ForExExperience.REGULARLY.get())
	                 .withAverageYearlyForExVolume(Questions.AverageYearlyForExVolume.VOLUME_150K_500K.get())
	                 .withCommonLeverage(Questions.CommonLeverageForExVolume.LEVERAGE_1TO200_1TO500.get())
	                 .withFinancialWorkExperience(Questions.FinancialWorkExperience.SEMINARS.get())
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
	                 .build());
	       
	       pages().creditCardDepositPage().waitforCreditCardDepositPage();
	    }
	    
	    @Parameters({"countrycode"}) 
        @Test(description = "CTW-9075")
	    public void  scoringTest3(String countrycode) {
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
	                .withEmploymentStatus(Questions.EmploymentStatus.STUDENT.get())
	                .withIndustry(Questions.Industry.FUNDS.get())
	                .withEmployerName("fgsfds")
	                .withTaxResidenceCountry("DE")
	                .withUSReportabilityStatus(Questions.HasTaxIdentificationNumber.NO.get())
	                .withTaxIdentificationNumberStatus(Questions.HasTaxIdentificationNumber.YES.get())
	                .withTaxIdentificationNumber("123456789")
	                .withEducationLevel(Questions.EducationLevel.SECONDARY.get())
	                .withEducationField(Questions.EducationField.COMPUTER.get())
	                .withPoliticalExposureStatus(Questions.IsPoliticallyExposed.NO.get())
	                .withSourceOfFunds(Questions.SourceOfFunds.RETIREMENT.get())
	                .withAnnualIncome(Questions.AnnualIncome.INCOME_25K_50K.get())
	                .withNetWealth(Questions.NetWealth.NET_WEALTH_50K_150K.get())
	                .withExpectedDepositsPerYear(Questions.ExpectedDepositsPerYear.DEPOSITS_15K_25K.get())
	                .withPurposeOfTrading(Questions.PurposeOfTrading.HEDGING.get())
	                .build());
	       
	       pages().fnsTradingExperience().submitOnWizard(TradingExperienceInfo.builder()
	                 .withSharesExperience(Questions.SharesExperience.OCCASIONALLY.get())
	                 .withBinaryExperience(Questions.BinaryExperience.REGULARLY.get())
	                 .withAverageYearlyBinaryVolume(Questions.AverageYearlyBinaryVolume.VOLUME_5K_10K.get())
	                 .withForExExperience(Questions.ForExExperience.OCCASIONALLY.get())
	                 .withAverageYearlyForExVolume(Questions.AverageYearlyForExVolume.VOLUME_50K_150K.get())
	                 .withCommonLeverage(Questions.CommonLeverageForExVolume.LEVERAGE_1TO50_1TO200.get())
	                 .withFinancialWorkExperience(Questions.FinancialWorkExperience.BOTH.get())
	                 .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.PHYSICALLY_DELIVERING.get())
	                 .withMainFactorKnowledge(Questions.MainFactorKnowledge.EMPLOYEE_LAYOFFS.get())
	                 .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.CANNOT_SELL.get())
	                 .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.NONE.get())
	                 .withStopLossKnowledge(Questions.StopLossKnowledge.TAKE.get())
	                 .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_100K.get())
	                 .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.MARGIN_UPGRADE.get())
	                 .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.OPEN_POSITION.get())
	                 .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A3_200.get())
	                 .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A3_1000.get())
	                 .build());
	       
	       pages().creditCardDepositPage().waitforCreditCardDepositPage();
	    }
	    
	    @Parameters({"countrycode"}) 
        @Test(description = "CTW-9076")
	    public void  scoringTest4(String countrycode) {
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
	                .withEmploymentStatus(Questions.EmploymentStatus.RETIRED.get())
	                .withIndustry(Questions.Industry.ATTORNEYS.get())
	                .withEmployerName("fgsfds")
	                .withTaxResidenceCountry("DE")
	                .withUSReportabilityStatus(Questions.HasTaxIdentificationNumber.NO.get())
	                .withTaxIdentificationNumberStatus(Questions.HasTaxIdentificationNumber.YES.get())
	                .withTaxIdentificationNumber("123456789")
	                .withEducationLevel(Questions.EducationLevel.PRIMARY.get())
	                .withEducationField(Questions.EducationField.MEDICINE.get())
	                .withPoliticalExposureStatus(Questions.IsPoliticallyExposed.NO.get())
	                .withSourceOfFunds(Questions.SourceOfFunds.INHERITANCE.get())
	                .withAnnualIncome(Questions.AnnualIncome.INCOME_15K_25K.get())
	                .withNetWealth(Questions.NetWealth.NET_WEALTH_15K_50K.get())
	                .withExpectedDepositsPerYear(Questions.ExpectedDepositsPerYear.DEPOSITS_10K_15K.get())
	                .withPurposeOfTrading(Questions.PurposeOfTrading.OTHER.get())
	                .withPurposeOfTradingOther("Training")
	                .build());
	       
	       pages().fnsTradingExperience().submitOnWizard(TradingExperienceInfo.builder()
	                 .withSharesExperience(Questions.SharesExperience.NEVER.get())
	                 .withBinaryExperience(Questions.BinaryExperience.OCCASIONALLY.get())
	                 .withAverageYearlyBinaryVolume(Questions.AverageYearlyBinaryVolume.VOLUME_500_5K.get())
	                 .withForExExperience(Questions.ForExExperience.NEVER.get())                 
	                 .withFinancialWorkExperience(Questions.FinancialWorkExperience.NEITHER.get())
	                 .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.SPECULATIVE.get())
	                 .withMainFactorKnowledge(Questions.MainFactorKnowledge.INTEREST_RATES.get())
	                 .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.ONLY_PLATFORM.get())
	                 .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.MAGNIFIES.get())
	                 .withStopLossKnowledge(Questions.StopLossKnowledge.BUY.get())
	                 .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_1K.get())
	                 .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.MARGIN_CALL.get())
	                 .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EQUITY_FALLS.get())
	                 .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A1_800.get())
	                 .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A1_1800.get())
	                 .build());
	       
	       pages().creditCardDepositPage().waitforCreditCardDepositPage();
	    }
	    
	    @Parameters({"countrycode"}) 
        @Test(description = "CTW-9077")
	    public void  scoringTest5(String countrycode) {
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
	                .withEmploymentStatus(Questions.EmploymentStatus.SELF_EMPLOYED.get())
	                .withIndustry(Questions.Industry.COMPUTER.get())
	                .withEmployerName("automation test")
	                .withTaxResidenceCountry("DE")
	                .withUSReportabilityStatus(Questions.HasTaxIdentificationNumber.NO.get())
	                .withTaxIdentificationNumberStatus(Questions.HasTaxIdentificationNumber.YES.get())
	                .withTaxIdentificationNumber("123456789")
	                .withEducationLevel(Questions.EducationLevel.NONE.get())
	                .withEducationField(Questions.EducationField.OTHER.get())
	                .withEducationFieldOther("Technical")
	                .withPoliticalExposureStatus(Questions.IsPoliticallyExposed.NO.get())
	                .withSourceOfFunds(Questions.SourceOfFunds.OTHER.get())
	                .withSourceOfFundsOther("Lottery")
	                .withAnnualIncome(Questions.AnnualIncome.INCOME_15KLESS.get())
	                .withNetWealth(Questions.NetWealth.NET_WEALTH_UNDER_15K.get())
	                .withExpectedDepositsPerYear(Questions.ExpectedDepositsPerYear.DEPOSITS_UNDER_10K.get())
	                .withPurposeOfTrading(Questions.PurposeOfTrading.SPECULATIVE.get())
	                .build());
	       
	       pages().fnsTradingExperience().submitOnWizard(TradingExperienceInfo.builder()
	                 .withSharesExperience(Questions.SharesExperience.NEVER.get())
	                 .withBinaryExperience(Questions.BinaryExperience.OCCASIONALLY.get())
	                 .withAverageYearlyBinaryVolume(Questions.AverageYearlyBinaryVolume.VOLUME_UNDER_500.get())
	                 .withForExExperience(Questions.ForExExperience.FREQUENTLY.get())
	                 .withAverageYearlyForExVolume(Questions.AverageYearlyForExVolume.VOLUME_UNDER_50K.get())
	                 .withCommonLeverage(Questions.CommonLeverageForExVolume.LEVERAGE_UNDER_1TO50.get())
	                 .withFinancialWorkExperience(Questions.FinancialWorkExperience.WORKED.get())
	                 .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.PHYSICALLY_DELIVERING.get())
	                 .withMainFactorKnowledge(Questions.MainFactorKnowledge.INTEREST_RATES.get())
	                 .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.LONDON_STOCK.get())
	                 .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.MAGNIFIES.get())
	                 .withStopLossKnowledge(Questions.StopLossKnowledge.BUY.get())
	                 .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_1K.get())
	                 .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.MARGIN_CALL.get())
	                 .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EQUITY_FALLS.get())
	                 .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A1_800.get())
	                 .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A2_1200.get())
	                 .build());
	       
	       pages().creditCardDepositPage().waitforCreditCardDepositPage();
	    }
	    
	    @Parameters({"countrycode"}) 
        @Test(description = "CTW-9078")
	    public void  scoringTest6(String countrycode) {
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
	                .withIndustry(Questions.Industry.OTHER.get())
	                .withIndustryOther("Stadistical area")
	                .withEmployerName("automation test")
	                .withTaxResidenceCountry("DE")
	                .withUSReportabilityStatus(Questions.HasTaxIdentificationNumber.NO.get())
	                .withTaxIdentificationNumberStatus(Questions.HasTaxIdentificationNumber.YES.get())
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
	                 .withSharesExperience(Questions.SharesExperience.NEVER.get())
	                 .withBinaryExperience(Questions.BinaryExperience.NEVER.get())
	                 .withForExExperience(Questions.ForExExperience.NEVER.get())
	                 .withFinancialWorkExperience(Questions.FinancialWorkExperience.WORKED.get())
	                 .withCfdBinaryKnowledge(Questions.CfdBinaryKnowledge.PHYSICALLY_DELIVERING.get())
	                 .withMainFactorKnowledge(Questions.MainFactorKnowledge.INTEREST_RATES.get())
	                 .withHowToCloseKnowledge(Questions.HowToCloseKnowledge.LONDON_STOCK.get())
	                 .withCfdLeverageKnowledge(Questions.CfdLeverageKnowledge.MAGNIFIES.get())
	                 .withStopLossKnowledge(Questions.StopLossKnowledge.BUY.get())
	                 .withRequiredMarginKnowledge(Questions.RequiredMarginKnowledge.MARGIN_1K.get())
	                 .withMarginLevelDropKnowledge(Questions.MarginLevelDropKnowledge.MARGIN_UPGRADE.get())
	                 .withAutomaticStopKnowledge(Questions.AutomaticStopKnowledge.EQUITY_FALLS.get())
	                 .withLossOn1to50Knowledge(Questions.LossOn1to50Knowledge.A1_800.get())
	                 .withLossOn1to200Knowledge(Questions.LossOn1to200Knowledge.A2_1200.get())
	                 .build());
	       
	       pages().creditCardDepositPage().waitforCreditCardDepositPage();
	    }
}