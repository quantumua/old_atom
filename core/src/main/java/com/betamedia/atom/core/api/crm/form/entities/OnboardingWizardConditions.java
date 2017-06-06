package com.betamedia.atom.core.api.crm.form.entities;

import com.betamedia.atom.core.fwdataaccess.converters.AccountTypeConverter;
import com.betamedia.atom.core.fwdataaccess.converters.ExperienceLevelConverter;
import com.betamedia.atom.core.fwdataaccess.converters.DocumentVerificationStatusConverter;
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
    @CsvBindByName
    private boolean customerCompliant;
    @CsvCustomBindByName(converter = DocumentVerificationStatusConverter.class)
    private DocumentVerificationStatus poiOcrStatus;
    @CsvBindByName
    private boolean showWizard;
    @CsvBindByName
    private boolean showWelcome;
    @CsvBindByName
    private boolean showDeposit;
    @CsvBindByName
    private boolean showRiskWarning;
    @CsvBindByName
    private boolean showStartWizard;
    @CsvBindByName
    private boolean showAdditionalDetails;
    @CsvBindByName
    private boolean showFnsPersonal;
    @CsvBindByName
    private boolean showFnsTrading;
    @CsvBindByName
    private boolean showPoiPor;
    @CsvBindByName
    private boolean showWelcomeBack;
    @CsvBindByName
    private String testId;
    @CsvBindByName
    private String username;


    private boolean hasRegulationAnswers = true;

    public OnboardingWizardConditions() {
    }

    public OnboardingWizardConditions(boolean fnsPersonal, boolean fnsTrading, boolean hasAdditionalDetails, boolean riskWarning, ExperienceLevel experienceLevel, boolean hasDeposit, AccountType accountType, DocumentVerificationStatus poiStatus, DocumentVerificationStatus porStatus, boolean hasPendingDeposit, boolean customerCompliant, DocumentVerificationStatus poiOcrStatus) {
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
        this.customerCompliant = customerCompliant;
        this.poiOcrStatus = poiOcrStatus;
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

    public boolean isCustomerCompliant() {
        return customerCompliant;
    }

    public DocumentVerificationStatus getPoiOcrStatus() {
        return poiOcrStatus;
    }

    public boolean isShowWizard() {
        return showWizard;
    }

    public boolean isShowWelcome() {
        return showWelcome;
    }

    public boolean isShowDeposit() {
        return showDeposit;
    }

    public boolean isShowRiskWarning() {
        return showRiskWarning;
    }

    public boolean isShowStartWizard() {
        return showStartWizard;
    }

    public boolean isShowAdditionalDetails() {
        return showAdditionalDetails;
    }

    public boolean isShowFnsPersonal() {
        return showFnsPersonal;
    }

    public boolean isShowFnsTrading() {
        return showFnsTrading;
    }

    public boolean isShowPoiPor() {
        return showPoiPor;
    }
    
    public String username() {
        return username;
    }

    public boolean isShowWelcomeBack() {
        return showWelcomeBack;
    }

    public enum ExperienceLevel {
        UNKNOWN(1000010),
        REJECTED(1000020),
        NO_EXPERIENCE(1000030),
        LOW_EXPERIENCE(1000000),
        HIGH_EXPERIENCE(1000040),
        EXPERT(1000001);

        private Integer level;

        ExperienceLevel(Integer level) {
            this.level = level;
        }

        public Integer getLevel() {
            return level;
        }
    }

    public enum DocumentVerificationStatus {
        EMPTY(100000001),
        NOT_VERIFIED(100000002),
        VERIFIED(100000003);

        private Integer status;

        DocumentVerificationStatus(Integer status) {
            this.status = status;
        }

        public Integer getStatus() {
            return status;
        }
    }

    public enum AccountType {
        REAL(100000000),
        DEMO(100000001);

        private Integer type;

        AccountType(Integer type) {
            this.type = type;
        }

        public Integer getType() {
            return type;
        }
    }

    public enum AccessType {
        ACTIVE(100000000),
        NODEPOSIT(100000001),
        NOTRADE(100000002),
        NOTRADENODEPOSIT(100000003),
        NOLOGIN(100000004);


        private Integer type;

        AccessType(Integer type) {
            this.type = type;
        }

        public Integer getType() {
            return type;
        }
    }

    @Override
    public String toString() {
        return "OnboardingWizardConditions{" +
                "username=" + username +
                ", testId='" + testId + '\'' +
                ", fnsPersonal=" + fnsPersonal +
                ", fnsTrading=" + fnsTrading +
                ", hasAdditionalDetails=" + hasAdditionalDetails +
                ", riskWarning=" + riskWarning +
                ", experienceLevel=" + experienceLevel +
                ", hasDeposit=" + hasDeposit +
                ", accountType=" + accountType +
                ", poiStatus=" + poiStatus +
                ", porStatus=" + porStatus +
                ", hasPendingDeposit=" + hasPendingDeposit +
                ", customerCompliant=" + customerCompliant +
                ", poiOcrStatus=" + poiOcrStatus +
                ", showWizard=" + showWizard +
                ", showWelcome=" + showWelcome +
                ", showDeposit=" + showDeposit +
                ", showRiskWarning=" + showRiskWarning +
                ", showStartWizard=" + showStartWizard +
                ", showAdditionalDetails=" + showAdditionalDetails +
                ", showFnsPersonal=" + showFnsPersonal +
                ", showFnsTrading=" + showFnsTrading +
                ", showPoiPor=" + showPoiPor +
                ", hasRegulationAnswers=" + hasRegulationAnswers +
                '}';
    }
}
