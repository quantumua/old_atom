package com.betamedia.qe.af.testslibrary.option24.end2end.crm.newQuestionnaries;

/**
 * Created by vsnigur on 5/23/17.
 */
public class Questions {

    /**
     * FNS Personal experience answers
     */

    public enum EmploymentStatus {
        SALARIED_EMPLOYEE("employmentStatusSalariedEmployee"),
        SELF_EMPLOYED("employmentStatusSelfEmployed"),
        STUDENT("employmentStatusStudent"),
        RETIRED("employmentStatusRetired"),
        UNEMPLOYED("employmentStatusUnemployed");

        private String answer;

        EmploymentStatus(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum Industry {
        ACCOUNTING("industryAccounting"),
        FINANCE("industryFinance"),
        FUNDS("industryFunds"),
        ATTORNEYS("industryAttorneys"),
        COMPUTER("industryComputer"),
        OTHER("industryOther");

        private String answer;

        Industry(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum HasTaxIdentificationNumber {
        YES("tinYes"),
        NO("tinNo");

        private String answer;

        HasTaxIdentificationNumber(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum IsUSReportable {
        YES("usReportableYes"),
        NO("usReportableNo");

        private String answer;

        IsUSReportable(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum EducationLevel {
        POST_GRADUATE("levelOfEducationPostGraduate"),
        BACHELOR("levelOfEducationBachelor"),
        SECONDARY("levelOfEducationSecondary"),
        PRIMARY("levelOfEducationPrimary"),
        NONE("levelOfEducationNone");

        private String answer;

        EducationLevel(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum EducationField {
        ACCOUNTING("fieldOfStudyAccounting"),
        LAW("fieldOfStudyLaw"),
        COMPUTER("fieldOfStudyComputer"),
        MEDICINE("fieldOfStudyMedicine"),
        OTHER("fieldOfStudyOther");

        private String answer;

        EducationField(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum IsPoliticallyExposed {
        NO("politicallyExposedNo"),
        YES("politicallyExposedYes");

        private String answer;

        IsPoliticallyExposed(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum SourceOfFunds {
        EMPLOYMENT("sourceOfFundsEmployment"),
        SAVINGS("sourceOfFundsSavings"),
        RETIREMENT("sourceOfFundsRetirement"),
        INHERITANCE("sourceOfFundsInheritance"),
        OTHER("sourceOfFundsOther");

        private String answer;

        SourceOfFunds(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum AnnualIncome {
        INCOME_OVER_100K("totalAnnualIncomeOver100k"),
        INCOME_50K_100K("totalAnnualIncome50k-100k"),
        INCOME_25K_50K("totalAnnualIncome25k-50k"),
        INCOME_15K_25K("totalAnnualIncome15k-25k"),
        INCOME_15KLESS("totalAnnualIncome15kless");

        private String answer;

        AnnualIncome(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum NetWealth {
        NET_WEALTH_OVER_300K("netWealthOver300k"),
        NET_WEALTH_150K_300K("netWealth150k-300k"),
        NET_WEALTH_50K_150K("netWealth50k-150k"),
        NET_WEALTH_15K_50K("netWealth15k-50k"),
        NET_WEALTH_UNDER_15K("netWealthUnder15k");

        private String answer;

        NetWealth(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum ExpectedDepositsPerYear {
        DEPOSITS_OVER_50K("expectedDepositsOver50k"),
        DEPOSITS_25K_50K("expectedDeposits25k-50k"),
        DEPOSITS_15K_25K("expectedDeposits15k-25k"),
        DEPOSITS_10K_15K("expectedDeposits10k-15k"),
        DEPOSITS_UNDER_10K("expectedDepositsUnder10k");

        private String answer;

        ExpectedDepositsPerYear(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum PurposeOfTrading {
        SPECULATIVE("purposeOfTradingSpeculative"),
        ADDITIONAL_INCOME("purposeOfTradingAdditionalIncome"),
        HEDGING("purposeOfTradingHedging"),
        OTHER("purposeOfTradingOther");

        private String answer;

        PurposeOfTrading(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    /**
     * FNS Experience Level answers
     */
    public enum SharesExperience {
        FREQUENTLY("sharesFrequently"),
        REGULARLY("sharesRegularly"),
        OCCASIONALLY("sharesOccasionally"),
        NEVER("sharesNever");

        private String answer;

        SharesExperience(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum BinaryExperience {
        FREQUENTLY("binaryFrequently"),
        REGULARLY("binaryRegularly"),
        OCCASIONALLY("binaryOccasionally"),
        NEVER("binaryNever");

        private String answer;

        BinaryExperience(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum ForExExperience {
        FREQUENTLY("forexFrequently"),
        REGULARLY("forexRegularly"),
        OCCASIONALLY("forexOccasionally"),
        NEVER("forexNever");

        private String answer;

        ForExExperience(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum AverageYearlyBinaryVolume {
        VOLUME_ABOVE_10K("financialProducts1VolumeAbove10k"),
        VOLUME_5K_10K("financialProducts1Volume5k-10k"),
        VOLUME_500_5K("financialProducts1Volume500-5k"),
        VOLUME_UNDER_500("financialProducts1VolumeUnder500");

        private String answer;

        AverageYearlyBinaryVolume(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum AverageYearlyForExVolume {
        VOLUME_ABOVE_500K("financialProducts2VolumeAbove500k"),
        VOLUME_150K_500K("financialProducts2Volume150k-500k"),
        VOLUME_50K_150K("financialProducts2Volume50k-150k"),
        VOLUME_UNDER_50K("financialProducts2VolumeUnder50k");

        private String answer;

        AverageYearlyForExVolume(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum CommonLeverageForExVolume {
        LEVERAGE_ABOVE_1TO500("financialProducts2LeverageAbove1to500"),
        LEVERAGE_1TO200_1TO500("financialProducts2Leverage1to200-1to500"),
        LEVERAGE_1TO50_1TO200("financialProducts2Leverage1to50-1to200"),
        LEVERAGE_UNDER_1TO50("financialProducts2LeverageUnder1to50");

        private String answer;

        CommonLeverageForExVolume(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum CommonLeverage {
        LEVERAGE_ABOVE_1TO500("financialProducts2LeverageAbove1to500"),
        LEVERAGE_1TO200_1TO500("financialProducts2Leverage1to200-1to500"),
        LEVERAGE_1TO50_1TO200("financialProducts2Leverage1to50-1to200"),
        LEVERAGE_UNDER_1TO50("financialProducts2LeverageUnder1to50");

        private String answer;

        CommonLeverage(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum FinancialWorkExperience {
        WORKED("selectIfApplicableWorked"),
        ATTENDED("selectIfApplicableAttended"),
        BOTH("selectIfApplicableBoth"),
        NEITHER("selectIfApplicableNeither");

        private String answer;

        FinancialWorkExperience(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum CfdBinaryKnowledge {
        SPECULATIVE("knowledgeCfdsBinariesSpeculative"),
        NON_RISKY("knowledgeCfdsBinariesNonRisky"),
        PHYSICALLY_DELIVERING("knowledgeCfdsBinariesPhysicallyDelivering");

        private String answer;

        CfdBinaryKnowledge(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum MainFactorKnowledge {
        INTEREST_RATES("knowledgeMainFactorInterestRates"),
        ANNOUNCEMENT("knowledgeMainFactorAnnouncement"),
        EMPLOYEE_LAYOFFS("knowledgeMainFactorEmployeeLayoffs");

        private String answer;

        MainFactorKnowledge(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum HowToCloseKnowledge {
        ONLY_PLATFORM("knowledgeIfYouOpenOnlyPlatform"),
        LONDON_STOCK("knowledgeIfYouOpenLondonStock"),
        CANNOT_SELL("knowledgeIfYouOpenCannotSell");

        private String answer;

        HowToCloseKnowledge(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum CfdLeverageKnowledge {
        MAGNIFIES("knowledgeLeverageCFDMagnifies"),
        PROVIDES("knowledgeLeverageCFDProvides"),
        NONE("knowledgeLeverageCFDNone");

        private String answer;

        CfdLeverageKnowledge(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum StopLossKnowledge {
        MINIMIZE("knowledgeStoplossMinimize"),
        BUY("knowledgeStoplossBuy"),
        TAKE("knowledgeStoplossTake");

        private String answer;

        StopLossKnowledge(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum RequiredMarginKnowledge {
        MARGIN_1K("knowledgeRequiredMargin1k"),
        MARGIN_10K("knowledgeRequiredMargin10k"),
        MARGIN_100K("knowledgeRequiredMargin100k");

        private String answer;

        RequiredMarginKnowledge(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum MarginLevelDropKnowledge {
        MARGIN_CALL("knowledgeMarginLevelDropMarginCall"),
        WARNING_CALL("knowledgeMarginLevelDropWarningCall"),
        MARGIN_UPGRADE("knowledgeMarginLevelDropMarginUpgrade");

        private String answer;

        MarginLevelDropKnowledge(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum AutomaticStopKnowledge {
        EQUITY_FALLS("knowledgeAutomaticStopEquityFalls"),
        EARNINGS("knowledgeAutomaticStopEarnings"),
        OPEN_POSITION("knowledgeAutomaticStopOpenPosition");

        private String answer;

        AutomaticStopKnowledge(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum LossOn1to50Knowledge {
        A1_800("knowledgePositionLoss1to50A1"),
        A2_450("knowledgePositionLoss1to50A2"),
        A3_200("knowledgePositionLoss1to50A3");

        private String answer;

        LossOn1to50Knowledge(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum LossOn1to200Knowledge {
        A1_1800("knowledgePositionLoss1to200A1"),
        A2_1200("knowledgePositionLoss1to200A2"),
        A3_1000("knowledgePositionLoss1to200A3");

        private String answer;

        LossOn1to200Knowledge(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum BinaryInvestProfitKnowledge {
        PROFIT_75("knowledgeInvestBinaryProfit75"),
        PROFIT_60("knowledgeInvestBinaryProfit60"),
        PROFIT_100("knowledgeInvestBinaryProfit100");

        private String answer;

        BinaryInvestProfitKnowledge(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum BinaryInvestLossKnowledge {
        LOSS_100("knowledgeInvestBinaryLoss100"),
        LOSS_75("knowledgeInvestBinaryLoss75"),
        LOSS_50("knowledgeInvestBinaryLoss50");

        private String answer;

        BinaryInvestLossKnowledge(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }

    public enum BinaryProbabilityKnowledge {
        MONEY_25("knowledgeBinaryInTheMoney25"),
        MONEY_35("knowledgeBinaryInTheMoney35"),
        MONEY_45("knowledgeBinaryInTheMoney45");

        private String answer;

        BinaryProbabilityKnowledge(String answer) {
            this.answer = answer;
        }

        public String get() {
            return answer;
        }
    }
}
