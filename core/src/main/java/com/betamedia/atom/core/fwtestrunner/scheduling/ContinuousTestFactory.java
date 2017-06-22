package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.betamedia.atom.core.fwtestrunner.TestInformation;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author mbelyaev
 * @since 6/19/17
 */
@FunctionalInterface
public interface ContinuousTestFactory {
    ContinuousTest get(TestInformation testInformation, Function<Consumer<List<TestInformation>>, List<TestInformation>> testExecution);
}
