package com.betamedia.qe.af.core.testingtype.tp;

import com.betamedia.qe.af.core.dsl.pages.factory.tp.TPPageFactoryImpl;
import com.betamedia.qe.af.core.dsl.templates.tp.TPTemplate;
import com.betamedia.qe.af.core.holders.AppContextHolder;
import com.betamedia.qe.af.core.holders.ThreadLocalBeansHolder;
import com.betamedia.qe.af.core.testingtype.base.AbstractEndToEndTest;
import com.betamedia.qe.af.core.testlink.TestLinkListener;
import org.testng.annotations.Listeners;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/22/17.
 */
@Listeners(TestLinkListener.class)
public class TPEndToEndTest extends AbstractEndToEndTest<TPTemplate, TPPageFactoryImpl> {
    @Override
    public TPTemplate getOperationTemplate() {
        return ThreadLocalBeansHolder.getOperationsTemplateThreadLocal();
    }

    @Override
    public TPPageFactoryImpl getPageFactory() {
        return AppContextHolder.getBean(TPPageFactoryImpl.class);
    }
}
