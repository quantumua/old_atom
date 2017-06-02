package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.onboarding.impl;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.onboarding.OnBoardingWizard;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

/**
 * Created by mbelyaev on 5/24/17.
 */
public class OnBoardingWizardImpl extends AbstractPageObject implements OnBoardingWizard {
    @StoredId
    private By poiDocumentWizard;
    @StoredId
    private By wizardMessage;
    @StoredId
    private By button;

    public OnBoardingWizardImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void assertOnPOI() {
        Assert.assertNotNull(waitUntilDisplayed(poiDocumentWizard));
    }

    @Override
    public void confirmMessage() {
        waitUntilDisplayed(wizardMessage, button).click();
    }
}