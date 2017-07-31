package com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.WelcomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Oleksandr Losiev on 5/23/17.
 */
public class WelcomePageImpl extends AbstractPageObject implements WelcomePage {

    @StoredId
    private By startButton;    

    public WelcomePageImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void start() {
        waitUntilDisplayed(startButton).click();
    }
    
    @Override
    public boolean isStartBtnDisplayed() {
        return maybe(() -> waitUntilDisplayed(startButton)).isPresent();
    }
    
}
