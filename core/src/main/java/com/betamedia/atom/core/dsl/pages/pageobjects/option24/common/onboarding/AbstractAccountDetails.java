package com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by lartemyev on 8/7/17.
 */
public abstract class AbstractAccountDetails extends AbstractPageObject implements AccountDetailsPage {

    @StoredId
    protected By title;
    @StoredId
    protected By street;
    @StoredId
    protected By streetNumber;
    @StoredId
    protected By city;
    @StoredId
    //autoselected after choosing the country
    protected By countryPhonePrefix2;
    @StoredId
    protected By phone2;
    @StoredId
    protected By birthdayDay;
    @StoredId
    protected By birthdayMonth;
    @StoredId
    protected By birthdayYear;
    @StoredId
    protected By email;
    @StoredId
    protected By update;

    protected AbstractAccountDetails(WebDriver webDriver) {
        super(webDriver);
    }

    private final String ATTRIBUTE_VALUE = "value";

    @Override
    public String getEmail() {
        return waitUntilDisplayed(email).getAttribute(ATTRIBUTE_VALUE);
    }
}