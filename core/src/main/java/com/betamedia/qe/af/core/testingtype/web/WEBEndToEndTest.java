package com.betamedia.qe.af.core.testingtype.web;

import com.betamedia.qe.af.core.holders.AppContextHolder;
import com.betamedia.qe.af.core.dsl.templates.tp.TPTemplate;
import com.betamedia.qe.af.core.dsl.pages.factory.tp.TPPageFactoryImpl;
import com.betamedia.qe.af.core.dsl.pages.factory.web.WEBPageFactoryImpl;
import com.betamedia.qe.af.core.holders.ThreadLocalBeansHolder;
import com.betamedia.qe.af.core.testingtype.base.AbstractClientTest;
import com.betamedia.qe.af.core.testingtype.base.AbstractEndToEndTest;

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
