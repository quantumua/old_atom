package com.betamedia.atom.core.testingtype.web;

import com.betamedia.atom.core.holders.AppContextHolder;
import com.betamedia.atom.core.dsl.pages.factory.web.WEBPageFactoryImpl;
import com.betamedia.atom.core.testingtype.base.AbstractClientTest;

/**
 * @author Lilian Medina
 *         Date: 5/15/17.
 */
public class WEBEndToEndTest extends AbstractClientTest<WEBPageFactoryImpl> {
   

    @Override
    public WEBPageFactoryImpl getPageFactory() {
        return AppContextHolder.getBean(WEBPageFactoryImpl.class);
    }
}
