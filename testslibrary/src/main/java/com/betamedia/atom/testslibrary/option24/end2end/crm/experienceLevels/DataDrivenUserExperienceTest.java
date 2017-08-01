package com.betamedia.atom.testslibrary.option24.end2end.crm.experienceLevels;

import com.betamedia.atom.core.api.crm.form.entities.QuestionnaireData;
import com.betamedia.atom.core.api.crm.form.parsers.PersonalInformationParser;
import com.betamedia.atom.core.api.crm.form.parsers.TradingExperienceInfoParser;
import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WidgetsNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.testingtype.widgets.WidgetsEndToEndTest;
import org.testng.annotations.Test;

/**
 * Created by mbelyaev on 5/17/17.
 */
public class DataDrivenUserExperienceTest extends WidgetsEndToEndTest {

    @Test(dataProvider = GENERIC_PARALLEL_DATA_PROVIDER)
    public void dataDrivenInformationInputTest(QuestionnaireData data) {
        CustomerRO customer = CustomerRO.builder(WidgetsNamingStrategy.get()).build();
        pages().navigation().register();
        pages().registerPage().register(customer);
        pages().navigation().fnsTradingExperience();
        pages().fnsTradingExperience().submit(TradingExperienceInfoParser.parse(data));
        pages().navigation().fnsPersonalInformation();
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
