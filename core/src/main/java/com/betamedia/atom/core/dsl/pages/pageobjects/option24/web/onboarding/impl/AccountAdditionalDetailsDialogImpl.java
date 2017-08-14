package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetailsData;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.AbstractAccountAdditionalDetails;
import com.betamedia.atom.core.testingtype.base.AbstractTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class AccountAdditionalDetailsDialogImpl extends AbstractAccountAdditionalDetails {

    public AccountAdditionalDetailsDialogImpl(WebDriver webDriver) {
        super(webDriver);
    }

    private static final Logger logger = LogManager.getLogger(AccountAdditionalDetailsDialogImpl.class);

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
    	return getCssProperty("value", birthDateDay);
    }

    @Override
    public String getBirthDateDayElementColor () {
        return getCssProperty("border", birthDateDay);
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
        AbstractTest.softAssert().assertEquals(waitUntilDisplayed(wizardProgressText).getText().replaceAll("[0-9]", ""), data.getProgressText());
        AbstractTest.softAssert().assertEquals(waitUntilDisplayed(chatNow).getText(), data.getChatLink());
        AbstractTest.softAssert().assertEquals(waitUntilDisplayed(additionalDetailsTitle).getText(), data.getAdditionalDetailsTitle());
        AbstractTest.softAssert().assertEquals(waitUntilDisplayed(birthDateTitle).getText(), data.getBirthDateTitle());
        AbstractTest.softAssert().assertEquals(waitUntilDisplayed(birthDateDayDropdownCaption).getText(), data.getBirthDateDayDropdownCaption());
        AbstractTest.softAssert().assertEquals(waitUntilDisplayed(birthDateMonthDropdownCaption).getText(), data.getBirthDateMonthDropdownCaption());
        AbstractTest.softAssert().assertEquals(waitUntilDisplayed(birthDateYearDropdownCaption).getText(), data.getBirthDateYearDropdownCaption());
        AbstractTest.softAssert().assertEquals(waitUntilDisplayed(countryOfBirthTitle).getText(), data.getCountryOfBirthTitle());
        AbstractTest.softAssert().assertEquals(waitUntilDisplayed(countryOfBirthDropdownCaption).getText(), data.getCountryOfBirthDropdownCaption());
	    // Assert.assertEquals(waitUntilDisplayed(countryOfBirthDropdownDataError).getAttribute("data-error"), data.getCountryOfBirthDropdownDataError());
        AbstractTest.softAssert().assertEquals(waitUntilDisplayed(nationalityTitle).getText(), data.getNationalityTitle());
        AbstractTest.softAssert().assertEquals(waitUntilDisplayed(nationalityDropdownCaption).getText(), data.getNationalityDropdownCaption());
	    // Assert.assertEquals(waitUntilDisplayed(nationalityDropdownDataError).getAttribute("data-error"), data.getNationalityDropdownDataError());
        AbstractTest.softAssert().assertEquals(waitUntilDisplayed(submit).getAttribute("value"), data.getSubmitButton());
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
        logger.info(String.format("Filling Additional Details form: %s", info.toString()));
    }

    private String getTextDirectionOfElement(By element) {
        return getCssProperty("direction", element);
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