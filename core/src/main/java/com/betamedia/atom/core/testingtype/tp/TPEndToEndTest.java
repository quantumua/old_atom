package com.betamedia.atom.core.testingtype.tp;

import com.betamedia.atom.core.dsl.pages.factory.tp.TPPageFactoryImpl;
import com.betamedia.atom.core.dsl.templates.tp.TPTemplate;
import com.betamedia.atom.core.holders.AppContextHolder;
import com.betamedia.atom.core.holders.ThreadLocalBeansHolder;
import com.betamedia.atom.core.testingtype.base.AbstractEndToEndTest;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/22/17.
 */
public abstract class TPEndToEndTest extends AbstractEndToEndTest<TPTemplate, TPPageFactoryImpl> {
    @Override
    public final TPTemplate getOperationTemplate() {
        return ThreadLocalBeansHolder.getOperationsTemplateThreadLocal();
    }

    @Override
    public final TPPageFactoryImpl getPageFactory() {
        return AppContextHolder.getBean(TPPageFactoryImpl.class);
    }
}