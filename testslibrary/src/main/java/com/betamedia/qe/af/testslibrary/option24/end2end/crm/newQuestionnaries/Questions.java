package com.betamedia.qe.af.testslibrary.option24.end2end.crm.newQuestionnaries;

/**
 * Created by vsnigur on 5/23/17.
 */
public class Questions {

    /**
     * FNS Personal experience answers
     */

    public enum FnsEmploymentStatus {
        SALARIED_EMPLOYEE("employmentStatusSalariedEmployee"),
        SELF_EMPLOYED("employmentStatusSelfEmployed"),
        STUDENT("employmentStatusStudent"),
        RETIRED("employmentStatusRetired"),
        UNEMPLOYED("employmentStatusUnemployed");

        private String answer;

        FnsEmploymentStatus(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsIndustry {
        ACCOUNTING("industryAccounting"),
        FINANCE("industryFinance"),
        FUNDS("industryFunds"),
        ATTORNEYS("industryAttorneys"),
        COMPUTER("industryComputer"),
        OTHER("industryOther");

        private String answer;

        FnsIndustry(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsHaveTin {
        YES("tinYes"),
        NO("tinNo");

        private String answer;

        FnsHaveTin(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsIsUSReportablePerson {
        YES("usReportableYes"),
        NO("usReportableNo");

        private String answer;

        FnsIsUSReportablePerson(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsLevelOfEducation {
        POST_GRADUATE("levelOfEducationPostGraduate"),
        BACHELOR("levelOfEducationBachelor"),
        SECONDARY("levelOfEducationSecondary"),
        PRIMARY("levelOfEducationPrimary"),
        NONE("levelOfEducationNone");

        private String answer;

        FnsLevelOfEducation(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsFieldOfStudy {
        ACCOUNTING("fieldOfStudyAccounting"),
        LAW("fieldOfStudyLaw"),
        COMPUTER("fieldOfStudyComputer"),
        MEDICINE("fieldOfStudyMedicine"),
        OTHER("fieldOfStudyOther");

        private String answer;

        FnsFieldOfStudy(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsPoliticallyExposed {
        NO("politicallyExposedNo"),
        YES("politicallyExposedYes");

        private String answer;

        FnsPoliticallyExposed(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsSourceOfFunds {
        EMPLOYMENT("sourceOfFundsEmployment"),
        SAVINGS("sourceOfFundsSavings"),
        RETIREMENT("sourceOfFundsRetirement"),
        INHERITANCE("sourceOfFundsInheritance"),
        OTHER("sourceOfFundsOther");

        private String answer;

        FnsSourceOfFunds(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsTotalAnnualIncome {
        INCOME_OVER100K("totalAnnualIncomeOver100k"),
        INCOME_50K_100K("totalAnnualIncome50k-100k"),
        INCOME_25K_50K("totalAnnualIncome25k-50k"),
        INCOME_15K_25K("totalAnnualIncome15k-25k"),
        INCOME_15KLESS("totalAnnualIncome15kless");

        private String answer;

        FnsTotalAnnualIncome(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsNetWealth {
        NET_WEALTH_OVER_300K("netWealthOver300k"),
        NET_WEALTH_150K_300K("netWealth150k-300k"),
        NET_WEALTH_50K_150K("netWealth50k-150k"),
        NET_WEALTH_15K_50K("netWealth15k-50k"),
        NET_WEALTH_UNDER_15K("netWealthUnder15k");

        private String answer;

        FnsNetWealth(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsExpectedDeposits {
        DEPOSITS_OVER50K("expectedDepositsOver50k"),
        DEPOSITS_25K_50K("expectedDeposits25k-50k"),
        DEPOSITS_15K_25K("expectedDeposits15k-25k"),
        DEPOSITS_10K_15K("expectedDeposits10k-15k"),
        DEPOSITS_UNDER_10K("expectedDepositsUnder10k");

        private String answer;

        FnsExpectedDeposits(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsPurposeOfTrading {
        SPECULATIVE("purposeOfTradingSpeculative"),
        ADDITIONAL_INCOME("purposeOfTradingAdditionalIncome"),
        HEDGING("purposeOfTradingHedging"),
        OTHER("purposeOfTradingOther");

        private String answer;

        FnsPurposeOfTrading(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    /**
     * FNS Experience Level answers
     */
    public enum FnsFinancialProducts1Shares {
        FREQUENTLY("sharesFrequently"),
        REGULARLY("sharesRegularly"),
        OCCASIONALLY("sharesOccasionally"),
        NEVER("sharesNever");

        private String answer;

        FnsFinancialProducts1Shares(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsFinancialProducts1Binary {
        FREQUENTLY("binaryFrequently"),
        REGULARLY("binaryRegularly"),
        OCCASIONALLY("binaryOccasionally"),
        NEVER("binaryNever");

        private String answer;

        FnsFinancialProducts1Binary(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsFinancialProducts2Forex {
        FREQUENTLY("forexFrequently"),
        REGULARLY("forexRegularly"),
        OCCASIONALLY("forexOccasionally"),
        NEVER("forexNever");

        private String answer;

        FnsFinancialProducts2Forex(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum AverageYearlyBinaryVolume {
        VOLUME_ABOVE_10K("financialProducts1VolumeAbove10k"),
        VOLUME_5K_10k("financialProducts1Volume5k-10k"),
        VOLUME_500_5K("financialProducts1Volume500-5k"),
        VOLUME_UNDER_500("financialProducts1VolumeUnder500");

        private String answer;

        AverageYearlyBinaryVolume(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
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

        public String getAnswer() {
            return answer;
        }
    }

    public enum CommonLeverageForExVolume {
        LEVERAGE_ABOVE1TO500("financialProducts2LeverageAbove1to500"),
        LEVERAGE_1TO200_1TO500("financialProducts2Leverage1to200-1to500"),
        LEVERAGE_1TO50_1TO200("financialProducts2Leverage1to50-1to200"),
        LEVERAGE_UNDER_1TO50("financialProducts2LeverageUnder1to50");

        private String answer;

        CommonLeverageForExVolume(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsFinancialProducts2Leverage {
        LEVERAGE_ABOVE_1TO500("financialProducts2LeverageAbove1to500"),
        LEVERAGE_1TO200_1TO500("financialProducts2Leverage1to200-1to500"),
        LEVERAGE_1TO50_1TO200("financialProducts2Leverage1to50-1to200"),
        LEVERAGE_UNDER_1TO50("financialProducts2LeverageUnder1to50");

        private String answer;

        FnsFinancialProducts2Leverage(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsSelectIfApplicable {
        WORKED("selectIfApplicableWorked"),
        ATTENDED("selectIfApplicableAttended"),
        BOTH("selectIfApplicableBoth"),
        NEITHER("selectIfApplicableNeither");

        private String answer;

        FnsSelectIfApplicable(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsKnowledgeCfdsBinaries {
        SPECULATIVE("knowledgeCfdsBinariesSpeculative"),
        NON_RISKY("knowledgeCfdsBinariesNonRisky"),
        PHYSICALLY_DELIVERING("knowledgeCfdsBinariesPhysicallyDelivering");

        private String answer;

        FnsKnowledgeCfdsBinaries(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsKnowledgeMainFactor {
        INTEREST_RATES("knowledgeMainFactorInterestRates"),
        ANNOUNCEMENT("knowledgeMainFactorAnnouncement"),
        EMPLOYEE_LAYOFFS("knowledgeMainFactorEmployeeLayoffs");

        private String answer;

        FnsKnowledgeMainFactor(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsKnowledgeIfYouOpen {
        ONLY_PLATFORM("knowledgeIfYouOpenOnlyPlatform"),
        LONDON_STOCK("knowledgeIfYouOpenLondonStock"),
        CANNOT_SELL("knowledgeIfYouOpenCannotSell");

        private String answer;

        FnsKnowledgeIfYouOpen(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsKnowledgeLeverageCFD {
        MAGNIFIES("knowledgeLeverageCFDMagnifies"),
        PROVIDES("knowledgeLeverageCFDProvides"),
        NONE("knowledgeLeverageCFDNone");

        private String answer;

        FnsKnowledgeLeverageCFD(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsKnowledgeStoploss {
        MINIMIZE("knowledgeStoplossMinimize"),
        BUY("knowledgeStoplossBuy"),
        TAKE("knowledgeStoplossTake");

        private String answer;

        FnsKnowledgeStoploss(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsKnowledgeRequiredMargin {
        MARGIN1K("knowledgeRequiredMargin1k"),
        MARGIN10K("knowledgeRequiredMargin10k"),
        MARGIN100K("knowledgeRequiredMargin100k");

        private String answer;

        FnsKnowledgeRequiredMargin(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsKnowledgeMarginLevelDrop {
        MARGIN_CALL("knowledgeMarginLevelDropMarginCall"),
        WARNING_CALL("knowledgeMarginLevelDropWarningCall"),
        MARGIN_UPGRADE("knowledgeMarginLevelDropMarginUpgrade");

        private String answer;

        FnsKnowledgeMarginLevelDrop(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsKnowledgeAutomaticStop {
        EQUITY_FALLS("knowledgeAutomaticStopEquityFalls"),
        EARNINGS("knowledgeAutomaticStopEarnings"),
        OPEN_POSITION("knowledgeAutomaticStopOpenPosition");

        private String answer;

        FnsKnowledgeAutomaticStop(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsKnowledgePositionLoss1to50 {
        LOSS1TO50A1_800("knowledgePositionLoss1to50A1"),
        LOSS1TO50A2_450("knowledgePositionLoss1to50A2"),
        LOSS1TO50A3_200("knowledgePositionLoss1to50A3");

        private String answer;

        FnsKnowledgePositionLoss1to50(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsKnowledgePositionLoss1to200 {
        LOSS1TO200A1_1800("knowledgePositionLoss1to200A1"),
        LOSS1TO200A2_1200("knowledgePositionLoss1to200A2"),
        LOSS1TO200A3_1000("knowledgePositionLoss1to200A3");

        private String answer;

        FnsKnowledgePositionLoss1to200(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsKnowledgeInvestBinaryProfit {
        BINARY_PROFIT_75("knowledgeInvestBinaryProfit75"),
        BINARY_PROFIT_60("knowledgeInvestBinaryProfit60"),
        BINARY_PROFIT_100("knowledgeInvestBinaryProfit100");

        private String answer;

        FnsKnowledgeInvestBinaryProfit(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsKnowledgeInvestBinaryLoss {
        LOSS100("knowledgeInvestBinaryLoss100"),
        LOSS75("knowledgeInvestBinaryLoss75"),
        LOSS50("knowledgeInvestBinaryLoss50");

        private String answer;

        FnsKnowledgeInvestBinaryLoss(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }

    public enum FnsKnowledgeBinaryInTheMoney {
        MONEY25("knowledgeBinaryInTheMoney25"),
        MONEY35("knowledgeBinaryInTheMoney35"),
        MONEY45("knowledgeBinaryInTheMoney45");

        private String answer;

        FnsKnowledgeBinaryInTheMoney(String answer) {
            this.answer = answer;
        }

        public String getAnswer() {
            return answer;
        }
    }
}
