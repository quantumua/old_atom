package com.betamedia.qe.af.core.api.tp.operations;

import com.betamedia.tp.api.feed.TickData;
import com.betamedia.tp.api.model.Asset;

/**
 * Created by mbelyaev on 3/23/17.
 */
public interface AssetOperations {
    Asset get();

    Asset get(String id);

}
