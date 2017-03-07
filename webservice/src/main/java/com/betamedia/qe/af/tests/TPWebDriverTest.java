package com.betamedia.qe.af.tests;

import com.betamedia.qe.af.configuration.AppContextHolder;
import com.betamedia.qe.af.pages.factory.TPPageFactoryImpl;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public abstract class TPWebDriverTest extends AbstractWebDriverTest<TPPageFactoryImpl> {


    @Override
    public TPPageFactoryImpl getPageFactory() {
        return AppContextHolder.getBean(TPPageFactoryImpl.class);
    }

}
