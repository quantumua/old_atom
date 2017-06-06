package com.betamedia.atom.core.api.tp.entities.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CRMError {

    private final String code;
    private final String message;

    @JsonCreator
    public CRMError(@JsonProperty("code") String code, @JsonProperty("message") String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        CRMError crmError = (CRMError) o;

        return code != null ? code.equals(crmError.code) : crmError.code == null;

    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }

    @Override
    public String toString() {
        return code + ": " + message;
    }
}
