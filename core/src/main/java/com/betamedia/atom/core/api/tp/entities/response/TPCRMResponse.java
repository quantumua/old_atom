package com.betamedia.atom.core.api.tp.entities.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
public class TPCRMResponse<T> {

    private final T result;
    private final List<CRMError> errors;

    @JsonCreator
    public TPCRMResponse(@JsonProperty("result") T result, @JsonProperty("errors") List<CRMError> errors) {
        this.result = result;
        this.errors = errors;
    }


    public T getResult() {
        return result;
    }

    public List<CRMError> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "TPCRMResponse{" +
                "result=" + result +
                ", errors=" + errors +
                '}';
    }
}
