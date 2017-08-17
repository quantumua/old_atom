package com.betamedia.atom.core.api.crm.form.entities;

/**
 * Created by vsnigur on 5/18/17.
 */
public class AccountAdditionalDetails {
    public final String birthDateDay;
    public final String birthDateMonth;
    public final String birthDateYear;
    public final String countryOfBirth;
    public final String nationality;

    private AccountAdditionalDetails(String birthDateDay, String birthDateMonth, String birthDateYear, String countryOfBirth, String nationality) {
        this.birthDateDay = birthDateDay;
        this.birthDateMonth = birthDateMonth;
        this.birthDateYear = birthDateYear;
        this.countryOfBirth = countryOfBirth;
        this.nationality = nationality;
    }

    public static AccountAdditionalDetailsBuilder builder(){
        return new AccountAdditionalDetailsBuilder();
    }

    @Override
    public String toString() {
        return "AccountAdditionalDetails{" +
                "birthDateDay='" + birthDateDay + '\'' +
                ", birthDateMonth='" + birthDateMonth + '\'' +
                ", birthDateYear='" + birthDateYear + '\'' +
                ", countryOfBirth='" + countryOfBirth + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }

    public static class AccountAdditionalDetailsBuilder {
        private String birthDateDay = "2";
        private String birthDateMonth = "2";
        private String birthDateYear = "1992";
        private String countryOfBirth = "TG";
        private String nationality = "TG";

        private AccountAdditionalDetailsBuilder(){}
        
        public String getDefaultBirthDate () {
        	return birthDateMonth + "/" + birthDateDay + "/" + birthDateYear;
        }
        
        public String getDefaultNationality() {
        	return nationality;
        }

        public String getDefaultCountryOfBirth() {
        	return countryOfBirth;
        }
        
        public AccountAdditionalDetailsBuilder withBirthDateDay(String birthDateDay) {
            this.birthDateDay = birthDateDay;
            return this;
        }

        public AccountAdditionalDetailsBuilder withBirthDateMonth(String birthDateMonth) {
            this.birthDateMonth = birthDateMonth;
            return this;
        }

        public AccountAdditionalDetailsBuilder withBirthDateYear(String birthDateYear) {
            this.birthDateYear = birthDateYear;
            return this;
        }

        public AccountAdditionalDetailsBuilder withCountryOfBirth(String countryOfBirth) {
            this.countryOfBirth = countryOfBirth;
            return this;
        }

        public AccountAdditionalDetailsBuilder withNationality(String nationality) {
            this.nationality = nationality;
            return this;
        }

        public AccountAdditionalDetails build() {
            return new AccountAdditionalDetails(
                    birthDateDay,
                    birthDateMonth,
                    birthDateYear,
                    countryOfBirth,
                    nationality);
        }

    }
}
