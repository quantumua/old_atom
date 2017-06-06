package com.betamedia.atom.core.dsl.pages.pageobjects.option24.navigation;

import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by mbelyaev on 4/24/17.
 */
public class LandingPageImpl extends AbstractPageObject implements LandingPage {
    @StoredId
    private By homepage;

    public LandingPageImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean isOnPage() {
        return waitUntilDisplayed(homepage) != null;
    }
}
