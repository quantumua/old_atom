package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.atom.core.dsl.pages.extensions.ScriptOperations;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.AbstractFnsTradingExperience;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.util.Objects.nonNull;

/**
 * @author mbelyaev
 * @since 8/2/17
 */
public class WebFnsTradingExperienceImpl extends AbstractFnsTradingExperience implements ScriptOperations {
    public WebFnsTradingExperienceImpl(WebDriver webDriver) {
        super(webDriver);
    }

    private static final Logger logger = LogManager.getLogger(WebFnsTradingExperienceImpl.class);

    @Override
    public void submit(TradingExperienceInfo info) {
        submitOnWizard(info.sharesExperience);
        submitOnWizard(info.binaryExperience);
        if (nonNull(info.averageYearlyBinaryVolume)) submitOnWizard(info.averageYearlyBinaryVolume);
        submitOnWizard(info.forExExperience);
        if (nonNull(info.averageYearlyForExVolume)) submitOnWizard(info.averageYearlyForExVolume);
        if (nonNull(info.commonLeverage)) submitOnWizard(info.commonLeverage);
        submitOnWizard(info.financialWorkExperience);
        submitOnWizard(info.cfdBinaryKnowledge);
        submitOnWizard(info.mainFactorKnowledge);
        submitOnWizard(info.howToCloseKnowledge);
        submitOnWizard(info.cfdLeverageKnowledge);
        submitOnWizard(info.stopLossKnowledge);
        submitOnWizard(info.requiredMarginKnowledge);
        submitOnWizard(info.marginLevelDropKnowledge);
        submitOnWizard(info.automaticStopKnowledge);
        submitOnWizard(info.lossOn1to50Knowledge);
        submitOnWizard(info.lossOn1to200Knowledge);
        waitUntilDisplayed(buttonWizardDeclaration).click();
    }

    private void submitOnWizard(String dataValue) {
        logger.info(String.format("filling Fns Trading Experience wizard value: %s",dataValue));
        waitUntilDisplayed(By.cssSelector("li[data-value='" + dataValue + "']")).click();
    }
}
