package com.betamedia.atom.core.dsl.pages.extensions;

import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import org.openqa.selenium.By;
import org.testng.Reporter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.betamedia.atom.core.dsl.pages.extensions.FieldOperations.Utils.getStream;


/**
 * @author mbelyaev
 * @since 8/17/17
 */
public interface FieldOperations {

    /**
     * Return a {@link List} of given page object's {@link By} fields
     * that match given {@link Predicate}s for {@link Field} and {@link StoredId}.
     *
     * @param fieldPredicate    predicate for field matching
     * @param storedIdPredicate predicate for StoredId properties matching
     * @param target            target page object
     */
    default <T> List<By> getPageElements(Predicate<Field> fieldPredicate, Predicate<StoredId> storedIdPredicate, T target) {
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
    default <T, U> List<U> getPageElements(Function<? super By, U> mapper, Predicate<Field> fieldPredicate, Predicate<StoredId> storedIdPredicate, T target) {
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
    default <T> void forPageElements(Consumer<By> consumer, Predicate<Field> fieldPredicate, Predicate<StoredId> storedIdPredicate, T target) {
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
    default <T, U> void forPageElements(Function<? super By, U> mapper, Consumer<U> consumer, Predicate<Field> fieldPredicate, Predicate<StoredId> storedIdPredicate, T target) {
        getStream(fieldPredicate, storedIdPredicate, mapper, target)
                .forEach(consumer);
    }

    abstract class Utils {
        static <T, U> Stream<U> getStream(Predicate<Field> fieldPredicate, Predicate<StoredId> storedIdPredicate, Function<? super By, U> mapper, T target) {
            return Arrays.stream(target.getClass().getDeclaredFields())
                    .filter(field -> By.class.isAssignableFrom(field.getType()))
                    .filter(fieldPredicate)
                    .filter(f -> storedIdPredicate.test(f.getAnnotation(StoredId.class)))
                    .map(field -> getValue(field, target))
                    .map(By.class::cast)
                    .map(mapper);
        }

        static <T> Object getValue(Field field, T target) {
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
}
