package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl;

import com.betamedia.qe.af.core.api.crm.form.builders.RegisterInfoBuilder;
import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.Register;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by vsnigur on 5/19/17.
 */
public class RegisterImpl extends AbstractPageObject implements Register {

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

    public RegisterImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void update(RegisterInfoBuilder.RegisterBuilderInfo info) {
        waitUntilDisplayed(title);
        in(title).selectByVisibleText(info.title);
        find(firstName).sendKeys(info.firstName);
        find(lastName).sendKeys(info.lastName);
        find(email).sendKeys(info.email);
        in(country).selectByVisibleText(info.country);
        if (info.telephonePrefix!="") {
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
}
