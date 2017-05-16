package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.orders.impl;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.orders.CfdPositions;
import com.betamedia.qe.af.core.fwdataaccess.entities.ExpectedCfdAsset;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static javax.swing.text.html.HTML.Tag.UL;

/**
 * Created by mbelyaev on 4/28/17.
 */
public class CfdPositionsImpl extends AbstractPageObject implements CfdPositions {

    @StoredId
    private By openPositions;
    @StoredId
    private By assetName;

    public CfdPositionsImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void validateLatestPosition(String expectedName) {
        waitUntilDisplayed(openPositions);
        WebElement latestPosition = find(openPositions, By.tagName(UL.toString()), assetName);
        Assert.assertEquals(latestPosition.getText(), expectedName);
    }
}
