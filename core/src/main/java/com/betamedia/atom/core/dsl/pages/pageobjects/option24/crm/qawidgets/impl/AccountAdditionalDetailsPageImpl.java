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
import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetailsData;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.AccountAdditionalDetailsPage;
/**
 * Created by vsnigur on 5/18/17.
 */
public class AccountAdditionalDetailsPageImpl extends AbstractPageObject implements AccountAdditionalDetailsPage {

    @StoredId
    private By wizardProgressText;
    @StoredId
    private By chatNow;
    @StoredId
    private By additionalDetailsTitle;  
    @StoredId
    private By birthDateTitle;
	@StoredId
    private By birthDateDay;
	@StoredId
    private By birthDateDayDropDownElements;
	@StoredId
    private By birthDateDayDropdownCaption;
    @StoredId
    private By birthDayDropDownSelectItem;
    @StoredId
    private By birthDateMonth;
    @StoredId
    private By birthDateMonthDropdownCaption;
    @StoredId
    private By birthDateYear;
    @StoredId
    private By birthDateYearDropdownCaption;
    @StoredId
    private By countryOfBirthTitle;
    @StoredId
    private By countryOfBirth;
    @StoredId
    private By countryOfBirthDropdownCaption;
    @StoredId
    private By countryOfBirthDropdownDataError;
    @StoredId
    private By nationalityTitle;    
    @StoredId
    private By nationality;
    @StoredId
    private By nationalityDropdownCaption;
    @StoredId
    private By nationalityDropdownDataError;    
    @StoredId
    private By submit;
    
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
    	if (waitUntilDisplayed(submit).isEnabled())
    		return true;
    	return false;
    }
    
    @Override
    public void expandDropDownButton(){
    	waitUntilDisplayed(birthDateDay).click();
    }

    @Override
    public List<String> getBirthDayDataList() {
        List<String> BirthDayDataOptions = new ArrayList<String>();
        for(WebElement webElement:findElements(birthDateDayDropDownElements)) {
        	BirthDayDataOptions.add(webElement.getText());
        }
        return BirthDayDataOptions;
    }
    
    @Override
    public void selectBirthDayData() {
    	this.expandDropDownButton();
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
    
    public void verifySlideTranslation(AccountAdditionalDetailsData data){
    	Assert.assertEquals(waitUntilDisplayed(wizardProgressText).getText().replaceAll("[0-9]", ""), data.getProgressText());
    	Assert.assertEquals(waitUntilDisplayed(chatNow).getText(), data.getChatLink());
	    Assert.assertEquals(waitUntilDisplayed(additionalDetailsTitle).getText(), data.getAdditionalDetailsTitle());	    
	    Assert.assertEquals(waitUntilDisplayed(birthDateTitle).getText(), data.getBirthDateTitle());
	    Assert.assertEquals(waitUntilDisplayed(birthDateDayDropdownCaption).getText(), data.getBirthDateDayDropdownCaption());
	    Assert.assertEquals(waitUntilDisplayed(birthDateMonthDropdownCaption).getText(), data.getBirthDateMonthDropdownCaption());
	    Assert.assertEquals(waitUntilDisplayed(birthDateYearDropdownCaption).getText(), data.getBirthDateYearDropdownCaption());
	    Assert.assertEquals(waitUntilDisplayed(countryOfBirthTitle).getText(), data.getCountryOfBirthTitle());
	    Assert.assertEquals(waitUntilDisplayed(countryOfBirthDropdownCaption).getText(), data.getCountryOfBirthDropdownCaption());
	    // Assert.assertEquals(waitUntilDisplayed(countryOfBirthDropdownDataError).getAttribute("data-error"), data.getCountryOfBirthDropdownDataError());
	    Assert.assertEquals(waitUntilDisplayed(nationalityTitle).getText(), data.getNationalityTitle());
	    Assert.assertEquals(waitUntilDisplayed(nationalityDropdownCaption).getText(), data.getNationalityDropdownCaption());
	    // Assert.assertEquals(waitUntilDisplayed(nationalityDropdownDataError).getAttribute("data-error"), data.getNationalityDropdownDataError());
	    Assert.assertEquals(waitUntilDisplayed(submit).getAttribute("value"), data.getSubmitButton());
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
    public void selectAllFormElements(AccountAdditionalDetails info) {
        this.exists();
        inSelect(birthDateDay).selectByValue(info.birthDateDay);
        inSelect(birthDateMonth).selectByValue(info.birthDateMonth);
        inSelect(birthDateYear).selectByValue(info.birthDateYear);
        inSelect(countryOfBirth).selectByValue(info.countryOfBirth);
        inSelect(nationality).selectByValue(info.nationality);
    }    
}
