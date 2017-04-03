package com.betamedia.qe.af.core.api.tp.entities.builders;

import com.betamedia.qe.af.core.api.tp.entities.request.AccountRO;

import static com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder.CHARS_IN_ID;
import static com.betamedia.qe.af.core.api.tp.entities.builders.CustomerBuilder.TP_AUTOMATION_PREFIX;
import static com.betamedia.qe.af.core.utils.StringUtils.generateRandomId;

public class AccountBuilder {

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

    public AccountBuilder setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public AccountBuilder setAccountType(String accountType) {
        this.accountType = accountType;
        return this;
    }

    public AccountBuilder setBrandDisplayId(String brandDisplayId) {
        this.brandDisplayId = brandDisplayId;
        return this;
    }

    public AccountBuilder setPriceType(String priceType) {
        this.priceType = priceType;
        return this;
    }

    public AccountBuilder setAcountGroupId(String acountGroupId) {
        this.acountGroupId = acountGroupId;
        return this;
    }

    public AccountBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AccountBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AccountBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public AccountBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public AccountBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public AccountBuilder setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
        return this;
    }

    public AccountBuilder setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getBrandDisplayId() {
        return brandDisplayId;
    }

    public AccountRO createAccountRO() {
        firstName = firstName!=null? firstName : TP_AUTOMATION_PREFIX + generateRandomId(CHARS_IN_ID);
        return new AccountRO(currency, accountType, brandDisplayId, priceType, acountGroupId, firstName, lastName, description, email, phone, accountStatus, country);
    }
}