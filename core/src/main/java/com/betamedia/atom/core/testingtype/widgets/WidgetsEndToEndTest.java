package com.betamedia.atom.core.testingtype.widgets;

import com.betamedia.atom.core.dsl.pages.factory.widgets.WidgetsPageFactoryImpl;
import com.betamedia.atom.core.dsl.templates.tp.TPTemplate;
import com.betamedia.atom.core.dsl.type.EnvironmentType;
import com.betamedia.atom.core.dsl.type.ProductType;
import com.betamedia.atom.core.holders.AppContextHolder;
import com.betamedia.atom.core.holders.ThreadLocalBeansHolder;
import com.betamedia.atom.core.testingtype.annotations.TestConfigurationProperties;
import com.betamedia.atom.core.testingtype.base.AbstractEndToEndTest;
import org.openqa.selenium.remote.BrowserType;

/**
 * Created by vsnigur on 7/31/17.
 */
@TestConfigurationProperties(
        productType = ProductType.TP,
        environment = EnvironmentType.QA,
        environmentUrl = "http://widgets-qa-eu.24option.com/TestPage1.html",
        browserType = BrowserType.FIREFOX)
public class WidgetsEndToEndTest extends AbstractEndToEndTest<TPTemplate, WidgetsPageFactoryImpl> {

    @Override
    public final TPTemplate getOperationTemplate() {
        return ThreadLocalBeansHolder.getOperationsTemplate();
    }

    @Override
    public WidgetsPageFactoryImpl getPageFactory() {
        return AppContextHolder.getBean(WidgetsPageFactoryImpl.class);
    }
}
