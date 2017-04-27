package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.assets.impl;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.assets.Assets;
import com.betamedia.qe.af.core.fwdataaccess.entities.ExpectedCfdAsset;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static javax.swing.text.html.HTML.Attribute.CLASS;
import static javax.swing.text.html.HTML.Tag.TR;

/**
 * Created by mbelyaev on 4/18/17.
 */
public class AssetsImpl extends AbstractPageObject implements Assets {
    private static final String ASSET_AVAILABLE_CLASS = "bmAvailable";

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
    @StoredId
    private By assetTooltipIcon;
    @StoredId
    private By assetTooltipContent;
    @StoredId
    private By requiredMarginRow;
    @StoredId
    private By swapRow;
    @StoredId
    private By underlyingAssetName;
    @StoredId
    private By assetNameTitle;
    @StoredId
    private By selectedAssetCurrency;
    @StoredId
    private By userDataCurrency;
    @StoredId
    private By firstRow;
    @StoredId
    private By secondRow;
    @StoredId
    private By toolTipName;


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
        waitUntil(() -> find(assetSelector).getAttribute(CLASS.toString()).contains(ASSET_AVAILABLE_CLASS));
        find(assetSelector).click();
        return this;
    }

    @Override
    public List<String> assetNames() {
        waitUntil(() -> !find(assetContainer).findElements(By.tagName(TR.toString())).isEmpty());
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

    @Override
    public void cfdValidateAssets(List<ExpectedCfdAsset> expectedAssets, String expectedCurrency) {
        inIFrame(() -> {
            validateAssets(expectedAssets, expectedCurrency);
            return null;
        }, pandaIframe);
    }

    @Override
    public void validateAssets(List<ExpectedCfdAsset> expectedAssets, String expectedCurrency) {
        Map<String, ExpectedCfdAsset> expectedAssetMap = createMapByAssetListName(expectedAssets);
        waitUntil(() -> !find(assetContainer).findElements(By.tagName(TR.toString())).isEmpty());
        find(assetContainer)
                .findElements(By.tagName(TR.toString()))
                .forEach(assetElement -> validateAsset(
                        assetElement,
                        expectedAssetMap.get(assetElement.findElement(assetName).getText()),
                        expectedCurrency
                ));
    }

    private Map<String, ExpectedCfdAsset> createMapByAssetListName(List<ExpectedCfdAsset> expectedAssets) {
        return expectedAssets.stream().collect(Collectors.toMap(ExpectedCfdAsset::getSymbol, asset -> asset));
    }

    //WIP
    private void validateAsset(WebElement assetElement, ExpectedCfdAsset expectedCfdAsset, String expectedCurrency) {
        Assert.assertNotNull(expectedCfdAsset);
        assetElement.click();
        makeActions().moveToElement(find(assetTooltipIcon)).perform();
        waitUntilDisplayed(assetTooltipContent);
        if (exists(assetTooltipContent, userDataCurrency)) {
            validateTooltipCurrency(userDataCurrency, expectedCurrency);
        } else {
            validateTooltipCurrency(selectedAssetCurrency, expectedCurrency);
        }
    }

    private void validateTooltipCurrency(By currencyType, String expectedCurrency) {
        validateSubRows(requiredMarginRow, currencyType, expectedCurrency);
        validateSubRows(swapRow, currencyType, expectedCurrency);
    }

    private void validateSubRows(By row, By currencyType, String expectedCurrency) {
        Assert.assertEquals(retrieveFromUpdatingElement(WebElement::getText,
                assetTooltipContent, row, firstRow, currencyType), expectedCurrency);
        Assert.assertEquals(retrieveFromUpdatingElement(WebElement::getText,
                assetTooltipContent, row, secondRow, currencyType), expectedCurrency);
    }


}
