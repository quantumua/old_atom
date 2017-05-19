package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl;

import com.betamedia.qe.af.core.api.crm.form.builders.AccountAdditionalDetailsBuilder;
import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.AccountAdditionalDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by vsnigur on 5/18/17.
 */
public class AccountAdditionalDetailsImpl extends AbstractPageObject implements AccountAdditionalDetails {

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


    public AccountAdditionalDetailsImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void update(AccountAdditionalDetailsBuilder.AccountAdditionalDetails info) {
        waitUntilDisplayed(birthDateDay);
        in(birthDateDay).selectByValue(info.birthDateDay);
        in(birthDateMonth).selectByValue(info.birthDateMonth);
        in(birthDateYear).selectByValue(info.birthDateYear);
        in(countryOfBirth).selectByVisibleText(info.countryOfBirth);
        in(nationality).selectByVisibleText(info.nationality);
        find(update).click();
    }
}
