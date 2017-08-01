package com.betamedia.atom.core.testingtype.widgets;

import com.betamedia.atom.core.dsl.pages.factory.widgets.WidgetsPageFactoryImpl;
import com.betamedia.atom.core.holders.AppContextHolder;
import com.betamedia.atom.core.testingtype.base.AbstractClientTest;

/**
 * @author vsnigur on 8/1/17
 */
public abstract class WidgetsClientTest extends AbstractClientTest<WidgetsPageFactoryImpl> {

    @Override
    public final WidgetsPageFactoryImpl getPageFactory() {
        return AppContextHolder.getBean(WidgetsPageFactoryImpl.class);
    }
}