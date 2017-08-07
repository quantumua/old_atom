package com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding;

import java.util.List;

/**
 * Created by vsnigur on 5/31/17.
 */
public interface SetLeveragePage {
    List<String> getLeveragesList();
    void assertSelectLeverageIsDisabled();
}