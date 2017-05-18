package com.betamedia.qe.af.testslibrary.option24.end2end.crm;

import com.betamedia.qe.af.core.api.crm.form.builders.TradingExperienceInfoBuilder;
import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.testingtype.tp.TPResourceAwareEndToEndTest;
import org.testng.annotations.Test;

/**
 * Created by mbelyaev on 5/17/17.
 */
public class CrmUserExperienceTest extends TPResourceAwareEndToEndTest{
    private static final String USERNAME = "qetest@qe.test";
    private final static String PASSWORD = CustomerBuilder.PASSWORD;

    @Test
    public void experienceInputTest(){

        pages().crmNavigation().login();
        pages().crmLoginPage().login(USERNAME, PASSWORD);
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit
                (new TradingExperienceInfoBuilder()
                .withSharesExperience("sharesRegularly")
                .withBinaryExperience("binaryRegularly")
                .withAverageYearlyBinaryVolume("financialProducts1Volume5k-10k")
                .withForExExperience("forexRegularly")
                .withAverageYearlyForExVolume("financialProducts2Volume50k-150k")
                .withCommonLeverage("financialProducts2Leverage1to50-1to200")
                .withFinancialWorkExperience("selectIfApplicableWorked")
                .withCfdBinaryKnowledge("knowledgeCfdsBinariesSpeculative")
                .build()
        );
    }
}
