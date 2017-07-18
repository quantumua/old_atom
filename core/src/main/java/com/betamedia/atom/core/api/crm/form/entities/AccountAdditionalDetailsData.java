package com.betamedia.atom.core.api.crm.form.entities;

import com.opencsv.bean.CsvBindByName;

/**
 * @author Leonid Artemiev
 * @since 07/12/2017
 */
public class AccountAdditionalDetailsData {
    @CsvBindByName
    private String Language;
    
    @CsvBindByName
    private String progressText;
    
    @CsvBindByName
    private String chatLink;
    
    @CsvBindByName
    private String additionalDetailsTitle;
    
    @CsvBindByName
    private String birthDateTitle;
    
    @CsvBindByName
    private String birthDateDayDropdownCaption;

    @CsvBindByName
    private String birthDateMonthDropdownCaption;
    
    @CsvBindByName
    private String birthDateYearDropdownCaption;
    
    @CsvBindByName
    private String countryOfBirthTitle;
    
    @CsvBindByName
    private String countryOfBirthDropdownCaption;

	@CsvBindByName
    private String countryOfBirthDropdownDataError;
    
    @CsvBindByName
    private String nationalityTitle;
    
    @CsvBindByName
    private String nationalityDropdownCaption;
    
    @CsvBindByName
    private String nationalityDropdownDataError;
    
    @CsvBindByName
    private String submitButton;

	public String getLanguage() {
		return Language;
	}

	public String getProgressText() {
		return progressText;
	}

	public String getChatLink() {
		return chatLink;
	}

	public String getAdditionalDetailsTitle() {
		return additionalDetailsTitle;
	}

	public String getBirthDateTitle() {
		return birthDateTitle;
	}

	public String getBirthDateDayDropdownCaption() {
		return birthDateDayDropdownCaption;
	}

	public String getBirthDateMonthDropdownCaption() {
		return birthDateMonthDropdownCaption;
	}

	public String getBirthDateYearDropdownCaption() {
		return birthDateYearDropdownCaption;
	}

	public String getCountryOfBirthTitle() {
		return countryOfBirthTitle;
	}

	public String getCountryOfBirthDropdownCaption() {
		return countryOfBirthDropdownCaption;
	}

	public String getCountryOfBirthDropdownDataError() {
		return countryOfBirthDropdownDataError;
	}

	public String getNationalityTitle() {
		return nationalityTitle;
	}

	public String getNationalityDropdownCaption() {
		return nationalityDropdownCaption;
	}

	public String getNationalityDropdownDataError() {
		return nationalityDropdownDataError;
	}

	public String getSubmitButton() {
		return submitButton;
	}
}
