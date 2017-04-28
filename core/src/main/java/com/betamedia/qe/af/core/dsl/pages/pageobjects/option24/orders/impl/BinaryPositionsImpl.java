package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.orders.impl;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.orders.BinaryPositions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static javax.swing.text.html.HTML.Tag.LI;

/**
 * Created by mbelyaev on 3/27/17.
 */
public class BinaryPositionsImpl extends AbstractPageObject implements BinaryPositions {

    @StoredId
    private By positionList;
    @StoredId
    private By position;

    public BinaryPositionsImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public List<String> get() {
        waitUntilDisplayed(positionList);
        return find(positionList).findElements(By.tagName(LI.toString())).stream()
                .map(e -> e.findElement(position))
                .map(WebElement::getText)
                .map(id -> id.substring(id.lastIndexOf('#') + 1))
                .collect(Collectors.toList());
    }

    @Override
    public String getLatest() {
        return get().get(0);
    }

    @Override
    public WebElement getTradeRow(String positionDisplayId) {
        // TODO: Move implementation of getting trade rows by position id from legacy framework: com.scipio.tptesting.pom.def.getTradeRow(...)
        return null;
    }

    @Override
    public void checkWin(String displayedId) {

    }

    @Override
    public void checkLose(String displayedId) {

    }

}
