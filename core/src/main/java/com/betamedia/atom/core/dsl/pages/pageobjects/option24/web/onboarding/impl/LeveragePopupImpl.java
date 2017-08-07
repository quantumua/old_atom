package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.LeveragePopup;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
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
