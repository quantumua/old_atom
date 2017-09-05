package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.atom.core.dsl.pages.extensions.ScriptOperations;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.AbstractFnsTradingExperience;
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
