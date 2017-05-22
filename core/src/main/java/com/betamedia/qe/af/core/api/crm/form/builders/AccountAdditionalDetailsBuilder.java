package com.betamedia.qe.af.core.api.crm.form.builders;

/**
 * Created by vsnigur on 5/18/17.
 */
public class AccountAdditionalDetailsBuilder {
    private String birthDateDay;
    private String birthDateMonth;
    private String birthDateYear;
    private String countryOfBirth;
    private String nationality;

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
    }
}

