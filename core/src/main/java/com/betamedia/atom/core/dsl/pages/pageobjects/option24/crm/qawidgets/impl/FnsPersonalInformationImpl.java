package com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl;

import static java.util.Objects.nonNull;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.FnsPersonalInformation;

/**
 * Created by vadimsn on 5/18/17.
 */
public class FnsPersonalInformationImpl extends AbstractPageObject implements FnsPersonalInformation {

    @StoredId
    private By employmentStatus;
    @StoredId
    private By employmentStatusSalariedEmployee;
    @StoredId
    private By industry;
    @StoredId
    private By industryOther;
    @StoredId
    private By educationLevel;
    @StoredId
    private By educationField;
    @StoredId
    private By educationFieldOther;
    @StoredId
    private By isPoliticallyExposed;
    @StoredId
    private By politicalExposureComment;
    @StoredId
    private By sourceOfFunds;
    @StoredId
    private By sourceOfFundsOther;
    @StoredId
    private By annualIncome;
    @StoredId
    private By netWealth;
    @StoredId
    private By expectedDepositsPerYear;
    @StoredId
    private By purposeOfTrading;
    @StoredId
    private By purposeOfTradingOther;
    @StoredId
    private By submit;
    @StoredId
    private By resultPlaceholder;
    @StoredId
    private By politicalExposureBttn;
    @StoredId
    private By purposeOfTradingBttn; 
    @StoredId
    private By educationFieldBttn;
    @StoredId
    private By sourceOfFundsBttn;
    @StoredId
    private By industryOtherBttn;
    
    public FnsPersonalInformationImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean exists() {
    	return waitUntilDisplayed(employmentStatusSalariedEmployee).isDisplayed();
    }

    @Override
    public void submit(PersonalInformation info) {
        inSelect(employmentStatus).selectByValue(info.employmentStatus);
        inSelect(industry).selectByValue(info.industry);
        if(nonNull(info.industryOther)) {find(industryOther).sendKeys(info.industryOther);}
        inSelect(educationLevel).selectByValue(info.educationLevel);
        inSelect(educationField).selectByValue(info.educationField);
        if(nonNull(info.educationFieldOther)) find(educationFieldOther).sendKeys(info.educationFieldOther);
        inSelect(isPoliticallyExposed).selectByValue(info.isPoliticallyExposed);
        if(nonNull(info.politicalExposureComment)) find(politicalExposureComment).sendKeys(info.politicalExposureComment);
        inSelect(sourceOfFunds).selectByValue(info.sourceOfFunds);
        if(nonNull(info.sourceOfFundsOther)) find(sourceOfFundsOther).sendKeys(info.sourceOfFundsOther);
        inSelect(annualIncome).selectByValue(info.annualIncome);
        inSelect(netWealth).selectByValue(info.netWealth);
        inSelect(expectedDepositsPerYear).selectByValue(info.expectedDepositsPerYear);
        inSelect(purposeOfTrading).selectByValue(info.purposeOfTrading);
        if(nonNull(info.purposeOfTradingOther)) find(purposeOfTradingOther).sendKeys(info.purposeOfTradingOther);
        scrollIntoView(find(submit)).click();
        waitUntil(() -> waitUntilDisplayed(resultPlaceholder).getText().contains("\"HasPersonalInformationAnswers\":true"));
    }

    @Override
    public void submitOnWizard(PersonalInformation info) {
        submitOnWizard(info.employmentStatus);
        submitOnWizard(info.industry);
        if(nonNull(info.industryOther)){ 
        	find(industryOther).sendKeys(info.industryOther);
        	executeScript("arguments[0].click()", find(industryOtherBttn));
        }
        submitOnWizard(info.educationLevel);
        submitOnWizard(info.educationField);
        if(nonNull(info.educationFieldOther)){ 
        	find(educationFieldOther).sendKeys(info.educationFieldOther);
        	executeScript("arguments[0].click()",find(educationFieldBttn));
        }
        submitOnWizard(info.isPoliticallyExposed);
        if(nonNull(info.politicalExposureComment)){ 
        	find(politicalExposureComment).sendKeys(info.politicalExposureComment);
        	executeScript("arguments[0].click()",find(politicalExposureBttn));
        }
        submitOnWizard(info.sourceOfFunds);
        if(nonNull(info.sourceOfFundsOther)){ 
        	find(sourceOfFundsOther).sendKeys(info.sourceOfFundsOther);
        	executeScript("arguments[0].click()",find(sourceOfFundsBttn));
        }
        submitOnWizard(info.annualIncome);
        submitOnWizard(info.netWealth);
        submitOnWizard(info.expectedDepositsPerYear);
        submitOnWizard(info.purposeOfTrading);
        if(nonNull(info.purposeOfTradingOther)) {
        	find(purposeOfTradingOther).sendKeys(info.purposeOfTradingOther);
        	executeScript("arguments[0].click()",find(purposeOfTradingBttn));
        }
        waitUntilExists(By.id("FinancialProducts1Shares"));
    }

    private void submitOnWizard(String dataValue) {
        waitUntilDisplayed(By.cssSelector("li[data-value='"+dataValue+"']")).click();
    }

}
