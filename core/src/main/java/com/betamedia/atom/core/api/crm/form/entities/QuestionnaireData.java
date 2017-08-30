package com.betamedia.atom.core.api.crm.form.entities;

import com.opencsv.bean.CsvBindByName;

/**
 * Created by mbelyaev on 5/23/17.
 */
public class QuestionnaireData {
    @CsvBindByName
    private String employmentStatus;
    @CsvBindByName
    private String industry;
    @CsvBindByName
    private String industryOther;
    @CsvBindByName
    private String employerName;
    @CsvBindByName
    private String taxResidenceCountry;
    @CsvBindByName
    private String isUSReportable;
    @CsvBindByName
    private String hasTaxIdentificationNumber;
    @CsvBindByName
    private String taxIdentificationNumber;
    @CsvBindByName
    private String socialSecurityNumber;
    @CsvBindByName
    private String educationLevel;
    @CsvBindByName
    private String educationField;
    @CsvBindByName
    private String educationFieldOther;
    @CsvBindByName
    private String isPoliticallyExposed;
    @CsvBindByName
    private String politicalExposureComment;
    @CsvBindByName
    private String sourceOfFunds;
    @CsvBindByName
    private String sourceOfFundsOther;
    @CsvBindByName
    private String annualIncome;
    @CsvBindByName
    private String netWealth;
    @CsvBindByName
    private String expectedDepositsPerYear;
    @CsvBindByName
    private String purposeOfTrading;
    @CsvBindByName
    private String purposeOfTradingOther;
    @CsvBindByName
    private String sharesExperience;
    @CsvBindByName
    private String binaryExperience;
    @CsvBindByName
    private String averageYearlyBinaryVolume;
    @CsvBindByName
    private String forExExperience;
    @CsvBindByName
    private String averageYearlyForExVolume;
    @CsvBindByName
    private String commonLeverage;
    @CsvBindByName
    private String financialWorkExperience;
    @CsvBindByName
    private String cfdBinaryKnowledge;
    @CsvBindByName
    private String mainFactorKnowledge;
    @CsvBindByName
    private String howToCloseKnowledge;
    @CsvBindByName
    private String cfdLeverageKnowledge;
    @CsvBindByName
    private String stopLossKnowledge;
    @CsvBindByName
    private String requiredMarginKnowledge;
    @CsvBindByName
    private String marginLevelDropKnowledge;
    @CsvBindByName
    private String automaticStopKnowledge;
    @CsvBindByName
    private String lossOn1to50Knowledge;
    @CsvBindByName
    private String lossOn1to200Knowledge;
    @CsvBindByName
    private String binaryInvestProfitKnowledge;
    @CsvBindByName
    private String binaryInvestLossKnowledge;
    @CsvBindByName
    private String binaryProbabilityKnowledge;
    @CsvBindByName
    private String instrumentsTradedBefore;
    @CsvBindByName
    private String frequencyPastTransactions;
    @CsvBindByName
    private String volumePastTransaction;
    @CsvBindByName
    private String commonLevelPastTransaction;

    public String getFrequencyPastTransactions() {
        return frequencyPastTransactions;
    }

    public String getVolumePastTransaction() {
        return volumePastTransaction;
    }

    public String getCommonLevelPastTransaction() {
        return commonLevelPastTransaction;
    }

    public String getInstrumentsTradedBefore() {
        return instrumentsTradedBefore;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public String getIndustry() {
        return industry;
    }

    public String getIndustryOther() {
        return industryOther;
    }

    public String getEmployerName() {
        return employerName;
    }

    public String getTaxResidenceCountry() {
        return taxResidenceCountry;
    }

    public String getIsUSReportable() {
        return isUSReportable;
    }

    public String getHasTaxIdentificationNumber() {
        return hasTaxIdentificationNumber;
    }

    public String getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public String getEducationField() {
        return educationField;
    }

    public String getEducationFieldOther() {
        return educationFieldOther;
    }

    public String getIsPoliticallyExposed() {
        return isPoliticallyExposed;
    }

    public String getPoliticalExposureComment() {
        return politicalExposureComment;
    }

    public String getSourceOfFunds() {
        return sourceOfFunds;
    }

    public String getSourceOfFundsOther() {
        return sourceOfFundsOther;
    }

    public String getAnnualIncome() {
        return annualIncome;
    }

    public String getNetWealth() {
        return netWealth;
    }

    public String getExpectedDepositsPerYear() {
        return expectedDepositsPerYear;
    }

    public String getPurposeOfTrading() {
        return purposeOfTrading;
    }

    public String getPurposeOfTradingOther() {
        return purposeOfTradingOther;
    }

    public String getSharesExperience() {
        return sharesExperience;
    }

    public String getBinaryExperience() {
        return binaryExperience;
    }

    public String getAverageYearlyBinaryVolume() {
        return averageYearlyBinaryVolume;
    }

    public String getForExExperience() {
        return forExExperience;
    }

    public String getAverageYearlyForExVolume() {
        return averageYearlyForExVolume;
    }

    public String getCommonLeverage() {
        return commonLeverage;
    }

    public String getFinancialWorkExperience() {
        return financialWorkExperience;
    }

    public String getCfdBinaryKnowledge() {
        return cfdBinaryKnowledge;
    }

    public String getMainFactorKnowledge() {
        return mainFactorKnowledge;
    }

    public String getHowToCloseKnowledge() {
        return howToCloseKnowledge;
    }

    public String getCfdLeverageKnowledge() {
        return cfdLeverageKnowledge;
    }

    public String getStopLossKnowledge() {
        return stopLossKnowledge;
    }

    public String getRequiredMarginKnowledge() {
        return requiredMarginKnowledge;
    }

    public String getMarginLevelDropKnowledge() {
        return marginLevelDropKnowledge;
    }

    public String getAutomaticStopKnowledge() {
        return automaticStopKnowledge;
    }

    public String getLossOn1to50Knowledge() {
        return lossOn1to50Knowledge;
    }

    public String getLossOn1to200Knowledge() {
        return lossOn1to200Knowledge;
    }

    public String getBinaryInvestProfitKnowledge() {
        return binaryInvestProfitKnowledge;
    }

    public String getBinaryInvestLossKnowledge() {
        return binaryInvestLossKnowledge;
    }

    public String getBinaryProbabilityKnowledge() {
        return binaryProbabilityKnowledge;
    }
}
