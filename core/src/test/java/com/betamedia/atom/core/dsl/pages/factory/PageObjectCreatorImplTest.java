package com.betamedia.atom.core.dsl.pages.factory;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.localization.LocalizationStorage;
import com.betamedia.atom.core.fwdataaccess.entities.FindBy;
import com.betamedia.atom.core.fwdataaccess.repository.VersionedWebElementRepository;
import com.betamedia.atom.core.fwdataaccess.repository.util.Language;
import com.betamedia.atom.core.fwservices.webdriver.WebDriverFactory;
import com.betamedia.atom.core.holders.ThreadLocalBeansHolder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        ThreadLocalBeansHolder.setWebDriverFactory(mockFactory);
        ThreadLocalBeansHolder.setVersionedWebElementRepository(mock(VersionedWebElementRepository.class));
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

    @Test
    public void hierarchicalPageObjectCreationTest() {
        /*Instantiate Page Object that is inherited from another Page Object that declares its own fields*/
        VersionedWebElementRepository repoMock = mock(VersionedWebElementRepository.class);
        ThreadLocalBeansHolder.setVersionedWebElementRepository(repoMock);
        ThreadLocalBeansHolder.setWebDriverFactory(() -> mock(WebDriver.class));
        PageObjectCreatorImpl pageObjectCreator = new PageObjectCreatorImpl();
        when(repoMock.get(AbstractMockPageObject.class.getSimpleName(), "inheritedField")).thenReturn(new FindBy("ID", "mockLocator"));
        when(repoMock.get(AbstractMockPageObjectImpl.class.getSimpleName(), "declaredField")).thenReturn(new FindBy("ID", "mockLocator"));
        AbstractMockPageObjectImpl page = pageObjectCreator.getPage(AbstractMockPageObjectImpl.class);
        Assert.assertNotNull(page.inheritedField);
        Assert.assertNotNull(page.declaredField);
        Assert.assertNull(page.notALocator);
    }

    @Test
    public void test() {
        Map<Language, String> field1Localizations = new HashMap<>();
        Map<Language, String> field2Localizations = new HashMap<>();
        String english1 = "English 1";
        String german1 = "German 1";
        String english2 = "English 2";
        String german2 = "German 2";
        field1Localizations.put(Language.ENGLISH, english1);
        field1Localizations.put(Language.GERMAN, german1);
        field2Localizations.put(Language.ENGLISH, english2);
        field2Localizations.put(Language.GERMAN, german2);
        ThreadLocalBeansHolder.setWebDriverFactory(() -> null);
        ThreadLocalBeansHolder.setVersionedWebElementRepository((name, id) -> {
            if ("LocalizedPage1".equals(name) && "field".equals(id)) return new FindBy("ID", "pageField1");
            else if ("LocalizedPage2".equals(name) && "field".equals(id)) return new FindBy("ID", "pageField2");
            else return null;
        });
        ThreadLocalBeansHolder.setVersionedLocalizationRepository((name, id) -> {
            if ("LocalizedPage1".equals(name) && "field".equals(id)) return field1Localizations;
            else if ("LocalizedPage2".equals(name) && "field".equals(id)) return field2Localizations;
            else return null;
        });
        PageObjectCreator creator = new PageObjectCreatorImpl();
        LocalizedPage1 page1 = creator.getPage(LocalizedPage1.class);
        LocalizedPage2 page2 = creator.getPage(LocalizedPage2.class);
        By field1 = page1.getField();
        By field2 = page2.getField();
        LocalizationStorage localizations1 = (LocalizationStorage) getField(page1, "localizations");
        LocalizationStorage localizations2 = (LocalizationStorage) getField(page2, "localizations");
        Assert.assertNotSame(localizations1, localizations2);
        Assert.assertEquals(localizations1.get(field1).get(Language.ENGLISH), english1);
        Assert.assertEquals(localizations1.get(field1).get(Language.GERMAN), german1);
        Assert.assertEquals(localizations2.get(field2).get(Language.ENGLISH), english2);
        Assert.assertEquals(localizations2.get(field2).get(Language.GERMAN), german2);
        Assert.assertSame(localizations1.get(field2), Collections.emptyMap());
        Assert.assertSame(localizations2.get(field1), Collections.emptyMap());
    }

    private PageObjectCreatorImpl initializePageObjectCreator() {
        ThreadLocalBeansHolder.setVersionedWebElementRepository(mock(VersionedWebElementRepository.class));
        ThreadLocalBeansHolder.setWebDriverFactory(() -> mock(WebDriver.class));
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

    private static abstract class AbstractMockPageObject extends AbstractPageObject {

        @StoredId
        protected By inheritedField;
        protected Object notALocator;

        protected AbstractMockPageObject(WebDriver webDriver) {
            super(webDriver);
        }

    }

    private static class AbstractMockPageObjectImpl extends AbstractMockPageObject {

        @StoredId
        private By declaredField;
        public AbstractMockPageObjectImpl(WebDriver webDriver) {
            super(webDriver);
        }

    }

    public static class LocalizedPage1 extends AbstractPageObject {
        @StoredId(localized = true)
        private By field;

        public LocalizedPage1(WebDriver webDriver) {
            super(webDriver);
        }

        private By getField() {
            return field;
        }
    }

    public static class LocalizedPage2 extends AbstractPageObject {
        @StoredId(localized = true)
        private By field;

        public LocalizedPage2(WebDriver webDriver) {
            super(webDriver);
        }

        private By getField() {
            return field;
        }
    }

}
