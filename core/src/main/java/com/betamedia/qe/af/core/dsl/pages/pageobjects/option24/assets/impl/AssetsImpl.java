package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.assets.impl;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.assets.Assets;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mbelyaev on 4/18/17.
 */
public class AssetsImpl extends AbstractPageObject implements Assets {
    @StoredId
    private By assetSearchBox;
    @StoredId
    private By assetDropdown;
    @StoredId
    private By assetContainer;
    @StoredId
    private By assetName;
    @StoredId
    private By pandaIframe;

    public AssetsImpl(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public Assets asset(String assetId, String assetName) {
        By assetSelector = By.className("bmId" + assetId);
        waitUntilExists(assetSelector);
        if (!find(assetSelector).isDisplayed()) {
            if (!find(assetSearchBox).isDisplayed()) {
                waitUntilDisplayed(assetDropdown);
                find(assetDropdown).click();
            }
            waitUntilDisplayed(assetSearchBox);
            find(assetSearchBox).sendKeys(assetName);
        }
        waitUntil(() -> find(assetSelector).getAttribute("class").contains("bmAvailable"));
        find(assetSelector).click();
        return this;
    }

    @Override
    public List<String> assetNames() {
        waitUntilExists(assetContainer);
        return find(assetContainer)
                .findElements(By.tagName("tr"))
                .stream()
                .map(webElement -> webElement.findElement(assetName).getText())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> cfdAssetNames() {
        return inIFrame(this::assetNames, pandaIframe);
    }

}
