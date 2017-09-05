package com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.impl;

import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import com.betamedia.atom.core.dsl.pages.extensions.ScriptOperations;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.AbstractFnsTradingExperience;
import org.openqa.selenium.WebDriver;
import static java.util.Objects.nonNull;

/**
 * @author mbelyaev
 * @since 8/2/17
 */
public class WidgetFnsTradingExperienceImpl extends AbstractFnsTradingExperience implements ScriptOperations {
    public WidgetFnsTradingExperienceImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void submit(TradingExperienceInfo info) {
        inSelect(sharesExperience).selectByValue(info.sharesExperience);
        inSelect(binaryExperience).selectByValue(info.binaryExperience);
        if (nonNull(info.averageYearlyBinaryVolume))
            inSelect(averageYearlyBinaryVolume).selectByValue(info.averageYearlyBinaryVolume);
        inSelect(forExExperience).selectByValue(info.forExExperience);
        if (nonNull(info.averageYearlyForExVolume))
            inSelect(averageYearlyForExVolume).selectByValue(info.averageYearlyForExVolume);
        if (nonNull(info.commonLeverage)) inSelect(commonLeverage).selectByValue(info.commonLeverage);
        inSelect(financialWorkExperience).selectByValue(info.financialWorkExperience);
        inSelect(cfdBinaryKnowledge).selectByValue(info.cfdBinaryKnowledge);
        inSelect(mainFactorKnowledge).selectByValue(info.mainFactorKnowledge);
        inSelect(howToCloseKnowledge).selectByValue(info.howToCloseKnowledge);
        inSelect(cfdLeverageKnowledge).selectByValue(info.cfdLeverageKnowledge);
        inSelect(stopLossKnowledge).selectByValue(info.stopLossKnowledge);
        inSelect(requiredMarginKnowledge).selectByValue(info.requiredMarginKnowledge);
        inSelect(marginLevelDropKnowledge).selectByValue(info.marginLevelDropKnowledge);
        inSelect(automaticStopKnowledge).selectByValue(info.automaticStopKnowledge);
        inSelect(lossOn1to50Knowledge).selectByValue(info.lossOn1to50Knowledge);
        inSelect(lossOn1to200Knowledge).selectByValue(info.lossOn1to200Knowledge);
        scrollIntoView(find(submit)).click();
        waitUntil(() -> waitUntilDisplayed(resultPlaceholder).getText().contains("\"HasTradingExperienceAnswers\":true"));
    }

    @Override
    public void checkDependsQuestions(TradingExperienceInfo info) {

    }


}
