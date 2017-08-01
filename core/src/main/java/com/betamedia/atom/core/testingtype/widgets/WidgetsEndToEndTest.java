package com.betamedia.atom.core.testingtype.widgets;

import com.betamedia.atom.core.dsl.pages.factory.widgets.WidgetsPageFactoryImpl;
import com.betamedia.atom.core.dsl.templates.tp.TPTemplate;
import com.betamedia.atom.core.holders.AppContextHolder;
import com.betamedia.atom.core.holders.ThreadLocalBeansHolder;
import com.betamedia.atom.core.testingtype.base.AbstractEndToEndTest;

/**
 * Created by vsnigur on 7/31/17.
 */
public class WidgetsEndToEndTest extends AbstractEndToEndTest<TPTemplate, WidgetsPageFactoryImpl> {

    @Override
    public final TPTemplate getOperationTemplate() {
        return ThreadLocalBeansHolder.getOperationsTemplateThreadLocal();
    }

    @Override
    public WidgetsPageFactoryImpl getPageFactory() {
        return AppContextHolder.getBean(WidgetsPageFactoryImpl.class);
    }
}
