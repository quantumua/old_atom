package com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.AccountAdditionalDetailsPage;

import org.junit.Assert;
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
        inSelect(birthDateDay).selectByValue(info.birthDateDay);
        inSelect(birthDateMonth).selectByValue(info.birthDateMonth);
        inSelect(birthDateYear).selectByValue(info.birthDateYear);
        inSelect(countryOfBirth).selectByValue(info.countryOfBirth);
        inSelect(nationality).selectByValue(info.nationality);
        scrollIntoView(find(update)).click();
    }

    @Override
    public boolean exists() {
        try {
            return waitUntil(() -> maybe(() -> find(birthDateDay))
                    .orElseGet(() -> find(birthDateDay))
                    .isDisplayed());
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public boolean isUpdateBtnEnabled(){
        return waitUntilDisplayed(birthDateDay) != null;
    }
    
    @Override
    public void SelectBirthDateDay(AccountAdditionalDetails info) {
        waitUntilDisplayed(birthDateDay);
        inSelect(birthDateDay).selectByValue(info.birthDateDay);
    }

    @Override
    public void SelectBirthDateMonth(AccountAdditionalDetails info) {
        waitUntilDisplayed(birthDateMonth);
        inSelect(birthDateMonth).selectByValue(info.birthDateMonth);
    }
    
    @Override
    public void SelectBirthDateYear(AccountAdditionalDetails info) {
        waitUntilDisplayed(birthDateYear);
        inSelect(birthDateYear).selectByValue(info.birthDateYear);
    }
    
    public void SelectCountryOfBirth(AccountAdditionalDetails info) {
        waitUntilDisplayed(countryOfBirth);
        inSelect(countryOfBirth).selectByValue(info.countryOfBirth);
    }    
    
    @Override
    public void SelectNationality(AccountAdditionalDetails info) {
        waitUntilDisplayed(nationality);
        inSelect(nationality).selectByValue(info.nationality);
    }
    
    @Override
    public void SelectAllData(AccountAdditionalDetails info) {
        waitUntilDisplayed(birthDateDay);
        inSelect(birthDateDay).selectByValue(info.birthDateDay);
        inSelect(birthDateMonth).selectByValue(info.birthDateMonth);
        inSelect(birthDateYear).selectByValue(info.birthDateYear);
        inSelect(countryOfBirth).selectByValue(info.countryOfBirth);
        inSelect(nationality).selectByValue(info.nationality);
    }
    

    
}
