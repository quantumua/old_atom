package com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.impl;

import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.AbstractCreditCardDeposit;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.CreditCardDepositPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vsnigur on 5/18/17.
 */
public class CreditCardDepositPageImpl extends AbstractCreditCardDeposit implements CreditCardDepositPage {

	public CreditCardDepositPageImpl(WebDriver webDriver) {
		super(webDriver);
	}
	private static final String CSS_BACKGROUND_COLOR = "background-color";

	@Override
	public void submit(CreditCardDeposit info) {
		click(depositAmount);
		find(depositAmount).sendKeys(info.depositAmount);
		click(creditCardNumber);
		find(creditCardNumber).sendKeys(info.creditCardNumber);
		find(cvv2).sendKeys(info.cvv2);
		inSelect(expiryDateMonth).selectByValue(info.expiryDateMonth);
		inSelect(expiryDateYear).selectByValue(info.expiryDateYear);
		find(cardHoldersFirstName).sendKeys(info.cardHoldersFirstName);
		find(cardHoldersLastName).sendKeys(info.cardHoldersLastName);
		find(billingAddress).sendKeys(info.billingAddress);
		find(city).sendKeys(info.city);
		find(zipCode).sendKeys(info.zipCode);

		if (info.country != null) {
			find(country).sendKeys(info.country);
		}
		scrollIntoView(find(submit)).click();
	}

	@Override
	public boolean isDisplayed() {
		return waitUntilDisplayed(creditCardNumber) != null;
	}

	@Override
	public void waitforCreditCardDepositPage() {
		waitUntilDisplayed(depositAmount);
	}

	@Override
	public void close() {
	}

	@Override
	public boolean invalidCreditCardNumberErrorExists() {
		return waitUntilDisplayed(invalidCreditCardNumberError) != null;
	}

	@Override
	public String getErrorMessageHint() {
		return waitUntilDisplayed(errorMessageHint).getText();
	}

	@Override
	public String getCreditCardNumber(){
		return  waitUntilDisplayed(creditCardNumber).getText();
	}

	@Override
	public String getCreditCardCity(){
		return  waitUntilDisplayed(city).getAttribute("value");
	}

	@Override
	public String getCreditCardZipCode(){
		return  waitUntilDisplayed(zipCode).getAttribute("value");
	}

	@Override
	public List<String> getExpiryDateMonthList() {
		return findElements(expiryDateMonth).stream()
				.map(WebElement::getText)
				.collect(Collectors.toList());
	}

	@Override
	public void expandDropDownButton(){
		waitUntilDisplayed(expiryDateMonth).click();
	}
	@Override
	public void selectExpiryDateMonth() {
		expandDropDownButton();
		waitUntilDisplayed(expiryDateMonthDropDownSelectItem).click();
	}

	@Override
	public int getExpiryDateMonthSelectedItem(){
		return Integer.valueOf(getAttribute("value", expiryDateMonth));
	}

	@Override
	public List<String> getExpiryDateYearList() {
		return findElements(expiryDateYearDropDownElements).stream()
				.map(WebElement::getText)
				.collect(Collectors.toList());
	}

	@Override
	public void expandYearDropDownButton(){
		waitUntilDisplayed(expiryDateYear).click();
	}
	@Override
	public void selectExpiryDateYear() {
		expandDropDownButton();
		waitUntilDisplayed(expiryDateYearDropDownSelectItem).click();
	}

	@Override
	public int getExpiryDateYearSelectedItem(){
		return Integer.valueOf(getAttribute("value", expiryDateYear));
	}

	@Override
	public void scrollToCountry() {
		waitUntilDisplayed(country).click();
		scrollIntoView(find(countryScrollToElement)).click();
	}

	@Override
	public String getSelectedCountryName(){
		return waitUntilDisplayed(country).getAttribute("value");
	}

	@Override
	public void moveCursorToSubmitButton() {
		makeActions().moveToElement(find(submit))
				.build()
				.perform();
	}

	@Override
	public String getSubmitButtonCollor() {
		String s = find(submit).getCssValue(CSS_BACKGROUND_COLOR);
		return s;
	}
}
