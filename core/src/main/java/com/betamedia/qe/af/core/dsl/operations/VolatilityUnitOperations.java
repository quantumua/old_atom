package com.betamedia.qe.af.core.dsl.operations;

import com.betamedia.common.search.criteria.SearchCriteria;
import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;
import com.betamedia.tp.api.model.volatility.VolatilityUnit;

import java.util.List;

/**
 * Created by mbelyaev on 3/23/17.
 */
public interface VolatilityUnitOperations<T extends EnvironmentDependent> extends EnvironmentDependent {
    void generateForAsset(String assetId, double sigma, double factor, double threshold, double restriction);

    List<VolatilityUnit> getVolatilityUnits(SearchCriteria<VolatilityUnit> criteria);

    List<VolatilityUnit> createForAsset(String assetId);

    List<VolatilityUnit> getForAsset(String assetId);
}
