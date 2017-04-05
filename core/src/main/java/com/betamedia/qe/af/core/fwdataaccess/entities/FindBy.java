package com.betamedia.qe.af.core.fwdataaccess.entities;

/**
 * Created by mbelyaev on 3/29/17.
 */
public class FindBy {
    public final String locatorType;
    public final String value;

    public FindBy(String locatorType, String value) {
        this.locatorType = locatorType;
        this.value = value;
    }
}