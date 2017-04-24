package com.betamedia.qe.af.core.api.tp.entities.request;

import com.betamedia.qe.af.core.api.tp.entities.builders.MobileDepositBuilder;

/**
 * Request object for mobile CRM deposit.
 *
 * Created by Oleksandr Losiev on 4/21/17.
 *
 * @see MobileDepositBuilder
 */
public class MobileDepositRO {

    private String lang;
    private String currency;
    private String amount;

    private String address;
    private String city;
    private String countryCode;
    private String zip;

    private String ccNumber;
    private String cvv2;
    private String expMonth;
    private String expYear;
    private String holderFirstName;
    private String holderLastName;

    private String tradingAccountId;
    private String tradingAccountName;

    public MobileDepositRO(String lang, String currency, String amount, String address, String city, String countryCode,
                           String zip, String ccNumber, String cvv2, String expMonth, String expYear,
                           String holderFirstName, String holderLastName, String tradingAccountId, String tradingAccountName) {
        this.lang = lang;
        this.currency = currency;
        this.amount = amount;
        this.address = address;
        this.city = city;
        this.countryCode = countryCode;
        this.zip = zip;
        this.ccNumber = ccNumber;
        this.cvv2 = cvv2;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.holderFirstName = holderFirstName;
        this.holderLastName = holderLastName;
        this.tradingAccountId = tradingAccountId;
        this.tradingAccountName = tradingAccountName;
    }

    public String getLang() {
        return lang;
    }

    public String getCurrency() {
        return currency;
    }

    public String getAmount() {
        return amount;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getZip() {
        return zip;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public String getCvv2() {
        return cvv2;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public String getHolderFirstName() {
        return holderFirstName;
    }

    public String getHolderLastName() {
        return holderLastName;
    }

    public String getTradingAccountId() {
        return tradingAccountId;
    }

    public String getTradingAccountName() {
        return tradingAccountName;
    }
}
