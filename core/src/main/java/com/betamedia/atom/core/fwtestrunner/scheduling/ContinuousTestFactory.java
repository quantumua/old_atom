package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.betamedia.atom.core.fwtestrunner.TestInformation;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author mbelyaev
 * @since 6/19/17
 */
@FunctionalInterface
public interface ContinuousTestFactory {
    ContinuousTest get(TestInformation testInformation, Supplier<List<TestInformation>> testExecution);
}
