package com.betamedia.atom.core.api.web.form;

import com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.UserNamingStrategy;
import com.betamedia.atom.core.api.tp.entities.request.CustomerRO;
import com.betamedia.atom.core.utils.StringUtils;
import java.util.Objects;

/**
 * Created by vsnigur on 7/17/17.
 * Class for working with Customer Registration UI dialog
 */
public class CustomerRegistrationInfo {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneCountryPrefix;
    private String phoneNumber;
    private String country;
    private String currency;
    private String password;
    private boolean receiveNewsLetters;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneCountryPrefix() {
        return phoneCountryPrefix;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneCountryPrefix(String phoneCountryPrefix) {
        this.phoneCountryPrefix = phoneCountryPrefix;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setReceiveNewsLetters(boolean receiveNewsLetters) {
        this.receiveNewsLetters = receiveNewsLetters;
    }

    public boolean isReceiveNewsLetters() {
        return receiveNewsLetters;
    }

    private CustomerRegistrationInfo(String firstName, String lastName, String email, String phoneCountryPrefix, String phoneNumber, String country, String currency, String password, boolean receiveNewsLetters) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneCountryPrefix = phoneCountryPrefix;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.currency = currency;
        this.password = password;
        this.receiveNewsLetters = receiveNewsLetters;
    }

    public static CustomerRegistrationInfoBuilder builder(UserNamingStrategy namingStrategy) {
        return new CustomerRegistrationInfoBuilder(namingStrategy);
    }

    public static class CustomerRegistrationInfoBuilder {

        public static final int TEN_CHARS_IN_PHONE_NUMBER = 10;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneCountryPrefix = Country.UNITED_KINGDOM.getPhonePrefix();
        private String phoneNumber;
        private String country = Country.UNITED_KINGDOM.getName();
        private String currency = Currency.EUR.getFullName();
        private String password = CustomerRO.CustomerROBuilder.DEFAULT_PASSWORD;
        private boolean receiveNewsLetters = true;

        private UserNamingStrategy userNamingStrategy;

        private CustomerRegistrationInfoBuilder(UserNamingStrategy namingStrategy) {
            this.userNamingStrategy = namingStrategy;
        }

        public CustomerRegistrationInfoBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CustomerRegistrationInfoBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public CustomerRegistrationInfoBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public CustomerRegistrationInfoBuilder withPhoneCountryPrefix(String phoneCountryPrefix) {
            this.phoneCountryPrefix = phoneCountryPrefix;
            return this;
        }

        public CustomerRegistrationInfoBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public CustomerRegistrationInfoBuilder withCountry(String country) {
            this.country = country;
            return this;
        }

        public CustomerRegistrationInfoBuilder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public CustomerRegistrationInfoBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public CustomerRegistrationInfoBuilder withReceiveNewsLetters(boolean receiveNewsLetters) {
            this.receiveNewsLetters = receiveNewsLetters;
            return this;
        }

        public CustomerRegistrationInfo build() {
            return new CustomerRegistrationInfo(
                    userNamingStrategy.getFirstName(firstName),
                    userNamingStrategy.getLastName(lastName),
                    userNamingStrategy.getEmail(email),
                    phoneCountryPrefix,
                    Objects.nonNull(phoneNumber) ? phoneNumber : StringUtils.generateNumbersSequence(TEN_CHARS_IN_PHONE_NUMBER),
                    country,
                    currency,
                    password,
                    receiveNewsLetters
            );
        }
    }

    @Override
    public String toString() {
        return "CustomerRegistrationInfo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneCountryPrefix='" + phoneCountryPrefix + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", country='" + country + '\'' +
                ", currency='" + currency + '\'' +
                ", password='" + password + '\'' +
                ", receiveNewsLetters=" + receiveNewsLetters +
                '}';
    }
}
