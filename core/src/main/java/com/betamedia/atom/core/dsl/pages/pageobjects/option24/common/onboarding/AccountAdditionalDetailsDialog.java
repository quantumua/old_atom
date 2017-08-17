package com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetailsData;
import com.betamedia.atom.core.dsl.pages.extensions.LocalizationOperations;

import java.util.List;

public interface AccountAdditionalDetailsDialog extends LocalizationOperations {

    void update(AccountAdditionalDetails info);

    boolean exists();

    boolean isUpdateBtnEnabled();

    void selectBirthDateDay(String value);

    void selectBirthDateMonth(String value);

    void selectCountryOfBirth(String value);

    void selectNationality(String value);

    void selectAllFormElements(AccountAdditionalDetails info);

    void expandDropDownButton();

    List<String> getBirthDayDataList();

    String getBirthDaySelectedItem();

    String getBirthDateDayElementColor();

    void selectBirthDayData();

    String getElementsBackground();

    void verifySlideTranslation(AccountAdditionalDetailsData data, String language);

}
