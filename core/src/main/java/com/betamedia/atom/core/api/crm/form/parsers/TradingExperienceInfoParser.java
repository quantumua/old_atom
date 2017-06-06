package com.betamedia.atom.core.api.crm.form.parsers;

import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.atom.core.api.crm.form.entities.QuestionnaireData;

/**
 * Created by mbelyaev on 5/23/17.
 */
public class TradingExperienceInfoParser {
    public static TradingExperienceInfo parse(QuestionnaireData data) {
        return TradingExperienceInfo.builder()
                .withSharesExperience(data.getSharesExperience())
                .withBinaryExperience(data.getBinaryExperience())
                .withForExExperience(data.getForExExperience())
                .withFinancialWorkExperience(data.getFinancialWorkExperience())
                .withCfdBinaryKnowledge(data.getCfdBinaryKnowledge())
                .withMainFactorKnowledge(data.getMainFactorKnowledge())
                .withHowToCloseKnowledge(data.getHowToCloseKnowledge())
                .withCfdLeverageKnowledge(data.getCfdLeverageKnowledge())
                .withStopLossKnowledge(data.getStopLossKnowledge())
                .withRequiredMarginKnowledge(data.getRequiredMarginKnowledge())
                .withMarginLevelDropKnowledge(data.getMarginLevelDropKnowledge())
                .withAutomaticStopKnowledge(data.getAutomaticStopKnowledge())
                .withLossOn1to50Knowledge(data.getLossOn1to50Knowledge())
                .withLossOn1to200Knowledge(data.getLossOn1to200Knowledge())
                .withBinaryInvestProfitKnowledge(data.getBinaryInvestProfitKnowledge())
                .withBinaryInvestLossKnowledge(data.getBinaryInvestLossKnowledge())
                .withBinaryProbabilityKnowledge(data.getBinaryProbabilityKnowledge())
                .withAverageYearlyBinaryVolume(data.getAverageYearlyBinaryVolume())
                .withAverageYearlyForExVolume(data.getAverageYearlyForExVolume())
                .withCommonLeverage(data.getCommonLeverage())
                .build();
    }
}
