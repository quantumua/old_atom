package com.betamedia.atom.core.testingtype.web;

import com.betamedia.atom.core.dsl.templates.tp.TPTemplate;
import com.betamedia.atom.core.dsl.type.ProductType;
import com.betamedia.atom.core.holders.ThreadLocalBeansHolder;
import com.betamedia.atom.core.testingtype.annotations.TestConfigurationProperties;
import com.betamedia.atom.core.testingtype.base.AbstractBackEndTest;

/**
 * @author vsnigur on 8/1/17
 */
@TestConfigurationProperties(productType = ProductType.TP)
public abstract class WebBackEndTest extends AbstractBackEndTest<TPTemplate> {

    @Override
    public final TPTemplate getOperationTemplate() {
        return ThreadLocalBeansHolder.getOperationsTemplate();
    }
}