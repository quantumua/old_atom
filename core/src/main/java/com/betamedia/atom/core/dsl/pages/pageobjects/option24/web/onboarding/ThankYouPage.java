package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding;

/**
 * @author mbelyaev
 * @since 7/31/17
 */
public interface ThankYouPage {
    void doContinue();

    void startTrade();

    boolean waitForStartTrade();

    boolean startTradeExists();
}
