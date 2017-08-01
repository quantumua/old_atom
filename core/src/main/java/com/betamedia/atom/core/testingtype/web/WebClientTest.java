package com.betamedia.atom.core.testingtype.web;

import com.betamedia.atom.core.dsl.pages.factory.web.WebPageFactoryImpl;
import com.betamedia.atom.core.holders.AppContextHolder;
import com.betamedia.atom.core.testingtype.base.AbstractClientTest;

/**
 * @author vsnigur on 8/1/17
 */
public abstract class WebClientTest extends AbstractClientTest<WebPageFactoryImpl> {

    @Override
    public final WebPageFactoryImpl getPageFactory() {
        return AppContextHolder.getBean(WebPageFactoryImpl.class);
    }
}