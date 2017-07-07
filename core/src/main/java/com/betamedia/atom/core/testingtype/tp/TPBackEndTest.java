package com.betamedia.atom.core.testingtype.tp;

import com.betamedia.atom.core.dsl.templates.tp.TPTemplate;
import com.betamedia.atom.core.holders.ThreadLocalBeansHolder;
import com.betamedia.atom.core.testingtype.base.AbstractBackEndTest;
import com.betamedia.atom.core.testlink.TestLinkListener;
import org.testng.annotations.Listeners;

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
