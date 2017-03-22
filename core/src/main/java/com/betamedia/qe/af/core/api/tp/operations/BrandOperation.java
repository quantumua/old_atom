package com.betamedia.qe.af.core.api.tp.operations;

import com.betamedia.tp.api.model.Brand;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
public interface BrandOperation {
    Brand get();
    Brand get(String brandId);
    Brand getByName(String brandName);
}
