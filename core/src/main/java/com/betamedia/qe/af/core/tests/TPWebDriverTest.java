package com.betamedia.qe.af.core.tests;


import com.betamedia.qe.af.common.holder.AppContextHolder;
import com.betamedia.qe.af.core.pages.factory.tp.TPPageFactoryImpl;

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
