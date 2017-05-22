package com.betamedia.qe.af.core.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by mbelyaev on 5/19/17.
 */
@Entity
@Table(name = "ContactExtensionBase")
public class ContactExtensionInfo {
    @Id
    @Column(name = "ContactId")
    private String contactId;
    @Column(name = "bt_username")
    private String username;
    @Column(name = "bt_access")
    private Integer access;
    @Column(name = "bt_ExperienceLevel")
    private Integer experienceLevel;
    @Column(name = "bt_ExperienceScore")
    private Double experienceScore;

    public Integer getExperienceLevel() {
        return experienceLevel;
    }

    public Double getExperienceScore() {
        return experienceScore;
    }

    public Integer getAccess() {
        return access;
    }
}
