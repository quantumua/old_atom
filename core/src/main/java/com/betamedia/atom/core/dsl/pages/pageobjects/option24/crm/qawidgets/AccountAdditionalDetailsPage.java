package com.betamedia.atom.core.dsl.pages.pageobjects.option24.crm.qawidgets;

import java.util.List;

import com.betamedia.atom.core.api.crm.form.entities.AccountAdditionalDetails;

public interface AccountAdditionalDetailsPage {

    void update(AccountAdditionalDetails info);
    boolean exists();
    boolean isUpdateBtnEnabled();
    void SelectBirthDateDay(AccountAdditionalDetails info);
    void SelectBirthDateMonth(AccountAdditionalDetails info);
    void SelectCountryOfBirth(AccountAdditionalDetails info);
    void SelectNationality(AccountAdditionalDetails info);
    void SelectAllData(AccountAdditionalDetails info);
	void clickDropDownButton();
	List<String> getBirthDayDataList();
	String getBirthDaySelectedItem();
	String getBirthDateDayElementColor();
	void selectBirthDayData();
	String getElementsBackground();
	void verifyTextDirectionElements (String textDirection);
}
