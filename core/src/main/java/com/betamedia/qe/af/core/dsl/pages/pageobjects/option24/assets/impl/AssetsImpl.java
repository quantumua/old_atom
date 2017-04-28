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
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static javax.swing.text.html.HTML.Attribute.CLASS;
import static javax.swing.text.html.HTML.Tag.TR;

/**
 * Created by mbelyaev on 4/18/17.
 */
public class AssetsImpl extends AbstractPageObject implements Assets {
    private static final String ASSET_AVAILABLE_CLASS = "bmAvailable";
    private static final String ASSETID_CLASS_PREFIX = "bmId";

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
        By assetSelector = By.className(ASSETID_CLASS_PREFIX + assetId);
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

    /**
     * WIP
     * TODO must confirm which expected data fields are matched to which page elements. <br/>
     * expectedCfdAsset is retrieved by {@link ExpectedCfdAsset#listBidderName} for each element of asset list on UI. <br/>
     * {@link ExpectedCfdAsset#tooltipName} is used to match "Underlying asset name" in tooltip <br/>
     * {@link ExpectedCfdAsset#listBidderName} is used to match asset title on bidding widget <br/>
     * Provided list of expected CFD assets does not match the product data - TEST WILL FAIL
     **/
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

    @Override
    public void forEachAssetClicked(Consumer<String> forSymbol) {
        waitUntil(() -> !find(assetContainer).findElements(By.tagName(TR.toString())).isEmpty());
        find(assetContainer)
                .findElements(By.tagName(TR.toString()))
                .stream()
                .peek(WebElement::click)
                .forEach(assetElement -> forSymbol.accept(assetElement.findElement(assetName).getText()));
    }

    @Override
    public void forEachCfdAssetClicked(Consumer<String> forSymbol) {
        inIFrame(() -> {
            forEachAssetClicked(forSymbol);
            return null;
        }, pandaIframe);
    }

    private Map<String, ExpectedCfdAsset> createMapByAssetListName(List<ExpectedCfdAsset> expectedAssets) {
        return expectedAssets.stream().collect(Collectors.toMap(ExpectedCfdAsset::getListBidderName, asset -> asset));
    }

    private void validateAsset(WebElement assetElement, ExpectedCfdAsset expectedCfdAsset, String expectedCurrency) {
        Assert.assertNotNull(expectedCfdAsset, "Expected asset data not found for " + assetElement.getText());
        assetElement.click();
        Assert.assertEquals(find(assetNameTitle).getText(),
                expectedCfdAsset.getSymbol(),
                "Bidding widget asset title mismatch");
        makeActions().moveToElement(find(assetTooltipIcon)).perform();
        waitUntilDisplayed(assetTooltipContent);
        Assert.assertEquals(find(assetTooltipContent, underlyingAssetName, toolTipName).getText(),
                expectedCfdAsset.getTooltipName(),
                "Asset tooltip name mismatch");
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
