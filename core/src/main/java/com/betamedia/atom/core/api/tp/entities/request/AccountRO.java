package com.betamedia.atom.core.api.tp.entities.request;

import java.util.Objects;

import static com.betamedia.atom.core.api.tp.entities.namingstrategies.customer.AbstractUserNamingStrategy.CHARS_IN_ID;
import static com.betamedia.atom.core.api.tp.entities.request.CustomerRO.CustomerROBuilder.TP_AUTOMATION_PREFIX;
import static com.betamedia.atom.core.utils.StringUtils.generateRandomId;

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

    private AccountRO(String currency, String accountType, String brandId, String priceType, String acountGroupId, String firstName, String lastName, String description, String email, String phone, String accountStatus, String country) {
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

    public static AccountROBuilder builder(){
        return new AccountROBuilder();
    }

    public static class AccountROBuilder {

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

        private AccountROBuilder(){}

        public AccountROBuilder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public AccountROBuilder setAccountType(String accountType) {
            this.accountType = accountType;
            return this;
        }

        public AccountROBuilder setBrandDisplayId(String brandDisplayId) {
            this.brandDisplayId = brandDisplayId;
            return this;
        }

        public AccountROBuilder setPriceType(String priceType) {
            this.priceType = priceType;
            return this;
        }

        public AccountROBuilder setAcountGroupId(String acountGroupId) {
            this.acountGroupId = acountGroupId;
            return this;
        }

        public AccountROBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public AccountROBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public AccountROBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public AccountROBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public AccountROBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public AccountROBuilder setAccountStatus(String accountStatus) {
            this.accountStatus = accountStatus;
            return this;
        }

        public AccountROBuilder setCountry(String country) {
            this.country = country;
            return this;
        }

        public String getBrandDisplayId() {
            return brandDisplayId;
        }

        public AccountRO build() {
            firstName = firstName!=null? firstName : TP_AUTOMATION_PREFIX + generateRandomId(CHARS_IN_ID);
            return new AccountRO(currency, accountType, brandDisplayId, priceType, acountGroupId, firstName, lastName, description, email, phone, accountStatus, country);
        }
    }
}
