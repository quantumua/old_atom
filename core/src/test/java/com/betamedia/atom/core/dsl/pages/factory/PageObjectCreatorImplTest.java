package com.betamedia.atom.core.dsl.pages.factory;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.fwdataaccess.repository.VersionedWebElementRepository;
import com.betamedia.atom.core.fwservices.webdriver.WebDriverFactory;
import com.betamedia.atom.core.holders.ThreadLocalBeansHolder;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.ReflectionTestUtils.getField;

/**
 * @author mbelyaev
 * @since 7/28/17
 */
public class PageObjectCreatorImplTest {

    @Test
    public void webDriverManagementTest() {
        /*Instances of different AbstractPageObject classes share the internal webDriver*/
        PageObjectCreatorImpl creator = initializePageObjectCreator();
        MockPageObject1 page1 = creator.getPage(MockPageObject1.class);
        MockPageObject2 page2 = creator.getPage(MockPageObject2.class);
        WebDriver page1Driver = (WebDriver) getField(page1, "webDriver");
        WebDriver page2Driver = (WebDriver) getField(page2, "webDriver");
        Assert.assertSame(page1Driver, page2Driver, "Page objects created by same PageObjectCreator do not share WebDriver instance");
    }

    @Test
    public void pageObjectManagementTest() {
        /*PageObjectCreatorImpl caches and shares single instance of page object per class across invocations*/
        PageObjectCreatorImpl creator = initializePageObjectCreator();
        MockPageObject1 page1 = creator.getPage(MockPageObject1.class);
        MockPageObject1 page2 = creator.getPage(MockPageObject1.class);
        Assert.assertSame(page1, page2, "Page object is not cached");
    }

    @Test
    public void concurrentCreatorsWebDriverManagementTest() throws ExecutionException, InterruptedException {
        /*PageObjectCreatorImpl instances in child threads should share preconfigured WebDriverFactory, but contain different WebDriver instances*/
        int threadNum = 10;
        WebDriverFactory mockFactory = mock(WebDriverFactory.class);
        when(mockFactory.get()).then(i -> mock(WebDriver.class));
        ThreadLocalBeansHolder.setWebDriverFactoryThreadLocal(mockFactory);
        ThreadLocalBeansHolder.setVersionedWebElementRepositoryThreadLocal(mock(VersionedWebElementRepository.class));
        ExecutorService pool = new ThreadPoolExecutor(0, threadNum, 0, TimeUnit.SECONDS, new SynchronousQueue<>());

        /*Instantiate PageObjectCreatorImpl from [threadNum] separate child threads*/
        List<CompletableFuture<PageObjectCreatorImpl>> futures = IntStream.range(0, threadNum).boxed()
                .map(i -> CompletableFuture.supplyAsync(PageObjectCreatorImpl::new, pool))
                .collect(Collectors.toList());
        /*Wait until PageObjectCreatorImpls are concurrently instantiated and then collect the results*/
        List<PageObjectCreatorImpl> creators = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .thenApply(v -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList()))
                .get();
        /*Verify that WebDriver has been requested from same factory [threadNum] times*/
        verify(mockFactory, times(threadNum)).get();
        /*Verify that WebDriver instances in created PageObjectCreatorImpls are not same instance*/
        IntStream.range(0, creators.size()).forEach(i ->
                IntStream.range(0, creators.size()).filter(j -> j != i).forEach(j ->
                        Assert.assertNotSame(
                                getField(creators.get(i), "driver"),
                                getField(creators.get(j), "driver"),
                                "Concurrently initialized PageObjectCreators share a WebDriver instance")
                )
        );
        /*Verify that WebDrivers in produced pages are also not same instance*/
        List<MockPageObject1> pageObjects = creators.stream().map(c -> c.getPage(MockPageObject1.class)).collect(Collectors.toList());
        IntStream.range(0, pageObjects.size()).forEach(i ->
                IntStream.range(0, pageObjects.size()).filter(j -> j != i).forEach(j ->
                        Assert.assertNotSame(
                                getField(pageObjects.get(i), "webDriver"),
                                getField(pageObjects.get(j), "webDriver"),
                                "Page objects produced by separate PageObjectCreators share a WebDriver instance")
                )
        );
    }

    private PageObjectCreatorImpl initializePageObjectCreator() {
        ThreadLocalBeansHolder.setVersionedWebElementRepositoryThreadLocal(mock(VersionedWebElementRepository.class));
        ThreadLocalBeansHolder.setWebDriverFactoryThreadLocal(() -> mock(WebDriver.class));
        return new PageObjectCreatorImpl();
    }

    private static class MockPageObject1 extends AbstractPageObject {
        public MockPageObject1(WebDriver webDriver) {
            super(webDriver);
        }
    }

    private static class MockPageObject2 extends AbstractPageObject {
        public MockPageObject2(WebDriver webDriver) {
            super(webDriver);
        }
    }
}
