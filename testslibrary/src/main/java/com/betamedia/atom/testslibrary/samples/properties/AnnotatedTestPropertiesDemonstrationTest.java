package com.betamedia.atom.testslibrary.samples.properties;

import com.betamedia.atom.core.dsl.type.EnvironmentType;
import com.betamedia.atom.core.dsl.type.ProductType;
import com.betamedia.atom.core.testingtype.annotations.TestConfigurationProperties;
import com.betamedia.atom.core.testingtype.web.WebClientTest;
import org.openqa.selenium.remote.BrowserType;

/**
 * Sample test class hierarchy that demonstrated {@link TestConfigurationProperties} functionality.
 * Provided properties are used to initialize ThreadLocal test components via {@link com.betamedia.atom.core.fwtestrunner.listeners.testng.impl.ContextInitializingListener}
 * Implementations of {@link com.betamedia.atom.core.fwtestrunner.environment.initializer.TestRunningEnvInitializer}
 * are expected to back off in case there is a component already present for current thread. This allows transparent configuration
 * override from webservice layer.
 *
 * @author mbelyaev
 * @since 8/7/17
 */
@TestConfigurationProperties(
        productType = ProductType.TP,
        environment = EnvironmentType.QA,
        environmentUrl = "https://qawww.24option.com/eu/trade/",
        browserType = BrowserType.FIREFOX)
public abstract class AnnotatedTestPropertiesDemonstrationTest extends WebClientTest {
}
