package com.betamedia.atom.core.dsl.pages.utils;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author mbelyaev
 * @since 8/14/17
 */
public class PageObjectUtils {
    private static final Logger logger = LogManager.getLogger(PageObjectUtils.class);
    private static final String DOM_UPDATE_MESSAGE = "DOM updated when retrieving value";

    public static final String RTL_DIRECTION = "rtl";

    private PageObjectUtils() {
    }

    /**
     * Return a {@link List} of given page object's {@link By} fields
     * that match given {@link Predicate}s for {@link Field} and {@link StoredId}.
     *
     * @param fieldPredicate    predicate for field matching
     * @param storedIdPredicate predicate for StoredId properties matching
     * @param target            target page object
     */
    public static <T extends AbstractPageObject> List<By> getPageElements(Predicate<Field> fieldPredicate, Predicate<StoredId> storedIdPredicate, T target) {
        return getPageElements(Function.identity(), fieldPredicate, storedIdPredicate, target);
    }

    /**
     * Return a {@link List} of the result of mapping {@link Function} on given page object's {@link By} fields
     * that match given {@link Predicate}s for {@link Field} and {@link StoredId}.
     *
     * @param mapper            mapping function of {@link By}
     * @param fieldPredicate    predicate for field matching
     * @param storedIdPredicate predicate for StoredId properties matching
     * @param target            target page object
     */
    public static <T extends AbstractPageObject, U> List<U> getPageElements(Function<? super By, U> mapper, Predicate<Field> fieldPredicate, Predicate<StoredId> storedIdPredicate, T target) {
        return getStream(fieldPredicate, storedIdPredicate, mapper, target)
                .collect(Collectors.toList());
    }

    /**
     * Execute {@link Consumer} on given page object's {@link By} fields that match given {@link Predicate}s for
     * {@link Field} and {@link StoredId}.
     *
     * @param consumer          consumer of matching {@link By} objects
     * @param fieldPredicate    predicate for field matching
     * @param storedIdPredicate predicate for StoredId properties matching
     * @param target            target page object
     */
    public static <T extends AbstractPageObject> void forPageElements(Consumer<By> consumer, Predicate<Field> fieldPredicate, Predicate<StoredId> storedIdPredicate, T target) {
        forPageElements(Function.identity(), consumer, fieldPredicate, storedIdPredicate, target);
    }

    /**
     * Execute {@link Consumer} on the result of mapping {@link Function} on given page object's {@link By} fields
     * that match given {@link Predicate}s for {@link Field} and {@link StoredId}.
     *
     * @param mapper            mapping function of {@link By}
     * @param consumer          consumer of the result of mapping function
     * @param fieldPredicate    predicate for field matching
     * @param storedIdPredicate predicate for StoredId properties matching
     * @param target            target page object
     */
    public static <T extends AbstractPageObject, U> void forPageElements(Function<? super By, U> mapper, Consumer<U> consumer, Predicate<Field> fieldPredicate, Predicate<StoredId> storedIdPredicate, T target) {
        getStream(fieldPredicate, storedIdPredicate, mapper, target)
                .forEach(consumer);
    }

    /**
     * Execute {@link Consumer} of {@link WebElement} for given {@link WebElement} located with a {@link By} chain.<br/>
     * This is for scenarios when target {@link WebElement} is expected to update often, raising {@link StaleElementReferenceException}.<br/>
     * The method will try to execute {@link Consumer} for {@link AbstractPageObject#MAX_WAIT_SEC} seconds
     *
     * @param supplier element supplier function
     * @param consumer consumer to execute on located {@link WebElement}
     */
    public static void retryOnStale(Supplier<? extends WebElement> supplier, Consumer<? super WebElement> consumer) {
        long start = System.currentTimeMillis();
        do {
            try {
                consumer.accept(supplier.get());
            } catch (StaleElementReferenceException e) {
                logger.debug(DOM_UPDATE_MESSAGE, e);
                Reporter.log(DOM_UPDATE_MESSAGE + '\n');
            }
        } while (System.currentTimeMillis() - start < AbstractPageObject.MAX_WAIT_SEC * 1000);
    }

    /**
     * Execute {@link Function} of {@link WebElement} for given {@link WebElement} located with a {@link By} chain.<br/>
     * This is for scenarios when target {@link WebElement} is expected to update often, raising {@link StaleElementReferenceException}.<br/>
     * The method will try to execute {@link Function} for {@link AbstractPageObject#MAX_WAIT_SEC} seconds
     *
     * @param supplier element supplier function
     * @param function function to evaluate on located {@link WebElement}
     * @return the function result
     */
    public static <T> T retryOnStale(Supplier<? extends WebElement> supplier, Function<? super WebElement, T> function) {
        long start = System.currentTimeMillis();
        do {
            try {
                return function.apply(supplier.get());
            } catch (StaleElementReferenceException e) {
                logger.debug(DOM_UPDATE_MESSAGE, e);
                Reporter.log(DOM_UPDATE_MESSAGE + '\n');
            }
        } while (System.currentTimeMillis() - start < AbstractPageObject.MAX_WAIT_SEC * 1000);
        return null;
    }

    public static <T> T ignoringStale(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (StaleElementReferenceException e) {
            return null;
        }
    }

    public static WebElement elementIfVisible(WebElement element) {
        return element.isDisplayed() ? element : null;
    }


    private static <T extends AbstractPageObject, U> Stream<U> getStream(Predicate<Field> fieldPredicate, Predicate<StoredId> storedIdPredicate, Function<? super By, U> mapper, T target) {
        return Arrays.stream(target.getClass().getDeclaredFields())
                .filter(fieldPredicate.and(field -> By.class.isAssignableFrom(field.getType())))
                .filter(f -> storedIdPredicate.test(f.getAnnotation(StoredId.class)))
                .map(field -> getValue(field, target))
                .map(By.class::cast)
                .map(mapper);
    }

    private static <T extends AbstractPageObject> Object getValue(Field field, T target) {
        try {
            field.setAccessible(true);
            Object element = field.get(target);
            Reporter.log("Found element: " + element);
            return element;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("", e);
        }
    }

}
