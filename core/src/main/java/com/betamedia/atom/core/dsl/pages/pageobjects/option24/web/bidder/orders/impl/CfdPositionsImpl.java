package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.bidder.orders.impl;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.bidder.orders.CfdPositions;
import org.openqa.selenium.*;

import javax.persistence.criteria.CriteriaBuilder;

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
    @StoredId
    private By accountBalance;

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
        return exists(openPositions, By.tagName(UL.toString()), assetName);
     }

    @Override
    public Integer countOfOpenedPosition(){
        Integer quantity = findElements(openPositions).size();
        return quantity;
    }

    @Override
    public String getAccountBalance(){
        return waitUntilDisplayed(accountBalance).getText();
    }
}
