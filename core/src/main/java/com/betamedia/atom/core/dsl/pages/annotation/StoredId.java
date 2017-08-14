package com.betamedia.atom.core.dsl.pages.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/20/17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface StoredId {
    String value() default "";

    boolean localized() default false;
}
