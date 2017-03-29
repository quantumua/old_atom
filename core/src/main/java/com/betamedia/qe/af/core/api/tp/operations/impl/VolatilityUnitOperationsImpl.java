package com.betamedia.qe.af.core.api.tp.operations.impl;

import com.betamedia.common.search.SortOrder;
import com.betamedia.common.search.Sorting;
import com.betamedia.common.search.criteria.SearchCriteria;
import com.betamedia.qe.af.common.connectors.tp.AFTPConnector;
import com.betamedia.qe.af.core.api.tp.operations.VolatilityUnitOperations;
import com.betamedia.tp.api.model.volatility.VolatilityUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by mbelyaev on 3/23/17.
 */
@Component
public class VolatilityUnitOperationsImpl implements VolatilityUnitOperations {
    private static final Logger logger = LogManager.getLogger(VolatilityUnitOperationsImpl.class);

    @Autowired
    private AFTPConnector tpConnector;

    @Override
    public void generateForAsset(String assetId, double sigma, double factor, double threshold, double restriction) {
        IntStream.range(1, 8).forEach(day ->
                IntStream.range(0, 24).forEach(hour -> {
                    List<VolatilityUnit> existingVolatilityUnits = getVolatilityUnits(forAssetDayHour(assetId, day, hour));
                    if (existingVolatilityUnits.isEmpty()) {
                        create(assetId, day, hour, sigma, factor, threshold, restriction);
                    } else {
                        logger.debug("Volatility unit '" + assetId + " " + day + " " + hour + "' already exists");
                    }
                }));
    }

    @Override
    public List<VolatilityUnit> createForAsset(String assetId) {
        return Collections.singletonList(
                create(assetId,
                        LocalDate.now().getDayOfWeek().getValue(),
                        LocalTime.now().getHour(),
                        0.2d,
                        2d,
                        1d,
                        1d
                ));
    }

    @Override
    public List<VolatilityUnit> getForAsset(String assetId) {
        return getVolatilityUnits(forAssetDayHour(assetId,
                LocalDate.now().getDayOfWeek().getValue(),
                LocalTime.now().getHour()));
    }

    @Override
    public List<VolatilityUnit> getVolatilityUnits(SearchCriteria<VolatilityUnit> criteria) {
        Sorting<VolatilityUnit> sorting = new Sorting<>(VolatilityUnit.EP_DAY, SortOrder.Asc);
        List<VolatilityUnit> units = tpConnector.readMultiple(criteria, null, sorting).getContent();
        units.sort((unit1, unit2) -> {
            int dayCompare = unit1.getDay().compareTo(unit2.getDay());
            if (dayCompare != 0) {
                return dayCompare;
            }
            return unit1.getHour().compareTo(unit2.getHour());
        });
        return units;
    }

    private VolatilityUnit create(String id,
                                  int day,
                                  int hour,
                                  double sigma,
                                  double factor,
                                  double threshold,
                                  double restriction) {
        VolatilityUnit volatilityUnit = new VolatilityUnit();
        volatilityUnit.setAssetId(id);
        volatilityUnit.setCreatedBy("Automatic test");
        volatilityUnit.setDay(day);
        volatilityUnit.setHour(hour);
        volatilityUnit.setSigma(sigma);
        volatilityUnit.setFactor(factor);
        volatilityUnit.setThreshold(threshold);
        volatilityUnit.setRestriction99(restriction);
        return tpConnector.create(volatilityUnit);
    }

    private SearchCriteria<VolatilityUnit> forAssetDayHour(String assetId, long day, long hour) {
        return new SearchCriteria<>(VolatilityUnit.class)
                .and(VolatilityUnit.EP_ASSET_ID.equalsTo(assetId))
                .and(VolatilityUnit.EP_DAY.equalsTo(day))
                .and(VolatilityUnit.EP_HOUR.equalsTo(hour));
    }

}
