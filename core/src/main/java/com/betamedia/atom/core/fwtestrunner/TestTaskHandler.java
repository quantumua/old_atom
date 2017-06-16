package com.betamedia.atom.core.fwtestrunner;

import java.util.UUID;

/**
 * @author mbelyaev
 * @since 6/16/17
 */
public interface TestTaskHandler {
    TestTask get(UUID taskId);

    void put(TestTask task);
}
