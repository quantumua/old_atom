package com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.AccountAdditionalDetailsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by vsnigur on 5/18/17.
 */
public class AccountAdditionalDetailsPageImpl extends AbstractPageObject implements AccountAdditionalDetailsPage {

    @StoredId
    private By birthDateDay;
    @StoredId
    private By birthDateMonth;
    @StoredId
    private By birthDateYear;
    @StoredId
    private By countryOfBirth;
    @StoredId
    private By nationality;
    @StoredId
    private By update;


    public AccountAdditionalDetailsPageImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void update(AccountAdditionalDetails info) {
        waitUntilDisplayed(birthDateDay);
        in(birthDateDay).selectByValue(info.birthDateDay);
        in(birthDateMonth).selectByValue(info.birthDateMonth);
        in(birthDateYear).selectByValue(info.birthDateYear);
        in(countryOfBirth).selectByValue(info.countryOfBirth);
        in(nationality).selectByValue(info.nationality);
        find(update).click();
    }
}
