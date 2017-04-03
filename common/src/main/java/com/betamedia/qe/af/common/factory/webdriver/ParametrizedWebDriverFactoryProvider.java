package com.betamedia.qe.af.common.factory.webdriver;

/**
 * Created by mbelyaev on 3/31/17.
 */
public interface ParametrizedWebDriverFactoryProvider {
    String getType();

    ParametrizedWebDriverFactory get();
}
