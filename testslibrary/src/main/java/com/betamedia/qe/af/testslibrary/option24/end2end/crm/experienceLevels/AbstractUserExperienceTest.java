package com.betamedia.qe.af.testslibrary.option24.end2end.crm.experienceLevels;

import com.betamedia.qe.af.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.qe.af.core.testingtype.tp.TPEndToEndTest;

import static com.betamedia.qe.af.testslibrary.option24.end2end.crm.newQuestionnaries.Questions.*;

/**
 * Created by mbelyaev on 5/22/17.
 */
public abstract class AbstractUserExperienceTest extends TPEndToEndTest {
    protected PersonalInformation personalInfoScore10() {
        return PersonalInformation.builder()
                .withEmploymentStatus(EmploymentStatus.SALARIED_EMPLOYEE.get())
                .withIndustry(Industry.FINANCE.get())
                .withEmployerName("fgsfds")
                .withTaxResidenceCountry("JP")
                .withUSReportabilityStatus(IsUSReportable.YES.get())
                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.NO.get())
                .withSocialSecurityNumber("123456789")
                .withEducationLevel(EducationLevel.POST_GRADUATE.get())
                .withEducationField(EducationField.ACCOUNTING.get())
                .withPoliticalExposureStatus(IsPoliticallyExposed.NO.get())
                .withSourceOfFunds(SourceOfFunds.EMPLOYMENT.get())
                .withAnnualIncome(AnnualIncome.INCOME_OVER_100K.get())
                .withNetWealth(NetWealth.NET_WEALTH_OVER_300K.get())
                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_OVER_50K.get())
                .withPurposeOfTrading(PurposeOfTrading.ADDITIONAL_INCOME.get())
                .build();
    }

    protected PersonalInformation personalInfoScore0() {
        return PersonalInformation.builder()
                .withEmploymentStatus(EmploymentStatus.SALARIED_EMPLOYEE.get())
                .withIndustry(Industry.COMPUTER.get())
                .withEmployerName("fgsfds")
                .withTaxResidenceCountry("JP")
                .withUSReportabilityStatus(IsUSReportable.YES.get())
                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.NO.get())
                .withSocialSecurityNumber("123456789")
                .withEducationLevel(EducationLevel.SECONDARY.get())
                .withEducationField(EducationField.COMPUTER.get())
                .withPoliticalExposureStatus(IsPoliticallyExposed.NO.get())
                .withSourceOfFunds(SourceOfFunds.EMPLOYMENT.get())
                .withAnnualIncome(AnnualIncome.INCOME_OVER_100K.get())
                .withNetWealth(NetWealth.NET_WEALTH_OVER_300K.get())
                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_OVER_50K.get())
                .withPurposeOfTrading(PurposeOfTrading.ADDITIONAL_INCOME.get())
                .build();
    }

    protected PersonalInformation personalInfoScore5() {
        return PersonalInformation.builder()
                .withEmploymentStatus(EmploymentStatus.SALARIED_EMPLOYEE.get())
                .withIndustry(Industry.FINANCE.get())
                .withEmployerName("fgsfds")
                .withTaxResidenceCountry("JP")
                .withUSReportabilityStatus(IsUSReportable.YES.get())
                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.NO.get())
                .withSocialSecurityNumber("123456789")
                .withEducationLevel(EducationLevel.SECONDARY.get())
                .withEducationField(EducationField.COMPUTER.get())
                .withPoliticalExposureStatus(IsPoliticallyExposed.NO.get())
                .withSourceOfFunds(SourceOfFunds.EMPLOYMENT.get())
                .withAnnualIncome(AnnualIncome.INCOME_OVER_100K.get())
                .withNetWealth(NetWealth.NET_WEALTH_OVER_300K.get())
                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_OVER_50K.get())
                .withPurposeOfTrading(PurposeOfTrading.ADDITIONAL_INCOME.get())
                .build();
    }
}
