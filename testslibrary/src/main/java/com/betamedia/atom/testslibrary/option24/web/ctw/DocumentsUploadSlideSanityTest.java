package com.betamedia.atom.testslibrary.option24.web.ctw;

import com.betamedia.atom.core.api.crm.form.entities.*;
import com.betamedia.atom.core.testingtype.web.WebEndToEndTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;

import static com.betamedia.atom.core.api.crm.form.entities.QuestionnaireAnswers.*;

/**
 * @author Leonid Artemiev
 * @since 7/20/17
 */

public class DocumentsUploadSlideSanityTest extends WebEndToEndTest {

    @BeforeMethod
    @Parameters({"countrycode"})
    public void before(@Optional("de") String countrycode) {
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(CustomerRO.builder(WebSiteNamingStrategy.get()).setCountryCode(countrycode).build());
        pages().welcomeDialog().isStartBtnDisplayed();
        pages().welcomeDialog().start();
        pages().accountAdditionalDetails().update(AccountAdditionalDetails.builder().build());
        PersonalInformation personalInfo = getPersonalInformation();
        pages().fnsPersonalInformation().submit(personalInfo);
        pages().fnsTradingExperience().submit(getTradingExperienceInfo());
        pages().creditCardDeposit().submit((CreditCardDeposit.builder().build()));
        pages().thankYouPage().doContinue();
        pages().fnsEmployerInfo().submit(personalInfo);
    }

    /*
     *[TestLink] CTW-5765:Successful upload document: POI section - Passport doc
     */
    @Test(description = "CTW-5765:Successful upload document: POI section - Passport doc")
    public void successfulUploadDocumentPOISectionPassportDoc() {
        pages().uploadDocumentDialog().uploadPassport();
        softAssert().assertFalse(pages().uploadDocumentDialog().poiBackImageExists(), "POI back image should not exists");
        softAssert().assertTrue(pages().thankYouPage().startTradeExists(), "Passport uploaded and user is ready to start Trade");
    }

    /*
     *[TestLink] CTW-5767:Successful upload document: POI section - Driver license doc
     */
    @Test(description = "CTW-5767:Successful upload document: POI section - Driver license doc")
    public void successfulUploadDocumentPOISectionDriverLicenceDoc() {
        pages().uploadDocumentDialog().uploadDriverLicense();
        softAssert().assertTrue(pages().thankYouPage().startTradeExists(), "Driver license uploaded and user is ready to start Trade");
    }

    /*
     *[TestLink] CTW-5768:Successful upload document: POI section - Identity card doc
     */
    @Test(description = "CTW-5768:Successful upload document: POI section - Identity card doc")
    public void successfulUploadDocumentPOISectionIdentityCardDoc() {
        pages().uploadDocumentDialog().uploadIdCard();
        softAssert().assertTrue(pages().thankYouPage().startTradeExists(), "ID Card uploaded and user is ready to start Trade");
    }

    private PersonalInformation getPersonalInformation() {
        return PersonalInformation.builder()
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
                .build();
    }

    private TradingExperienceInfo getTradingExperienceInfo(){
        return TradingExperienceInfo.builder()
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
                .build();
    }
}

