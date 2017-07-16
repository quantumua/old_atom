package com.betamedia.atom.core.testingtype.web;

import com.betamedia.atom.core.holders.AppContextHolder;
import com.betamedia.atom.core.holders.ThreadLocalBeansHolder;
import com.betamedia.atom.core.dsl.pages.factory.web.WEBPageFactoryImpl;
import com.betamedia.atom.core.dsl.templates.tp.TPTemplate;
import com.betamedia.atom.core.testingtype.base.AbstractClientTest;
import com.betamedia.atom.core.testingtype.base.AbstractEndToEndTest;

/**
 * @author Lilian Medina
 *         Date: 5/15/17.
 */
public class WEBEndToEndTest extends AbstractEndToEndTest<TPTemplate, WEBPageFactoryImpl> {
	@Override
    public final TPTemplate getOperationTemplate() {
        return ThreadLocalBeansHolder.getOperationsTemplateThreadLocal();
    }

    @Override
    public WEBPageFactoryImpl getPageFactory() {
        return AppContextHolder.getBean(WEBPageFactoryImpl.class);
    }
}