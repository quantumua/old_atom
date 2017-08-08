package com.betamedia.atom.core.testlink.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that specifies a TestLink display id (e.g. CRM-8831) that corresponds to a test method.
 * It should be specified on test cases for TestLink integration.
 * Created by Oleksandr Losiev on 5/16/17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestLinkProperties {
    String displayId() default "";

    int buildId() default -1;

    int planId() default -1;
}
