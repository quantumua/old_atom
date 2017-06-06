package com.betamedia.atom.core.api.crm.form.entities;

import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;


/**
 * Created by vsnigur on 5/18/17.
 */
public class AccountDetails {
    public final String title;
    public final String street;
    public final String streetNumber;
    public final String city;
    public final String phone2;
    public final String birthdayDay;
    public final String birthdayMonth;
    public final String birthdayYear;

    private AccountDetails(String title, String street, String streetNumber, String city, String phone2, String birthdayDay, String birthdayMonth, String birthdayYear) {
        this.title = title;
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.phone2 = phone2;
        this.birthdayDay = birthdayDay;
        this.birthdayMonth = birthdayMonth;
        this.birthdayYear = birthdayYear;
    }

    public static AccountDetailsBuilder builderFor(CustomerRO customerRO){
        return new AccountDetailsBuilder(customerRO);
    }

    public static class AccountDetailsBuilder {
        private String title;
        private String street;
        private String streetNumber = "streetNumber";
        private String city;
        private String phone2;
        private String birthdayDay;
        private String birthdayMonth;
        private String birthdayYear;

        private AccountDetailsBuilder(CustomerRO customer){
            title = customer.getTitle();
            street = customer.getStreet();
            city = customer.getCity();
            phone2 = customer.getPhone();
            birthdayDay = customer.getBirthdayDayOfMonth();
            birthdayMonth = customer.getBirthdayMonth();
            birthdayYear = customer.getBirthdayYear();
        }

        public AccountDetailsBuilder setStreetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public AccountDetails build() {
            return new AccountDetails(
                    title,
                    street,
                    streetNumber,
                    city,
                    phone2,
                    birthdayDay,
                    birthdayMonth,
                    birthdayYear
            );
        }

    }
}
