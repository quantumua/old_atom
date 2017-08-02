package com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.impl;

import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author mbelyaev
 * @since 8/2/17
 */
public class WebFnsEmployerInformationImpl extends AbstractFnsEmployerInformation {
    public WebFnsEmployerInformationImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void submit(PersonalInformation info) {
        waitUntilDisplayed(employerName).sendKeys(info.employerName);
        setDisplayBlock(taxResidenceCountry);
        inSelect(taxResidenceCountry).selectByValue(info.taxResidenceCountry);
        submitOnWizardAndWriteValue(info.hasTaxIdentificationNumber, info.taxIdentificationNumber);
        click(fnsSuitabilityButton);

    }

    private void submitOnWizardAndWriteValue(String dataValue, String numberTaxId) {
        waitUntilDisplayed(By.cssSelector("li[data-value='" + dataValue + "']")).click();
        waitUntilDisplayed(By.id(dataValue + "_alternate"));
        find(By.id(dataValue + "_alternate")).sendKeys(numberTaxId);
    }
}
