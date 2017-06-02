package com.betamedia.qe.af.core.api.crm.form.parsers;

import com.betamedia.qe.af.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.qe.af.core.api.crm.form.entities.QuestionnaireData;

/**
 * Created by mbelyaev on 5/23/17.
 */
public class PersonalInformationParser {
    public static PersonalInformation parse(QuestionnaireData data) {
        return PersonalInformation.builder()
                .withEmploymentStatus(data.getEmploymentStatus())
                .withIndustry(data.getIndustry())
                .withIndustryOther(data.getIndustryOther())
                .withEmployerName(data.getEmployerName())
                .withTaxResidenceCountry(data.getTaxResidenceCountry())
                .withUSReportabilityStatus(data.getIsUSReportable())
                .withTaxIdentificationNumberStatus(data.getHasTaxIdentificationNumber())
                .withTaxIdentificationNumber(data.getTaxIdentificationNumber())
                .withSocialSecurityNumber(data.getSocialSecurityNumber())
                .withEducationLevel(data.getEducationLevel())
                .withEducationField(data.getEducationField())
                .withEducationFieldOther(data.getEducationFieldOther())
                .withPoliticalExposureStatus(data.getIsPoliticallyExposed())
                .withPoliticalExposureComment(data.getPoliticalExposureComment())
                .withSourceOfFunds(data.getSourceOfFunds())
                .withSourceOfFundsOther(data.getSourceOfFundsOther())
                .withAnnualIncome(data.getAnnualIncome())
                .withNetWealth(data.getNetWealth())
                .withExpectedDepositsPerYear(data.getExpectedDepositsPerYear())
                .withPurposeOfTrading(data.getPurposeOfTrading())
                .withPurposeOfTradingOther(data.getPurposeOfTradingOther())
                .build();
    }
}
