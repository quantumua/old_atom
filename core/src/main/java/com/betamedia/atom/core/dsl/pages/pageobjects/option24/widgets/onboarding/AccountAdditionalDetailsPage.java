package com.betamedia.atom.core.dsl.pages.pageobjects.option24.widgets.onboarding;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;
import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetailsData;
import java.util.List;

public interface AccountAdditionalDetailsPage {

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

    void verifyTextDirectionElements(String textDirection);

    void verifySlideTranslation(AccountAdditionalDetailsData data);
}