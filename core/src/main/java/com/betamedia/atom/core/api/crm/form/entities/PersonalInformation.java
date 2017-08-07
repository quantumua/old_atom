package com.betamedia.atom.core.api.crm.form.entities;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static com.betamedia.atom.core.api.crm.form.entities.QuestionnaireAnswers.*;

/**
 * @author mbelyaev
 * @since 5/18/17
 */
public class PersonalInformation {
    public final String employmentStatus;
    public final String industry;
    public final String industryOther;
    public final String employerName;
    public final String taxResidenceCountry;
    public final String isUSReportable;
    public final String hasTaxIdentificationNumber;
    public final String taxIdentificationNumber;
    public final String socialSecurityNumber;
    public final String educationLevel;
    public final String educationField;
    public final String educationFieldOther;
    public final String isPoliticallyExposed;
    public final String politicalExposureComment;
    public final String sourceOfFunds;
    public final String sourceOfFundsOther;
    public final String annualIncome;
    public final String netWealth;
    public final String expectedDepositsPerYear;
    public final String purposeOfTrading;
    public final String purposeOfTradingOther;
    public final int expectedScore;

    private PersonalInformation(String employmentStatus, String industry, String industryOther, String employerName, String taxResidenceCountry, String isUSReportable, String hasTaxIdentificationNumber, String taxIdentificationNumber, String socialSecurityNumber, String educationLevel, String educationField, String educationFieldOther, String isPoliticallyExposed, String politicalExposureComment, String sourceOfFunds, String sourceOfFundsOther, String annualIncome, String netWealth, String expectedDepositsPerYear, String purposeOfTrading, String purposeOfTradingOther, int expectedScore) {
        this.employmentStatus = employmentStatus;
        this.industry = industry;
        this.industryOther = industryOther;
        this.employerName = employerName;
        this.taxResidenceCountry = taxResidenceCountry;
        this.isUSReportable = isUSReportable;
        this.hasTaxIdentificationNumber = hasTaxIdentificationNumber;
        this.taxIdentificationNumber = taxIdentificationNumber;
        this.socialSecurityNumber = socialSecurityNumber;
        this.educationLevel = educationLevel;
        this.educationField = educationField;
        this.educationFieldOther = educationFieldOther;
        this.isPoliticallyExposed = isPoliticallyExposed;
        this.politicalExposureComment = politicalExposureComment;
        this.sourceOfFunds = sourceOfFunds;
        this.sourceOfFundsOther = sourceOfFundsOther;
        this.annualIncome = annualIncome;
        this.netWealth = netWealth;
        this.expectedDepositsPerYear = expectedDepositsPerYear;
        this.purposeOfTrading = purposeOfTrading;
        this.purposeOfTradingOther = purposeOfTradingOther;
        this.expectedScore = expectedScore;
    }

    public static PersonalInformationBuilder builder() {
        return new PersonalInformationBuilder();
    }

    @Override
    public String toString() {
        return "PersonalInformation{" +
                "employmentStatus='" + employmentStatus + '\'' +
                ", industry='" + industry + '\'' +
                ", industryOther='" + industryOther + '\'' +
                ", employerName='" + employerName + '\'' +
                ", taxResidenceCountry='" + taxResidenceCountry + '\'' +
                ", isUSReportable='" + isUSReportable + '\'' +
                ", hasTaxIdentificationNumber='" + hasTaxIdentificationNumber + '\'' +
                ", taxIdentificationNumber='" + taxIdentificationNumber + '\'' +
                ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
                ", educationLevel='" + educationLevel + '\'' +
                ", educationField='" + educationField + '\'' +
                ", educationFieldOther='" + educationFieldOther + '\'' +
                ", isPoliticallyExposed='" + isPoliticallyExposed + '\'' +
                ", politicalExposureComment='" + politicalExposureComment + '\'' +
                ", sourceOfFunds='" + sourceOfFunds + '\'' +
                ", sourceOfFundsOther='" + sourceOfFundsOther + '\'' +
                ", annualIncome='" + annualIncome + '\'' +
                ", netWealth='" + netWealth + '\'' +
                ", expectedDepositsPerYear='" + expectedDepositsPerYear + '\'' +
                ", purposeOfTrading='" + purposeOfTrading + '\'' +
                ", purposeOfTradingOther='" + purposeOfTradingOther + '\'' +
                ", expectedScore=" + expectedScore +
                '}';
    }

    public static class PersonalInformationBuilder {
        private EmploymentStatus employmentStatus;
        private Industry industry;
        private String industryOther;
        private String employerName;
        private String taxResidenceCountry;
        private IsUSReportable isUSReportable;
        private HasTaxIdentificationNumber hasTaxIdentificationNumber;
        private String taxIdentificationNumber;
        private String socialSecurityNumber;
        private EducationLevel educationLevel;
        private EducationField educationField;
        private String educationFieldOther;
        private IsPoliticallyExposed isPoliticallyExposed;
        private String politicalExposureComment;
        private SourceOfFunds sourceOfFunds;
        private String sourceOfFundsOther;
        private AnnualIncome annualIncome;
        private NetWealth netWealth;
        private ExpectedDepositsPerYear expectedDepositsPerYear;
        private PurposeOfTrading purposeOfTrading;
        private String purposeOfTradingOther;

        private PersonalInformationBuilder() {
        }

        public PersonalInformationBuilder withEmploymentStatus(EmploymentStatus employmentStatus) {
            this.employmentStatus = employmentStatus;
            return this;
        }

        public PersonalInformationBuilder withIndustry(Industry industry) {
            this.industry = industry;
            return this;
        }

        public PersonalInformationBuilder withIndustryOther(String industryOther) {
            this.industryOther = industryOther;
            return this;
        }

        public PersonalInformationBuilder withEmployerName(String employerName) {
            this.employerName = employerName;
            return this;
        }

        public PersonalInformationBuilder withTaxResidenceCountry(String taxResidenceCountry) {
            this.taxResidenceCountry = taxResidenceCountry;
            return this;
        }

        public PersonalInformationBuilder withUSReportabilityStatus(IsUSReportable isUSReportable) {
            this.isUSReportable = isUSReportable;
            return this;
        }

        public PersonalInformationBuilder withTaxIdentificationNumberStatus(HasTaxIdentificationNumber hasTaxIdentificationNumber) {
            this.hasTaxIdentificationNumber = hasTaxIdentificationNumber;
            return this;
        }

        public PersonalInformationBuilder withTaxIdentificationNumber(String taxIdentificationNumber) {
            this.taxIdentificationNumber = taxIdentificationNumber;
            return this;
        }

        public PersonalInformationBuilder withSocialSecurityNumber(String socialSecurityNumber) {
            this.socialSecurityNumber = socialSecurityNumber;
            return this;
        }

        public PersonalInformationBuilder withEducationLevel(EducationLevel educationLevel) {
            this.educationLevel = educationLevel;
            return this;
        }

        public PersonalInformationBuilder withEducationField(EducationField educationField) {
            this.educationField = educationField;
            return this;
        }


        public PersonalInformationBuilder withEducationFieldOther(String educationFieldOther) {
            this.educationFieldOther = educationFieldOther;
            return this;
        }

        public PersonalInformationBuilder withPoliticalExposureStatus(IsPoliticallyExposed isPoliticallyExposed) {
            this.isPoliticallyExposed = isPoliticallyExposed;
            return this;
        }

        public PersonalInformationBuilder withPoliticalExposureComment(String politicalExposureComment) {
            this.politicalExposureComment = politicalExposureComment;
            return this;
        }

        public PersonalInformationBuilder withSourceOfFunds(SourceOfFunds sourceOfFunds) {
            this.sourceOfFunds = sourceOfFunds;
            return this;
        }

        public PersonalInformationBuilder withSourceOfFundsOther(String sourceOfFundsOther) {
            this.sourceOfFundsOther = sourceOfFundsOther;
            return this;
        }

        public PersonalInformationBuilder withAnnualIncome(AnnualIncome annualIncome) {
            this.annualIncome = annualIncome;
            return this;
        }

        public PersonalInformationBuilder withNetWealth(NetWealth netWealth) {
            this.netWealth = netWealth;
            return this;
        }

        public PersonalInformationBuilder withExpectedDepositsPerYear(ExpectedDepositsPerYear expectedDepositsPerYear) {
            this.expectedDepositsPerYear = expectedDepositsPerYear;
            return this;
        }

        public PersonalInformationBuilder withPurposeOfTrading(PurposeOfTrading purposeOfTrading) {
            this.purposeOfTrading = purposeOfTrading;
            return this;
        }

        public PersonalInformationBuilder withPurposeOfTradingOther(String purposeOfTradingOther) {
            this.purposeOfTradingOther = purposeOfTradingOther;
            return this;
        }

        public PersonalInformation build() {
            return new PersonalInformation(
                    valueIfPresent(employmentStatus),
                    valueIfPresent(industry),
                    industryOther,
                    employerName,
                    taxResidenceCountry,
                    valueIfPresent(isUSReportable),
                    valueIfPresent(hasTaxIdentificationNumber),
                    taxIdentificationNumber,
                    socialSecurityNumber,
                    valueIfPresent(educationLevel),
                    valueIfPresent(educationField),
                    educationFieldOther,
                    valueIfPresent(isPoliticallyExposed),
                    politicalExposureComment,
                    valueIfPresent(sourceOfFunds),
                    sourceOfFundsOther,
                    valueIfPresent(annualIncome),
                    valueIfPresent(netWealth),
                    valueIfPresent(expectedDepositsPerYear),
                    valueIfPresent(purposeOfTrading),
                    purposeOfTradingOther,
                    getExpectedScore());
        }

        private String valueIfPresent(Answer answer) {
            return Optional.ofNullable(answer).map(Answer::getValue).orElse(null);
        }

        private int getExpectedScore() {
            return Arrays.stream(this.getClass().getDeclaredFields())
                    .filter(field -> Answer.class.isAssignableFrom(field.getType()))
                    .map(field -> {
                        try {
                            return field.get(this);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException("", e);
                        }
                    })
                    .map(Answer.class::cast)
                    .filter(Objects::nonNull)
                    .mapToInt(Answer::getScore)
                    .sum();
        }

    }
}
