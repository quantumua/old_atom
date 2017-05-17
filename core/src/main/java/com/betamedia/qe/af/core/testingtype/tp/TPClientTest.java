package com.betamedia.qe.af.core.testingtype.tp;


import com.betamedia.qe.af.core.dsl.pages.factory.tp.TPPageFactoryImpl;
import com.betamedia.qe.af.core.holders.AppContextHolder;
import com.betamedia.qe.af.core.testingtype.base.AbstractClientTest;
import com.betamedia.qe.af.core.testlink.TestLinkListener;
import org.testng.annotations.Listeners;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
@Listeners(TestLinkListener.class)
public abstract class TPClientTest extends AbstractClientTest<TPPageFactoryImpl> {

    @Override
    public TPPageFactoryImpl getPageFactory() {
        return AppContextHolder.getBean(TPPageFactoryImpl.class);
    }

}
