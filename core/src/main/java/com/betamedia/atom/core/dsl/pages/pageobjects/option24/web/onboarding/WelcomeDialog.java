package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding;

import com.betamedia.atom.core.fwdataaccess.repository.util.Language;

/**
 * Created by Oleksandr Losiev on 5/23/17.
 */
public interface WelcomeDialog {
    void start();
    boolean isStartBtnDisplayed();
    String getCaption();
    String getStartButtonCaption();

    void validateCaption(Language language);

    void validateStartButtonCaption(Language language);
}
