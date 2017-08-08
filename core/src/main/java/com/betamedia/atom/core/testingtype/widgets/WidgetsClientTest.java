package com.betamedia.atom.core.testingtype.widgets;

import com.betamedia.atom.core.dsl.pages.factory.widgets.WidgetsPageFactoryImpl;
import com.betamedia.atom.core.dsl.type.EnvironmentType;
import com.betamedia.atom.core.dsl.type.ProductType;
import com.betamedia.atom.core.holders.AppContextHolder;
import com.betamedia.atom.core.testingtype.annotations.TestConfigurationProperties;
import com.betamedia.atom.core.testingtype.base.AbstractClientTest;
import org.openqa.selenium.remote.BrowserType;

/**
 * @author vsnigur on 8/1/17
 */
@TestConfigurationProperties(
        productType = ProductType.TP,
        environment = EnvironmentType.QA,
        environmentUrl = "http://widgets-qa-eu.24option.com/TestPage1.html",
        browserType = BrowserType.FIREFOX)
public abstract class WidgetsClientTest extends AbstractClientTest<WidgetsPageFactoryImpl> {

    @Override
    public final WidgetsPageFactoryImpl getPageFactory() {
        return AppContextHolder.getBean(WidgetsPageFactoryImpl.class);
    }
}