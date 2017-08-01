package com.betamedia.atom.core.api.tp.entities.request;

import com.betamedia.atom.core.api.tp.adapters.impl.AbstractMobileCRMHTTPAdapter;

/**
 * Request object for mobile CRM deposit.
 *
 * Created by Oleksandr Losiev on 4/21/17.
 *
 * @see MobileDepositROBuilder
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

    private MobileDepositRO(String lang, String currency, String amount, String address, String city, String countryCode,
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

    public static MobileDepositROBuilder builder(String tradingAccountId){
        return new MobileDepositROBuilder(tradingAccountId);
    }

    /**
     * This is a builder for MobileDepositRO, which, in turn, is used by mobile widgets adatper.
     *
     * Created by Oleksandr Losiev on 4/21/17.
     *
     * @see MobileDepositRO
     * @see AbstractMobileCRMHTTPAdapter
     */
    public static class MobileDepositROBuilder {

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

        private String tradingAccountId;
        private String tradingAccountName;

        private MobileDepositROBuilder(String tradingAccountId) {
            this.tradingAccountId = tradingAccountId;
        }

        public MobileDepositROBuilder setLanguage(String language) {
            this.language = language;
            return this;
        }

        public MobileDepositROBuilder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public MobileDepositROBuilder setAmount(Long amount) {
            this.amount = amount;
            return this;
        }

        public MobileDepositROBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public MobileDepositROBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public MobileDepositROBuilder setCountryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public MobileDepositROBuilder setZipCode(Integer zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public MobileDepositROBuilder setCreditCardNumber(Long creditCardNumber) {
            this.creditCardNumber = creditCardNumber;
            return this;
        }

        public MobileDepositROBuilder setCvv2(Integer cvv2) {
            this.cvv2 = cvv2;
            return this;
        }

        public MobileDepositROBuilder setExpiryMonth(Integer expiryMonth) {
            this.expiryMonth = expiryMonth;
            return this;
        }

        public MobileDepositROBuilder setExpiryYear(Integer expiryYear) {
            this.expiryYear = expiryYear;
            return this;
        }

        public MobileDepositROBuilder setHolderFirstName(String holderFirstName) {
            this.holderFirstName = holderFirstName;
            return this;
        }

        public MobileDepositROBuilder setHolderLastName(String holderLastName) {
            this.holderLastName = holderLastName;
            return this;
        }

        public MobileDepositROBuilder setTradingAccountId(String tradingAccountId) {
            this.tradingAccountId = tradingAccountId;
            return this;
        }

        public MobileDepositROBuilder setTradingAccountName(String tradingAccountName) {
            this.tradingAccountName = tradingAccountName;
            return this;
        }

        public MobileDepositRO build() {
            return new MobileDepositRO(language, currency, amount.toString(), address, city, countryCode, zipCode.toString(),
                    creditCardNumber.toString(), cvv2.toString(), expiryMonth.toString(), expiryYear.toString(),
                    holderFirstName, holderLastName, tradingAccountId, tradingAccountName);
        }
    }
}
