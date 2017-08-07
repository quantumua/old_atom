package com.betamedia.atom.core.product;

import com.betamedia.atom.core.dsl.type.ProductType;

/**
 * @author Maksym Tsybulskyy
 *         Date: 7/3/17.
 */
public interface TPProduct extends ProductDependent{

    @Override
    default ProductType getType(){
        return ProductType.TP;
    }
}
