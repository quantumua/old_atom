package com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.FnsTradingExperience;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author mbelyaev
 * @since 5/17/17
 */
public abstract class AbstractFnsTradingExperience extends AbstractPageObject implements FnsTradingExperience {
    @StoredId
    protected By sharesExperience;
    @StoredId
    protected By binaryExperience;
    @StoredId
    protected By averageYearlyBinaryVolume;
    @StoredId
    protected By forExExperience;
    @StoredId
    protected By averageYearlyForExVolume;
    @StoredId
    protected By commonLeverage;
    @StoredId
    protected By financialWorkExperience;
    @StoredId
    protected By cfdBinaryKnowledge;
    @StoredId
    protected By mainFactorKnowledge;
    @StoredId
    protected By howToCloseKnowledge;
    @StoredId
    protected By cfdLeverageKnowledge;
    @StoredId
    protected By stopLossKnowledge;
    @StoredId
    protected By requiredMarginKnowledge;
    @StoredId
    protected By marginLevelDropKnowledge;
    @StoredId
    protected By automaticStopKnowledge;
    @StoredId
    protected By lossOn1to50Knowledge;
    @StoredId
    protected By lossOn1to200Knowledge;
    @StoredId
    protected By submit;
    @StoredId
    protected By resultPlaceholder;
    @StoredId
    protected By buttonWizardDeclaration;

    protected AbstractFnsTradingExperience(WebDriver webDriver) {
        super(webDriver);
    }
}
