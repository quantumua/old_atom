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

    @StoredId
    protected By wizardProgressText;
    @StoredId
    protected By chatNow;
    @StoredId
    protected By additionalDetailsTitle;
    @StoredId
    protected By birthDateTitle;
    @StoredId
    protected By birthDateDay;
    @StoredId
    protected By birthDateDayDropDownElements;
    @StoredId
    protected By birthDateDayDropdownCaption;
    @StoredId
    protected By birthDayDropDownSelectItem;
    @StoredId
    protected By birthDateMonth;
    @StoredId
    protected By birthDateMonthDropdownCaption;
    @StoredId
    protected By birthDateYear;
    @StoredId
    protected By birthDateYearDropdownCaption;
    @StoredId
    protected By countryOfBirthTitle;
    @StoredId
    protected By countryOfBirth;
    @StoredId
    protected By countryOfBirthDropdownCaption;
    @StoredId
    protected By countryOfBirthDropdownDataError;
    @StoredId
    protected By nationalityTitle;
    @StoredId
    protected By nationality;
    @StoredId
    protected By nationalityDropdownCaption;
    @StoredId
    protected By nationalityDropdownDataError;
    @StoredId
    protected By submit;

    protected AbstractAccountAdditionalDetails(WebDriver webDriver) {
        super(webDriver);
    }

}
