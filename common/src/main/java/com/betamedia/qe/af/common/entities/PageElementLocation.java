package com.betamedia.qe.af.common.entities;

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
    private String id;

    public String getPageObjectName() {
        return pageObjectName;
    }

    public String getElementId() {
        return elementId;
    }

    public String getVersion() {
        return version;
    }

    public String getId() {
        return id;
    }
}
