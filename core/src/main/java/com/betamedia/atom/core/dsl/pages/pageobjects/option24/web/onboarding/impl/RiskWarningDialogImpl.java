package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding.RiskWarningDialog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Oleksandr Losiev on 5/24/17.
 */
public class RiskWarningDialogImpl extends AbstractPageObject implements RiskWarningDialog {
    private static final int X_OFFSET = 0;
    private static final int Y_OFFSET = 10;

    @StoredId
    private By iAmOver18Statement;

    @StoredId
    private By updateButton;

    public RiskWarningDialogImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void accept() {
        waitUntilDisplayed(updateButton);
        /*
         *  Center of the risk warning acceptance statement is a hyperlink so we need to offset the click by few pixels down
         */
        makeActions().moveToElement(find(iAmOver18Statement),X_OFFSET, Y_OFFSET).click().perform();
        find(updateButton).click();
    }
    
    @Override
    public void waitForRiskWarning() {
    	waitUntilDisplayed(updateButton);    	
    }
}