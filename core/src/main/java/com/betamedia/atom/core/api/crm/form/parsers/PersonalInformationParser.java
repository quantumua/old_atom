package com.betamedia.atom.core.api.crm.form.parsers;

import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.atom.core.api.crm.form.entities.QuestionnaireData;

import static com.betamedia.atom.core.api.crm.form.entities.QuestionnaireAnswers.*;

/**
 * Created by mbelyaev on 5/23/17.
 */
public class PersonalInformationParser {
    public static PersonalInformation parse(QuestionnaireData data) {
        return PersonalInformation.builder()
                .withEmploymentStatus(EmploymentStatus.parse(data.getEmploymentStatus()))
                .withIndustry(Industry.parse(data.getIndustry()))
                .withIndustryOther(data.getIndustryOther())
                .withEmployerName(data.getEmployerName())
                .withTaxResidenceCountry(data.getTaxResidenceCountry())
                .withUSReportabilityStatus(IsUSReportable.parse(data.getIsUSReportable()))
                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.parse(data.getHasTaxIdentificationNumber()))
                .withTaxIdentificationNumber(data.getTaxIdentificationNumber())
                .withSocialSecurityNumber(data.getSocialSecurityNumber())
                .withEducationLevel(EducationLevel.parse(data.getEducationLevel()))
                .withEducationField(EducationField.parse(data.getEducationField()))
                .withEducationFieldOther(data.getEducationFieldOther())
                .withPoliticalExposureStatus(IsPoliticallyExposed.parse(data.getIsPoliticallyExposed()))
                .withPoliticalExposureComment(data.getPoliticalExposureComment())
                .withSourceOfFunds(SourceOfFunds.parse(data.getSourceOfFunds()))
                .withSourceOfFundsOther(data.getSourceOfFundsOther())
                .withAnnualIncome(AnnualIncome.parse(data.getAnnualIncome()))
                .withNetWealth(NetWealth.parse(data.getNetWealth()))
                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.parse(data.getExpectedDepositsPerYear()))
                .withPurposeOfTrading(PurposeOfTrading.parse(data.getPurposeOfTrading()))
                .withPurposeOfTradingOther(data.getPurposeOfTradingOther())
                .build();
    }

    private PersonalInformationParser() {
    }

}
