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

    public void assertControlsUsability(PersonalInformation info, String expectedAnswerColor, String expectedHoverStateColor, String expectedSelectionColor) {


        System.out.println(find(By.cssSelector("li[data-value='" + info.employmentStatus + "']")).getCssValue("color"));

        softAssert().assertEquals(find(By.cssSelector("option[value='" + info.employmentStatus + "']"))
                .getCssValue("border-bottom-color"), "rgb(153, 153, 153)");

        softAssert().assertEquals(scrollIntoView(waitUntilDisplayed(By.cssSelector("li[data-value='" + info.employmentStatus + "']")))
                .getCssValue("text-decoration-color"), "rgb(180, 180, 180)");


        softAssert().assertEquals(scrollIntoView(waitUntilDisplayed(By.cssSelector("li[data-value='" + info.employmentStatus + "']")))
                .getCssValue("color"), "rgb(180, 180, 180)");
        softAssert().assertEquals(find(By.cssSelector("option[value='" + info.employmentStatus + "']"))
                .getCssValue("border-bottom-color"), "rgb(180, 180, 180)");
        softAssert().assertEquals(scrollIntoView(waitUntilDisplayed(By.cssSelector("li[data-value='" + info.employmentStatus + "']")))
                .getCssValue("border-right-color:"), "rgb(180, 180, 180)");
        softAssert().assertEquals(scrollIntoView(waitUntilDisplayed(By.cssSelector("li[data-value='" + info.employmentStatus + "']")))
                .getCssValue("border-left-color"), "rgb(180, 180, 180)");
        softAssert().assertEquals(scrollIntoView(waitUntilDisplayed(By.cssSelector("li[data-value='" + info.employmentStatus + "']")))
                .getCssValue("border-top-color"), "rgb(180, 180, 180)");

        softAssert().assertEquals(scrollIntoView(waitUntilDisplayed(By.cssSelector("li[data-value='" + info.employmentStatus + "']")))
                .getCssValue("lighting-color"), "rgb(255, 255, 255)");


        softAssert().assertEquals(find(employmentStatus).getCssValue("border-bottom-color"),"rgba(0, 0, 0, 0)");
    }

    private void checkAnswerColor(PersonalInformation info, String expectedAnswerColor) {


    }

    private void submitOnWizard(String dataValue) {
        logger.info(String.format("filling Fns PersonalInformation wizard value: %s",dataValue));
        scrollIntoView(waitUntilDisplayed(By.cssSelector("li[data-value='" + dataValue + "']"))).click();
    }

}
