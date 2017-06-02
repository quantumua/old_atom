package com.betamedia.qe.af.core.api.crm.form.entities;

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

    private PersonalInformation(String employmentStatus, String industry, String industryOther, String employerName, String taxResidenceCountry, String isUSReportable, String hasTaxIdentificationNumber, String taxIdentificationNumber, String socialSecurityNumber, String educationLevel, String educationField, String educationFieldOther, String isPoliticallyExposed, String politicalExposureComment, String sourceOfFunds, String sourceOfFundsOther, String annualIncome, String netWealth, String expectedDepositsPerYear, String purposeOfTrading, String purposeOfTradingOther) {
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
    }

    public static PersonalInformationBuilder builder(){
        return new PersonalInformationBuilder();
    }

    public static class PersonalInformationBuilder {
        private String employmentStatus;
        private String industry;
        private String industryOther;
        private String employerName;
        private String taxResidenceCountry;
        private String isUSReportable;
        private String hasTaxIdentificationNumber;
        private String taxIdentificationNumber;
        private String socialSecurityNumber;
        private String educationLevel;
        private String educationField;
        private String educationFieldOther;
        private String isPoliticallyExposed;
        private String politicalExposureComment;
        private String sourceOfFunds;
        private String sourceOfFundsOther;
        private String annualIncome;
        private String netWealth;
        private String expectedDepositsPerYear;
        private String purposeOfTrading;
        private String purposeOfTradingOther;

        private PersonalInformationBuilder(){}

        public PersonalInformationBuilder withEmploymentStatus(String employmentStatus) {
            this.employmentStatus = employmentStatus;
            return this;
        }

        public PersonalInformationBuilder withIndustry(String industry) {
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

        public PersonalInformationBuilder withUSReportabilityStatus(String isUSReportable) {
            this.isUSReportable = isUSReportable;
            return this;
        }

        public PersonalInformationBuilder withTaxIdentificationNumberStatus(String hasTaxIdentificationNumber) {
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

        public PersonalInformationBuilder withEducationLevel(String educationLevel) {
            this.educationLevel = educationLevel;
            return this;
        }

        public PersonalInformationBuilder withEducationField(String educationField) {
            this.educationField = educationField;
            return this;
        }


        public PersonalInformationBuilder withEducationFieldOther(String educationFieldOther) {
            this.educationFieldOther = educationFieldOther;
            return this;
        }

        public PersonalInformationBuilder withPoliticalExposureStatus(String isPoliticallyExposed) {
            this.isPoliticallyExposed = isPoliticallyExposed;
            return this;
        }

        public PersonalInformationBuilder withPoliticalExposureComment(String politicalExposureComment) {
            this.politicalExposureComment = politicalExposureComment;
            return this;
        }

        public PersonalInformationBuilder withSourceOfFunds(String sourceOfFunds) {
            this.sourceOfFunds = sourceOfFunds;
            return this;
        }

        public PersonalInformationBuilder withSourceOfFundsOther(String sourceOfFundsOther) {
            this.sourceOfFundsOther = sourceOfFundsOther;
            return this;
        }

        public PersonalInformationBuilder withAnnualIncome(String annualIncome) {
            this.annualIncome = annualIncome;
            return this;
        }

        public PersonalInformationBuilder withNetWealth(String netWealth) {
            this.netWealth = netWealth;
            return this;
        }

        public PersonalInformationBuilder withExpectedDepositsPerYear(String expectedDepositsPerYear) {
            this.expectedDepositsPerYear = expectedDepositsPerYear;
            return this;
        }

        public PersonalInformationBuilder withPurposeOfTrading(String purposeOfTrading) {
            this.purposeOfTrading = purposeOfTrading;
            return this;
        }

        public PersonalInformationBuilder withPurposeOfTradingOther(String purposeOfTradingOther) {
            this.purposeOfTradingOther = purposeOfTradingOther;
            return this;
        }

        public PersonalInformation build() {
            return new PersonalInformation(
                    employmentStatus,
                    industry,
                    industryOther,
                    employerName,
                    taxResidenceCountry,
                    isUSReportable,
                    hasTaxIdentificationNumber,
                    taxIdentificationNumber,
                    socialSecurityNumber,
                    educationLevel,
                    educationField,
                    educationFieldOther,
                    isPoliticallyExposed,
                    politicalExposureComment,
                    sourceOfFunds,
                    sourceOfFundsOther,
                    annualIncome,
                    netWealth,
                    expectedDepositsPerYear,
                    purposeOfTrading,
                    purposeOfTradingOther);
        }

    }
}
