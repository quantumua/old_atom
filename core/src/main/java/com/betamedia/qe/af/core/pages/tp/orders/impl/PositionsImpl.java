package com.betamedia.qe.af.core.pages.tp.orders.impl;

import com.betamedia.qe.af.core.pages.AbstractPageObject;
import com.betamedia.qe.af.core.pages.tp.orders.Positions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mbelyaev on 3/27/17.
 */
public class PositionsImpl extends AbstractPageObject implements Positions {

    private By positionList = By.cssSelector(".bmPositionList.bmOptionList");

    public PositionsImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public List<String> get() {
        waitFor(positionList);
        return webDriver.findElement(positionList).findElements(By.tagName("li")).stream()
                .map(e -> e.findElement(By.className("bmCellContent")))
                .map(WebElement::getText)
                .map(id -> id.substring(id.lastIndexOf('#') + 1))
                .collect(Collectors.toList());
    }
}
