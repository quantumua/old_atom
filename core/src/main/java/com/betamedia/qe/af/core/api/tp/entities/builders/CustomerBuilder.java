package com.betamedia.qe.af.core.api.tp.entities.builders;

import com.betamedia.qe.af.core.api.tp.entities.Customer;

import static com.betamedia.qe.af.core.utils.StringUtils.generateRandomId;

public class CustomerBuilder {

    public static final String TP_AUTOMATION_PREFIX = "tp_automation_";
    public static final int CHARS_IN_ID = 6;

    private String userName;
    private String userPassword = "1234";
    private String currency = "usd";
    private String accountType = "real";
    private String brandDisplayId;
    private String priceType;
    private String acountGroupId;
    private String firstName;
    private String lastName;
    private String description;
    private String email;
    private String phone;
    private String accountStatus;
    private String country;

    public CustomerBuilder setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public CustomerBuilder setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }

    public CustomerBuilder setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public CustomerBuilder setAccountType(String accountType) {
        this.accountType = accountType;
        return this;
    }

    public CustomerBuilder setBrandDisplayId(String brandDisplayId) {
        this.brandDisplayId = brandDisplayId;
        return this;
    }

    public CustomerBuilder setPriceType(String priceType) {
        this.priceType = priceType;
        return this;
    }

    public CustomerBuilder setAcountGroupId(String acountGroupId) {
        this.acountGroupId = acountGroupId;
        return this;
    }

    public CustomerBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CustomerBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CustomerBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public CustomerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public CustomerBuilder setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
        return this;
    }

    public CustomerBuilder setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getBrandDisplayId() {
        return brandDisplayId;
    }

    public Customer createCustomer() {
        final int charsInId = 6;
        userName = userName!=null? userName : TP_AUTOMATION_PREFIX + generateRandomId(CHARS_IN_ID);
        return new Customer(userName, userPassword, currency, accountType, brandDisplayId, priceType, acountGroupId, firstName, lastName, description, email, phone, accountStatus, country);
    }
}