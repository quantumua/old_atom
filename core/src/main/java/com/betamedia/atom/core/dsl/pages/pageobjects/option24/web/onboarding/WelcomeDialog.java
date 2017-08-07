package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding;

/**
 * Created by Oleksandr Losiev on 5/23/17.
 */
public interface WelcomeDialog {
    void start();
    boolean isStartBtnDisplayed();
    String getCaption();
    String getStartButtonCaption();
}
