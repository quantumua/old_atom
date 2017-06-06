package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.onboarding.impl;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.onboarding.LeveragePopup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Oleksandr Losiev on 5/24/17.
 */
public class LeveragePopupImpl extends AbstractPageObject implements LeveragePopup {

    @StoredId
    private By leverageDropdown;

    @StoredId
    private By leverageOption;

    @StoredId
    private By okButton;

    public LeveragePopupImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void setLeverage() {
        waitUntilDisplayed(leverageDropdown);
        find(leverageDropdown).click();
        waitUntilDisplayed(leverageOption);
        find(leverageOption).click();
        find(okButton).click();
    }

}
