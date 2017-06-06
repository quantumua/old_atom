package com.betamedia.atom.core.fwdataaccess.converters;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author mbelyaev
 * @since 5/31/17
 */
public class LocalDateTimeConverter extends AbstractBeanField<LocalDateTime> {
    public static final String DATE_PATTERN = "y-M-d H:m Z";

    @Override
    public Object convert(String value) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, CsvConstraintViolationException {
        return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(DATE_PATTERN));
    }
}
