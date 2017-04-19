package com.betamedia.qe.af.core.dsl.operations.impl;

import com.betamedia.qe.af.core.connectors.tp.AFTPConnector;
import com.betamedia.qe.af.core.connectors.tp.feedgateway.AFFeedGatewayConnector;
import com.betamedia.qe.af.core.dsl.operations.AssetOperations;
import com.betamedia.qe.af.core.dsl.operations.TradingCalendarOperations;
import com.betamedia.qe.af.core.dsl.operations.VolatilityUnitOperations;
import com.betamedia.tp.api.model.Asset;
import com.betamedia.tp.api.model.enums.AssetStatus;
import com.betamedia.tp.api.model.enums.Market;
import com.betamedia.tp.api.model.scheduling.TradingCalendar;
import com.betamedia.tp.api.service.feedgateway.IFeedService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.testng.Assert.assertNotNull;

/**
 * This class is designed to facilitate the execution of common operations related to asset.
 * It can be used as a "building block" when writing integration tests.
 *
 * Created by mbelyaev on 3/23/17.
 *
 * @see Asset
 */
@Component
public class AssetOperationsImpl implements AssetOperations {
    private static final Logger logger = LogManager.getLogger(AssetOperationsImpl.class);

    @Autowired
    private AFTPConnector tpConnector;
    @Autowired
    private AFFeedGatewayConnector feedGatewayConnector;
    @Autowired
    private TradingCalendarOperations tradingCalendarOperations;
    @Autowired
    private VolatilityUnitOperations volatilityUnitOperations;

    /**
     * A method that creates and returns a default trading platform asset.
     */
    @Override
    public Asset get() {
        return create();
    }

    /**
     * A method to get asset by id.
     */
    @Override
    public Asset get(String id) {
        String internalId = id.replace("/", "");
        Asset asset = tpConnector.readById(Asset.class, internalId);
        assertNotNull(asset, "Asset id=" + internalId + " is not available in GS");
        return asset;
    }

    private Asset create() {
        Asset asset = new Asset();
        asset.setAssetName("QEAUTOTEST");
        asset.setMarket(Market.INDICES);
        TradingCalendar tradingCalendar = tradingCalendarOperations.get();
        asset.setTradingCalendar(tradingCalendar);
        int feedTimeout = 500;
        asset.setFeedTimeout(feedTimeout);
        asset.setFeedTimeoutAlert(feedTimeout + 1);
        asset.setPipSize(0.001);
        asset.setRoundFactor(5);
        asset.setStatus(AssetStatus.ACTIVE);
        asset = tpConnector.create(asset);
        logger.info("Asset '" + asset.getId() + "' created");
        volatilityUnitOperations.generateForAsset(asset.getId(), 0.002d, 1.0d, 1.0d, 1.0d);
        feedGatewayConnector.serviceProxy(IFeedService.class).setRoundFactor(asset.getId(), asset.getRoundFactor());
        return asset;
    }
}
