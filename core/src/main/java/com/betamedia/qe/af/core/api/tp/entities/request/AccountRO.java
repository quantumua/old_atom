package com.betamedia.qe.af.core.api.tp.entities.request;

import java.util.Objects;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/28/17.
 */
public class AccountRO {
    //    usd, eur or other
    private final String currency;
    //    demo/real
    private final String accountType;
    private final String brandId;
    //    market,  user or all
    private final String priceType;
    //    if empty, use default AG for brand.
    private final String acountGroupId;
    private final String firstName;
    private final String lastName;
    private final String description;
    private final String email;
    private final String phone;
    //    active, inactive or read_only
    private String accountStatus;
    //    us - United States, it - Italy, jp -Japan ...
    private String country;

    public AccountRO(String currency, String accountType, String brandId, String priceType, String acountGroupId, String firstName, String lastName, String description, String email, String phone, String accountStatus, String country) {
        Objects.requireNonNull(currency);
        Objects.requireNonNull(accountType);
        Objects.requireNonNull(brandId);
        this.currency = currency;
        this.accountType = accountType;
        this.brandId = brandId;
        this.priceType = priceType;
        this.acountGroupId = acountGroupId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.email = email;
        this.phone = phone;
        this.accountStatus = accountStatus;
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getBrandId() {
        return brandId;
    }

    public String getPriceType() {
        return priceType;
    }

    public String getAcountGroupId() {
        return acountGroupId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "AccountRO{" +
                "currency='" + currency + '\'' +
                ", accountType='" + accountType + '\'' +
                ", brandId='" + brandId + '\'' +
                ", priceType='" + priceType + '\'' +
                ", acountGroupId='" + acountGroupId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}