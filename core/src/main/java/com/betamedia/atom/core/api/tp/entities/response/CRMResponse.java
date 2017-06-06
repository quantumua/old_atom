package com.betamedia.atom.core.api.tp.entities.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
/**
 * @author Maksym Tsybulskyy
 *         Date: 3/21/17.
 */
public class CRMResponse<T> {

    private final List<String> html;
    private final T result;
    private final List<CRMError> errors;
    private final String method;
    private final String action;

    @JsonCreator
    public CRMResponse(@JsonProperty("html") List<String> html, @JsonProperty("result") T result,
                       @JsonProperty("errors") List<CRMError> errors, @JsonProperty("method") String method,
                       @JsonProperty("action") String action) {
        this.html = html;
        this.result = result;
        this.errors = errors;
        this.method = method;
        this.action = action;
    }

    public List<String> getHtml() {
        return html;
    }

    public T getResult() {
        return result;
    }

    public List<CRMError> getErrors() {
        return errors;
    }

    public String getMethod() {
        return method;
    }

    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return "CRMResponse{" +
                "html=" + html +
                ", result=" + result +
                ", errors=" + errors +
                ", method='" + method + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
