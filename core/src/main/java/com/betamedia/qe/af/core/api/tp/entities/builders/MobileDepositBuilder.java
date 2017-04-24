package com.betamedia.qe.af.core.api.tp.entities.builders;

import com.betamedia.qe.af.core.api.tp.adapters.impl.AbstractMobileCRMHTTPAdapter;
import com.betamedia.qe.af.core.api.tp.entities.request.MobileDepositRO;

/**
 * This is a builder for MobileDepositRO, which, in turn, is used by mobile crm adatper.
 *
 * Created by Oleksandr Losiev on 4/21/17.
 *
 * @see MobileDepositRO
 * @see AbstractMobileCRMHTTPAdapter
 */
public class MobileDepositBuilder {

    private String language = "en";
    private String currency = "USD";
    private Long amount = 100L;

    private String address = "301E45";
    private String city = "NYC";
    private String countryCode = "AR";
    private Integer zipCode = 10017;

    private Long creditCardNumber = 4444436501403986L;
    private Integer cvv2 = 685;
    private Integer expiryMonth = 12;
    private Integer expiryYear = 2022;
    private String holderFirstName = "Cosmo";
    private String holderLastName = "Kramer";

    private String tradingAccountId = "E8DE6CAC-A800-E611-A726-005056B73B53";
    private String tradingAccountName = null;

    public MobileDepositBuilder setLanguage(String language) {
        this.language = language;
        return this;
    }

    public MobileDepositBuilder setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public MobileDepositBuilder setAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    public MobileDepositBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public MobileDepositBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public MobileDepositBuilder setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public MobileDepositBuilder setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public MobileDepositBuilder setCreditCardNumber(Long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
        return this;
    }

    public MobileDepositBuilder setCvv2(Integer cvv2) {
        this.cvv2 = cvv2;
        return this;
    }

    public MobileDepositBuilder setExpiryMonth(Integer expiryMonth) {
        this.expiryMonth = expiryMonth;
        return this;
    }

    public MobileDepositBuilder setExpiryYear(Integer expiryYear) {
        this.expiryYear = expiryYear;
        return this;
    }

    public MobileDepositBuilder setHolderFirstName(String holderFirstName) {
        this.holderFirstName = holderFirstName;
        return this;
    }

    public MobileDepositBuilder setHolderLastName(String holderLastName) {
        this.holderLastName = holderLastName;
        return this;
    }

    public MobileDepositBuilder setTradingAccountId(String tradingAccountId) {
        this.tradingAccountId = tradingAccountId;
        return this;
    }

    public MobileDepositBuilder setTradingAccountName(String tradingAccountName) {
        this.tradingAccountName = tradingAccountName;
        return this;
    }

    public MobileDepositRO createMobileDepositRO() {
        return new MobileDepositRO(language, currency, amount.toString(), address, city, countryCode, zipCode.toString(),
                creditCardNumber.toString(), cvv2.toString(), expiryMonth.toString(), expiryYear.toString(),
                holderFirstName, holderLastName, tradingAccountId, tradingAccountName);
    }
}
