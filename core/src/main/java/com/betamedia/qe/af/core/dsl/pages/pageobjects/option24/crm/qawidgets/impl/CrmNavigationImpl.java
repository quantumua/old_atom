package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.CrmNavigation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by mbelyaev on 5/17/17.
 */
public class CrmNavigationImpl extends AbstractPageObject implements CrmNavigation {
    @StoredId
    private By loginButton;
    @StoredId
    private By fnsPersonalInformationButton;
    @StoredId
    private By fnsTradingExperienceButton;

    public CrmNavigationImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void login() {
        click(loginButton);
    }

    @Override
    public void fnsPersonalInformation() {
        click(fnsPersonalInformationButton);
    }

    @Override
    public void fnsTradingExperience() {
        click(fnsTradingExperienceButton);
    }
}
