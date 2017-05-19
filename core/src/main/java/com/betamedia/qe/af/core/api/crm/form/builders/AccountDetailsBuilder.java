package com.betamedia.qe.af.core.api.crm.form.builders;

/**
 * Created by vsnigur on 5/18/17.
 */
public class AccountDetailsBuilder {
    private String title;
    private String street;
    private String streetNumber;
    private String city;
    private String countryPhonePrefix2;
    private String phone2;
    private String birthdayDay;
    private String birthdayMonth;
    private String birthdayYear;

    public AccountDetails build() {
        return new AccountDetails(
                title,
                street,
                streetNumber,
                city,
                countryPhonePrefix2,
                phone2,
                birthdayDay,
                birthdayMonth,
                birthdayYear
        );
    }

    public AccountDetailsBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public AccountDetailsBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public AccountDetailsBuilder withStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public AccountDetailsBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public AccountDetailsBuilder withCountryPhonePrefix2(String countryPhonePrefix2) {
        this.countryPhonePrefix2 = countryPhonePrefix2;
        return this;
    }

    public AccountDetailsBuilder withPhone2(String phone2) {
        this.phone2 = phone2;
        return this;
    }

    public AccountDetailsBuilder withBirthdayDay(String birthdayDay) {
        this.birthdayDay = birthdayDay;
        return this;
    }

    public AccountDetailsBuilder withBirthdayMonth(String birthdayMonth) {
        this.birthdayMonth = birthdayMonth;
        return this;
    }

    public AccountDetailsBuilder withBirthdayYear(String birthdayYear) {
        this.birthdayYear= birthdayYear;
        return this;
    }


    public class AccountDetails {
        public final String title;
        public final String street;
        public final String streetNumber;
        public final String city;
        public final String countryPhonePrefix2;
        public final String phone2;
        public final String birthdayDay;
        public final String birthdayMonth;
        public final String birthdayYear;

        private AccountDetails(String title, String street, String streetNumber, String city, String countryPhonePrefix2, String phone2, String birthdayDay, String birthdayMonth, String birthdayYear) {
            this.title = title;
            this.street = street;
            this.streetNumber = streetNumber;
            this.city = city;
            this.countryPhonePrefix2 = countryPhonePrefix2;
            this.phone2 = phone2;
            this.birthdayDay = birthdayDay;
            this.birthdayMonth = birthdayMonth;
            this.birthdayYear = birthdayYear;
        }
    }
}
