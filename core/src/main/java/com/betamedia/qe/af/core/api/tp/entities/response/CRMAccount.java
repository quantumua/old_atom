package com.betamedia.qe.af.core.api.tp.entities.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * @author Maksym Tsybulskiy
 *         Date: 1/18/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CRMAccount {

    private String id;
    private String currency;
    private String displayId;
    private String accountType;
    private BigDecimal balance;
    private String platform;
    private String product;
    private BigDecimal pendingAmount;


    @JsonCreator
    public CRMAccount(@JsonProperty("Id") String id, @JsonProperty("Currency") String currency, @JsonProperty("TradingAccountName") String displayId, @JsonProperty("AccountType") String accountType,
                      @JsonProperty("Platform")  String platform, @JsonProperty("Product") String product, @JsonProperty("PendingAmount") BigDecimal pendingAmount) {
        this.id = id;
        this.currency = currency;
        this.displayId = displayId;
        this.accountType = accountType;
        this.balance = new BigDecimal(0);
        this.platform = platform;
        this.product = product;
        this.pendingAmount = pendingAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDisplayId() {
        return displayId;
    }

    public void setDisplayId(String displayId) {
        this.displayId = displayId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getPendingAmount() {
        return pendingAmount;
    }

    public void setPendingAmount(BigDecimal pendingAmount) {
        this.pendingAmount = pendingAmount;
    }


    @Override
    public String toString() {
        return "CRMAccount{" +
                "id='" + id + '\'' +
                ", currency='" + currency + '\'' +
                ", displayId='" + displayId + '\'' +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                ", platform='" + platform + '\'' +
                ", product='" + product + '\'' +
                ", pendingAmount=" + pendingAmount +
                '}';
    }

    public enum PlatformType {
        SCIPIO("scipio"),
        PANDA("panda");

        private String value;

        PlatformType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static PlatformType parse(String name) {
            for (PlatformType value : values()) {
                if (value.getValue().equals(name)) {
                    return value;
                }
            }
            throw new IllegalArgumentException(
                    "No PlatformType constant for " + name);
        }
    }
}
