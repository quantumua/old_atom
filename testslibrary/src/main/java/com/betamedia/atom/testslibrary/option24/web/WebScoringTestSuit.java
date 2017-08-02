package com.betamedia.atom.testslibrary.option24.web;


import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.testingtype.web.WebEndToEndTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static com.betamedia.atom.core.api.crm.form.entities.QuestionnaireAnswers.*;

/**
 * @author Lilian Medina
 *         Date: 5/15/17.
 */

public class WebScoringTestSuit extends WebEndToEndTest {
	 private static CustomerRO customer = CustomerRO.builder(WebSiteNamingStrategy.get()).build();
	      
	 	    	    
	 @Parameters({"countrycode"}) 
     @Test(description = "CTW-9050")
	    public void  scoringTest1(String countrycode) {
	    	CustomerRO customerRO = CustomerRO.builder(WebSiteNamingStrategy.get()).build();
	    	String userName = customerRO.getEmail();
		   pages().topNavigationPage().signUp();
	       pages().registrationDialog().register(customerRO, countrycode);
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
	                .withIndustry(Industry.ACCOUNTING)
	                .withEmployerName("fgsfds")
	                .withTaxResidenceCountry("DE")
	                .withUSReportabilityStatus(IsUSReportable.YES)
	                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.NO)
	                .withTaxIdentificationNumber("123456789")
	                .withEducationLevel(EducationLevel.POST_GRADUATE)
	                .withEducationField(EducationField.ACCOUNTING)
	                .withPoliticalExposureStatus(IsPoliticallyExposed.NO)
	                .withSourceOfFunds(SourceOfFunds.EMPLOYMENT)
	                .withAnnualIncome(AnnualIncome.INCOME_OVER_100K)
	                .withNetWealth(NetWealth.NET_WEALTH_OVER_300K)
	                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_OVER_50K)
	                .withPurposeOfTrading(PurposeOfTrading.SPECULATIVE)
	                .build());
	       
	       pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
	                 .withSharesExperience(SharesExperience.FREQUENTLY)
	                 .withBinaryExperience(BinaryExperience.NEVER)
	                 .withForExExperience(ForExExperience.FREQUENTLY)
	                 .withAverageYearlyForExVolume(AverageYearlyForExVolume.VOLUME_ABOVE_500K)
	                 .withCommonLeverage(CommonForExLeverage.LEVERAGE_ABOVE_1TO500)
	                 .withFinancialWorkExperience(FinancialWorkExperience.WORKED)
	                 .withCfdBinaryKnowledge(CfdBinaryKnowledge.SPECULATIVE)
	                 .withMainFactorKnowledge(MainFactorKnowledge.ANNOUNCEMENT)
	                 .withHowToCloseKnowledge(HowToCloseKnowledge.ONLY_PLATFORM)
	                 .withCfdLeverageKnowledge(CfdLeverageKnowledge.MAGNIFIES)
	                 .withStopLossKnowledge(StopLossKnowledge.MINIMIZE)
	                 .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K)
	                 .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.MARGIN_CALL)
	                 .withAutomaticStopKnowledge(AutomaticStopKnowledge.EQUITY_FALLS)
	                 .withLossOn1to50Knowledge(LossOn1to50Knowledge.A1_800)
	                 .withLossOn1to200Knowledge(LossOn1to200Knowledge.A1_1800)
	                 .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_60)
	                 .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_75)
	                 .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_35)
	                 .build());
	       
	       pages().creditCardDepositPage().waitforCreditCardDepositPage();
	       operations().onBoardingOperations().assertUsernameScore(userName,Double.valueOf(79));
	    }
	    @Parameters({"countrycode"}) 
     @Test(description = "CTW-9074")
	    public void  scoringTest2(String countrycode) {
	    	CustomerRO customerRO = CustomerRO.builder(WebSiteNamingStrategy.get()).build();
	    	String userName = customerRO.getEmail();
		   pages().topNavigationPage().signUp();
	       pages().registrationDialog().register(customerRO, countrycode);
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
	                .withEmploymentStatus(EmploymentStatus.UNEMPLOYED)
	                .withIndustry(Industry.FINANCE)
	                .withEmployerName("fgsfds")
	                .withTaxResidenceCountry("DE")
	                .withUSReportabilityStatus(IsUSReportable.YES)
	                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.NO)
	                .withTaxIdentificationNumber("123456789")
	                .withEducationLevel(EducationLevel.BACHELOR)
	                .withEducationField(EducationField.LAW)
	                .withPoliticalExposureStatus(IsPoliticallyExposed.YES)
	                .withPoliticalExposureComment("President")
	                .withSourceOfFunds(SourceOfFunds.SAVINGS)
	                .withAnnualIncome(AnnualIncome.INCOME_50K_100K)
	                .withNetWealth(NetWealth.NET_WEALTH_150K_300K)
	                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_25K_50K)
	                .withPurposeOfTrading(PurposeOfTrading.ADDITIONAL_INCOME)
	                .build());
	       
	       pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
	                 .withSharesExperience(SharesExperience.REGULARLY)
	                 .withBinaryExperience(BinaryExperience.FREQUENTLY)
	                 .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_ABOVE_10K)
	                 .withForExExperience(ForExExperience.REGULARLY)
	                 .withAverageYearlyForExVolume(AverageYearlyForExVolume.VOLUME_150K_500K)
	                 .withCommonLeverage(CommonForExLeverage.LEVERAGE_1TO200_1TO500)
	                 .withFinancialWorkExperience(FinancialWorkExperience.SEMINARS)
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
	                 .build());
	       
	       pages().creditCardDepositPage().waitforCreditCardDepositPage();
	       operations().onBoardingOperations().assertUsernameScore(userName,Double.valueOf(52));
	    }
	    
	    @Parameters({"countrycode"}) 
     @Test(description = "CTW-9075")
	    public void  scoringTest3(String countrycode) {
	    	CustomerRO customerRO = CustomerRO.builder(WebSiteNamingStrategy.get()).build();
	    	String userName = customerRO.getEmail();
		   pages().topNavigationPage().signUp();
	       pages().registrationDialog().register(customerRO, countrycode);
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
	                .withEmploymentStatus(EmploymentStatus.STUDENT)
	                .withIndustry(Industry.FUNDS)
	                .withEmployerName("fgsfds")
	                .withTaxResidenceCountry("DE")
	                .withUSReportabilityStatus(IsUSReportable.NO)
	                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.YES)
	                .withTaxIdentificationNumber("123456789")
	                .withEducationLevel(EducationLevel.SECONDARY)
	                .withEducationField(EducationField.COMPUTER)
	                .withPoliticalExposureStatus(IsPoliticallyExposed.NO)
	                .withSourceOfFunds(SourceOfFunds.RETIREMENT)
	                .withAnnualIncome(AnnualIncome.INCOME_25K_50K)
	                .withNetWealth(NetWealth.NET_WEALTH_50K_150K)
	                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_15K_25K)
	                .withPurposeOfTrading(PurposeOfTrading.HEDGING)
	                .build());
	       
	       pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
	                 .withSharesExperience(SharesExperience.OCCASIONALLY)
	                 .withBinaryExperience(BinaryExperience.REGULARLY)
	                 .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_5K_10K)
	                 .withForExExperience(ForExExperience.OCCASIONALLY)
	                 .withAverageYearlyForExVolume(AverageYearlyForExVolume.VOLUME_50K_150K)
	                 .withCommonLeverage(CommonForExLeverage.LEVERAGE_1TO50_1TO200)
	                 .withFinancialWorkExperience(FinancialWorkExperience.BOTH)
	                 .withCfdBinaryKnowledge(CfdBinaryKnowledge.PHYSICALLY_DELIVERING)
	                 .withMainFactorKnowledge(MainFactorKnowledge.EMPLOYEE_LAYOFFS)
	                 .withHowToCloseKnowledge(HowToCloseKnowledge.CANNOT_SELL)
	                 .withCfdLeverageKnowledge(CfdLeverageKnowledge.NONE)
	                 .withStopLossKnowledge(StopLossKnowledge.TAKE)
	                 .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_100K)
	                 .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.MARGIN_UPGRADE)
	                 .withAutomaticStopKnowledge(AutomaticStopKnowledge.OPEN_POSITION)
	                 .withLossOn1to50Knowledge(LossOn1to50Knowledge.A3_200)
	                 .withLossOn1to200Knowledge(LossOn1to200Knowledge.A3_1000)
	                 .build());
	       
	       pages().creditCardDepositPage().waitforCreditCardDepositPage();
	       operations().onBoardingOperations().assertUsernameScore(userName,Double.valueOf(46));
	    }
	    
	    @Parameters({"countrycode"}) 
     @Test(description = "CTW-9076")
	    public void  scoringTest4(String countrycode) {
	    	CustomerRO customerRO = CustomerRO.builder(WebSiteNamingStrategy.get()).build();
	    	String userName = customerRO.getEmail();
		   pages().topNavigationPage().signUp();
	       pages().registrationDialog().register(customerRO, countrycode);
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
	                .withEmploymentStatus(EmploymentStatus.RETIRED)
	                .withIndustry(Industry.ATTORNEYS)
	                .withEmployerName("fgsfds")
	                .withTaxResidenceCountry("DE")
	                .withUSReportabilityStatus(IsUSReportable.NO)
	                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.YES)
	                .withTaxIdentificationNumber("123456789")
	                .withEducationLevel(EducationLevel.PRIMARY)
	                .withEducationField(EducationField.MEDICINE)
	                .withPoliticalExposureStatus(IsPoliticallyExposed.NO)
	                .withSourceOfFunds(SourceOfFunds.INHERITANCE)
	                .withAnnualIncome(AnnualIncome.INCOME_15K_25K)
	                .withNetWealth(NetWealth.NET_WEALTH_15K_50K)
	                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_10K_15K)
	                .withPurposeOfTrading(PurposeOfTrading.OTHER)
	                .withPurposeOfTradingOther("Training")
	                .build());
	       
	       pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
	                 .withSharesExperience(SharesExperience.NEVER)
	                 .withBinaryExperience(BinaryExperience.OCCASIONALLY)
	                 .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_500_5K)
	                 .withForExExperience(ForExExperience.NEVER)
	                 .withFinancialWorkExperience(FinancialWorkExperience.NEITHER)
	                 .withCfdBinaryKnowledge(CfdBinaryKnowledge.SPECULATIVE)
	                 .withMainFactorKnowledge(MainFactorKnowledge.INTEREST_RATES)
	                 .withHowToCloseKnowledge(HowToCloseKnowledge.ONLY_PLATFORM)
	                 .withCfdLeverageKnowledge(CfdLeverageKnowledge.MAGNIFIES)
	                 .withStopLossKnowledge(StopLossKnowledge.BUY)
	                 .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K)
	                 .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.MARGIN_CALL)
	                 .withAutomaticStopKnowledge(AutomaticStopKnowledge.EQUITY_FALLS)
	                 .withLossOn1to50Knowledge(LossOn1to50Knowledge.A1_800)
	                 .withLossOn1to200Knowledge(LossOn1to200Knowledge.A1_1800)
	                 .build());
	       
	       pages().creditCardDepositPage().waitforCreditCardDepositPage();
	       operations().onBoardingOperations().assertUsernameScore(userName,Double.valueOf(41));
	    }
	    
	    @Parameters({"countrycode"}) 
     @Test(description = "CTW-9077")
	    public void  scoringTest5(String countrycode) {
	    	CustomerRO customerRO = CustomerRO.builder(WebSiteNamingStrategy.get()).build();
	    	String userName = customerRO.getEmail();
		   pages().topNavigationPage().signUp();
	       pages().registrationDialog().register(customerRO, countrycode);
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
	                .withEmploymentStatus(EmploymentStatus.SELF_EMPLOYED)
	                .withIndustry(Industry.COMPUTER)
	                .withEmployerName("automation test")
	                .withTaxResidenceCountry("DE")
	                .withUSReportabilityStatus(IsUSReportable.NO)
	                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.YES)
	                .withTaxIdentificationNumber("123456789")
	                .withEducationLevel(EducationLevel.NONE)
	                .withEducationField(EducationField.OTHER)
	                .withEducationFieldOther("Technical")
	                .withPoliticalExposureStatus(IsPoliticallyExposed.NO)
	                .withSourceOfFunds(SourceOfFunds.OTHER)
	                .withSourceOfFundsOther("Lottery")
	                .withAnnualIncome(AnnualIncome.INCOME_15KLESS)
	                .withNetWealth(NetWealth.NET_WEALTH_UNDER_15K)
	                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_UNDER_10K)
	                .withPurposeOfTrading(PurposeOfTrading.SPECULATIVE)
	                .build());
	       
	       pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
	                 .withSharesExperience(SharesExperience.NEVER)
	                 .withBinaryExperience(BinaryExperience.OCCASIONALLY)
	                 .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_UNDER_500)
	                 .withForExExperience(ForExExperience.FREQUENTLY)
	                 .withAverageYearlyForExVolume(AverageYearlyForExVolume.VOLUME_UNDER_50K)
	                 .withCommonLeverage(CommonForExLeverage.LEVERAGE_UNDER_1TO50)
	                 .withFinancialWorkExperience(FinancialWorkExperience.WORKED)
	                 .withCfdBinaryKnowledge(CfdBinaryKnowledge.PHYSICALLY_DELIVERING)
	                 .withMainFactorKnowledge(MainFactorKnowledge.INTEREST_RATES)
	                 .withHowToCloseKnowledge(HowToCloseKnowledge.LONDON_STOCK)
	                 .withCfdLeverageKnowledge(CfdLeverageKnowledge.MAGNIFIES)
	                 .withStopLossKnowledge(StopLossKnowledge.BUY)
	                 .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K)
	                 .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.MARGIN_CALL)
	                 .withAutomaticStopKnowledge(AutomaticStopKnowledge.EQUITY_FALLS)
	                 .withLossOn1to50Knowledge(LossOn1to50Knowledge.A1_800)
	                 .withLossOn1to200Knowledge(LossOn1to200Knowledge.A2_1200)
	                 .build());
	       
	       pages().creditCardDepositPage().waitforCreditCardDepositPage();
	       operations().onBoardingOperations().assertUsernameScore(userName,Double.valueOf(45));
	    }
	    
	    @Parameters({"countrycode"}) 
     @Test(description = "CTW-9078")
	    public void  scoringTest6(String countrycode) {
	    	CustomerRO customerRO = CustomerRO.builder(WebSiteNamingStrategy.get()).build();
	    	String userName = customerRO.getEmail();
		   pages().topNavigationPage().signUp();
	       pages().registrationDialog().register(customerRO, countrycode);
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
	                .withIndustry(Industry.OTHER)
	                .withIndustryOther("Stadistical area")
	                .withEmployerName("automation test")
	                .withTaxResidenceCountry("DE")
	                .withUSReportabilityStatus(IsUSReportable.NO)
	                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.YES)
	                .withTaxIdentificationNumber("123456789")
	                .withEducationLevel(EducationLevel.POST_GRADUATE)
	                .withEducationField(EducationField.ACCOUNTING)
	                .withPoliticalExposureStatus(IsPoliticallyExposed.NO)
	                .withSourceOfFunds(SourceOfFunds.EMPLOYMENT)
	                .withAnnualIncome(AnnualIncome.INCOME_OVER_100K)
	                .withNetWealth(NetWealth.NET_WEALTH_OVER_300K)
	                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_OVER_50K)
	                .withPurposeOfTrading(PurposeOfTrading.SPECULATIVE)
	                .build());
	       
	       pages().fnsTradingExperience().submit(TradingExperienceInfo.builder()
	                 .withSharesExperience(SharesExperience.NEVER)
	                 .withBinaryExperience(BinaryExperience.NEVER)
	                 .withForExExperience(ForExExperience.NEVER)
	                 .withFinancialWorkExperience(FinancialWorkExperience.WORKED)
	                 .withCfdBinaryKnowledge(CfdBinaryKnowledge.PHYSICALLY_DELIVERING)
	                 .withMainFactorKnowledge(MainFactorKnowledge.INTEREST_RATES)
	                 .withHowToCloseKnowledge(HowToCloseKnowledge.LONDON_STOCK)
	                 .withCfdLeverageKnowledge(CfdLeverageKnowledge.MAGNIFIES)
	                 .withStopLossKnowledge(StopLossKnowledge.BUY)
	                 .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K)
	                 .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.MARGIN_UPGRADE)
	                 .withAutomaticStopKnowledge(AutomaticStopKnowledge.EQUITY_FALLS)
	                 .withLossOn1to50Knowledge(LossOn1to50Knowledge.A1_800)
	                 .withLossOn1to200Knowledge(LossOn1to200Knowledge.A2_1200)
	                 .build());
	       
	       pages().riskWarning().waitForRiskWarning();
	       operations().onBoardingOperations().assertUsernameScore(userName,Double.valueOf(30));
	    }
}