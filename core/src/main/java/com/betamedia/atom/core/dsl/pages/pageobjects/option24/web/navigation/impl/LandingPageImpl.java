package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.navigation.impl;

import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.extensions.ScriptOperations;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.navigation.LandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by mbelyaev on 4/24/17.
 */
public class LandingPageImpl extends AbstractPageObject implements LandingPage, ScriptOperations {
    @StoredId
    private By homepage;
    @StoredId
    private By legalTermsOfUse;

    public LandingPageImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean isOnPage() {
        return waitUntilDisplayed(homepage) != null;
    }

    @Override
    public void legalTermsOfUse() {
        scrollIntoView(waitUntilDisplayed(legalTermsOfUse)).click();
    }
}
