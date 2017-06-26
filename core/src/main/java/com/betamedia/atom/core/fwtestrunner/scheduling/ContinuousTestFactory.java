package com.betamedia.atom.core.fwtestrunner.scheduling;

import com.betamedia.atom.core.fwtestrunner.TestInformation;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author mbelyaev
 * @since 6/19/17
 */
@FunctionalInterface
public interface ContinuousTestFactory {
    ContinuousTest get(Supplier<ListenableFuture<TestInformation>> testExecution, Optional<String> cronExpression);
}
