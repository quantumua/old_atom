package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.onboarding.impl;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.onboarding.SetLeverageDialog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by vsnigur on 5/29/17.
 */
public class SetLeverageDialogImpl extends AbstractPageObject implements SetLeverageDialog {
    @StoredId
    private By leverageComboBox;

    @StoredId
    private By okButton;

    @StoredId
    private By cancelButton;

    @StoredId
    private By leveragesButton;

    @StoredId
    private By leveragesList;

    public SetLeverageDialogImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void selectLeverage() {

    }

    @Override
    public void okButton() {

    }

    @Override
    public void cancelButton() {

    }

    @Override
    public void assertCustomerLeverage() {
        assertTrue(waitUntilDisplayed(leveragesButton).isDisplayed());
    }

    @Override
    public List<String> getLeveragesList() {
        List<String> leverageOptions = new ArrayList<String>();
        for(WebElement webElement:findElements(leveragesList)) {
            leverageOptions.add(webElement.getText());
        }
        return leverageOptions;
    }

    @Override
    public void expandList() {
        waitUntilDisplayed(leveragesButton).click();
    }
}
