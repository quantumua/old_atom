package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.assets.impl;

import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.assets.Assets;
import com.betamedia.qe.af.core.fwdataaccess.entities.ExpectedCfdAsset;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;
import java.util.stream.Collectors;

import static javax.swing.text.html.HTML.Attribute.CLASS;
import static javax.swing.text.html.HTML.Tag.TR;

/**
 * Created by mbelyaev on 4/18/17.
 */
public class AssetsImpl extends AbstractPageObject implements Assets {
    private static final Logger logger = LogManager.getLogger(AssetsImpl.class);

    private static final String ASSET_AVAILABLE_CLASS = "bmAvailable";
    private static final String ASSETID_CLASS_PREFIX = "bmId";
    private static final String ASSET_NOT_FOUND_MESSAGE = "Asset not found on page";

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
    @StoredId
    private By tooltipClose;


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
    public boolean tryValidateAsset(String listName, String symbol, String tooltipName, String expectedCurrency) {
        waitUntil(() -> !find(assetContainer).findElements(By.tagName(TR.toString())).isEmpty());
        List<WebElement> assetElement = find(assetContainer).findElements(byText(listName));
        if (assetElement.isEmpty()) {
            logger.debug(ASSET_NOT_FOUND_MESSAGE);
            Reporter.log(ASSET_NOT_FOUND_MESSAGE + '\n');
            return false;
        }
        validateFoundAsset(assetElement.get(0), listName, symbol, tooltipName, expectedCurrency);
        find(tooltipClose).click();
        return true;
    }

    private By byText(String listBidderName) {
        return By.xpath(".//td[text() = '" + listBidderName + "']");
    }

    @Override
    public void switchToPanda() {
        switchToFrame(pandaIframe);
    }

    private void validateFoundAsset(WebElement assetElement, String listName, String symbol, String tooltipName, String expectedCurrency) {
        assetElement.click();
        Assert.assertEquals(find(assetNameTitle).getText(),
                listName,
                "Bidding widget asset title mismatch");
        makeActions().moveToElement(find(assetTooltipIcon)).perform();
        waitUntilDisplayed(assetTooltipContent);
        Assert.assertEquals(find(assetTooltipContent, underlyingAssetName, toolTipName).getText(),
                symbol,
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
