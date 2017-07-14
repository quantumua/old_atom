package com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

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
    	return getElementCssValue(birthDateDay,"value");
    }

    public String getBirthDateDayElementColor () {
    	String borderColor = getElementCssValue(birthDateDay, "border");
        return borderColor;	
    }
    
    public void verifyTextDirectionElements (String textDirection) {
    	List<By> listElements = getDeclaredElements(this);
        for (By element : listElements) {
    	    String actualTextDirection = getTextDirectionOfElement(element);        	
            Assert.assertEquals(textDirection.toLowerCase(), actualTextDirection.toLowerCase());
        }
    }
    
    /**
     * Getting declared list of {@link By} elements for given {@link Object} page class
     *
     * @param page class for any page 
     * @return list of elements on a page
     *
     */

    protected List<By> getDeclaredElements(Object page) {
    	Field[] declaredFields = page.getClass().getDeclaredFields();
    	List<By> listOfElements = new ArrayList<>();
        for (Field field : declaredFields) {
        	By element = null;
        	try {
				element = (By) field.get(page);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        	
			Reporter.log("Found element : " + element + "<br/>");
        	
        	if (element != null) {
        		listOfElements.add(element);
        	}
        }
        return listOfElements;
    } 
    
    private String getTextDirectionOfElement(By element){
    	String direction = getElementCssValue(element, "direction");    	    	
        return direction;	
    }
    
    private String getElementCssValue(By element, String cssValueName){
    	String cssValue =waitUntilDisplayed(element).getCssValue(cssValueName);
    	Reporter.log("Processing element: " + element.toString() + ", got CSS Value: " + cssValueName + "=" + cssValue + "<br/>");
    	return cssValue;
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
