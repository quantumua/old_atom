package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.bidder.assets;

import java.util.List;

/**
 * Created by mbelyaev on 4/18/17.
 */
public interface Assets {

    /**
     * Find and click asset in list
     */
    Assets asset(String assetId, String assetName);

    /**
     * @return list of asset names in the asset selector widget
     */
    List<String> assetNames();


    /**
     * Switches to Panda CFD iFrame to get data and then returns to default context
     * @return list of asset names in the asset selector widget
     */
    List<String> cfdAssetNames();

    /**
     * Attempts to find the corresponding asset on product page and validate its info. <br/>
     *
     * @param listName         expected name in asset list; used to locate the asset
     * @param symbol           expected symbol
     * @param tooltipName      expected tooltip
     * @param expectedCurrency user currency
     * @return <code>true</code> if asset was found on page and validated,<br/> <code>false</code> otherwise (failure to locate asset is expected and is not a test failure)
     */
    boolean tryValidateAsset(String listName, String symbol, String tooltipName, String expectedCurrency);

    /**
     * Switches context to Panda CFD iFrame
     */
    void switchToPanda();

    void leavePandaFrame();
}
