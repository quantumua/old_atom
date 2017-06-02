package com.betamedia.qe.af.core.api.crm.form.parsers;

import com.betamedia.qe.af.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.qe.af.core.api.crm.form.entities.QuestionnaireData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

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
                .withSharesExperience("sampleSharesExperience")
                .withBinaryExperience("sampleBinaryExperience")
                .withAverageYearlyBinaryVolume("sampleAverageYearlyBinaryVolume")
                .withForExExperience("sampleForExExperience")
                .withAverageYearlyForExVolume("sampleAverageYearlyForExVolume")
                .withCommonLeverage("sampleCommonLeverage")
                .withFinancialWorkExperience("sampleFinancialWorkExperience")
                .withCfdBinaryKnowledge("sampleCfdBinaryKnowledge")
                .withMainFactorKnowledge("sampleMainFactorKnowledge")
                .withHowToCloseKnowledge("sampleHowToCloseKnowledge")
                .withCfdLeverageKnowledge("sampleCfdLeverageKnowledge")
                .withStopLossKnowledge("sampleStopLossKnowledge")
                .withRequiredMarginKnowledge("sampleRequiredMarginKnowledge")
                .withMarginLevelDropKnowledge("sampleMarginLevelDropKnowledge")
                .withAutomaticStopKnowledge("sampleAutomaticStopKnowledge")
                .withLossOn1to50Knowledge("sampleLossOn1to50Knowledge")
                .withLossOn1to200Knowledge("sampleLossOn1to200Knowledge")
                .withBinaryInvestProfitKnowledge("sampleBinaryInvestProfitKnowledge")
                .withBinaryInvestLossKnowledge("sampleBinaryInvestLossKnowledge")
                .withBinaryProbabilityKnowledge("sampleBinaryProbabilityKnowledge")
                .build();
    }

    private QuestionnaireData createSourceData(TradingExperienceInfo source) {
        QuestionnaireData data = new QuestionnaireData();
        Arrays.stream(source.getClass().getFields()).forEach(f -> setField(data, f.getName(), getField(source, f.getName())));
        return data;
    }

    private void assertEquals(TradingExperienceInfo actual, TradingExperienceInfo expected) {
        Arrays.stream(actual.getClass().getFields())
                .map(Field::getName)
                .forEach(fn -> Assert.assertEquals(getField(actual, fn), getField(expected, fn)));
    }


}