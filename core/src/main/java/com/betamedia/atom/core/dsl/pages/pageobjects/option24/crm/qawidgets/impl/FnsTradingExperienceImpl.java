package com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.FnsTradingExperience;
import com.betamedia.atom.core.api.crm.form.entities.TradingExperienceInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static java.util.Objects.nonNull;

/**
 * @author mbelyaev
 * @since 5/17/17
 */
public class FnsTradingExperienceImpl extends AbstractPageObject implements FnsTradingExperience {
    @StoredId
    private By sharesExperience;
    @StoredId
    private By binaryExperience;
    @StoredId
    private By averageYearlyBinaryVolume;
    @StoredId
    private By forExExperience;
    @StoredId
    private By averageYearlyForExVolume;
    @StoredId
    private By commonLeverage;
    @StoredId
    private By financialWorkExperience;
    @StoredId
    private By cfdBinaryKnowledge;
    @StoredId
    private By mainFactorKnowledge;
    @StoredId
    private By howToCloseKnowledge;
    @StoredId
    private By cfdLeverageKnowledge;
    @StoredId
    private By stopLossKnowledge;
    @StoredId
    private By requiredMarginKnowledge;
    @StoredId
    private By marginLevelDropKnowledge;
    @StoredId
    private By automaticStopKnowledge;
    @StoredId
    private By lossOn1to50Knowledge;
    @StoredId
    private By lossOn1to200Knowledge;
    @StoredId
    private By binaryInvestProfitKnowledge;
    @StoredId
    private By binaryInvestLossKnowledge;
    @StoredId
    private By binaryProbabilityKnowledge;
    @StoredId
    private By submit;
    @StoredId
    private By resultPlaceholder;

    public FnsTradingExperienceImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void submit(TradingExperienceInfo info) {
        inSelect(sharesExperience).selectByValue(info.sharesExperience);
        inSelect(binaryExperience).selectByValue(info.binaryExperience);
        if(nonNull(info.averageYearlyBinaryVolume)) inSelect(averageYearlyBinaryVolume).selectByValue(info.averageYearlyBinaryVolume);
        inSelect(forExExperience).selectByValue(info.forExExperience);
        if(nonNull(info.averageYearlyForExVolume)) inSelect(averageYearlyForExVolume).selectByValue(info.averageYearlyForExVolume);
        if(nonNull(info.commonLeverage)) inSelect(commonLeverage).selectByValue(info.commonLeverage);
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
        inSelect(binaryInvestProfitKnowledge).selectByValue(info.binaryInvestProfitKnowledge);
        inSelect(binaryInvestLossKnowledge).selectByValue(info.binaryInvestLossKnowledge);
        inSelect(binaryProbabilityKnowledge).selectByValue(info.binaryProbabilityKnowledge);
        scrollIntoView(find(submit)).click();
        waitUntil(() -> waitUntilDisplayed(resultPlaceholder).getText().contains("\"HasTradingExperienceAnswers\":true"));
    }

    @Override
    public void submitOnWizard(TradingExperienceInfo info) {
        submitOnWizard(info.sharesExperience);
        submitOnWizard(info.binaryExperience);
        if(nonNull(info.averageYearlyBinaryVolume))submitOnWizard(info.averageYearlyBinaryVolume);
        submitOnWizard(info.forExExperience);
        if(nonNull(info.averageYearlyForExVolume))submitOnWizard(info.averageYearlyForExVolume);
        if(nonNull(info.commonLeverage))submitOnWizard(info.commonLeverage);
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
        submitOnWizard(info.binaryInvestProfitKnowledge);
        submitOnWizard(info.binaryInvestLossKnowledge);
        submitOnWizard(info.binaryProbabilityKnowledge);
    }

    @Override
    public void submitOnWizard(String dataValue) {
        waitUntilDisplayed(By.cssSelector("li[data-value='"+dataValue+"']")).click();
    }

}
