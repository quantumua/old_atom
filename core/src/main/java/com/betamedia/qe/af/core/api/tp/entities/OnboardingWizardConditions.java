package com.betamedia.qe.af.core.api.tp.entities;

import com.betamedia.qe.af.core.dataprovider.csv.converters.AccountTypeConverter;
import com.betamedia.qe.af.core.dataprovider.csv.converters.DocumentVerificationStatusConverter;
import com.betamedia.qe.af.core.dataprovider.csv.converters.ExperienceLevelConverter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

/**
 * Created by Oleksandr Losiev on 5/19/17.
 */
public class OnboardingWizardConditions {
    @CsvBindByName
    private boolean fnsPersonal;
    @CsvBindByName
    private boolean fnsTrading;
    @CsvBindByName
    private boolean hasAdditionalDetails;
    @CsvBindByName
    private boolean riskWarning;
    @CsvCustomBindByName(converter = ExperienceLevelConverter.class)
    private ExperienceLevel experienceLevel;
    @CsvBindByName
    private boolean hasDeposit;
    @CsvCustomBindByName(converter = AccountTypeConverter.class)
    private AccountType accountType;
    @CsvCustomBindByName(converter = DocumentVerificationStatusConverter.class)
    private DocumentVerificationStatus poiStatus;
    @CsvCustomBindByName(converter = DocumentVerificationStatusConverter.class)
    private DocumentVerificationStatus porStatus;
    @CsvBindByName
    private boolean hasPendingDeposit;

    private boolean hasRegulationAnswers = true;

    public OnboardingWizardConditions() {
    }

    public OnboardingWizardConditions(boolean fnsPersonal, boolean fnsTrading, boolean hasAdditionalDetails, boolean riskWarning,
                                      ExperienceLevel experienceLevel, boolean hasDeposit, AccountType accountType,
                                      DocumentVerificationStatus poiStatus, DocumentVerificationStatus porStatus, boolean hasPendingDeposit) {
        this.fnsPersonal = fnsPersonal;
        this.fnsTrading = fnsTrading;
        this.hasAdditionalDetails = hasAdditionalDetails;
        this.riskWarning = riskWarning;
        this.experienceLevel = experienceLevel;
        this.hasDeposit = hasDeposit;
        this.accountType = accountType;
        this.poiStatus = poiStatus;
        this.porStatus = porStatus;
        this.hasPendingDeposit = hasPendingDeposit;
    }

    public boolean isFnsPersonal() {
        return fnsPersonal;
    }

    public boolean isFnsTrading() {
        return fnsTrading;
    }

    public boolean hasAdditionalDetails() {
        return hasAdditionalDetails;
    }

    public boolean hasRiskWarning() {
        return riskWarning;
    }

    public ExperienceLevel getExperienceLevel() {
        return experienceLevel;
    }

    public boolean hasDeposit() {
        return hasDeposit;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public DocumentVerificationStatus getPoiStatus() {
        return poiStatus;
    }

    public DocumentVerificationStatus getPorStatus() {
        return porStatus;
    }

    public boolean hasPendingDeposit() {
        return hasPendingDeposit;
    }

    public boolean hasRegulationAnswers() {
        return hasRegulationAnswers;
    }

    public enum ExperienceLevel {
        NO_EXPERIENCE(1000030),
        LOW_EXPERIENCE(1000000),
        HIGH_EXPERIENCE(1000040),
        EXPERT(1000001);

        private int level;

        ExperienceLevel(int level) {
            this.level = level;
        }

        public int getLevel() {
            return level;
        }
    }

    public enum DocumentVerificationStatus {
        NOT_UPLOADED(1000030),
        NOT_VERIFIED(1000000),
        VERIFIED(1000040);

        private int status;

        DocumentVerificationStatus(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }
    }

    public enum AccountType {
        REAL(100000000),
        DEMO(100000001);

        private int type;

        AccountType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }
    }
}