package com.betamedia.qe.af.core.fwdataaccess.entities;

import com.opencsv.bean.CsvBindByName;

/**
 * Created by mbelyaev on 2/16/17.
 */
public class PageElementLocation {

    @CsvBindByName
    private String pageObjectName;

    @CsvBindByName
    private String locatorId;

    @CsvBindByName
    private String build;

    @CsvBindByName
    private String locatorType;

    @CsvBindByName
    private String value;

    public String getPageObjectName() {
        return pageObjectName;
    }

    public String getLocatorId() {
        return locatorId;
    }

    public String getBuild() {
        return build;
    }

    public String getValue() {
        return value;
    }

    public String getLocatorType() {
        return locatorType;
    }
}
