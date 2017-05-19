package com.betamedia.qe.af.core.api.crm.form.builders;

/**
 * Created by vsnigur on 5/18/17.
 */
public class CreditCardDepositInfoBuilder {
    private String depositAmount;
    private String creditCardNumber;
    private String cvv2;
    private String expiryDateMonth;
    private String expiryDateYear;
    private String cardHoldersFirstName;
    private String cardHoldersLastName;
    private String billingAddress;
    private String city;
    private String zipCode;
    private String country;

    public CreditCardDepositBuilderInfo build() {
        return new CreditCardDepositBuilderInfo(
                depositAmount,
                creditCardNumber,
                cvv2,
                expiryDateMonth,
                expiryDateYear,
                cardHoldersFirstName,
                cardHoldersLastName,
                billingAddress,
                city,
                zipCode,
                country
        );
    }

    public CreditCardDepositInfoBuilder withDepositAmount(String depositAmount) {
        this.depositAmount = depositAmount;
        return this;
    }

    public CreditCardDepositInfoBuilder withCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
        return this;
    }

    public CreditCardDepositInfoBuilder withCVV2(String cvv2) {
        this.cvv2 = cvv2;
        return this;
    }

    public CreditCardDepositInfoBuilder withExpiryDateMonth(String expiryDateMonth) {
        this.expiryDateMonth = expiryDateMonth;
        return this;
    }

    public CreditCardDepositInfoBuilder withExpiryDateYear(String expiryDateYear) {
        this.expiryDateYear = expiryDateYear;
        return this;
    }

    public CreditCardDepositInfoBuilder withCardHoldersFirstName(String cardHoldersFirstName) {
        this.cardHoldersFirstName = cardHoldersFirstName;
        return this;
    }

    public CreditCardDepositInfoBuilder withCardHoldersLastName(String cardHoldersLastName) {
        this.cardHoldersLastName = cardHoldersLastName;
        return this;
    }

    public CreditCardDepositInfoBuilder withBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
        return this;
    }

    public CreditCardDepositInfoBuilder withCity(String city) {
        this.city= city;
        return this;
    }

    public CreditCardDepositInfoBuilder withZipCode(String zipCode) {
        this.zipCode= zipCode;
        return this;
    }

    public CreditCardDepositInfoBuilder withCountry(String country) {
        this.country= country;
        return this;
    }


    public class CreditCardDepositBuilderInfo {
        public final String depositAmount;
        public final String creditCardNumber;
        public final String cvv2;
        public final String expiryDateMonth;
        public final String expiryDateYear;
        public final String cardHoldersFirstName;
        public final String cardHoldersLastName;
        public final String billingAddress;
        public final String city;
        public final String zipCode;
        public final String country;

        public CreditCardDepositBuilderInfo(String depositAmount, String creditCardNumber, String cvv2, String expiryDateMonth, String expiryDateYear, String cardHoldersFirstName, String cardHoldersLastName, String billingAddress, String city, String zipCode, String country) {
            this.depositAmount = depositAmount;
            this.creditCardNumber = creditCardNumber;
            this.cvv2 = cvv2;
            this.expiryDateMonth = expiryDateMonth;
            this.expiryDateYear = expiryDateYear;
            this.cardHoldersFirstName = cardHoldersFirstName;
            this.cardHoldersLastName = cardHoldersLastName;
            this.billingAddress = billingAddress;
            this.city = city;
            this.zipCode = zipCode;
            this.country = country;
        }
    }
}
