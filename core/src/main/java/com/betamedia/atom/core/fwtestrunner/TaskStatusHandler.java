package com.betamedia.atom.core.fwtestrunner;

import java.util.UUID;

/**
 * @author mbelyaev
 * @since 6/16/17
 */
public interface TaskStatusHandler {
    TaskStatus get(UUID taskId);

    void put(TaskStatus status);
}
