package com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.impl;

import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.util.Objects.nonNull;

/**
 * @author mbelyaev
 * @since 8/2/17
 */
public class WebFnsTradingExperienceImpl extends AbstractFnsTradingExperience {
    public WebFnsTradingExperienceImpl(WebDriver webDriver) {
        super(webDriver);
    }

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
        waitUntilDisplayed(By.cssSelector("li[data-value='" + dataValue + "']")).click();
    }
}
