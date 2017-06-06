package com.betamedia.atom.core.fwservices.webdriver;

/**
 * Created by mbelyaev on 3/31/17.
 */
public interface ParametrizedWebDriverFactoryProvider {
    String getType();

    ParametrizedWebDriverFactory get();
}
