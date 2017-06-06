package com.betamedia.atom.core.dsl.operations;

import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.tp.api.model.Brand;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
public interface BrandOperations <T extends EnvironmentDependent> extends EnvironmentDependent{
    Brand get();

    Brand get(String brandId);

    Brand getByName(String brandName);
}
