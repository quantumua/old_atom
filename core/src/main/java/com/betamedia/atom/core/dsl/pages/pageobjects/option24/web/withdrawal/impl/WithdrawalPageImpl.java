package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.withdrawal.impl;

import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.withdrawal.WithdrawalPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.betamedia.atom.core.api.crm.form.entities.Withdrawal;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;

public class WithdrawalPageImpl extends AbstractPageObject implements WithdrawalPage {

	private static final Logger logger = LogManager.getLogger(WithdrawalPageImpl.class);

	@StoredId
	private By withdrawalAmount;
	@StoredId
	private By withdrawalBtnStep1;
	@StoredId
	private By withdrawalbankName;
	@StoredId
	private By withdrawalBeneficaryCity;
	@StoredId
	private By withdrawalBeneficaryName;
	@StoredId
	private By withdrawalBranch;
	@StoredId
	private By withdrawalIban;
	@StoredId
	private By withdrawalCountryCode;
	@StoredId
	private By withdrawalRoutingNumber;
	@StoredId
	private By withdrawalSwift;
	@StoredId
	private By withdrawalComment;
	@StoredId
	private By withdrawalBtnStep2;
	@StoredId
	private By withdrawalPrev;

	public WithdrawalPageImpl(WebDriver webDriver) {
		super(webDriver);

	}

	@Override
	public void submit(Withdrawal info) {        
    	logger.info(" Beggining to fill withdrawal page step 1 ");
		waitUntilDisplayed(withdrawalAmount).click();
		click(withdrawalAmount);
		find(withdrawalAmount).sendKeys(info.withdrawalAmount);
		logger.info(" Succesfully filled withdrawal amount , attempting to reach step 2 ");
		waitUntilClickable(withdrawalBtnStep1).click();
		waitUntilDisplayed(withdrawalbankName).click();
		logger.info(" Succesfully reach step2 ");
		find(withdrawalbankName).sendKeys(info.withdrawalbankName);
		find(withdrawalBeneficaryCity).sendKeys(info.withdrawalBeneficaryCity);
		find(withdrawalBeneficaryName).sendKeys(info.withdrawalBeneficaryName);
		find(withdrawalBranch).sendKeys(info.withdrawalBranch);
		find(withdrawalIban).sendKeys(info.withdrawalIban);
		inSelect(withdrawalCountryCode).selectByValue(info.withdrawalCountryCode);
		find(withdrawalRoutingNumber).sendKeys(info.withdrawalRoutingNumber);
		find(withdrawalSwift).sendKeys(info.withdrawalSwift);
		find(withdrawalComment).sendKeys(info.withdrawalComment);
		waitUntilClickable(withdrawalBtnStep2).click();

	}

	@Override
	public void clickPrevious() {

		find(withdrawalPrev).click();
	}

	
	
}
