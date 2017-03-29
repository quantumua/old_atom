package com.betamedia.qe.af.core.api.tp.operations;

import com.betamedia.tp.api.feed.TickData;
import com.betamedia.tp.api.model.order.Order;

/**
 * Created by mbelyaev on 3/27/17.
 */
public interface FeedOperations {
    TickData injectFeed(String assetId, double price);

}
