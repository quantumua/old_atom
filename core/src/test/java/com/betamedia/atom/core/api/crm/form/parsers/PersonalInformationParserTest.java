package com.betamedia.atom.core.api.crm.form.parsers;

import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.atom.core.api.crm.form.entities.QuestionnaireData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

import static com.betamedia.atom.core.api.crm.form.entities.QuestionnaireAnswers.*;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

/**
 * Created by mbelyaev on 5/23/17.
 */
public class PersonalInformationParserTest {
    @Test
    public void testParse() throws Exception {
        PersonalInformation expectedInfo = getExpectedInfo();
        QuestionnaireData data = createSourceData(expectedInfo);
        assertEquals(PersonalInformationParser.parse(data), expectedInfo);
    }

    private PersonalInformation getExpectedInfo() {
        return PersonalInformation.builder()
                .withEmploymentStatus(EmploymentStatus.RETIRED)
                .withIndustry(Industry.ACCOUNTING)
                .withIndustryOther("sampleIndustryOther")
                .withEmployerName("sampleEmployerName")
                .withTaxResidenceCountry("sampleTaxResidenceCountry")
                .withUSReportabilityStatus(IsUSReportable.NO)
                .withTaxIdentificationNumberStatus(HasTaxIdentificationNumber.NO)
                .withTaxIdentificationNumber("sampleTIN")
                .withSocialSecurityNumber("sampleSSN")
                .withEducationLevel(EducationLevel.BACHELOR)
                .withEducationField(EducationField.ACCOUNTING)
                .withEducationFieldOther("sampleEduFieldOther")
                .withPoliticalExposureStatus(IsPoliticallyExposed.NO)
                .withPoliticalExposureComment("samplePoliticalComment")
                .withSourceOfFunds(SourceOfFunds.EMPLOYMENT)
                .withSourceOfFundsOther("sampleSourceOfFundsOther")
                .withAnnualIncome(AnnualIncome.INCOME_15K_25K)
                .withNetWealth(NetWealth.NET_WEALTH_15K_50K)
                .withExpectedDepositsPerYear(ExpectedDepositsPerYear.DEPOSITS_10K_15K)
                .withPurposeOfTrading(PurposeOfTrading.ADDITIONAL_INCOME)
                .withPurposeOfTradingOther("samplePurposeOfTradingOther")
                .build();
    }

    private QuestionnaireData createSourceData(PersonalInformation source) {
        QuestionnaireData data = new QuestionnaireData();
        Arrays.stream(source.getClass().getFields()).forEach(f -> setField(data, f.getName(), getField(source, f.getName())));
        return data;
    }

    private void assertEquals(PersonalInformation actual, PersonalInformation expected) {
        Arrays.stream(actual.getClass().getFields())
                .map(Field::getName)
                .forEach(fn -> Assert.assertEquals(getField(actual, fn), getField(expected, fn)));
    }


}