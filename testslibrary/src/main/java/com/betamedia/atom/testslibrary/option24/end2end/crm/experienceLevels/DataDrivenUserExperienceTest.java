package com.betamedia.atom.testslibrary.option24.end2end.crm.experienceLevels;

import com.betamedia.atom.core.api.crm.form.entities.QuestionnaireData;
import com.betamedia.atom.core.api.crm.form.parsers.PersonalInformationParser;
import com.betamedia.atom.core.api.crm.form.parsers.TradingExperienceInfoParser;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.testingtype.tp.TPEndToEndTest;
import org.testng.annotations.Test;

/**
 * Created by mbelyaev on 5/17/17.
 */
public class DataDrivenUserExperienceTest extends TPEndToEndTest {

    @Test(dataProvider = "GenericDataProvider")
    public void dataDrivenInformationInputTest(QuestionnaireData data) {
        CustomerRO customer = CustomerRO.builder().build();
        pages().crmNavigation().register();
        pages().register().register(customer);
        pages().crmNavigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(TradingExperienceInfoParser.parse(data));
        pages().crmNavigation().fnsPersonalInformation();
        pages().fnsPersonalInformation().submit(PersonalInformationParser.parse(data));
        operations().onBoardingOperations().assertUsernameLowExperience(customer.getEmail());
    }

    @Override
    protected Class getDataSourceEntity() {
        return QuestionnaireData.class;
    }

    @Override
    protected String getDataSourcePath() {
        return "/data/questionnaireData.csv";
    }

}
