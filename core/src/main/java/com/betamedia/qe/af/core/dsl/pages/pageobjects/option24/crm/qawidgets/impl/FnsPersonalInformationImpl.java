package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl;

import com.betamedia.qe.af.core.api.crm.form.builders.PersonalInformationBuilder.PersonalInformation;
import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.FnsPersonalInformation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import java.util.List;

/**
 * Created by vadimsn on 5/18/17.
 */
public class FnsPersonalInformationImpl extends AbstractPageObject implements FnsPersonalInformation {

    @StoredId
    private By employmentStatus;
    @StoredId
    private By industry;
    @StoredId
    private By industryOther;
    @StoredId
    private By employerName;
    @StoredId
    private By taxResidenceCountry;
    @StoredId
    private By isUSReportable;
    @StoredId
    private By hasTaxIdentificationNumber;
    @StoredId
    private By taxIdentificationNumber;
    @StoredId
    private By socialSecurityNumber;
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

    public FnsPersonalInformationImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void submit(PersonalInformation info) {
        in(employmentStatus).selectByValue(info.employmentStatus);
        in(industry).selectByValue(info.industry);
        if(notNull(info.industryOther)) find(industryOther).sendKeys(info.industryOther);
        if(notNull(info.employerName)) find(employerName).sendKeys(info.employerName);
        in(taxResidenceCountry).selectByValue(info.taxResidenceCountry);
        if(notNull(info.isUSReportable)) in(isUSReportable).selectByValue(info.isUSReportable);
        in(hasTaxIdentificationNumber).selectByValue(info.hasTaxIdentificationNumber);
        if(notNull(info.taxIdentificationNumber)) find(taxIdentificationNumber).sendKeys(info.taxIdentificationNumber);
        if(notNull(info.socialSecurityNumber)) find(socialSecurityNumber).sendKeys(info.socialSecurityNumber);
        in(educationLevel).selectByValue(info.educationLevel);
        in(educationField).selectByValue(info.educationField);
        if(notNull(info.educationFieldOther)) find(educationFieldOther).sendKeys(info.educationFieldOther);
        in(isPoliticallyExposed).selectByValue(info.isPoliticallyExposed);
        if(notNull(info.politicalExposureComment)) find(politicalExposureComment).sendKeys(info.politicalExposureComment);
        in(sourceOfFunds).selectByValue(info.sourceOfFunds);
        if(notNull(info.sourceOfFundsOther)) find(sourceOfFundsOther).sendKeys(info.sourceOfFundsOther);
        in(annualIncome).selectByValue(info.annualIncome);
        in(netWealth).selectByValue(info.netWealth);
        in(expectedDepositsPerYear).selectByValue(info.expectedDepositsPerYear);
        in(purposeOfTrading).selectByValue(info.purposeOfTrading);
        if(notNull(info.purposeOfTradingOther)) find(purposeOfTradingOther).sendKeys(info.purposeOfTradingOther);
        click(submit);
        waitUntilDisplayed(resultPlaceholder);
    }

    @Override
    public void submitOnWizard(String dataValue) {
        waitUntilExists(By.cssSelector("li[data-value='"+dataValue+"']")).click();
    }

    private static boolean notNull(Object o) {
        return o != null;
    }
}
