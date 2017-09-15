package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.atom.core.dsl.pages.extensions.ScriptOperations;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.AbstractFnsTradingExperience;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static com.betamedia.atom.core.testingtype.base.AbstractTest.softAssert;

/**
 * @author mbelyaev
 * @since 8/2/17
 */
public class WebFnsTradingExperienceImpl extends AbstractFnsTradingExperience implements ScriptOperations {
    private static final String ATTRIBUTE_CLASS = "class";
    private static final String COLLAPSED = "q-collapsed";

    public WebFnsTradingExperienceImpl(WebDriver webDriver) {
        super(webDriver);
    }

    private static final Logger logger = LogManager.getLogger(WebFnsTradingExperienceImpl.class);

    @Override
    public void submit(TradingExperienceInfo info) {
        submitOnWizard(info.instrumentsTradedBefore);
        submitOnWizard(info.frequencyPastTransactions);
        submitOnWizard(info.volumePastTransaction);
        submitOnWizard(info.commonLevelPastTransaction);
        submitOnWizard(info.financialWorkExperience);
        submitOnWizard(info.cfdBinaryKnowledge);
        submitOnWizard(info.mainFactorKnowledge);
        submitOnWizard(info.howToCloseKnowledge);
        submitOnWizard(info.requiredMarginKnowledge);
        submitOnWizard(info.marginLevelDropKnowledge);
        submitOnWizard(info.lossOn1to50Knowledge);
        submitOnWizard(info.lossOn1to200Knowledge);
        waitUntilDisplayed(buttonWizardDeclaration).click();
    }

    @Override
    public void checkDependsQuestions(TradingExperienceInfo info) {

        By volumeTransaction = By.cssSelector("li[data-value='" + info.volumePastTransaction + "']");
        By commonTransaction = By.cssSelector("li[data-value='" + info.commonLevelPastTransaction + "']");
        By lossOn200 = By.cssSelector("li[data-value='" + info.lossOn1to200Knowledge + "']");
        submitOnWizard(info.instrumentsTradedBefore);

        isExpanded(divQFinancialProductsFrequency);
        isCollapsed(divQFinancialProductsVolume);
        isCollapsed(divQFinancialProductsCommonLeverage);
        submitOnWizard(info.frequencyPastTransactions);
        waitUntilDisplayed(volumeTransaction);
        isCollapsed(divQFinancialProductsFrequency);
        isExpanded(divQFinancialProductsVolume);
        isCollapsed(divQFinancialProductsCommonLeverage);
        submitOnWizard(info.volumePastTransaction);
        waitUntilDisplayed(commonTransaction);
        isCollapsed(divQFinancialProductsFrequency);
        isCollapsed(divQFinancialProductsVolume);
        isExpanded(divQFinancialProductsCommonLeverage);
        submitOnWizard(info.commonLevelPastTransaction);

        submitOnWizard(info.financialWorkExperience);
        submitOnWizard(info.cfdBinaryKnowledge);
        submitOnWizard(info.mainFactorKnowledge);
        submitOnWizard(info.howToCloseKnowledge);
        submitOnWizard(info.requiredMarginKnowledge);
        submitOnWizard(info.marginLevelDropKnowledge);
        isExpanded(divQKnowledgePositionLoss1to50);
        isCollapsed(divQKnowledgePositionLoss1to200);
        submitOnWizard(info.lossOn1to50Knowledge);
        waitUntilDisplayed(lossOn200);
        isCollapsed(divQKnowledgePositionLoss1to50);
        isExpanded(divQKnowledgePositionLoss1to200);
        submitOnWizard(info.lossOn1to200Knowledge);

        waitUntilDisplayed(buttonWizardDeclaration).click();
    }

    @Override
    public void assertBoundaryFields(TradingExperienceInfo info) {
        submitOnWizard(info.instrumentsTradedBefore);
        submitOnWizard(info.frequencyPastTransactions);
        submitOnWizard(info.volumePastTransaction);
        submitOnWizard(info.commonLevelPastTransaction);
        submitOnWizard(info.financialWorkExperience);
        submitOnWizard(info.cfdBinaryKnowledge);
        submitOnWizard(info.mainFactorKnowledge);
        submitOnWizard(info.howToCloseKnowledge);
        submitOnWizard(info.requiredMarginKnowledge);
        submitOnWizard(info.marginLevelDropKnowledge);
        submitOnWizard(info.lossOn1to50Knowledge);
        submitOnWizard(info.lossOn1to200Knowledge);
        waitUntilDisplayed(buttonWizardDeclaration).click();
    }

    @Override
    public void assertCloseNotExist(TradingExperienceInfo info) {
        softAssert().assertFalse(find(modalClose).isDisplayed());
        submitOnWizard(info.instrumentsTradedBefore);
        softAssert().assertFalse(find(modalClose).isDisplayed());
        submitOnWizard(info.frequencyPastTransactions);
        softAssert().assertFalse(find(modalClose).isDisplayed());
        submitOnWizard(info.volumePastTransaction);
        softAssert().assertFalse(find(modalClose).isDisplayed());
        submitOnWizard(info.commonLevelPastTransaction);
        softAssert().assertFalse(find(modalClose).isDisplayed());
        submitOnWizard(info.financialWorkExperience);
        softAssert().assertFalse(find(modalClose).isDisplayed());
        submitOnWizard(info.cfdBinaryKnowledge);
        softAssert().assertFalse(find(modalClose).isDisplayed());
        submitOnWizard(info.mainFactorKnowledge);
        softAssert().assertFalse(find(modalClose).isDisplayed());
        submitOnWizard(info.howToCloseKnowledge);
        softAssert().assertFalse(find(modalClose).isDisplayed());
        submitOnWizard(info.requiredMarginKnowledge);
        softAssert().assertFalse(find(modalClose).isDisplayed());
        submitOnWizard(info.marginLevelDropKnowledge);
        softAssert().assertFalse(find(modalClose).isDisplayed());
        submitOnWizard(info.lossOn1to50Knowledge);
        softAssert().assertFalse(find(modalClose).isDisplayed());
        submitOnWizard(info.lossOn1to200Knowledge);
        waitUntilDisplayed(buttonWizardDeclaration).click();
    }

    @Override
    public void assertProgressBar(TradingExperienceInfo tradingExperienceInfo, String... expectedProgress) {
        softAssert().assertEquals(find(progressValue).getText(), expectedProgress[0]);
        submitOnWizard(tradingExperienceInfo.instrumentsTradedBefore);
        softAssert().assertEquals(find(progressValue).getText(),expectedProgress[1]);
        submitOnWizard(tradingExperienceInfo.frequencyPastTransactions);
        softAssert().assertEquals(find(progressValue).getText(),expectedProgress[2]);
        submitOnWizard(tradingExperienceInfo.volumePastTransaction);
        softAssert().assertEquals(find(progressValue).getText(),expectedProgress[3]);
        submitOnWizard(tradingExperienceInfo.commonLevelPastTransaction);
        softAssert().assertEquals(find(progressValue).getText(),expectedProgress[4]);
        submitOnWizard(tradingExperienceInfo.financialWorkExperience);
        softAssert().assertEquals(find(progressValue).getText(),expectedProgress[5]);
        submitOnWizard(tradingExperienceInfo.cfdBinaryKnowledge);
        softAssert().assertEquals(find(progressValue).getText(),expectedProgress[6]);
        submitOnWizard(tradingExperienceInfo.mainFactorKnowledge);
        softAssert().assertEquals(find(progressValue).getText(),expectedProgress[7]);
        submitOnWizard(tradingExperienceInfo.howToCloseKnowledge);
        softAssert().assertEquals(find(progressValue).getText(),expectedProgress[8]);
        submitOnWizard(tradingExperienceInfo.requiredMarginKnowledge);
        softAssert().assertEquals(find(progressValue).getText(),expectedProgress[9]);
        submitOnWizard(tradingExperienceInfo.marginLevelDropKnowledge);
        softAssert().assertEquals(find(progressValue).getText(),expectedProgress[10]);
        submitOnWizard(tradingExperienceInfo.lossOn1to50Knowledge);
        softAssert().assertEquals(find(progressValue).getText(),expectedProgress[11]);
        submitOnWizard(tradingExperienceInfo.lossOn1to200Knowledge);
        waitUntilDisplayed(buttonWizardDeclaration).click();
    }

    @Override
    public void assertNavigationPossibilityBetweenSlides(TradingExperienceInfo tradingExperienceInfo) {

        softAssert().assertTrue(waitUntilDisplayed(backwardButton).isDisplayed());
        softAssert().assertTrue(find(forwardButton).isDisplayed());
        softAssert().assertFalse(find(backwardButton).isEnabled());
        softAssert().assertFalse(find(forwardButton).isEnabled());
        submitOnWizard(tradingExperienceInfo.instrumentsTradedBefore);

        softAssert().assertTrue(waitUntilDisplayed(backwardButton).isDisplayed());
        softAssert().assertTrue(find(forwardButton).isDisplayed());
        softAssert().assertTrue(find(backwardButton).isEnabled());
        softAssert().assertFalse(find(forwardButton).isEnabled());
        submitOnWizard(tradingExperienceInfo.frequencyPastTransactions);

        softAssert().assertTrue(waitUntilDisplayed(backwardButton).isDisplayed());
        softAssert().assertTrue(find(forwardButton).isDisplayed());
        softAssert().assertTrue(find(backwardButton).isEnabled());
        softAssert().assertFalse(find(forwardButton).isEnabled());
        submitOnWizard(tradingExperienceInfo.volumePastTransaction);
        find(backwardButton).click();
        softAssert().assertTrue(waitUntilDisplayed(backwardButton).isDisplayed());
        softAssert().assertTrue(find(forwardButton).isDisplayed());
        softAssert().assertTrue(find(backwardButton).isEnabled());
        softAssert().assertTrue(find(forwardButton).isEnabled());
        find(forwardButton).click();
        submitOnWizard(tradingExperienceInfo.commonLevelPastTransaction);

        softAssert().assertTrue(waitUntilDisplayed(backwardButton).isDisplayed());
        softAssert().assertTrue(find(forwardButton).isDisplayed());
        softAssert().assertTrue(find(backwardButton).isEnabled());
        softAssert().assertFalse(find(forwardButton).isEnabled());
        submitOnWizard(tradingExperienceInfo.financialWorkExperience);

        softAssert().assertTrue(waitUntilDisplayed(backwardButton).isDisplayed());
        softAssert().assertTrue(find(forwardButton).isDisplayed());
        softAssert().assertTrue(find(backwardButton).isEnabled());
        softAssert().assertFalse(find(forwardButton).isEnabled());
        submitOnWizard(tradingExperienceInfo.cfdBinaryKnowledge);

        softAssert().assertTrue(waitUntilDisplayed(backwardButton).isDisplayed());
        softAssert().assertTrue(find(forwardButton).isDisplayed());
        softAssert().assertTrue(find(backwardButton).isEnabled());
        softAssert().assertFalse(find(forwardButton).isEnabled());
        submitOnWizard(tradingExperienceInfo.mainFactorKnowledge);

        softAssert().assertTrue(waitUntilDisplayed(backwardButton).isDisplayed());
        softAssert().assertTrue(find(forwardButton).isDisplayed());
        softAssert().assertTrue(find(backwardButton).isEnabled());
        softAssert().assertFalse(find(forwardButton).isEnabled());
        submitOnWizard(tradingExperienceInfo.howToCloseKnowledge);

        softAssert().assertTrue(waitUntilDisplayed(backwardButton).isDisplayed());
        softAssert().assertTrue(find(forwardButton).isDisplayed());
        softAssert().assertTrue(find(backwardButton).isEnabled());
        softAssert().assertFalse(find(forwardButton).isEnabled());
        submitOnWizard(tradingExperienceInfo.requiredMarginKnowledge);

        softAssert().assertTrue(waitUntilDisplayed(backwardButton).isDisplayed());
        softAssert().assertTrue(find(forwardButton).isDisplayed());
        softAssert().assertTrue(find(backwardButton).isEnabled());
        softAssert().assertFalse(find(forwardButton).isEnabled());
        submitOnWizard(tradingExperienceInfo.marginLevelDropKnowledge);

        softAssert().assertTrue(waitUntilDisplayed(backwardButton).isDisplayed());
        softAssert().assertTrue(find(forwardButton).isDisplayed());
        softAssert().assertTrue(find(backwardButton).isEnabled());
        softAssert().assertFalse(find(forwardButton).isEnabled());
        submitOnWizard(tradingExperienceInfo.lossOn1to50Knowledge);

        softAssert().assertTrue(waitUntilDisplayed(backwardButton).isDisplayed());
        softAssert().assertTrue(find(forwardButton).isDisplayed());
        softAssert().assertTrue(find(backwardButton).isEnabled());
        softAssert().assertFalse(find(forwardButton).isEnabled());
        submitOnWizard(tradingExperienceInfo.lossOn1to200Knowledge);

        waitUntilDisplayed(buttonWizardDeclaration).click();
    }

    private void isCollapsed(By element) {
        softAssert().assertTrue(find(element).getAttribute(ATTRIBUTE_CLASS).contains(COLLAPSED),
                element.toString()+" is not collapsed!");
    }
    private void isExpanded(By element) {
        softAssert().assertFalse(find(element).getAttribute(ATTRIBUTE_CLASS).contains(COLLAPSED),
                element.toString()+" is not expanded!");
    }

    private void submitOnWizard(String dataValue) {
        logger.info(String.format("filling Fns Trading Experience wizard value: %s",dataValue));
        waitUntilDisplayed(By.cssSelector("li[data-value='" + dataValue + "']")).click();
    }
}
