package com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding.impl;

import com.betamedia.atom.core.api.crm.form.entities.PersonalInformation;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.AbstractFnsEmployerInformation;
import org.openqa.selenium.WebDriver;

import static java.util.Objects.nonNull;

/**
 * @author mbelyaev
 * @since 8/2/17
 */
public class WidgetFnsEmployerInformationImpl extends AbstractFnsEmployerInformation {
    public WidgetFnsEmployerInformationImpl(WebDriver webDriver) {
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
}
