package com.betamedia.atom.core.api.crm.form.entities;

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

    private TradingExperienceInfo(String sharesExperience, String binaryExperience, String averageYearlyBinaryVolume, String forExExperience, String averageYearlyForExVolume, String commonLeverage, String financialWorkExperience, String cfdBinaryKnowledge, String mainFactorKnowledge, String howToCloseKnowledge, String cfdLeverageKnowledge, String stopLossKnowledge, String requiredMarginKnowledge, String marginLevelDropKnowledge, String automaticStopKnowledge, String lossOn1to50Knowledge, String lossOn1to200Knowledge, String binaryInvestProfitKnowledge, String binaryInvestLossKnowledge, String binaryProbabilityKnowledge) {
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
                '}';
    }

    public static TradingExperienceInfoBuilder builder(){
        return new TradingExperienceInfoBuilder();
    }

    public static class TradingExperienceInfoBuilder {
        private String sharesExperience;
        private String binaryExperience;
        private String averageYearlyBinaryVolume;
        private String forExExperience;
        private String averageYearlyForExVolume;
        private String commonLeverage;
        private String financialWorkExperience;
        private String cfdBinaryKnowledge;
        private String mainFactorKnowledge;
        private String howToCloseKnowledge;
        private String cfdLeverageKnowledge;
        private String stopLossKnowledge;
        private String requiredMarginKnowledge;
        private String marginLevelDropKnowledge;
        private String automaticStopKnowledge;
        private String lossOn1to50Knowledge;
        private String lossOn1to200Knowledge;
        private String binaryInvestProfitKnowledge;
        private String binaryInvestLossKnowledge;
        private String binaryProbabilityKnowledge;

        private TradingExperienceInfoBuilder(){}

        public TradingExperienceInfoBuilder withSharesExperience(String sharesExperience) {
            this.sharesExperience = sharesExperience;
            return this;
        }

        public TradingExperienceInfoBuilder withBinaryExperience(String binaryExperience) {
            this.binaryExperience = binaryExperience;
            return this;
        }

        public TradingExperienceInfoBuilder withAverageYearlyBinaryVolume(String averageYearlyBinaryVolume) {
            this.averageYearlyBinaryVolume = averageYearlyBinaryVolume;
            return this;
        }

        public TradingExperienceInfoBuilder withForExExperience(String forExExperience) {
            this.forExExperience = forExExperience;
            return this;
        }

        public TradingExperienceInfoBuilder withAverageYearlyForExVolume(String averageYearlyForExVolume) {
            this.averageYearlyForExVolume = averageYearlyForExVolume;
            return this;
        }

        public TradingExperienceInfoBuilder withCommonLeverage(String commonLeverage) {
            this.commonLeverage = commonLeverage;
            return this;
        }

        public TradingExperienceInfoBuilder withFinancialWorkExperience(String financialWorkExperience) {
            this.financialWorkExperience = financialWorkExperience;
            return this;
        }

        public TradingExperienceInfoBuilder withCfdBinaryKnowledge(String cfdBinaryKnowledge) {
            this.cfdBinaryKnowledge = cfdBinaryKnowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withMainFactorKnowledge(String mainFactorKnowledge) {
            this.mainFactorKnowledge = mainFactorKnowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withHowToCloseKnowledge(String howToCloseKnowledge) {
            this.howToCloseKnowledge = howToCloseKnowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withCfdLeverageKnowledge(String cfdLeverageKnowledge) {
            this.cfdLeverageKnowledge = cfdLeverageKnowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withStopLossKnowledge(String stopLossKnowledge) {
            this.stopLossKnowledge = stopLossKnowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withRequiredMarginKnowledge(String requiredMarginKnowledge) {
            this.requiredMarginKnowledge = requiredMarginKnowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withMarginLevelDropKnowledge(String marginLevelDropKnowledge) {
            this.marginLevelDropKnowledge = marginLevelDropKnowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withAutomaticStopKnowledge(String automaticStopKnowledge) {
            this.automaticStopKnowledge = automaticStopKnowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withLossOn1to50Knowledge(String lossOn1to50Knowledge) {
            this.lossOn1to50Knowledge = lossOn1to50Knowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withLossOn1to200Knowledge(String lossOn1to200Knowledge) {
            this.lossOn1to200Knowledge = lossOn1to200Knowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withBinaryInvestProfitKnowledge(String binaryInvestProfitKnowledge) {
            this.binaryInvestProfitKnowledge = binaryInvestProfitKnowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withBinaryInvestLossKnowledge(String binaryInvestLossKnowledge) {
            this.binaryInvestLossKnowledge = binaryInvestLossKnowledge;
            return this;
        }

        public TradingExperienceInfoBuilder withBinaryProbabilityKnowledge(String binaryProbabilityKnowledge) {
            this.binaryProbabilityKnowledge = binaryProbabilityKnowledge;
            return this;
        }

        public TradingExperienceInfo build() {
            return new TradingExperienceInfo(
                    sharesExperience,
                    binaryExperience,
                    averageYearlyBinaryVolume,
                    forExExperience,
                    averageYearlyForExVolume,
                    commonLeverage,
                    financialWorkExperience,
                    cfdBinaryKnowledge,
                    mainFactorKnowledge,
                    howToCloseKnowledge,
                    cfdLeverageKnowledge,
                    stopLossKnowledge,
                    requiredMarginKnowledge,
                    marginLevelDropKnowledge,
                    automaticStopKnowledge,
                    lossOn1to50Knowledge,
                    lossOn1to200Knowledge,
                    binaryInvestProfitKnowledge,
                    binaryInvestLossKnowledge,
                    binaryProbabilityKnowledge
            );
        }


    }
}
