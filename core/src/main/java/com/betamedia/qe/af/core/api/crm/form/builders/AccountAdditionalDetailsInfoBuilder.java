package com.betamedia.qe.af.core.api.crm.form.builders;

/**
 * Created by vsnigur on 5/18/17.
 */
public class AccountAdditionalDetailsInfoBuilder {
    private String birthDateDay;
    private String birthDateMonth;
    private String birthDateYear;
    private String countryOfBirth;
    private String nationality;

    public AccountAdditionalDetailsInfoBuilder withBirthDateDay(String birthDateDay) {
        this.birthDateDay = birthDateDay;
        return this;
    }

    public AccountAdditionalDetailsInfoBuilder withBirthDateMonth(String birthDateMonth) {
        this.birthDateMonth = birthDateMonth;
        return this;
    }

    public AccountAdditionalDetailsInfoBuilder withBirthDateYear(String birthDateYear) {
        this.birthDateYear = birthDateYear;
        return this;
    }

    public AccountAdditionalDetailsInfoBuilder withCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
        return this;
    }

    public AccountAdditionalDetailsInfoBuilder withNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public AccountAdditionalDetailsBuilderInfo build() {
        return new AccountAdditionalDetailsBuilderInfo(
                birthDateDay,
                birthDateMonth,
                birthDateYear,
                countryOfBirth,
                nationality);
    }

    public class AccountAdditionalDetailsBuilderInfo {
        public final String birthDateDay;
        public final String birthDateMonth;
        public final String birthDateYear;
        public final String countryOfBirth;
        public final String nationality;

        public AccountAdditionalDetailsBuilderInfo(String birthDateDay, String birthDateMonth, String birthDateYear, String countryOfBirth, String nationality) {
            this.birthDateDay = birthDateDay;
            this.birthDateMonth = birthDateMonth;
            this.birthDateYear = birthDateYear;
            this.countryOfBirth = countryOfBirth;
            this.nationality = nationality;
        }
    }
}

