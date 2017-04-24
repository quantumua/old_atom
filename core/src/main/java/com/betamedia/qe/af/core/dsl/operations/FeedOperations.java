package com.betamedia.qe.af.core.dsl.operations;

import com.betamedia.qe.af.core.environment.tp.EnvironmentDependent;
import com.betamedia.tp.api.feed.TickData;

/**
 * Created by mbelyaev on 3/27/17.
 */
public interface FeedOperations<T extends EnvironmentDependent> extends EnvironmentDependent {
    TickData injectFeed(String assetId, double price);

}
