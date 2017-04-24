package com.betamedia.qe.af.core.dsl.operations.impl;

import com.betamedia.qe.af.core.connectors.tp.feedgateway.FWFeedGatewayConnector;
import com.betamedia.qe.af.core.dsl.operations.FeedOperations;
import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;
import com.betamedia.tp.api.feed.TickData;
import com.betamedia.tp.api.service.feedgateway.IFeedService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class is designed to facilitate the execution of common operations related to feed operations.
 * It can be used as a "building block" when writing integration tests.
 *
 * Created by mbelyaev on 3/27/17.
 * @see TickData
 */
public abstract class AbstractFeedOperations<T extends EnvironmentDependent> implements FeedOperations<T> {
    private static final Logger logger = LogManager.getLogger(AbstractFeedOperations.class);

    @Autowired
    private FWFeedGatewayConnector<T> feedGatewayConnector;

    @Override
    public TickData injectFeed(String assetId, double price) {
        TickData tickData = create(assetId, price);
        feedGatewayConnector.serviceProxy(IFeedService.class).simulateTicks(tickData);
        logger.info("Injected dummy feed for asset id=" + assetId + ", price=" + price);
        return tickData;
    }

    private TickData create(String assetId, double price) {
        TickData tickData = TickData.createDummy();
        tickData.setAssetID(assetId);
        tickData.setSpot(price);
        return tickData;
    }
}
