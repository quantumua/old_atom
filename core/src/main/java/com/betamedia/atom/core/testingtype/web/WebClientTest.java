package com.betamedia.atom.core.testingtype.web;

import com.betamedia.atom.core.dsl.pages.factory.web.WebPageFactoryImpl;
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
        environmentUrl = "https://qawww.24option.com/eu/trade/",
        browserType = BrowserType.FIREFOX)
public abstract class WebClientTest extends AbstractClientTest<WebPageFactoryImpl> {

    @Override
    public final WebPageFactoryImpl getPageFactory() {
        return AppContextHolder.getBean(WebPageFactoryImpl.class);
    }
}