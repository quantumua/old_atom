package com.betamedia.qe.af.core.testingtype.tp;

import com.betamedia.qe.af.core.dsl.templates.tp.TPTemplate;
import com.betamedia.qe.af.core.holders.ThreadLocalBeansHolder;
import com.betamedia.qe.af.core.testingtype.base.AbstractBackEndTest;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/22/17.
 */
public abstract class TPBackEndTest extends AbstractBackEndTest<TPTemplate> {

    @Override
    public final TPTemplate getOperationTemplate() {
        return ThreadLocalBeansHolder.getOperationsTemplateThreadLocal();
    }
}
