package com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;

public interface AccountAdditionalDetailsPage {

    void update(AccountAdditionalDetails info);
    boolean exists();
    boolean isUpdateBtnEnabled();
    void SelectBirthDateDay(AccountAdditionalDetails info);
    void SelectBirthDateMonth(AccountAdditionalDetails info);
    void SelectBirthDateYear(AccountAdditionalDetails info);
    void SelectCountryOfBirth(AccountAdditionalDetails info);
    void SelectNationality(AccountAdditionalDetails info);
    void SelectAllData(AccountAdditionalDetails info);

}
