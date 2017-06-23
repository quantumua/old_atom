package com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.WelcomeBackMessage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by vsnigur on 6/8/17.
 */
public class WelcomeBackMessageImpl extends AbstractPageObject implements WelcomeBackMessage {
    @StoredId
    private By continueQuestionnaire;

    public WelcomeBackMessageImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void continueQuestionnaire() {
        waitUntilExists(continueQuestionnaire).click();
    }

    @Override
    public Boolean exists() {
        try {
            return waitUntilExists(continueQuestionnaire).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
