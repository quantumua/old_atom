package com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl;

import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.FnsPersonalInformation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
    public boolean exists() {
        return waitUntilDisplayed(employmentStatus) != null;
    }
    
    @Override
    public void submit(PersonalInformation info) {
        inSelect(employmentStatus).selectByValue(info.employmentStatus);
        inSelect(industry).selectByValue(info.industry);
        if(notNull(info.industryOther)) find(industryOther).sendKeys(info.industryOther);
        if(notNull(info.employerName)) find(employerName).sendKeys(info.employerName);
        inSelect(taxResidenceCountry).selectByValue(info.taxResidenceCountry);
        if(notNull(info.isUSReportable)) inSelect(isUSReportable).selectByValue(info.isUSReportable);
        inSelect(hasTaxIdentificationNumber).selectByValue(info.hasTaxIdentificationNumber);
        if(notNull(info.taxIdentificationNumber)) find(taxIdentificationNumber).sendKeys(info.taxIdentificationNumber);
        if(notNull(info.socialSecurityNumber)) find(socialSecurityNumber).sendKeys(info.socialSecurityNumber);
        inSelect(educationLevel).selectByValue(info.educationLevel);
        inSelect(educationField).selectByValue(info.educationField);
        if(notNull(info.educationFieldOther)) find(educationFieldOther).sendKeys(info.educationFieldOther);
        inSelect(isPoliticallyExposed).selectByValue(info.isPoliticallyExposed);
        if(notNull(info.politicalExposureComment)) find(politicalExposureComment).sendKeys(info.politicalExposureComment);
        inSelect(sourceOfFunds).selectByValue(info.sourceOfFunds);
        if(notNull(info.sourceOfFundsOther)) find(sourceOfFundsOther).sendKeys(info.sourceOfFundsOther);
        inSelect(annualIncome).selectByValue(info.annualIncome);
        inSelect(netWealth).selectByValue(info.netWealth);
        inSelect(expectedDepositsPerYear).selectByValue(info.expectedDepositsPerYear);
        inSelect(purposeOfTrading).selectByValue(info.purposeOfTrading);
        if(notNull(info.purposeOfTradingOther)) find(purposeOfTradingOther).sendKeys(info.purposeOfTradingOther);
        scrollIntoView(find(submit)).click();
        waitUntil(() -> waitUntilDisplayed(resultPlaceholder).getText().contains("\"HasPersonalInformationAnswers\":true"));
    }

    @Override
    public void submitOnWizard(PersonalInformation info) {
        submitOnWizard(info.employmentStatus);
        submitOnWizard(info.industry);

        waitUntilDisplayed(employerName);
        find(employerName).sendKeys(info.employerName);

        waitUntilDisplayed(taxResidenceCountry);
        inSelect(taxResidenceCountry).selectByValue(info.taxResidenceCountry);

//        submitOnWizard(info.hasTaxIdentificationNumber);
//        submitOnWizard(info.hasTaxIdentificationNumber);

//        waitUntilDisplayed(taxIdentificationNumber);
//        find(taxIdentificationNumber).sendKeys(info.taxIdentificationNumber);
        submitOnWizardAndWriteValue(info.hasTaxIdentificationNumber, info.taxIdentificationNumber);

        WebElement submitButton = find(By.xpath("//*[@id=\"fns-pi-form-space\"]/div/div[2]/div/div[3]/input"));
        executeScript("arguments[0].scrollIntoView(true)", submitButton);
        makeActions().moveToElement(submitButton).click().build().perform();

        submitOnWizard(info.educationLevel);
        submitOnWizard(info.educationField);
        submitOnWizard(info.isPoliticallyExposed);
        submitOnWizard(info.sourceOfFunds);
        submitOnWizard(info.annualIncome);
        submitOnWizard(info.netWealth);
        submitOnWizard(info.expectedDepositsPerYear);
        submitOnWizard(info.purposeOfTrading);
        waitUntilExists(By.id("FinancialProducts1Shares"));
    }

    @Override
    public void submitOnWizard(String dataValue) {
        waitUntilDisplayed(By.cssSelector("li[data-value='"+dataValue+"']")).click();
    }
    
    public void submitOnWizardAndWriteValue(String dataValue, String numberTaxId) {
        waitUntilDisplayed(By.cssSelector("li[data-value='"+dataValue+"']")).click();
        waitUntilDisplayed(By.id(dataValue+"_alternate"));
        find(By.id(dataValue+"_alternate")).sendKeys(numberTaxId);
    }

    private static boolean notNull(Object o) {
        return o != null;
    }
}
