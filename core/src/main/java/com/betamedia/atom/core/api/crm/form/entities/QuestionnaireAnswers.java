package com.betamedia.atom.core.api.crm.form.entities;

import java.util.Arrays;

/**
 * Created by vsnigur on 5/23/17.
 */
public class QuestionnaireAnswers {

    public interface Answer {
        String getValue();

        int getScore();
    }

    /**
     * FNS Personal experience values
     */

    public enum EmploymentStatus implements Answer {
        SALARIED_EMPLOYEE("employmentStatusSalariedEmployee", 0),
        SELF_EMPLOYED("employmentStatusSelfEmployed", 0),
        STUDENT("employmentStatusStudent", 0),
        RETIRED("employmentStatusRetired", 0),
        UNEMPLOYED("employmentStatusUnemployed", 0);

        public final String value;
        public final int score;

        EmploymentStatus(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static EmploymentStatus parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum Industry implements Answer {
        ACCOUNTING("industryAccounting", 5),
        FINANCE("industryFinance", 5),
        FUNDS("industryFunds", 5),
        ATTORNEYS("industryAttorneys", 2),
        COMPUTER("industryComputer", 0),
        OTHER("industryOther", 0);

        public final String value;
        public final int score;

        Industry(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static Industry parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum HasTaxIdentificationNumber implements Answer {
        YES("tinYes", 0),
        NO("tinNo", 0);

        public final String value;
        public final int score;

        HasTaxIdentificationNumber(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static HasTaxIdentificationNumber parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum IsUSReportable implements Answer {
        YES("usReportableYes", 0),
        NO("usReportableNo", 0);

        public final String value;
        public final int score;

        IsUSReportable(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static IsUSReportable parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum EducationLevel implements Answer {
        POST_GRADUATE("levelOfEducationPostGraduate", 2),
        BACHELOR("levelOfEducationBachelor", 1),
        SECONDARY("levelOfEducationSecondary", 0),
        PRIMARY("levelOfEducationPrimary", 0),
        NONE("levelOfEducationNone", 0);

        public final String value;
        public final int score;

        EducationLevel(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static EducationLevel parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum EducationField implements Answer {
        ACCOUNTING("fieldOfStudyAccounting", 3),
        LAW("fieldOfStudyLaw", 1),
        COMPUTER("fieldOfStudyComputer", 0),
        MEDICINE("fieldOfStudyMedicine", 0),
        OTHER("fieldOfStudyOther", 0);

        public final String value;
        public final int score;

        EducationField(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static EducationField parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum IsPoliticallyExposed implements Answer {
        NO("politicallyExposedNo", 0),
        YES("politicallyExposedYes", 0);

        public final String value;
        public final int score;

        IsPoliticallyExposed(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static IsPoliticallyExposed parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum SourceOfFunds implements Answer {
        EMPLOYMENT("sourceOfFundsEmployment", 0),
        SAVINGS("sourceOfFundsSavings", 0),
        RETIREMENT("sourceOfFundsRetirement", 0),
        INHERITANCE("sourceOfFundsInheritance", 0),
        OTHER("sourceOfFundsOther", 0);

        public final String value;
        public final int score;

        SourceOfFunds(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static SourceOfFunds parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum AnnualIncome implements Answer {
        INCOME_OVER_100K("totalAnnualIncomeOver100k", 0),
        INCOME_50K_100K("totalAnnualIncome50k-100k", 0),
        INCOME_25K_50K("totalAnnualIncome25k-50k", 0),
        INCOME_15K_25K("totalAnnualIncome15k-25k", 0),
        INCOME_15KLESS("totalAnnualIncome15kless", 0);

        public final String value;
        public final int score;

        AnnualIncome(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static AnnualIncome parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum NetWealth implements Answer {
        NET_WEALTH_OVER_300K("netWealthOver300k", 0),
        NET_WEALTH_150K_300K("netWealth150k-300k", 0),
        NET_WEALTH_50K_150K("netWealth50k-150k", 0),
        NET_WEALTH_15K_50K("netWealth15k-50k", 0),
        NET_WEALTH_UNDER_15K("netWealthUnder15k", 0);

        public final String value;
        public final int score;

        NetWealth(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static NetWealth parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum ExpectedDepositsPerYear implements Answer {
        DEPOSITS_OVER_50K("expectedDepositsOver50k", 0),
        DEPOSITS_25K_50K("expectedDeposits25k-50k", 0),
        DEPOSITS_15K_25K("expectedDeposits15k-25k", 0),
        DEPOSITS_10K_15K("expectedDeposits10k-15k", 0),
        DEPOSITS_UNDER_10K("expectedDepositsUnder10k", 0);

        public final String value;
        public final int score;

        ExpectedDepositsPerYear(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static ExpectedDepositsPerYear parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum PurposeOfTrading implements Answer {
        SPECULATIVE("purposeOfTradingSpeculative", 0),
        ADDITIONAL_INCOME("purposeOfTradingAdditionalIncome", 0),
        HEDGING("purposeOfTradingHedging", 0),
        OTHER("purposeOfTradingOther", 0);

        public final String value;
        public final int score;

        PurposeOfTrading(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static PurposeOfTrading parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    /**
     * FNS Experience Level values
     */
    public enum SharesExperience implements Answer {
        FREQUENTLY("sharesFrequently", 4),
        REGULARLY("sharesRegularly", 3),
        OCCASIONALLY("sharesOccasionally", 1),
        NEVER("sharesNever", 0);

        public final String value;
        public final int score;

        SharesExperience(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static SharesExperience parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum BinaryExperience implements Answer {
        FREQUENTLY("binaryFrequently", 7),
        REGULARLY("binaryRegularly", 5),
        OCCASIONALLY("binaryOccasionally", 3),
        NEVER("binaryNever", 0);

        public final String value;
        public final int score;

        BinaryExperience(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static BinaryExperience parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum AverageYearlyBinaryVolume implements Answer {
        VOLUME_ABOVE_10K("financialProducts1VolumeAbove10k", 8),
        VOLUME_5K_10K("financialProducts1Volume5k-10k", 6),
        VOLUME_500_5K("financialProducts1Volume500-5k", 4),
        VOLUME_UNDER_500("financialProducts1VolumeUnder500", 2);

        public final String value;
        public final int score;

        AverageYearlyBinaryVolume(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static AverageYearlyBinaryVolume parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum ForExExperience implements Answer {
        FREQUENTLY("forexFrequently", 10),
        REGULARLY("forexRegularly", 8),
        OCCASIONALLY("forexOccasionally", 6),
        NEVER("forexNever", 0);

        public final String value;
        public final int score;

        ForExExperience(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static ForExExperience parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum AverageYearlyForExVolume implements Answer {
        VOLUME_ABOVE_500K("financialProducts2VolumeAbove500k", 10),
        VOLUME_150K_500K("financialProducts2Volume150k-500k", 8),
        VOLUME_50K_150K("financialProducts2Volume50k-150k", 6),
        VOLUME_UNDER_50K("financialProducts2VolumeUnder50k", 4);

        public final String value;
        public final int score;

        AverageYearlyForExVolume(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static AverageYearlyForExVolume parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum CommonForExLeverage implements Answer {
        LEVERAGE_ABOVE_1TO500("financialProducts2LeverageAbove1to500", 5),
        LEVERAGE_1TO200_1TO500("financialProducts2Leverage1to200-1to500", 4),
        LEVERAGE_1TO50_1TO200("financialProducts2Leverage1to50-1to200", 3),
        LEVERAGE_UNDER_1TO50("financialProducts2LeverageUnder1to50", 1);

        public final String value;
        public final int score;

        CommonForExLeverage(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static CommonForExLeverage parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum FinancialWorkExperience implements Answer {
        WORKED("selectIfApplicableWorked", 5),
        SEMINARS("selectIfApplicableAttended", 1),
        BOTH("selectIfApplicableBoth", 6),
        NEITHER("selectIfApplicableNeither", 0);

        public final String value;
        public final int score;

        FinancialWorkExperience(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static FinancialWorkExperience parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum CfdBinaryKnowledge implements Answer {
        SPECULATIVE("knowledgeCfdsSpeculative", 4),
        NON_RISKY("knowledgeCfdsNonRisky", 0),
        PHYSICALLY_DELIVERING("knowledgeCfdsPhysicallyDelivering", 0);

        public final String value;
        public final int score;

        CfdBinaryKnowledge(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static CfdBinaryKnowledge parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum MainFactorKnowledge implements Answer {
        INTEREST_RATES("knowledgeMainFactorInterestRates", 2),
        ANNOUNCEMENT("knowledgeMainFactorAnnouncement", 0),
        EMPLOYEE_LAYOFFS("knowledgeMainFactorEmployeeLayoffs", 0);

        public final String value;
        public final int score;

        MainFactorKnowledge(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static MainFactorKnowledge parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum HowToCloseKnowledge implements Answer {
        ONLY_PLATFORM("knowledgeIfYouOpenOnlyPlatform", 3),
        LONDON_STOCK("knowledgeIfYouOpenLondonStock", 0),
        CANNOT_SELL("knowledgeIfYouOpenCannotSell", 0);

        public final String value;
        public final int score;

        HowToCloseKnowledge(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static HowToCloseKnowledge parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum CfdLeverageKnowledge implements Answer {
        MAGNIFIES("knowledgeLeverageCFDMagnifies", 3),
        PROVIDES("knowledgeLeverageCFDProvides", 0),
        NONE("knowledgeLeverageCFDNone", 0);

        public final String value;
        public final int score;

        CfdLeverageKnowledge(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static CfdLeverageKnowledge parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum StopLossKnowledge implements Answer {
        MINIMIZE("knowledgeStoplossMinimize", 3),
        BUY("knowledgeStoplossBuy", 0),
        TAKE("knowledgeStoplossTake", 0);

        public final String value;
        public final int score;

        StopLossKnowledge(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static StopLossKnowledge parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum RequiredMarginKnowledge implements Answer {
        MARGIN_1K("knowledgeRequiredMargin1k", 4),
        MARGIN_10K("knowledgeRequiredMargin10k", 0),
        MARGIN_100K("knowledgeRequiredMargin100k", 0);

        public final String value;
        public final int score;

        RequiredMarginKnowledge(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static RequiredMarginKnowledge parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum MarginLevelDropKnowledge implements Answer {
        MARGIN_CALL("knowledgeMarginLevelDropMarginCall", 3),
        WARNING_CALL("knowledgeMarginLevelDropWarningCall", 0),
        MARGIN_UPGRADE("knowledgeMarginLevelDropMarginUpgrade", 0);

        public final String value;
        public final int score;

        MarginLevelDropKnowledge(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static MarginLevelDropKnowledge parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum AutomaticStopKnowledge implements Answer {
        EQUITY_FALLS("knowledgeAutomaticStopEquityFalls", 3),
        EARNINGS("knowledgeAutomaticStopEarnings", 0),
        OPEN_POSITION("knowledgeAutomaticStopOpenPosition", 0);

        public final String value;
        public final int score;

        AutomaticStopKnowledge(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static AutomaticStopKnowledge parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum LossOn1to50Knowledge implements Answer {
        A1_800("knowledgePositionLoss1to50A1", 2),
        A2_450("knowledgePositionLoss1to50A2", 0),
        A3_200("knowledgePositionLoss1to50A3", 0);

        public final String value;
        public final int score;

        LossOn1to50Knowledge(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static LossOn1to50Knowledge parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum LossOn1to200Knowledge implements Answer {
        A1_1800("knowledgePositionLoss1to200A1", 2),
        A2_1200("knowledgePositionLoss1to200A2", 0),
        A3_1000("knowledgePositionLoss1to200A3", 0);

        public final String value;
        public final int score;

        LossOn1to200Knowledge(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static LossOn1to200Knowledge parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum BinaryInvestProfitKnowledge implements Answer {
        PROFIT_75("knowledgeInvestBinaryProfit75", 3),
        PROFIT_60("knowledgeInvestBinaryProfit60", 0),
        PROFIT_100("knowledgeInvestBinaryProfit100", 0);

        public final String value;
        public final int score;

        BinaryInvestProfitKnowledge(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static BinaryInvestProfitKnowledge parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum BinaryInvestLossKnowledge implements Answer {
        LOSS_100("knowledgeInvestBinaryLoss100", 4),
        LOSS_75("knowledgeInvestBinaryLoss75", 0),
        LOSS_50("knowledgeInvestBinaryLoss50", 0);

        public final String value;
        public final int score;

        BinaryInvestLossKnowledge(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static BinaryInvestLossKnowledge parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum BinaryProbabilityKnowledge implements Answer {
        MONEY_25("knowledgeBinaryInTheMoney25", 4),
        MONEY_35("knowledgeBinaryInTheMoney35", 0),
        MONEY_45("knowledgeBinaryInTheMoney45", 0);

        public final String value;
        public final int score;

        BinaryProbabilityKnowledge(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static BinaryProbabilityKnowledge parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum InstrumentsTradedBefore implements Answer {
        LEVERAGED("productLeveraged", 0),
        SHARES("productBonds", 0),
        BINARY("productBinary", 0),
        NO_EXPERIENCE("productNever", 0);

        public final String value;
        public final int score;

        InstrumentsTradedBefore(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static InstrumentsTradedBefore parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum FrequencyPastTransactions implements Answer {
        FREQUENTLY("sharesFrequently", 0),
        REGULARLY("sharesRegularly", 0),
        OCCASIONALLY("sharesOccasionally", 0);

        public final String value;
        public final int score;

        FrequencyPastTransactions(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static FrequencyPastTransactions parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum VolumePastTransaction implements Answer {
        MORE_THAN_100("volumeMoreThan_100", 0),
        BETWEEN_10_100("volumeBetween_10-100", 0),
        LESS_THAN_10("volumeLessThan_10", 0);

        public final String value;
        public final int score;

        VolumePastTransaction(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static VolumePastTransaction parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    public enum CommonLevelPastTransaction implements Answer {
        LEVERAGE_1_200_TO_1_500("commonLeverage_1_to_200_or_1_to_500", 0),
        LEVERAGE_HIGHER_THAN_1_500("commonLeverage_HigherThan_1_to_500", 0),
        LEVERAGE_1_50_TO_1_200("commonLeverage_1_to_50_or_1_to_200", 0),
        LOWER_THAN_1_50("commonLeverage_LowerThan_1_to_50", 0);

        public final String value;
        public final int score;

        CommonLevelPastTransaction(String value, int score) {
            this.value = value;
            this.score = score;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getScore() {
            return score;
        }

        public static CommonLevelPastTransaction parse(String input) {
            return QuestionnaireAnswers.parse(values(), input);
        }
    }

    private static <T extends Answer> T parse(T[] enums, String input) {
        return Arrays.stream(enums)
                .filter(e -> e.getValue().equals(input))
                .findFirst()
                .orElseThrow(() -> noConstantException(input));
    }

    private static IllegalArgumentException noConstantException(String value) {
        return new IllegalArgumentException("No constant with value " + value + " found");
    }

}
