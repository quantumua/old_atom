package com.betamedia.qe.af.testslibrary.option24.end2end.crm;

import com.betamedia.qe.af.core.api.tp.entities.OnboardingWizardConditions;
import com.betamedia.qe.af.core.testingtype.tp.TPEndToEndTest;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.core.io.ClassPathResource;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;

/**
 * Created by Oleksandr Losiev on 5/19/17.
 */
public class MobileCRMOnboardingWizardConditionsTest extends TPEndToEndTest {

    @DataProvider(name = "conditions")
    public static Object[][] conditions() {
        List<OnboardingWizardConditions> wizardConditionsList = new ArrayList<>();
        HeaderColumnNameMappingStrategy<OnboardingWizardConditions> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(OnboardingWizardConditions.class);
        CsvToBean<OnboardingWizardConditions> csvToBean = new CsvToBean<>();

        try (InputStream resourceInputStream = new ClassPathResource("/data/wizardConditionTestCases.csv").getInputStream();) {
            wizardConditionsList = csvToBean.parse(strategy, new CSVReader(new InputStreamReader(resourceInputStream)));
        } catch (Exception e) {
            fail("Failed to initialize wizard condition tests");
        }

        Object[][] result = new Object[wizardConditionsList.size()][1];
        for (int i = 0; i < wizardConditionsList.size(); i++) {
            result[i][0] = wizardConditionsList.get(i);
        }
        return result;

    }

    @Test(dataProvider = "conditions")
    public void testWizard(OnboardingWizardConditions wizardConditions) {
        operations().customerOperations().registerWithWizardConditions(wizardConditions);
    }
}
