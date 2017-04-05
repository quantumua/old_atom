package com.betamedia.qe.af.core.dsl.operations;

import com.betamedia.tp.api.feed.TickData;

/**
 * Created by mbelyaev on 3/27/17.
 */
public interface FeedOperations {
    TickData injectFeed(String assetId, double price);

}
