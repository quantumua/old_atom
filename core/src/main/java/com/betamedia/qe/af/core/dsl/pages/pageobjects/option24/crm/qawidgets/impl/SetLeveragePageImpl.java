package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.impl;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.crm.qawidgets.SetLeveragePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vsnigur on 5/31/17.
 */
public class SetLeveragePageImpl extends AbstractPageObject implements SetLeveragePage {
    @StoredId
    private By setLeverageComboBox;

    public SetLeveragePageImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public List<String> getLeveragesList() {
        return in(waitUntilDisplayed(setLeverageComboBox)).getOptions()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Override
    public void assertSelectLeverageIsDisabled() {
        Assert.assertTrue(waitUntilDisplayed(setLeverageComboBox).isEnabled());
    }
}
