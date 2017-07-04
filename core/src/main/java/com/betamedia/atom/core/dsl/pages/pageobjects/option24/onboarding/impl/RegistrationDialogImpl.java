package com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.impl;

import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.RegistrationDialog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

/**
 * Created by vsnigur on 7/3/17.
 */
public class RegistrationDialogImpl extends AbstractPageObject implements RegistrationDialog {

    @StoredId
    private By caption;
    @StoredId
    private By firstName;
    @StoredId
    private By lastName;
    @StoredId
    private By email;
    @StoredId
    private By phoneNumber;
    @StoredId
    private By selectedFlag;
    @StoredId
    private By selectCountry;
    @StoredId
    private By password;
    @StoredId
    private By retypePassword;
    @StoredId
    private By accountAgree;
    @StoredId
    private By registrationWidget;
    @StoredId
    private By submitButton;
    @StoredId
    private By logo;
    @StoredId
    private By languageMenu;
    @StoredId
    private By riskWarningFooter;
    @StoredId
    private By liveChat;
    @StoredId
    private By loginButton;
    @StoredId
    private By firstNameInputError;

    public RegistrationDialogImpl(WebDriver webDriver) {
        super(webDriver);
    }


    @Override
    public String dialogCaption() {
        return waitUntilDisplayed(caption).getText();
    }

    @Override
    public boolean exists() {
        return waitUntilDisplayed(caption,submitButton).isDisplayed();
    }

    @Override
    public boolean logoExists() {
        return waitUntilDisplayed(logo).isDisplayed();
    }

    @Override
    public boolean languageExists() {
        return waitUntilDisplayed(languageMenu).isDisplayed();
    }

    @Override
    public boolean liveChatExists() {
        return waitUntilDisplayed(liveChat).isDisplayed();
    }

    @Override
    public boolean riskMessageExists() {
        return waitUntilDisplayed(riskWarningFooter).isDisplayed();
    }

    @Override
    public boolean loginButtonExists() {
        return waitUntilDisplayed(loginButton).isDisplayed();
    }

    @Override
    public String firstNameGetMessage() {
        return waitUntilDisplayed(firstNameInputError).getText();
    }

    @Override
    public String getFirstName() {
        return find(firstName).getText();
    }

    @Override
    public boolean register(CustomerRO customerRO) {
        try {
            waitUntilDisplayed(firstName).sendKeys(customerRO.getFirstName());
            find(lastName).sendKeys(customerRO.getLastName());
            find(email).sendKeys(customerRO.getEmail());
            find(phoneNumber).sendKeys(customerRO.getPhone());
            find(password).sendKeys(customerRO.getPassword());
            find(retypePassword).sendKeys(customerRO.getPassword());
            executeScript("arguments[0].click()", find(accountAgree));
            find(selectedFlag).click();
            inSelect(selectCountry).selectByValue(customerRO.getCountryCode());
            find(submitButton).click();
            return true;
        } catch (Exception e) {
            Reporter.log("Exception happens during submit new user");
            return false;
        }
    }
}
