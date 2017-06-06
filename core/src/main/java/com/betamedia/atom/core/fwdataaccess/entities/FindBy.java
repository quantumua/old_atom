package com.betamedia.atom.core.fwdataaccess.entities;

/**
 * @author mbelyaev
 * @since 3/29/17
 */
public class FindBy {
    public final String locatorType;
    public final String value;

    public FindBy(String locatorType, String value) {
        this.locatorType = locatorType;
        this.value = value;
    }
}