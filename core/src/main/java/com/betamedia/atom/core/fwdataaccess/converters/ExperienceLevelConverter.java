package com.betamedia.atom.core.fwdataaccess.converters;

import com.betamedia.atom.core.api.crm.form.entities.OnboardingWizardConditions;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

/**
 * Created by Oleksandr Losiev on 5/19/17.
 */
public class ExperienceLevelConverter extends AbstractBeanField {

    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, CsvConstraintViolationException {
        return OnboardingWizardConditions.ExperienceLevel.valueOf(s);
    }
}
