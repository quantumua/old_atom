package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.atom.core.dsl.pages.extensions.ScriptOperations;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.AbstractFnsPersonalInformation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static com.betamedia.atom.core.testingtype.base.AbstractTest.softAssert;
import static java.util.Objects.nonNull;

/**
 * @author mbelyaev
 * @since 8/2/17
 */
public class WebFnsPersonalInformationImpl extends AbstractFnsPersonalInformation implements ScriptOperations {

    private static final String CSS_COLOR = "color";
    private static final String CSS_BACKGROUND_COLOR = "background-color";
    private static final int MAX_CHARS_IN_OTHER_ANSWER = 101;

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

    @Override
    public void assertControlsUsability(PersonalInformation info, String expectedItemsBackgroundColor, String expectedAnswerColor, String expectedHoverAnswersColor, String expectedSelectionColor) {

        By employmentStatusItem = By.cssSelector("li[data-value='" + info.employmentStatus + "']");
        By answerOption = By.cssSelector("option[value='" + info.employmentStatus + "']");
        By industryItem = By.cssSelector("li[data-value='" + info.industry + "']");

        waitUntilDisplayed(employmentStatusItem);
        softAssert().assertEquals(find(answerOption)
                .getCssValue(CSS_COLOR), expectedItemsBackgroundColor,
                "assert background colors for answers");

        softAssert().assertEquals(find(employmentStatusItem)
                        .getCssValue(CSS_COLOR),
                expectedAnswerColor,"assert border line and font colors for answers");
        makeActions().moveToElement(find(employmentStatusItem))
                .build().perform();
        softAssert().assertEquals(find(employmentStatusItem)
                        .getCssValue(CSS_COLOR),
                expectedHoverAnswersColor, "assert border line and font colors for answers if hover happens");

        find(employmentStatusItem).click();
        waitUntilDisplayed(industryItem);
        softAssert().assertEquals(find(employmentStatusItem)
                        .getCssValue(CSS_BACKGROUND_COLOR),
                expectedSelectionColor, "assert background colors for answers after click answer");

    }

    @Override
    public void assertBoundaryFields(PersonalInformation info) {
        submitOnWizard(info.employmentStatus);
        submitOnWizard(info.industry);
        if (nonNull(info.industryOther)) {
            softAssert().assertTrue(find(purposeOfTradingBttn).isDisplayed());
            find(industryOther).sendKeys(info.industryOther);
            softAssert().assertEquals(find(industryOther).getText().length(), MAX_CHARS_IN_OTHER_ANSWER,
                    industryOther+"Contains more chars than maximum");
            executeScript("arguments[0].click()", find(industryOtherBttn));
        }
        submitOnWizard(info.educationLevel);
        submitOnWizard(info.educationField);
        if (nonNull(info.educationFieldOther)) {
            softAssert().assertTrue(find(purposeOfTradingBttn).isDisplayed());
            find(educationFieldOther).sendKeys(info.educationFieldOther);
            softAssert().assertEquals(find(educationFieldOther).getText().length(), MAX_CHARS_IN_OTHER_ANSWER,
                    educationFieldOther+"Contains more chars than maximum");
            executeScript("arguments[0].click()", find(educationFieldBttn));
        }
        submitOnWizard(info.isPoliticallyExposed);
        if (nonNull(info.politicalExposureComment)) {
            softAssert().assertTrue(find(purposeOfTradingBttn).isDisplayed());
            find(politicalExposureComment).sendKeys(info.politicalExposureComment);
            softAssert().assertEquals(find(politicalExposureComment).getText().length(), MAX_CHARS_IN_OTHER_ANSWER,
                    politicalExposureComment+"Contains more chars than maximum");
            executeScript("arguments[0].click()", find(politicalExposureBttn));
        }
        submitOnWizard(info.sourceOfFunds);
        if (nonNull(info.sourceOfFundsOther)) {
            softAssert().assertTrue(find(purposeOfTradingBttn).isDisplayed());
            find(sourceOfFundsOther).sendKeys(info.sourceOfFundsOther);
            softAssert().assertEquals(find(sourceOfFundsOther).getText().length(), MAX_CHARS_IN_OTHER_ANSWER,
                    sourceOfFundsOther+"Contains more chars than maximum");
            executeScript("arguments[0].click()", find(sourceOfFundsBttn));
        }
        submitOnWizard(info.annualIncome);
        submitOnWizard(info.netWealth);
        submitOnWizard(info.expectedDepositsPerYear);
        submitOnWizard(info.purposeOfTrading);
        if (nonNull(info.purposeOfTradingOther)) {
            softAssert().assertTrue(find(purposeOfTradingBttn).isDisplayed());
            find(purposeOfTradingOther).sendKeys(info.purposeOfTradingOther);
            softAssert().assertEquals(find(purposeOfTradingOther).getText().length(), MAX_CHARS_IN_OTHER_ANSWER,
                    purposeOfTradingOther+"Contains more chars than maximum");
            executeScript("arguments[0].click()", find(purposeOfTradingBttn));
        }
    }

    @Override
    public void assertCloseNotExist(PersonalInformation info) {
        softAssert().assertFalse(find(modalClose).isDisplayed());
        submitOnWizard(info.employmentStatus);
        softAssert().assertFalse(find(modalClose).isDisplayed());
        submitOnWizard(info.industry);
        if (nonNull(info.industryOther)) {
            find(industryOther).sendKeys(info.industryOther);
            executeScript("arguments[0].click()", find(industryOtherBttn));
        }
        softAssert().assertFalse(find(modalClose).isDisplayed());
        submitOnWizard(info.educationLevel);
        softAssert().assertFalse(find(modalClose).isDisplayed());
        submitOnWizard(info.educationField);
        if (nonNull(info.educationFieldOther)) {
            find(educationFieldOther).sendKeys(info.educationFieldOther);
            executeScript("arguments[0].click()", find(educationFieldBttn));
        }
        softAssert().assertFalse(find(modalClose).isDisplayed());
        submitOnWizard(info.isPoliticallyExposed);
        if (nonNull(info.politicalExposureComment)) {
            find(politicalExposureComment).sendKeys(info.politicalExposureComment);
            executeScript("arguments[0].click()", find(politicalExposureBttn));
        }
        softAssert().assertFalse(find(modalClose).isDisplayed());
        submitOnWizard(info.sourceOfFunds);
        if (nonNull(info.sourceOfFundsOther)) {
            find(sourceOfFundsOther).sendKeys(info.sourceOfFundsOther);
            executeScript("arguments[0].click()", find(sourceOfFundsBttn));
        }
        softAssert().assertFalse(find(modalClose).isDisplayed());
        submitOnWizard(info.annualIncome);
        softAssert().assertFalse(find(modalClose).isDisplayed());
        submitOnWizard(info.netWealth);
        softAssert().assertFalse(find(modalClose).isDisplayed());
        submitOnWizard(info.expectedDepositsPerYear);
        softAssert().assertFalse(find(modalClose).isDisplayed());
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
