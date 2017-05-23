package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl;

import com.betamedia.qe.af.core.api.crm.form.builders.TradingExperienceInfoBuilder.TradingExperienceInfo;
import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.FnsTradingExperience;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by mbelyaev on 5/17/17.
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

    //TODO extract method for send-keys-if-not-null scenario
    @Override
    public void submit(TradingExperienceInfo info) {
        in(sharesExperience).selectByValue(info.sharesExperience);
        in(binaryExperience).selectByValue(info.binaryExperience);
        if(notNull(info.averageYearlyBinaryVolume)) in(averageYearlyBinaryVolume).selectByValue(info.averageYearlyBinaryVolume);
        in(forExExperience).selectByValue(info.forExExperience);
        if(notNull(info.averageYearlyForExVolume)) in(averageYearlyForExVolume).selectByValue(info.averageYearlyForExVolume);
        if(notNull(info.commonLeverage)) in(commonLeverage).selectByValue(info.commonLeverage);
        in(financialWorkExperience).selectByValue(info.financialWorkExperience);
        in(cfdBinaryKnowledge).selectByValue(info.cfdBinaryKnowledge);
        in(mainFactorKnowledge).selectByValue(info.mainFactorKnowledge);
        in(howToCloseKnowledge).selectByValue(info.howToCloseKnowledge);
        in(cfdLeverageKnowledge).selectByValue(info.cfdLeverageKnowledge);
        in(stopLossKnowledge).selectByValue(info.stopLossKnowledge);
        in(requiredMarginKnowledge).selectByValue(info.requiredMarginKnowledge);
        in(marginLevelDropKnowledge).selectByValue(info.marginLevelDropKnowledge);
        in(automaticStopKnowledge).selectByValue(info.automaticStopKnowledge);
        in(lossOn1to50Knowledge).selectByValue(info.lossOn1to50Knowledge);
        in(lossOn1to200Knowledge).selectByValue(info.lossOn1to200Knowledge);
        in(binaryInvestProfitKnowledge).selectByValue(info.binaryInvestProfitKnowledge);
        in(binaryInvestLossKnowledge).selectByValue(info.binaryInvestLossKnowledge);
        in(binaryProbabilityKnowledge).selectByValue(info.binaryProbabilityKnowledge);
        click(submit);
        waitUntilDisplayed(resultPlaceholder);
    }


    private static boolean notNull(Object o) {
        return o != null;
    }
}
