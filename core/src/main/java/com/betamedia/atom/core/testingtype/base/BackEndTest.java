package com.betamedia.atom.core.testingtype.base;

import com.betamedia.atom.core.dsl.templates.BackEndOperationsTemplate;

/**
 * Interface for tests that expose backend operations
 *
 * @author mbelyaev
 * @since 5/30/17
 */
public interface BackEndTest <T extends BackEndOperationsTemplate>{
    T operations();
}
