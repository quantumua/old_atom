package com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.impl;

import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding.SetLeverageDialog;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by vsnigur on 5/29/17.
 */
public class SetLeverageDialogImpl extends AbstractPageObject implements SetLeverageDialog {

    @StoredId
    private By setLeverageDialog;

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

    @StoredId
    private By leveragesPickOption;

    @StoredId
    private By closeLeverageDialog;

    public SetLeverageDialogImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void selectLeverage() {

    }

    @Override
    public void okButton() {
        waitUntilDisplayed(okButton).click();
    }

    @Override
    public void cancelButton() {
        waitUntilDisplayed(cancelButton).click();
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
    public List<String> getLeveragesPickOptions() {
        List<String> leverageOptions = new ArrayList<String>();
        waitUntilDisplayed(leveragesPickOption);
        for(WebElement webElement:findElements(leveragesPickOption)) {
            leverageOptions.add(webElement.getText());
        }
        return leverageOptions;
    }

    @Override
    public void expandList() {
        waitUntilDisplayed(leveragesButton).click();
    }

    @Override
    public void closeLeverageDialog() {
        waitUntilExists(closeLeverageDialog).click();
    }
}
