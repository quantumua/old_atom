package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.WelcomeBackMessage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
        waitUntilDisplayed(continueQuestionnaire).click();
    }

    public void continueQuestionnaireMayBe() {
        maybe(() -> find(continueQuestionnaire)).ifPresent(WebElement::click);
    }

    @Override
    public Boolean exists() {
        try {
            return waitUntil(() -> maybe(() -> find(continueQuestionnaire))
                    .orElseGet(() -> find(continueQuestionnaire))
                    .isDisplayed());
        } catch (Exception e) {
            return false;
        }
    }

}
