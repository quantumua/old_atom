package com.betamedia.qe.af.core.api.crm.form.builders;

/**
 * Created by vsnigur on 5/19/17.
 */
public class RegisterBuilder {
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

    public Register build() {
        return new Register(
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

    public RegisterBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public RegisterBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public RegisterBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public RegisterBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public RegisterBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public RegisterBuilder withTelephonePrefix(String telephonePrefix) {
        this.telephonePrefix = telephonePrefix;
        return this;
    }

    public RegisterBuilder withTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
        return this;
    }

    public RegisterBuilder withBirthDateDay(String birthDateDay) {
        this.birthDateDay = birthDateDay;
        return this;
    }

    public RegisterBuilder withBirthDateMonth(String birthDateMonth) {
        this.birthDateMonth = birthDateMonth;
        return this;
    }

    public RegisterBuilder withBirthDateYear(String birthDateYear) {
        this.birthDateYear = birthDateYear;
        return this;
    }

    public RegisterBuilder withAccountBaseCurrency(String accountBaseCurrency) {
        this.accountBaseCurrency = accountBaseCurrency;
        return this;
    }

    public RegisterBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public class Register {
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

        private Register(String title, String firstName, String lastName, String email, String country, String telephonePrefix, String telephoneNumber, String birthDateDay, String birthDateMonth, String birthDateYear, String accountBaseCurrency, String password) {
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
