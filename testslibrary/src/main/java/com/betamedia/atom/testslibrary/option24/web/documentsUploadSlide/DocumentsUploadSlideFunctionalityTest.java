package com.betamedia.atom.testslibrary.option24.web.documentsUploadSlide;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WebSiteNamingStrategy;
import com.betamedia.atom.core.api.web.form.CustomerRegistrationInfo;
import com.betamedia.atom.core.testingtype.web.WebEndToEndTest;
import com.betamedia.atom.core.testlink.annotations.TestLinkProperties;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.betamedia.atom.core.api.crm.form.entities.QuestionnaireAnswers.*;

/**
 * @author Leonid Artemiev
 * @since 8/10/17
 */

public class DocumentsUploadSlideFunctionalityTest extends WebEndToEndTest {

    @BeforeMethod
    @Parameters({"countrycode", "phonecountryprefix"})
    public void before(@Optional("Germany") String countrycode, @Optional("+49") String phonecountryprefix) {
        pages().topNavigationPage().signUp();
        pages().registrationDialog().register(CustomerRegistrationInfo.builder(WebSiteNamingStrategy.get()).withCountry(countrycode)
                .withPhoneCountryPrefix(phonecountryprefix)
                .build());
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
     *[TestLink] CTW-5691:SEU: Upload Documents slide contains Exit (X) button test
     */
    @Test(description = "CTW-5691:SEU: Upload Documents slide contains Exit (X) button test")
    @TestLinkProperties(displayId = "CTW-5691")
    public void uploadDocumentsSlideContainsExitXButtonTest() {
        softAssert().assertTrue(pages().uploadDocumentDialog().isExitButtonExists(), "Upload Documents slide contain an Exit (X) button.");
        pages().uploadDocumentDialog().close();
        softAssert().assertTrue(pages().confirmCloseMessage().exists(), "Exit (X) button is working correct. Expected message is present.");
        pages().confirmCloseMessage().dismissClose();
        softAssert().assertFalse(pages().confirmCloseMessage().exists(), "Pop up message closed. (No) button is working correct.");
        pages().uploadDocumentDialog().close();
        pages().confirmCloseMessage().acceptClose();
        pages().topNavigationPage().signUp();
        softAssert().assertTrue(pages().topNavigationPage().isLoggedIn(), "Pop up message closed. (Yes) button is working correct. User remained on the Trading platform.");
    }

    /*
     *[TestLink] CTW-5730:SEU: Each slide section can be collapsed / expanded
     */
    @Test(description = "CTW-5730:SEU: Each slide section can be collapsed / expanded")
    @TestLinkProperties(displayId = "CTW-5730")
    public void eachSlideSectionCanBeCollapsedExpanded() {
        pages().uploadDocumentDialog().poiClickHeader();
        softAssert().assertTrue(pages().uploadDocumentDialog().isPoiExpanded(), "POI section not expanded.");
        pages().uploadDocumentDialog().poiClickHeader();
        softAssert().assertTrue(pages().uploadDocumentDialog().isPoiCollapsed(), "POI section not collapsed.");
        pages().uploadDocumentDialog().porClickHeader();
        softAssert().assertTrue(pages().uploadDocumentDialog().isPorExpanded(), "POR section not expanded.");
        pages().uploadDocumentDialog().porClickHeader();
        softAssert().assertTrue(pages().uploadDocumentDialog().isPorCollapsed(), "POR section not collapsed.");
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

