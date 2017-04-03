package com.betamedia.qe.af.core.entities;

import com.opencsv.bean.CsvBindByName;

/**
 * Created by mbelyaev on 2/16/17.
 */
public class PageElementLocation {

    @CsvBindByName
    private String pageObjectName;

    @CsvBindByName
    private String elementId;

    @CsvBindByName
    private String version;

    @CsvBindByName
    private String how;

    @CsvBindByName
    private String value;

    public String getPageObjectName() {
        return pageObjectName;
    }

    public String getElementId() {
        return elementId;
    }

    public String getVersion() {
        return version;
    }

    public String getValue() {
        return value;
    }

    public String getHow() {
        return how;
    }
}
