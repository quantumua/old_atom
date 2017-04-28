package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.orders.impl;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.orders.CfdPositions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
    public String getSymbolForLatest() {
        waitUntilDisplayed(openPositions);
        return find(openPositions).findElements(By.tagName(UL.toString())).stream()
                .map(e -> e.findElement(assetName))
                .map(WebElement::getText)
                .findFirst()
                .orElse("");
    }
}
