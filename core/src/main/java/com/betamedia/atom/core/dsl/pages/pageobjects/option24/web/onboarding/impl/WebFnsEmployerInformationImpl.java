package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.atom.core.dsl.pages.extensions.ScriptOperations;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.AbstractFnsEmployerInformation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static com.betamedia.atom.core.testingtype.base.AbstractTest.softAssert;

/**
 * @author mbelyaev
 * @since 8/2/17
 */
public class WebFnsEmployerInformationImpl extends AbstractFnsEmployerInformation implements ScriptOperations {
    public WebFnsEmployerInformationImpl(WebDriver webDriver) {
        super(webDriver);
    }

    private static final Logger logger = LogManager.getLogger(WebFnsEmployerInformationImpl.class);

    @Override
    public void submit(PersonalInformation info) {
        waitUntilDisplayed(employerName).sendKeys(info.employerName);
        setDisplayBlock(taxResidenceCountry);
        inSelect(taxResidenceCountry).selectByValue(info.taxResidenceCountry);
        submitOnWizardAndWriteValue(info.hasTaxIdentificationNumber, info.taxIdentificationNumber);
        logger.info(String.format("filling Fns Employer Information wizard with values: %s", info.toString()));
        scrollIntoView(find(fnsSuitabilityButton)).click();

    }

    @Override
    public void assertCloseNotExist(PersonalInformation info) {
        softAssert().assertFalse(find(modalClose).isDisplayed());
    }

    @Override
    public void assertPersonalInformationExists() {
        softAssert().assertTrue(waitUntilDisplayed(employerName).isDisplayed(),
                employerName + " does not exists");
        softAssert().assertTrue(waitUntilDisplayed(taxResidenceCountry).isDisplayed(),
                taxResidenceCountry + " does not exists");

    }

    @Override
    public boolean progressBarExists() {
        return maybe(() -> find(progressBar)).get().isDisplayed();
    }

    private void submitOnWizardAndWriteValue(String dataValue, String numberTaxId) {
        waitUntilDisplayed(By.cssSelector("li[data-value='" + dataValue + "']")).click();
        waitUntilDisplayed(By.id(dataValue + "_alternate"));
        find(By.id(dataValue + "_alternate")).sendKeys(numberTaxId);
    }
}
