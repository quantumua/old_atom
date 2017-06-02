package com.betamedia.qe.af.core.fwdataaccess.entities;

import com.betamedia.qe.af.core.fwdataaccess.converters.LocalDateTimeConverter;
import com.betamedia.qe.af.core.fwdataaccess.repository.util.Index;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

import java.time.LocalDateTime;

import com.betamedia.qe.af.core.fwdataaccess.repository.util.RepositoryVersion;

/**
 * @author mbelyaev
 * @since 2/16/17
 */
public class PageElementLocation {

    @CsvBindByName
    private String pageObjectName;
    @CsvBindByName
    private String locatorId;
    @CsvBindByName
    private String implementationVersion;
    @CsvCustomBindByName(converter = LocalDateTimeConverter.class)
    private LocalDateTime revisionDate;
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

    public RepositoryVersion getRepositoryVersion() {
        return new RepositoryVersion(implementationVersion, revisionDate);
    }

    public String getValue() {
        return value;
    }

    public String getLocatorType() {
        return locatorType;
    }

    public FindBy getFindBy(){
        return new FindBy(locatorType, value);
    }

    public Index getIndex(){
        return new Index(pageObjectName, locatorId);
    }
}
