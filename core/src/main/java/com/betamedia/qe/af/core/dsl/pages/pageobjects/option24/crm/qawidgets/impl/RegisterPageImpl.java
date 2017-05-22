package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl;

import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder;
import com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder.CustomerRO;
import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.RegisterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;

/**
 * Created by vsnigur on 5/19/17.
 */
public class RegisterPageImpl extends AbstractPageObject implements RegisterPage {

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
        waitUntilDisplayed(title);
        in(title).selectByValue(customer.getTitle());
        find(firstName).sendKeys(customer.getFirstName());
        find(lastName).sendKeys(customer.getLastName());
        find(email).sendKeys(customer.getEmail());
        in(country).selectByValue(customer.getCountryCode());
        find(telephoneNumber).sendKeys(customer.getPhone());
        LocalDate dateOfBirth = LocalDate.parse(customer.getBirthOfDate());
        in(birthDateDay).selectByValue(Integer.toString(dateOfBirth.getDayOfMonth()));
        in(birthDateMonth).selectByValue(Integer.toString(dateOfBirth.getMonthValue()));
        in(birthDateYear).selectByValue(Integer.toString(dateOfBirth.getYear()));
        in(accountBaseCurrency).selectByValue(customer.getCurrency());
        find(password).sendKeys(customer.getPassword());
        find(startTrading).click();
        waitUntilDisplayed(resultPlaceHolder);
    }

    @Override
    public void register() {
        register(new CustomerBuilder().createCustomerRO());
    }
}
