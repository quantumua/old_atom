package com.betamedia.atom.core.dsl.operations;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.tp.api.model.Asset;

/**
 * Created by mbelyaev on 3/23/17.
 */
public interface AssetOperations <T extends EnvironmentDependent> extends EnvironmentDependent {
    Asset get();

    Asset get(String id);

    Asset setTimeout(Asset asset, Integer timeout, Integer timeoutAlert);

}
