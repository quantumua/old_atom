package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl;

import com.betamedia.qe.af.core.api.crm.form.builders.AccountDetailsBuilder;
import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.AccountDetailsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by vsnigur on 5/18/17.
 */
public class AccountDetailsPageImpl extends AbstractPageObject implements AccountDetailsPage {

    @StoredId
    private By title;
    @StoredId
    private By street;
    @StoredId
    private By streetNumber;
    @StoredId
    private By city;
    @StoredId
    //autoselected after choosing the country
    private By countryPhonePrefix2;
    @StoredId
    private By phone2;
    @StoredId
    private By birthdayDay;
    @StoredId
    private By birthdayMonth;
    @StoredId
    private By birthdayYear;
    @StoredId
    private By update;


    public AccountDetailsPageImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void update(AccountDetailsBuilder.AccountDetails info) {
        waitUntilDisplayed(title);
        in(title).selectByVisibleText(info.title);
        find(street).sendKeys(info.street);
        find(streetNumber).sendKeys(info.streetNumber);
        find(city).sendKeys(info.city);
        find(phone2).sendKeys(info.phone2);
        in(birthdayDay).selectByValue(info.birthdayDay);
        in(birthdayMonth).selectByValue(info.birthdayMonth);
        in(birthdayYear).selectByValue(info.birthdayYear);
        find(update).click();
    }
}
