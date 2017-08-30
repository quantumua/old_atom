package com.betamedia.atom.core.api.crm.form.entities;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static com.betamedia.atom.core.api.crm.form.entities.QuestionnaireAnswers.*;

/**
 * @author mbelyaev
 * @since 5/17/17
 */
public class TradingExperienceInfo {
    public final String sharesExperience;
    public final String binaryExperience;
    public final String averageYearlyBinaryVolume;
    public final String forExExperience;
    public final String averageYearlyForExVolume;
    public final String commonLeverage;
    public final String financialWorkExperience;
    public final String cfdBinaryKnowledge;
    public final String mainFactorKnowledge;
    public final String howToCloseKnowledge;
    public final String cfdLeverageKnowledge;
    public final String stopLossKnowledge;
    public final String requiredMarginKnowledge;
    public final String marginLevelDropKnowledge;
    public final String automaticStopKnowledge;
    public final String lossOn1to50Knowledge;
    public final String lossOn1to200Knowledge;
    public final String binaryInvestProfitKnowledge;
    public final String binaryInvestLossKnowledge;
    public final String binaryProbabilityKnowledge;

    public final String instrumentsTradedBefore;
    public final String frequencyPastTransactions;
    public final String volumePastTransaction;
    public final String commonLevelPastTransaction;

    public final int expectedScore;

    public TradingExperienceInfo(String sharesExperience, String binaryExperience, String averageYearlyBinaryVolume, String forExExperience, String averageYearlyForExVolume, String commonLeverage, String financialWorkExperience, String cfdBinaryKnowledge, String mainFactorKnowledge, String howToCloseKnowledge, String cfdLeverageKnowledge, String stopLossKnowledge, String requiredMarginKnowledge, String marginLevelDropKnowledge, String automaticStopKnowledge, String lossOn1to50Knowledge, String lossOn1to200Knowledge, String binaryInvestProfitKnowledge, String binaryInvestLossKnowledge, String binaryProbabilityKnowledge, String instrumentsTradedBefore, String frequencyPastTransactions, String volumePastTransaction, String commonLevelPastTransaction, int expectedScore) {
        this.sharesExperience = sharesExperience;
        this.binaryExperience = binaryExperience;
        this.averageYearlyBinaryVolume = averageYearlyBinaryVolume;
        this.forExExperience = forExExperience;
        this.averageYearlyForExVolume = averageYearlyForExVolume;
        this.commonLeverage = commonLeverage;
        this.financialWorkExperience = financialWorkExperience;
        this.cfdBinaryKnowledge = cfdBinaryKnowledge;
        this.mainFactorKnowledge = mainFactorKnowledge;
        this.howToCloseKnowledge = howToCloseKnowledge;
        this.cfdLeverageKnowledge = cfdLeverageKnowledge;
        this.stopLossKnowledge = stopLossKnowledge;
        this.requiredMarginKnowledge = requiredMarginKnowledge;
        this.marginLevelDropKnowledge = marginLevelDropKnowledge;
        this.automaticStopKnowledge = automaticStopKnowledge;
        this.lossOn1to50Knowledge = lossOn1to50Knowledge;
        this.lossOn1to200Knowledge = lossOn1to200Knowledge;
        this.binaryInvestProfitKnowledge = binaryInvestProfitKnowledge;
        this.binaryInvestLossKnowledge = binaryInvestLossKnowledge;
        this.binaryProbabilityKnowledge = binaryProbabilityKnowledge;
        this.instrumentsTradedBefore = instrumentsTradedBefore;
        this.frequencyPastTransactions = frequencyPastTransactions;
        this.volumePastTransaction = volumePastTransaction;
        this.commonLevelPastTransaction = commonLevelPastTransaction;
        this.expectedScore = expectedScore;
    }

    @Override
    public String toString() {
        return "TradingExperienceInfo{" +
                "sharesExperience='" + sharesExperience + '\'' +
                ", binaryExperience='" + binaryExperience + '\'' +
                ", averageYearlyBinaryVolume='" + averageYearlyBinaryVolume + '\'' +
                ", forExExperience='" + forExExperience + '\'' +
                ", averageYearlyForExVolume='" + averageYearlyForExVolume + '\'' +
                ", commonLeverage='" + commonLeverage + '\'' +
                ", financialWorkExperience='" + financialWorkExperience + '\'' +
                ", cfdBinaryKnowledge='" + cfdBinaryKnowledge + '\'' +
                ", mainFactorKnowledge='" + mainFactorKnowledge + '\'' +
                ", howToCloseKnowledge='" + howToCloseKnowledge + '\'' +
                ", cfdLeverageKnowledge='" + cfdLeverageKnowledge + '\'' +
                ", stopLossKnowledge='" + stopLossKnowledge + '\'' +
                ", requiredMarginKnowledge='" + requiredMarginKnowledge + '\'' +
                ", marginLevelDropKnowledge='" + marginLevelDropKnowledge + '\'' +
                ", automaticStopKnowledge='" + automaticStopKnowledge + '\'' +
                ", lossOn1to50Knowledge='" + lossOn1to50Knowledge + '\'' +
                ", lossOn1to200Knowledge='" + lossOn1to200Knowledge + '\'' +
                ", binaryInvestProfitKnowledge='" + binaryInvestProfitKnowledge + '\'' +
                ", binaryInvestLossKnowledge='" + binaryInvestLossKnowledge + '\'' +
                ", binaryProbabilityKnowledge='" + binaryProbabilityKnowledge + '\'' +
                ", expectedScore=" + expectedScore +
                '}';
    }

    public static TradingExperienceInfoBuilder builder(){
        return new TradingExperienceInfoBuilder();
    }

    public static class TradingExperienceInfoBuilder {
        private SharesExperience sharesExperience;
        private BinaryExperience binaryExperience;
        private AverageYearlyBinaryVolume averageYearlyBinaryVolume;
        private ForExExperience forExExperience;
        private AverageYearlyForExVolume averageYearlyForExVolume;
        private CommonForExLeverage commonLeverage;
        private FinancialWorkExperience financialWorkExperience;
        private CfdBinaryKnowledge cfdBinaryKnowledge;
        private MainFactorKnowledge mainFactorKnowledge;
        private HowToCloseKnowledge howToCloseKnowledge;
        private CfdLeverageKnowledge cfdLeverageKnowledge;
        private StopLossKnowledge stopLossKnowledge;
        private RequiredMarginKnowledge requiredMarginKnowledge;
        private MarginLevelDropKnowledge marginLevelDropKnowledge;
        private AutomaticStopKnowledge automaticStopKnowledge;
        private LossOn1to50Knowledge lossOn1to50Knowledge;
        private LossOn1to200Knowledge lossOn1to200Knowledge;
        private BinaryInvestProfitKnowledge binaryInvestProfitKnowledge;
        private BinaryInvestLossKnowledge binaryInvestLossKnowledge;
        private BinaryProbabilityKnowledge binaryProbabilityKnowledge;
        private InstrumentsTradedBefore instrumentsTradedBefore;
        private FrequencyPastTransactions frequencyPastTransactions;
        private VolumePastTransaction volumePastTransaction;
        private CommonLevelPastTransaction commonLevelPastTransaction;
        private TradingExperienceInfoBuilder(){}

        public TradingExperienceInfoBuilder withSharesExperience(SharesExperience sharesExperience) {
            this.sharesExperience = sharesExperience;
            return this;
        }

        public TradingExperienceInfoBuilder withBinaryExperience(BinaryExperience binaryExperience) {
            this.binaryExperience = binaryExperience;
            return this;
        }

        public TradingExperienceInfoBuilder withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume averageYearlyBinaryVolume) {
            this.averageYearlyBinaryVolume = averageYearlyBinaryVolume;
            return this;
        }

        public TradingExperienceInfoBuilder withForExExperience(ForExExperience forExExperience) {
            this.forExExperience = forExExperience;
            return this;
        }

        public TradingExperienceInfoBuilder withAverageYearlyForExVolume(AverageYearlyForExVolume averageYearlyForExVolume) {
            this.averageYearlyForExVolume = averageYearlyForExVolume;
            return this;
        }

        public TradingExperienceInfoBuilder withCommonLeverage(CommonForExLeverage commonLeverage) {
            this.commonLeverage = commonLeverage;
            return this;
        }

        public TradingExperienceInfoBuilder withFinancialWorkExperience(FinancialWorkExperience financialWorkExperience) {
            this.financialWorkExperience = financialWorkExperience;
            return this;
        }

        public TradingExperienceInfoBuilder withCfdBinaryKnowledge(CfdBinaryKnowledge cfdBinaryKnowledge) {
            this.cfdBinaryKnowledge = cfdBinaryKnowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withMainFactorKnowledge(MainFactorKnowledge mainFactorKnowledge) {
            this.mainFactorKnowledge = mainFactorKnowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withHowToCloseKnowledge(HowToCloseKnowledge howToCloseKnowledge) {
            this.howToCloseKnowledge = howToCloseKnowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withCfdLeverageKnowledge(CfdLeverageKnowledge cfdLeverageKnowledge) {
            this.cfdLeverageKnowledge = cfdLeverageKnowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withStopLossKnowledge(StopLossKnowledge stopLossKnowledge) {
            this.stopLossKnowledge = stopLossKnowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withRequiredMarginKnowledge(RequiredMarginKnowledge requiredMarginKnowledge) {
            this.requiredMarginKnowledge = requiredMarginKnowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withMarginLevelDropKnowledge(MarginLevelDropKnowledge marginLevelDropKnowledge) {
            this.marginLevelDropKnowledge = marginLevelDropKnowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withAutomaticStopKnowledge(AutomaticStopKnowledge automaticStopKnowledge) {
            this.automaticStopKnowledge = automaticStopKnowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withLossOn1to50Knowledge(LossOn1to50Knowledge lossOn1to50Knowledge) {
            this.lossOn1to50Knowledge = lossOn1to50Knowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withLossOn1to200Knowledge(LossOn1to200Knowledge lossOn1to200Knowledge) {
            this.lossOn1to200Knowledge = lossOn1to200Knowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge binaryInvestProfitKnowledge) {
            this.binaryInvestProfitKnowledge = binaryInvestProfitKnowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge binaryInvestLossKnowledge) {
            this.binaryInvestLossKnowledge = binaryInvestLossKnowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge binaryProbabilityKnowledge) {
            this.binaryProbabilityKnowledge = binaryProbabilityKnowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withInstrumentsTradedBefore(InstrumentsTradedBefore instrumentsTradedBefore) {
            this.instrumentsTradedBefore = instrumentsTradedBefore;
            return this;
        }

        public TradingExperienceInfoBuilder withFrequencyPastTransactions(FrequencyPastTransactions frequencyPastTransactions) {
            this.frequencyPastTransactions = frequencyPastTransactions;
            return this;
        }

        public TradingExperienceInfoBuilder withVolumePastTransaction(VolumePastTransaction volumePastTransaction) {
            this.volumePastTransaction = volumePastTransaction;
            return this;
        }

        public TradingExperienceInfoBuilder withCommonLevelPastTransaction(CommonLevelPastTransaction commonLevelPastTransaction) {
            this.commonLevelPastTransaction = commonLevelPastTransaction;
            return this;
        }

        public TradingExperienceInfo build() {
            return new TradingExperienceInfo(
                    valueIfPresent(sharesExperience),
                    valueIfPresent(binaryExperience),
                    valueIfPresent(averageYearlyBinaryVolume),
                    valueIfPresent(forExExperience),
                    valueIfPresent(averageYearlyForExVolume),
                    valueIfPresent(commonLeverage),
                    valueIfPresent(financialWorkExperience),
                    valueIfPresent(cfdBinaryKnowledge),
                    valueIfPresent(mainFactorKnowledge),
                    valueIfPresent(howToCloseKnowledge),
                    valueIfPresent(cfdLeverageKnowledge),
                    valueIfPresent(stopLossKnowledge),
                    valueIfPresent(requiredMarginKnowledge),
                    valueIfPresent(marginLevelDropKnowledge),
                    valueIfPresent(automaticStopKnowledge),
                    valueIfPresent(lossOn1to50Knowledge),
                    valueIfPresent(lossOn1to200Knowledge),
                    valueIfPresent(binaryInvestProfitKnowledge),
                    valueIfPresent(binaryInvestLossKnowledge),
                    valueIfPresent(binaryProbabilityKnowledge),
                    valueIfPresent(instrumentsTradedBefore),
                    valueIfPresent(frequencyPastTransactions),
                    valueIfPresent(volumePastTransaction),
                    valueIfPresent(commonLevelPastTransaction),
                    getExpectedScore()
            );
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
