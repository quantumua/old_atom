package com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.AccountAdditionalDetailsPage;
/**
 * Created by vsnigur on 5/18/17.
 */
public class AccountAdditionalDetailsPageImpl extends AbstractPageObject implements AccountAdditionalDetailsPage {

    @StoredId
    private By birthDateDay;
    @StoredId
    private By birthDayDropDown;
    @StoredId
    private By birthDayDropDownSelectItem;
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
    	if (waitUntilDisplayed(update).isEnabled())
    		return true;
    	return false;
    }
    
    @Override
    public void clickDropDownButton(){
    	waitUntilDisplayed(birthDateDay).click();
    }

    @Override
    public List<String> getBirthDayDataList() {
        List<String> BirthDayDataOptions = new ArrayList<String>();
        for(WebElement webElement:findElements(birthDayDropDown)) {
        	BirthDayDataOptions.add(webElement.getText());
        }
        selectBirthDayData();
        return BirthDayDataOptions;
    }
    
    @Override
    public void selectBirthDayData() {
    	waitUntilDisplayed(birthDayDropDownSelectItem).click();
    }
    
    public String getBirthDaySelectedItem(){
    	return waitUntilDisplayed(birthDateDay).getAttribute("value");
    }

    public String getColorOfElement(){
    	String borderColor = waitUntilDisplayed(birthDateDay).getCssValue("border");
        return borderColor;	
    }
    
    @Override
    public String getElementsBackground() {
    	String backgroundImage = waitUntilDisplayed(birthDateDay).getCssValue("background-image");
    	return backgroundImage ;
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
