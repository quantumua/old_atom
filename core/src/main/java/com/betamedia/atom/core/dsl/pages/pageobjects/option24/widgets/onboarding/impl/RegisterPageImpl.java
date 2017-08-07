package com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.impl;

import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.WidgetsNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.RegisterPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by vsnigur on 5/19/17.
 */
public class RegisterPageImpl extends AbstractPageObject implements RegisterPage {
    private static final Logger logger = LogManager.getLogger(RegisterPageImpl.class);

    @StoredId
    private By title;
    @StoredId
    private By firstName;
    @StoredId
    private By lastName;
    @StoredId
    private By email;
    @StoredId
    private By country;
    @StoredId
    private By telephonePrefix;
    @StoredId
    private By telephoneNumber;
    @StoredId
    private By birthDateDay;
    @StoredId
    private By birthDateMonth;
    @StoredId
    private By birthDateYear;
    @StoredId
    private By accountBaseCurrency;
    @StoredId
    private By password;
    @StoredId
    private By startTrading;
    @StoredId
    private By swProgressImg;
    @StoredId
    private By resultPlaceHolder;

    public RegisterPageImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void register(CustomerRO customer) {
        logger.info("Attempting to log in with customer " + customer);
        waitUntilDisplayed(title);
        inSelect(title).selectByValue(customer.getTitle());
        find(firstName).sendKeys(customer.getFirstName());
        find(lastName).sendKeys(customer.getLastName());
        find(email).sendKeys(customer.getEmail());
        inSelect(country).selectByValue(customer.getCountryCode());
        find(telephoneNumber).sendKeys(customer.getPhone());
        inSelect(birthDateDay).selectByValue(customer.getBirthdayDayOfMonth());
        inSelect(birthDateMonth).selectByValue(customer.getBirthdayMonth());
        inSelect(birthDateYear).selectByValue(customer.getBirthdayYear());
        inSelect(accountBaseCurrency).selectByValue(customer.getCurrency());
        find(password).sendKeys(customer.getPassword());
        find(startTrading).click();
        waitUntil(() -> waitUntilDisplayed(resultPlaceHolder).getText().contains("CustomerId"));
    }

    @Override
    public void register() {
        register(CustomerRO.builder(WidgetsNamingStrategy.get()).build());
    }
}