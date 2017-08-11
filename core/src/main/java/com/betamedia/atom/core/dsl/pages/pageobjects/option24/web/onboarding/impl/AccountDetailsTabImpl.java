package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.api.crm.form.entities.AccountDetails;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.AbstractAccountDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by vsnigur on 5/18/17.
 */
public class AccountDetailsTabImpl extends AbstractAccountDetails {

    @StoredId
    private By winID;

    public AccountDetailsTabImpl(WebDriver webDriver) {
        super(webDriver);
    }


    public void invoke() {
        waitUntilDisplayed(winID).click();
    }

    @Override
    public void update(AccountDetails info) {
        // not used for WEB yet
    }

}
