package com.betamedia.qe.af.core.api.crm.form.builders;

/**
 * Created by mbelyaev on 5/17/17.
 */
public class TradingExperienceInfoBuilder {
    private String sharesExperience;
    private String binaryExperience;
    private String averageYearlyBinaryVolume;
    private String forExExperience;
    private String averageYearlyForExVolume;
    private String commonLeverage;
    private String financialWorkExperience;
    private String cfdBinaryKnowledge;

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


    public TradingExperienceInfo build() {
        return new TradingExperienceInfo(
                sharesExperience,
                binaryExperience,
                averageYearlyBinaryVolume,
                forExExperience,
                averageYearlyForExVolume,
                commonLeverage,
                financialWorkExperience,
                cfdBinaryKnowledge);
    }

    public class TradingExperienceInfo {
        public final String sharesExperience;
        public final String binaryExperience;
        public final String averageYearlyBinaryVolume;
        public final String forExExperience;
        public final String averageYearlyForExVolume;
        public final String commonLeverage;
        public final String financialWorkExperience;
        public final String cfdBinaryKnowledge;

        private TradingExperienceInfo(String sharesExperience, String binaryExperience, String averageYearlyBinaryVolume, String forExExperience, String averageYearlyForExVolume, String commonLeverage, String financialWorkExperience, String cfdBinaryKnowledge) {
            this.sharesExperience = sharesExperience;
            this.binaryExperience = binaryExperience;
            this.averageYearlyBinaryVolume = averageYearlyBinaryVolume;
            this.forExExperience = forExExperience;
            this.averageYearlyForExVolume = averageYearlyForExVolume;
            this.commonLeverage = commonLeverage;
            this.financialWorkExperience = financialWorkExperience;
            this.cfdBinaryKnowledge = cfdBinaryKnowledge;
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
                    '}';
        }
    }


}
