package com.betamedia.qe.af.entities.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/28/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RunTestParams {
    @NotEmpty
    @Pattern(regexp = "^.*\\.properties$", message = "Support only *.properties files for SUT parameters")
    private String sut;
    //    @Pattern(regexp = "^.*\\.xml$", message = "Support only *.properties files for SUT parameters")
    @NotEmpty
    private List<String> suite;
    private String data;
}
