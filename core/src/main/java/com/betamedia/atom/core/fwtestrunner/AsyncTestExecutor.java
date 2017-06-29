package com.betamedia.atom.core.fwtestrunner;

import org.springframework.util.concurrent.ListenableFuture;

import java.util.Optional;

/**
 * @author mbelyaev
 * @since 6/26/17
 */
public interface AsyncTestExecutor {
    ListenableFuture<TestInformation> submit(TestInformation supplier, Optional<String> tempJarPath);
}
