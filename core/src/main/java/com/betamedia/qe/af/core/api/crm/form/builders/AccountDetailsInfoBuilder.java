package com.betamedia.qe.af.core.api.crm.form.builders;

/**
 * Created by vsnigur on 5/18/17.
 */
public class AccountDetailsInfoBuilder {
    private String title;
    private String street;
    private String streetNumber;
    private String city;
    private String countryPhonePrefix2;
    private String phone2;
    private String birthdayDay;
    private String birthdayMonth;
    private String birthdayYear;

    public AccountDetailsBuilderInfo build() {
        return new AccountDetailsBuilderInfo(
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

    public AccountDetailsInfoBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public AccountDetailsInfoBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public AccountDetailsInfoBuilder withStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public AccountDetailsInfoBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public AccountDetailsInfoBuilder withCountryPhonePrefix2(String countryPhonePrefix2) {
        this.countryPhonePrefix2 = countryPhonePrefix2;
        return this;
    }

    public AccountDetailsInfoBuilder withPhone2(String phone2) {
        this.phone2 = phone2;
        return this;
    }

    public AccountDetailsInfoBuilder withBirthdayDay(String birthdayDay) {
        this.birthdayDay = birthdayDay;
        return this;
    }

    public AccountDetailsInfoBuilder withBirthdayMonth(String birthdayMonth) {
        this.birthdayMonth = birthdayMonth;
        return this;
    }

    public AccountDetailsInfoBuilder withBirthdayYear(String birthdayYear) {
        this.birthdayYear= birthdayYear;
        return this;
    }


    public class AccountDetailsBuilderInfo {
        public final String title;
        public final String street;
        public final String streetNumber;
        public final String city;
        public final String countryPhonePrefix2;
        public final String phone2;
        public final String birthdayDay;
        public final String birthdayMonth;
        public final String birthdayYear;

        public AccountDetailsBuilderInfo(String title, String street, String streetNumber, String city, String countryPhonePrefix2, String phone2, String birthdayDay, String birthdayMonth, String birthdayYear) {
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
