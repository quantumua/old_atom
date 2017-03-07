package com.betamedia.qe.af.core.pages.factory;

import com.betamedia.qe.af.core.pages.type.AppType;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface PageFactory<E extends AppType> {

    E getType();

    void closeBrowser();

}
