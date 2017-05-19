package com.betamedia.qe.af.core.api.crm.form.builders;

/**
 * Created by vsnigur on 5/19/17.
 */
public class RegisterInfoBuilder {
    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String telephonePrefix;
    private String telephoneNumber;
    private String birthDateDay;
    private String birthDateMonth;
    private String birthDateYear;
    private String accountBaseCurrency;
    private String password;

    public RegisterBuilderInfo build() {
        return new RegisterBuilderInfo(
                title,
                firstName,
                lastName,
                email,
                country,
                telephonePrefix,
                telephoneNumber,
                birthDateDay,
                birthDateMonth,
                birthDateYear,
                accountBaseCurrency,
                password
        );
    }

    public RegisterInfoBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public RegisterInfoBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public RegisterInfoBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public RegisterInfoBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public RegisterInfoBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public RegisterInfoBuilder withTelephonePrefix(String telephonePrefix) {
        this.telephonePrefix = telephonePrefix;
        return this;
    }

    public RegisterInfoBuilder withTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
        return this;
    }

    public RegisterInfoBuilder withBirthDateDay(String birthDateDay) {
        this.birthDateDay = birthDateDay;
        return this;
    }

    public RegisterInfoBuilder withBirthDateMonth(String birthDateMonth) {
        this.birthDateMonth = birthDateMonth;
        return this;
    }

    public RegisterInfoBuilder withBirthDateYear(String birthDateYear) {
        this.birthDateYear = birthDateYear;
        return this;
    }

    public RegisterInfoBuilder withAccountBaseCurrency(String accountBaseCurrency) {
        this.accountBaseCurrency = accountBaseCurrency;
        return this;
    }

    public RegisterInfoBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public class RegisterBuilderInfo {
        public final String title;
        public final String firstName;
        public final String lastName;
        public final String email;
        public final String country;
        public final String telephonePrefix;
        public final String telephoneNumber;
        public final String birthDateDay;
        public final String birthDateMonth;
        public final String birthDateYear;
        public final String accountBaseCurrency;
        public final String password;

        public RegisterBuilderInfo(String title, String firstName, String lastName, String email, String country, String telephonePrefix, String telephoneNumber, String birthDateDay, String birthDateMonth, String birthDateYear, String accountBaseCurrency, String password) {
            this.title = title;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.country = country;
            this.telephonePrefix = telephonePrefix;
            this.telephoneNumber = telephoneNumber;
            this.birthDateDay = birthDateDay;
            this.birthDateMonth = birthDateMonth;
            this.birthDateYear = birthDateYear;
            this.accountBaseCurrency = accountBaseCurrency;
            this.password = password;
        }
    }
}
