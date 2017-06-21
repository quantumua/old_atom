package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.betamedia.atom.core.fwtestrunner.TestInformation;
import com.betamedia.atom.core.fwtestrunner.listeners.TestCompletionListener;

import java.util.List;
import java.util.function.Function;

/**
 * @author mbelyaev
 * @since 6/19/17
 */
@FunctionalInterface
public interface ContinuousTestFactory {
    ContinuousTest get(TestInformation testInformation, Function<TestCompletionListener, List<TestInformation>> testExecution);
}
