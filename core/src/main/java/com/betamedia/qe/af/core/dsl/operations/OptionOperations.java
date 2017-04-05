package com.betamedia.qe.af.core.dsl.operations;

import com.betamedia.tp.api.model.Option;
import com.betamedia.tp.api.model.enums.OptionType;

/**
 * Created by mbelyaev on 3/24/17.
 */
public interface OptionOperations {
    Option issue(String assetId, OptionType type, TagOperations.TagName tag);
}
