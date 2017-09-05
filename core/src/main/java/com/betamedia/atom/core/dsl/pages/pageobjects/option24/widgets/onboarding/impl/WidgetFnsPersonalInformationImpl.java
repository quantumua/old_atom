package com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.impl;

import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.atom.core.dsl.pages.extensions.ScriptOperations;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.AbstractFnsPersonalInformation;
import org.openqa.selenium.WebDriver;

import static java.util.Objects.nonNull;

/**
 * @author mbelyaev
 * @since 8/2/17
 */
public class WidgetFnsPersonalInformationImpl extends AbstractFnsPersonalInformation implements ScriptOperations {

    public WidgetFnsPersonalInformationImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void submit(PersonalInformation info) {
        inSelect(employmentStatus).selectByValue(info.employmentStatus);
        inSelect(industry).selectByValue(info.industry);
        if(nonNull(info.industryOther)) {find(industryOther).sendKeys(info.industryOther);}
        inSelect(educationLevel).selectByValue(info.educationLevel);
        inSelect(educationField).selectByValue(info.educationField);
        if(nonNull(info.educationFieldOther)) find(educationFieldOther).sendKeys(info.educationFieldOther);
        inSelect(isPoliticallyExposed).selectByValue(info.isPoliticallyExposed);
        if(nonNull(info.politicalExposureComment)) find(politicalExposureComment).sendKeys(info.politicalExposureComment);
        inSelect(sourceOfFunds).selectByValue(info.sourceOfFunds);
        if(nonNull(info.sourceOfFundsOther)) find(sourceOfFundsOther).sendKeys(info.sourceOfFundsOther);
        inSelect(annualIncome).selectByValue(info.annualIncome);
        inSelect(netWealth).selectByValue(info.netWealth);
        inSelect(expectedDepositsPerYear).selectByValue(info.expectedDepositsPerYear);
        inSelect(purposeOfTrading).selectByValue(info.purposeOfTrading);
        if(nonNull(info.purposeOfTradingOther)) find(purposeOfTradingOther).sendKeys(info.purposeOfTradingOther);
        scrollIntoView(find(submit)).click();
        waitUntil(() -> waitUntilDisplayed(resultPlaceholder).getText().contains("\"HasPersonalInformationAnswers\":true"));
    }

    @Override
    public void assertControlsUsability(PersonalInformation info, String expectedItemsBackgroundColor, String expectedAnswerColor, String expectedHoverAnswersColor, String expectedSelectionColor) {

    }


}
