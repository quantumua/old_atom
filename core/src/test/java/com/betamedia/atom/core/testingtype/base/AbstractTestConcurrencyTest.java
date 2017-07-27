package com.betamedia.atom.core.testingtype.base;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author mbelyaev
 * @since 7/27/17
 */
public class AbstractTestConcurrencyTest {
    @Test
    public void threadLocalSoftAssertTest() throws InterruptedException, ExecutionException {
        /*Create thread pool that terminates threads immediately preventing reuse*/
        ExecutorService pool = new ThreadPoolExecutor(0, 10, 0, TimeUnit.SECONDS, new SynchronousQueue<>());
        /*Request SoftAssert from 10 separate threads*/
        List<SoftAssert> asserts = IntStream.range(0, 10).boxed()
                .map(i -> CompletableFuture.supplyAsync(AbstractTest::softAssert, pool))
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        /*Assert none of the retrieved SoftAsserts in the resulting collection are the same*/
        IntStream.range(0, asserts.size()).forEach(i ->
                IntStream.range(0, asserts.size()).filter(j -> j != i).forEach(j ->
                        Assert.assertNotEquals(asserts.get(i), asserts.get(j))
                )
        );
    }
}
