package com.betamedia.qe.af.core.dsl.pages.factory;

import com.betamedia.qe.af.core.dsl.pages.type.ProductType;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface PageFactory<E extends ProductType> {

    E getType();

    void closeBrowser();

}
