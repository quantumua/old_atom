package com.betamedia.atom.core.testingtype.tp;


import com.betamedia.atom.core.dsl.pages.factory.tp.TPPageFactoryImpl;
import com.betamedia.atom.core.holders.AppContextHolder;
import com.betamedia.atom.core.testingtype.base.AbstractClientTest;
import com.betamedia.atom.core.testlink.TestLinkListener;
import org.testng.annotations.Listeners;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public abstract class TPClientTest extends AbstractClientTest<TPPageFactoryImpl> {

    @Override
    public final TPPageFactoryImpl getPageFactory() {
        return AppContextHolder.getBean(TPPageFactoryImpl.class);
    }

}
