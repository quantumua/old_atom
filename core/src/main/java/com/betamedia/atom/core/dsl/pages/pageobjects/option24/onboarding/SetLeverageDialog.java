package com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding;

import java.util.List;

/**
 * Created by vsnigur on 5/29/17.
 */
public interface SetLeverageDialog {
    void selectLeverage();
    void okButton();
    void cancelButton();
    void assertCustomerLeverage();
    List<String> getLeveragesList();
    List<String> getLeveragesPickOptions();
    void expandList();
    void closeLeverageDialog();
}
