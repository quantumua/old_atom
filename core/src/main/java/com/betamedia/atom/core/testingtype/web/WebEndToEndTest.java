package com.betamedia.atom.core.testingtype.web;

import com.betamedia.atom.core.dsl.pages.factory.web.WebPageFactoryImpl;
import com.betamedia.atom.core.dsl.templates.tp.TPTemplate;
import com.betamedia.atom.core.dsl.type.EnvironmentType;
import com.betamedia.atom.core.dsl.type.ProductType;
import com.betamedia.atom.core.holders.AppContextHolder;
import com.betamedia.atom.core.holders.ThreadLocalBeansHolder;
import com.betamedia.atom.core.testingtype.annotations.TestConfigurationProperties;
import com.betamedia.atom.core.testingtype.base.AbstractEndToEndTest;
import org.openqa.selenium.remote.BrowserType;

/**
 * @author Lilian Medina
 *         Date: 5/15/17.
 */
@TestConfigurationProperties(
        productType = ProductType.TP,
        environment = EnvironmentType.QA,
        environmentUrl = "https://qawww.24option.com/eu/trade/",
        browserType = BrowserType.FIREFOX)
public class WebEndToEndTest extends AbstractEndToEndTest<TPTemplate, WebPageFactoryImpl> {
	@Override
    public final TPTemplate getOperationTemplate() {
        return ThreadLocalBeansHolder.getOperationsTemplate();
    }

    @Override
    public WebPageFactoryImpl getPageFactory() {
        return AppContextHolder.getBean(WebPageFactoryImpl.class);
    }
}