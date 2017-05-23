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
    private int access;
    @Column(name = "bt_ExperienceLevel")
    private int experienceLevel;
    @Column(name = "bt_ExperienceScore")
    private double experienceScore;

    public int getExperienceLevel() {
        return experienceLevel;
    }

    public double getExperienceScore() {
        return experienceScore;
    }

    public int getAccess(){
        return access;}
}
