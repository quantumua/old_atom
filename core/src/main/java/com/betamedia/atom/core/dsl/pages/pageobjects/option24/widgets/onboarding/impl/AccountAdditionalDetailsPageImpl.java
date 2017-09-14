package com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.impl;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.AbstractAccountAdditionalDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vsnigur on 5/18/17.
 */
public class AccountAdditionalDetailsPageImpl extends AbstractAccountAdditionalDetails {

    public AccountAdditionalDetailsPageImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void update(AccountAdditionalDetails info) {
    	selectAllFormElements(info);
        scrollIntoView(find(submit)).click();
    }

    @Override
    public boolean exists() {
        return maybe(() -> waitUntilDisplayed(birthDateDay)).isPresent();
    }
    
    @Override
    public boolean isUpdateBtnEnabled(){
        return waitUntilDisplayed(submit).isEnabled();
    }
    
    @Override
    public void expandDropDownButton(){
    	waitUntilDisplayed(birthDateDay).click();
    }

    @Override
    public List<String> getBirthDayDataList() {
        return findElements(birthDateDayDropDownElements).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
    
    @Override
    public void selectBirthDayData() {
        expandDropDownButton();
        waitUntilDisplayed(birthDayDropDownSelectItem).click();
    }

    @Override
    public String getBirthDaySelectedItem(){
        return getCssValue("value", birthDateDay);
    }

    @Override
    public String getBirthDateDayElementColor () {
        return getCssValue("border", birthDateDay);
    }

    @Override
    public String getElementsBackground() {
        return waitUntilDisplayed(birthDateDay).getCssValue("background-image");
    }

    @Override
    public void selectBirthDateDay(String value) {
        inSelect(birthDateDay).selectByValue(value);
    }

    @Override
    public void selectBirthDateMonth(String value) {
        inSelect(birthDateMonth).selectByValue(value);
    }

    @Override
    public void selectCountryOfBirth(String value) {
        inSelect(countryOfBirth).selectByValue(value);
    }

    @Override
    public void selectNationality(String value) {
        inSelect(nationality).selectByValue(value);
    }

    @Override
    public void selectAllFormElements(AccountAdditionalDetails info) {
        exists();
        inSelect(birthDateDay).selectByValue(info.birthDateDay);
        inSelect(birthDateMonth).selectByValue(info.birthDateMonth);
        inSelect(birthDateYear).selectByValue(info.birthDateYear);
        inSelect(countryOfBirth).selectByValue(info.countryOfBirth);
        inSelect(nationality).selectByValue(info.nationality);
    }

}