package com.betamedia.atom.core.testingtype.web;

import com.betamedia.atom.core.dsl.templates.tp.TPTemplate;
import com.betamedia.atom.core.holders.ThreadLocalBeansHolder;
import com.betamedia.atom.core.testingtype.base.AbstractBackEndTest;

/**
 * @author vsnigur on 8/1/17
 */
public abstract class WebBackEndTest extends AbstractBackEndTest<TPTemplate> {

    @Override
    public final TPTemplate getOperationTemplate() {
        return ThreadLocalBeansHolder.getOperationsTemplateThreadLocal();
    }
}