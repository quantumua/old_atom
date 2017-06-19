package com.betamedia.atom.core.fwtestrunner.listeners;

import com.betamedia.atom.core.fwtestrunner.TestInformation;

import java.util.function.Consumer;

/**
 * @author mbelyaev
 * @since 6/16/17
 */
@FunctionalInterface
public interface TestTaskCompletionListener extends Consumer<TestInformation> {
}
