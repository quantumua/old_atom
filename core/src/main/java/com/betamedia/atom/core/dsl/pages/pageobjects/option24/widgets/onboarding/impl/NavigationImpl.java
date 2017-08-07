package com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.Navigation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by mbelyaev on 5/17/17.
 */
public class NavigationImpl extends AbstractPageObject implements Navigation {
    @StoredId
    private By loginButton;
    @StoredId
    private By fnsPersonalInformationButton;
    @StoredId
    private By fnsTradingExperienceButton;
    @StoredId
    private By accountDetailsButton;
    @StoredId
    private By accountAdditionalDetailsButton;
    @StoredId
    private By creditCardDepositButton;
    @StoredId
    private By registerButton;
    @StoredId
    private By setLeverageButton;

    public NavigationImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void login() {
        waitUntilDisplayed(loginButton).click();
    }

    @Override
    public void fnsPersonalInformation() {
        click(fnsPersonalInformationButton);
    }

    @Override
    public void fnsTradingExperience() {
        click(fnsTradingExperienceButton);
    }

    @Override
    public void accountDetails() {
        click(accountDetailsButton);
    }

    @Override
    public void accountAdditionalDetails() {
        click(accountAdditionalDetailsButton);
    }

    @Override
    public void creditCardDeposit() {
        click(creditCardDepositButton);
    }

    @Override
    public void register() {
        click(registerButton);
    }

    @Override
    public void setLeverage() {
        click(setLeverageButton);
    }
}
