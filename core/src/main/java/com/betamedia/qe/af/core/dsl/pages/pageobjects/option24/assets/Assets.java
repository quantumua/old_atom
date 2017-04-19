package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.assets;

import java.util.List;

/**
 * Created by mbelyaev on 4/18/17.
 */
public interface Assets {
    Assets asset(String assetId, String assetName);

    List<String> assetNames();

    List<String> cfdAssetNames();
}
