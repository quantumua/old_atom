package com.betamedia.atom.core.api.crm.form.entities;

import com.opencsv.bean.CsvBindByName;

/**
 * @author Leonid Artemiev
 * @since 07/12/2017
 */
public class AccountAdditionalDetailsData {
    @CsvBindByName
    private String employmentStatus;
    
    @CsvBindByName
    private String progressText;
    
    @CsvBindByName
    private String chatLink;
    
    @CsvBindByName
    private String additionalDetailsTitle;
    
    @CsvBindByName
    private String birthDate;
    
    @CsvBindByName
    private String birthDateDay;

    @CsvBindByName
    private String birthDateMonth;
    
    @CsvBindByName
    private String birthDateYear;
    
    @CsvBindByName
    private String countryOfBirthCodeText;
    
    @CsvBindByName
    private String countryOfBirthCode;
    
    @CsvBindByName
    private String nationalityCodeText;
    
    @CsvBindByName
    private String nationalityCode;
    
    @CsvBindByName
    private String submitButton;

    public String getEmploymentStatus() {
        return employmentStatus;
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

    public String getBirthDate() {
        return birthDate;
    }

    public String getBirthDateDay() {
        return birthDateDay;
    }

    public String getBirthDateMonth() {
        return birthDateMonth;
    }

    public String getBirthDateYear() {
        return birthDateYear;
    }

    public String getCountryOfBirthCodeText() {
        return countryOfBirthCodeText;
    }

    public String getCountryOfBirthCode() {
        return countryOfBirthCode;
    }

    public String getNationalityCodeText() {
        return nationalityCodeText;
    }

    public String getNationalityCode() {
        return nationalityCode;
    }

    public String getSubmitButton() {
        return submitButton;
    }

}
