package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.AbstractCreditCardDeposit;
import org.apache.logging.log4j.LogManager;
import org.apache.xalan.templates.ElemValueOf;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.xml.stream.events.Attribute;
import java.util.List;
import java.util.jar.Attributes;
import java.util.stream.Collectors;

/**
 * Created by vsnigur on 5/18/17.
 */
public class CreditCardDepositDialogImpl extends AbstractCreditCardDeposit {

	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(CreditCardDepositDialogImpl.class);

	public CreditCardDepositDialogImpl(WebDriver webDriver) {
		super(webDriver);
	}


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

		logger.info(String.format("filling Credit Card Deposit wizard with values: %s", info. toString()));
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
		waitUntilDisplayed(modalClose).click();
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
		return  waitUntilDisplayed(creditCardNumber).getAttribute("value");
	}

	@Override
	public List<String> getExpiryDateMonthList() {
		return findElements(expiryDateMonthDropDownElements).stream()
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
		expandYearDropDownButton();
		waitUntilDisplayed(expiryDateYearDropDownSelectItem).click();
	}

	@Override
	public int getExpiryDateYearSelectedItem(){
		return Integer.valueOf(getAttribute("value", expiryDateYear));
	}


}
