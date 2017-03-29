package com.betamedia.qe.af.core.api.tp.operations;

import com.betamedia.common.search.criteria.SearchCriteria;
import com.betamedia.tp.api.model.volatility.VolatilityUnit;

import java.util.List;

/**
 * Created by mbelyaev on 3/23/17.
 */
public interface VolatilityUnitOperations {
    void generateForAsset(String assetId, double sigma, double factor, double threshold, double restriction);

    List<VolatilityUnit> getVolatilityUnits(SearchCriteria<VolatilityUnit> criteria);

    List<VolatilityUnit> createForAsset(String assetId);

    List<VolatilityUnit> getForAsset(String assetId);
}
