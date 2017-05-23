package com.betamedia.qe.af.core.api.crm.form.parsers;

import com.betamedia.qe.af.core.api.crm.form.builders.PersonalInformationBuilder;
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
public class PersonalInformationParserTest {
    @Test
    public void testParse() throws Exception {
        PersonalInformationBuilder.PersonalInformation expectedInfo = getExpectedInfo();
        QuestionnaireData data = createSourceData(expectedInfo);
        assertEquals(PersonalInformationParser.parse(data), expectedInfo);
    }

    private PersonalInformationBuilder.PersonalInformation getExpectedInfo() {
        return new PersonalInformationBuilder()
                .withEmploymentStatus("sampleStatus")
                .withIndustry("sampleIndustry")
                .withIndustryOther("sampleIndustryOther")
                .withEmployerName("sampleEmployerName")
                .withTaxResidenceCountry("sampleTaxResidenceCountry")
                .withUSReportabilityStatus("sampleUSReportable")
                .withTaxIdentificationNumberStatus("sampleTINStatus")
                .withTaxIdentificationNumber("sampleTIN")
                .withSocialSecurityNumber("sampleSSN")
                .withEducationLevel("sampleEduLevel")
                .withEducationField("sampleEduField")
                .withEducationFieldOther("sampleEduFieldOther")
                .withPoliticalExposureStatus("samplePoliticalExposure")
                .withPoliticalExposureComment("samplePoliticalComment")
                .withSourceOfFunds("sampleSourceOfFunds")
                .withSourceOfFundsOther("sampleSourceOfFundsOther")
                .withAnnualIncome("sampleAnnualIncome")
                .withNetWealth("sampleNetWealth")
                .withExpectedDepositsPerYear("sampleDepositsPerYear")
                .withPurposeOfTrading("samplePurposeOfTrading")
                .withPurposeOfTradingOther("samplePurposeOfTradingOther")
                .build();
    }

    private QuestionnaireData createSourceData(PersonalInformationBuilder.PersonalInformation source) {
        QuestionnaireData data = new QuestionnaireData();
        Arrays.stream(source.getClass().getFields()).forEach(f -> setField(data, f.getName(), getField(source, f.getName())));
        return data;
    }

    private void assertEquals(PersonalInformationBuilder.PersonalInformation actual, PersonalInformationBuilder.PersonalInformation expected) {
        Arrays.stream(actual.getClass().getFields())
                .map(Field::getName)
                .forEach(fn -> Assert.assertEquals(getField(actual, fn), getField(expected, fn)));
    }


}