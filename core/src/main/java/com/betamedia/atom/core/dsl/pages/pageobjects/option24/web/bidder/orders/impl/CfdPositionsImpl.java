package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.bidder.orders.impl;

import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.bidder.orders.CfdPositions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import static com.betamedia.atom.core.testingtype.base.AbstractTest.softAssert;

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
        softAssert().assertEquals(latestPosition.getText(), expectedName);
    }

    @Override
    public boolean isAnyPositionOpened() {
        waitUntilDisplayed(openPositions);
        try{
            return find(openPositions, By.tagName(UL.toString()), assetName).isDisplayed();
        }catch (NoSuchElementException e) {
            getLogger().debug(EXPECTED_LOOKUP_FAILURE_MESSAGE, e);
            Reporter.log(EXPECTED_LOOKUP_FAILURE_MESSAGE + '\n');
            return false;
        }
     }
}
