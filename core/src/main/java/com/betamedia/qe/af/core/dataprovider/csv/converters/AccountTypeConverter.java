package com.betamedia.qe.af.core.dataprovider.csv.converters;

import com.betamedia.qe.af.core.api.tp.entities.OnboardingWizardConditions;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

/**
 * Created by Oleksandr Losiev on 5/19/17.
 */
public class AccountTypeConverter extends AbstractBeanField {

    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, CsvConstraintViolationException {
        return OnboardingWizardConditions.AccountType.valueOf(s);
    }
}
