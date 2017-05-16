package com.betamedia.qe.af.core.dsl.pages.pageobjects.option24.orders;

import com.betamedia.qe.af.core.fwdataaccess.entities.ExpectedCfdAsset;

/**
 * Created by mbelyaev on 4/28/17.
 */
public interface CfdPositions {
    void validateLatestPosition(String expectedName);
}
