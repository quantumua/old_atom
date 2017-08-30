package com.betamedia.atom.core.api.crm.form.parsers;

import com.betamedia.atom.core.api.crm.form.entities.QuestionnaireData;
import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static com.betamedia.atom.core.api.crm.form.entities.QuestionnaireAnswers.*;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

/**
 * Created by mbelyaev on 5/23/17.
 */
public class TradingExperienceInfoParserTest {
    @Test
    public void testParse() throws Exception {
        TradingExperienceInfo expectedInfo = getExpectedInfo();
        QuestionnaireData data = createSourceData(expectedInfo);
        assertEquals(TradingExperienceInfoParser.parse(data), expectedInfo);
    }

    private TradingExperienceInfo getExpectedInfo() {
        return TradingExperienceInfo.builder()
                .withSharesExperience(SharesExperience.FREQUENTLY)
                .withBinaryExperience(BinaryExperience.FREQUENTLY)
                .withAverageYearlyBinaryVolume(AverageYearlyBinaryVolume.VOLUME_5K_10K)
                .withForExExperience(ForExExperience.FREQUENTLY)
                .withAverageYearlyForExVolume(AverageYearlyForExVolume.VOLUME_50K_150K)
                .withCommonLeverage(CommonForExLeverage.LEVERAGE_1TO50_1TO200)
                .withFinancialWorkExperience(FinancialWorkExperience.BOTH)
                .withCfdBinaryKnowledge(CfdBinaryKnowledge.NON_RISKY)
                .withMainFactorKnowledge(MainFactorKnowledge.ANNOUNCEMENT)
                .withHowToCloseKnowledge(HowToCloseKnowledge.CANNOT_SELL)
                .withCfdLeverageKnowledge(CfdLeverageKnowledge.MAGNIFIES)
                .withStopLossKnowledge(StopLossKnowledge.BUY)
                .withRequiredMarginKnowledge(RequiredMarginKnowledge.MARGIN_1K)
                .withMarginLevelDropKnowledge(MarginLevelDropKnowledge.MARGIN_CALL)
                .withAutomaticStopKnowledge(AutomaticStopKnowledge.EARNINGS)
                .withLossOn1to50Knowledge(LossOn1to50Knowledge.A1_800)
                .withLossOn1to200Knowledge(LossOn1to200Knowledge.A1_1800)
                .withBinaryInvestProfitKnowledge(BinaryInvestProfitKnowledge.PROFIT_60)
                .withBinaryInvestLossKnowledge(BinaryInvestLossKnowledge.LOSS_50)
                .withBinaryProbabilityKnowledge(BinaryProbabilityKnowledge.MONEY_25)
                .withInstrumentsTradedBefore(InstrumentsTradedBefore.LEVERAGED)
                .withFrequencyPastTransactions(FrequencyPastTransactions.FREQUENTLY)
                .withVolumePastTransaction(VolumePastTransaction.LESS_THAN_10)
                .withCommonLevelPastTransaction(CommonLevelPastTransaction.LOWER_THAN_1_50)
                .build();
    }

    private QuestionnaireData createSourceData(TradingExperienceInfo source) {
        QuestionnaireData result = new QuestionnaireData();
        Arrays.stream(source.getClass().getFields())
                .filter(notExpectedScore())
                .forEach(setValue(source, result));
        return result;
    }

    private Predicate<Field> notExpectedScore() {
        return field -> !"expectedScore".equals(field.getName());
    }

    private Consumer<Field> setValue(TradingExperienceInfo source, QuestionnaireData data) {
        return field -> setField(data, field.getName(), getField(source, field.getName()));
    }

    private void assertEquals(TradingExperienceInfo actual, TradingExperienceInfo expected) {
        Arrays.stream(actual.getClass().getFields())
                .map(Field::getName)
                .forEach(fn -> Assert.assertEquals(getField(actual, fn), getField(expected, fn)));
    }


}