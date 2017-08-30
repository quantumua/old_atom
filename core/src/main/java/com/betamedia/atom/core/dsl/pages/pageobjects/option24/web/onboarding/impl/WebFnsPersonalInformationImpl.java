package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.atom.core.dsl.pages.extensions.ScriptOperations;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.AbstractFnsPersonalInformation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static java.util.Objects.nonNull;

/**
 * @author mbelyaev
 * @since 8/2/17
 */
public class WebFnsPersonalInformationImpl extends AbstractFnsPersonalInformation implements ScriptOperations {

    public WebFnsPersonalInformationImpl(WebDriver webDriver) {
        super(webDriver);
    }

    private static final Logger logger = LogManager.getLogger(WebFnsPersonalInformationImpl.class);

    @Override
    public void submit(PersonalInformation info) {
        submitOnWizard(info.employmentStatus);
        submitOnWizard(info.industry);
        if (nonNull(info.industryOther)) {
            find(industryOther).sendKeys(info.industryOther);
            executeScript("arguments[0].click()", find(industryOtherBttn));
        }
        submitOnWizard(info.educationLevel);
        submitOnWizard(info.educationField);
        if (nonNull(info.educationFieldOther)) {
            find(educationFieldOther).sendKeys(info.educationFieldOther);
            executeScript("arguments[0].click()", find(educationFieldBttn));
        }
        submitOnWizard(info.isPoliticallyExposed);
        if (nonNull(info.politicalExposureComment)) {
            find(politicalExposureComment).sendKeys(info.politicalExposureComment);
            executeScript("arguments[0].click()", find(politicalExposureBttn));
        }
        submitOnWizard(info.sourceOfFunds);
        if (nonNull(info.sourceOfFundsOther)) {
            find(sourceOfFundsOther).sendKeys(info.sourceOfFundsOther);
            executeScript("arguments[0].click()", find(sourceOfFundsBttn));
        }
        submitOnWizard(info.annualIncome);
        submitOnWizard(info.netWealth);
        submitOnWizard(info.expectedDepositsPerYear);
        submitOnWizard(info.purposeOfTrading);
        if (nonNull(info.purposeOfTradingOther)) {
            find(purposeOfTradingOther).sendKeys(info.purposeOfTradingOther);
            executeScript("arguments[0].click()", find(purposeOfTradingBttn));
        }
    }

    private void submitOnWizard(String dataValue) {
        logger.info(String.format("filling Fns PersonalInformation wizard value: %s",dataValue));
        scrollIntoView(waitUntilDisplayed(By.cssSelector("li[data-value='" + dataValue + "']"))).click();
    }
}
