package com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.AbstractAccountDetails;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.AccountDetailsPage;
import com.betamedia.atom.core.api.crm.form.entities.AccountDetails;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by vsnigur on 5/18/17.
 */
public class AccountDetailsPageImpl extends AbstractAccountDetails {

    public AccountDetailsPageImpl(WebDriver webDriver) {
        super(webDriver);
    }

    public void invoke() {
        // to implement for widgets
    }

    @Override
    public void update(AccountDetails info) {
        waitUntilDisplayed(title);
        inSelect(title).selectByVisibleText(info.title);
        find(street).sendKeys(info.street);
        find(streetNumber).sendKeys(info.streetNumber);
        find(city).sendKeys(info.city);
        find(phone2).sendKeys(info.phone2);
        inSelect(birthdayDay).selectByValue(info.birthdayDay);
        inSelect(birthdayMonth).selectByValue(info.birthdayMonth);
        inSelect(birthdayYear).selectByValue(info.birthdayYear);
        find(update).click();
    }
}
