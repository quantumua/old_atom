package com.betamedia.atom.testslibrary.option24.end2end.crm.experienceLevels;

import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.api.tp.entities.response.CRMCustomer;
import com.betamedia.atom.core.testingtype.widgets.WidgetsEndToEndTest;
import static com.betamedia.atom.core.api.crm.form.entities.QuestionnaireAnswers.*;

/**
 * Created by mbelyaev on 5/22/17.
 */
public abstract class AbstractUserExperienceTest extends WidgetsEndToEndTest {
    protected PersonalInformation personalInfoScore10() {
        return PersonalInformation.builder()
                .withEmploymentStatus(EmploymentStatus.SALARIED_EMPLOYEE)
                .withIndustry(Industry.FINANCE)
                .withEmployerName("fgsfds")
                .withTaxResidenceCountry("JP")
                .withUSReportabilityStatus(IsUSReportable.YES)
                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.NO)
                .withSocialSecurityNumber("123456789")
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

    protected PersonalInformation personalInfoScoreMax() {
        return PersonalInformation.builder()
                .withEmploymentStatus(EmploymentStatus.SALARIED_EMPLOYEE)
                .withIndustry(Industry.ACCOUNTING)
                .withEmployerName("erter")
                .withTaxResidenceCountry("JP")
                .withUSReportabilityStatus(IsUSReportable.YES)
                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.NO)
                .withSocialSecurityNumber("123456789")
                .withEducationLevel(EducationLevel.POST_GRADUATE)
                .withEducationField(EducationField.ACCOUNTING)
                .withPoliticalExposureStatus(IsPoliticallyExposed.NO)
                .withSourceOfFunds(SourceOfFunds.EMPLOYMENT)
                .withAnnualIncome(AnnualIncome.INCOME_OVER_100K)
                .withNetWealth(NetWealth.NET_WEALTH_OVER_300K)
                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_OVER_50K)
                .withPurposeOfTrading(PurposeOfTrading.SPECULATIVE)
                .build();
    }

    protected PersonalInformation personalInfoScore0() {
        return PersonalInformation.builder()
                .withEmploymentStatus(EmploymentStatus.SALARIED_EMPLOYEE)
                .withIndustry(Industry.COMPUTER)
                .withEmployerName("fgsfds")
                .withTaxResidenceCountry("JP")
                .withUSReportabilityStatus(IsUSReportable.YES)
                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.NO)
                .withSocialSecurityNumber("123456789")
                .withEducationLevel(EducationLevel.SECONDARY)
                .withEducationField(EducationField.COMPUTER)
                .withPoliticalExposureStatus(IsPoliticallyExposed.NO)
                .withSourceOfFunds(SourceOfFunds.EMPLOYMENT)
                .withAnnualIncome(AnnualIncome.INCOME_OVER_100K)
                .withNetWealth(NetWealth.NET_WEALTH_OVER_300K)
                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_OVER_50K)
                .withPurposeOfTrading(PurposeOfTrading.ADDITIONAL_INCOME)
                .build();
    }

    protected PersonalInformation personalInfoScore5() {
        return PersonalInformation.builder()
                .withEmploymentStatus(EmploymentStatus.SALARIED_EMPLOYEE)
                .withIndustry(Industry.FINANCE)
                .withEmployerName("fgsfds")
                .withTaxResidenceCountry("JP")
                .withUSReportabilityStatus(IsUSReportable.YES)
                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.NO)
                .withSocialSecurityNumber("123456789")
                .withEducationLevel(EducationLevel.SECONDARY)
                .withEducationField(EducationField.COMPUTER)
                .withPoliticalExposureStatus(IsPoliticallyExposed.NO)
                .withSourceOfFunds(SourceOfFunds.EMPLOYMENT)
                .withAnnualIncome(AnnualIncome.INCOME_OVER_100K)
                .withNetWealth(NetWealth.NET_WEALTH_OVER_300K)
                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_OVER_50K)
                .withPurposeOfTrading(PurposeOfTrading.ADDITIONAL_INCOME)
                .build();
    }

    /**
     * Build trading experience info with 0 score
     * @return - TradingExperienceInfo instance
     */
    protected TradingExperienceInfo tradingExperienceInfoWith0Score() {
        return TradingExperienceInfo.builder()
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
                .build();
    }

    protected CRMCustomer crmRegisterAndLogIn() {
        CRMCustomer customer = operations().customerOperations().register();
        pages().navigation().login();
        pages().loginPage().login(customer.getUserName(), CustomerRO.CustomerROBuilder.DEFAULT_PASSWORD);
        return customer;
    }
}
