package com.betamedia.atom.core.dsl.pages.factory;

import com.betamedia.atom.core.dsl.pages.pageobjects.browser.BrowserOperations;
import com.betamedia.atom.core.dsl.type.ProductType;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface PageFactory<E extends ProductType> {

    E getType();

    BrowserOperations browser();

}
