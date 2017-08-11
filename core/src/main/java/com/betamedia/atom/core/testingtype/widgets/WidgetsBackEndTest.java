package com.betamedia.atom.core.testingtype.widgets;

import com.betamedia.atom.core.dsl.templates.tp.TPTemplate;
import com.betamedia.atom.core.dsl.type.ProductType;
import com.betamedia.atom.core.holders.ThreadLocalBeansHolder;
import com.betamedia.atom.core.testingtype.annotations.TestConfigurationProperties;
import com.betamedia.atom.core.testingtype.base.AbstractBackEndTest;

/**
 * @author vsnigur on 8/1/2017.
 */
@TestConfigurationProperties(productType = ProductType.TP)
public abstract class WidgetsBackEndTest extends AbstractBackEndTest<TPTemplate> {

    @Override
    public final TPTemplate getOperationTemplate() {
        return ThreadLocalBeansHolder.getOperationsTemplate();
    }
}
