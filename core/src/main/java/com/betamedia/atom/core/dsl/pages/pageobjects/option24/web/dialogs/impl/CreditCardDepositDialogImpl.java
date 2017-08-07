package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.dialogs.impl;

import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.CreditCardDepositPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

/**
 * Created by vsnigur on 5/18/17.
 */
public class CreditCardDepositDialogImpl extends AbstractPageObject implements CreditCardDepositPage {

	@StoredId
	private By depositAmount;
	@StoredId
	private By creditCardNumber;
	@StoredId
	private By cvv2;
	@StoredId
	private By expiryDateMonth;
	@StoredId
	private By expiryDateYear;
	@StoredId
	private By cardHoldersFirstName;
	@StoredId
	private By cardHoldersLastName;
	@StoredId
	private By billingAddress;
	@StoredId
	private By city;
	@StoredId
	private By zipCode;
	@StoredId
	private By country;
	@StoredId
	private By submit;

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
}