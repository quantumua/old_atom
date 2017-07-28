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
    public void threadLocalDifferentSoftAssertTest() throws InterruptedException, ExecutionException {
        int threadNum = 10;
        /*Create thread pool that terminates threads immediately preventing reuse*/
        ExecutorService pool = new ThreadPoolExecutor(0, threadNum, 0, TimeUnit.SECONDS, new SynchronousQueue<>());
        /*Request SoftAssert from [threadNum] separate threads*/
        List<CompletableFuture<SoftAssert>> futures = IntStream.range(0, threadNum).boxed()
                .map(i -> CompletableFuture.supplyAsync(AbstractTest::softAssert, pool))
                .collect(Collectors.toList());
        /*Wait until SoftAsserts are concurrently retrieved and then collect the results*/
        List<SoftAssert> asserts = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .thenApply(v -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList()))
                .get();
        /*Assert none of the retrieved SoftAsserts in the resulting collection are the same*/
        IntStream.range(0, asserts.size()).forEach(i ->
                IntStream.range(0, asserts.size()).filter(j -> j != i).forEach(j ->
                        Assert.assertNotSame(asserts.get(i), asserts.get(j))
                )
        );
    }

    @Test
    public void threadLocalSameSoftAssertTest() throws InterruptedException, ExecutionException {
        /*SoftAssert object is the same across a single thread*/
        SoftAssert assert1 = AbstractTest.softAssert();
        SoftAssert assert2 = AbstractTest.softAssert();
        Assert.assertSame(assert1, assert2);
    }
}
