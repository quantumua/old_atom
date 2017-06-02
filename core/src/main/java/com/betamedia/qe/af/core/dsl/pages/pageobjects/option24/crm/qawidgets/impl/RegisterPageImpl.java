package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl;

import com.betamedia.qe.af.core.api.tp.entities.request.CustomerRO;
import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.RegisterPage;
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
        in(title).selectByValue(customer.getTitle());
        find(firstName).sendKeys(customer.getFirstName());
        find(lastName).sendKeys(customer.getLastName());
        find(email).sendKeys(customer.getEmail());
        in(country).selectByValue(customer.getCountryCode());
        find(telephoneNumber).sendKeys(customer.getPhone());
        in(birthDateDay).selectByValue(customer.getBirthdayDayOfMonth());
        in(birthDateMonth).selectByValue(customer.getBirthdayMonth());
        in(birthDateYear).selectByValue(customer.getBirthdayYear());
        in(accountBaseCurrency).selectByValue(customer.getCurrency());
        find(password).sendKeys(customer.getPassword());
        find(startTrading).click();
        waitUntilDisplayed(resultPlaceHolder);
    }

    @Override
    public void register() {
        register(CustomerRO.builder().build());
    }
}
