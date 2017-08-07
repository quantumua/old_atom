package com.betamedia.atom.core.api.crm.form.entities;


/**
 * Created by vsnigur on 5/18/17.
 */
public class CreditCardDeposit {
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

    private CreditCardDeposit(String depositAmount, String creditCardNumber, String cvv2, String expiryDateMonth, String expiryDateYear, String cardHoldersFirstName, String cardHoldersLastName, String billingAddress, String city, String zipCode, String country) {
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

    public static CreditCardDepositBuilder builder(){
        return new CreditCardDepositBuilder();
    }

    @Override
    public String toString() {
        return "CreditCardDeposit{" +
                "depositAmount='" + depositAmount + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", cvv2='" + cvv2 + '\'' +
                ", expiryDateMonth='" + expiryDateMonth + '\'' +
                ", expiryDateYear='" + expiryDateYear + '\'' +
                ", cardHoldersFirstName='" + cardHoldersFirstName + '\'' +
                ", cardHoldersLastName='" + cardHoldersLastName + '\'' +
                ", billingAddress='" + billingAddress + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public static class CreditCardDepositBuilder {
        private static final String DEFAULT_CREDIT_CARD_VISA = "4000027891380961";

        private String depositAmount = "50";
        private String creditCardNumber = DEFAULT_CREDIT_CARD_VISA;
        private String cvv2 = "123";
        private String expiryDateMonth = "1";
        private String expiryDateYear = "2020";
        private String cardHoldersFirstName = "Automation";
        private String cardHoldersLastName = "Automation";
        private String billingAddress = "Test Address";
        private String city = "Test City";
        private String zipCode = "00000";
        private String country;

        private CreditCardDepositBuilder(){}

        public CreditCardDepositBuilder withDepositAmount(String depositAmount) {
            this.depositAmount = depositAmount;
            return this;
        }

        public CreditCardDepositBuilder withCreditCardNumber(String creditCardNumber) {
            this.creditCardNumber = creditCardNumber;
            return this;
        }

        public CreditCardDepositBuilder withCVV2(String cvv2) {
            this.cvv2 = cvv2;
            return this;
        }

        public CreditCardDepositBuilder withExpiryDateMonth(String expiryDateMonth) {
            this.expiryDateMonth = expiryDateMonth;
            return this;
        }

        public CreditCardDepositBuilder withExpiryDateYear(String expiryDateYear) {
            this.expiryDateYear = expiryDateYear;
            return this;
        }

        public CreditCardDepositBuilder withCardHoldersFirstName(String cardHoldersFirstName) {
            this.cardHoldersFirstName = cardHoldersFirstName;
            return this;
        }

        public CreditCardDepositBuilder withCardHoldersLastName(String cardHoldersLastName) {
            this.cardHoldersLastName = cardHoldersLastName;
            return this;
        }

        public CreditCardDepositBuilder withBillingAddress(String billingAddress) {
            this.billingAddress = billingAddress;
            return this;
        }

        public CreditCardDepositBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public CreditCardDepositBuilder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public CreditCardDepositBuilder withCountry(String country) {
            this.country = country;
            return this;
        }

        public CreditCardDeposit build() {
            return new CreditCardDeposit(
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
    }
}
