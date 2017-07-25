package com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetailsData;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.AccountAdditionalDetailsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    	return getElementCssValue(birthDateDay,"value");
    }

    @Override
    public String getBirthDateDayElementColor () {
        return getElementCssValue(birthDateDay, "border");
    }

    @Override
    public void verifyTextDirectionElements(String expectedDirection) {
        getPageElements()
                .stream()
                .map(this::getTextDirectionOfElement)
                .forEach(textDirection ->
                        Assert.assertEquals(
                                textDirection.toLowerCase(),
                                expectedDirection.toLowerCase(),
                                "Text direction verification for: " + this));
    }

    @Override
    public void verifySlideTranslation(AccountAdditionalDetailsData data) {
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
        return waitUntilDisplayed(birthDateDay).getCssValue("background-image");
    }

    @Override
    public void SelectBirthDateDay(AccountAdditionalDetails info) {
        inSelect(birthDateDay).selectByValue(info.birthDateDay);
    }

    @Override
    public void SelectBirthDateMonth(AccountAdditionalDetails info) {
        inSelect(birthDateMonth).selectByValue(info.birthDateMonth);
    }

    @Override
    public void SelectCountryOfBirth(AccountAdditionalDetails info) {
        inSelect(countryOfBirth).selectByValue(info.countryOfBirth);
    }

    @Override
    public void SelectNationality(AccountAdditionalDetails info) {
        inSelect(nationality).selectByValue(info.nationality);
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

    private String getTextDirectionOfElement(By element) {
        return getElementCssValue(element, "direction");
    }

    private String getElementCssValue(By element, String cssValueName) {
        String cssValue = waitUntilDisplayed(element).getCssValue(cssValueName);
        Reporter.log("Processing element: " + element.toString() + ", got CSS Value: " + cssValueName + "=" + cssValue + "<br/>");
        return cssValue;
    }

    private List<By> getPageElements() {
        return Arrays.stream(this.getClass().getDeclaredFields())
                .filter(field -> By.class.isAssignableFrom(field.getType()))
                .map(field -> {
                    try {
                        Object element = field.get(this);
                        Reporter.log("Found element: " + element);
                        return element;
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("", e);
                    }
                })
                .map(By.class::cast)
                .collect(Collectors.toList());
    }

}
