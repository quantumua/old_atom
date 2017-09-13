package com.betamedia.atom.core.dsl.pages.pageobjects.option24.common.onboarding;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.extensions.CssOperations;
import com.betamedia.atom.core.dsl.pages.extensions.FieldOperations;
import com.betamedia.atom.core.dsl.pages.extensions.LocalizationOperations;
import com.betamedia.atom.core.dsl.pages.extensions.ScriptOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by lartemyev on 8/7/17.
 */
public abstract class AbstractAccountAdditionalDetails extends AbstractPageObject implements AccountAdditionalDetailsDialog, CssOperations, FieldOperations, ScriptOperations, LocalizationOperations {

    @StoredId (localized = true)
    protected By wizardProgressText;
    @StoredId (localized = true)
    protected By chatNow;
    @StoredId (localized = true)
    protected By additionalDetailsTitle;
    @StoredId (localized = true)
    protected By birthDateTitle;
    @StoredId
    protected By birthDateDay;
    @StoredId
    protected By birthDateDayDropDownElements;
    @StoredId (localized = true)
    protected By birthDateDayDropdownCaption;
    @StoredId
    protected By birthDayDropDownSelectItem;
    @StoredId
    protected By birthDateMonth;
    @StoredId (localized = true)
    protected By birthDateMonthDropdownCaption;
    @StoredId
    protected By birthDateYear;
    @StoredId (localized = true)
    protected By birthDateYearDropdownCaption;
    @StoredId (localized = true)
    protected By countryOfBirthTitle;
    @StoredId
    protected By countryOfBirth;
    @StoredId (localized = true)
    protected By countryOfBirthDropdownCaption;
    @StoredId
    protected By countryOfBirthDropdownDataError;
    @StoredId (localized = true)
    protected By nationalityTitle;
    @StoredId
    protected By nationality;
    @StoredId (localized = true)
    protected By nationalityDropdownCaption;
    @StoredId
    protected By nationalityDropdownDataError;
    @StoredId
    protected By submit;

    protected AbstractAccountAdditionalDetails(WebDriver webDriver) {
        super(webDriver);
    }

}
