package com.betamedia.atom.core.fwtestrunner;

import java.util.UUID;

/**
 * @author mbelyaev
 * @since 6/16/17
 */
public interface TestInformationHandler {
    TestInformation get(UUID testId);

    void put(TestInformation testInformation);

}
