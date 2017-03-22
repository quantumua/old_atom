package com.betamedia.qe.af.core.tests.tp;

import com.betamedia.qe.af.common.holder.AppContextHolder;
import com.betamedia.qe.af.core.api.tp.TPTemplate;
import com.betamedia.qe.af.core.pages.factory.tp.TPPageFactoryImpl;
import com.betamedia.qe.af.core.tests.base.AbstractEndToEndTest;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/22/17.
 */
public class TPEndToEndTest extends AbstractEndToEndTest<TPTemplate, TPPageFactoryImpl> {
    @Override
    public TPTemplate getOperationTemplate() {
        return AppContextHolder.getBean(TPTemplate.class);
    }

    @Override
    public TPPageFactoryImpl getPageFactory() {
        return AppContextHolder.getBean(TPPageFactoryImpl.class);
    }
}
