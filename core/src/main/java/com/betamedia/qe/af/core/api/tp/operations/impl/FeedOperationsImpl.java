package com.betamedia.qe.af.core.api.tp.operations.impl;

import com.betamedia.qe.af.common.connectors.feedgateway.AFFeedGatewayConnector;
import com.betamedia.qe.af.core.api.tp.operations.FeedOperations;
import com.betamedia.tp.api.feed.TickData;
import com.betamedia.tp.api.service.feedgateway.IFeedService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mbelyaev on 3/27/17.
 */
@Component
public class FeedOperationsImpl implements FeedOperations {
    private static final Logger logger = LogManager.getLogger(FeedOperationsImpl.class);

    @Autowired
    private AFFeedGatewayConnector feedGatewayConnector;

    @Override
    public TickData injectFeed(String assetId, double price) {
        TickData tickData = create(assetId, price);
        feedGatewayConnector.serviceProxy(IFeedService.class).simulateTicks(tickData);
        return tickData;
    }

    private TickData create(String assetId, double price) {
        TickData tickData = TickData.createDummy();
        tickData.setAssetID(assetId);
        tickData.setSpot(price);
        return tickData;
    }
}