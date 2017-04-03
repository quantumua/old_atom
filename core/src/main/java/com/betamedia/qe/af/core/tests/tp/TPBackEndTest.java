package com.betamedia.qe.af.core.tests.tp;

import com.betamedia.qe.af.core.holder.AppContextHolder;
import com.betamedia.qe.af.core.api.tp.TPTemplate;
import com.betamedia.qe.af.core.tests.base.AbstractBackEndTest;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/22/17.
 */
public class TPBackEndTest extends AbstractBackEndTest<TPTemplate> {

    @Override
    public TPTemplate getOperationTemplate() {
        return AppContextHolder.getBean(TPTemplate.class);
    }
}
