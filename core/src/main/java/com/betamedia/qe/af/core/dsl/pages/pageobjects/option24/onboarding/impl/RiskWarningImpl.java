package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.onboarding.impl;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.onboarding.RiskWarning;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Oleksandr Losiev on 5/24/17.
 */
public class RiskWarningImpl extends AbstractPageObject implements RiskWarning {

    @StoredId
    private By iAmOver18CheckBox;

    @StoredId
    private By updateButton;

    public RiskWarningImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void accept() {
        waitUntilDisplayed(updateButton);
        makeActions().moveToElement(find(iAmOver18CheckBox)).click().build().perform();
        find(updateButton).click();
    }
}
