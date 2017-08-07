package com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding.FnsEmployerInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author mbelyaev
 * @since 7/31/17
 */
public abstract class AbstractFnsEmployerInformation extends AbstractPageObject implements FnsEmployerInfo {
    @StoredId
    protected By employerName;
    @StoredId
    protected By taxResidenceCountry;
    @StoredId
    protected By isUSReportable;
    @StoredId
    protected By hasTaxIdentificationNumber;
    @StoredId
    protected By taxIdentificationNumber;
    @StoredId
    protected By socialSecurityNumber;
    @StoredId
    protected By fnsSuitabilityButton;

    protected AbstractFnsEmployerInformation(WebDriver webDriver) {
        super(webDriver);
    }
}
