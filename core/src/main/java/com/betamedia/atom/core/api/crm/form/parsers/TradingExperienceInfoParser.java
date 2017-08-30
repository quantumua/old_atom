package com.betamedia.atom.core.api.crm.form.parsers;

import com.betamedia.atom.core.api.crm.form.entities.QuestionnaireData;
import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;

import static com.betamedia.atom.core.api.crm.form.entities.QuestionnaireAnswers.*;

/**
 * Created by mbelyaev on 5/23/17.
 */
public class TradingExperienceInfoParser {
    public static TradingExperienceInfo parse(QuestionnaireData data) {
        return TradingExperienceInfo.builder()
                .withSharesExperience(SharesExperience.parse(data.getSharesExperience()))
                .withBinaryExperience(BinaryExperience.parse(data.getBinaryExperience()))
                .withForExExperience(ForExExperience.parse(data.getForExExperience()))
                .withFinancialWorkExperience(FinancialWorkExperience.parse(data.getFinancialWorkExperience()))
                .withCfdBinaryKnowledge(CfdBinaryKnowledge.parse(data.getCfdBinaryKnowledge()))
                .withMainFactorKnowledge(MainFactorKnowledge.parse(data.getMainFactorKnowledge()))
                .withHowToCloseKnowledge(HowToCloseKnowledge.parse(data.getHowToCloseKnowledge()))
                .withCfdLeverageKnowledge(CfdLeverageKnowledge.parse(data.getCfdLeverageKnowledge()))
                .withStopLossKnowledge(StopLossKnowledge.parse(data.getStopLossKnowledge()))
                .withRequiredMarginKnowledge(RequiredMarginKnowledge.parse(data.getRequiredMarginKnowledge()))
                .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.parse(data.getMarginLevelDropKnowledge()))
                .withAutomaticStopKnowledge(AutomaticStopKnowledge.parse(data.getAutomaticStopKnowledge()))
                .withLossOn1to50Knowledge(LossOn1to50Knowledge.parse(data.getLossOn1to50Knowledge()))
                .withLossOn1to200Knowledge(LossOn1to200Knowledge.parse(data.getLossOn1to200Knowledge()))
                .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.parse(data.getBinaryInvestProfitKnowledge()))
                .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.parse(data.getBinaryInvestLossKnowledge()))
                .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.parse(data.getBinaryProbabilityKnowledge()))
                .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.parse(data.getAverageYearlyBinaryVolume()))
                .withAverageYearlyForExVolume(AverageYearlyForExVolume.parse(data.getAverageYearlyForExVolume()))
                .withCommonLeverage(CommonForExLeverage.parse(data.getCommonLeverage()))
                .withInstrumentsTradedBefore(InstrumentsTradedBefore.parse(data.getInstrumentsTradedBefore()))
                .withFrequencyPastTransactions(FrequencyPastTransactions.parse(data.getFrequencyPastTransactions()))
                .withVolumePastTransaction(VolumePastTransaction.parse(data.getVolumePastTransaction()))
                .withCommonLevelPastTransaction(CommonLevelPastTransaction.parse(data.getCommonLevelPastTransaction()))
                .build();
    }
}
