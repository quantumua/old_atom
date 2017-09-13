package com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.impl;

import com.betamedia.atom.core.api.crm.form.entities.CreditCardDeposit;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.AbstractCreditCardDeposit;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.CreditCardDepositPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.betamedia.atom.core.testingtype.base.AbstractTest.softAssert;

/**
 * Created by vsnigur on 5/18/17.
 */
public class CreditCardDepositPageImpl extends AbstractCreditCardDeposit implements CreditCardDepositPage {

	public CreditCardDepositPageImpl(WebDriver webDriver) {
		super(webDriver);
	}
	private static final String CSS_BACKGROUND_COLOR = "background-color";
	final private static String  DEPOSIT_AMOUNT= "Deposit Amount";
	final private static String  CREDIT_CARD_NUMBER = "Credit Card Number";
	final private static String  CVV2= "Last 3 digits written on the back of your card.";
	final private static String  FIRST_NAME= "Card Holder's First Name";
	final private static String  LAST_NAME= "Card Holder's Last Name";
	final private static String  BILLING_ADDRESS= "Billing Address";
	final private static String  CITY= "City";
	final private static String  ZIP_CODE= "Zip Code";
	final private static String  COUNTRY= "Country of Residence";

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
	public String getInfoMessageHint() {
		return waitUntilDisplayed(infoMessageHint).getText();
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
		moveCursorToElement(submit);
	}

	@Override
	public String getSubmitButtonCollor() {
		return find(submit).getCssValue(CSS_BACKGROUND_COLOR);
	}

	@Override
	public void assertToolTipVisibilyty(){
		moveCursorToElement(depositAmount);
		softAssert().assertEquals(getInfoMessageHint(), DEPOSIT_AMOUNT,"Verification for tooltip deposit amount");
		moveCursorToElement(creditCardNumber);
		softAssert().assertEquals(getInfoMessageHint(), CREDIT_CARD_NUMBER,"Verification for tooltip credit card number");
		moveCursorToElement(cvv2);
		softAssert().assertEquals(getInfoMessageHint(), CVV2,"Verification for tooltip CVV2 code");
		moveCursorToElement(cardHoldersFirstName);
		softAssert().assertEquals(getInfoMessageHint(), FIRST_NAME,"Verification for tooltip first name");
		moveCursorToElement(cardHoldersLastName);
		softAssert().assertEquals(getInfoMessageHint(), LAST_NAME,"Verification for tooltip last name");
		moveCursorToElement(billingAddress);
		softAssert().assertEquals(getInfoMessageHint(), BILLING_ADDRESS,"Verification for tooltip billing address");
		moveCursorToElement(city);
		softAssert().assertEquals(getInfoMessageHint(), CITY,"Verification for tooltip city");
		moveCursorToElement(zipCode);
		softAssert().assertEquals(getInfoMessageHint(), ZIP_CODE,"Verification for tooltip zip code");
		moveCursorToElement(country);
		softAssert().assertEquals(getInfoMessageHint(), COUNTRY,"Verification for tooltip country");
	}

	@Override
	public void assertEmptyFieldTooltipError(CreditCardDeposit info){
		find(cardHoldersFirstName).clear();
		find(cardHoldersLastName).clear();
		scrollIntoView(find(submit)).click();

		softAssert().assertEquals(getErrorMessageHint(), "Please Enter Only Numbers","Verification for deposit amount");
		find(depositAmount).sendKeys(info.depositAmount);

		scrollIntoView(find(submit)).click();

		softAssert().assertEquals(getErrorMessageHint(), "Invalid Credit Card Number","Verification for credit card number");
		find(creditCardNumber).sendKeys(info.creditCardNumber);

		scrollIntoView(find(submit)).click();

		softAssert().assertEquals(getErrorMessageHint(), "Please Enter Only Numbers","Verification for CVV2");
		find(cvv2).sendKeys(info.cvv2);
		inSelect(expiryDateMonth).selectByValue(info.expiryDateMonth);
		inSelect(expiryDateYear).selectByValue(info.expiryDateYear);

		scrollIntoView(find(submit)).click();

		softAssert().assertEquals(getErrorMessageHint(), "Please Enter Only English characters","Verification for card holder name");
		find(cardHoldersFirstName).sendKeys(info.cardHoldersFirstName);
		find(cardHoldersLastName).sendKeys(info.cardHoldersLastName);

		scrollIntoView(find(submit)).click();

		softAssert().assertEquals(getErrorMessageHint(), "Please Enter A Billing Address","Verification for billing address");
		find(billingAddress).sendKeys(info.billingAddress);

		scrollIntoView(find(submit)).click();

		softAssert().assertEquals(getErrorMessageHint(), "Please Enter A City","Verification for city");
		find(city).sendKeys(info.city);

		scrollIntoView(find(submit)).click();

		softAssert().assertEquals(getErrorMessageHint(), "Please Enter A Zip Code","Verification for zip code");
		find(zipCode).sendKeys(info.zipCode);

		if (info.country != null) {
			find(country).sendKeys(info.country);
		}

		scrollIntoView(find(submit)).click();
	}


	private void moveCursorToElement(By element) {
		makeActions().moveToElement(find(element))
				.build()
				.perform();
	}
}
