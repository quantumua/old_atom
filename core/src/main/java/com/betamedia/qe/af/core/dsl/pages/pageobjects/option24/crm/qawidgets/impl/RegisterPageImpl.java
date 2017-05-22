package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl;

import com.betamedia.qe.af.core.api.crm.form.builders.RegisterBuilder;
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
    public void update(RegisterBuilder.Register info) {
        waitUntilDisplayed(title);
        in(title).selectByVisibleText(info.title);
        find(firstName).sendKeys(info.firstName);
        find(lastName).sendKeys(info.lastName);
        find(email).sendKeys(info.email);
        in(country).selectByVisibleText(info.country);
        if (info.telephonePrefix != "") {
            find(telephonePrefix).clear();
            find(telephonePrefix).sendKeys(info.telephonePrefix);
        }
        find(telephoneNumber).sendKeys(info.telephoneNumber);
        in(birthDateDay).selectByValue(info.birthDateDay);
        in(birthDateMonth).selectByValue(info.birthDateMonth);
        in(birthDateYear).selectByValue(info.birthDateYear);
        in(accountBaseCurrency).selectByValue(info.accountBaseCurrency);
        find(password).sendKeys(info.password);
        find(startTrading).click();
        waitUntilDisplayed(resultPlaceHolder);
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
}
