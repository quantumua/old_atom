package com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl;

import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets.FnsEmployerInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.util.Objects.nonNull;

/**
 * @author mbelyaev
 * @since 7/31/17
 */
public class FnsEmployerInfoImpl extends AbstractPageObject implements FnsEmployerInfo {
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
    private By fnsSuitabilityButton;

    public FnsEmployerInfoImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void submit(PersonalInformation info) {
        if (nonNull(info.employerName)) find(employerName).sendKeys(info.employerName);
        inSelect(taxResidenceCountry).selectByValue(info.taxResidenceCountry);
        if (nonNull(info.isUSReportable)) inSelect(isUSReportable).selectByValue(info.isUSReportable);
        inSelect(hasTaxIdentificationNumber).selectByValue(info.hasTaxIdentificationNumber);
        if (nonNull(info.taxIdentificationNumber)) find(taxIdentificationNumber).sendKeys(info.taxIdentificationNumber);
        if (nonNull(info.socialSecurityNumber)) find(socialSecurityNumber).sendKeys(info.socialSecurityNumber);
    }

    @Override
    public void submitOnWizard(PersonalInformation info) {
        waitUntilDisplayed(employerName).sendKeys(info.employerName);
        setDisplayBlock(taxResidenceCountry);
        inSelect(taxResidenceCountry).selectByValue(info.taxResidenceCountry);
        submitOnWizardAndWriteValue(info.hasTaxIdentificationNumber, info.taxIdentificationNumber);
        click(fnsSuitabilityButton);

    }

    private void submitOnWizard(String dataValue) {
        waitUntilDisplayed(By.cssSelector("li[data-value='" + dataValue + "']")).click();
    }

    private void submitOnWizardAndWriteValue(String dataValue, String numberTaxId) {
        waitUntilDisplayed(By.cssSelector("li[data-value='" + dataValue + "']")).click();
        waitUntilDisplayed(By.id(dataValue + "_alternate"));
        find(By.id(dataValue + "_alternate")).sendKeys(numberTaxId);
    }
}
