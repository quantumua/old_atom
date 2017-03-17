package com.betamedia.qe.af.webservice.web.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/28/17.
 */
public class RunTestParams {
    @NotEmpty
    @Pattern(regexp = "^.*\\.properties$", message = "Support only *.properties files for SUT parameters")
    private String sut;
    //    @Pattern(regexp = "^.*\\.xml$", message = "Support only *.properties files for SUT parameters")
    @NotEmpty
    private List<String> suite;

    public RunTestParams() {
    }

    public RunTestParams(String sut, List<String> suite) {
        this.sut = sut;
        this.suite = suite;
    }

    public String getSut() {
        return sut;
    }

    public void setSut(String sut) {
        this.sut = sut;
    }

    public List<String> getSuite() {
        return suite;
    }

    public void setSuite(List<String> suite) {
        this.suite = suite;
    }

}
