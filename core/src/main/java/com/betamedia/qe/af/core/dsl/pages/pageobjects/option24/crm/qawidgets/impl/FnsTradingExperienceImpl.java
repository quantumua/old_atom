package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl;

import com.betamedia.qe.af.core.api.crm.form.builders.TradingExperienceInfoBuilder.TradingExperienceInfo;
import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.FnsTradingExperience;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

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
    private By submit;

    public FnsTradingExperienceImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void submit(TradingExperienceInfo info) {
        in(sharesExperience).selectByValue(info.sharesExperience);
        in(binaryExperience).selectByValue(info.binaryExperience);
        in(averageYearlyBinaryVolume).selectByValue(info.averageYearlyBinaryVolume);
        in(forExExperience).selectByValue(info.forExExperience);
        in(averageYearlyForExVolume).selectByValue(info.averageYearlyForExVolume);
        in(commonLeverage).selectByValue(info.commonLeverage);
        in(financialWorkExperience).selectByValue(info.financialWorkExperience);
        in(cfdBinaryKnowledge).selectByValue(info.cfdBinaryKnowledge);
        click(submit);
    }

    private Select in(By element) {
        return in(waitUntilDisplayed(element));
    }


    private Select in(WebElement element) {
        return new Select(element);
    }

}
