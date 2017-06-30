package com.betamedia.atom.core.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Oleksandr Losiev on 4/27/17.
 */
@Entity
@Table(name = "bt_trackinginfoBase")
public class TrackingInfo {

    public TrackingInfo() {
    }

    public TrackingInfo(String id) {
        this.id = id;
    }

    @Id
    @Column(name = "bt_trackinginfoId")
    private String id;

    @Column(name = "CreatedOn")
    private Date createdOn;

    public String getId() {
        return id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }
}
