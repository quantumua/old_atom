package com.betamedia.qe.af.core.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
@Entity
@Table(name = "tcversions")
public class TestLinkTestCase {

    public TestLinkTestCase() {
    }

    public TestLinkTestCase(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "tc_external_id")
    private Long externalId;

    @Column(name = "execution_type")
    private Long executionType;

    public Integer getId() {
        return id;
    }

    public Long getExternalId() {
        return externalId;
    }

    public Long getExecutionType() {
        return executionType;
    }
}
