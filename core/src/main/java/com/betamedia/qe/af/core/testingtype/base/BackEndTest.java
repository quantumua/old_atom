package com.betamedia.qe.af.core.testingtype.base;

import com.betamedia.qe.af.core.dsl.templates.BackEndOperationsTemplate;

/**
 * Interface for tests that expose backend operations
 *
 * @author mbelyaev
 * @since 5/30/17
 */
public interface BackEndTest <T extends BackEndOperationsTemplate>{
    T operations();
}
