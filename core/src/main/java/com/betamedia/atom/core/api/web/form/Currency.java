package com.betamedia.atom.core.api.web.form;

/**
 * Created by vsnigur on 7/17/17.
 */
public enum Currency {
    EUR("EUR", "Euro (EUR)"),
    GBP("GBP", "British Pound (GBP)"),
    CHF("CHF", "Swiss franc (CHF)"),
    TRY("TRY", "Turkish lira (TRY)"),
    RUB("RUB", "Russian ruble (RUB)"),
    ZAR("ZAR", "SA Rand (ZAR)"),
    USD("USD", "US Dollar (USD)");

    private String shortName;
    private String fullName;

    Currency(String shortName, String fullName) {
        this.shortName = shortName;
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getFullName() {
        return fullName;
    }
}
