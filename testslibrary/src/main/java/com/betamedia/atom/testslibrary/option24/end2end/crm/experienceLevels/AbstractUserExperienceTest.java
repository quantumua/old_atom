package com.betamedia.atom.testslibrary.option24.end2end.crm.experienceLevels;

import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.atom.core.testingtype.tp.TPEndToEndTest;
import com.betamedia.atom.testslibrary.option24.end2end.crm.newQuestionnaries.Questions;

/**
 * Created by mbelyaev on 5/22/17.
 */
public abstract class AbstractUserExperienceTest extends TPEndToEndTest {
    protected PersonalInformation personalInfoScore10() {
        return PersonalInformation.builder()
                .withEmploymentStatus(Questions.EmploymentStatus.SALARIED_EMPLOYEE.get())
                .withIndustry(Questions.Industry.FINANCE.get())
                .withEmployerName("fgsfds")
                .withTaxResidenceCountry("JP")
                .withUSReportabilityStatus(Questions.IsUSReportable.YES.get())
                .withTaxIdentificationNumberStatus(Questions.HasTaxIdentificationNumber.NO.get())
                .withSocialSecurityNumber("123456789")
                .withEducationLevel(Questions.EducationLevel.POST_GRADUATE.get())
                .withEducationField(Questions.EducationField.ACCOUNTING.get())
                .withPoliticalExposureStatus(Questions.IsPoliticallyExposed.NO.get())
                .withSourceOfFunds(Questions.SourceOfFunds.EMPLOYMENT.get())
                .withAnnualIncome(Questions.AnnualIncome.INCOME_OVER_100K.get())
                .withNetWealth(Questions.NetWealth.NET_WEALTH_OVER_300K.get())
                .withExpectedDepositsPerYear(Questions.ExpectedDepositsPerYear.DEPOSITS_OVER_50K.get())
                .withPurposeOfTrading(Questions.PurposeOfTrading.ADDITIONAL_INCOME.get())
                .build();
    }

    protected PersonalInformation personalInfoScore0() {
        return PersonalInformation.builder()
                .withEmploymentStatus(Questions.EmploymentStatus.SALARIED_EMPLOYEE.get())
                .withIndustry(Questions.Industry.COMPUTER.get())
                .withEmployerName("fgsfds")
                .withTaxResidenceCountry("JP")
                .withUSReportabilityStatus(Questions.IsUSReportable.YES.get())
                .withTaxIdentificationNumberStatus(Questions.HasTaxIdentificationNumber.NO.get())
                .withSocialSecurityNumber("123456789")
                .withEducationLevel(Questions.EducationLevel.SECONDARY.get())
                .withEducationField(Questions.EducationField.COMPUTER.get())
                .withPoliticalExposureStatus(Questions.IsPoliticallyExposed.NO.get())
                .withSourceOfFunds(Questions.SourceOfFunds.EMPLOYMENT.get())
                .withAnnualIncome(Questions.AnnualIncome.INCOME_OVER_100K.get())
                .withNetWealth(Questions.NetWealth.NET_WEALTH_OVER_300K.get())
                .withExpectedDepositsPerYear(Questions.ExpectedDepositsPerYear.DEPOSITS_OVER_50K.get())
                .withPurposeOfTrading(Questions.PurposeOfTrading.ADDITIONAL_INCOME.get())
                .build();
    }

    protected PersonalInformation personalInfoScore5() {
        return PersonalInformation.builder()
                .withEmploymentStatus(Questions.EmploymentStatus.SALARIED_EMPLOYEE.get())
                .withIndustry(Questions.Industry.FINANCE.get())
                .withEmployerName("fgsfds")
                .withTaxResidenceCountry("JP")
                .withUSReportabilityStatus(Questions.IsUSReportable.YES.get())
                .withTaxIdentificationNumberStatus(Questions.HasTaxIdentificationNumber.NO.get())
                .withSocialSecurityNumber("123456789")
                .withEducationLevel(Questions.EducationLevel.SECONDARY.get())
                .withEducationField(Questions.EducationField.COMPUTER.get())
                .withPoliticalExposureStatus(Questions.IsPoliticallyExposed.NO.get())
                .withSourceOfFunds(Questions.SourceOfFunds.EMPLOYMENT.get())
                .withAnnualIncome(Questions.AnnualIncome.INCOME_OVER_100K.get())
                .withNetWealth(Questions.NetWealth.NET_WEALTH_OVER_300K.get())
                .withExpectedDepositsPerYear(Questions.ExpectedDepositsPerYear.DEPOSITS_OVER_50K.get())
                .withPurposeOfTrading(Questions.PurposeOfTrading.ADDITIONAL_INCOME.get())
                .build();
    }
}
