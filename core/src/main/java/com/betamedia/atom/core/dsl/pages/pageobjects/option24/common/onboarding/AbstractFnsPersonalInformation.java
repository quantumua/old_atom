package com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.FnsPersonalInformation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by vadimsn on 5/18/17.
 */
public abstract class AbstractFnsPersonalInformation extends AbstractPageObject implements FnsPersonalInformation {

    @StoredId
    protected By employmentStatus;
    @StoredId
    protected By employmentStatusSalariedEmployee;
    @StoredId
    protected By industry;
    @StoredId
    protected By industryOther;
    @StoredId
    protected By educationLevel;
    @StoredId
    protected By educationField;
    @StoredId
    protected By educationFieldOther;
    @StoredId
    protected By isPoliticallyExposed;
    @StoredId
    protected By politicalExposureComment;
    @StoredId
    protected By sourceOfFunds;
    @StoredId
    protected By sourceOfFundsOther;
    @StoredId
    protected By annualIncome;
    @StoredId
    protected By netWealth;
    @StoredId
    protected By expectedDepositsPerYear;
    @StoredId
    protected By purposeOfTrading;
    @StoredId
    protected By purposeOfTradingOther;
    @StoredId
    protected By submit;
    @StoredId
    protected By resultPlaceholder;
    @StoredId
    protected By politicalExposureBttn;
    @StoredId
    protected By purposeOfTradingBttn; 
    @StoredId
    protected By educationFieldBttn;
    @StoredId
    protected By sourceOfFundsBttn;
    @StoredId
    protected By industryOtherBttn;

    public AbstractFnsPersonalInformation(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean exists() {
    	return waitUntilDisplayed(employmentStatusSalariedEmployee).isDisplayed();
    }

}
