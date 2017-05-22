package com.betamedia.qe.af.testslibrary.option24.end2end.crm;

import com.betamedia.qe.af.core.api.crm.form.builders.PersonalInformationBuilder;
import com.betamedia.qe.af.core.testingtype.tp.TPResourceAwareEndToEndTest;

/**
 * Created by mbelyaev on 5/22/17.
 */
public abstract class AbstractUserExperienceTest extends TPResourceAwareEndToEndTest {
    protected PersonalInformationBuilder.PersonalInformation personalInfoScore10() {
        return new PersonalInformationBuilder()
                .withEmploymentStatus("employmentStatusSalariedEmployee")
                .withIndustry("industryFinance")
                .withEmployerName("fgsfds")
                .withTaxResidenceCountry("JP")
                .withUSReportabilityStatus("usReportableYes")
                .withTaxIdentificationNumberStatus("tinNo")
                .withSocialSecurityNumber("123456789")
                .withEducationLevel("levelOfEducationPostGraduate")
                .withEducationField("fieldOfStudyAccounting")
                .withPoliticalExposureStatus("politicallyExposedNo")
                .withSourceOfFunds("sourceOfFundsEmployment")
                .withAnnualIncome("totalAnnualIncomeOver100k")
                .withNetWealth("netWealthOver300k")
                .withExpectedDepositsPerYear("expectedDepositsOver50k")
                .withPurposeOfTrading("purposeOfTradingAdditionalIncome")
                .build();
    }

    protected PersonalInformationBuilder.PersonalInformation personalInfoScore0() {
        return new PersonalInformationBuilder()
                .withEmploymentStatus("employmentStatusSalariedEmployee")
                .withIndustry("industryComputer")
                .withEmployerName("fgsfds")
                .withTaxResidenceCountry("JP")
                .withUSReportabilityStatus("usReportableYes")
                .withTaxIdentificationNumberStatus("tinNo")
                .withSocialSecurityNumber("123456789")
                .withEducationLevel("levelOfEducationSecondary")
                .withEducationField("fieldOfStudyComputer")
                .withPoliticalExposureStatus("politicallyExposedNo")
                .withSourceOfFunds("sourceOfFundsEmployment")
                .withAnnualIncome("totalAnnualIncomeOver100k")
                .withNetWealth("netWealthOver300k")
                .withExpectedDepositsPerYear("expectedDepositsOver50k")
                .withPurposeOfTrading("purposeOfTradingAdditionalIncome")
                .build();
    }

    protected PersonalInformationBuilder.PersonalInformation personalInfoScore5() {
        return new PersonalInformationBuilder()
                .withEmploymentStatus("employmentStatusSalariedEmployee")
                .withIndustry("industryFinance")
                .withEmployerName("fgsfds")
                .withTaxResidenceCountry("JP")
                .withUSReportabilityStatus("usReportableYes")
                .withTaxIdentificationNumberStatus("tinNo")
                .withSocialSecurityNumber("123456789")
                .withEducationLevel("levelOfEducationSecondary")
                .withEducationField("fieldOfStudyComputer")
                .withPoliticalExposureStatus("politicallyExposedNo")
                .withSourceOfFunds("sourceOfFundsEmployment")
                .withAnnualIncome("totalAnnualIncomeOver100k")
                .withNetWealth("netWealthOver300k")
                .withExpectedDepositsPerYear("expectedDepositsOver50k")
                .withPurposeOfTrading("purposeOfTradingAdditionalIncome")
                .build();
    }
}
