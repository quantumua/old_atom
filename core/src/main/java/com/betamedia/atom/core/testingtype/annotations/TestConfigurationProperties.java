package com.betamedia.atom.core.testingtype.annotations;


import com.betamedia.atom.core.dsl.type.EnvironmentType;
import com.betamedia.atom.core.dsl.type.ProductType;

import java.lang.annotation.*;

/**
 *
 * @author mbelyaev
 * @since 8/7/17
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface TestConfigurationProperties {

    ProductType productType();

    EnvironmentType environment() default EnvironmentType.QA;

    String environmentUrl() default "";

    String browserType() default "";

    String remoteDriverUrl() default "";

    String implVersion() default "";

    String revisionDate() default "";

}
