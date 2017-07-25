package com.betamedia.atom.core.api.crm.form.entities;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static com.betamedia.atom.core.api.crm.form.entities.QuestionnaireAnswers.*;

/**
 * @author mbelyaev
 * @since 7/25/17
 */
public class AmlInformation {
    public final String employerName;
    public final String taxResidenceCountry;
    public final String isUSReportable;
    public final String hasTaxIdentificationNumber;
    public final String taxIdentificationNumber;
    public final String socialSecurityNumber;
    public final int expectedScore;

    private AmlInformation(String employerName, String taxResidenceCountry, String isUSReportable, String hasTaxIdentificationNumber, String taxIdentificationNumber, String socialSecurityNumber, int expectedScore) {
        this.employerName = employerName;
        this.taxResidenceCountry = taxResidenceCountry;
        this.isUSReportable = isUSReportable;
        this.hasTaxIdentificationNumber = hasTaxIdentificationNumber;
        this.taxIdentificationNumber = taxIdentificationNumber;
        this.socialSecurityNumber = socialSecurityNumber;
        this.expectedScore = expectedScore;
    }

    public static AmlInformationBuilder builder() {
        return new AmlInformationBuilder();
    }

    public static class AmlInformationBuilder {
        private String employerName;
        private String taxResidenceCountry;
        private IsUSReportable isUSReportable;
        private HasTaxIdentificationNumber hasTaxIdentificationNumber;
        private String taxIdentificationNumber;
        private String socialSecurityNumber;


        private AmlInformationBuilder() {
        }

        public AmlInformationBuilder withEmployerName(String employerName) {
            this.employerName = employerName;
            return this;
        }

        public AmlInformationBuilder withTaxResidenceCountry(String taxResidenceCountry) {
            this.taxResidenceCountry = taxResidenceCountry;
            return this;
        }

        public AmlInformationBuilder withUSReportabilityStatus(IsUSReportable isUSReportable) {
            this.isUSReportable = isUSReportable;
            return this;
        }

        public AmlInformationBuilder withTaxIdentificationNumberStatus(HasTaxIdentificationNumber hasTaxIdentificationNumber) {
            this.hasTaxIdentificationNumber = hasTaxIdentificationNumber;
            return this;
        }

        public AmlInformationBuilder withTaxIdentificationNumber(String taxIdentificationNumber) {
            this.taxIdentificationNumber = taxIdentificationNumber;
            return this;
        }

        public AmlInformationBuilder withSocialSecurityNumber(String socialSecurityNumber) {
            this.socialSecurityNumber = socialSecurityNumber;
            return this;
        }

        public AmlInformation build() {
            return new AmlInformation(
                    employerName,
                    taxResidenceCountry,
                    valueIfPresent(isUSReportable),
                    valueIfPresent(hasTaxIdentificationNumber),
                    taxIdentificationNumber,
                    socialSecurityNumber,
                    getExpectedScore());
        }

        private String valueIfPresent(Answer answer) {
            return Optional.ofNullable(answer).map(Answer::getValue).orElse(null);
        }

        private int getExpectedScore() {
            return Arrays.stream(this.getClass().getDeclaredFields())
                    .filter(field -> Answer.class.isAssignableFrom(field.getType()))
                    .map(field -> {
                        try {
                            return field.get(this);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException("", e);
                        }
                    })
                    .map(Answer.class::cast)
                    .filter(Objects::nonNull)
                    .mapToInt(Answer::getScore)
                    .sum();
        }

    }
}
