package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.assets;

import com.betamedia.qe.af.core.fwdataaccess.entities.ExpectedCfdAsset;

import java.util.List;

/**
 * Created by mbelyaev on 4/18/17.
 */
public interface Assets {
    Assets asset(String assetId, String assetName);

    List<String> assetNames();

    List<String> cfdAssetNames();

    boolean tryValidateAsset(String listName, String symbol, String tooltipName, String expectedCurrency);

    void switchToPanda();
}
